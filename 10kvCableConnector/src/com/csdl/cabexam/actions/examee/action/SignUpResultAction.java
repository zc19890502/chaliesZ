package com.csdl.cabexam.actions.examee.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.examee.service.ExamineeService;
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

public class SignUpResultAction extends ActionSupport{

	//判断考生报名情况
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		//判断用户有没登录，没登陆返回nologin
		if(null!=userInfo&&!"".equals(userInfo)){
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			ExtraInfoDao extraDao = new ExtraInfoDaoImp();
			ExamInfoDao examDao = new ExamInfoDaoImp();
			ExtraInfo extraInfo = extraDao.getExtraInfo();
			ExamineeService examineeServ = new ExamineeService();
			CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
			String resultStr = examineeServ.examineeSignUpState(userInfo);
			if("outofDate".equals(resultStr)){
				//考试时间过期，不能报名
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeState", resultStr);
				return "examStateNo";
			}else
			if("noInfoCanSignUp".equals(resultStr)){
				//没有考生信息，可以报名
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeState", resultStr);
				return "examStateYes";
			}else
			if("initnoCheckCanUpdate".equals(resultStr)){
				//初审没通过，可以报名，提示再次报名后会删除之前所有信息后，点击跳转到可查看考生信息
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				return "examStateYes";
			}else
			if("waitCheckedCanUpdate".equals(resultStr)){
				//待审核，可以报名，提示再次报名后会删除之前所有信息后，点击跳转到可查看考生信息
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateYes";
			}else
			if("initpassCheckCannoutSignup".equals(resultStr)){
				//初审通过，提示等待复审，不能报名，提示等待复审
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateNo";
			}else
			if("noCheckedCanUpdate".equals(resultStr)){
				//审核没通过，可以报名，提示再次报名后会删除之前所有信息后，点击跳转到可查看考生信息
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				return "examStateYes";
			}else
			if("checkedCannotSignUp".equals(resultStr)){
				//审核通过，不可以报名，提示上传理论缴费单
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateNo";
			}else
			if("thoryFeeSuccessCannotSignup".equals(resultStr)){
				//理论缴费单审核通过，不可以报名，提示后跳转进查看考生信息
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateNo";
			}else
			if("thoryFeeFailCannotSignup".equals(resultStr)){
				//理论缴费单审核失败，不可以报名，提示重新上传缴费单
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateNo";
			}else
			if("hasExamCanSignUp".equals(resultStr)){
				//已经有所有成绩信息，可以再次报名，
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateYes";
			}else
			if("hasNoExamCanNotSignUp".equals(resultStr)){
				//没有理论成绩，不可以再次报名，提示后跳转到准考证信息管理
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("operateFeeCanUp".equals(resultStr)){
				//有理论成绩，理论成绩及格，提示可以上传操作缴费单
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("operateFeeCannotUp".equals(resultStr)){
				//有理论成绩，理论成绩不及格，不可以上传操作缴费单
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("operatFeeSuccessConnotSignup".equals(resultStr)){
				//操作缴费单审核成功，
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("operatFeeFailConnotSignup".equals(resultStr)){
				//操作缴费单审核成功，
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("hasValidCertCanSignUp".equals(resultStr)){
				//有有效的证书新信息,可以再次报名，提示将要删除所有信息，然后列出所有信息
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				CertificateInfo certInfo = certInfoDao.getvalidCertByUserId(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("certInfo", certInfo);
				request.setAttribute("examInfo", examInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateYes";
			}else if("refOrder".equals(resultStr)){
				//有有效的证书新信息,可以再次报名，提示将要删除所有信息，然后列出所有信息
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateYes";
			}else if("refOk".equals(resultStr)){
				//有有效的证书新信息,可以再次报名，提示将要删除所有信息，然后列出所有信息
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateYes";
			}else{
				return ERROR;
			}
		}else{
			return "nologin";
		}
	}

}
