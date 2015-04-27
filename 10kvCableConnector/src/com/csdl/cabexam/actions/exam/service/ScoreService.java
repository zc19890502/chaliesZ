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
	
	
	//�ж�֤���б����Ƿ���certState��֤��,�з���true,
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
	
	//ͨ��examInfoId����޸ĸ������۳ɼ�    
		public void UpdateScoreById(int examInfoId,Float theoryScore){
			ExamInfoDao examDao = new ExamInfoDaoImp();
			CommonDao cd = new CommonDaoImp();
			ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
	    	examInfo.setTheoryScore(theoryScore);
	    	cd.updateObject(examInfo);
	    	
		}
		//ͨ��examInfoId����޸ĸ��������ɼ�
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

	
	
	/*//ͨ��userInfoid����޸ĸ��˳ɼ�,�����֤���¼
	public String UpdateScoreById(int userId,int examInfoId,Float theoryScore){
		ExtraInfo ex = new ExtraInfoDaoImp().getExtraInfo();
		ExamInfoDao examDao = new ExamInfoDaoImp();
		GenerateNums geneNum = new GenerateNums();
		CommonDao cd = new CommonDaoImp();
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		// ���֤���¼�Ƿ����
		List<CertificateInfo> certList = certInfoDao.getCertInfosByUserId(userId);
	    //�жϳɼ��ϸ�
		if(theoryScore>=ex.getTheoryScoreLimit()){
				//���³ɼ�
				ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
		    	examInfo.setTheoryScore(theoryScore);
		    	cd.updateObject(examInfo);		    	
				return "examPass";
			//������
		}else {
			theoryScore = (null==theoryScore)?0:theoryScore;
			if(certList.isEmpty()){
				//֤���б��ǿ�
				//���³ɼ�
				ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
		    	examInfo.setTheoryScore(theoryScore);
		    	examInfo.setExameState("2");
		    	
		    	cd.updateObject(examInfo);
				return "examFailed";
			}else{
				if(!this.isCertInfoListStated(certList, "1")){
					//���³ɼ�
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
					//���³ɼ�
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
		// ���֤���¼�Ƿ����
		List<CertificateInfo> certList = certInfoDao.getCertInfosByUserId(userId);
	    //�жϳɼ��ϸ�
	    //�жϳɼ��ϸ�
		if(coldMidScore>=80||coldTemScore>=80||hotMidScore>=80||hotTemScore>=80){
			//�ж���ûcertSate==1��֤��
			if(certList.isEmpty()){
				//֤���б��ǿ�
				UserInfo user =new UserInfoDaoImp().getUserInfoById(userId);    //֤��Ϊ��
				CertificateInfo cert=new CertificateInfo();
				cert.setUserInfo(user);
				cert.setCertificationState("1");
				String certNum = geneNum.getNums("certInfoNum");   
				cert.setCertificationNum(certNum);     
				cd.addObject(cert);	
				//���³ɼ�
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
					//û����Ч֤��
					UserInfo user =new UserInfoDaoImp().getUserInfoById(userId);    //֤��Ϊ��
					CertificateInfo cert=new CertificateInfo();
					cert.setUserInfo(user);
					cert.setCertificationState("1");
					String certNum = geneNum.getNums("certInfoNum");
					cert.setCertificationNum(certNum);
					cd.addObject(cert);
					
					//���³ɼ�
					ExamInfo examInfo = examDao.getExamInfoByExamInfoId(examInfoId);
			    	examInfo.setColdMidScore(coldMidScore);
			    	examInfo.setColdTemScore(coldTemScore);
			    	examInfo.setHotMidScore(hotMidScore);
			    	examInfo.setHotTemScore(hotTemScore);	
			    	examInfo.setExameState("1");
			    	cd.updateObject(examInfo);
					return "examPass";
				}else{
					//����Ч֤��
					
					//���³ɼ�
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
			
			//������
		}else {
			coldMidScore = (null==coldMidScore)?0:coldMidScore;
			coldTemScore = (null==coldTemScore)?0:coldTemScore;
			hotMidScore = (null==hotMidScore)?0:hotMidScore;
			hotTemScore = (null==hotTemScore)?0:hotTemScore;
			if(certList.isEmpty()){
				//֤���б��ǿ�
				
				//���³ɼ�
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
					
					//���³ɼ�
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
					
					//���³ɼ�
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
