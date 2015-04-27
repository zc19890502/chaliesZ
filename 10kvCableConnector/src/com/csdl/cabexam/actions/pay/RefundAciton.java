package com.csdl.cabexam.actions.pay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.bean.GoRefBean;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.sign.Sign;
import com.csdl.cabexam.util.GetPath;
import com.csdl.cabexam.util.HttpSend;
import com.csdl.cabexam.util.ProductionOrder;
import com.csdl.cabexam.util.ResponsePattern;
import com.opensymphony.xwork2.ActionSupport;

public class RefundAciton extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionUrl;
	private GoRefBean bean;
	private int userInfoId;
	
	OrderInfoDao orderDao = new OrderInfoDao();
	/**
	 * @return
	 */
	public String execute() {
		System.out.println("userInfoId: "+userInfoId);
		String reStr = "success";
		HttpServletRequest request = ServletActionContext.getRequest();
//		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		OrderInfo order = ProductionOrder.productionOrder(userInfoId);
		GoRefBean goRef = new GoRefBean();
		String merId = GetPath.getMerId();
		actionUrl = GetPath.getEduPayPgUrl();
		
		Date current = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String MerRefId = df.format(current)+"01";
		goRef.setMerId(merId);
		goRef.setBusiId("");
		goRef.setOrdId(order.getOrdId());
		goRef.setMerRefId(MerRefId);
		goRef.setRefAmt(order.getPrice());
		goRef.setVersion(GetPath.getVersion());
		goRef.setBgRetUrl(GetPath.getEduRefBgUrl());
		goRef.setPageRetUrl(GetPath.getEduRefBgUrl());
		goRef.setParam1("");
		goRef.setParam2("");
		goRef.setParam3("");
		goRef.setParam4("");
		goRef.setParam5("");
		goRef.setParam6("");
		goRef.setParam7("");
		goRef.setParam8("");
		goRef.setParam9("");
		goRef.setParam10("");
		goRef.setRefDesc("refunddescing");
		System.out.println("GetPath.getShareType(): "+GetPath.getShareType());
		System.out.println("GetPath.getShareData(): "+GetPath.getShareData());
		goRef.setShareType(GetPath.getShareType());
		goRef.setShareData(GetPath.getShareData()+"^"+order.getPrice());
		goRef.setPriv1("");
		
		StringBuffer sb = new StringBuffer();
		sb.append(goRef.getMerId());
		sb.append(goRef.getBusiId());
		sb.append(goRef.getOrdId());
		sb.append(goRef.getMerRefId());
		sb.append(goRef.getRefAmt());
		sb.append(goRef.getVersion());
		sb.append(goRef.getBgRetUrl());
		sb.append(goRef.getPageRetUrl());
		sb.append(goRef.getParam1());
		sb.append(goRef.getParam2());
		sb.append(goRef.getParam3());
		sb.append(goRef.getParam4());
		sb.append(goRef.getParam5());
		sb.append(goRef.getParam6());
		sb.append(goRef.getParam7());
		sb.append(goRef.getParam8());
		sb.append(goRef.getParam9());
		sb.append(goRef.getParam10());
		sb.append(goRef.getShareType());
		sb.append(goRef.getShareData());
		sb.append(goRef.getPriv1());
		// 签名
		Sign s = new Sign();
		String chkValue = null;
		try {
			chkValue = s.sign(merId, sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (chkValue == null || "".equals(chkValue) || chkValue.length() != 256) {
			request.setAttribute("message", "对不起。退款时 验签失败！");
			reStr = "error";
		}
		goRef.setChkValue(chkValue);
		
		// 将要查询的数据 发送给 考试平台
		String[] array1 = new String[] { "MerId", goRef.getMerId() };
		String[] array2 = new String[] { "BusiId", goRef.getBusiId() };
		String[] array3 = new String[] { "OrdId", goRef.getOrdId() };
		String[] array4 = new String[] { "MerRefId", goRef.getMerRefId() };
		String[] array5 = new String[] { "RefAmt", goRef.getRefAmt() };
		String[] array6 = new String[] { "Version", goRef.getVersion() };
		String[] array7 = new String[] { "BgRetUrl", goRef.getBgRetUrl() };
		String[] array8 = new String[] { "PageRetUrl", goRef.getPageRetUrl() };

		String[] array9 = new String[] { "Param1", goRef.getParam1() };
		String[] array10 = new String[] { "Param2", goRef.getParam2() };
		String[] array11 = new String[] { "Param3", goRef.getParam3() };
		String[] array12 = new String[] { "Param4", goRef.getParam4() };
		String[] array13 = new String[] { "Param5", goRef.getParam5() };
		String[] array14 = new String[] { "Param6", goRef.getParam6() };
		String[] array15 = new String[] { "Param7", goRef.getParam7() };
		String[] array16 = new String[] { "Param8", goRef.getParam8() };
		String[] array17 = new String[] { "Param9", goRef.getParam9() };
		String[] array18 = new String[] { "Param10", goRef.getParam10() };

		String[] array19 = new String[] { "RefDesc", goRef.getRefDesc() };
		String[] array20 = new String[] { "ShareType", goRef.getShareType() };
		String[] array21 = new String[] { "ShareData", goRef.getShareData() };
		String[] array22 = new String[] { "Priv1", goRef.getPriv1() };

		String[] array23 = new String[] { "ChkValue", chkValue };

		List list = new ArrayList();
		list.add(array1);
		list.add(array2);
		list.add(array3);
		list.add(array4);
		list.add(array5);
		list.add(array6);
		list.add(array7);
		list.add(array8);
		list.add(array9);
		list.add(array10);
		list.add(array11);
		list.add(array12);
		list.add(array13);
		list.add(array14);
		list.add(array15);
		list.add(array16);
		list.add(array17);
		list.add(array18);
		list.add(array19);
		list.add(array20);
		list.add(array21);
		list.add(array22);
		list.add(array23);

		// 得到商户后台查询的url
		String url = GetPath.getEduRefBgUrl();

		String resStr = HttpSend.send(url, list);

		Map<String, String> map = ResponsePattern.decodeForMap(resStr);
		
		OrderInfoDao oiDao = new OrderInfoDao();
		order.setState("4");
		oiDao.refOrder(order);
		
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		ExamineeInfo examinee = examineeDao.getExamineeInfoByUserInfo(userInfoId);
		examinee.setCheckState("12");
		examineeDao.updateExaminee(examinee);
		
		// 得到信息转发到页面
		request.getSession().setAttribute("map", map);
//		request.setAttribute("goRef", goRef);
//		request.getSession().setAttribute("goRef", goRef);
		return reStr;
	}
	
	
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public GoRefBean getBean() {
		return bean;
	}
	public void setBean(GoRefBean bean) {
		this.bean = bean;
	}
	public int getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}
	
	
}