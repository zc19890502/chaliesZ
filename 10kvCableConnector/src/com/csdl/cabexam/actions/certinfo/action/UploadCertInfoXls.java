package com.csdl.cabexam.actions.certinfo.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.actions.examee.service.ExamineeService;
import com.csdl.cabexam.beans.CertInfoExcel;
import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.UserInfo;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.UserInfoDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.csdl.cabexam.dao.imp.UserInfoDaoImp;
import com.csdl.cabexam.util.ExcelRead;
import com.csdl.cabexam.util.GenerateXls;
import com.opensymphony.xwork2.ActionSupport;

public class UploadCertInfoXls extends ActionSupport {
	private File Xlsfile;
	private String XlsfileContentType;
	private String XlsfileFileName;;
	
	  
	public String getXlsfileContentType() {
		return XlsfileContentType;
	}


	public void setXlsfileContentType(String xlsfileContentType) {
		XlsfileContentType = xlsfileContentType;
	}


	public String getXlsfileFileName() {
		return XlsfileFileName;
	}


	public void setXlsfileFileName(String xlsfileFileName) {
		XlsfileFileName = xlsfileFileName;
	}


	public File getXlsfile() {
		return Xlsfile;
	}


	public void setXlsfile(File xlsfile) {
		Xlsfile = xlsfile;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		HttpServletRequest request = ServletActionContext.getRequest();
		ExamineeService examineeService = new ExamineeService();
		UserInfoDao userInfoDao = new UserInfoDaoImp();
		CertificateInfoDao certInfoDao = new CertificateInfoDaoImp();
		CommonDao cd = new CommonDaoImp();
		// 上传考生成績錄入表
		// 如果为空，跳入错误页面
		if (null != Xlsfile) {
			String url = ServletActionContext.getServletContext().getRealPath(
					"/xls/up/");
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}

			String rootpath = url + "\\certInfo.xls";
			// 调用下面io流方法存放文件
			FileOutputStream fos = new FileOutputStream(rootpath);
			FileInputStream fis = new FileInputStream(Xlsfile);
			InputStream is = new FileInputStream(rootpath);
			byte[] buffer = new byte[1024];
			int len = 0;
			try {
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				ExcelRead er = new ExcelRead();
				// 将xls文件中的userInfoId和score读到List中
				List<String> errList = er.validateCertXls(is);
				if(!errList.isEmpty()){
					request.setAttribute("errList", errList);
					return "tips";
				}else{
				List<CertInfoExcel> list = er.readCertExcelContent(is);
				for (CertInfoExcel certInfoExcel : list) {
					UserInfo userInfo = userInfoDao
							.getUserInfoByidnum(certInfoExcel.getIdnum());
					if (null != userInfo && !"".equals(userInfo)) {
						if (!certInfoDao.getCertInfosByUserIdNochecked(userInfo
								.getUserInfoId())) {
							CertificateInfo certInfo = new CertificateInfo();
							certInfo.setCertificationNum(certInfoExcel
									.getCertificationNum());
							certInfo.setCertificationGrantDate(certInfoExcel
									.getCertificationGrantDate());
							certInfo.setCertificationState("1");
							certInfo.setUserInfo(userInfo);
//							certInfo.setRemainingScore(certInfoExcel.getRemainingScore());
							cd.addObject(certInfo);
						}
					} else {
						if (certInfoDao.InsertCertInfoExl(certInfoExcel)) {
						} else {
							return "insertCertError";
						}
					}
				}
				return SUCCESS;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ERROR;
			} finally {
				fos.close();
				fis.close();
				is.close();
			}

		} else {
			String emptyerror = "empty";
			return emptyerror;
		}
	}
}	
