package com.csdl.cabexam.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class OrderInfoDao {	
	public void addOrderInfo(OrderInfo oi){
		Session session=HibernateSessionFactory.getSession();
		Transaction trans=session.beginTransaction();
		session.save(oi);
		trans.commit();
		session.close();
	}
	
	public OrderInfo queryById(int orderInfoId){
		OrderInfo order = null;
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery("from OrderInfo o where o.orderInfoId=?");
		query.setInteger(0, orderInfoId);
		order = (OrderInfo) query.uniqueResult();
		session.close();
		return order;
	}
	
	public List<OrderInfo> queryByAll(){
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery("from OrderInfo o");
		List<OrderInfo> orderList = query.list();
		session.close();
		return orderList;
	}
	
	public int querySize(){
		Session session=HibernateSessionFactory.getSession();
		String sql = "select count(*) from orderInfo";
		Query query = session.createSQLQuery(sql);
		int i = (Integer) query.uniqueResult();
		session.close();
		return i;
	}
	
	public int querySizeFY(String year,String batch,String type,String state){
		Session session=HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from orderInfo where 1=1");
		if (year!=null && !"".equals(year)) {
			sb.append(" and batch like '"+year+"%' ");
		}
		if (batch!=null && !"".equals(batch)) {
			sb.append(" and batch like '%"+batch+"' ");
		}
		if (type!=null && !"".equals(type)) {
			sb.append(" and type = '"+type+"' ");
		}
		if (state!=null && !"".equals(state)) {
			sb.append(" and state = '"+state+"' ");
		}
		Query query = session.createSQLQuery(sb.toString());
		int i = (Integer) query.uniqueResult();
		session.close();
		return i;
	}
	
	public String nullToNothing(String str){
		String reStr="";
		if (str!=null) {
			reStr = str;
		}
		return reStr;
	}
	
	public List<OrderInfo> queryByAllFY(int length,int page,String year,String batch,String type,String state){
		//开始行 = (当前页-1) x 单页行数
		int startRow = (page-1)*length;
		System.out.println("length: "+length);
		System.out.println("startRow: "+startRow);
		Session session=HibernateSessionFactory.getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select top("+length+") * from orderInfo where 1=1");
		if (year!=null && !"".equals(year)) {
			sb.append(" and batch like '"+year+"%' ");
		}
		if (batch!=null && !"".equals(batch)) {
			sb.append(" and batch like '%"+batch+"' ");
		}
		if (type!=null && !"".equals(type)) {
			sb.append(" and type = '"+type+"' ");
		}
		if (state!=null && !"".equals(state)) {
			sb.append(" and state = '"+state+"' ");
		}
		sb.append(" and orderInfoId not in(select top("+startRow+") orderInfoId from orderInfo where 1=1");
		if (year!=null && !"".equals(year)) {
			sb.append(" and batch like '"+year+"%' ");
		}
		if (batch!=null && !"".equals(batch)) {
			sb.append(" and batch like '%"+batch+"' ");
		}
		if (type!=null && !"".equals(type)) {
			sb.append(" and type = '"+type+"' ");
		}
		if (state!=null && !"".equals(state)) {
			sb.append(" and state = '"+state+"'");
		}
		sb.append(")");
		Query query = session.createSQLQuery(sb.toString()).addEntity(OrderInfo.class);
		List<OrderInfo> orderList = query.list();
		for (int i = 0; i < orderList.size(); i++) {
			System.out.println("ordId: "+orderList.get(i).getOrdId());
		}
		session.close();
		return orderList;
	}
	
	public OrderInfo queryByOrderId(String orderId){
		OrderInfo order = null;
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery("from OrderInfo o where o.ordId=?");
		query.setString(0, orderId);
		order = (OrderInfo) query.uniqueResult();
		session.close();
		return order;
	}
	
	public OrderInfo queryByUserIdAndBath(int userId,String bath){
		OrderInfo order = null;
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery("from OrderInfo o where o.userInfo.userInfoId=? and o.batch=?");
		query.setInteger(0, userId);
		query.setString(1, bath);
		order = (OrderInfo) query.uniqueResult();
		session.close();
		return order;
	}
	
	public OrderInfo queryByIdOrderInfoId(int userInfoId,String type,String batch){
		System.out.println("userInfoId: "+userInfoId);
		System.out.println("type: "+type);
		System.out.println("batch: "+batch);
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery("from OrderInfo o where o.type=? and o.batch=? and o.userInfo.userInfoId=? and o.state!='5'");
		query.setString(0, type);
		query.setString(1, batch);
		query.setInteger(2, userInfoId);
		OrderInfo order = (OrderInfo) query.uniqueResult();
		return order;
	}	
	
	public void refOrder(OrderInfo order){
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery("from OrderInfo o where o.orderInfoId=?");
		query.setInteger(0, order.getOrderInfoId());
		OrderInfo oi = (OrderInfo) query.uniqueResult();
		oi.setState(order.getState());
		oi.setRefExplain(order.getRefExplain());
		Transaction trans=session.beginTransaction();
		session.save(oi);
		trans.commit();
		session.close();
	}
	
	public void updateOrderInfo(OrderInfo oi){
		Session session=HibernateSessionFactory.getSession();
		Transaction trans=session.beginTransaction();
		session.update(oi);
		trans.commit();
		session.close();
	}

	public void updateOrderInfoState(String state,String ordId){
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery("from OrderInfo o where o.ordId=?");
		query.setString(0, ordId);
		OrderInfo order = (OrderInfo) query.uniqueResult();
		order.setState(state);
		Transaction trans=session.beginTransaction();
		session.update(order);
		trans.commit();
		session.close();
	}
	
	public String getMaxOrder(String type){
		String orderInfoId = "";
		Session session = HibernateSessionFactory.getSession();                                                     
		String sql = "select max(ordid) from orderInfo where type = ?";
		Query query = session.createSQLQuery(sql);
		query.setString(0, type);
		if (query.uniqueResult()!=null) {
			orderInfoId = query.uniqueResult().toString();
		}
		session.close();
		return orderInfoId;
	}
	
	public List<OrderInfo> getOrderByYear(String year,String zhuantai){
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		Session session = HibernateSessionFactory.getSession();
		String hql = "";
		if (zhuantai=="1") {
			hql = "from OrderInfo o where o.batch like '"+year+"%' ";
		}else {
			hql = "from OrderInfo o where o.batch like '"+year+"%' and o.state = '4'";
		}
		Query query = session.createQuery(hql);
		list = query.list();
		return list;
	}
}
