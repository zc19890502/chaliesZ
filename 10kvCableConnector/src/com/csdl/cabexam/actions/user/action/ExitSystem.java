package com.csdl.cabexam.actions.user.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ExitSystem extends ActionSupport{

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().removeAttribute("userSession");
		return SUCCESS;
	}

}
