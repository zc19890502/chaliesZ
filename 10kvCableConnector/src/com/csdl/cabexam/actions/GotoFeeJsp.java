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
					//��˸�ͨ��ʱ�����ϴ����۽ɷ�
					if(checkState.equals("1")){
						request.setAttribute("examineeInfo", examineeInfo);
						request.setAttribute("type", "theoryFee");
						return SUCCESS;
					}else if(checkState.equals("3")){
						//���۽ɷ��ϴ��ɹ�ʱ�����ϴ������ɷ�
						request.setAttribute("examineeInfo", examineeInfo);
						request.setAttribute("type", "operateFee");
						return SUCCESS;
					}else if(checkState.equals("4")){
						//���۽ɷ����ʧ��ʱ�����������ϴ����۽ɷ�
						request.setAttribute("examineeInfo", examineeInfo);
						request.setAttribute("type", "theoryFee");
						return SUCCESS;
					}else if(checkState.equals("6")){
						//�����ɷ����ʧ��ʱ�����������ϴ������ɷ�
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
