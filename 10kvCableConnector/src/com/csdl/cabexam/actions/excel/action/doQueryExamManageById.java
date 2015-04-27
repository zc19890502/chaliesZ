package com.csdl.cabexam.actions.excel.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.GenerateXls;
import com.opensymphony.xwork2.ActionSupport;

public class doQueryExamManageById extends ActionSupport {
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		    HttpServletRequest request = ServletActionContext.getRequest();
		    String examid_s = request.getParameter("examInfoId");
		    Integer examid = null;
		    if(examid_s!=null&&!examid_s.equals("")){
		    	examid=Integer.parseInt(examid_s);
		    }		    
		    try {
		    	ExamInfo ex = new ExamInfoDaoImp().getExamInfoByExamInfoId(examid);
		        request.setAttribute("examManage", ex);
		    	return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				return ERROR;
			}

		}

	}

	

