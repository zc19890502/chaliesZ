package com.csdl.cabexam.actions.examee.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.examee.service.ExamineeService;
import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class ExamineeEnterAction extends ActionSupport{
	private ExamineeInfo examineeInfo;
	private File picLoad;
	private String picLoadContentType;
	private String picLoadFileName;
	private File idLoad;
	private String idLoadContentType;
	private String idLoadFileName;
	private File workLoad;
	private String workLoadContentType;
	private String workLoadFileName;
	private File compLoad;
	private String compLoadContentType;
	private String compLoadFileName;
	private String birTime;
	private String workTime;


	public String execute() throws Exception {
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		ExamineeService examineeServ = new ExamineeService();
		ExamInfoDao examDao = new ExamInfoDaoImp();
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		HttpServletRequest request = ServletActionContext.getRequest();
		ExamineeService examineeService = new ExamineeService();
		CommonDao cd = new CommonDaoImp();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userSession");
		if(null!=userInfo&&!"".equals(userInfo)){
			if(null!=birTime&&!"".equals(birTime)){
				examineeInfo.setBirth(examineeService.convertToTimestamp(birTime,"yyyy-MM-dd"));
			}
			if(null!=workTime&&!"".equals(workTime)){
				examineeInfo.setWorkDate(examineeService.convertToTimestamp(workTime,"yyyy-MM-dd"));
			}
			String resultStr = examineeServ.examineeSignUpState(userInfo);
			if("waitCheckedCanUpdate".equals(resultStr)||"noInfoCanSignUp".equals(resultStr)||"noCheckedCanUpdate".equals(resultStr)||"hasExamCanSignUp".equals(resultStr)||"hasValidCertCanSignUp".equals(resultStr)){
				ExamineeInfo examineeInfo_db = examineeDao.getExamineeInfoByUserInfo(userInfo.getUserInfoId());
				ExamInfo examInfo = examDao.getExamInfoByUserId(userInfo.getUserInfoId());
				CertificateInfo certInfo = certInfoDao.getvalidCertByUserId(userInfo.getUserInfoId());
				int examineeId = 0;
				if(null!=examineeInfo_db&&!"".equals(examineeInfo_db)){
					examineeId = examineeInfo_db.getExamineeInfoId();
					examineeInfo.setExamineeInfoId(examineeId);
					cd.updateObject(examineeInfo);
				}else{
					examineeId = cd.saveObjectAndGetObjectId(examineeInfo);
				}
				if(null!=examInfo&&!"".equals(examInfo)){
					cd.deleteObject(examInfo);
				}
				if(null!=certInfo&&!"".equals(certInfo)){
					certInfo.setCertificationState("2");
					cd.updateObject(certInfo);
				}
				String roots = null;
				File file = null;
				roots = ServletActionContext.getServletContext().getRealPath("/uploadList/"+examineeId);
				file = new File(roots);
				if(!file.exists()){
					file.mkdirs();
				}
				//上传考生照片
				//如果为空，跳入错误页面
				if(null!=picLoad||(null!=examineeInfo.getPhoto()&&!"".equals(examineeInfo.getPhoto()))){
					if(null!=picLoad){
						String relatepath = examineeService.uploadFile(picLoadFileName, "photo", examineeId, picLoad, roots);
						examineeInfo.setPhoto(relatepath);
					}
				}else{
					cd.deleteObject(examineeInfo);
					request.setAttribute("uploaderr", 1);
					return "err01";
				}
				//上传考生id扫描件
				//如果为空，跳入错误页面
				if(null!=idLoad||(null!=examineeInfo.getIdcardScan()&&!"".equals(examineeInfo.getIdcardScan()))){
					if(null!=idLoad){
						String relatepath = examineeService.uploadFile(idLoadFileName, "id", examineeId, idLoad, roots);
						examineeInfo.setIdcardScan(relatepath);
					}
				}else{
					cd.deleteObject(examineeInfo);
					examineeService.deleteFiles(file);
					request.setAttribute("uploaderr", 2);
					return "err02";
				}
				//上传考生工作证明
				//如果为空，跳入错误页面
				if(null!=workLoad||(null!=examineeInfo.getWorkingProof()&&!"".equals(examineeInfo.getWorkingProof()))){
					if(null!=workLoad){
						String relatepath = examineeService.uploadFile(workLoadFileName, "workproof", examineeId, workLoad, roots);
						examineeInfo.setWorkingProof(relatepath);
					}
				}else{
					cd.deleteObject(examineeInfo);
					examineeService.deleteFiles(file);
					request.setAttribute("uploaderr", 3);
					return "err03";
				}
				//上传公司审核意见
				//如果为空，跳入错误页面
				if(null!=compLoad||(null!=examineeInfo.getCompanyExamine()&&!"".equals(examineeInfo.getCompanyExamine()))){
					if(null!=compLoad){
						String relatepath = examineeService.uploadFile(compLoadFileName, "company", examineeId, compLoad, roots);
						examineeInfo.setCompanyExamine(relatepath);
					}
				}else{
					cd.deleteObject(examineeInfo);
					examineeService.deleteFiles(file);
					request.setAttribute("uploaderr", 4);
					return "err04";
				}
				//checkState "0"表示待审核，"1"审核通过
				examineeInfo.setCheckState("0");
				cd.updateObject(examineeInfo);
				//userState "1"表示本届,"0"表示往届
				userInfo.setUserState("1");
				cd.updateObject(userInfo);
				request.getSession().setAttribute("userSession", userInfo);
				request.setAttribute("examineeInfoRequest", examineeInfo);
				return SUCCESS;
			}else{
				request.setAttribute("uploaderr", 5);
				return "cannotSignUp";
			}
		}else{
			return "nologin";
		}
		
	}

	public ExamineeInfo getExamineeInfo() {
		return examineeInfo;
	}

	public void setExamineeInfo(ExamineeInfo examineeInfo) {
		this.examineeInfo = examineeInfo;
	}


	public File getPicLoad() {
		return picLoad;
	}

	public void setPicLoad(File picLoad) {
		this.picLoad = picLoad;
	}

	public String getPicLoadContentType() {
		return picLoadContentType;
	}

	public void setPicLoadContentType(String picLoadContentType) {
		this.picLoadContentType = picLoadContentType;
	}

	public String getPicLoadFileName() {
		return picLoadFileName;
	}

	public void setPicLoadFileName(String picLoadFileName) {
		this.picLoadFileName = picLoadFileName;
	}

	public File getIdLoad() {
		return idLoad;
	}

	public void setIdLoad(File idLoad) {
		this.idLoad = idLoad;
	}

	public String getIdLoadContentType() {
		return idLoadContentType;
	}

	public void setIdLoadContentType(String idLoadContentType) {
		this.idLoadContentType = idLoadContentType;
	}

	public String getIdLoadFileName() {
		return idLoadFileName;
	}

	public void setIdLoadFileName(String idLoadFileName) {
		this.idLoadFileName = idLoadFileName;
	}

	public File getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(File workLoad) {
		this.workLoad = workLoad;
	}

	public String getWorkLoadContentType() {
		return workLoadContentType;
	}

	public void setWorkLoadContentType(String workLoadContentType) {
		this.workLoadContentType = workLoadContentType;
	}

	public String getWorkLoadFileName() {
		return workLoadFileName;
	}

	public void setWorkLoadFileName(String workLoadFileName) {
		this.workLoadFileName = workLoadFileName;
	}

	public File getCompLoad() {
		return compLoad;
	}

	public void setCompLoad(File compLoad) {
		this.compLoad = compLoad;
	}

	public String getCompLoadContentType() {
		return compLoadContentType;
	}

	public void setCompLoadContentType(String compLoadContentType) {
		this.compLoadContentType = compLoadContentType;
	}

	public String getCompLoadFileName() {
		return compLoadFileName;
	}

	public void setCompLoadFileName(String compLoadFileName) {
		this.compLoadFileName = compLoadFileName;
	}

	public String getBirTime() {
		return birTime;
	}

	public void setBirTime(String birTime) {
		this.birTime = birTime;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}



	
}
