package com.csdl.cabexam.dao;

import java.util.List;

import com.csdl.cabexam.beans.CertInfoExcel;
import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.UserInfo;

public interface CertificateInfoDao {
	//获取所有(考试成绩状态为通过的)证书信息
	public List<CertificateInfo> getAllCertInfo();
   //根据ID获取证书信息
	public CertificateInfo getCertInfoById(int certInfoId);
	
   //根据证书编号和身份证号获取证书信息
   public CertificateInfo getCertInfoByIdnumAndCertId(String idnum,String certnum);
   //根据证书编号获取证书信息
   public List<CertificateInfo> getCertInfoByCertNum(String certnum);
	//根据用户id获取改用户所有证书信息
	public List<CertificateInfo> getCertInfosByUserId(int userId);
	//获取系统外的最大值的证书号
	public String getMaxCertNums();
	//获取系统内最大值的证书号
	public String getMaxCertNumsInSystem();
	
	//根据证书状态获取所有证书状态证书信息
	public List<CertificateInfo> getCertInfoByState(String state);
	//导入证书,成功返回true,失败返回false
	public boolean InsertCertInfoExl(CertInfoExcel certInfoExcel);
	//根据用户id查出是否改用户是否有未被吊销的证书，有返回true,无返回false
	public boolean getCertInfosByUserIdNochecked(int userId);
	//根据用户id个人有效的证书
	public CertificateInfo getvalidCertByUserId(int userId);
	//查出用户id未被吊销的证书
	public CertificateInfo getCertInfosNochecked(int userId);	
	//通过姓名，证书编号，身份证号来查找证书
	public List<CertificateInfo> getCertInfosByCondition(String realName, String certnum,String idnum);
	
	
}
