package com.csdl.cabexam.actions.manager.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.manager.service.ManagerService;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.dao.ManagerInfoDao;
import com.csdl.cabexam.dao.imp.ManagerInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport {
	private ManagerInfo managerInfo;
	private String randStr;

	
	
	public String managerLogin() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerService managerService = new ManagerService();
		ManagerInfoDao md = new ManagerInfoDaoImp();
		if(null!=randStr&&!"".equals(randStr)){
			String randImageStr = (String) request.getSession().getAttribute("randstr");
			if(randStr.equalsIgnoreCase(randImageStr)){
				int i = managerService.checkManager(managerInfo);
				if(i==2){
					//登录成功后将userInfo信息放入session内
					ManagerInfo managerSession = md.getManagerInfoByName(managerInfo.getManagerAccount());
					request.getSession().setAttribute("managerSession", managerSession);
					return SUCCESS;
				}else if(i==0) {
					//该管理员不存在，将userInfo信息和错误信息一起放入request
					request.setAttribute("err", 0);
					request.setAttribute("managererr", managerInfo);
					return "err01";
				}else{
					//登录密码错误
					request.setAttribute("err", 1);
					request.setAttribute("managererr", managerInfo);
					return "err02";
				}
			}else{
				request.setAttribute("err", 2);
				request.setAttribute("managererr", managerInfo);
				return "err03";
			}
		}else{
			request.setAttribute("err", 2);
			request.setAttribute("managererr", managerInfo);
			return "err03";
		}
	}
	
	
	public String queryAllManager(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo manageSession = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=manageSession&&!"".equals(manageSession)){
			ManagerInfoDao md = new ManagerInfoDaoImp();
			List<ManagerInfo> list = md.getAllManagerInfo();
	        request.setAttribute("managerList", list);  
	        return SUCCESS;  
		}else{
			return ERROR;
		}
	}

	public String delManager(){
		//System.out.println(managerInfo.getManagerAccount());
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo manageSession = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=manageSession&&!"".equals(manageSession)){
			ManagerService ms = new ManagerService();
			String s_managerId = request.getParameter("managerId");
			if(null!=s_managerId&&!"".equals(s_managerId.trim())){
				int managerId = Integer.parseInt(s_managerId);
				if(ms.deleteManager(managerId)){
					return SUCCESS; 
				}else{
					//删除失败
					return "err01";
				}
			}else{
				return ERROR;
			}
		}else{
			return ERROR;
		}
		
	}
	
	
	public String addManager(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo manageSession = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=manageSession&&!"".equals(manageSession)){
			ManagerService ms = new ManagerService();
			int i = ms.addManagerInfo(managerInfo);
			if(i==1){
				return SUCCESS; 
			}else{
				request.setAttribute("err", 1);
				request.setAttribute("managererr",managerInfo);
				return "err01";
			}
		}else{
			return ERROR;
		}
		
   }
	
	public String updateManager(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo manageSession = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=manageSession&&!"".equals(manageSession)){
			ManagerService ms = new ManagerService();
			int i = ms.updateManagerInfo(managerInfo);
			return SUCCESS; 
		}else{
			return ERROR;
		}
		
   }
		
	public String updatePsw(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("password");
		int managerId = Integer.parseInt(request.getParameter("managerId"));
		if( newPassword!=null &&!"".equals(newPassword)){
			ManagerService examinService = new ManagerService();
			examinService.updateManagerPassword(managerId, newPassword);
			return SUCCESS;
		}else{
			//新密码为空
			return ERROR;
		}
		
	}
	
	
	public String queryManagerById(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo manageSession = (ManagerInfo) request.getSession().getAttribute("managerSession");
		if(null!=manageSession&&!"".equals(manageSession)){
			int managerId = Integer.parseInt(request.getParameter("managerId"));
			ManagerInfoDao managerDao = new ManagerInfoDaoImp();
			ManagerInfo managerInfo = managerDao.getManagerInfoById(managerId);
			request.setAttribute("managerInfo", managerInfo);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}

	public String getRandStr() {
		return randStr;
	}

	public void setRandStr(String randStr) {
		this.randStr = randStr;
	}
	public ManagerInfo getManagerInfo() {
		return managerInfo;
	}

	public void setManagerInfo(ManagerInfo managerInfo) {
		this.managerInfo = managerInfo;
	}
}
