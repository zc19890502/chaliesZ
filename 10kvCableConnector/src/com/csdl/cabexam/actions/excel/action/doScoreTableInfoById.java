package com.csdl.cabexam.actions.excel.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.GenerateXls;
import com.opensymphony.xwork2.ActionSupport;

public class doScoreTableInfoById extends ActionSupport {
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		    HttpServletRequest request = ServletActionContext.getRequest();
		    String userId_s = request.getParameter("userInfoId");
		    String examid_s = request.getParameter("examInfoId");
		    Integer userInfoId = null;
		    Integer examid = null;
		    if(examid_s!=null&&!examid_s.equals("")&&null!=userId_s&&!"".equals(userId_s)){
		    	examid=Integer.parseInt(examid_s);
		    	userInfoId=Integer.parseInt(userId_s);
		    }
		    MutiTableDao mt=new MutiTableDaoImp();
		    try {
		    	ScoreExcel se = mt.getScoreXlsINfoByexamInfoId(userInfoId, examid);
		        request.setAttribute("socreinfo", se);
		    	return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			}

		}

	}

	

