package com.csdl.cabexam.actions.notice.action;

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

public class UpdateNotcePageAction extends ActionSupport{
	private Notice notice;
	private String myEditor;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDao cd = new CommonDaoImp();
		NoticeDao noticeDao = new NoticeDaoImp();
		NoticeService noticeServ = new NoticeService();
		String jspRelativePath = "";
		Calendar calendar = Calendar.getInstance();
		notice.setNoticeDate(new Timestamp(calendar.getTimeInMillis()));
		Notice notice_db = noticeDao.getNoticeByNoticeId(notice.getNoticeId());
		//判断是添加新页面还是更新旧页面
		if(null!=notice_db){
			//公告不为空。先删除公告文件，然后再添加
			noticeServ.deleteNoticePage(request, notice_db);
			jspRelativePath = noticeServ.addNotciePage(request, jspRelativePath, calendar,myEditor,notice);
			notice.setNoticeContent(jspRelativePath);
			cd.updateObject(notice);
			return SUCCESS;
		}else{
			return ERROR;
		}
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
