package com.csdl.cabexam.actions.exam.action;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.mapping.Map;

import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QueryAllExamManage extends ActionSupport {
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		    HttpServletRequest request = ServletActionContext.getRequest();
		    HttpServletResponse response = ServletActionContext.getResponse();			
		    try {
		    	//List<ScoreExcel> list = mt.getAllXlsInfo();
		    	List<ExamInfo> list = new ExamInfoDaoImp().getAllExamInfo();
		        request.setAttribute("examManageList", list);
		    	return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				return ERROR;
			}

		}
}
