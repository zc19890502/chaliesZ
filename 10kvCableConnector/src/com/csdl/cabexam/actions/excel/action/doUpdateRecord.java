package com.csdl.cabexam.actions.excel.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.exam.service.ScoreService;
import com.csdl.cabexam.actions.examee.service.ExamineeService;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.RecordOfYear;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.dao.imp.RecordOfYearDaoImp;
import com.csdl.cabexam.util.ExcelRead;
import com.csdl.cabexam.util.GenerateXls;
import com.opensymphony.xwork2.ActionSupport;

public class doUpdateRecord extends ActionSupport {
	private File Xlsfile;
	private String XlsfileContentType;
	private String XlsfileFileName;;
	
	  
	public String getXlsfileContentType() {
		return XlsfileContentType;
	}


	public void setXlsfileContentType(String xlsfileContentType) {
		XlsfileContentType = xlsfileContentType;
	}


	public String getXlsfileFileName() {
		return XlsfileFileName;
	}


	public void setXlsfileFileName(String xlsfileFileName) {
		XlsfileFileName = xlsfileFileName;
	}


	public File getXlsfile() {
		return Xlsfile;
	}


	public void setXlsfile(File xlsfile) {
		Xlsfile = xlsfile;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		HttpServletRequest request = ServletActionContext.getRequest();
		ExamineeService examineeService = new ExamineeService();
		if(null!=Xlsfile){
			String url = ServletActionContext.getServletContext().getRealPath("/xls/up/");
			File file = new File(url);
			if(!file.exists()){
				file.mkdirs();
			}
			
			String rootpath=url+"\\record.xls";
			//��������io����������ļ�
			FileOutputStream fos = new FileOutputStream(rootpath);
			FileInputStream fis = new FileInputStream(Xlsfile);
			InputStream is = new FileInputStream(rootpath);
			
			byte[] buffer = new byte[1024];
			int len=0;
			
			try {
					while((len = fis.read(buffer))>0){
						fos.write(buffer,0,len);
					}
					
					ExcelRead er = new ExcelRead();
					File oldfile = new File( rootpath);
					//������֤
					List<String> errList = er.validateRecord(is);
					
					if(!errList.isEmpty()){
						request.setAttribute("errList", errList);
						return "tips";
					}else{
						
						List<RecordOfYear> list = er.readExcelRecord(is);
						CommonDao cd = new CommonDaoImp();
						for(RecordOfYear e :list){
							List<RecordOfYear> e_list = new RecordOfYearDaoImp().getRecordByIdYear(e.getIdNumber(), e.getYyyyDate());
							RecordOfYear e1;
							if(e_list.size()==0){
								e1 = new RecordOfYear();
								int recordId = e1.getRecordID();
								e1=e;
								e1.setRecordID(recordId);
								cd.addObject(e1);
							}else if(e_list.size()==1){
								e1 = e_list.get(0);
								int recordId = e1.getRecordID();
								e1 = e;
								e1.setRecordID(recordId);
								cd.updateObject(e1);
							}
						}	
		                return SUCCESS;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ERROR;
				} finally{
					fos.close();
					fis.close();
					is.close();
				}
		}else{
				     String emptyerror="empty";
				     return emptyerror;
			}
			
	}
}
	
