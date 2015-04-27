package com.csdl.cabexam.actions.record;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.RecordOfYear;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.RecordOfYearDao;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.dao.imp.RecordOfYearDaoImp;
import com.csdl.cabexam.util.GenerateXls;
import com.opensymphony.xwork2.ActionSupport;

public class doRecord extends ActionSupport {
	private String idNumber;   
	private String yyyyDate;    //年份

	/**
	 * 遍历所有的年度评价
	 */
	public String  queryAllRecord(){
		HttpServletRequest request=ServletActionContext.getRequest();
		RecordOfYearDao recordOfYear = new RecordOfYearDaoImp();
		List<RecordOfYear> record = recordOfYear.getRecordByIdYear(null, null);
		if(record.isEmpty()){
			return "empty";
		}else{
			request.setAttribute("record", record);
			return "success";
		}
	}
	/**
	 * 根据省份证和年份查询唯一记录
	 */
	public String queryRecordByIdYear(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String idNumber = request.getParameter("idNumber");
		String yyyyDate = request.getParameter("yyyyDate");	
		System.out.println(idNumber+"+++++"+yyyyDate);
		String c_idNumber=null;
		String c_yyyyDate=null;
		try {
			if (null != idNumber && !"".equals(idNumber)) {
				idNumber = new String(idNumber.getBytes("iso-8859-1"), "utf-8");
				c_idNumber = idNumber;
			}
			if (null != yyyyDate && !"".equals(yyyyDate)) {
				yyyyDate = new String(yyyyDate.getBytes("iso-8859-1"), "utf-8");
				c_yyyyDate = yyyyDate;
			}
			RecordOfYearDao recordOfYear = new RecordOfYearDaoImp();
			List<RecordOfYear> record = recordOfYear.getRecordByIdYear(c_idNumber,c_yyyyDate);
			request.setAttribute("record", record);
			request.setAttribute("idNumber",c_idNumber);
			request.setAttribute("yyyyDate",c_yyyyDate);
			return "success";
		} catch (Exception e) {
			return "error";
		}
		
	}

	
}

	

