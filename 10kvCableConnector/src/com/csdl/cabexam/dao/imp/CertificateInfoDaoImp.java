package com.csdl.cabexam.dao.imp;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.beans.CertInfoExcel;
import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class CertificateInfoDaoImp implements CertificateInfoDao {

	@Override
	public List<CertificateInfo> getAllCertInfo() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
//		String hql = "from CertificateInfo c where c.userInfo=(select e.userInfo from ExamInfo e where e.exameState=3)";
		String hql = "from CertificateInfo c";
		Query query = session.createQuery(hql);
		List<CertificateInfo> certInfoList = query.list();
		session.close();
		return certInfoList;
	}

	@Override
	public CertificateInfo getCertInfoById(int certInfoId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction tran =  session.beginTransaction();
		CertificateInfo certInfo = (CertificateInfo) session.get(CertificateInfo.class, certInfoId);
		tran.commit();
		session.close();
		return certInfo;
	}

	   //根据证书编号和身份证号获取证书信息
	@Override
	public CertificateInfo getCertInfoByIdnumAndCertId (String idnum,String certnum){
		Session session = HibernateSessionFactory.getSession();
		Transaction tran =  session.beginTransaction();
		String hql="from CertificateInfo c where c.userInfo.idnum=:idnum and c.certificationNum=:certnum";
		Query query = session.createQuery(hql);
		query.setString("idnum", idnum);
		query.setString("certnum", certnum);	
		CertificateInfo certInfo = (CertificateInfo) query.uniqueResult();
		session.close();
		return certInfo;
	}
	   //根据证书编号信息
	@Override
	public List<CertificateInfo> getCertInfoByCertNum (String certnum){
		Session session = HibernateSessionFactory.getSession();
		Transaction tran =  session.beginTransaction();
		String hql="from CertificateInfo c where c.certificationNum=:certnum";
		Query query = session.createQuery(hql);
		query.setString("certnum", certnum);	
		List<CertificateInfo> certInfoList = query.list();
		session.close();
		return certInfoList;
	}
	
	@Override
	public List<CertificateInfo> getCertInfosByUserId(int userId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from CertificateInfo c where c.userInfo.userInfoId="+userId;
		Query query = session.createQuery(hql);
		List<CertificateInfo> certInfoList = query.list();
		session.close();
		return certInfoList;
	}

	//找到所有的系统内的最大的证书号
		public String getMaxCertNumsInSystem() {
			// TODO Auto-generated method stub
			Session session = HibernateSessionFactory.getSession();
			String hql = "select c.certificationNum from CertificateInfo c,ExamineeInfo e where c.userInfo=e.userInfo and e.examineeSource='0' order by c.certificationNum desc";
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			String certNum = (String) query.uniqueResult();
			session.close();
			return certNum;
		}
		
	//找到所有的系统外的最大的证书号
		public String getMaxCertNums() {
			// TODO Auto-generated method stub
			Session session = HibernateSessionFactory.getSession();
			String hql = "select c.certificationNum from CertificateInfo c,ExamineeInfo e where c.userInfo=e.userInfo and e.examineeSource='1' order by c.certificationNum desc";
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			String certNum = (String) query.uniqueResult();
			session.close();
			return certNum;
		}

	@Override
	public List<CertificateInfo> getCertInfoByState(String state) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from CertificateInfo c where c.certificationState=?";
		Query query = session.createQuery(hql);
		query.setString(0, state);
		List<CertificateInfo> certInfoList = query.list();
		session.close();
		return certInfoList;
	}

	@Override
	public boolean InsertCertInfoExl(CertInfoExcel certInfoExcel) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			UserInfo userInfo_new = new UserInfo();
			userInfo_new.setAccount(certInfoExcel.getIdnum());
			userInfo_new.setPasswd(certInfoExcel.getIdnum());
			userInfo_new.setEmail("1@qq.com");
			userInfo_new.setIdnum(certInfoExcel.getIdnum());
			userInfo_new.setRealName(certInfoExcel.getRealName());
			userInfo_new.setUserState("0");
			session.save(userInfo_new);
			CertificateInfo certInfo = new CertificateInfo();
			certInfo.setCertificationNum(certInfoExcel.getCertificationNum());
			certInfo.setCertificationGrantDate(certInfoExcel.getCertificationGrantDate());
			certInfo.setCertificationState("1");
			certInfo.setRemainingScore(certInfoExcel.getRemainingScore());
			certInfo.setUserInfo(userInfo_new);
			session.save(certInfo);
			ExamineeInfo examineeInfo = new ExamineeInfo();
			examineeInfo.setCompany(certInfoExcel.getCompany());
			examineeInfo.setTel("1111111");
			examineeInfo.setUserInfo(userInfo_new);
			examineeInfo.setCheckState("5");
			session.save(examineeInfo);
			tran.commit();
		}catch (Exception e) {
			// TODO: handle exception
			tran.rollback();
			e.printStackTrace();
			return false;
		}finally{
			session.close();
		}
		return true;
	}

	@Override
	public boolean getCertInfosByUserIdNochecked(int userId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from CertificateInfo c where c.certificationState=1 and c.userInfo.userInfoId="+userId;
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		CertificateInfo certInfo = (CertificateInfo) query.uniqueResult();
		session.close();
		if(null!=certInfo&&!"".equals(certInfo)){
			return true;
		}else{
			return false;
		}
	}
	
	public CertificateInfo getvalidCertByUserId(int userId){
		Session session = HibernateSessionFactory.getSession();
		String hql = "from CertificateInfo c where c.certificationState=1 and c.userInfo.userInfoId=:userId";
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		CertificateInfo c =  (CertificateInfo) query.uniqueResult();
		session.close();
/*		if(c.isEmpty()){
			System.out.println("没有证书记录");
		}*/
	    return c;
	}
	
	public CertificateInfo getCertInfosNochecked(int userId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from CertificateInfo c where c.certificationState=1 and c.userInfo.userInfoId=?";
		Query query = session.createQuery(hql);
		query.setInteger(1, userId);
		query.setMaxResults(1);
		CertificateInfo certInfo = (CertificateInfo) query.uniqueResult();
		session.close();
		return certInfo;
	}	
	public List<CertificateInfo> getCertInfosByCondition(String realName, String certnum,String idnum){
		Session session = HibernateSessionFactory.getSession();
		String hql = "from CertificateInfo c where 1=1";
		if(realName!=null && !"".equals(realName)){
			hql +="and c.userInfo.realName like '%"+realName+"%'";
		}
		if(certnum!=null && !"".equals(certnum)){
			hql +="and c.certificationNum like '%"+certnum+"%'";
		}
		if(idnum!=null && !"".equals(idnum)){
			hql +="and c.userInfo.idnum like '%"+idnum+"%'";
		}	
		Query query = session.createQuery(hql);
		List<CertificateInfo> certInfoList = query.list();
		session.close();
		return certInfoList;

	}
//	public static void main(String[] args) {
//	System.out.println(new CertificateInfoDaoImp().getMaxCertNums());
//	}
}
