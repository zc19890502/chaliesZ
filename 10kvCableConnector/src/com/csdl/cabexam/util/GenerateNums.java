package com.csdl.cabexam.util;

import java.util.Calendar;
import java.util.Date;

import java.util.List;

import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;

public class GenerateNums {

	public String getNums(String type){
		
		String numResult="";
		if(type.equals("certInfoNum")){
			CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
			numResult = this.generaeNum(certInfoDao.getMaxCertNums(), 8);
		}
		if(type.equals("examNum")){
			ExamInfoDao examInfoDao = new ExamInfoDaoImp();
			numResult = this.generaeNum(examInfoDao.getMaxExamNum(), 6);
		}
		return numResult;
	}
	
	
	
	public String generaeNum(String num,int index){
		String s_month="";
		String s_day="";
		String sixNum ="";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if(month<10){
			s_month="0"+month;
		}else{
			s_month=""+month;
		}
		if(day<10){
			s_day="0"+day;
		}else{
			s_day=""+day;
		}
		if(index==8){
			sixNum = year+s_month+s_day;
		}else{
			sixNum = year+s_month;
		}
		if(null!=num&&!"".equals(num)){
			
			String s_sixNum = num.substring(0, index);
			
			if(sixNum.equals(s_sixNum)){
				String s_lastFourNum = num.substring(index);
				int lastFourNum = Integer.parseInt(s_lastFourNum);
				lastFourNum++;
				if(lastFourNum<10){
					num = ""+sixNum+"000"+lastFourNum;
				}
				if(lastFourNum<100&&lastFourNum>10){
					num = ""+sixNum+"00"+lastFourNum;
				}
				if(lastFourNum<1000&&lastFourNum>100){
					num = ""+sixNum+"0"+lastFourNum;
				}
				num = ""+sixNum+lastFourNum;
			}else{
				num = ""+sixNum+"0001";
			}
		}else{
			num = ""+sixNum+"0001";
		}
		return num;
	}
	
	
	//生成准考证号
	public String getExamNum(){
		ExamInfoDao examInfoDao = new ExamInfoDaoImp();
		String num=examInfoDao.getMaxExamNum();
		System.out.println(num+"!!!!!!!!!1");
		String s_month="";
		String s_day="";
		String sixNum ="";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		if(month<10){
			s_month="0"+month;
		}else{
			s_month=""+month;
		}
		sixNum = year+s_month;
        if(null!=num&&!"".equals(num)){
			String s_sixNum = num.substring(0, 6);
			if(sixNum.equals(s_sixNum)){
				String s_lastFourNum = num.substring(6);
				int lastFourNum = Integer.parseInt(s_lastFourNum);
				lastFourNum++;
				if(lastFourNum<10){
					num = ""+sixNum+"000"+lastFourNum;
				}else
				if(lastFourNum<100&&lastFourNum>=10){
					num = ""+sixNum+"00"+lastFourNum;
				}else
				if(lastFourNum<1000&&lastFourNum>=100){
					num = ""+sixNum+"0"+lastFourNum;
				}else{
					num = ""+sixNum+lastFourNum;
				}
			}else{
				num = ""+sixNum+"0001";
			}
		}else{
			num = ""+sixNum+"0001";
		}
		return num;
	}
	
	//根据考生来源，生成证书编号
	public String getCertNum(String flag){
		String str="";
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		if(flag.equals("0")){
			String num=certInfoDao.getMaxCertNumsInSystem();
			if(null!=num&&!"".equals(num)){
				Integer i=Integer.parseInt(num);
				i++;
				if(i<10){
					str="000"+i;
				}else if(i>=10&&i<100){
					str="00"+i;
				}else if(i>=100&&i<1000){
					str="0"+i;
				}else{
					str=""+i;
				}
			}else{
				str="0001";
			}	
		}else{
		   String num=certInfoDao.getMaxCertNums();
		   if(null!=num&&!"".equals(num)){
			   Integer i=Integer.parseInt(num);
				i++;
			   str=""+i;
			}else{
				str="5001";
			}
		}
		return str;
	}
}
