package com.csdl.cabexam.actions.examee.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ExtraInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.csdl.cabexam.util.GenerateNums;
import com.sun.net.httpserver.Authenticator.Success;

public class ExamineeService {
	
	//�ϴ��ļ�����
	/*
	 * @param compLoadFileName:�ϴ��ļ���ȫ��
	 * @param typeName:�ϴ��ļ���ŵ��ļ�������
	 * @param examineeId:�ϴ��ļ���������ļ��е�����
	 * @param compLoad:�ϴ��ļ��Ķ��������ļ�
	 * @param compLoad:�ϴ��ļ��ľ���·��
	 */
	public String uploadFile(String FileName,String typeName,int examineeId,File fileload,String roots){
		try {
			String str1 = FileName.substring(FileName.lastIndexOf("."));
			str1 = str1.toLowerCase();
			String filename = typeName+str1;
			String relatepath = "uploadList/"+examineeId+"/"+filename;
			String rootpath=roots+"\\"+filename;
			//��������io����������ļ�
			FileOutputStream fos = new FileOutputStream(rootpath);
			FileInputStream fis = new FileInputStream(fileload);
			byte[] buffer = new byte[1024];
			int len=0;
			while((len = fis.read(buffer))>0){
				fos.write(buffer,0,len);
			}
			fos.close();
			fis.close();
			return relatepath;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateCheckAdvice(int examineeId,String checkAdvice){
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		CommonDao cd = new CommonDaoImp();
		ExamineeInfo exameeInfo = examineeDao.getExamineeInfoByID(examineeId);
		exameeInfo.setAdvice(checkAdvice);
		cd.updateObject(exameeInfo);
	}
	
	
	
	/*
	 * ��js���ڸ�ʽת��Ϊtimestamp���ͷ���
	 * @param��date���������������,yyyy-M-dd��ʽ
	 */
	public Timestamp convertToTimestamp(String date, String pattern) throws ParseException{
		Timestamp time = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		time = new Timestamp(sdf.parse(date).getTime());
		return time;
	}
	
	
	
	
	/*
	 * ���ܣ��жϸ��û��Ƿ��Ѿ�����(�����û�id�Ƿ��Ӧ�б�����Ϣ��
	 * ����������У��򷵻���true
	 * ���δ�����򷵻ؼ�false
	 */
	public boolean isExamineeSignUp(UserInfo userInfo){
		ExamineeInfoDao examDao = new ExamineeInfoDaoImp();
		ExamineeInfo examineeInfo = examDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
		if(null!=examineeInfo){
			return true;
		}else{
			return false;
		}
	}
	/*
	 * ɾ���ϴ�����ʱ���ɵ��ļ���
	 * 
	 */
	public void deleteFiles(File file){
		if(file.isFile() || file.list().length ==0){  
		   file.delete();       
		   }else{      
		     File[] files = file.listFiles();  
		     for (int i = 0; i < files.length; i++) {  
		    	 deleteFiles(files[i]);  
		    	 files[i].delete();      
		     }  
			 if(file.exists())         //����ļ��������Ŀ¼ ����Ҫɾ��Ŀ¼  
			 file.delete();  
		  }  
	}
	
	//�ж�examInfoList����û��Ϣ��examState�ģ����򷵻�true,û�з���false
	public boolean isExamListState2(List<ExamInfo> examInfoList,String examState){
		boolean flag = false;
		for (ExamInfo examInfo : examInfoList) {
			if(examState.equals(examInfo.getExameState())){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/*
	 * �ı���˵�״̬
	 * ��ͨ����ˣ��򣺷���newExamInfo���ɹ���ˣ�����cannotCheck�����ܵ�����ͨ��
	 * ��δͨ����ˣ��򣺷���noChencked���ɹ���˲�ͨ��������cannotnoChecked�����ܵ�����ͨ��
	 */
	public String updateCheckState(int examineeId,String checkState,String checkAdvice){
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		ExamInfoDao examDao = new ExamInfoDaoImp();
		CommonDao cd = new CommonDaoImp();
		ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByID(examineeId);
		if(null!=examineeInfo&&!"".equals(examineeInfo)){
			if(checkState.equals("1")){
				examineeInfo.setCheckState("1");
				if(null!=checkAdvice&&!"".equals(checkAdvice)){
					examineeInfo.setAdvice(checkAdvice);
				}else{
					examineeInfo.setAdvice("����ͨ��");
				}
				cd.updateObject(examineeInfo);
				return "ok";
			}else if(checkState.equals("2")){
				ExamInfo examInfo = examDao.getExamInfoByUserId(examineeInfo.getUserInfo().getUserInfoId());
				if(null!=examInfo&&!"".equals(examInfo)){
					return "cannotChecked";
				}else{
					examineeInfo.setCheckState("2");
					if(null!=checkAdvice&&!"".equals(checkAdvice)){
						examineeInfo.setAdvice(checkAdvice);
					}else{
						examineeInfo.setAdvice("����ͨ��");
					}
					cd.updateObject(examineeInfo);
					return "ok";
				}
			}else if(checkState.equals("7")){
				ExamInfo examInfo = examDao.getExamInfoByUserId(examineeInfo.getUserInfo().getUserInfoId());
				if(null!=examInfo&&!"".equals(examInfo)){
					return "cannotChecked";
				}else{
					if(null!=examineeInfo.getCheckState()&&examineeInfo.getCheckState().equals("2")){
						return "cannotChecked";
					}
					examineeInfo.setCheckState("7");
					if(null!=checkAdvice&&!"".equals(checkAdvice)){
						examineeInfo.setAdvice(checkAdvice);
					}else{
						examineeInfo.setAdvice("����ͨ��");
					}
					cd.updateObject(examineeInfo);
					return "ok";
				}
			}else if(checkState.equals("8")){
				if(null!=examineeInfo.getCheckState()&&examineeInfo.getCheckState().equals("2")){
					return "cannotChecked";
				}
				examineeInfo.setCheckState("8");
				if(null!=checkAdvice&&!"".equals(checkAdvice)){
					examineeInfo.setAdvice(checkAdvice);
				}else{
					examineeInfo.setAdvice("����ͨ��");
				}
				cd.updateObject(examineeInfo);
				return "ok";
			}
			else{
				return "inputCheckSateErr";
			}
		}else{
			return "noExamineeInfo";
		}
	}
	//�жϱ���ʱ���Ƿ����,���ڷ���false,û���ڷ���true
	public boolean isSignUpOutOfDate(){
		ExtraInfoDao extraDao = new ExtraInfoDaoImp();
		Date date = new Date();       
		Timestamp nowDate = new Timestamp(date.getTime());
		ExtraInfo extraInfo = extraDao.getExtraInfo();
		Timestamp limitDate = extraInfo.getSignLimitDate();
		if(null!=limitDate&&!"".equals(limitDate)){
			if(nowDate.after(limitDate)){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
		
	}
	
	//�жϿ������������ĸ���״̬
	public String examineeSignUpState(UserInfo userInfo){
		String resultStr="";
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		ExamInfoDao examDao = new ExamInfoDaoImp();
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		//�Ƿ����
		if(this.isSignUpOutOfDate()){
			ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
			//�Ƿ��п�����Ϣ
			if(null!=examineeInfo&&!"".equals(examineeInfo)){
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				//�Ƿ��гɼ���Ϣ
				if(null!=examInfo&&!"".equals(examInfo)){
					CertificateInfo certInfo = certInfoDao.getvalidCertByUserId(userInfo.getUserInfoId());
					//�ж��Ƿ�����Ч֤��
					if(null!=certInfo&&!"".equals(certInfo)){
						//����Ч֤��
						resultStr = "hasValidCertCanSignUp";
						return resultStr;
					}else{
						String checkState = examineeInfo.getCheckState();
						String examState = examInfo.getExameState();
						//�ж��Ƿ��гɼ���Ϣ�������ɿ���ʱ��examState=0,checkStateֻ����3��5��6ʱ�Ż����ɿ���
						if("0".equals(examState)){
							//examState=0,��chenckstate=5ʱ�������ɷ���˳ɹ������ܱ���
							if("5".equals(checkState)){
								resultStr = "operatFeeSuccessConnotSignup";
								return resultStr;
							}else if("6".equals(checkState)){
								//examState=0,��chenckstate=6ʱ�������ɷ����ʧ�ܣ����ܱ���
								resultStr = "operatFeeFailConnotSignup";
								return resultStr;
							}else if("3".equals(checkState)&&null!=examInfo.getTheoryScore()){
								//examState=0,��chenckstate=3ʱ�������ɷ�δ�ϴ���
								float theoryScore = examInfo.getTheoryScore();
								if(theoryScore>80){
									//���۳ɼ�����80�����ϴ���
									resultStr = "operateFeeCanUp";
									return resultStr;
								}else{
									//С��80�������ϴ�
									resultStr = "operateFeeCannotUp";
									return resultStr;
								}
							}else if("11".equals(checkState)){
								//���������˷�
								resultStr = "refOrder";
								return resultStr;
							}else if("12".equals(checkState)){
								//�����˷ѳɹ�
								resultStr = "refOk";
								return resultStr;
							}else if("13".equals(checkState)){
								//��������˷�
								resultStr = "refOrder";
								return resultStr;
							}else if("14".equals(checkState)){
								//�����˷ѳɹ�
								resultStr = "refOk";
								return resultStr;
							}else{
								//û�гɼ�
								resultStr = "hasNoExamCanNotSignUp";
								return resultStr;
							}
						}else{
							resultStr = "hasExamCanSignUp";
							return resultStr;
						}
					}
				}else{
					String checkState = examineeInfo.getCheckState();
					//�Ƿ����ͨ��
					if("7".equals(checkState)){
						//����ͨ��
						resultStr = "initnoCheckCanUpdate";
						return resultStr;
					}else if("8".equals(checkState)){
						//����ͨ��
						resultStr = "initpassCheckCannoutSignup";
						return resultStr;
					}if("1".equals(checkState)){
						//ͨ��
						resultStr = "checkedCannotSignUp";
						return resultStr;
					}else if("2".equals(checkState)){
						resultStr = "noCheckedCanUpdate";
						return resultStr;
					}else if("0".equals(checkState)){
						//TODO  ���״̬
						resultStr = "waitCheckedCanUpdate";
						return resultStr;
					}else if("3".equals(checkState)){
						//���۽ɷѵ��ϸ�
						resultStr = "thoryFeeSuccessCannotSignup";
						return resultStr;
					}else if("11".equals(checkState)){
						resultStr = "refOrder";
						return resultStr;
					}else if("12".equals(checkState)){
						resultStr = "refOk";
						return resultStr;
					}else{
						//���۽ɷѵ����ϸ�
						resultStr = "thoryFeeFailCannotSignup";
						return resultStr;
					}
				}
			}else{
				resultStr = "noInfoCanSignUp";
				return resultStr;
			}
		}else{
			resultStr = "outofDate";
			return resultStr;
		}
	}
	
}
