package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ScoreExcel;

public interface MutiTableDao {
	//历年的xls表
    public List<ScoreExcel> getScoreXlsInfo();
	//理论成绩的xls表
	public List<ScoreExcel> getTheoryScoreXlsInfo();
	//操作成绩的xls表
	public List<ScoreExcel> getAllScoreXlsInfo();
	//根据姓名、籍贯、工作单位组合查询理论成绩信息
	public List<ScoreExcel> getTheoryScoreInfoByCondition(String realName,String place, String company,String theoryScoreFlag);
	//根据姓名、籍贯、工作单位组合查询操作成绩信息
	public List<ScoreExcel> getAllScoreInfoByCondition(String realName,String place,String company);
	//根据姓名、籍贯、工作单位组合查询历年成绩信息
	public List<ScoreExcel> getScoreInfoByCondition(String realName,String place,String company);
	//通过userInfoid来获得个人成绩信息
	public ScoreExcel getScoreXlsINfoByexamInfoId(int userInfoId,int examInfoId);
	//通过userInfoid来获得个人准考信息
	public ScoreExcel getNumberXlsINfoByexamInfoId(int userInfoId,int examInfoId);
	//通过examInfoid逐个修改个人成绩
	public void UpdateScoreById(int id,Float theoryScore,Float coldMidScore,Float coldTemScore,Float hotMidScore,Float hotTemScore);
    //打印准考证所需要的信息
	public ScoreExcel getAdmissionINfoById(int userid,int examid);
	//准考证xls表
	public List<ScoreExcel> getNumberXlsInfo();
	//根据姓名、籍贯、工作单位组合查询成绩信息
	public List<ScoreExcel> getNumberInfoByCondition(String realName,
			String place, String company); 
	//通过userInfoid逐个修改准考证号
	public void UpdateNumberByexamInfoId(int examInfoid,String number);
}
