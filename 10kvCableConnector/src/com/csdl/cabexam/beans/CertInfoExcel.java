package com.csdl.cabexam.beans;

import java.sql.Timestamp;


public class CertInfoExcel {
	private String certificationNum;
	private Timestamp certificationGrantDate;
	private String company;
	private String realName;
	private String idnum;
	private Integer remainingScore;
	
	public CertInfoExcel(){
		
	}
	public CertInfoExcel(String certificationNum,Timestamp certificationGrantDate,
			String company,String realName,String idnum,Integer remainingScore){
		this.certificationGrantDate = certificationGrantDate;
		this.certificationNum = certificationNum;
		this.company = company;
		this.realName = realName;
		this.idnum = idnum;
		this.remainingScore=remainingScore;
	}
	public String getCertificationNum() {
		return certificationNum;
	}
	public void setCertificationNum(String certificationNum) {
		this.certificationNum = certificationNum;
	}
	public Timestamp getCertificationGrantDate() {
		return certificationGrantDate;
	}
	public void setCertificationGrantDate(Timestamp certificationGrantDate) {
		this.certificationGrantDate = certificationGrantDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public Integer getRemainingScore() {
		return remainingScore;
	}
	public void setRemainingScore(Integer remainingScore) {
		this.remainingScore = remainingScore;
	}

}
