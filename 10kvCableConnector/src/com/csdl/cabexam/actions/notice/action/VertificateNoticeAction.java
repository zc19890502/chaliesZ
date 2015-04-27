package com.csdl.cabexam.actions.notice.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.beans.Notice;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.NoticeDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.NoticeDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class VertificateNoticeAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		NoticeDao noticeDao = new NoticeDaoImp();
		CommonDao cd = new CommonDaoImp();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=managerInfo&&!"".equals(managerInfo)){
			String s_notcieId = request.getParameter("noticeId");
			if(null!=s_notcieId&&!"".equals(s_notcieId)){
				int noticeId = Integer.parseInt(s_notcieId);
				Notice notice = noticeDao.getNoticeByNoticeId(noticeId);
				if(null!=notice&&!"".equals(notice)){
					if("7".equals(notice.getNoticeType())){
						notice.setNoticeType("1");
					}
					if("8".equals(notice.getNoticeType())){
						notice.setNoticeType("2");
					}
					if("9".equals(notice.getNoticeType())){
						notice.setNoticeType("3");
					}
					cd.updateObject(notice);
					return SUCCESS;
				}else{
					return ERROR;
				}
			}else{
				return ERROR;
			}
		}else{
			return "mananologin";
		}
	}

}
