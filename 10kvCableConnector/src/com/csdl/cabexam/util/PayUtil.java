package com.csdl.cabexam.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;

public class PayUtil {
	//��ö������
	public static String getOrderNumber(int userInfoId,String type) {
		String orderNumber = "";
		String level = getLevel(userInfoId);
		if (level.length()<2) {
			level = "0"+level;
		}
		if (type.length()<2) {
			type = "0"+type;
		}
		orderNumber = getStrDate()+getBatch()+type+getMaxId(type);
		return orderNumber;
	}

	//�����
	public static String getStrYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(new Date());
	}
	
	//���ʱ��
	public static String getStrDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String date = sdf.format(new Date());
		return date;
	}
	
	//�������
	public static String getBatch() {
		ExtraInfoDao eDao = new ExtraInfoDaoImp();
		String batch = eDao.getExtraInfo().getExamBatch();
		batch = batch.substring(batch.length()-1);
		return batch;
	}
	
	//��ñ������
	public static String getLevel(int userInfoId){
		String level = "";
		ExamineeInfoDao eiDao =new ExamineeInfoDaoImp();
		level = eiDao.getExamineeInfoByUserInfo(userInfoId).getSkillLevel();
		return level;
	}
	
	//�����ˮ��
	public static String getMaxId(String type){
		OrderInfoDao oiDao =new OrderInfoDao();
		String maxId = "";
		type= type.substring(0,type.length()-1);
		System.out.println("type: "+type);
		System.out.println("MaxOrderId: "+oiDao.getMaxOrder(type));
		if (oiDao.getMaxOrder(type)!=null && !"".equals(oiDao.getMaxOrder(type))) {
			String ordId = oiDao.getMaxOrder(type);
			Long longMaxId = Long.parseLong(ordId);
			longMaxId++;
			maxId = String.valueOf(longMaxId).substring(9, 16);
		}else {
			maxId = "0000001";
		}
		return maxId;
	}
}
