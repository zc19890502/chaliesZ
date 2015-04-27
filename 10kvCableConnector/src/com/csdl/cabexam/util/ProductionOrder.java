package com.csdl.cabexam.util;

import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ExtraInfo;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;

public class ProductionOrder {
	public static OrderInfo productionOrder(int userInfoId){
		//���巵����Ҫ������
		OrderInfo order = null;
		//��ʵ����ʵ�������������ԡ��������ɼ������ӿ�
		ExtraInfoDao extraDao = new ExtraInfoDaoImp();
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		ExamInfoDao examDao = new ExamInfoDaoImp();	
		OrderInfoDao orderDao = new OrderInfoDao();
		//��ѯ����Ҫ�Ķ��������ԡ��������ɼ�����
		ExtraInfo extra = extraDao.getExtraInfo();
		ExamineeInfo examinee = examineeDao.getExamineeInfoByUserInfo(userInfoId);
		ExamInfo exam = examDao.getExamInfoByUserId(userInfoId);
		//�����û�ID�����μ����ͻ�ö���
		String type = null;
		System.out.println("exam: "+exam);
		if (exam==null) {
			type = "0";
		}else {
			type = examinee.getSkillLevel();
		}
		System.out.println("type1111: "+type);
		String batch = extra.getExamBatch();
		order = orderDao.queryByIdOrderInfoId(userInfoId,type, batch);
		if (order==null) {
			String state = "1";
			String price = "";
			if ("0".equals(type)) {
				price = "200";
			}
			if ("1".equals(type)) {
				price = extra.getColdExamPrice();
			}
			if ("2".equals(type)) {
				price = extra.getHotExamPrice();
			}
			if ("3".equals(type)) {
				price = String.valueOf(
						Integer.parseInt(extra.getColdExamPrice())
						+Integer.parseInt(extra.getHotExamPrice()));
			}
			String ordId = PayUtil.getOrderNumber(userInfoId,type);
			UserInfoDao uDao = new UserInfoDaoImp();
			order = new OrderInfo();
			order.setBatch(batch);
			order.setExamineeInfo(examinee);
			order.setOrdId(ordId);
			order.setPrice(price);
			order.setState(state);
			order.setType(type);
			order.setUserInfo(uDao.getUserInfoById(userInfoId));
		}
		return order;
	}
}
