package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ScoreExcel;

public interface MutiTableDao {
	//�����xls��
    public List<ScoreExcel> getScoreXlsInfo();
	//���۳ɼ���xls��
	public List<ScoreExcel> getTheoryScoreXlsInfo();
	//�����ɼ���xls��
	public List<ScoreExcel> getAllScoreXlsInfo();
	//�������������ᡢ������λ��ϲ�ѯ���۳ɼ���Ϣ
	public List<ScoreExcel> getTheoryScoreInfoByCondition(String realName,String place, String company,String theoryScoreFlag);
	//�������������ᡢ������λ��ϲ�ѯ�����ɼ���Ϣ
	public List<ScoreExcel> getAllScoreInfoByCondition(String realName,String place,String company);
	//�������������ᡢ������λ��ϲ�ѯ����ɼ���Ϣ
	public List<ScoreExcel> getScoreInfoByCondition(String realName,String place,String company);
	//ͨ��userInfoid����ø��˳ɼ���Ϣ
	public ScoreExcel getScoreXlsINfoByexamInfoId(int userInfoId,int examInfoId);
	//ͨ��userInfoid����ø���׼����Ϣ
	public ScoreExcel getNumberXlsINfoByexamInfoId(int userInfoId,int examInfoId);
	//ͨ��examInfoid����޸ĸ��˳ɼ�
	public void UpdateScoreById(int id,Float theoryScore,Float coldMidScore,Float coldTemScore,Float hotMidScore,Float hotTemScore);
    //��ӡ׼��֤����Ҫ����Ϣ
	public ScoreExcel getAdmissionINfoById(int userid,int examid);
	//׼��֤xls��
	public List<ScoreExcel> getNumberXlsInfo();
	//�������������ᡢ������λ��ϲ�ѯ�ɼ���Ϣ
	public List<ScoreExcel> getNumberInfoByCondition(String realName,
			String place, String company); 
	//ͨ��userInfoid����޸�׼��֤��
	public void UpdateNumberByexamInfoId(int examInfoid,String number);
}
