package com.csdl.cabexam.beans;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * CertificateInfo entity. @author MyEclipse Persistence Tools
 */

public class CertificateInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer certificateInfoId;
	private UserInfo userInfo;
	private String certificationNum;
	private String certificationState;
	private Timestamp revokeDate;
	private String revokeReason;
	private String revokeOperator;
	private Timestamp certificationGrantDate;
	private Integer remainingScore;
	private Set deductionRecordInfos = new HashSet(0);


	// Constructors

	/** default constructor */
	public CertificateInfo() {
	}

	/** minimal constructor */
	public CertificateInfo(Integer certificateInfoId, UserInfo userInfo) {
		this.certificateInfoId = certificateInfoId;
		this.userInfo = userInfo;
	}

	/** full constructor */
	public CertificateInfo(Integer certificateInfoId, UserInfo userInfo,
			String certificationNum, String certificationState,
			Timestamp revokeDate, String revokeReason, String revokeOperator,Timestamp certificationGrantDate,Set deductionRecordInfos,Integer remainingScore) {
		this.certificateInfoId = certificateInfoId;
		this.userInfo = userInfo;
		this.certificationNum = certificationNum;
		this.certificationState = certificationState;
		this.revokeDate = revokeDate;
		this.revokeReason = revokeReason;
		this.revokeOperator = revokeOperator;
		this.certificationGrantDate = certificationGrantDate;
		this.deductionRecordInfos = deductionRecordInfos;
		this.remainingScore = remainingScore;
	}

	// Property accessors

	public Integer getCertificateInfoId() {
		return this.certificateInfoId;
	}

	public void setCertificateInfoId(Integer certificateInfoId) {
		this.certificateInfoId = certificateInfoId;
	}

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getCertificationNum() {
		return this.certificationNum;
	}

	public void setCertificationNum(String certificationNum) {
		this.certificationNum = certificationNum;
	}

	public String getCertificationState() {
		return this.certificationState;
	}

	public void setCertificationState(String certificationState) {
		this.certificationState = certificationState;
	}

	public Timestamp getRevokeDate() {
		return this.revokeDate;
	}

	public void setRevokeDate(Timestamp revokeDate) {
		this.revokeDate = revokeDate;
	}

	public String getRevokeReason() {
		return this.revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	public String getRevokeOperator() {
		return this.revokeOperator;
	}

	public void setRevokeOperator(String revokeOperator) {
		this.revokeOperator = revokeOperator;
	}

	public Timestamp getCertificationGrantDate() {
		return this.certificationGrantDate;
	}

	public void setCertificationGrantDate(Timestamp certificationGrantDate) {
		this.certificationGrantDate = certificationGrantDate;
	}

	public Set getDeductionRecordInfos() {
		return this.deductionRecordInfos;
	}

	public void setDeductionRecordInfos(Set deductionRecordInfos) {
		this.deductionRecordInfos = deductionRecordInfos;
	}

	public Integer getRemainingScore() {
		return remainingScore;
	}

	public void setRemainingScore(Integer remainingScore) {
		this.remainingScore = remainingScore;
	}



}