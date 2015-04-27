package com.csdl.cabexam.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.beans.Notice;
import com.csdl.cabexam.dao.NoticeDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class NoticeDaoImp implements NoticeDao {

	@Override
	public List<Notice> getAllNoticeInfo() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Notice n order by n.noticeDate";
		Query query = session.createQuery(hql);
		List<Notice> noticeList = query.list();
		session.close();
		return noticeList;
	}

	@Override
	public Notice getNoticeByNoticeId(int noticeId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = session.beginTransaction();
		Notice notice = (Notice) session.get(Notice.class, noticeId);
		tran.commit();
		session.close();
		return notice;
	}

	@Override
	public List<Notice> getNoticeByNoticeType(String noticeType,int pageSize) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Notice n where n.noticeType =? order by n.noticeDate";
		Query query = session.createQuery(hql);
		query.setString(0, noticeType);
		query.setMaxResults(pageSize);
		List<Notice> noticeList = query.list();
		session.close();
		return noticeList;
	}

	@Override
	public List<Notice> getAllNoticeInfoByCondition(String head,String type) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String sql = "from Notice n where 1=1";
		if(head!=null && !"".equals(head)){
			sql +="and n.noticeHead like '%"+head+"%'";
		}
		if(type!=null && !"".equals(type)){
			sql +="and n.noticeType = '"+type+"'";
		}
		//System.out.println(sql);
		Query query = session.createQuery(sql);
		List<Notice> noticeList = query.list();
		session.close();
		return noticeList;
	}

}
