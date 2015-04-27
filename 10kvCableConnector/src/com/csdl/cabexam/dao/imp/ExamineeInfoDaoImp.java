package com.csdl.cabexam.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class ExamineeInfoDaoImp implements ExamineeInfoDao {

	public ExamineeInfo getExamineeInfoByID(int examineeId) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = session.beginTransaction();
		ExamineeInfo examineeInfo = (ExamineeInfo) session.get(ExamineeInfo.class, examineeId);
		tran.commit();
		session.close();
		return examineeInfo;
	}

	public ExamineeInfo getExamineeInfoByUserInfo(Integer userId) {
		// TODO Auto-generated method stub
		//System.out.println(userId);
		Session session = HibernateSessionFactory.getSession();
		String hql = "from ExamineeInfo e where e.userInfo.userInfoId=:userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		List<ExamineeInfo> examineeInfoList =  query.list();
		session.close();
		if(examineeInfoList.isEmpty()){
		    return null;
		}else{
		    return examineeInfoList.get(0);
		}
	}

	public List<ExamineeInfo> getExamineeInfoByCheckState(String checkState) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from ExamineeInfo e where e.checkState=:checkState and e.userInfo.userState='1'";
		Query query = session.createQuery(hql);
		query.setParameter("checkState", checkState);
		List<ExamineeInfo> examineeInfos = query.list();
		session.close();
		return examineeInfos;
	}
    //TODO 调出符合操作缴费的列表
	public List<ExamineeInfo> getExamineeInfoByOperateFee() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "select e from ExamineeInfo e , ExamInfo ex where e.userInfo=ex.userInfo and e.checkState='3' and e.userInfo.userState='1' and ex.theoryScore>=80";
		String hql1="select e from ExamineeInfo e , ExamInfo ex where e.userInfo=ex.userInfo and e.checkState='6'  and e.userInfo.userState='1' and ex.theoryScore>=80";
		String hql2="select e from ExamineeInfo e , ExamInfo ex where e.userInfo=ex.userInfo and e.checkState='5' and e.userInfo.userState='1' and ex.theoryScore>=80";
		Query query = session.createQuery(hql);
		List<ExamineeInfo> examineeInfos = query.list();
		examineeInfos.addAll(session.createQuery(hql1).list());
		examineeInfos.addAll(session.createQuery(hql2).list());
		session.close();
		return examineeInfos;
	}
	
	
	public ExamineeInfo getExamineeInfoByAccount(String account) {
		Session session = HibernateSessionFactory.getSession();
		String sql = "from ExamineeInfo e where e.userInfo.account=?";
		Query q = session.createQuery(sql);
		q.setString(0, account);
		ExamineeInfo examineeInfo = (ExamineeInfo) q.uniqueResult();
		session.close();
		return examineeInfo;
	}

	public List<ExamineeInfo> getAllExammerInfo() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from ExamineeInfo e where e.userInfo.userState=1");
		List<ExamineeInfo> exammerList = query.list();
		session.close();
		return exammerList;
	}

	//根据姓名、籍贯、工作单位组合查询
	@Override
	public List<ExamineeInfo> getExamineeInfoByCondition(String realName,
			String homesite, String company) {
		Session session = HibernateSessionFactory.getSession();
		String sql = "from ExamineeInfo e where 1=1";
		if(realName!=null && !"".equals(realName)){
			sql +="and e.userInfo.realName like '%"+realName+"%'";
		}
		if(homesite!=null && !"".equals(homesite)){
			sql +="and homesite like '%"+homesite+"%'";
		}
		if(company!=null && !"".equals(company)){
			sql +="and company like '%"+company+"%'";
		}
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		
		List<ExamineeInfo> examineeInfos = query.list();
		session.close();
		return examineeInfos;
	}
	
	
	public List<ExamineeInfo> getExamineeInfoOfFeeByCondition(String realName,String skillLeval,String company){	
		Session session = HibernateSessionFactory.getSession();
		String sql = "from ExamineeInfo e where 1=1";
		if(realName!=null && !"".equals(realName)){
			sql +="and e.userInfo.realName like '%"+realName+"%'";
		}
		if(skillLeval!=null && !"".equals(skillLeval)){
			sql +="and skillLevel like '%"+skillLeval+"%'";
		}
		if(company!=null && !"".equals(company)){
			sql +="and company like '%"+company+"%'";
		}
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		
		List<ExamineeInfo> examineeInfos = query.list();
		session.close();
		return examineeInfos;
	}
	
	public List<ExamineeInfo> getExamineeInfoOfTheoryFeeByCondition(String realName,String skillLeval,String company){	
		Session session = HibernateSessionFactory.getSession();
		String sql1 = "from ExamineeInfo e where e.userInfo.userState='1' and e.checkState='1' ";
		String sql2 = "from ExamineeInfo e where e.userInfo.userState='1' and e.checkState='4' ";
		String sql3 = "from ExamineeInfo e where e.userInfo.userState='1' and e.checkState='3' ";
		String sqlSerarch="";
		if(realName!=null && !"".equals(realName)){
			sqlSerarch +="and e.userInfo.realName like '%"+realName+"%'";
		}
		if(skillLeval!=null && !"".equals(skillLeval)){
			sqlSerarch +="and e.skillLevel like '%"+skillLeval+"%'";
		}
		if(company!=null && !"".equals(company)){
			sqlSerarch +="and e.company like '%"+company+"%'";
		}
		//System.out.println(sql); session.createQuery(sql);
		List<ExamineeInfo> examineeInfo1 = session.createQuery(sql1+sqlSerarch).list();
		examineeInfo1.addAll(session.createQuery(sql2+sqlSerarch).list());
		examineeInfo1.addAll(session.createQuery(sql3+sqlSerarch).list());
		session.close();
		return examineeInfo1;
	}
	
	public List<ExamineeInfo> getExamineeInfoOfOperateFeeByCondition(String realName,String skillLeval,String company){	
		Session session = HibernateSessionFactory.getSession();
		String sql1 = "select e from ExamineeInfo e , ExamInfo ex where e.userInfo=ex.userInfo and e.checkState='3' and e.userInfo.userState='1' and ex.theoryScore>=80 ";
		String sql2 = "select e from ExamineeInfo e , ExamInfo ex where e.userInfo=ex.userInfo and e.checkState='6' and e.userInfo.userState='1' and ex.theoryScore>=80";
		String sql3 = "select e from ExamineeInfo e , ExamInfo ex where e.userInfo=ex.userInfo and e.checkState='5' and e.userInfo.userState='1' and ex.theoryScore>=80";
		String sqlSerarch="";
		if(realName!=null && !"".equals(realName)){
			sqlSerarch +="and e.userInfo.realName like '%"+realName+"%'";
		}
		if(skillLeval!=null && !"".equals(skillLeval)){
			sqlSerarch +="and e.skillLevel like '%"+skillLeval+"%'";
		}
		if(company!=null && !"".equals(company)){
			sqlSerarch +="and e.company like '%"+company+"%'";
		}
		//System.out.println(sql); session.createQuery(sql);
		List<ExamineeInfo> examineeInfo1 = session.createQuery(sql1+sqlSerarch).list();
		examineeInfo1.addAll(session.createQuery(sql2+sqlSerarch).list());
		examineeInfo1.addAll(session.createQuery(sql3+sqlSerarch).list());
		session.close();
		return examineeInfo1;
	}
	
	
	public List<ExamineeInfo> getFailedExamineeInfoByCondition(String realName,
			String homesite, String company) {
		Session session = HibernateSessionFactory.getSession();
		String sql = "from ExamineeInfo e where e.checkState='2'";
		if(realName!=null && !"".equals(realName)){
			sql +="and e.userInfo.realName like '%"+realName+"%'";
		}
		if(homesite!=null && !"".equals(homesite)){
			sql +="and homesite like '%"+homesite+"%'";
		}
		if(company!=null && !"".equals(company)){
			sql +="and company like '%"+company+"%'";
		}
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		
		List<ExamineeInfo> examineeInfos = query.list();
		session.close();
		return examineeInfos;
	}
	
	public List<ExamineeInfo> getPassExamineeInfoByCondition(String realName,
			String homesite, String company) {
		Session session = HibernateSessionFactory.getSession();
		String sql = "from ExamineeInfo e where e.checkState='1'";
		if(realName!=null && !"".equals(realName)){
			sql +="and e.userInfo.realName like '%"+realName+"%'";
		}
		if(homesite!=null && !"".equals(homesite)){
			sql +="and homesite like '%"+homesite+"%'";
		}
		if(company!=null && !"".equals(company)){
			sql +="and company like '%"+company+"%'";
		}
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		
		List<ExamineeInfo> examineeInfos = query.list();
		session.close();
		return examineeInfos;
	}
	public List<ExamineeInfo> getcheckStateExamineeInfoByCondition(String realName,
			String homesite, String company,String checkState) {
		Session session = HibernateSessionFactory.getSession();
		String sql = "";
		if(null!=checkState&&!"".equals(checkState)){
			sql = "from ExamineeInfo e where e.userInfo.userState=1 and e.checkState="+checkState;
		}else{
			sql = "from ExamineeInfo e where e.userInfo.userState=1";
		}
		if(realName!=null && !"".equals(realName)){
			sql +=" and e.userInfo.realName like '%"+realName+"%'";
		}
		if(homesite!=null && !"".equals(homesite)){
			sql +=" and homesite like '%"+homesite+"%'";
		}
		if(company!=null && !"".equals(company)){
			sql +=" and company like '%"+company+"%'";
		}
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		
		List<ExamineeInfo> examineeInfos = query.list();
		session.close();
		return examineeInfos;
	}
	
	public List<ExamineeInfo> getWaitExamineeInfoByCondition(String realName,
			String homesite, String company) {
		Session session = HibernateSessionFactory.getSession();
		String sql = "from ExamineeInfo e where e.checkState='0'";
		if(realName!=null && !"".equals(realName)){
			sql +="and e.userInfo.realName like '%"+realName+"%'";
		}
		if(homesite!=null && !"".equals(homesite)){
			sql +="and homesite like '%"+homesite+"%'";
		}
		if(company!=null && !"".equals(company)){
			sql +="and company like '%"+company+"%'";
		}
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		
		List<ExamineeInfo> examineeInfos = query.list();
		session.close();
		return examineeInfos;
	}
	
	public void updateExaminee(ExamineeInfo examinee) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction trans=session.beginTransaction();
		session.update(examinee);
		trans.commit();
		session.close();
	}
}
