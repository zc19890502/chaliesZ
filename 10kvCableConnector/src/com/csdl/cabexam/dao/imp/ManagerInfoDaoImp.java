package com.csdl.cabexam.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.ManagerInfoDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class ManagerInfoDaoImp implements ManagerInfoDao {

	//���������õ��û�
	public ManagerInfo getManagerInfoByName(String managerAccount) {
		Session session = HibernateSessionFactory.getSession();
		String sql = "from ManagerInfo m where m.managerAccount=?";
		Query q = session.createQuery(sql);
		q.setString(0, managerAccount);
		ManagerInfo managerInfo = (ManagerInfo) q.uniqueResult();
		session.close();
		return managerInfo;
	}

	//��ѯ���й���Ա����Ϣ
	public List<ManagerInfo> getAllManagerInfo() {
		
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from ManagerInfo");
		List<ManagerInfo> managerList = query.list();
		session.close();
		return managerList;
	}

	//���ݹ���ԱID�õ�����Ա��Ϣ
	@Override
	public ManagerInfo getManagerInfoById(int managerId) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = session.beginTransaction();
		ManagerInfo managerInfo = (ManagerInfo) session.get(ManagerInfo.class, managerId);
		tran.commit();
		session.close();
		return managerInfo;
	}
	
}
