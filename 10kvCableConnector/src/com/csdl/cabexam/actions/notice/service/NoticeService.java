package com.csdl.cabexam.actions.notice.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.csdl.cabexam.beans.Notice;

public class NoticeService {
	
	//删除公告信息文件,成功返回true，失败返回false
	public boolean deleteNoticePage(HttpServletRequest request,Notice notice){
		boolean flag = false;
		String noticeAbsPath = request.getRealPath(notice.getNoticeContent());
		//判断文件是否存在，如果存在，则删除文件
		File file = new File(noticeAbsPath);
		if(file.exists()){
			file.delete();
		}
		//判断公告所在时间文件夹是否为空，为空则删除文件夹
		String dirpath = noticeAbsPath.substring(0, noticeAbsPath.lastIndexOf("\\"));
		File dir = new File(dirpath);
		if(dir.exists()){
			if(dir.list().length==0){
				dir.delete();
			}
		}
		flag=true;
		return flag;
	}
	
	//添加公告信息文件，在服务器notice/page文件夹下生产公告信息的jsp页面，并返回页面的相对路径，存入数据库中
	public String addNotciePage(HttpServletRequest request,String jspRelativePath, Calendar calendar,String myEditor,Notice notice) throws IOException {
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		try{
			String filePath = ""; //模板路径
			filePath = request.getRealPath("/")+"ueditor/notice.jsp"; 
			String templateContent=""; 
			//读取模块文件 
			String str = "";
			br = new BufferedReader(new InputStreamReader( new FileInputStream(filePath), "UTF-8"));
			while ((str = br.readLine()) != null) {
				templateContent += str+"\n";
			}
			//替换模板内容
			myEditor = (null==myEditor)?"":myEditor;
			templateContent=templateContent.replaceAll("###content###",myEditor);
			templateContent=templateContent.replaceAll("###title###",notice.getNoticeHead()); 
			templateContent=templateContent.replaceAll("###date###",notice.getNoticeDate().toString().substring(0,notice.getNoticeDate().toString().indexOf(" ")));
			// 根据时间建立文件夹和文件
			String noticeFile = ""+calendar.get(calendar.YEAR)+calendar.get(calendar.MONTH)+calendar.get(calendar.DATE);
			String fileName = request.getRealPath("/page/notice/"+noticeFile);//保存jsp目标文件夹路径
			File file = new File(fileName);
			if(!file.exists()){
				file.mkdirs();
			}
			String jspPath = String.valueOf(calendar.getTimeInMillis()) +".jsp"; //jsp文件的名字
			String jspAbsolutePath = fileName+"/"+jspPath;//生成的html文件保存绝对路径 
			jspRelativePath = "page/notice/"+noticeFile+"/"+jspPath;//存入数据库的相对路径
			//输出jsp文件
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jspAbsolutePath), "utf-8"));
			bw.write(templateContent);
		} 
		catch(Exception e){ 
			e.printStackTrace();
		}finally{
			if(null!=br){
				br.close();
			}
			if(null!=bw){
				bw.close();
			}
		}
		return jspRelativePath;
	}
}
