package com.csdl.cabexam.beans;

import java.sql.Timestamp;

/**
 * ExamInfo entity. @author MyEclipse Persistence Tools
 */

public class ExamInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer examInfoId;
	private UserInfo userInfo;
	private String number;
	private Float theoryScore;
	private String exameState;
	private Timestamp theoryExamDate;
	private String theoryExamPlace;
	private String theoryExamRoom;
	private Float coldMidScore;
	private Float coldTemScore;
	private Float hotMidScore;
	private Float hotTemScore;
	private Timestamp limitDate;
	private String scoreBatch;
	// Constructors

	/** default constructor */
	public ExamInfo() {
	}

	/** minimal constructor */
	public ExamInfo(Integer examInfoId, UserInfo userInfo) {
		this.examInfoId = examInfoId;
		this.userInfo = userInfo;
	}

	/** full constructor */
	public ExamInfo(Integer examInfoId, UserInfo userInfo, String number,
			Float theoryScore,String exameState, Timestamp theoryExamDate,
		    String theoryExamPlace,String theoryExamRoom,Float coldMidScore,
		    Float coldTemScore,Float hotMidScore,Float hotTemScore, 
		    Timestamp limitDate,String scoreBatch) {
		this.examInfoId = examInfoId;
		this.userInfo = userInfo;
		this.number = number;
		this.theoryScore = theoryScore;
		this.exameState = exameState;
		this.theoryExamDate = theoryExamDate;
		this.theoryExamPlace = theoryExamPlace;
		this.theoryExamRoom = theoryExamRoom;
		this.coldMidScore = coldMidScore;
		this.coldTemScore = coldTemScore;
		this.hotMidScore = hotMidScore;
		this.hotTemScore = hotTemScore;
		this.limitDate = limitDate;
		this.scoreBatch=scoreBatch;
	}

	// Property accessors

	public Integer getExamInfoId() {
		return this.examInfoId;
	}

	public void setExamInfoId(Integer examInfoId) {
		this.examInfoId = examInfoId;
	}

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Float getTheoryScore() {
		return this.theoryScore;
	}

	public void setTheoryScore(Float theoryScore) {
		this.theoryScore = theoryScore;
	}

	public String getExameState() {
		return this.exameState;
	}

	public void setExameState(String exameState) {
		this.exameState = exameState;
	}

	public Timestamp getTheoryExamDate() {
		return this.theoryExamDate;
	}

	public void setTheoryExamDate(Timestamp theoryExamDate) {
		this.theoryExamDate = theoryExamDate;
	}

	public String getTheoryExamPlace() {
		return this.theoryExamPlace;
	}

	public void setTheoryExamPlace(String theoryExamPlace) {
		this.theoryExamPlace = theoryExamPlace;
	}

	public String getTheoryExamRoom() {
		return this.theoryExamRoom;
	}

	public void setTheoryExamRoom(String theoryExamRoom) {
		this.theoryExamRoom = theoryExamRoom;
	}



	public Float getColdMidScore() {
		return coldMidScore;
	}

	public void setColdMidScore(Float coldMidScore) {
		this.coldMidScore = coldMidScore;
	}

	public Float getColdTemScore() {
		return coldTemScore;
	}

	public void setColdTemScore(Float coldTemScore) {
		this.coldTemScore = coldTemScore;
	}

	public Float getHotMidScore() {
		return hotMidScore;
	}

	public void setHotMidScore(Float hotMidScore) {
		this.hotMidScore = hotMidScore;
	}

	public Float getHotTemScore() {
		return hotTemScore;
	}

	public void setHotTemScore(Float hotTemScore) {
		this.hotTemScore = hotTemScore;
	}

	public Timestamp getLimitDate() {
		return this.limitDate;
	}


	public void setLimitDate(Timestamp limitDate) {
		this.limitDate = limitDate;
	}

	public String getScoreBatch() {
		return scoreBatch;
	}

	public void setScoreBatch(String scoreBatch) {
		this.scoreBatch = scoreBatch;
	}

}