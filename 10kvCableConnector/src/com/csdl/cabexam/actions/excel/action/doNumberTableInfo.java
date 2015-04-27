package com.csdl.cabexam.actions.excel.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.GenerateXls;
import com.opensymphony.xwork2.ActionSupport;

public class doNumberTableInfo extends ActionSupport {
	private String c_realName;
	private String c_place;
	private String c_company;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		    HttpServletRequest request = ServletActionContext.getRequest();
		    MutiTableDao mt=new MutiTableDaoImp();
		    try {
		    	List<ScoreExcel> list = mt.getNumberXlsInfo();
		        request.setAttribute("numberInfoList", list);
		    	return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				return ERROR;
			}

		}
	
	
	//根据考生姓名、籍贯、工作单位组合查询考生准考证信息
	public String queryNumberByCondition(){
		HttpServletRequest request=ServletActionContext.getRequest();
		try {
			
			String realName = request.getParameter("realName");
			String place = request.getParameter("place");
			String company = request.getParameter("company");
			if(null!=realName&&!"".equals(realName)){
				realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
				c_realName=realName;
			}
			if(null!=place&&!"".equals(place)){
				place = new String(place.getBytes("iso-8859-1"),"utf-8");
				c_place=place;
			}
			if(null!=company&&!"".equals(company)){
				company = new String(company.getBytes("iso-8859-1"),"utf-8");
				c_company=company;
			}
			
			//System.out.println(realName+"-"+place+"-"+company);
			MutiTableDao mt= new MutiTableDaoImp();
			List<ScoreExcel> list = mt.getNumberInfoByCondition(c_realName, c_place, c_company) ;
			request.setAttribute("numberInfoList", list);
			request.setAttribute("realName", c_realName);
			request.setAttribute("place", c_place);
			request.setAttribute("company", c_company);
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


	public String getC_place() {
		return c_place;
	}


	public void setC_place(String c_place) {
		this.c_place = c_place;
	}


	public String getC_company() {
		return c_company;
	}


	public void setC_company(String c_company) {
		this.c_company = c_company;
	}
	
	
	

	}

	

