package com.csdl.cabexam.actions.pay;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.bean.GoPayBean;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.sign.Sign;
import com.csdl.cabexam.util.GetPath;
import com.csdl.cabexam.util.ProductionOrder;
import com.opensymphony.xwork2.ActionSupport;

public class GoPayAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GoPayBean bean;
	private String actionUrl;
	OrderInfoDao orderDao = new OrderInfoDao();
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		OrderInfo order = ProductionOrder.productionOrder(userInfo.getUserInfoId());
		System.out.println("OrdId: "+order.getOrdId());
		if (order.getOrderInfoId()==null) {
			orderDao.addOrderInfo(order);
		}
		String gateId = request.getRemoteAddr() == null ? "" : request.getRemoteAddr();
		String ordDesc = "";
		String customIp = "";
		bean = productionOrder(order.getOrdId(), order.getPrice(), gateId, ordDesc, customIp);
		return "success";
	}
	
	public GoPayBean productionOrder(String ordId,String price,String gateId,String ordDesc,String customIp){
		GoPayBean goPayBean = new GoPayBean();
		String merId = GetPath.getMerId();
		actionUrl = GetPath.getEduPayPgUrl();
		goPayBean = new GoPayBean();
		goPayBean.setMerId(merId);
		goPayBean.setBusiId("");
		goPayBean.setOrdId(ordId);
		goPayBean.setOrdAmt(price);
		goPayBean.setCuryId(GetPath.getCuryId());
		goPayBean.setVersion(GetPath.getVersion());
		goPayBean.setBgRetUrl(GetPath.getReturnMerBgUrl());
		goPayBean.setPageRetUrl(GetPath.getReturnMerPgUrl());
		goPayBean.setGateId("0001");//gateId暂时不放入
		goPayBean.setParam1("");
		goPayBean.setParam2("");
		goPayBean.setParam3("");
		goPayBean.setParam4("");
		goPayBean.setParam5("");
		goPayBean.setParam6("");
		goPayBean.setParam7("");
		goPayBean.setParam8("");
		goPayBean.setParam9("");
		goPayBean.setParam10("");
		goPayBean.setOrdDesc(ordDesc);
		goPayBean.setShareType(GetPath.getShareType());
		goPayBean.setShareData(GetPath.getShareData());
		goPayBean.setPriv1("");
		goPayBean.setCustomIp("");//暂时不放入customIp
		StringBuffer sb = new StringBuffer();
		sb.append(goPayBean.getMerId());
		sb.append(goPayBean.getBusiId());
		sb.append(goPayBean.getOrdId());
		sb.append(goPayBean.getOrdAmt());
		sb.append(goPayBean.getCuryId());
		sb.append(goPayBean.getVersion());
		sb.append(goPayBean.getBgRetUrl());
		sb.append(goPayBean.getPageRetUrl());
		sb.append(goPayBean.getGateId());
		sb.append(goPayBean.getParam1());
		sb.append(goPayBean.getParam2());
		sb.append(goPayBean.getParam3());
		sb.append(goPayBean.getParam4());
		sb.append(goPayBean.getParam5());
		sb.append(goPayBean.getParam6());
		sb.append(goPayBean.getParam7());
		sb.append(goPayBean.getParam8());
		sb.append(goPayBean.getParam9());
		sb.append(goPayBean.getParam10());
		sb.append(goPayBean.getShareType());
		sb.append(goPayBean.getShareData()+"^"+goPayBean.getOrdAmt());
		sb.append(goPayBean.getPriv1());
		sb.append(customIp);
		// 签名
		Sign s = new Sign();
		String chkValue = null;
		try {
			chkValue = s.sign(merId, sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		goPayBean.setChkValue(chkValue);
		return goPayBean;
	}

	public GoPayBean getBean() {
		return bean;
	}

	public void setBean(GoPayBean bean) {
		this.bean = bean;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	
}
