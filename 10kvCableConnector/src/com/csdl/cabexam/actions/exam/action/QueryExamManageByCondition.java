package com.csdl.cabexam.actions.exam.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

public class QueryExamManageByCondition extends ActionSupport {
	private String c_realName;
	private String c_number;
	
	//根据考生姓名、籍贯、工作单位组合查询考生准考证信息
	public String queryExamManageByCondition(){
		HttpServletRequest request=ServletActionContext.getRequest();
		try {
			
			String realName = request.getParameter("realName");
			String number = request.getParameter("number");
			if(null!=realName&&!"".equals(realName)){
				realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
				c_realName=realName;
			}
			if(null!=number&&!"".equals(number)){
				number = new String(number.getBytes("iso-8859-1"),"utf-8");
				c_number=number;
			}
			
			ExamInfoDao ei= new ExamInfoDaoImp();
			List<ExamInfo> list = ei.getExamManageByCondition(c_realName, c_number);
			request.setAttribute("examManageList", list);
			request.setAttribute("realName", c_realName);
			request.setAttribute("number", c_number);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
    }

	public String getC_realName() {
		return c_realName;
	}

	public void setC_realName(String c_realName) {
		this.c_realName = c_realName;
	}

	public String getC_number() {
		return c_number;
	}

	public void setC_number(String c_number) {
		this.c_number = c_number;
	}
}
