/*
 * ���������֤ͼƬ�ķ���
 * 1,�������ͼƬ,ͨ�����ʵõ�
 * 2,��������ַ�,����ͼƬ�ڻ���
 * 3,��ͼƬ�������������,���ŵ�ȵ�
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
	//�������ɵ�ͼƬ���,�߶�,�Լ������
	private int width = 80;
	private int height = 20;
	private OutputStream out = null;
	private Random rand = new Random();
	
	//ͨ�����췽������������������,��Ϊ������������Ӧ�еõ�
	public GenerateRandImg(OutputStream out){
		this.out = out;
	}
	
	//��������ַ�
	public String getRangString(int num){
		String str="";
		//��������ַ�ȡֵ��Χ
		final String SEED = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		//���num��������ַ�
		for(int i=0;i<num;i++){
			//����Ĵ�seed��ȡ�������λ�ַ�,������str��
			char s = SEED.charAt(rand.nextInt(SEED.length()));
			str+=s;
		}
		
		return str;
	}
	
	//��������ɫ
	public Color getRandColor(){
		int red = rand.nextInt(255);
		int green = rand.nextInt(255);
		int blue = rand.nextInt(255);
		return new Color(red,green,blue);
	}
	
	//�������ͼƬ,Ϊ�˻���������֤���ַ��������Ƕ���,Ϊ��������һ������ֵ
	public String getRandImg(){
		//���bufferImage,ͼƬ�����,�������ⲿ���ͼƬ
		BufferedImage bfi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//��������л�û���
		Graphics g = bfi.getGraphics();
		//���û�����ɫ,��������,��ɫ,Ȼ�󻭳�����
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		//������ɫ���εı߿�,��Ϊ������趨ͼƬֻ��width���height��,�ʻ��ľ���Ӧ�ñ�����С1����λ
		g.drawRect(0, 0, width-1, height-1);
		
		//��������ַ�
		//������������
		g.setFont(new Font("����", Font.BOLD, 25));
		//���������ַ���
		String strcode = this.getRangString(4);
		//����õ��ַ���һ��һ��ȡ��,������
		for(int i =0;i<strcode.length();i++){
			g.setColor(this.getRandColor());
			//���ַ�����ȡ���ַ�
			char temp = strcode.charAt(i);
			//�����ַ���,��Ϊֻ�ܻ����ַ���,���Խ��ַ�ת��λ�ַ���,ÿ���ַ����20+40*i�ľ���
			g.drawString(String.valueOf(temp), 6+20*i, 18);
		}
		
		//�����������
		for(int i =0;i<3;i++){
			g.setColor(this.getRandColor());
			int x1 = rand.nextInt(width);
			int y1 = rand.nextInt(height);
			int x2 = rand.nextInt(width);
			int y2 = rand.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		//�������ŵ�
		for(int i=0;i<20;i++){
			g.setColor(this.getRandColor());
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			//������Բ,��Ϊ���ŵ�
			g.drawOval(x, y, 2, 2);
			g.fillOval(x, y, 2, 2);
		}
		
		//�رջ���
		g.dispose();
		
		try
		{
			//��bufferimag�����out��
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
