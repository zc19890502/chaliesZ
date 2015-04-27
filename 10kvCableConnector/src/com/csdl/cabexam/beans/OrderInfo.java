package com.csdl.cabexam.beans;

/**
 * OrderInfo entity. @author MyEclipse Persistence Tools
 */

public class OrderInfo implements java.io.Serializable {

	// Fields

	private Integer orderInfoId;
	private String ordId;
	private String price;
	private String type;
	private String state;
	private ExamineeInfo examineeInfo;
	private UserInfo userInfo;
	private String batch;
	private String refId;
	private String refExplain;

	// Constructors

	/** default constructor */
	public OrderInfo() {
	}

	/** full constructor */
	public OrderInfo(String ordId, String price, String type, String state,
			ExamineeInfo examineeInfo, UserInfo userInfo, String batch, String refId,
			String refExplain) {
		this.ordId = ordId;
		this.price = price;
		this.type = type;
		this.state = state;
		this.examineeInfo = examineeInfo;
		this.userInfo = userInfo;
		this.batch = batch;
		this.refId = refId;
		this.refExplain = refExplain;
	}

	// Property accessors

	public Integer getOrderInfoId() {
		return this.orderInfoId;
	}

	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public String getOrdId() {
		return this.ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ExamineeInfo getExamineeInfo() {
		return examineeInfo;
	}

	public void setExamineeInfo(ExamineeInfo examineeInfo) {
		this.examineeInfo = examineeInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getRefId() {
		return this.refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getRefExplain() {
		return this.refExplain;
	}

	public void setRefExplain(String refExplain) {
		this.refExplain = refExplain;
	}

}