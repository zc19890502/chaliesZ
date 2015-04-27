package com.csdl.cabexam.actions.user.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class GotoOneAction extends ActionSupport{

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userSession = (UserInfo) request.getSession().getAttribute("userSession");
		if(null!=userSession&&!"".equals(userSession)){
			return SUCCESS;
		}else{
			return "nologin";
		}
	}

}
