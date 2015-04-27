package com.csdl.cabexam.actions.user.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;
import com.csdl.cabexam.actions.user.service.SendMailService;

public class SendEmailAction extends ActionSupport{
	private String idnum;
	private String email;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String classPath = SendEmailAction.class.getClassLoader().getResource("/").getPath();
		String propertyPath = classPath+"email.properties";
		propertyPath = propertyPath.replace("\\", "/");
		SendMailService sendMaiS = new SendMailService();
		if(null!=idnum&&!"".equals(idnum)&&null!=email&&!"".equals(email)){
			UserInfoDao userDao = new UserInfoDaoImp();
			UserInfo userInfo = userDao.getUserInfoByidnum(idnum);
			if(null!=userInfo&&!"".equals(userInfo)){
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				String actionPath = basePath+"dogotoResetPasswd.action";
//				System.out.println(basePath+"!!"+propertyPath+"!!!	 "+actionPath+"!!!"+email);
				sendMaiS.sendEmail(propertyPath,actionPath,email);
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
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
