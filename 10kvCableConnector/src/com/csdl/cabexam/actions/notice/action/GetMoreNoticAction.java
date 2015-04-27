package com.csdl.cabexam.actions.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.Notice;
import com.csdl.cabexam.dao.NoticeDao;
import com.csdl.cabexam.dao.imp.NoticeDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GetMoreNoticAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("noticeType");
		NoticeDao noticeDao = new NoticeDaoImp();
		List<Notice> noticeList = null;
		if(null!=type&&!"".equals(type)){
			noticeList = noticeDao.getNoticeByNoticeType(type, 20);
		}else{
			noticeList = noticeDao.getAllNoticeInfo();
		}
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("noticeType", type);
		return SUCCESS;
	}

}
