package com.csdl.cabexam.beans;

import java.sql.Timestamp;

/**
 * ExamineeInfo entity. @author MyEclipse Persistence Tools
 */

public class ExamineeInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer examineeInfoId;
	private UserInfo userInfo;
	private String sex;
	private String nationality;
	private String place;
	private String homesite;
	private String tel;
	private Timestamp birth;
	private String photo;
	private String party;
	private String education;
	private String physicalCondition;
	private Timestamp workDate;
	private Integer workLimitTime;
	private String skillLevel;
	private String company;
	private String jobResume;
	private String idcardScan;
	private String companyExamine;
	private String workingProof;
	private String checkState;
	private String examineeSource;
	
	private String advice;
	private String theoryFeePath;
	private String operateFeePath;

	
	
	//private 


	// Constructors

	/** default constructor */
	public ExamineeInfo() {
	}

	/** minimal constructor */
	public ExamineeInfo(Integer examineeInfoId, UserInfo userInfo, String sex,
			String nationality) {
		this.examineeInfoId = examineeInfoId;
		this.userInfo = userInfo;
		this.sex = sex;
		this.nationality = nationality;
	}

	/** full constructor */
	public ExamineeInfo(Integer examineeInfoId, UserInfo userInfo, String sex,
			String nationality, String place, String homesite, String tel,
			Timestamp birth, String photo, String party, String education,
			String physicalCondition, Timestamp workDate,
			Integer workLimitTime, String skillLevel, String company,
			String jobResume, String idcardScan, String companyExamine,
			String workingProof, String checkState,String examineeSource,
			String advice,String theoryFeePath,String operateFeePath) {
		this.examineeInfoId = examineeInfoId;
		this.userInfo = userInfo;
		this.sex = sex;
		this.nationality = nationality;
		this.place = place;
		this.homesite = homesite;
		this.tel = tel;
		this.birth = birth;
		this.photo = photo;
		this.party = party;
		this.education = education;
		this.physicalCondition = physicalCondition;
		this.workDate = workDate;
		this.workLimitTime = workLimitTime;
		this.skillLevel = skillLevel;
		this.company = company;
		this.jobResume = jobResume;
		this.idcardScan = idcardScan;
		this.companyExamine = companyExamine;
		this.workingProof = workingProof;
		this.checkState = checkState;
		this.examineeSource = examineeSource;
		this.advice=advice;
		this.theoryFeePath=theoryFeePath;
		this.operateFeePath=operateFeePath;
	}

	// Property accessors

	public Integer getExamineeInfoId() {
		return this.examineeInfoId;
	}

	public void setExamineeInfoId(Integer examineeInfoId) {
		this.examineeInfoId = examineeInfoId;
	}

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getHomesite() {
		return this.homesite;
	}

	public void setHomesite(String homesite) {
		this.homesite = homesite;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Timestamp getBirth() {
		return this.birth;
	}

	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getParty() {
		return this.party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPhysicalCondition() {
		return this.physicalCondition;
	}

	public void setPhysicalCondition(String physicalCondition) {
		this.physicalCondition = physicalCondition;
	}

	public Timestamp getWorkDate() {
		return this.workDate;
	}

	public String getAdvice() {
		return advice;
	}

	public String getTheoryFeePath() {
		return theoryFeePath;
	}

	public String getOperateFeePath() {
		return operateFeePath;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public void setTheoryFeePath(String theoryFeePath) {
		this.theoryFeePath = theoryFeePath;
	}

	public void setOperateFeePath(String operateFeePath) {
		this.operateFeePath = operateFeePath;
	}

	public void setWorkDate(Timestamp workDate) {
		this.workDate = workDate;
	}

	public Integer getWorkLimitTime() {
		return this.workLimitTime;
	}

	public void setWorkLimitTime(Integer workLimitTime) {
		this.workLimitTime = workLimitTime;
	}

	public String getSkillLevel() {
		return this.skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobResume() {
		return this.jobResume;
	}

	public void setJobResume(String jobResume) {
		this.jobResume = jobResume;
	}

	public String getIdcardScan() {
		return this.idcardScan;
	}

	public void setIdcardScan(String idcardScan) {
		this.idcardScan = idcardScan;
	}

	public String getCompanyExamine() {
		return this.companyExamine;
	}

	public void setCompanyExamine(String companyExamine) {
		this.companyExamine = companyExamine;
	}

	public String getWorkingProof() {
		return this.workingProof;
	}

	public void setWorkingProof(String workingProof) {
		this.workingProof = workingProof;
	}

	public String getCheckState() {
		return this.checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public String getExamineeSource() {
		return examineeSource;
	}

	public void setExamineeSource(String examineeSource) {
		this.examineeSource = examineeSource;
	}



}