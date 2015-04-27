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

import com.csdl.cabexam.actions.examee.service.ExamineeService;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.util.ExcelRead;
import com.csdl.cabexam.util.GenerateXls;
import com.opensymphony.xwork2.ActionSupport;

public class doUpdateExamXls extends ActionSupport {
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
		CommonDao cd = new CommonDaoImp();
		//上传考生成績錄入表
		//如果为空，跳入错误页面
		if(null!=Xlsfile){
			String url = ServletActionContext.getServletContext().getRealPath("/xls/up/");
			File file = new File(url);
			if(!file.exists()){
				file.mkdirs();
			}
			
			String rootpath=url+"\\exam.xls";
			//调用下面io流方法存放文件
			FileOutputStream fos = new FileOutputStream(rootpath);
			FileInputStream fis = new FileInputStream(Xlsfile);
			InputStream is = new FileInputStream(rootpath);
			byte[] buffer = new byte[1024];
			int len=0;
			try {
					while((len = fis.read(buffer))>0){
						fos.write(buffer,0,len);
					}
					
					//上传之后更新
					ExcelRead er = new ExcelRead();
					List<String> errList = er.validateExamXls(is);
					
					if(!errList.isEmpty()){
						request.setAttribute("errList", errList);
						return "tips";
					}else{
					
					//将xls文件中的userInfoId和score读到List中
					List<ExamInfo> list = er.readExcelExam(is);
		       
					for(ExamInfo e :list){
						//通过
						ExamInfo exam =new ExamInfoDaoImp().getExamInfoByExamInfoId(e.getExamInfoId());

						exam.setTheoryExamDate(e.getTheoryExamDate());
						exam.setTheoryExamRoom(e.getTheoryExamRoom());
						exam.setTheoryExamPlace(e.getTheoryExamPlace());
						new CommonDaoImp().updateObject(exam);
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
