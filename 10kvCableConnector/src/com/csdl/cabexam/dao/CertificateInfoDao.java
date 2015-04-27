package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.CertInfoExcel;
import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.UserInfo;

public interface CertificateInfoDao {
	//��ȡ����(���Գɼ�״̬Ϊͨ����)֤����Ϣ
	public List<CertificateInfo> getAllCertInfo();
   //����ID��ȡ֤����Ϣ
	public CertificateInfo getCertInfoById(int certInfoId);
	
   //����֤���ź����֤�Ż�ȡ֤����Ϣ
   public CertificateInfo getCertInfoByIdnumAndCertId(String idnum,String certnum);
   //����֤���Ż�ȡ֤����Ϣ
   public List<CertificateInfo> getCertInfoByCertNum(String certnum);
	//�����û�id��ȡ���û�����֤����Ϣ
	public List<CertificateInfo> getCertInfosByUserId(int userId);
	//��ȡϵͳ������ֵ��֤���
	public String getMaxCertNums();
	//��ȡϵͳ�����ֵ��֤���
	public String getMaxCertNumsInSystem();
	
	//����֤��״̬��ȡ����֤��״̬֤����Ϣ
	public List<CertificateInfo> getCertInfoByState(String state);
	//����֤��,�ɹ�����true,ʧ�ܷ���false
	public boolean InsertCertInfoExl(CertInfoExcel certInfoExcel);
	//�����û�id����Ƿ���û��Ƿ���δ��������֤�飬�з���true,�޷���false
	public boolean getCertInfosByUserIdNochecked(int userId);
	//�����û�id������Ч��֤��
	public CertificateInfo getvalidCertByUserId(int userId);
	//����û�idδ��������֤��
	public CertificateInfo getCertInfosNochecked(int userId);	
	//ͨ��������֤���ţ����֤��������֤��
	public List<CertificateInfo> getCertInfosByCondition(String realName, String certnum,String idnum);
	
	
}
