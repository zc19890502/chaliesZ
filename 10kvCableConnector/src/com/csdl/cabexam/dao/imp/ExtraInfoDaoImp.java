package com.csdl.cabexam.dao.imp;

import org.hibernate.Query;
import org.hibernate.Session;

import com.csdl.cabexam.beans.ExtraHistory;
import com.csdl.cabexam.beans.ExtraInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;
import java.sql.Timestamp;
import java.util.List;

public  class ExtraInfoDaoImp implements ExtraInfoDao{
	
	
	public ExtraInfo getExtraInfo(){
		Session session = HibernateSessionFactory.getSession();
	    String hql = "from ExtraInfo e";
	    Query q = session.createQuery(hql);
	    ExtraInfo extraInfo = (ExtraInfo) q.uniqueResult();
	    session.close();
	    return extraInfo;
	}	
	
	public List<String> getDistinctBatch(String year){
		Session session = HibernateSessionFactory.getSession();
	    String sql = "select distinct(batch) as batch from dbo.extraHistory where year=?";
	    Query q = session.createSQLQuery(sql);
	    q.setString(0, year);
	    List<String> batchs = q.list();
	    session.close();
	    return batchs;
	}	
	
	public List<String> getDistinctBatch(){
		Session session = HibernateSessionFactory.getSession();
	    String sql = "select distinct(examBatch) as examBatch from dbo.extraHistory";
	    Query q = session.createSQLQuery(sql);
	    List<String> batchs = q.list();
	    session.close();
	    return batchs;
	}
	public List<String> getDistinctYear(){
		Session session = HibernateSessionFactory.getSession();
	    String sql = "select distinct(year) as ehyear from dbo.extraHistory order by ehyear desc";
	    Query q = session.createSQLQuery(sql);
	    List<String> years = q.list();
	    session.close();
	    return years;
	}
	
	public List<ExtraHistory> getExtraHistory(){
		Session session = HibernateSessionFactory.getSession();
	    String hql = "from ExtraHistory e";
	    Query q = session.createQuery(hql);
	    List<ExtraHistory> extraHistorys = q.list();
	    session.close();
	    return extraHistorys;
	}
	
	public ExtraHistory getExtraHistory(String examBatch){
		Session session = HibernateSessionFactory.getSession();
	    String hql = "from ExtraHistory e where examBatch = ?";
	    Query q = session.createQuery(hql);
	    q.setString(0, examBatch);
	    ExtraHistory extraHistory = (ExtraHistory) q.uniqueResult();
	    session.close();
	    return extraHistory;
	}
	
	//根据Id得到用户
	@Override
	public boolean updateExtraInfo(Float theoryScoreLimit,Timestamp signLimitDate,Timestamp theoryExamDate,String examBatch,String theoryExamPrice,String coldExamPrice,String hotExamPrice) {
		boolean flag=false;
		ExtraInfo extraInfo = getExtraInfo();
		if(extraInfo==null){
			try {
				System.out.println("******没有找到考试初始数据!******");
				ExtraInfo ex=new ExtraInfo();
				ex.setSignLimitDate(signLimitDate);
				ex.setTheoryExamDate(theoryExamDate);
				ex.setTheoryScoreLimit(theoryScoreLimit);
				ex.setExamBatch(examBatch);
				ex.setTheoryExamPrice(theoryExamPrice);
				ex.setColdExamPrice(coldExamPrice);
				ex.setHotExamPrice(hotExamPrice);
				CommonDao cd = new CommonDaoImp();
				cd.addObject(ex);
				System.out.println("******建立考试数据成功!******");
				flag=true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else{
			try {
				System.out.println("******找到考试初始数据!******");
				extraInfo.setSignLimitDate(signLimitDate);
				extraInfo.setTheoryExamDate(theoryExamDate);
				extraInfo.setExamBatch(examBatch);
				extraInfo.setTheoryExamPrice(theoryExamPrice);
				extraInfo.setColdExamPrice(coldExamPrice);
				extraInfo.setHotExamPrice(hotExamPrice);
				CommonDao cd = new CommonDaoImp();
				cd.updateObject(extraInfo);
				System.out.println("******修改考试数据成功!******");
				flag=true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return flag;
	}
		
	public boolean updateExtraHistory(Float theoryScoreLimit,Timestamp signLimitDate,Timestamp theoryExamDate,String examBatch,String theoryExamPrice,String coldExamPrice,String hotExamPrice,String year,String batch) {
		boolean flag=false;
		ExtraHistory extraHistory = getExtraHistory(examBatch);
		if(extraHistory==null){
			try {
				System.out.println("******没有找到考试历史数据!******");
				ExtraHistory ex=new ExtraHistory();
				ex.setSignLimitDate(signLimitDate);
				ex.setTheoryExamDate(theoryExamDate);
				ex.setTheoryScoreLimit(theoryScoreLimit);
				ex.setExamBatch(examBatch);
				ex.setTheoryExamPrice(theoryExamPrice);
				ex.setColdExamPrice(coldExamPrice);
				ex.setHotExamPrice(hotExamPrice);
				ex.setYear(year);
				ex.setBatch(batch);
				CommonDao cd = new CommonDaoImp();
				cd.addObject(ex);
				System.out.println("******建立考试历史数据成功!******");
				flag=true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else{
			try {
				System.out.println("******找到考试历史数据!******");
				extraHistory.setSignLimitDate(signLimitDate);
				extraHistory.setTheoryExamDate(theoryExamDate);
				extraHistory.setExamBatch(examBatch);
				extraHistory.setTheoryExamPrice(theoryExamPrice);
				extraHistory.setColdExamPrice(coldExamPrice);
				extraHistory.setHotExamPrice(hotExamPrice);
				extraHistory.setYear(year);
				extraHistory.setBatch(batch);
				CommonDao cd = new CommonDaoImp();
				cd.updateObject(extraHistory);
				System.out.println("******修改考试历史数据成功!******");
				flag=true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println("******修改考试数据成功!******");
		return flag;
	}
	
	

}
