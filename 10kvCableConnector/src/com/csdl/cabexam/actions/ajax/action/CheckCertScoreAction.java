package com.csdl.cabexam.actions.ajax.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.ajax.service.InputCheck;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class CheckCertScoreAction extends ActionSupport {


public void checkit() throws Exception {
	// TODO Auto-generated method stub
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	response.setContentType("text/html;charset=utf-8");
	
	String s_certId=request.getParameter("attr1");
	String s_certScoreInJsp=request.getParameter("value1");
	int certId=1;
	int certScoreInjsp=0;
	if(null!=s_certId&&!"".equals(s_certId)){
		certId=Integer.parseInt(s_certId);
	}
	if(null!=s_certScoreInJsp&&!"".equals(s_certScoreInJsp)){
		certScoreInjsp=Integer.parseInt(s_certScoreInJsp);
	}
	int certScoreNext=0;
	PrintWriter out = response.getWriter();
	
    InputCheck ic = new InputCheck();
    certScoreNext=ic.checkCertScore(certId, certScoreInjsp);
    if(certScoreNext<0){
    	certScoreNext=0;
    }
    //如果扣除页面分，将只剩下多少分
	out.print(certScoreNext);
	out.flush();  
    out.close();  
}

}
