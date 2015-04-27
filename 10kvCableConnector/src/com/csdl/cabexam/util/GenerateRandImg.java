/*
 * 生成随机验证图片的方法
 * 1,生成随机图片,通过画笔得到
 * 2,生成随机字符,并在图片内画出
 * 3,在图片上随机生成线条,干扰点等等
 * 
 */


package com.csdl.cabexam.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class GenerateRandImg
{	
	//定义生成的图片宽度,高度,以及输出流
	private int width = 80;
	private int height = 20;
	private OutputStream out = null;
	private Random rand = new Random();
	
	//通过构造方法将输出量传入该类中,因为输出流必须从响应中得到
	public GenerateRandImg(OutputStream out){
		this.out = out;
	}
	
	//生成随机字符
	public String getRangString(int num){
		String str="";
		//定义随机字符取值范围
		final String SEED = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		//获得num个随机的字符
		for(int i=0;i<num;i++){
			//随机的从seed中取出第随机位字符,并存入str中
			char s = SEED.charAt(rand.nextInt(SEED.length()));
			str+=s;
		}
		
		return str;
	}
	
	//身材随机颜色
	public Color getRandColor(){
		int red = rand.nextInt(255);
		int green = rand.nextInt(255);
		int blue = rand.nextInt(255);
		return new Color(red,green,blue);
	}
	
	//生成随机图片,为了获得随机的验证码字符串到底是多少,为方法增加一个返回值
	public String getRandImg(){
		//获得bufferImage,图片输出流,用来向外部输出图片
		BufferedImage bfi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//从输出流中获得画笔
		Graphics g = bfi.getGraphics();
		//设置画笔颜色,即背景是,白色,然后画出方格
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		//画出白色矩形的边框,因为输出流设定图片只有width宽和height高,故画的矩形应该比整体小1个单位
		g.drawRect(0, 0, width-1, height-1);
		
		//画出随机字符
		//设置字体类型
		g.setFont(new Font("宋体", Font.BOLD, 25));
		//获得随机的字符串
		String strcode = this.getRangString(4);
		//将获得的字符串一个一个取出,并画出
		for(int i =0;i<strcode.length();i++){
			g.setColor(this.getRandColor());
			//从字符串中取出字符
			char temp = strcode.charAt(i);
			//画出字符串,因为只能画出字符串,所以将字符转换位字符串,每个字符相隔20+40*i的距离
			g.drawString(String.valueOf(temp), 6+20*i, 18);
		}
		
		//画出随机曲线
		for(int i =0;i<3;i++){
			g.setColor(this.getRandColor());
			int x1 = rand.nextInt(width);
			int y1 = rand.nextInt(height);
			int x2 = rand.nextInt(width);
			int y2 = rand.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		//画出干扰点
		for(int i=0;i<20;i++){
			g.setColor(this.getRandColor());
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			//画出椭圆,即为干扰点
			g.drawOval(x, y, 2, 2);
			g.fillOval(x, y, 2, 2);
		}
		
		//关闭画笔
		g.dispose();
		
		try
		{
			//将bufferimag输出到out流
			ImageIO.write(bfi, "jpeg", out);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strcode;
	}
	
}
