package com.csdl.cabexam.bean;

public class FeeStatistics {
	//缴费年
	private String fYear;
	
	//缴费
	private int payTotal0;
	private int payTotal1;
	private int payTotal2;
	private int payTotal3;
	private int payTotalAll;
	private String payTotalPrice;
	
	//退费
	private int refTotal0;
	private int refTotal1;
	private int refTotal2;
	private int refTotal3;
	private int refTotalAll;
	private String refTotalPrice;
	
	//结余
	private String balance;
	
	public FeeStatistics() {
		
	}

	public FeeStatistics(String fYear, int payTotal0, int payTotal1,
			int payTotal2, int payTotal3, int payTotalAll,
			String payTotalPrice, int refTotal0, int refTotal1, int refTotal2,
			int refTotal3, int refTotalAll, String refTotalPrice, String balance) {
		super();
		this.fYear = fYear;
		this.payTotal0 = payTotal0;
		this.payTotal1 = payTotal1;
		this.payTotal2 = payTotal2;
		this.payTotal3 = payTotal3;
		this.payTotalAll = payTotalAll;
		this.payTotalPrice = payTotalPrice;
		this.refTotal0 = refTotal0;
		this.refTotal1 = refTotal1;
		this.refTotal2 = refTotal2;
		this.refTotal3 = refTotal3;
		this.refTotalAll = refTotalAll;
		this.refTotalPrice = refTotalPrice;
		this.balance = balance;
	}

	public String getfYear() {
		return fYear;
	}

	public void setfYear(String fYear) {
		this.fYear = fYear;
	}

	public int getPayTotal0() {
		return payTotal0;
	}

	public void setPayTotal0(int payTotal0) {
		this.payTotal0 = payTotal0;
	}

	public int getPayTotal1() {
		return payTotal1;
	}

	public void setPayTotal1(int payTotal1) {
		this.payTotal1 = payTotal1;
	}

	public int getPayTotal2() {
		return payTotal2;
	}

	public void setPayTotal2(int payTotal2) {
		this.payTotal2 = payTotal2;
	}

	public int getPayTotal3() {
		return payTotal3;
	}

	public void setPayTotal3(int payTotal3) {
		this.payTotal3 = payTotal3;
	}

	public int getPayTotalAll() {
		return payTotalAll;
	}

	public void setPayTotalAll(int payTotalAll) {
		this.payTotalAll = payTotalAll;
	}

	public String getPayTotalPrice() {
		return payTotalPrice;
	}

	public void setPayTotalPrice(String payTotalPrice) {
		this.payTotalPrice = payTotalPrice;
	}

	public int getRefTotal0() {
		return refTotal0;
	}

	public void setRefTotal0(int refTotal0) {
		this.refTotal0 = refTotal0;
	}

	public int getRefTotal1() {
		return refTotal1;
	}

	public void setRefTotal1(int refTotal1) {
		this.refTotal1 = refTotal1;
	}

	public int getRefTotal2() {
		return refTotal2;
	}

	public void setRefTotal2(int refTotal2) {
		this.refTotal2 = refTotal2;
	}

	public int getRefTotal3() {
		return refTotal3;
	}

	public void setRefTotal3(int refTotal3) {
		this.refTotal3 = refTotal3;
	}

	public int getRefTotalAll() {
		return refTotalAll;
	}

	public void setRefTotalAll(int refTotalAll) {
		this.refTotalAll = refTotalAll;
	}

	public String getRefTotalPrice() {
		return refTotalPrice;
	}

	public void setRefTotalPrice(String refTotalPrice) {
		this.refTotalPrice = refTotalPrice;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
}
