package com.csdl.cabexam.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.csdl.cabexam.beans.RecordOfYear;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.RecordOfYearDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class RecordOfYearDaoImp implements RecordOfYearDao {

	@Override
	public List<RecordOfYear> getRecordByIdYear(String idNumber,String yyyyDate) {
		boolean flag1 = (idNumber==null||"".equals(idNumber));
		boolean flag2 =	(yyyyDate==null||"".equals(yyyyDate));
		if(flag1 && flag2){
			return  this.getAllRecord();
		}else if(!flag1 && flag2){
			return this.getRecordByIdNumber(idNumber);
		}else if(flag1 && !flag2){
			return this.getRecordByYear(yyyyDate);
		}else{
			Session session = HibernateSessionFactory.getSession();
			String hql = "from RecordOfYear r where r.idNumber=:idNumber and r.yyyyDate=:yyyyDate";
			Query query = session.createQuery(hql);
			query.setParameter("idNumber", idNumber);
			query.setParameter("yyyyDate", yyyyDate);
			RecordOfYear onerecord = (RecordOfYear) query.uniqueResult();
			session.close();
			List<RecordOfYear> record = new ArrayList<RecordOfYear>();
			record.add(onerecord);
			return record;
		}
	}
	
	
	
	public List<RecordOfYear> getAllRecord() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from RecordOfYear r";
		Query query = session.createQuery(hql);
		List<RecordOfYear> yearRecordList = query.list();
		session.close();
		return yearRecordList;
	}

	public List<RecordOfYear> getRecordByYear(String yyyyDate) {
		Session session = HibernateSessionFactory.getSession();
		if(yyyyDate==null||"".equals(yyyyDate)){
			return this.getAllRecord();
		}else{
			String hql = "from RecordOfYear r where r.yyyyDate=:yyyyDate";
			Query query = session.createQuery(hql);
			query.setParameter("yyyyDate", yyyyDate);
			List<RecordOfYear> yearRecordList = query.list();
			session.close();
			return yearRecordList;
		}
	}

	public List<RecordOfYear> getRecordByIdNumber(String idNumber) {
		Session session = HibernateSessionFactory.getSession();
		if(idNumber==null||"".equals(idNumber)){
			return this.getAllRecord();
		}else{
			String hql = "from RecordOfYear r where r.idNumber=:idNumber";
			Query query = session.createQuery(hql);
			query.setParameter("idNumber", idNumber);
			List<RecordOfYear> yearRecordList = query.list();
			session.close();
			return yearRecordList;
		}
	}
	public RecordOfYear getRecordById(Integer recordId) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from RecordOfYear r where r.recordID=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, recordId);
		RecordOfYear record = (RecordOfYear) query.uniqueResult();
		session.close();
		return record;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		String name =new RecordOfYearDaoImp().getRecordByIdYear("432524000000000003","2014").getName();
		System.out.println(name);
		*/
		
		List<RecordOfYear> yearRecordList = new RecordOfYearDaoImp().getRecordByIdYear("432524000000000001", "2014");
		for(RecordOfYear ry:yearRecordList){
			System.out.println(ry.getIdNumber());
			System.out.println(ry.getCertNumber());
			System.out.println(ry.getComments());
			System.out.println(ry.getName());
			System.out.println(ry.getYyyyDate());
			System.out.println();
		}
		
		/*
		CommonDao cd = new CommonDaoImp();
		RecordOfYear ry = new RecordOfYear("2014", "æ¤æ¤", 1, "432524132255469820", "ÌìÌÃÄñ", 1, "00001", 100, 300, 60, 40, 1, "ºÃº¢×Ó");
		cd.addObject(ry);
		*/
		
	}

}
