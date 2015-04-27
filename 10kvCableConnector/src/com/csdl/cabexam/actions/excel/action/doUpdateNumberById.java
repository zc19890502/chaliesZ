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
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.ExcelRead;
import com.opensymphony.xwork2.ActionSupport;

public class doUpdateNumberById extends ActionSupport {
	private ScoreExcel se;
	
	public ScoreExcel getSe() {
		return se;
	}

	public void setSe(ScoreExcel se) {
		this.se = se;
	}
    
	
	public void downloadById(){
		
	}
	
	
	
	@Override
	public String execute() throws Exception {
		// Auto-generated method stub
		//HttpServletRequest request = ServletActionContext.getRequest();
        MutiTableDao mt = new MutiTableDaoImp() ;
        try {
        	mt.UpdateNumberByexamInfoId(se.getExamInfoId(),se.getNumber());
        	return SUCCESS;
		} catch (Exception e) {
			//  handle exception
			return ERROR;
		}
	    //有return会覆盖输出流   所有弹出alert
		
	

	}

	
}
