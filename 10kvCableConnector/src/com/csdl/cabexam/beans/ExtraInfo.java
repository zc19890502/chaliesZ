package com.csdl.cabexam.beans;

import java.sql.Timestamp;

/**
 * ExtraInfo entity. @author MyEclipse Persistence Tools
 */

public class ExtraInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer extraInfoId;
	private Float theoryScoreLimit;
	private Timestamp signLimitDate;
	private Timestamp theoryExamDate;
	private String examBatch;
	private String theoryExamPrice;
	private String coldExamPrice;
	private String hotExamPrice;

	// Constructors

	/** default constructor */
	public ExtraInfo() {
	}

	/** minimal constructor */
	public ExtraInfo(Integer extraInfoId) {
		this.extraInfoId = extraInfoId;
	}

 
	/** full constructor */
	public ExtraInfo(Integer extraInfoId, Float theoryScoreLimit, Timestamp signLimitDate,
			Timestamp theoryExamDate,String examBatch) {
		this.extraInfoId = extraInfoId;
		this.theoryScoreLimit = theoryScoreLimit;
		this.signLimitDate = signLimitDate;
		this.theoryExamDate = theoryExamDate;
		this.examBatch=examBatch;
	}

	// Property accessors

	public Integer getExtraInfoId() {
		return this.extraInfoId;
	}

	public void setExtraInfoId(Integer extraInfoId) {
		this.extraInfoId = extraInfoId;
	}

	public Float getTheoryScoreLimit() {
		return this.theoryScoreLimit;
	}

	public void setTheoryScoreLimit(Float theoryScoreLimit) {
		this.theoryScoreLimit = theoryScoreLimit;
	}

	public Timestamp getSignLimitDate() {
		return this.signLimitDate;
	}

	public void setSignLimitDate(Timestamp signLimitDate) {
		this.signLimitDate = signLimitDate;
	}

	public Timestamp getTheoryExamDate() {
		return this.theoryExamDate;
	}

	public void setTheoryExamDate(Timestamp theoryExamDate) {
		this.theoryExamDate = theoryExamDate;
	}

	public String getExamBatch() {
		return examBatch;
	}

	public void setExamBatch(String examBatch) {
		this.examBatch = examBatch;
	}

	public String getTheoryExamPrice() {
		return theoryExamPrice;
	}

	public void setTheoryExamPrice(String theoryExamPrice) {
		this.theoryExamPrice = theoryExamPrice;
	}

	public String getColdExamPrice() {
		return coldExamPrice;
	}

	public void setColdExamPrice(String coldExamPrice) {
		this.coldExamPrice = coldExamPrice;
	}

	public String getHotExamPrice() {
		return hotExamPrice;
	}

	public void setHotExamPrice(String hotExamPrice) {
		this.hotExamPrice = hotExamPrice;
	}


}