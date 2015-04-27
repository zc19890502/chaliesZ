package com.csdl.cabexam.actions.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.Notice;
import com.csdl.cabexam.dao.NoticeDao;
import com.csdl.cabexam.dao.imp.NoticeDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GetAllNoticeAction extends ActionSupport{

	private String c_head;
	private String c_type;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String head = request.getParameter("head");
		String type = request.getParameter("type");
		if(null!=head&&!"".equals(head)){
			head = new String(head.getBytes("iso-8859-1"),"utf-8");
			c_head=head;
		}
		if(null!=type&&!"".equals(type)){
			type = new String(type.getBytes("iso-8859-1"),"utf-8");
			c_type=type;
		}
		NoticeDao noticeDao = new NoticeDaoImp();
		List<Notice> noticeList = noticeDao.getAllNoticeInfoByCondition(c_head,c_type);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("head", c_head);
		request.setAttribute("type", c_type);
		return SUCCESS;
	}
	public String getC_head() {
		return c_head;
	}
	public void setC_head(String c_head) {
		this.c_head = c_head;
	}
	public String getC_type() {
		return c_type;
	}
	public void setC_type(String c_type) {
		this.c_type = c_type;
	}

}
