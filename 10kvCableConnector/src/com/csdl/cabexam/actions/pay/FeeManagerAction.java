package com.csdl.cabexam.actions.pay;

import java.util.List;

import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class FeeManagerAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	//订单集合
	private List<OrderInfo> orders;
	//有考试的年份集合
	private List<String> years;
	//考试批次集合
	private List<String> batchs;
	
	private String year;
	
	private String batch;
	
	private String type;
	
	private String state;
	
	//最大页面
	private int maxPage;
	//当前页
	private int page;
	
	//分页方法
	public void fyFunction(){
		
	}
	/**
	 * @return
	 */
	OrderInfoDao orderDao = new OrderInfoDao();
	ExtraInfoDao extraDao = new ExtraInfoDaoImp();
	public String execute() {
		String reStr = "success";
		years = extraDao.getDistinctYear();
		if (years!=null) {
			String year0 = years.get(0);
			batchs = extraDao.getDistinctBatch(year0);
			//单页数量
			int length = 2;
			if (page==0) {
				page = 1;
			}
			System.out.println("page: "+page);
			System.out.println("year: "+year);
			System.out.println("batch: "+batch);
			System.out.println("type: "+type);
			System.out.println("state: "+state);
			int size = orderDao.querySizeFY(year, batch, type, state);
			System.out.println("size: "+size);
			if (size>0) {
				if (size%length==0) {
					maxPage = size/length;
				}else {
					maxPage = size/length+1;
				}
				System.out.println("maxPage: "+maxPage);
				if (page>maxPage) {
					page = maxPage;
				}
				orders = orderDao.queryByAllFY(length, page, year, batch, type, state);				
			}
		}
		return reStr;
	}
	
	public List<OrderInfo> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderInfo> orders) {
		this.orders = orders;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public List<String> getBatchs() {
		return batchs;
	}

	public void setBatchs(List<String> batchs) {
		this.batchs = batchs;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}