package com.csdl.cabexam.actions;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

public class DownloadAction {
    private String fileName;
    
    public void setFileName(String fileName) {
            this.fileName = fileName;
    }
    public InputStream getInputStream() {
            return ServletActionContext.getServletContext().getResourceAsStream("/" + fileName);
    }
   
    public String getFileName() {
    	String [] str= fileName.split("/");
    	return str[str.length-1];
	}
	public String execute(){
            return "success";
    }
}

