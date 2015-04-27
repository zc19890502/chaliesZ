package com.csdl.cabexam.actions.examee.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.examee.service.ExamineeService;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ExtraInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class CheckExamineeInfoAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		ExtraInfoDao extraDao = new ExtraInfoDaoImp();
		//ÅÐ¶ÏÊÇ·ñµÇÂ¼,Î´µÇÂ¼£¬·µ»Ønologin
		if(null!=userInfo&&!"".equals(userInfo)){
			ExamineeService examineeService = new ExamineeService();
			//ÅÐ¶ÏÊÇ·ñ±¨Ãû,Î´±¨Ãû·µ»Ønosignup
			if(examineeService.isExamineeSignUp(userInfo)){
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeInfo", examineeInfo);
				return "signup";
			}else{
				
				ExtraInfo extra = extraDao.getExtraInfo();
				request.setAttribute("extraInfo", extra);
				request.setAttribute("examineeState","noInfoCanSignUp");
				return "nosignup";
			}
		}else{
			return "nologin";
		}
	}

}
