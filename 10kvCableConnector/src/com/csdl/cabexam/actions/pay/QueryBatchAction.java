package com.csdl.cabexam.actions.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class QueryBatchAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<OrderInfo> orders;
	private List<String> years;
	private List<String> batchs;
	private String year;
	/**
	 * @return
	 */
	OrderInfoDao orderDao = new OrderInfoDao();
	ExtraInfoDao extraDao = new ExtraInfoDaoImp();
	public String execute() {
		System.out.println("year: "+year);
		if (year!=null) {
			batchs = extraDao.getDistinctBatch(year);
			String strs = batchs.get(0);
			for (int i = 1; i < batchs.size(); i++) {
				strs = strs + "|" + batchs.get(i);
			}
			System.out.println("strs: "+strs);
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");	
			try {
				PrintWriter out = response.getWriter();
				out.print(strs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
}