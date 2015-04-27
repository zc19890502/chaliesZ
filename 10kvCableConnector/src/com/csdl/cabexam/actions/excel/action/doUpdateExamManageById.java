package com.csdl.cabexam.actions.excel.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.ExcelRead;
import com.opensymphony.xwork2.ActionSupport;

public class doUpdateExamManageById extends ActionSupport {
	private ExamInfo examInfo;
	
	public ExamInfo getExamInfo() {
		return examInfo;
	}

	public void setExamInfo(ExamInfo examInfo) {
		this.examInfo = examInfo;
	}

	
	
	@Override
	public String execute() throws Exception {
		//必须有examInfoId才能更新
        try {
        	ExamInfo exam = new ExamInfoDaoImp().getExamInfoByExamInfoId(examInfo.getExamInfoId());
//		    exam.setTheoryExamDate(examInfo.getTheoryExamDate());
//			exam.setTheoryExamRoom(examInfo.getTheoryExamRoom());
//			exam.setTheoryExamPlace(examInfo.getTheoryExamPlace());
			exam.setTheoryExamDate(examInfo.getTheoryExamDate());
			exam.setTheoryExamRoom(examInfo.getTheoryExamRoom());
			exam.setTheoryExamPlace(examInfo.getTheoryExamPlace());          	
        	new CommonDaoImp().updateObject(exam);
        	return SUCCESS;
		} catch (Exception e) {
			//  handle exception
			return ERROR;
		}
	    //有return会覆盖输出流   所有弹出alert
		
	

	}

	
}
