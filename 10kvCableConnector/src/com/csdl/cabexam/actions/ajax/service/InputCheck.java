package com.csdl.cabexam.actions.ajax.service;

import org.hibernate.Query;
import org.hibernate.Session;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class InputCheck {
public int checkInput(String inputId,String inputValue){
		
		int flag=0;
         //用户账户验证
		if(inputId.equals("registeraccount")){
			
			Session session=HibernateSessionFactory.getSession();
			String hql="select count(*) from UserInfo as u where u.account=:account";
			Query query=session.createQuery(hql);
			query.setParameter("account", inputValue);
			
			Long count=(Long)query.uniqueResult();     //有问题
			session.close();
			if(count>0){
				flag=1;
			}
		}
		//邮箱验证
		else if(inputId.equals("registeremail")){
			
			Session session=HibernateSessionFactory.getSession();
			String hql="select count(*) from UserInfo as u where u.email=:email";
			Query query=session.createQuery(hql);
			query.setParameter("email", inputValue);
			
			Long count=(Long)query.uniqueResult();     //有问题
			session.close();
			if(count>0){
				flag=3;
			}else{
				flag=2;
			}
		}
		//身份证验证
		else if(inputId.equals("registeridnum")){
			Session session=HibernateSessionFactory.getSession();
			String hql="select count(*) from UserInfo as u where u.idnum=:idnum";
			Query query=session.createQuery(hql);
			query.setParameter("idnum", inputValue);
			
			Long count=(Long)query.uniqueResult();     //有问题
			session.close();
			if(count>0){
				flag=5;
			}else{
				flag=4;
			}
		}
		//电话验证
		else if(inputId.equals("examineetel")){
			Session session=HibernateSessionFactory.getSession();
			String hql="select count(*) from ExamineeInfo as e where e.tel=:tel";
			Query query=session.createQuery(hql);
			query.setParameter("tel", inputValue);
			
			Long count=(Long)query.uniqueResult();     
			session.close();
			if(count>0){
				flag=7;
			}else{
				flag=6;
			}
		}
		//准考证重复验证
		else if(inputId.equals("numberInJsp")){
			Session session=HibernateSessionFactory.getSession();
			String hql="select count(*) from ExamInfo as e where e.number=:number";
			Query query=session.createQuery(hql);
			query.setParameter("number", inputValue);			
			Long count=(Long)query.uniqueResult();     
			session.close();
			if(count>0){
				flag=11;
			}else{
				flag=10;
			}
		}
		
		return flag;
	}
	
	public int checkCertScore(int certId,int certScoreInJsp){
		int i=0;
		CertificateInfoDao ctDao = new CertificateInfoDaoImp();
		CertificateInfo    ct    = ctDao.getCertInfoById(certId);  
		Integer remainScore=ct.getRemainingScore();
		if(null==remainScore){	
			i=12-certScoreInJsp;
		}else{
			i=remainScore-certScoreInJsp;		
		}
		return i;
	}

}
