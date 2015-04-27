package com.csdl.cabexam.beans;

import java.sql.Timestamp;

/**
 * DeductionRecordInfo entity. @author MyEclipse Persistence Tools
 */

public class RecordOfYear implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private Integer recordID;
	 private String yyyyDate;      // --ʱ��
	 private String name;        
	 private Integer sex;             // 0ΪŮ1Ϊ��
	 private String idNumber;         // --���֤��
	 private String company;       
	 private Integer kind;            //--0Ϊ����  1Ϊ���� 2Ϊ����+����
	 private String certNumber;       //   --֤����
	 private Integer yearCount ;      //  --�������
	 private Integer threeYearCount;  // --��������
	 private Integer passCount;       //  --�ϸ���
	 private Integer failCount;       // --���ϸ���
	 private Integer deduction;       //  --�۷�           
	 private String comments;         //  --��ע
	
	 
	public RecordOfYear() {
		 super();
	 }
	/* default constructor */
	public RecordOfYear(
			String yyyyDate, String name,
			Integer sex, String idNumber,
			String company, Integer kind,
			String certNumber,Integer yearCount,
			Integer threeYearCount, Integer passCount,
			Integer failCount,Integer deduction,
			String comments) {
		super();
		this.yyyyDate = yyyyDate;
		this.name = name;
		this.sex = sex;
		this.idNumber = idNumber;
		this.company = company;
		this.kind = kind;
		this.certNumber = certNumber;
		this.yearCount = yearCount;
		this.threeYearCount = threeYearCount;
		this.passCount = passCount;
		this.failCount = failCount;
		this.deduction = deduction;
		this.comments = comments;
	}

	// Property accessors
	public Integer getRecordID() {
		return recordID;
	}
	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYyyyDate() {
		return yyyyDate;
	}
	public void setYyyyDate(String yyyyDate) {
		this.yyyyDate = yyyyDate;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public String getCertNumber() {
		return certNumber;
	}

	public void setCertNumber(String certNumber) {
		this.certNumber = certNumber;
	}

	public Integer getYearCount() {
		return yearCount;
	}

	public void setYearCount(Integer yearCount) {
		this.yearCount = yearCount;
	}

	public Integer getThreeYearCount() {
		return threeYearCount;
	}

	public void setThreeYearCount(Integer threeYearCount) {
		this.threeYearCount = threeYearCount;
	}

	public Integer getPassCount() {
		return passCount;
	}

	public void setPassCount(Integer passCount) {
		this.passCount = passCount;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public Integer getDeduction() {
		return deduction;
	}

	public void setDeduction(Integer deduction) {
		this.deduction = deduction;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

   

}