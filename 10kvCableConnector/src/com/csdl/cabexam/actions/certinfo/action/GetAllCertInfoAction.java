package com.csdl.cabexam.actions.certinfo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GetAllCertInfoAction extends ActionSupport{

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=managerInfo&&!"".equals(managerInfo)){
			CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
			List<CertificateInfo> certInfoList = certInfoDao.getAllCertInfo();
			request.setAttribute("certInfoList", certInfoList);
			return SUCCESS;
		}else{
			return "mananologin";
		}
	}

}
