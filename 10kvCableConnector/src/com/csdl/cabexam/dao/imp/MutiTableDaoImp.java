package com.csdl.cabexam.dao.imp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class MutiTableDaoImp implements MutiTableDao {

	@Override
	//获得不包含成绩的xls列表信息
/*	public List<Object[]> getAllXlsInfo() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from ExamineeInfo e ,ExamInfo ex where e.userInfo=ex.userInfo";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		session.close();
		return list;
	}*/
	//得到历年成绩信息列表
	 public List<ScoreExcel> getScoreXlsInfo(){
		List<ScoreExcel> list=null;
		Session session = HibernateSessionFactory.getSession();                                                     
		String hql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,ex.scoreBatch,e.company,ex.theoryExamDate,ex.theoryScore,ex.coldMidScore,ex.coldTemScore,ex.hotMidScore,ex.hotTemScore) from ExamInfo ex,ExamineeInfo e where e.userInfo=ex.userInfo and ex.userInfo.userState=0";
		Query query = session.createQuery(hql);
		list = query.list();
		session.close();
		return list;
	}
	//得到今年理论成绩信息列表
	public List<ScoreExcel> getTheoryScoreXlsInfo(){
		List<ScoreExcel> list=null;
		Session session = HibernateSessionFactory.getSession();                                                     
		String hql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,e.company,ex.theoryExamDate,ex.theoryScore,ex.coldMidScore,ex.coldTemScore,ex.hotMidScore,ex.hotTemScore) from ExamInfo ex,ExamineeInfo e where e.userInfo=ex.userInfo and e.userInfo.userState='1' and ex.exameState='0' and (e.checkState='3' or e.checkState='5' or e.checkState='6')";
		Query query = session.createQuery(hql);
		list = query.list();
		session.close();
		return list;
	}
	//得到今年操作成绩信息列表
	//TODO 添加CheckState=5
	public List<ScoreExcel> getAllScoreXlsInfo(){
		List<ScoreExcel> list=null;
		Session session = HibernateSessionFactory.getSession();                                                     
		String hql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,e.company,ex.theoryExamDate,ex.theoryScore,ex.coldMidScore,ex.coldTemScore,ex.hotMidScore,ex.hotTemScore) from ExamInfo ex,ExamineeInfo e where e.userInfo=ex.userInfo and e.userInfo.userState='1' and ex.exameState='0' and e.checkState='5' and ex.theoryScore>=80";
		Query query = session.createQuery(hql);
		list = query.list();
		session.close();
		return list;
	}
	//得到准考证信息列表
	public List<ScoreExcel> getNumberXlsInfo(){
		List<ScoreExcel> list=null;
		Session session = HibernateSessionFactory.getSession();
		String hql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,e.company) from ExamInfo ex,ExamineeInfo e where e.userInfo=ex.userInfo and e.userInfo.userState='1' and ex.exameState='0'";
		Query query = session.createQuery(hql);
		list = query.list();
		session.close();
		return list;
	}
	//通过userInfoid来获得个人准考证信息
	public ScoreExcel getNumberXlsINfoByexamInfoId(int userInfoId,int examInfoId){
		Session session = HibernateSessionFactory.getSession();
		String hql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,e.company) from ExamInfo ex,ExamineeInfo e where e.userInfo=? and ex.userInfo=? and ex.examInfoId=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, userInfoId);
		query.setInteger(1, userInfoId);
		query.setInteger(2, examInfoId);
		ScoreExcel se=(ScoreExcel) query.uniqueResult();
		session.close();
		return se;
	}
	//通过userInfoid来获得个人成绩信息
	public ScoreExcel getScoreXlsINfoByexamInfoId(int userInfoId,int examInfoId){
		Session session = HibernateSessionFactory.getSession();
		String hql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,e.company,ex.theoryExamDate,ex.theoryScore,ex.coldMidScore,ex.coldTemScore,ex.hotMidScore,ex.hotTemScore) from ExamInfo ex,ExamineeInfo e where e.userInfo=? and ex.userInfo=? and ex.examInfoId=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, userInfoId);
		query.setInteger(1, userInfoId);
		query.setInteger(2, examInfoId);
		ScoreExcel se=(ScoreExcel) query.uniqueResult();
		session.close();
		return se;
	}
	
	
	//通过userInfoid来获得个人准考证信息
		public ScoreExcel getAdmissionINfoById(int userid,int examid){
			Session session = HibernateSessionFactory.getSession();
			String hql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,e.sex,ex.number,ex.userInfo.idnum,e.place,e.company,ex.theoryExamDate,ex.theoryExamPlace,ex.theoryExamRoom,e.photo) from ExamInfo ex,ExamineeInfo e where e.userInfo=? and ex.userInfo=? and ex.examInfoId=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, userid);
			query.setInteger(1, userid);
			query.setInteger(2, examid);
			ScoreExcel se=(ScoreExcel) query.uniqueResult();
			session.close();
			return se;
		}
		
	
	//通过examInfoid逐个修改个人成绩
	public void UpdateScoreById(int id,Float theoryScore,Float coldMidScore,Float coldTemScore,Float hotMidScore,Float hotTemScore){
//	public void UpdateScoreById(int id,Float theoryScore,Float operateScore){
		Session session = HibernateSessionFactory.getSession();
		Transaction ts=session.beginTransaction();
		String hql = "update ExamInfo set theoryScore=?,coldMidScore=?,coldTemScore=?,hotMidScore=?,hotTemScore=? where examInfoId=?";
		Query query=session.createSQLQuery(hql);
		query.setFloat(0, theoryScore);
		query.setFloat(1, coldMidScore);
		query.setFloat(2, coldTemScore);
		query.setFloat(3, hotMidScore);
		query.setFloat(4, hotTemScore);
		query.setInteger(5, id);
		try {
			query.executeUpdate();
			ts.commit();	
		} catch (Exception e) {
			ts.rollback();
			e.printStackTrace();
		}finally{
			if(session.isOpen()){
				session.close();
			}
		}
	}

	//通过examInfoid逐个修改准考证号
	
	public void UpdateNumberByexamInfoId(int examInfoid,String number){
		ExamInfoDao examInfoDao = new ExamInfoDaoImp();
		CommonDao cd = new CommonDaoImp();
		ExamInfo examInfo = examInfoDao.getExamInfoByExamInfoId(examInfoid);
		examInfo.setNumber(number);
		cd.updateObject(examInfo);
	}	
	//根据姓名、籍贯、工作单位组合查询成绩信息
	public List<ScoreExcel> getTheoryScoreInfoByCondition(String realName,
			String place, String company,String operateScoreFlag){
		Session session = HibernateSessionFactory.getSession();
		//and ex.examState='0'
		String sql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,e.company,ex.theoryExamDate,ex.theoryScore,ex.coldMidScore,ex.coldTemScore,ex.hotMidScore,ex.hotTemScore) from ExamInfo ex,ExamineeInfo e where  e.userInfo=ex.userInfo and e.userInfo.userState='1' and ex.exameState='0' and (e.checkState='3' or e.checkState='5' or e.checkState='6') ";
		if(realName!=null && !"".equals(realName)){
			sql +=" and e.userInfo.realName like '%"+realName+"%'";
		}
		if(place!=null && !"".equals(place)){
			sql +=" and e.place like '%"+place+"%'";
		}
		if(company!=null && !"".equals(company)){
			sql +=" and e.company like '%"+company+"%'";
		}
		if(operateScoreFlag.equals("1")){
			sql +=" and ex.theoryScore<80";
		}else if(operateScoreFlag.equals("2")){
			sql +=" and ex.theoryScore>=80";
		}
		List<ScoreExcel> list=null;
        try {
        	Query query = session.createQuery(sql);
        	
        	list = query.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//System.out.println(sql);
		session.close();
		return list;
		
		
		
	}
	public List<ScoreExcel> getAllScoreInfoByCondition(String realName,String place,String company){
		
		Session session = HibernateSessionFactory.getSession();
		
		String sql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,e.company,ex.theoryExamDate,ex.theoryScore,ex.coldMidScore,ex.coldTemScore,ex.hotMidScore,ex.hotTemScore) from ExamInfo ex,ExamineeInfo e where  e.userInfo=ex.userInfo and e.userInfo.userState='1' and ex.exameState='0' and e.checkState='5' and ex.theoryScore>=80 ";
		if(realName!=null && !"".equals(realName)){
			sql +="and e.userInfo.realName like '%"+realName+"%' ";
		}
		if(place!=null && !"".equals(place)){
			sql +="and e.place like '%"+place+"%' ";
		}
		if(company!=null && !"".equals(company)){
			sql +="and e.company like '%"+company+"%' ";
		}
	
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		
		List<ScoreExcel> list = query.list();
		session.close();
		return list;
		
		
			
		
	}
	
	public List<ScoreExcel> getScoreInfoByCondition(String realName,String scoreBatch,String company){
		
		Session session = HibernateSessionFactory.getSession();
		
		String sql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,ex.scoreBatch,e.company,ex.theoryExamDate,ex.theoryScore,ex.coldMidScore,ex.coldTemScore,ex.hotMidScore,ex.hotTemScore) from ExamInfo ex,ExamineeInfo e where  e.userInfo=ex.userInfo ";
		if(realName!=null && !"".equals(realName)){
			sql +="and e.userInfo.realName like '%"+realName+"%' ";
		}
		if(scoreBatch!=null && !"".equals(scoreBatch)){
			sql +="and ex.scoreBatch like '%"+scoreBatch+"%' ";
		}
		if(company!=null && !"".equals(company)){
			sql +="and e.company like '%"+company+"%' ";
		}
	
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		
		List<ScoreExcel> list = query.list();
		session.close();
		return list;
		
		
			
		
	}
	
	
	//根据姓名、籍贯、工作单位组合查询准考证信息
	public List<ScoreExcel> getNumberInfoByCondition(String realName,
			String place, String company){
		Session session = HibernateSessionFactory.getSession();
		
		String sql = "select new com.csdl.cabexam.beans.ScoreExcel(ex.userInfo.userInfoId,ex.examInfoId,ex.userInfo.realName,ex.number,ex.userInfo.idnum,e.place,e.company) from ExamInfo ex,ExamineeInfo e where  e.userInfo=ex.userInfo and e.userInfo.userState='1' and ex.exameState='0' ";
		if(realName!=null && !"".equals(realName)){
			sql +="and e.userInfo.realName like '%"+realName+"%'";
		}
		if(place!=null && !"".equals(place)){
			sql +="and e.place like '%"+place+"%'";
		}
		if(company!=null && !"".equals(company)){
			sql +="and e.company like '%"+company+"%'";
		}
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		
		List<ScoreExcel> list = query.list();
		session.close();
		return list;
		
		
		
	}
	
/*	//方法测试
	public static void main(String[] args) {
		
		  //测试通过userInfoid来获得个人成绩信息
	    Object[] obj=new MutiTableDaoImp().getXlsINfoById(1);
		System.out.println(obj);
		ExamineeInfo examinee=(ExamineeInfo)obj[0];
		ExamInfo exam=(ExamInfo)obj[1];
		System.out.println(examinee.getCompany());
		System.out.println(examinee.getHomesite());
		System.out.println(examinee.getTel());
		System.out.println(exam.getNumber());
		System.out.println(exam.getExamDate());
		
		List<ScoreExcel> list=new MutiTableDaoImp().getAllXlsInfo();
	    for(ScoreExcel s:list){
	    	System.out.println(s.getRealName());
			System.out.println(s.getExamDate());
			System.out.println(s.getCompany());
		}
		  //测试通过userInfoid逐个修改个人成绩
//		new MutiTableDaoImp().UpdateScoreById(1, 80);
		
	}*/


		
}