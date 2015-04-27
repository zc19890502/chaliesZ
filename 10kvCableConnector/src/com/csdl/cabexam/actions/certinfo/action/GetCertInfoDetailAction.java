package com.csdl.cabexam.actions.certinfo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.DeductionRecordInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.DeductionRecordInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.DeductionRecordInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GetCertInfoDetailAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=managerInfo&&!"".equals(managerInfo)){
			String s_certId = request.getParameter("certInfoId");
			if(null!=s_certId&&!"".equals(s_certId)){
				int certInfoId = Integer.parseInt(s_certId);
				DeductionRecordInfoDao deducRecordDao = new DeductionRecordInfoDaoImp();
				CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
				CertificateInfo certInfo = certInfoDao.getCertInfoById(certInfoId);
				List<DeductionRecordInfo> deducRecordList = deducRecordDao.getDeducRecordBycertId(certInfoId);
				request.setAttribute("deducRecordList", deducRecordList);
				request.setAttribute("certInfo", certInfo);
				request.setAttribute("certInfoId", certInfoId);
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return "mananologin";
		}
	}

}
