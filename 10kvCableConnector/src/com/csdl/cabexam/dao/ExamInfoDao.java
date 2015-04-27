package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.ExamInfo;

public interface ExamInfoDao {
	//ͨ��examInfo id�õ����Գɼ���Ϣ����
	public ExamInfo getExamInfoByExamInfoId(int examInfoId);
	//�����û�id�õ������ɼ���Ϣ����
	public ExamInfo getExamInfoByUserId(Integer userId);
	//����Id�õ������ɼ�ͨ���Ķ���
	public ExamInfo getPassedExamInfoByUserId(Integer userId,String exameState);
	public List<ExamInfo> getExamInfoByState(String exameState);
	public List<ExamInfo>  getAllExamInfo();
	public String updateExaminnerState(Integer userInfoId,String examState);
	public List<ExamInfo> getExamInfoByRealName(String realName);
	//�鿴�����Ƿ����ĳ��׼��֤��
	public boolean checkNumberExist(String number);
	//���׼��֤�����ֵ
	public String getMaxExamNum();
	//ͨ��id�����³ɼ�
	public void UpdateExamScoreById(int id,Float theoryScore,Float operateScore);
	//ͨ�����ֺ�׼��֤������ѯ����������Ϣ
	public List<ExamInfo> getExamManageByCondition(String realName,String number);
	
	//�������userState=1��ExamInfo
	public List<ExamInfo> getExamInfoOfThisYear();
	
	
	//���   userinfoid ��examinfoid��number��һ����,��һ�·���false,һ�·���true
	public boolean checkCoordinate(Integer userInfoId,Integer examInfoId,String number);
	
}
