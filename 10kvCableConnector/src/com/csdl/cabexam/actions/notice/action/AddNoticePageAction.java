package com.csdl.cabexam.actions.notice.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.notice.service.NoticeService;
import com.csdl.cabexam.beans.Notice;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.NoticeDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.NoticeDaoImp;
import com.opensymphony.xwork2.ActionSupport;


public class AddNoticePageAction extends ActionSupport{
	private Notice notice;
	private String myEditor;
	public String execute() throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("~~~~~");
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDao cd = new CommonDaoImp();
		NoticeDao noticeDao = new NoticeDaoImp();
		NoticeService noticeServ = new NoticeService();
		String jspRelativePath = "";
		Calendar calendar = Calendar.getInstance();
		notice.setNoticeDate(new Timestamp(calendar.getTimeInMillis()));
		//判断是添加新页面还是更新旧页面
		jspRelativePath = noticeServ.addNotciePage(request, jspRelativePath, calendar,myEditor,notice);
		notice.setNoticeContent(jspRelativePath);
		cd.addObject(notice);
		return SUCCESS;
	}
	
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public String getMyEditor() {
		return myEditor;
	}
	public void setMyEditor(String myEditor) {
		this.myEditor = myEditor;
	}

}
