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

	//�жϿ����������
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		//�ж��û���û��¼��û��½����nologin
		if(null!=userInfo&&!"".equals(userInfo)){
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			ExtraInfoDao extraDao = new ExtraInfoDaoImp();
			ExamInfoDao examDao = new ExamInfoDaoImp();
			ExtraInfo extraInfo = extraDao.getExtraInfo();
			ExamineeService examineeServ = new ExamineeService();
			CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
			String resultStr = examineeServ.examineeSignUpState(userInfo);
			if("outofDate".equals(resultStr)){
				//����ʱ����ڣ����ܱ���
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeState", resultStr);
				return "examStateNo";
			}else
			if("noInfoCanSignUp".equals(resultStr)){
				//û�п�����Ϣ�����Ա���
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeState", resultStr);
				return "examStateYes";
			}else
			if("initnoCheckCanUpdate".equals(resultStr)){
				//����ûͨ�������Ա�������ʾ�ٴα������ɾ��֮ǰ������Ϣ�󣬵����ת���ɲ鿴������Ϣ
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				return "examStateYes";
			}else
			if("waitCheckedCanUpdate".equals(resultStr)){
				//����ˣ����Ա�������ʾ�ٴα������ɾ��֮ǰ������Ϣ�󣬵����ת���ɲ鿴������Ϣ
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateYes";
			}else
			if("initpassCheckCannoutSignup".equals(resultStr)){
				//����ͨ������ʾ�ȴ����󣬲��ܱ�������ʾ�ȴ�����
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateNo";
			}else
			if("noCheckedCanUpdate".equals(resultStr)){
				//���ûͨ�������Ա�������ʾ�ٴα������ɾ��֮ǰ������Ϣ�󣬵����ת���ɲ鿴������Ϣ
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				return "examStateYes";
			}else
			if("checkedCannotSignUp".equals(resultStr)){
				//���ͨ���������Ա�������ʾ�ϴ����۽ɷѵ�
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateNo";
			}else
			if("thoryFeeSuccessCannotSignup".equals(resultStr)){
				//���۽ɷѵ����ͨ���������Ա�������ʾ����ת���鿴������Ϣ
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateNo";
			}else
			if("thoryFeeFailCannotSignup".equals(resultStr)){
				//���۽ɷѵ����ʧ�ܣ������Ա�������ʾ�����ϴ��ɷѵ�
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateNo";
			}else
			if("hasExamCanSignUp".equals(resultStr)){
				//�Ѿ������гɼ���Ϣ�������ٴα�����
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateYes";
			}else
			if("hasNoExamCanNotSignUp".equals(resultStr)){
				//û�����۳ɼ����������ٴα�������ʾ����ת��׼��֤��Ϣ����
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("operateFeeCanUp".equals(resultStr)){
				//�����۳ɼ������۳ɼ�������ʾ�����ϴ������ɷѵ�
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("operateFeeCannotUp".equals(resultStr)){
				//�����۳ɼ������۳ɼ������񣬲������ϴ������ɷѵ�
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("operatFeeSuccessConnotSignup".equals(resultStr)){
				//�����ɷѵ���˳ɹ���
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("operatFeeFailConnotSignup".equals(resultStr)){
				//�����ɷѵ���˳ɹ���
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("extraInfo", extraInfo);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("examInfo", examInfo);
				return "examStateNo";
			}else
			if("hasValidCertCanSignUp".equals(resultStr)){
				//����Ч��֤������Ϣ,�����ٴα�������ʾ��Ҫɾ��������Ϣ��Ȼ���г�������Ϣ
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
				//����Ч��֤������Ϣ,�����ٴα�������ʾ��Ҫɾ��������Ϣ��Ȼ���г�������Ϣ
				ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				request.setAttribute("examineeState", resultStr);
				request.setAttribute("examineeInfo", examineeInfo);
				request.setAttribute("extraInfo", extraInfo);
				return "examStateYes";
			}else if("refOk".equals(resultStr)){
				//����Ч��֤������Ϣ,�����ٴα�������ʾ��Ҫɾ��������Ϣ��Ȼ���г�������Ϣ
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
