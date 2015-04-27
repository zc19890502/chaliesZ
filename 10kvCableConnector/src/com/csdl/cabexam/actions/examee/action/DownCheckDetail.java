package com.csdl.cabexam.actions.examee.action;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.util.GenerateXls;

public class DownCheckDetail {
	private String fileName;
	    
    public void setFileName(String fileName) {
            this.fileName = fileName;
    }
    public String getFileName() {
    	String [] str= fileName.split("/");
    	return str[str.length-1];
	}
	public InputStream getInputStream() {
         return ServletActionContext.getServletContext().getResourceAsStream("/xls/down/checkFir.xls");
    }
	
	public String downCheckXls(){
		String url = ServletActionContext.getServletContext().getRealPath("/xls/down/");
		File file = new File(url);
		if(!file.exists()){
			file.mkdirs();
		}
		GenerateXls gxl=new GenerateXls();
		try {
			gxl.checkFirDetailXls(url+"\\checkFir.xls");
			//gxl.getScoreXlsTable(url+"\\score.xls");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
    
	}
}
