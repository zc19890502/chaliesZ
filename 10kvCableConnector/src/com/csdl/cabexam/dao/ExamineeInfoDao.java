package com.csdl.cabexam.dao;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;
import java.util.List;
public interface ExamineeInfoDao {
	//根据id获得考生信息对象
	public ExamineeInfo getExamineeInfoByID(int examineeId);
	//根据用户对象id获得考生信息对象
	public ExamineeInfo getExamineeInfoByUserInfo(Integer userId);
	//根据checkState状态获取考生,查询出userState=1,本届报名的所有考生
	public List<ExamineeInfo> getExamineeInfoByCheckState(String checkState);
	
	//根据姓名、籍贯、所属单位获取考生
	public List<ExamineeInfo> getExamineeInfoByCondition(String realName,String homesite,String company);
	
	//根据姓名、考试等级、所属单位获取考生
	public List<ExamineeInfo> getExamineeInfoOfFeeByCondition(String realName,String skillLeval,String company);
	
	//根据姓名、考试等级、所属单位获取考生
	public List<ExamineeInfo> getExamineeInfoOfTheoryFeeByCondition(String realName,String skillLeval,String company);
		
	//根据姓名、考试等级、所属单位获取考生
	public List<ExamineeInfo> getExamineeInfoOfOperateFeeByCondition(String realName,String skillLeval,String company);
		
	
	//根据姓名、籍贯、所属单位获取考生
	public List<ExamineeInfo> getFailedExamineeInfoByCondition(String realName,String homesite,String company);
	
	//根据姓名、籍贯、所属单位获取考生
	public List<ExamineeInfo> getcheckStateExamineeInfoByCondition(String realName,String homesite,String company,String checkState);
	
	//根据姓名、籍贯、所属单位获取考生
	public List<ExamineeInfo> getPassExamineeInfoByCondition(String realName,String homesite,String company);
	
	//根据姓名、籍贯、所属单位获取考生
	public List<ExamineeInfo> getWaitExamineeInfoByCondition(String realName,String homesite,String company);
	
	//查出所有的操作费用情况
	public List<ExamineeInfo> getExamineeInfoByOperateFee();
	
	public ExamineeInfo getExamineeInfoByAccount(String account);
	public List<ExamineeInfo> getAllExammerInfo();
	public void updateExaminee(ExamineeInfo examinee);
}
