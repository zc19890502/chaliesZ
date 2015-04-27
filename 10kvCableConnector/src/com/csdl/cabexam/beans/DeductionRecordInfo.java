package com.csdl.cabexam.beans;

import java.sql.Timestamp;

/**
 * DeductionRecordInfo entity. @author MyEclipse Persistence Tools
 */

public class DeductionRecordInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer deductionRecordInfoId;
	private CertificateInfo certificateInfo;
	private Integer deduction;
	private String deductionReason;
	private String decuctionOperator;
	private Timestamp deductionDate;

	// Constructors

	/** default constructor */
	public DeductionRecordInfo() {
	}

	/** minimal constructor */
	public DeductionRecordInfo(Integer deductionRecordInfoId,
			CertificateInfo certificateInfo) {
		this.deductionRecordInfoId = deductionRecordInfoId;
		this.certificateInfo = certificateInfo;
	}

	/** full constructor */
	public DeductionRecordInfo(Integer deductionRecordInfoId,
			CertificateInfo certificateInfo, Integer deduction,
			String deductionReason, String decuctionOperator,
			Timestamp deductionDate) {
		this.deductionRecordInfoId = deductionRecordInfoId;
		this.certificateInfo = certificateInfo;
		this.deduction = deduction;
		this.deductionReason = deductionReason;
		this.decuctionOperator = decuctionOperator;
		this.deductionDate = deductionDate;
	}

	// Property accessors

	public Integer getDeductionRecordInfoId() {
		return this.deductionRecordInfoId;
	}

	public void setDeductionRecordInfoId(Integer deductionRecordInfoId) {
		this.deductionRecordInfoId = deductionRecordInfoId;
	}

	public CertificateInfo getCertificateInfo() {
		return this.certificateInfo;
	}

	public void setCertificateInfo(CertificateInfo certificateInfo) {
		this.certificateInfo = certificateInfo;
	}

	public Integer getDeduction() {
		return this.deduction;
	}

	public void setDeduction(Integer deduction) {
		this.deduction = deduction;
	}

	public String getDeductionReason() {
		return this.deductionReason;
	}

	public void setDeductionReason(String deductionReason) {
		this.deductionReason = deductionReason;
	}

	public String getDecuctionOperator() {
		return this.decuctionOperator;
	}

	public void setDecuctionOperator(String decuctionOperator) {
		this.decuctionOperator = decuctionOperator;
	}

	public Timestamp getDeductionDate() {
		return this.deductionDate;
	}

	public void setDeductionDate(Timestamp deductionDate) {
		this.deductionDate = deductionDate;
	}


}