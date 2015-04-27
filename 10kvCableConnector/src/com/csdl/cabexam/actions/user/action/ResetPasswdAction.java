package com.csdl.cabexam.actions.user.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.csdl.cabexam.util.MD5Encoding;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswdAction extends ActionSupport{
	private String idNum;
	private String newPasswsd;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfoDao userDao = new UserInfoDaoImp();
		CommonDao cd = new CommonDaoImp();
		MD5Encoding md5encode = new MD5Encoding();
		if(null!=idNum&&!"".equals(idNum)&&null!=newPasswsd&&!"".equals(newPasswsd)){
			UserInfo userInfo = userDao.getUserInfoByidnum(idNum);
			if(null!=userInfo&&!"".equals(userInfo)){
				userInfo.setPasswd(md5encode.getMD5ofStr(newPasswsd));
				cd.updateObject(userInfo);
				return SUCCESS;
			}else{
				request.setAttribute("errState", "noThisUser");
				return "noThisUser";
			}
		}else{
			request.setAttribute("errState", "noinput");
			return "noinput";
		}
	}
	
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getNewPasswsd() {
		return newPasswsd;
	}
	public void setNewPasswsd(String newPasswsd) {
		this.newPasswsd = newPasswsd;
	}
}
