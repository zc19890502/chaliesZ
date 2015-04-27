package com.csdl.cabexam.actions.examee.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class CheckFeeAction extends ActionSupport{
    /**
     * advice为审核不通过的原因
     */
	private ExamineeInfo examineeInfo;
	private String ordId;

    //通过理论缴费审查
	public String checkTheoryFeePass(){
		OrderInfoDao orderDao = new OrderInfoDao();
		orderDao.updateOrderInfoState("2", ordId);
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		
		if(null!=managerInfo&&!"".equals(managerInfo)){
			
			ExamineeInfo examinee_db=new ExamineeInfoDaoImp().getExamineeInfoByID(examineeInfo.getExamineeInfoId());
			examinee_db.setAdvice("理论考试缴费成功！");
			examinee_db.setCheckState("3");
			new CommonDaoImp().updateObject(examinee_db);
			return SUCCESS;	
		}else{
			return "mananologin";
		}
	}
	//通过理论缴费失败
	public String checkTheoryFeeFail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		
		if(null!=managerInfo&&!"".equals(managerInfo)){
			
			ExamineeInfo examinee_db=new ExamineeInfoDaoImp().getExamineeInfoByID(examineeInfo.getExamineeInfoId());
			if(null!=examineeInfo.getAdvice()&&!"".equals(examineeInfo.getAdvice())){
				examinee_db.setAdvice(examineeInfo.getAdvice());
			}else{
				examinee_db.setAdvice("理论缴费单审核失败");
			}
			examinee_db.setCheckState("4");
			new CommonDaoImp().updateObject(examinee_db);	
			return SUCCESS;	
		}else{
			return "mananologin";
		}
	}
	
	public String checkOperateFeePass(){
		OrderInfoDao orderDao = new OrderInfoDao();
		orderDao.updateOrderInfoState("2", ordId);
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		
		if(null!=managerInfo&&!"".equals(managerInfo)){			
			ExamineeInfo examinee_db=new ExamineeInfoDaoImp().getExamineeInfoByID(examineeInfo.getExamineeInfoId());		    
			examinee_db.setAdvice("操作考试缴费成功！");
			examinee_db.setCheckState("5");
			new CommonDaoImp().updateObject(examinee_db);	
			return SUCCESS;	
		}else{
			return "mananologin";
		}
	}
	public String checkOperateFeeFail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerInfo managerInfo = (ManagerInfo) request.getSession().getAttribute("managerSession");
		
		if(null!=managerInfo&&!"".equals(managerInfo)){
			
			ExamineeInfo examinee_db=new ExamineeInfoDaoImp().getExamineeInfoByID(examineeInfo.getExamineeInfoId());
			if(null!=examineeInfo.getAdvice()&&!"".equals(examineeInfo.getAdvice())){
				examinee_db.setAdvice(examineeInfo.getAdvice());
			}else{
				examinee_db.setAdvice("操作缴费单审核失败");
			}
			examinee_db.setCheckState("6");
			new CommonDaoImp().updateObject(examinee_db);	
			return SUCCESS;	
		}else{
			return "mananologin";
		}
	}
	
	
	

	public ExamineeInfo getExamineeInfo() {
		return examineeInfo;
	}
	
	public void setExamineeInfo(ExamineeInfo examineeInfo) {
		this.examineeInfo = examineeInfo;
	}
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

}