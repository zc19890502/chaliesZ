package com.csdl.cabexam.actions.excel.action;




import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ManagerInfo;
import com.opensymphony.xwork2.ActionSupport;

public class doMangeRecord extends ActionSupport {
	
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=managerInfo&&!"".equals(managerInfo)){
			return SUCCESS;
		}else{
			return "mananologin";
		}
		}

	}

