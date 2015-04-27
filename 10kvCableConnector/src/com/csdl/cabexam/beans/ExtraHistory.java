package com.csdl.cabexam.beans;

import java.sql.Timestamp;

/**
 * ExtraHistory entity. @author MyEclipse Persistence Tools
 */

public class ExtraHistory implements java.io.Serializable {

	// Fields

	private Integer extraInfoId;
	private Float theoryScoreLimit;
	private Timestamp signLimitDate;
	private Timestamp theoryExamDate;
	private String examBatch;
	private String theoryExamPrice;
	private String coldExamPrice;
	private String hotExamPrice;
	private String year;
	private String batch;

	// Constructors

	/** default constructor */
	public ExtraHistory() {
	}

	/** full constructor */
	public ExtraHistory(Float theoryScoreLimit, Timestamp signLimitDate,
			Timestamp theoryExamDate, String examBatch, String theoryExamPrice,
			String coldExamPrice, String hotExamPrice, String year, String batch) {
		this.theoryScoreLimit = theoryScoreLimit;
		this.signLimitDate = signLimitDate;
		this.theoryExamDate = theoryExamDate;
		this.examBatch = examBatch;
		this.theoryExamPrice = theoryExamPrice;
		this.coldExamPrice = coldExamPrice;
		this.hotExamPrice = hotExamPrice;
		this.year = year;
		this.batch = batch;
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
		return this.examBatch;
	}

	public void setExamBatch(String examBatch) {
		this.examBatch = examBatch;
	}

	public String getTheoryExamPrice() {
		return this.theoryExamPrice;
	}

	public void setTheoryExamPrice(String theoryExamPrice) {
		this.theoryExamPrice = theoryExamPrice;
	}

	public String getColdExamPrice() {
		return this.coldExamPrice;
	}

	public void setColdExamPrice(String coldExamPrice) {
		this.coldExamPrice = coldExamPrice;
	}

	public String getHotExamPrice() {
		return this.hotExamPrice;
	}

	public void setHotExamPrice(String hotExamPrice) {
		this.hotExamPrice = hotExamPrice;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

}