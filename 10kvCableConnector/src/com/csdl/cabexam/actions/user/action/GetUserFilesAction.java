package com.csdl.cabexam.actions.user.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.ExtraInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class GetUserFilesAction extends ActionSupport{
	//判断是否通过审核，pass表示通过，nopass表示没通过，nosignup标识没填包括信息，error表示没登陆
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			HttpServletRequest request = ServletActionContext.getRequest();
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
			//判断用户有没登录，没登陆返回nologin
			if(null!=userInfo&&!"".equals(userInfo)){
				ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
				ExtraInfoDao extraDao = new ExtraInfoDaoImp();
				ExamInfoDao examDao = new ExamInfoDaoImp();
				ExtraInfo extra = extraDao.getExtraInfo();
				CertificateInfoDao certiDao = new CertificateInfoDaoImp();
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				List<CertificateInfo> certInfoList = certiDao.getCertInfosByUserId(userInfo.getUserInfoId());
				ExtraInfo extraInfo = extraDao.getExtraInfo();
				if(null!=examineeInfo&&!"".equals(examineeInfo)){
					request.setAttribute("examineeInfo", examineeInfo);
				}
				if(null!=examInfo&&!"".equals(examInfo)){
					request.setAttribute("examInfo", examInfo);
				}
				if(null!=extraInfo&&!"".equals(extraInfo)){
					request.setAttribute("extra", extraInfo);
				}
				if(certInfoList.size()>0&&null!=certInfoList){
					request.setAttribute("certInfoList", certInfoList);
				}
				return SUCCESS;
			}else{
				return "nologin";
			}
		}
}
