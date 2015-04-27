package com.csdl.cabexam.actions.certinfo.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.certinfo.service.CertInfoService;
import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class CheckCertByIdNumAndcertNum extends ActionSupport{

	public String check() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
	    String idnum =	request.getParameter("idnum");
		String certnum = request.getParameter("certinum");
		if(null!=idnum&&!"".equals(idnum)){
			idnum=idnum.trim();
		}
		if(null!=certnum&&!"".equals(certnum)){
			certnum=certnum.trim();
		}
		CertificateInfoDao ctdao=new CertificateInfoDaoImp();
		CertificateInfo certInfo= ctdao.getCertInfoByIdnumAndCertId(idnum, certnum);
		if(null!=certInfo){	
			request.setAttribute("certInfo", certInfo);
		}
	    return SUCCESS;

		}
	}


