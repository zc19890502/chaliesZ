package com.csdl.cabexam.actions.user.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.util.MD5Encoding;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUserPswAction extends ActionSupport{
	private String oldPasswsd;
	private String newPasswsd;
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userSession = (UserInfo) request.getSession().getAttribute("userSession");
		CommonDao cd = new CommonDaoImp();
		MD5Encoding md5 = new MD5Encoding();
		if(null!=userSession&&!"".equals(userSession)){
			System.out.println("~~~~~~~");
			if(null!=oldPasswsd&&!"".equals(oldPasswsd)){
				if(md5.getMD5ofStr(oldPasswsd).equals(userSession.getPasswd())){
					userSession.setPasswd(md5.getMD5ofStr(newPasswsd));
					request.getSession().setAttribute("userSession", userSession);
					cd.updateObject(userSession);
					return SUCCESS;
				}else{
					request.setAttribute("err", "æ…√‹¬Î¥ÌŒÛ");
					return ERROR;
				}
			}else{
				request.setAttribute("err", "«Î ‰»Îæ…√‹¬Î");
				return ERROR;
			}
		}else{
			return "nologin";
		}
	}
	public String getOldPasswsd() {
		return oldPasswsd;
	}
	public void setOldPasswsd(String oldPasswsd) {
		this.oldPasswsd = oldPasswsd;
	}
	public String getNewPasswsd() {
		return newPasswsd;
	}
	public void setNewPasswsd(String newPasswsd) {
		this.newPasswsd = newPasswsd;
	}
}
