package com.csdl.cabexam.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.Notice;
import com.csdl.cabexam.dao.NoticeDao;
import com.csdl.cabexam.dao.imp.NoticeDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GetIndexInfoAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		NoticeDao noticeDao = new NoticeDaoImp();
		List<Notice> noticeNewList = noticeDao.getNoticeByNoticeType("1",5);
		List<Notice> noticeAnounceList = noticeDao.getNoticeByNoticeType("2",10);
		List<Notice> noticeExamList = noticeDao.getNoticeByNoticeType("3",5);
		request.setAttribute("noticeNewList", noticeNewList);
		request.setAttribute("noticeAnounceList", noticeAnounceList);
		request.setAttribute("noticeExamList", noticeExamList);
		return SUCCESS;
	}

}
