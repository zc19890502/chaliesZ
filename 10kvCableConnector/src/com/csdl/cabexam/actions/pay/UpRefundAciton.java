package com.csdl.cabexam.actions.pay;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class UpRefundAciton extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String refExplain;
	/**
	 * @return
	 */
	public String execute() {
		String reStr = "success";
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		ExtraInfoDao extraDao = new ExtraInfoDaoImp();
		String bath = extraDao.getExtraInfo().getExamBatch();
		OrderInfoDao oiDao = new OrderInfoDao();
		OrderInfo order = oiDao.queryByUserIdAndBath(userInfo.getUserInfoId(), bath);
		order.setState("3");
		order.setRefExplain(refExplain);
		oiDao.refOrder(order);
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		ExamineeInfo examinee = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
		examinee.setCheckState("11");
		examineeDao.updateExaminee(examinee);
		return reStr;
	}
	public String getRefExplain() {
		return refExplain;
	}

	public void setRefExplain(String refExplain) {
		this.refExplain = refExplain;
	}

}