package com.csdl.cabexam.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class UserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userInfoId;
	private String account;
	private String passwd;
	private String email;
	private String realName;
	private String idnum;
	private String userState;
	private Set examInfos = new HashSet(0);
	private Set certificateInfos = new HashSet(0);
	private Set examineeInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(Integer userInfoId, String account, String passwd,
			String email, String realName, String idnum,String userState) {
		this.userInfoId = userInfoId;
		this.account = account;
		this.passwd = passwd;
		this.email = email;
		this.realName = realName;
		this.idnum = idnum;
		this.userState=userState;
	}

	/** full constructor */
	public UserInfo(Integer userInfoId, String account, String passwd,
			String email, String realName, String idnum,String userState ,Set examInfos,
			Set certificateInfos, Set examineeInfos) {
		this.userInfoId = userInfoId;
		this.account = account;
		this.passwd = passwd;
		this.email = email;
		this.realName = realName;
		this.idnum = idnum;
		this.userState=userState;
		this.examInfos = examInfos;
		this.certificateInfos = certificateInfos;
		this.examineeInfos = examineeInfos;
	}

	// Property accessors

	public Integer getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getIdnum() {
		return this.idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public Set getExamInfos() {
		return this.examInfos;
	}

	public void setExamInfos(Set examInfos) {
		this.examInfos = examInfos;
	}

	public Set getCertificateInfos() {
		return this.certificateInfos;
	}

	public void setCertificateInfos(Set certificateInfos) {
		this.certificateInfos = certificateInfos;
	}

	public Set getExamineeInfos() {
		return this.examineeInfos;
	}

	public void setExamineeInfos(Set examineeInfos) {
		this.examineeInfos = examineeInfos;
	}

}