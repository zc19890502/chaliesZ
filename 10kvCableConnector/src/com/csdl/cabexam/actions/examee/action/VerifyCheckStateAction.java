package com.csdl.cabexam.actions.examee.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.examee.service.ExamineeService;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class VerifyCheckStateAction extends ActionSupport{
	
	public String passCheck(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=managerInfo&&!"".equals(managerInfo)){
			String s_examineeId = request.getParameter("examineeId");
			String finalVerfyState = request.getParameter("finalVerfyState");
			String checkAdvice = request.getParameter("checkAdvice");
			if(null!=s_examineeId&&!"".equals(s_examineeId)){
				int examineeId = Integer.parseInt(s_examineeId);
				ExamineeService examinService = new ExamineeService();
				String checkResult = "";
				if(null!=finalVerfyState&&!"".equals(finalVerfyState)){
					checkResult = examinService.updateCheckState(examineeId, "1",checkAdvice);
				}else{
					checkResult = examinService.updateCheckState(examineeId, "8",checkAdvice);
				}
				if("ok".equals(checkResult)){
					return SUCCESS;
				}else if("cannotChecked".equals(checkResult)){
					request.setAttribute("errorState", "cannotCheck");
					return "cannotCheck";
				}else{
					return ERROR;
				}
			}else{
				return ERROR;
			}
		}else{
			return "mananologin";
		}
	}
	
	
	
	public String noPassCheck(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		String finalVerfyState = request.getParameter("finalVerfyState");
		String checkAdvice = request.getParameter("checkAdvice");
		if(null!=managerInfo&&!"".equals(managerInfo)){
			String s_examineeId = request.getParameter("examineeId");
			if(null!=s_examineeId&&!"".equals(s_examineeId)){
				int examineeId = Integer.parseInt(s_examineeId);
				ExamineeService examinService = new ExamineeService();
				String checkResult = "";
				if(null!=finalVerfyState&&!"".equals(finalVerfyState)){
					checkResult = examinService.updateCheckState(examineeId, "2",checkAdvice);
				}else{
					checkResult = examinService.updateCheckState(examineeId, "7",checkAdvice);
				}
				if("ok".equals(checkResult)){
					return SUCCESS;
				}else if("cannotChecked".equals(checkResult)){
					request.setAttribute("errorState", "cannotnoChecked");
					return "cannotnoChecked";
				}else{
					return ERROR;
				}
			}else{
				return ERROR;
			}
		}else{
			return "mananologin";
		}
	}
	
}

