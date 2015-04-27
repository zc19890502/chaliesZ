package com.csdl.cabexam.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GotoFeeJsp extends ActionSupport{
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userSession = (UserInfo) request.getSession().getAttribute("userSession");
		if(null!=userSession&&!"".equals(userSession)){
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userSession.getUserInfoId());
			if(null!=examineeInfo&&!"".equals(examineeInfo)){
				String checkState = examineeInfo.getCheckState();
				if(null!=checkState&&!"".equals(checkState)){
					//审核刚通过时可以上传理论缴费
					if(checkState.equals("1")){
						request.setAttribute("examineeInfo", examineeInfo);
						request.setAttribute("type", "theoryFee");
						return SUCCESS;
					}else if(checkState.equals("3")){
						//理论缴费上传成功时可以上传操作缴费
						request.setAttribute("examineeInfo", examineeInfo);
						request.setAttribute("type", "operateFee");
						return SUCCESS;
					}else if(checkState.equals("4")){
						//理论缴费审核失败时，可以重新上传理论缴费
						request.setAttribute("examineeInfo", examineeInfo);
						request.setAttribute("type", "theoryFee");
						return SUCCESS;
					}else if(checkState.equals("6")){
						//操作缴费审核失败时，可以重新上传操作缴费
						request.setAttribute("examineeInfo", examineeInfo);
						request.setAttribute("type", "operateFee");
						return SUCCESS;
					}else{
						request.setAttribute("error1", "cannotUp");
						return SUCCESS;
					}
				}else{
					request.setAttribute("error1", "waitVertify");
					return ERROR;
				}
			}else{
				request.setAttribute("error1", "noExamineeInfo");
				return ERROR;
			}
		}else{
			return "nologin";
		}
	}
}
