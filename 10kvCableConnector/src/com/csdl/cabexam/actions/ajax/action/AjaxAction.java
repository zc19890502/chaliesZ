package com.csdl.cabexam.actions.ajax.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.ajax.service.InputCheck;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class AjaxAction extends ActionSupport {


public void checkit() throws Exception {
	// TODO Auto-generated method stub
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	response.setContentType("text/html;charset=utf-8");
	
	String inputId=request.getParameter("attr1");
	String inputValue=request.getParameter("value1");
	int flag=0;
	PrintWriter out = response.getWriter();
	
    if(inputId.equals("registerStr")){
    	String str=(String) request.getSession().getAttribute("randstr");
    	if(inputValue.equalsIgnoreCase(str)){
    		flag=6;
    	}else{
    		flag=7;
    	}
    }else if(inputId.equals("loginStr")){
    	String str=(String) request.getSession().getAttribute("randstr");
    	if(inputValue.equalsIgnoreCase(str)){
    		flag=8;
    	}else{
    		flag=9;
    	}
    }
    else{
    	InputCheck ic = new InputCheck();
    	flag=ic.checkInput(inputId, inputValue);
    }
	out.print(flag);
	out.flush();  
    out.close();  
}

}
