package com.csdl.cabexam.actions.certinfo.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.certinfo.service.CertInfoService;
import com.csdl.cabexam.actions.examee.service.ExamineeService;
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

public class UpdateCertInfoAction extends ActionSupport{
	private CertificateInfo certInfo;
	private DeductionRecordInfo deducRecord;
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=managerInfo&&!"".equals(managerInfo)){
			CommonDao cd = new CommonDaoImp();
			CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
			DeductionRecordInfoDao deduciDao = new DeductionRecordInfoDaoImp();
			CertInfoService certInfoService = new CertInfoService();
			//增加扣分记录外键
			CertificateInfo certInfo_db = certInfoDao.getCertInfoById(certInfo.getCertificateInfoId());
			deducRecord.setCertificateInfo(certInfo_db);
			//增加扣分记录时间
			Date date = new Date();       
			Timestamp nousedate = new Timestamp(date.getTime());
			deducRecord.setDeductionDate(nousedate);
			//增加扣分记录剩余分数
			int remainingScore = 0;
			int deductionScore = 0;
			//得到所有扣分
			deductionScore = deducRecord.getDeduction()+certInfoService.getRemainingScore(certInfo.getCertificateInfoId());
			//满12分吊销证书，未满，得到剩余分数,更新证书中的分数
			if(deductionScore>=12){
				certInfoService.revokeCertInfo(certInfo.getCertificateInfoId(),managerInfo);
				remainingScore = 0;
			}else{
				remainingScore = 12-deductionScore;
				certInfo_db.setRemainingScore(remainingScore);
				cd.updateObject(certInfo_db);
			}
			
			//添加本记录的剩余分数
			cd.addObject(deducRecord);
			request.setAttribute("certattrId",certInfo.getCertificateInfoId());
			return SUCCESS;
		}else{
			return "mananologin";
		}
	}
	public CertificateInfo getCertInfo() {
		return certInfo;
	}
	public void setCertInfo(CertificateInfo certInfo) {
		this.certInfo = certInfo;
	}
	public DeductionRecordInfo getDeducRecord() {
		return deducRecord;
	}
	public void setDeducRecord(DeductionRecordInfo deducRecord) {
		this.deducRecord = deducRecord;
	}

}
