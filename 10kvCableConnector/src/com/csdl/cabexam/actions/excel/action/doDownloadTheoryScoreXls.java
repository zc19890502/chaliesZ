package com.csdl.cabexam.actions.excel.action;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.util.GenerateXls;

public class doDownloadTheoryScoreXls {
    private String fileName;
    
    public void setFileName(String fileName) {
            this.fileName = fileName;
    }
    public InputStream getInputStream() {
    	
         return ServletActionContext.getServletContext().getResourceAsStream("/xls/down/theory.xls");
    }
   
    public String getFileName() {
    	String [] str= fileName.split("/");
    	return str[str.length-1];
	}
    
    
    
	public String generateXls(){
		HttpServletRequest request = ServletActionContext.getRequest();
        
		String url = ServletActionContext.getServletContext().getRealPath("/xls/down/");
		File file = new File(url);
		if(!file.exists()){
			file.mkdirs();
		}
		GenerateXls gxl=new GenerateXls();
		try {
			gxl.getTheoryScoreXlsTable(url+"\\theory.xls");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
    }
}
