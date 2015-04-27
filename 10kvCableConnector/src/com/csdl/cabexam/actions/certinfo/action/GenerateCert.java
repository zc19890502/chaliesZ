package com.csdl.cabexam.actions.certinfo.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.certinfo.service.CertInfoService;
import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GenerateCert extends ActionSupport{

	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=managerInfo&&!"".equals(managerInfo)){
	        CertInfoService certinfoservice=new CertInfoService();
	        certinfoservice.generateAllCert();
	        return SUCCESS;
		}else{
			return "mananologin";
		}
		}
	}


