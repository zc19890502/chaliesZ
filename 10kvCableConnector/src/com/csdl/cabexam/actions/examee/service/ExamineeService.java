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
	
	//上传文件方法
	/*
	 * @param compLoadFileName:上传文件的全名
	 * @param typeName:上传文件存放的文件的名字
	 * @param examineeId:上传文件存放所在文件夹的名字
	 * @param compLoad:上传文件的二进制流文件
	 * @param compLoad:上传文件的绝对路径
	 */
	public String uploadFile(String FileName,String typeName,int examineeId,File fileload,String roots){
		try {
			String str1 = FileName.substring(FileName.lastIndexOf("."));
			str1 = str1.toLowerCase();
			String filename = typeName+str1;
			String relatepath = "uploadList/"+examineeId+"/"+filename;
			String rootpath=roots+"\\"+filename;
			//调用下面io流方法存放文件
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
	 * 将js日期格式转换为timestamp类型方法
	 * @param：date，传入的日期类型,yyyy-M-dd格式
	 */
	public Timestamp convertToTimestamp(String date, String pattern) throws ParseException{
		Timestamp time = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		time = new Timestamp(sdf.parse(date).getTime());
		return time;
	}
	
	
	
	
	/*
	 * 功能：判断该用户是否已经报名(即该用户id是否对应有报名信息）
	 * 如果报名（有）则返回真true
	 * 如果未报名则返回假false
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
	 * 删除上传错误时生成的文件夹
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
			 if(file.exists())         //如果文件本身就是目录 ，就要删除目录  
			 file.delete();  
		  }  
	}
	
	//判断examInfoList中有没信息是examState的，有则返回true,没有返回false
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
	 * 改变审核的状态
	 * 若通过审核，则：返回newExamInfo，成功审核，返回cannotCheck，不能点击审核通过
	 * 若未通过审核，则：返回noChencked，成功审核不通过，返回cannotnoChecked，不能点击审核通过
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
					examineeInfo.setAdvice("复审通过");
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
						examineeInfo.setAdvice("复审不通过");
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
						examineeInfo.setAdvice("初审不通过");
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
					examineeInfo.setAdvice("初审通过");
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
	//判断报名时间是否过期,过期返回false,没过期返回true
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
	
	//判断考生点击报名后的各种状态
	public String examineeSignUpState(UserInfo userInfo){
		String resultStr="";
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		ExamInfoDao examDao = new ExamInfoDaoImp();
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		//是否过期
		if(this.isSignUpOutOfDate()){
			ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
			//是否有考生信息
			if(null!=examineeInfo&&!"".equals(examineeInfo)){
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				//是否有成绩信息
				if(null!=examInfo&&!"".equals(examInfo)){
					CertificateInfo certInfo = certInfoDao.getvalidCertByUserId(userInfo.getUserInfoId());
					//判断是否有有效证书
					if(null!=certInfo&&!"".equals(certInfo)){
						//有有效证书
						resultStr = "hasValidCertCanSignUp";
						return resultStr;
					}else{
						String checkState = examineeInfo.getCheckState();
						String examState = examInfo.getExameState();
						//判断是否有成绩信息，当生成考号时，examState=0,checkState只有是3，5，6时才会生成考号
						if("0".equals(examState)){
							//examState=0,当chenckstate=5时，操作缴费审核成功，不能报名
							if("5".equals(checkState)){
								resultStr = "operatFeeSuccessConnotSignup";
								return resultStr;
							}else if("6".equals(checkState)){
								//examState=0,当chenckstate=6时，操作缴费审核失败，不能报名
								resultStr = "operatFeeFailConnotSignup";
								return resultStr;
							}else if("3".equals(checkState)&&null!=examInfo.getTheoryScore()){
								//examState=0,当chenckstate=3时，操作缴费未上传，
								float theoryScore = examInfo.getTheoryScore();
								if(theoryScore>80){
									//理论成绩大于80可以上传，
									resultStr = "operateFeeCanUp";
									return resultStr;
								}else{
									//小于80，不能上传
									resultStr = "operateFeeCannotUp";
									return resultStr;
								}
							}else if("11".equals(checkState)){
								//申请理论退费
								resultStr = "refOrder";
								return resultStr;
							}else if("12".equals(checkState)){
								//理论退费成功
								resultStr = "refOk";
								return resultStr;
							}else if("13".equals(checkState)){
								//申请操作退费
								resultStr = "refOrder";
								return resultStr;
							}else if("14".equals(checkState)){
								//操作退费成功
								resultStr = "refOk";
								return resultStr;
							}else{
								//没有成绩
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
					//是否审核通过
					if("7".equals(checkState)){
						//初审不通过
						resultStr = "initnoCheckCanUpdate";
						return resultStr;
					}else if("8".equals(checkState)){
						//初审通过
						resultStr = "initpassCheckCannoutSignup";
						return resultStr;
					}if("1".equals(checkState)){
						//通过
						resultStr = "checkedCannotSignUp";
						return resultStr;
					}else if("2".equals(checkState)){
						resultStr = "noCheckedCanUpdate";
						return resultStr;
					}else if("0".equals(checkState)){
						//TODO  添加状态
						resultStr = "waitCheckedCanUpdate";
						return resultStr;
					}else if("3".equals(checkState)){
						//理论缴费单合格
						resultStr = "thoryFeeSuccessCannotSignup";
						return resultStr;
					}else if("11".equals(checkState)){
						resultStr = "refOrder";
						return resultStr;
					}else if("12".equals(checkState)){
						resultStr = "refOk";
						return resultStr;
					}else{
						//理论缴费单不合格
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
