package com.csdl.cabexam.beans;

/**
 * ManagerInfo entity. @author MyEclipse Persistence Tools
 */

public class ManagerInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer managerId;
	private String managerAccount;
	private String powerGrade;
	private String passwd;
	private String email;
	private String realName;

	// Constructors

	/** default constructor */
	public ManagerInfo() {
	}

	/** minimal constructor */
	public ManagerInfo(Integer managerId) {
		this.managerId = managerId;
	}

	/** full constructor */
	public ManagerInfo(Integer managerId, String managerAccount,
			String powerGrade, String passwd, String email, String realName) {
		this.managerId = managerId;
		this.managerAccount = managerAccount;
		this.powerGrade = powerGrade;
		this.passwd = passwd;
		this.email = email;
		this.realName = realName;
	}

	// Property accessors

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerAccount() {
		return this.managerAccount;
	}

	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}

	public String getPowerGrade() {
		return this.powerGrade;
	}

	public void setPowerGrade(String powerGrade) {
		this.powerGrade = powerGrade;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}