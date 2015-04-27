package com.csdl.cabexam.actions.pay;

import java.util.ArrayList;
import java.util.List;

import com.csdl.cabexam.bean.FeeStatistics;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class CopyOfFeeStatisticsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<FeeStatistics> fsList;
	/**
	 * @return
	 */
	
	OrderInfoDao orderDao = new OrderInfoDao();
	ExtraInfoDao extraDao = new ExtraInfoDaoImp();
	public String execute() {
		String reStr = "success";
		fsList = new ArrayList<FeeStatistics>();
		//��ѯȫ��������
		List<String> years = extraDao.getDistinctYear();
		if (years!=null) {
			FeeStatistics fs = null;
			for (int i = 0; i < years.size(); i++) {
				List<OrderInfo> payOrders = orderDao.getOrderByYear(years.get(i),"1");
				List<OrderInfo> refOrders = orderDao.getOrderByYear(years.get(i),"2");
				int payTotal0=0;
				int payTotal1=0;
				int payTotal2=0;
				int payTotal3=0;
				int payTotalPrice=0;		
				int refTotal0=0;
				int refTotal1=0;
				int refTotal2=0;
				int refTotal3=0;
				int refTotalPrice=0;
				fs = new FeeStatistics();
				fs.setfYear(years.get(i));
				
				if (payOrders!=null) {					
					for (int j = 0; j < payOrders.size(); j++) {
						OrderInfo oi = payOrders.get(i);
						if ("0".equals(oi.getType())) {
							payTotal0=payTotal0+1;
						}else if ("1".equals(oi.getType())) {
							payTotal1=payTotal1+1;
						}else if ("2".equals(oi.getType())) {
							payTotal2=payTotal2+1;
						}else if ("3".equals(oi.getType())) {
							payTotal3=payTotal3+1;
						}
					}
					fs.setPayTotal0(payTotal0);
					fs.setPayTotal1(payTotal1);
					fs.setPayTotal2(payTotal2);
					fs.setPayTotal3(payTotal3);
					fs.setPayTotalAll(payOrders.size());
					payTotalPrice=payTotal0*200+payTotal1*6000+payTotal2*3000+payTotal3*9000;
					fs.setPayTotalPrice(String.valueOf(payTotalPrice));
				}
				if (refOrders!=null) {
					for (int j = 0; j < refOrders.size(); j++) {
						OrderInfo oi = refOrders.get(i);
						if ("0".equals(oi.getType())) {
							refTotal0=refTotal0+1;
						}else if ("1".equals(oi.getType())) {
							refTotal1=refTotal1+1;
						}else if ("2".equals(oi.getType())) {
							refTotal2=refTotal2+1;
						}else if ("3".equals(oi.getType())) {
							refTotal3=refTotal3+1;
						}
					}
					fs.setRefTotal0(refTotal0);
					fs.setRefTotal1(refTotal1);
					fs.setRefTotal2(refTotal2);
					fs.setRefTotal3(refTotal3);
					fs.setRefTotalAll(refOrders.size());
					refTotalPrice=refTotal0*200+refTotal1*6000+refTotal2*3000+refTotal3*9000;
					fs.setRefTotalPrice(String.valueOf(refTotalPrice));
				}
				fsList.add(fs);
			}					
		}
		return reStr;
	}
	
	public List<FeeStatistics> getFsList() {
		return fsList;
	}
	public void setFsList(List<FeeStatistics> fsList) {
		this.fsList = fsList;
	}
	
	
}