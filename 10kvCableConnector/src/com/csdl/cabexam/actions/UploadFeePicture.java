package com.csdl.cabexam.actions;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.examee.service.ExamineeService;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.util.ProductionOrder;
import com.opensymphony.xwork2.ActionSupport;

public class UploadFeePicture extends ActionSupport {
	private File picLoad;
	private String picLoadFileName;

	public String theoryFeeUpload() throws Exception {
		//TODO 添加checkState=3时置为checkState=1
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		if (null != userInfo && !"".equals(userInfo)) {			
			OrderInfo order = ProductionOrder.productionOrder(userInfo.getUserInfoId());
			OrderInfoDao orderDao = new OrderInfoDao();
			if (order.getOrderInfoId()==null) {
				orderDao.addOrderInfo(order);
			}	
			String roots = null;			
			ExamineeInfo examineeInfo = new ExamineeInfoDaoImp().getExamineeInfoByUserInfo(userInfo.getUserInfoId());
            Integer examineeId=examineeInfo.getExamineeInfoId();
            
			// 上传缴费照片
			// 如果为空，跳入错误页面
			if (null != picLoad) {
				roots = ServletActionContext.getServletContext().getRealPath("/uploadList/" + examineeId);
				File file = new File(roots);
				if (!file.exists()) {
					file.mkdirs();
				}
				
				String relatepath = new ExamineeService().uploadFile(picLoadFileName,"theory", examineeId, picLoad, roots);
				examineeInfo.setTheoryFeePath(relatepath);
				
				new CommonDaoImp().updateObject(examineeInfo);
				
			} else {
				request.setAttribute("uploaderr", 1);
				return "err01";
			}

			return "success";

		} else {
			return "nologin";
		}

	}
	public String operateFeeUpload() throws Exception {
		//TODO 添加checkState=5时置为checkState=3
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		if (null != userInfo && !"".equals(userInfo)) {			
			OrderInfo order = ProductionOrder.productionOrder(userInfo.getUserInfoId());
			OrderInfoDao orderDao = new OrderInfoDao();
			if (order.getOrderInfoId()==null) {
				orderDao.addOrderInfo(order);
			}		
			String roots = null;			
			ExamineeInfo examineeInfo = new ExamineeInfoDaoImp().getExamineeInfoByUserInfo(userInfo.getUserInfoId());
			Integer examineeId=examineeInfo.getExamineeInfoId();
			// 上传缴费照片
			// 如果为空，跳入错误页面
			if (null != picLoad) {
				roots = ServletActionContext.getServletContext().getRealPath("/uploadList/" + examineeId);
				File file = new File(roots);
				if (!file.exists()) {
					file.mkdirs();
				}
				
				String relatepath = new ExamineeService().uploadFile(picLoadFileName,"operate", examineeId, picLoad, roots);
				examineeInfo.setOperateFeePath(relatepath);
				
				new CommonDaoImp().updateObject(examineeInfo);
				
			} else {
				request.setAttribute("uploaderr", 1);
				return "err01";
			}
			
			return SUCCESS;
			
		} else {
			return "nologin";
		}
		
	}
	
	
	
	
	

	public File getPicLoad() {
		return picLoad;
	}

	public void setPicLoad(File picLoad) {
		this.picLoad = picLoad;
	}

	public String getPicLoadFileName() {
		return picLoadFileName;
	}

	public void setPicLoadFileName(String picLoadFileName) {
		this.picLoadFileName = picLoadFileName;
	}

}
