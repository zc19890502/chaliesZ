package com.csdl.cabexam.actions.exam.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.mapping.Map;

import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExamAction extends ActionSupport {
	private ExamInfo examInfo;

	public ExamInfo getExamInfo() {
	return examInfo;
	}

	public void setExamInfo(ExamInfo examInfo) {
	this.examInfo = examInfo;
	}
	
	public String verifyPass(){
		HttpServletRequest request=ServletActionContext.getRequest();
		//如何获取verification.jsp中的userInfoId？？？？
		Integer userInfoId = new Integer(request.getParameter("userInfoId"));
		ExamInfoDao em = new ExamInfoDaoImp();
		em.updateExaminnerState(userInfoId, "1");
		return SUCCESS; 
	}
	
	public String verifyNotPass(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Integer userInfoId = new Integer(request.getParameter("userInfoId"));
		ExamInfoDao em = new ExamInfoDaoImp();
		em.updateExaminnerState(userInfoId, "2");
		return SUCCESS; 
	}
}
