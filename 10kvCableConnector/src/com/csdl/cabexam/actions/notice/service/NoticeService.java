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
	
	//ɾ��������Ϣ�ļ�,�ɹ�����true��ʧ�ܷ���false
	public boolean deleteNoticePage(HttpServletRequest request,Notice notice){
		boolean flag = false;
		String noticeAbsPath = request.getRealPath(notice.getNoticeContent());
		//�ж��ļ��Ƿ���ڣ�������ڣ���ɾ���ļ�
		File file = new File(noticeAbsPath);
		if(file.exists()){
			file.delete();
		}
		//�жϹ�������ʱ���ļ����Ƿ�Ϊ�գ�Ϊ����ɾ���ļ���
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
	
	//��ӹ�����Ϣ�ļ����ڷ�����notice/page�ļ���������������Ϣ��jspҳ�棬������ҳ������·�����������ݿ���
	public String addNotciePage(HttpServletRequest request,String jspRelativePath, Calendar calendar,String myEditor,Notice notice) throws IOException {
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		try{
			String filePath = ""; //ģ��·��
			filePath = request.getRealPath("/")+"ueditor/notice.jsp"; 
			String templateContent=""; 
			//��ȡģ���ļ� 
			String str = "";
			br = new BufferedReader(new InputStreamReader( new FileInputStream(filePath), "UTF-8"));
			while ((str = br.readLine()) != null) {
				templateContent += str+"\n";
			}
			//�滻ģ������
			myEditor = (null==myEditor)?"":myEditor;
			templateContent=templateContent.replaceAll("###content###",myEditor);
			templateContent=templateContent.replaceAll("###title###",notice.getNoticeHead()); 
			templateContent=templateContent.replaceAll("###date###",notice.getNoticeDate().toString().substring(0,notice.getNoticeDate().toString().indexOf(" ")));
			// ����ʱ�佨���ļ��к��ļ�
			String noticeFile = ""+calendar.get(calendar.YEAR)+calendar.get(calendar.MONTH)+calendar.get(calendar.DATE);
			String fileName = request.getRealPath("/page/notice/"+noticeFile);//����jspĿ���ļ���·��
			File file = new File(fileName);
			if(!file.exists()){
				file.mkdirs();
			}
			String jspPath = String.valueOf(calendar.getTimeInMillis()) +".jsp"; //jsp�ļ�������
			String jspAbsolutePath = fileName+"/"+jspPath;//���ɵ�html�ļ��������·�� 
			jspRelativePath = "page/notice/"+noticeFile+"/"+jspPath;//�������ݿ�����·��
			//���jsp�ļ�
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
