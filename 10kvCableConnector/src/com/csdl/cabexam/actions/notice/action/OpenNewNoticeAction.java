package com.csdl.cabexam.actions.notice.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.Notice;
import com.csdl.cabexam.dao.NoticeDao;
import com.csdl.cabexam.dao.imp.NoticeDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class OpenNewNoticeAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		NoticeDao noticeDao = new NoticeDaoImp();
		String s_noticeId = request.getParameter("noticeId");
		if(null!=s_noticeId&&!"".equals(s_noticeId)){
			int noticeId = Integer.parseInt(s_noticeId);
			Notice notice = noticeDao.getNoticeByNoticeId(noticeId);
			request.setAttribute("notice", notice);
			return "updateNotice";
		}else{
			return "newNotice";
		}
	}
}
