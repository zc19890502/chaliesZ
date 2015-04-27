package com.csdl.cabexam.beans;

import java.sql.Timestamp;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer noticeId;
	private String noticeHead;
	private Timestamp noticeDate;
	private String noticeOperator;
	private String noticeType;
	private String noticeContent;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(Integer noticeId) {
		this.noticeId = noticeId;
	}

	/** full constructor */
	public Notice(Integer noticeId, String noticeHead, Timestamp noticeDate,
			String noticeOperator, String noticeType, String noticeContent) {
		this.noticeId = noticeId;
		this.noticeHead = noticeHead;
		this.noticeDate = noticeDate;
		this.noticeOperator = noticeOperator;
		this.noticeType = noticeType;
		this.noticeContent = noticeContent;
	}

	// Property accessors

	public Integer getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeHead() {
		return this.noticeHead;
	}

	public void setNoticeHead(String noticeHead) {
		this.noticeHead = noticeHead;
	}

	public Timestamp getNoticeDate() {
		return this.noticeDate;
	}

	public void setNoticeDate(Timestamp noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeOperator() {
		return this.noticeOperator;
	}

	public void setNoticeOperator(String noticeOperator) {
		this.noticeOperator = noticeOperator;
	}

	public String getNoticeType() {
		return this.noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

}