package com.csdl.cabexam.actions;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ExtraInfo;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;

public class GetExtraInfo extends ActionSupport {
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		ExtraInfoDao eiDao = new ExtraInfoDaoImp();
		ExtraInfo ei=eiDao.getExtraInfo();
		if(ei!=null){
			request.setAttribute("extraInfo", ei);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}	
}

