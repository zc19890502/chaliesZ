package com.csdl.cabexam.actions.pay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.csdl.cabexam.bean.GoQueryBean;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.sign.Sign;
import com.csdl.cabexam.util.GetPath;
import com.csdl.cabexam.util.HttpSend;
import com.csdl.cabexam.util.ResponsePattern;
import com.opensymphony.xwork2.ActionSupport;

public class BGQueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String OrdId;
	/**
	 * @return
	 */
	public String execute() {
		System.out.println("OrdId: "+OrdId);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.getSession().removeAttribute("GoPayBean");
		String MerId = GetPath.getMerId();
		GoQueryBean goQueryBean = new GoQueryBean();		
		goQueryBean.setMerId(MerId);
		goQueryBean.setBusiId("");
		goQueryBean.setOrdId(OrdId);
		goQueryBean.setParam1("");
		goQueryBean.setParam2("");
		goQueryBean.setParam3("");
		goQueryBean.setParam4("");
		goQueryBean.setParam5("");
		goQueryBean.setParam6("");
		goQueryBean.setParam7("");
		goQueryBean.setParam8("");
		goQueryBean.setParam9("");
		goQueryBean.setParam10("");
		goQueryBean.setChkValue("");
		
		StringBuffer sb = new StringBuffer();
		sb.append(goQueryBean.getMerId());
		sb.append(goQueryBean.getBusiId());
		sb.append(goQueryBean.getOrdId());
		sb.append(goQueryBean.getParam1());
		sb.append(goQueryBean.getParam2());
		sb.append(goQueryBean.getParam3());
		sb.append(goQueryBean.getParam4());
		sb.append(goQueryBean.getParam5());
		sb.append(goQueryBean.getParam6());
		sb.append(goQueryBean.getParam7());
		sb.append(goQueryBean.getParam8());
		sb.append(goQueryBean.getParam9());
		sb.append(goQueryBean.getParam10());
		// 签名
		Sign s = new Sign();
		String chkValue = null;
		try {
			chkValue = s.sign(MerId, sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (chkValue == null || "".equals(chkValue) || chkValue.length() != 256) {
			System.out.println("对不起，支付时，签名失败！");
		}
		goQueryBean.setChkValue(chkValue);
		
		String[] array1 = new String[] { "MerId", goQueryBean.getMerId() };
		String[] array2 = new String[] { "BusiId", goQueryBean.getBusiId() };
		String[] array3 = new String[] { "OrdId", goQueryBean.getOrdId() };

		String[] array4 = new String[] { "Param1", goQueryBean.getParam1() };
		String[] array5 = new String[] { "Param2", goQueryBean.getParam2() };
		String[] array6 = new String[] { "Param3", goQueryBean.getParam3() };
		String[] array7 = new String[] { "Param4", goQueryBean.getParam4() };
		String[] array8 = new String[] { "Param5", goQueryBean.getParam5() };
		String[] array9 = new String[] { "Param6", goQueryBean.getParam6() };
		String[] array10 = new String[] { "Param7", goQueryBean.getParam7() };
		String[] array11 = new String[] { "Param8", goQueryBean.getParam8() };
		String[] array12 = new String[] { "Param9", goQueryBean.getParam9() };
		String[] array13 = new String[] { "Param10", goQueryBean.getParam10() };

		String[] array14 = new String[] { "ChkValue", chkValue };

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
		
		String url = GetPath.getEduQueryBgUrl();
		String resStr = HttpSend.send(url, list);

		Map<String, String> map = ResponsePattern.decodeForMap(resStr);

		//支付状态
		String payStat = (String)map.get("PayStat");
		System.out.println("payStat: "+payStat);
		OrderInfoDao orderDao = new OrderInfoDao();
		OrderInfo order = new OrderInfo();
		order = orderDao.queryByOrderId(OrdId);
		int examineeId = order.getExamineeInfo().getExamineeInfoId();
		if ("1001".equals(payStat) || payStat=="1001") {
			String state = "2";
			orderDao.updateOrderInfoState(state,OrdId);
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			ExamineeInfo examinee = order.getExamineeInfo();
			String checkState = "3";
			if (examinee.getCheckState()=="1" || "1".equals(examinee.getCheckState())) {
				checkState = "3";
			}
			if (examinee.getCheckState()=="3" || "3".equals(examinee.getCheckState())) {
				checkState = "5";
			}
			System.out.println(checkState);
			examinee.setCheckState(checkState);
			examineeDao.updateExaminee(examinee);
		}
		System.out.println("examineeId: "+examineeId);
		HttpSession sesion =request.getSession();
		sesion.setAttribute("examineeId", examineeId);
		return "success";	
	}
	
	public String getOrdId() {
		return OrdId;
	}
	public void setOrdId(String ordId) {
		OrdId = ordId;
	}
	
	
}