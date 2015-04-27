package com.csdl.cabexam.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class ExamInfoDaoImp implements ExamInfoDao {

	//根据用户id得到考生成绩信息对象
	public ExamInfo getExamInfoByUserId(Integer userId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from ExamInfo e where e.userInfo.userInfoId=:userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		ExamInfo examInfo = (ExamInfo) query.uniqueResult();
		session.close();
		return examInfo;
	}

	@Override
	public List<ExamInfo> getExamInfoByState(String exameState) {
		Session session = HibernateSessionFactory.getSession();
		String sql = "from ExamInfo e where exameState=?";
		Query query = session.createQuery(sql);
		query.setString(0,exameState);
		List<ExamInfo> examList = query.list();
		session.close();
		return examList;
	}

	@Override
	public List<ExamInfo>  getAllExamInfo() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from ExamInfo e where e.exameState='0'");
		List<ExamInfo> examList = query.list();
		session.close();
		return examList;
	}

	@Override
	public String updateExaminnerState(Integer userInfoId, String examState) {
		Session session = HibernateSessionFactory.getSession();
		Transaction trans=session.beginTransaction();
		Query query = session.createQuery("from ExamInfo e where e.userInfo.userInfoId=?");
		query.setInteger(0, userInfoId);
		ExamInfo eb = (ExamInfo) query.uniqueResult();
		eb.setExameState(examState);
		session.update(eb);
		trans.commit();
		session.close();
		return null;
	}

	@Override
	public List<ExamInfo> getExamInfoByRealName(String realName) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from ExamInfo e where e.userInfo.realName like :realName");
		query.setString("realName", "%"+realName+"%");
		List<ExamInfo> examList = query.list();
		session.close();
		return examList;
	}
	
	/**
	 * @param falg  true表示准考证不重复  ，false表示准考证重复
	 */
	public boolean checkNumberExist(String number){
		boolean flag = true;  //不存在重复准考证
		Session session = HibernateSessionFactory.getSession();
		Query query =session.createQuery("from ExamInfo e where e.number:number and e.exameState=0");
		query.setString("number", number);
		ExamInfo eb = (ExamInfo) query.uniqueResult();
		if(eb!=null){
			flag=false;
		}
		session.close();
		return flag;
	}

	public String getMaxExamNum() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "select e.number from ExamInfo e order by e.number desc";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		String examNum = (String) query.uniqueResult();
		session.close();
		return examNum;
	}
	
	public void UpdateExamScoreById(int id,Float theoryScore,Float operateScore){
		Session session = HibernateSessionFactory.getSession();
		Transaction ts=session.beginTransaction();
		String hql = "update ExamInfo set theoryScore=?,operateScore=? where userInfoID=?";
	    Query query=session.createSQLQuery(hql);
		query.setFloat(0, theoryScore);
		query.setFloat(1, operateScore);
		query.setInteger(2, id);
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
	//通过名字和准考证号来查询考场安排信息
	public List<ExamInfo> getExamManageByCondition(String realName,String number){
		Session session = HibernateSessionFactory.getSession();
		
		String sql = "from ExamInfo ex where ex.exameState='0' ";
		if(realName!=null && !"".equals(realName)){
			sql +="and ex.userInfo.realName like '%"+realName+"%'";
		}
		if(number!=null && !"".equals(number)){
			sql +="and ex.number like '%"+number+"%'";
		}

		//System.out.println(sql);
		Query query = session.createQuery(sql);
		List<ExamInfo> list = query.list();
		session.close();
		return list;
	}

	@Override
	public ExamInfo getPassedExamInfoByUserId(Integer userId,String exameState) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from ExamInfo e where e.userInfo.userInfoId=:userId and e.exameState="+exameState;
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		ExamInfo examInfo = (ExamInfo) query.uniqueResult();
		session.close();
		return examInfo;
	}

	@Override
	public ExamInfo getExamInfoByExamInfoId(int examInfoId) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = session.beginTransaction();
		ExamInfo examInfo = (ExamInfo) session.get(ExamInfo.class, examInfoId);
		tran.commit();
		session.close();
		return examInfo;
	}
	
	@Override
	public List<ExamInfo> getExamInfoOfThisYear(){
		Session session = HibernateSessionFactory.getSession();
		String hql = "from ExamInfo e where e.userInfo.userState='1'";
		Query query = session.createQuery(hql);
		List<ExamInfo> list = query.list();
		session.close();
		return list;
	}
	
	@Override
	public boolean checkCoordinate(Integer userInfoId, Integer examInfoId,
			String number) {
		ExamInfo examInfo = this.getExamInfoByExamInfoId(examInfoId);
		if(examInfo!=null){
			System.out.println(examInfo.getUserInfo().getUserInfoId()!=userInfoId);
			System.out.println("******"+examInfo.getUserInfo().getUserInfoId()+"*****");
			System.out.println("*******"+userInfoId+"***********");
			System.out.println(examInfo.getExamInfoId()!=examInfoId);
			System.out.println(examInfo.getNumber().equals(number));
			return  (examInfo.getUserInfo().getUserInfoId()!=(int)userInfoId||examInfo.getExamInfoId()!=(int)examInfoId||!examInfo.getNumber().equals(number))?false:true;
		}else{
			return false;
		}
	}
}
