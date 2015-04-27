package com.csdl.cabexam.beans;

import java.util.Date;


public class ScoreExcel {
	private Integer userInfoId;
	private Integer examInfoId;
	private String realName;
	private String sex;
	private String number;
	private String idnum;
	private String place;
	private String company;   //二级单位
	private Date theoryExamDate;
	private Float theoryScore;
	private String theoryExamRoom;
	private String theoryExamPlace;
	private Float coldMidScore;
	private Float coldTemScore;
	private Float hotMidScore;
	private Float hotTemScore;
	private String picture;
	private String scoreBatch;
	
	
	public ScoreExcel(){
		
	}
	   //获得成绩下载列表的构造函数
	public ScoreExcel(Integer userInfoId, Integer examInfoId,String realName,String number,String idnum,String place,String company,Date theoryExamDate,Float theoryScore,Float coldMidScore,Float coldTemScore,Float hotMidScore,Float hotTemScore) {   
        this.userInfoId = userInfoId;
        this.examInfoId = examInfoId;
        this.realName = realName;  
        this.number = number;  
        this.idnum = idnum;  
        this.place = place;   
        this.company=company;
        this.theoryExamDate = theoryExamDate;  
        this.theoryScore =theoryScore; 
        this.coldMidScore =coldMidScore; 
        this.coldTemScore =coldTemScore; 
        this.hotMidScore =hotMidScore; 
        this.hotTemScore =hotTemScore; 
    } 
	
    public ScoreExcel(Integer userInfoId, Integer examInfoId,String realName,String number,String idnum,String place,String scoreBatch,String company,Date theoryExamDate,Float theoryScore,Float coldMidScore,Float coldTemScore,Float hotMidScore,Float hotTemScore) {   
        this.userInfoId = userInfoId;
        this.examInfoId = examInfoId;
        this.realName = realName;  
        this.number = number;  
        this.idnum = idnum;  
        this.place = place;   
        this.scoreBatch=scoreBatch;
        this.company=company;
        this.theoryExamDate = theoryExamDate;  
        this.theoryScore =theoryScore; 
        this.coldMidScore =coldMidScore; 
        this.coldTemScore =coldTemScore; 
        this.hotMidScore =hotMidScore; 
        this.hotTemScore =hotTemScore; 
    }                         
	                        
	//userInfoId,examInfoId,realName,number,idnum,place,company,theoryExamDate,theoryScore,coldMidScore,coldMidScore,hotMidScore,hotTemScore
	//获得准考证下载列表的构造函数
	public ScoreExcel(Integer userInfoId,Integer examInfoId, String realName,String number,String idnum,String place,String company) {   
		this.userInfoId = userInfoId;
		this.examInfoId = examInfoId;
		this.realName = realName;  
		this.number = number;  
		this.idnum = idnum;  
		this.place = place;   
		this.company=company;
	}
	//通过userINfoid获得准考证信息的构造函数
	public ScoreExcel(Integer userInfoId,Integer examInfoId, String realName,String sex,String number,String idnum,String place,String company,Date theoryExamDate,String theoryExamPlace,String theoryExamRoom,String picture) {   
		this.userInfoId = userInfoId;
		this.examInfoId = examInfoId;
        this.realName = realName; 
        this.sex=sex;
        this.number = number;  
        this.idnum = idnum;  
        this.place = place;   
        this.company=company;
        this.theoryExamDate = theoryExamDate;  
    	this.theoryExamRoom=theoryExamRoom;
    	this.theoryExamPlace=theoryExamPlace;
        this.picture=picture;
    } 



	public Integer getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getTheoryExamDate() {
		return theoryExamDate;
	}

	public void setTheoryExamDate(Date theoryExamDate) {
		this.theoryExamDate = theoryExamDate;
	}

	public Float getTheoryScore() {
		return theoryScore;
	}

	public void setTheoryScore(Float theoryScore) {
		this.theoryScore = theoryScore;
	}


	public String getTheoryExamRoom() {
		return theoryExamRoom;
	}

	public void setTheoryExamRoom(String theoryExamRoom) {
		this.theoryExamRoom = theoryExamRoom;
	}

	public String getTheoryExamPlace() {
		return theoryExamPlace;
	}

	public void setTheoryExamPlace(String theoryExamPlace) {
		this.theoryExamPlace = theoryExamPlace;
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

	public String getScoreBatch() {
		return scoreBatch;
	}
	public void setScoreBatch(String scoreBatch) {
		this.scoreBatch = scoreBatch;
	}
	public void setHotTemScore(Float hotTemScore) {
		this.hotTemScore = hotTemScore;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getExamInfoId() {
		return examInfoId;
	}

	public void setExamInfoId(Integer examInfoId) {
		this.examInfoId = examInfoId;
	}

	
	

}
