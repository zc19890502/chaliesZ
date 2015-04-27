package com.csdl.cabexam.test;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.csdl.cabexam.actions.examee.service.ExamineeService;
import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.Notice;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.NoticeDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.NoticeDaoImp;
import com.csdl.cabexam.util.hibernate.HibernateSessionFactory;

public class MethodTest {
	
	private ExamineeInfo examineeInfo;
	private UserInfo userInfo=new UserInfo();
	
	public static void main(String[] args) {
		MethodTest m = new MethodTest();
		m.getCertInfo();
	}
	
	public void deleteFiles(){
		ExamineeService esver = new ExamineeService();
		File file = new File("E:\\upload");
		if(!file.exists()){
			file.mkdirs();
			System.out.println("~~~~");
		}else{
			esver.deleteFiles(file);
			System.out.println("!!!");
		}
	}
	
	
	public void exmaineeDaoTest() {
		userInfo.setUserInfoId(10);
		ExamineeInfoDao exma = new ExamineeInfoDaoImp();
		examineeInfo = exma.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
		System.out.println(examineeInfo);
	}
	public void noticeDaoTest(){
		NoticeDao noticedao = new NoticeDaoImp();
		List<Notice> noticeList = noticedao.getNoticeByNoticeType("1",5);
		System.out.println(noticeList.get(0).getNoticeType());
		
	}
	
	private void getTimes() {
		SimpleDateFormat sqf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = "2014-6-5";
		try {
			Date date = sqf.parse(strDate);
			Timestamp tm = new Timestamp(date.getTime());
			System.out.println(date);
			System.out.println(tm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getCertInfo(){
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		List<CertificateInfo> certInfoList = certInfoDao.getAllCertInfo();
		Set<ExamInfo> examSet = certInfoList.get(0).getUserInfo().getExamInfos();
		for (ExamInfo examInfo : examSet) {
			System.out.println(examInfo.getNumber());
		}		
		System.out.println(certInfoList.get(0).getUserInfo().getExamInfos());
	}
}
