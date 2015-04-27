package com.csdl.cabexam.actions.excel.service;

import java.util.List;

import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.csdl.cabexam.util.GenerateNums;

public class NumberService {
	 //自动生成所有准考证
	public void generateAllNum(){
		ExamInfoDao examdao = new ExamInfoDaoImp();
		UserInfoDao userInfoDao = new UserInfoDaoImp();
		ExamineeInfoDao examineedao = new ExamineeInfoDaoImp();
		CommonDao cd = new CommonDaoImp();
		//查出所有examState='0'
		List<UserInfo> userInfoList = userInfoDao.getAllUserInfoByState("1");
		//TODO userState=1 的 userInfo记录没有对应的 examineeInfo记录
		for (UserInfo userInfo : userInfoList) {
			ExamInfo examInfo_db = examdao.getExamInfoByUserId(userInfo.getUserInfoId());
			ExamineeInfo examinee = examineedao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
			if(examinee!=null){
				if(null==examInfo_db){
					ExamInfo examInfo = new ExamInfo();
			   		if((examinee.getCheckState()).equals("3")){
						String number = new GenerateNums().getExamNum();	
						examInfo.setUserInfo(userInfo);
						examInfo.setNumber(number);				 
						examInfo.setExameState("0");
						cd.addObject(examInfo);
					}
				}else{
					examInfo_db.setExameState("0");
					cd.updateObject(examInfo_db);
				}				
			}
			
		}
	}
}
