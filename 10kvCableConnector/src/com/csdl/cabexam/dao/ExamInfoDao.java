package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.ExamInfo;

public interface ExamInfoDao {
	//通过examInfo id得到考试成绩信息对象
	public ExamInfo getExamInfoByExamInfoId(int examInfoId);
	//根据用户id得到考生成绩信息对象
	public ExamInfo getExamInfoByUserId(Integer userId);
	//根据Id得到考生成绩通过的对象
	public ExamInfo getPassedExamInfoByUserId(Integer userId,String exameState);
	public List<ExamInfo> getExamInfoByState(String exameState);
	public List<ExamInfo>  getAllExamInfo();
	public String updateExaminnerState(Integer userInfoId,String examState);
	public List<ExamInfo> getExamInfoByRealName(String realName);
	//查看今年是否存在某个准考证号
	public boolean checkNumberExist(String number);
	//获得准考证号最大值
	public String getMaxExamNum();
	//通过id来更新成绩
	public void UpdateExamScoreById(int id,Float theoryScore,Float operateScore);
	//通过名字和准考证号来查询考场安排信息
	public List<ExamInfo> getExamManageByCondition(String realName,String number);
	
	//查出所有userState=1的ExamInfo
	public List<ExamInfo> getExamInfoOfThisYear();
	
	
	//检查   userinfoid 、examinfoid、number的一致性,不一致返回false,一致返回true
	public boolean checkCoordinate(Integer userInfoId,Integer examInfoId,String number);
	
}
