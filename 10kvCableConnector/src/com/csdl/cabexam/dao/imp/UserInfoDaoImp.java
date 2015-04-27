package com.csdl.cabexam.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class UserInfoDaoImp implements UserInfoDao{
	
	//根据Id得到用户
	public UserInfo getUserInfoById(Integer userId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = session.beginTransaction();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userId);
		tran.commit();
		session.close();
		return userInfo;
	}
	
	//根据姓名得到用户
	public UserInfo getUserInfoByName(String account) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String sql = "from UserInfo u where u.account=?";
		Query q = session.createQuery(sql);
		q.setString(0, account);
		UserInfo userInfo = (UserInfo) q.uniqueResult();
		session.close();
		return userInfo;
	}
	
	//根据身份证得到用户
	public UserInfo getUserInfoByidnum(String idnum) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String sql = "from UserInfo u where u.idnum=?";
		Query q = session.createQuery(sql);
		q.setString(0, idnum);
		UserInfo userInfo = (UserInfo) q.uniqueResult();
		session.close();
		return userInfo;
	}
	//得到用户所有信息
	public List<UserInfo> getAllUserInfo(){
		Session session = HibernateSessionFactory.getSession();
		String hql = "from UserInfo u where u=(select e.userInfo from ExamInfo e where e.exameState=3)";
		Query query = session.createQuery(hql);
		List<UserInfo> userInfoList = query.list();
		session.close();
		return userInfoList;
	}

	@Override
	public List<UserInfo> getAllUserInfoByState(String userState) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from UserInfo u where u.userState="+userState;
		Query query = session.createQuery(hql);
		List<UserInfo> userInfoList = query.list();
		session.close();
		return userInfoList;
	}
}
