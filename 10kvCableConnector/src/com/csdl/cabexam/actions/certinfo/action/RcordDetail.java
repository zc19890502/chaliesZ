package com.csdl.cabexam.actions.certinfo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.RecordOfYear;
import com.csdl.cabexam.dao.RecordOfYearDao;
import com.csdl.cabexam.dao.imp.RecordOfYearDaoImp;

public class RcordDetail {
	String recordId;
	
	public String record(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(null!=recordId&&!"".equals(recordId)){
			int id = Integer.parseInt(recordId);
			RecordOfYearDao recordOfYear = new RecordOfYearDaoImp();
			RecordOfYear record = recordOfYear.getRecordById(id);
			request.setAttribute("record", record);
			return "success";
		}else{
			return "failed";
		}
	}
	
	
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
}
