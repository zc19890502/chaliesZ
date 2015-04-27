package com.csdl.cabexam.actions.excel.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.csdl.cabexam.actions.exam.service.ScoreService;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.ExcelRead;
import com.opensymphony.xwork2.ActionSupport;

public class doUpdateAllScoreById extends ActionSupport {
	private ScoreExcel se;
	
	public ScoreExcel getSe() {
		return se;
	}

	public void setSe(ScoreExcel se) {
		this.se = se;
	}

	
	@Override
	public String execute() throws Exception {
		// Auto-generated method stub
	/*	ScoreService ss=null;			
		for(ExamInfo e :list){
			int id=e.getUserInfo().getUserInfoId();
			Float theoryScore=e.getTheoryScore();
			Float operateScore=e.getOperateScore();
			ss= new ScoreService();
			ss.UpdateScoreById(id,theoryScore,operateScore);
		}*/
		//HttpServletRequest request = ServletActionContext.getRequest();
		ScoreService ss = new ScoreService(); ;
        try {
        	//ss.UpdateScoreById(se.getUserInfoId(),se.getExamInfoId(),se.getTheoryScore());    	
        	ss.UpdateAllScoreById(se.getExamInfoId(),se.getColdMidScore(),se.getColdTemScore(),se.getHotMidScore(),se.getHotTemScore());
        	return SUCCESS;
		} catch (Exception e) {
			//  handle exception
			e.printStackTrace();
			return ERROR;
		}
	    //有return会覆盖输出流   所有弹出alert
		
	

	}

	
}
