package com.csdl.cabexam.dao.imp;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.beans.DeductionRecordInfo;
import com.csdl.cabexam.dao.DeductionRecordInfoDao;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class DeductionRecordInfoDaoImp implements DeductionRecordInfoDao {

	@Override
	public List<DeductionRecordInfo> getDeducRecordBycertId(int certInfoId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		String hql = "from DeductionRecordInfo d where d.certificateInfo.certificateInfoId=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, certInfoId);
		List<DeductionRecordInfo> deducRecordList = query.list();
		session.close();
		return deducRecordList;
	}

	@Override
	public DeductionRecordInfo getDeducRecordBydeducId(int deducId) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = null;
		DeductionRecordInfo deducInfo = null;
		try{
			tran = session.beginTransaction();
			deducInfo = (DeductionRecordInfo) session.get(DeductionRecordInfo.class, deducId);
			tran.commit();
		}catch(Exception e){
			e.printStackTrace();
			tran.rollback();
		}finally{
			if(session.isOpen())
				session.close();
		}
		return deducInfo;
	}

}
