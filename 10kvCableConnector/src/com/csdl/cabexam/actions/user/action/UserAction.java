package com.csdl.cabexam.actions.user.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.user.service.UserService;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private UserInfo userInfo;
	private String randStr;
	public String getRandStr() {
		return randStr;
	}

	public void setRandStr(String randStr) {
		this.randStr = randStr;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String userLogin() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		UserService userService = new UserService();
		UserInfoDao ud = new UserInfoDaoImp();
		if(null!=randStr&&!"".equals(randStr)){
			String randImageStr = (String) request.getSession().getAttribute("randstr");
			if(randStr.equalsIgnoreCase(randImageStr)){
				int i = userService.checkUser(userInfo);
				if(i==2){
					//登录成功后将userInfo信息放入session内
					UserInfo userSession = ud.getUserInfoByName(userInfo.getAccount());
					request.getSession().setAttribute("userSession", userSession);
					return SUCCESS;
				}else if(i==0) {
					//失败后，将userInfo信息和错误信息一起放入request
					System.out.println("~~~~~~~~~0");
					request.setAttribute("err", 0);
					request.setAttribute("usererr", userInfo);
					return "err01";
				}else{
					System.out.println("~~~~~~~~~1");
					request.setAttribute("err", 1);
					request.setAttribute("usererr", userInfo);
					return "err02";
				}
			}else{
				System.out.println("~~~~~~~~~2");
				request.setAttribute("err", 2);
				request.setAttribute("usererr", userInfo);
				return "err03";
			}
		}else{
			request.setAttribute("err", 2);
			request.setAttribute("usererr", userInfo);
			return "err03";
		}
	}
	
	//注册信息action
	public String userRegister() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		if(null!=randStr&&!"".equals(randStr)){
			String randImageStr = (String) request.getSession().getAttribute("randstr");
			if(randStr.equalsIgnoreCase(randImageStr)){
				UserService userService = new UserService();
				int i = userService.addUserInfo(userInfo);
				if(i==2){
					request.getSession().setAttribute("userSession", userInfo);
					return SUCCESS;
				}else if(i==0){
					request.setAttribute("err", 0);
					request.setAttribute("usererr", userInfo);
					return "err01";
				}else{
					request.setAttribute("err", 1);
					request.setAttribute("usererr", userInfo);
					return "err02";
				}
			}else{
				request.setAttribute("err", 2);
				request.setAttribute("usererr", userInfo);
				return "err03";
			}
		}else{
			request.setAttribute("err", 2);
			request.setAttribute("usererr", userInfo);
			return "err03";
		}
	}

}
