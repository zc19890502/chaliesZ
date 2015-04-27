package com.csdl.cabexam.actions.exam.service;

import java.util.List;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.DeductionRecordInfo;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExtraInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.DeductionRecordInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.csdl.cabexam.util.GenerateNums;

public class ScoreService {
	
	
	//判断证书列表内是否有certState的证书,有返回true,
	public boolean isCertInfoListStated(List<CertificateInfo> certList,String certState){
		boolean flag = false;
		for (CertificateInfo certInfo : certList) {
			if(certState.equals(certInfo.getCertificationState())){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	//通过examInfoId逐个修改个人理论成绩    
		public void UpdateScoreById(int examInfoId,Float theoryScore){
			ExamInfoDao examDao = new ExamInfoDaoImp();
			CommonDao cd = new CommonDaoImp();
			ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
	    	examInfo.setTheoryScore(theoryScore);
	    	cd.updateObject(examInfo);
	    	
		}
		//通过examInfoId逐个修改个人其他成绩
		public void UpdateAllScoreById(int examInfoId,Float coldMidScore,Float coldTemScore,Float hotMidScore,Float hotTemScore){
			ExamInfoDao examDao = new ExamInfoDaoImp();
			CommonDao cd = new CommonDaoImp();
			ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
			examInfo.setColdMidScore(coldMidScore);
			examInfo.setColdTemScore(coldTemScore);
			examInfo.setHotMidScore(hotMidScore);
			examInfo.setHotTemScore(hotTemScore);
			cd.updateObject(examInfo);
		}

	
	
	/*//通过userInfoid逐个修改个人成绩,和添加证书记录
	public String UpdateScoreById(int userId,int examInfoId,Float theoryScore){
		ExtraInfo ex = new ExtraInfoDaoImp().getExtraInfo();
		ExamInfoDao examDao = new ExamInfoDaoImp();
		GenerateNums geneNum = new GenerateNums();
		CommonDao cd = new CommonDaoImp();
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		// 检查证书记录是否存在
		List<CertificateInfo> certList = certInfoDao.getCertInfosByUserId(userId);
	    //判断成绩合格
		if(theoryScore>=ex.getTheoryScoreLimit()){
				//更新成绩
				ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
		    	examInfo.setTheoryScore(theoryScore);
		    	cd.updateObject(examInfo);		    	
				return "examPass";
			//不及格
		}else {
			theoryScore = (null==theoryScore)?0:theoryScore;
			if(certList.isEmpty()){
				//证书列表是空
				//更新成绩
				ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
		    	examInfo.setTheoryScore(theoryScore);
		    	examInfo.setExameState("2");
		    	
		    	cd.updateObject(examInfo);
				return "examFailed";
			}else{
				if(!this.isCertInfoListStated(certList, "1")){
					//更新成绩
					ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
			    	examInfo.setTheoryScore(theoryScore);
			    	examInfo.setExameState("2");
			    	examInfo.setColdMidScore(Float.parseFloat("0"));
			    	examInfo.setColdTemScore(Float.parseFloat("0"));
			    	examInfo.setHotMidScore(Float.parseFloat("0"));
			    	examInfo.setHotTemScore(Float.parseFloat("0"));	
			    	cd.updateObject(examInfo);
					return "examFailed";
				}else{
					CertificateInfo certInfo =  certInfoDao.getvalidCertByUserId(userId);
					new CommonDaoImp().deleteObject(certInfo);					
					//更新成绩
					ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
			    	examInfo.setTheoryScore(theoryScore);
			    	examInfo.setExameState("2");
			    	examInfo.setColdMidScore(Float.parseFloat("0"));
			    	examInfo.setColdTemScore(Float.parseFloat("0"));
			    	examInfo.setHotMidScore(Float.parseFloat("0"));
			    	examInfo.setHotTemScore(Float.parseFloat("0"));			    	
			    	cd.updateObject(examInfo);
					return "examFialedandDelete";
				}
			}
		}
    	
	}
	public String UpdateAllScoreById(int userId,int examInfoId,Float coldMidScore,Float coldTemScore,Float hotMidScore,Float hotTemScore){
		ExamInfoDao examDao = new ExamInfoDaoImp();
		GenerateNums geneNum = new GenerateNums();
		CommonDao cd = new CommonDaoImp();
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		// 检查证书记录是否存在
		List<CertificateInfo> certList = certInfoDao.getCertInfosByUserId(userId);
	    //判断成绩合格
	    //判断成绩合格
		if(coldMidScore>=80||coldTemScore>=80||hotMidScore>=80||hotTemScore>=80){
			//判断有没certSate==1的证书
			if(certList.isEmpty()){
				//证书列表是空
				UserInfo user =new UserInfoDaoImp().getUserInfoById(userId);    //证书为空
				CertificateInfo cert=new CertificateInfo();
				cert.setUserInfo(user);
				cert.setCertificationState("1");
				String certNum = geneNum.getNums("certInfoNum");   
				cert.setCertificationNum(certNum);     
				cd.addObject(cert);	
				//更新成绩
				ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
		    	examInfo.setColdMidScore(coldMidScore);
		    	examInfo.setColdTemScore(coldTemScore);
		    	examInfo.setHotMidScore(hotMidScore);
		    	examInfo.setHotTemScore(hotTemScore);	
		    	examInfo.setExameState("1");
		    	cd.updateObject(examInfo);	
				return "examPass";
			}else{
				
				if(!this.isCertInfoListStated(certList, "1")){
					//没有有效证书
					UserInfo user =new UserInfoDaoImp().getUserInfoById(userId);    //证书为空
					CertificateInfo cert=new CertificateInfo();
					cert.setUserInfo(user);
					cert.setCertificationState("1");
					String certNum = geneNum.getNums("certInfoNum");
					cert.setCertificationNum(certNum);
					cd.addObject(cert);
					
					//更新成绩
					ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
			    	examInfo.setColdMidScore(coldMidScore);
			    	examInfo.setColdTemScore(coldTemScore);
			    	examInfo.setHotMidScore(hotMidScore);
			    	examInfo.setHotTemScore(hotTemScore);	
			    	examInfo.setExameState("1");
			    	cd.updateObject(examInfo);
					return "examPass";
				}else{
					//有有效证书
					
					//更新成绩
					ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
			    	examInfo.setColdMidScore(coldMidScore);
			    	examInfo.setColdTemScore(coldTemScore);
			    	examInfo.setHotMidScore(hotMidScore);
			    	examInfo.setHotTemScore(hotTemScore);	
			    	examInfo.setExameState("1");
			    	cd.updateObject(examInfo);
					return "validcertExited";
				}
			}
			
			//不及格
		}else {
			coldMidScore = (null==coldMidScore)?0:coldMidScore;
			coldTemScore = (null==coldTemScore)?0:coldTemScore;
			hotMidScore = (null==hotMidScore)?0:hotMidScore;
			hotTemScore = (null==hotTemScore)?0:hotTemScore;
			if(certList.isEmpty()){
				//证书列表是空
				
				//更新成绩
				ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
		    	examInfo.setColdMidScore(coldMidScore);
		    	examInfo.setColdTemScore(coldTemScore);
		    	examInfo.setHotMidScore(hotMidScore);
		    	examInfo.setHotTemScore(hotTemScore);	
		    	examInfo.setExameState("2");
		    	cd.updateObject(examInfo);
				return "examFailed";
			}else{
				if(!this.isCertInfoListStated(certList, "1")){
					
					//更新成绩
					ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
			    	examInfo.setColdMidScore(coldMidScore);
			    	examInfo.setColdTemScore(coldTemScore);
			    	examInfo.setHotMidScore(hotMidScore);
			    	examInfo.setHotTemScore(hotTemScore);	
			    	examInfo.setExameState("2");
			    	cd.updateObject(examInfo);
					return "examFailed";
				}else{
					CertificateInfo certInfo =  certInfoDao.getvalidCertByUserId(userId);
					new CommonDaoImp().deleteObject(certInfo);
					
					//更新成绩
					ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
			    	examInfo.setColdMidScore(coldMidScore);
			    	examInfo.setColdTemScore(coldTemScore);
			    	examInfo.setHotMidScore(hotMidScore);
			    	examInfo.setHotTemScore(hotTemScore);	
			    	examInfo.setExameState("2");
			    	cd.updateObject(examInfo);
					return "examFialedandDelete";
				}
			}
		}		
		
	}*/
}
