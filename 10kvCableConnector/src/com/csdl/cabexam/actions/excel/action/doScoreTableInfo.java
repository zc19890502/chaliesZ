package com.csdl.cabexam.actions.excel.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.GenerateXls;
import com.opensymphony.xwork2.ActionSupport;

public class doScoreTableInfo extends ActionSupport {
	private String c_realName;   
	private String c_place;
	private String c_company;     
	private String c_theoryScoreFlag;
	
	private String c_scoreBatch;   
	@Override
	//得到所有的考生成绩信息
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		    HttpServletRequest request = ServletActionContext.getRequest();
		    MutiTableDao mt=new MutiTableDaoImp();
		    try {
		    	List<ScoreExcel> list = mt.getTheoryScoreXlsInfo();
		        request.setAttribute("socreInfoList", list);
		    	return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			}

		}
	//得到所有的考生成绩信息
	public String execute1() throws Exception {
		// TODO Auto-generated method stub
		    HttpServletRequest request = ServletActionContext.getRequest();
		    MutiTableDao mt=new MutiTableDaoImp();
		    try {
		    	List<ScoreExcel> list = mt.getAllScoreXlsInfo();
		        request.setAttribute("socreInfoList", list);
		    	return SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ERROR;
			}

		}
	//得到历年考生成绩信息
	public String execute2() throws Exception {
		// TODO Auto-generated method stub
		    HttpServletRequest request = ServletActionContext.getRequest();
		    MutiTableDao mt=new MutiTableDaoImp();
		    try {
		    	List<ScoreExcel> list = mt.getScoreXlsInfo();
		        request.setAttribute("socreInfoList", list);
		    	return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}

		}
	//根据考生姓名、籍贯、工作单位组合查询考生成绩信息
			public String queryTheoryScoreByCondition(){
				HttpServletRequest request=ServletActionContext.getRequest();
				try {
					
					String realName = request.getParameter("realName");
					String place = request.getParameter("place");
					String company = request.getParameter("company");
					String theoryScoreFlag = request.getParameter("theoryScoreFlag");
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
					if(null!=theoryScoreFlag&&!"".equals(theoryScoreFlag)){
						theoryScoreFlag = new String(theoryScoreFlag.getBytes("iso-8859-1"),"utf-8");
						c_theoryScoreFlag=theoryScoreFlag;
					}
					MutiTableDao mt= new MutiTableDaoImp();
					System.out.println(c_realName+c_place+c_company+c_theoryScoreFlag+"~~~~~");
					List<ScoreExcel> list =  mt.getTheoryScoreInfoByCondition(c_realName, c_place, c_company,c_theoryScoreFlag);
					request.setAttribute("socreInfoList", list);
					request.setAttribute("realName", c_realName);
					request.setAttribute("place", c_place);
					request.setAttribute("company", c_company);
					request.setAttribute("theoryScoreFlag", c_theoryScoreFlag);
					return SUCCESS;
				} catch (Exception e) {
					return ERROR;
				}
				
				
			}

			//根据考生姓名、籍贯、工作单位组合查询考生成绩信息
			public String queryAllScoreByCondition(){
				HttpServletRequest request=ServletActionContext.getRequest();
				try {
					
					String realName = request.getParameter("realName");
					String place = request.getParameter("place");
					String company = request.getParameter("company");
					String theoryScoreFlag = request.getParameter("theoryScoreFlag");
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
					MutiTableDao mt= new MutiTableDaoImp();
					System.out.println(c_realName+c_place+c_company+c_theoryScoreFlag);
					List<ScoreExcel> list =  mt.getAllScoreInfoByCondition(c_realName, c_place, c_company);
					request.setAttribute("socreInfoList", list);
					request.setAttribute("realName", c_realName);
					request.setAttribute("place", c_place);
					request.setAttribute("company", c_company);
					request.setAttribute("theoryScoreFlag", c_theoryScoreFlag);
					return SUCCESS;
				} catch (Exception e) {
					return ERROR;
				}
				
				
			}
			

			//根据历年考生姓名、籍贯、工作单位组合查询考生成绩信息
			public String queryScoreByCondition(){
				HttpServletRequest request=ServletActionContext.getRequest();
				try {
					System.out.println(c_scoreBatch);
					System.out.println(c_company);
					String realName = request.getParameter("realName");
					String scoreBatch = request.getParameter("scoreBatch");
					String company = request.getParameter("company");
					//String theoryScoreFlag = request.getParameter("theoryScoreFlag");
					if(null!=realName&&!"".equals(realName)){
						realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
						c_realName=realName;
						
					}
					if(null!=scoreBatch&&!"".equals(scoreBatch)){
						scoreBatch = new String(scoreBatch.getBytes("iso-8859-1"),"utf-8");
						c_scoreBatch=scoreBatch;
						
					}
					if(null!=company&&!"".equals(company)){
						company = new String(company.getBytes("iso-8859-1"),"utf-8");
						c_company=company;
						
					}
					MutiTableDao mt= new MutiTableDaoImp();
					List<ScoreExcel> list =  mt.getScoreInfoByCondition(c_realName, c_scoreBatch, c_company);
					request.setAttribute("socreInfoList", list);
					request.setAttribute("realName", c_realName);
					request.setAttribute("scoreBatch", c_scoreBatch);
					request.setAttribute("company", c_company);
					//request.setAttribute("theoryScoreFlag", c_theoryScoreFlag);
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

			public String getC_theoryScoreFlag() {
				return c_theoryScoreFlag;
			}

			public void setC_theoryScoreFlag(String c_theoryScoreFlag) {
				this.c_theoryScoreFlag = c_theoryScoreFlag;
			}
			public String getC_scoreBatch() {
				return c_scoreBatch;
			}
			public void setC_scoreBatch(String c_scoreBatch) {
				this.c_scoreBatch = c_scoreBatch;
			}
	
	

	}

	

