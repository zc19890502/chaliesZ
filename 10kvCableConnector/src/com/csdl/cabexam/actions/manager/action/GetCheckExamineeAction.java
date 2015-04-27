package com.csdl.cabexam.actions.manager.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GetCheckExamineeAction extends ActionSupport{

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request=ServletActionContext.getRequest();
		String exameState = request.getParameter("exameState");
		if(null!=exameState&&!"".equals(exameState)){
			ExamineeInfoDao examInfoDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> examineeInfoList = examInfoDao.getExamineeInfoByCheckState(exameState);
			request.setAttribute("examineeInfoList", examineeInfoList);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}

}
