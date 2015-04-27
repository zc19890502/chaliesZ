package com.csdl.cabexam.dao;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;
import java.util.List;
public interface ExamineeInfoDao {
	//����id��ÿ�����Ϣ����
	public ExamineeInfo getExamineeInfoByID(int examineeId);
	//�����û�����id��ÿ�����Ϣ����
	public ExamineeInfo getExamineeInfoByUserInfo(Integer userId);
	//����checkState״̬��ȡ����,��ѯ��userState=1,���챨�������п���
	public List<ExamineeInfo> getExamineeInfoByCheckState(String checkState);
	
	//�������������ᡢ������λ��ȡ����
	public List<ExamineeInfo> getExamineeInfoByCondition(String realName,String homesite,String company);
	
	//�������������Եȼ���������λ��ȡ����
	public List<ExamineeInfo> getExamineeInfoOfFeeByCondition(String realName,String skillLeval,String company);
	
	//�������������Եȼ���������λ��ȡ����
	public List<ExamineeInfo> getExamineeInfoOfTheoryFeeByCondition(String realName,String skillLeval,String company);
		
	//�������������Եȼ���������λ��ȡ����
	public List<ExamineeInfo> getExamineeInfoOfOperateFeeByCondition(String realName,String skillLeval,String company);
		
	
	//�������������ᡢ������λ��ȡ����
	public List<ExamineeInfo> getFailedExamineeInfoByCondition(String realName,String homesite,String company);
	
	//�������������ᡢ������λ��ȡ����
	public List<ExamineeInfo> getcheckStateExamineeInfoByCondition(String realName,String homesite,String company,String checkState);
	
	//�������������ᡢ������λ��ȡ����
	public List<ExamineeInfo> getPassExamineeInfoByCondition(String realName,String homesite,String company);
	
	//�������������ᡢ������λ��ȡ����
	public List<ExamineeInfo> getWaitExamineeInfoByCondition(String realName,String homesite,String company);
	
	//������еĲ����������
	public List<ExamineeInfo> getExamineeInfoByOperateFee();
	
	public ExamineeInfo getExamineeInfoByAccount(String account);
	public List<ExamineeInfo> getAllExammerInfo();
	public void updateExaminee(ExamineeInfo examinee);
}
