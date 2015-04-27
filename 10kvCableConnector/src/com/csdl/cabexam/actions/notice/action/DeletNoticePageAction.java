package com.csdl.cabexam.actions.notice.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.notice.service.NoticeService;
import com.csdl.cabexam.beans.Notice;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.NoticeDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.NoticeDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class DeletNoticePageAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String s_noticeId = request.getParameter("noticeId");
		NoticeDao noticeDao = new NoticeDaoImp();
		NoticeService noticeServ = new NoticeService();
		CommonDao cd = new CommonDaoImp();
		if(null!=s_noticeId&&!"".equals(s_noticeId)){
			int noticeId = Integer.parseInt(s_noticeId);
			Notice notice = noticeDao.getNoticeByNoticeId(noticeId);
			if(noticeServ.deleteNoticePage(request, notice)){
				//É¾³ýÊý¾Ý¿â¼ÇÂ¼
				cd.deleteObject(notice);
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return ERROR;
		}
	}
	
}
