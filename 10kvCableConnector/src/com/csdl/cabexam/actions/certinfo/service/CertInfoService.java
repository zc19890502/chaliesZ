package com.csdl.cabexam.actions.certinfo.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.DeductionRecordInfo;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.DeductionRecordInfoDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.DeductionRecordInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.csdl.cabexam.util.GenerateNums;

public class CertInfoService {
	
	public boolean revokeCertInfo(int certId,ManagerInfo managerInfo){
		boolean flag = false;
		CommonDao cd = new CommonDaoImp();
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		CertificateInfo certInfo = certInfoDao.getCertInfoById(certId);
		if(null!=certInfo){
			Date date = new Date();
			Timestamp times = new Timestamp(date.getTime());
			certInfo.setRevokeDate(times);
			certInfo.setCertificationState("2");
			certInfo.setRemainingScore(0);
			certInfo.setRevokeOperator(managerInfo.getRealName());
			cd.updateObject(certInfo);
			flag = true;
		}
		return flag;
	}
	
	public int getRemainingScore(int certId){
		int deductionScore = 0;
		DeductionRecordInfoDao deduciDao = new DeductionRecordInfoDaoImp();
		List<DeductionRecordInfo> deducList = deduciDao.getDeducRecordBycertId(certId);
		if(deducList.size()>0){
			for (DeductionRecordInfo deductInfo : deducList) {
				if(null!=deductInfo.getDeduction()){
					deductionScore+=deductInfo.getDeduction();
				}
			}
		}
		return deductionScore;
	}
	
	

    //自动生成所有的证书
	public void generateAllCert(){
		ExamInfoDao examdao = new ExamInfoDaoImp();
		UserInfoDao userInfoDao = new UserInfoDaoImp();
		CertificateInfoDao certInfodao = new CertificateInfoDaoImp();
		ExamineeInfoDao examineedao = new ExamineeInfoDaoImp();
		CommonDao cd = new CommonDaoImp();
		Date date = new Date();
		Timestamp nowDate = new Timestamp(date.getTime());
		List<UserInfo> userInfoList = userInfoDao.getAllUserInfoByState("1");
		for (UserInfo userInfo : userInfoList) {
			CertificateInfo certInfo_db = certInfodao.getvalidCertByUserId(userInfo.getUserInfoId());
				CertificateInfo certInfo = new CertificateInfo();
				ExamineeInfo examineeInfo = examineedao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examdao.getExamInfoByUserId(userInfo.getUserInfoId());
				// 有没成绩
				if (null != examInfo && !"".equals(examInfo)) {
					Float theoryScore = examInfo.getTheoryScore();
					Float coldMidScore = examInfo.getColdMidScore();
					Float coldTemScore = examInfo.getColdTemScore();
					Float hotMidScore = examInfo.getHotMidScore();
					Float hotTemScore = examInfo.getHotTemScore();
					if(null==theoryScore||"".equals(theoryScore)){
						theoryScore=0F;
					}
					if(null==coldMidScore||"".equals(coldMidScore)){
						coldMidScore=0F;
					}
					if(null==coldTemScore||"".equals(coldTemScore)){
						coldTemScore=0F;
					}
					if(null==hotMidScore||"".equals(hotMidScore)){
						hotMidScore=0F;
					}
					if(null==hotTemScore||"".equals(hotTemScore)){
						hotTemScore=0F;
					}
					//没有有效证书
					if (null == certInfo_db&&!"".equals(certInfo_db)) {
						// 如果成绩合格
						if (theoryScore >= 80&& (coldMidScore >= 80 || coldTemScore >= 80 || hotMidScore >= 80 || hotTemScore >= 80)) {
							// 考试来源，0代表系统内
							String source = examineeInfo.getExamineeSource();
							if (source.equals("0")) {
								String certNumA = new GenerateNums().getCertNum("0");
								certInfo.setUserInfo(userInfo);
								certInfo.setCertificationState("1");
								certInfo.setRemainingScore(12);
								certInfo.setCertificationNum(certNumA);
								certInfo.setCertificationGrantDate(nowDate);
								cd.addObject(certInfo);
							} else {// 1代表系统外
								String certNumB = new GenerateNums().getCertNum("1");
								certInfo.setUserInfo(userInfo);
								certInfo.setCertificationState("1");
								certInfo.setRemainingScore(12);
								certInfo.setCertificationNum(certNumB);
								certInfo.setCertificationGrantDate(nowDate);
								cd.addObject(certInfo);
							}
							examInfo.setScoreBatch(new ExtraInfoDaoImp().getExtraInfo().getExamBatch());			
							examInfo.setExameState("1");
							cd.updateObject(examInfo);
						}else{
							examInfo.setExameState("2");
							cd.updateObject(examInfo);
						}
					}else{
						//有有效证书
						if (theoryScore >= 80&& (coldMidScore >= 80 || coldTemScore >= 80 || hotMidScore >= 80 || hotTemScore >= 80)) {
							examInfo.setExameState("1");
							cd.updateObject(examInfo);
						}else{
							cd.deleteObject(certInfo_db);
							examInfo.setExameState("2");
							examInfo.setScoreBatch(new ExtraInfoDaoImp().getExtraInfo().getExamBatch());
							cd.updateObject(examInfo);
						}
					}
				} 
				userInfo.setUserState("0");
				cd.updateObject(userInfo);
			}
		}
}
