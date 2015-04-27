/*
 * 公共Dao，一些添加，删除，更新操作，只需将要添加到数据库的对象（任何对象）传入
 * 
 * 
 * 
 */

package com.csdl.cabexam.dao.imp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class CommonDaoImp implements CommonDao {

	public void addObject(Object obj) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction trans=session.beginTransaction();
		session.save(obj);
		trans.commit();
		session.close();
	}

	public void deleteObject(Object obj) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction trans=session.beginTransaction();
		session.delete(obj);
		trans.commit();
		session.close();
	}

	public void updateObject(Object obj) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction trans=session.beginTransaction();
		session.update(obj);
		trans.commit();
		session.close();
	}

	public long getCountNum(String type) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		String sql="select count(t) from "+type+" t";
		Query query=session.createQuery(sql);
		Long count=(Long)query.uniqueResult();
		session.close();
		return count;
	}


	public Integer saveObjectAndGetObjectId(Object obj) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getSession();
		Transaction trans=session.beginTransaction();
		session.save(obj);
		Integer key=(Integer)session.getIdentifier(obj);
		trans.commit();
		session.close();
		return key;
	}

}
