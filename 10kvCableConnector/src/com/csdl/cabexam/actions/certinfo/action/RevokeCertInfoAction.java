package com.csdl.cabexam.actions.certinfo.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.certinfo.service.CertInfoService;
import com.csdl.cabexam.beans.ManagerInfo;
import com.opensymphony.xwork2.ActionSupport;

public class RevokeCertInfoAction extends ActionSupport{

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
			if(null!=managerInfo&&!"".equals(managerInfo)){
			String s_certId = request.getParameter("certId");
			if(null!=s_certId&&!"".equals(s_certId)){
				int certInfoId = Integer.parseInt(s_certId);
				CertInfoService certInfoService = new CertInfoService();
				certInfoService.revokeCertInfo(certInfoId,managerInfo);
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return "mananologin";
		}
	}

}
