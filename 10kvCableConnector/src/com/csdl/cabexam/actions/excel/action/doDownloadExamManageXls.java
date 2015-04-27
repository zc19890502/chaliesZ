package com.csdl.cabexam.actions.excel.action;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.util.GenerateXls;
import com.opensymphony.xwork2.ActionSupport;

public class doDownloadExamManageXls extends ActionSupport {
    private String fileName;
    
    public void setFileName(String fileName) {
            this.fileName = fileName;
    }
    public InputStream getInputStream() {
    	
         return ServletActionContext.getServletContext().getResourceAsStream("/xls/down/exam.xls");
    }
   
    public String getFileName() {
    	String [] str= fileName.split("/");
    	return str[str.length-1];
	}
    
    
    
	public String generateXls(){
		HttpServletRequest request = ServletActionContext.getRequest();
        
		String url = ServletActionContext.getServletContext().getRealPath("/xls/down/");
		System.out.println(url);
		File file = new File(url);
		if(!file.exists()){
			file.mkdirs();
		}
		
		
		GenerateXls gxl=new GenerateXls();
		try {
			gxl.getExamManageTable(url+"\\exam.xls");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

    }
}
