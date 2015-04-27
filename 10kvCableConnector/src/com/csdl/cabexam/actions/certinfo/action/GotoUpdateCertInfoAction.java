package com.csdl.cabexam.actions.certinfo.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.DeductionRecordInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.DeductionRecordInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.DeductionRecordInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GotoUpdateCertInfoAction extends ActionSupport{

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=managerInfo&&!"".equals(managerInfo)){
			String s_certId = "";
			String certIdattr = (String) request.getAttribute("certattrId");
			if(null!=certIdattr&&!"".equals(certIdattr)){
				s_certId = certIdattr;
			}else{
				s_certId = request.getParameter("certId");
			}
			if(null!=s_certId&&!"".equals(s_certId)){
				int certInfoId = Integer.parseInt(s_certId);
				CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
				CertificateInfo certInfo = certInfoDao.getCertInfoById(certInfoId);
				request.setAttribute("certInfo", certInfo);
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return "mananologin";
		}
	}
	
	public String deleteCertInfo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=managerInfo&&!"".equals(managerInfo)){
			String s_deducId = request.getParameter("deducId");
			if(null!=s_deducId&&!"".equals(s_deducId)){
				int deducId = Integer.parseInt(s_deducId);
				DeductionRecordInfoDao deducDao = new DeductionRecordInfoDaoImp();
				CommonDao cd = new CommonDaoImp();
				DeductionRecordInfo deducInfo =  deducDao.getDeducRecordBydeducId(deducId);
				CertificateInfo certInfo_db = deducInfo.getCertificateInfo();
				int remainScore = certInfo_db.getRemainingScore();
				remainScore = deducInfo.getDeduction()+remainScore;
				certInfo_db.setRemainingScore(remainScore);
				cd.updateObject(certInfo_db);
				request.setAttribute("certattrId", deducInfo.getCertificateInfo().getCertificateInfoId());
				cd.deleteObject(deducInfo);
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return "mananologin";
		}
	}
}
