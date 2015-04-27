package com.csdl.cabexam.actions.pay;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.util.ProductionOrder;
import com.opensymphony.xwork2.ActionSupport;

public class GoOrderAction extends ActionSupport{

	private OrderInfo order;
	private String typeName;
	
	//�жϿ����������
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		//�ж��û���û��¼��û��½����nologin
		if(null!=userInfo&&!"".equals(userInfo)){
			order = ProductionOrder.productionOrder(userInfo.getUserInfoId());
			if ("0".equals(order.getType())) {
				typeName = "����";
			}
			if ("1".equals(order.getType())) {
				typeName = "����";
			}
			if ("2".equals(order.getType())) {
				typeName = "����";
			}
			if ("3".equals(order.getType())) {
				typeName = "����+����";
			}			
			return "success";
		}else{
			return "nologin";
		}
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}




}
