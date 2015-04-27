package com.csdl.cabexam.actions.certinfo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.CertificateInfo;
import com.csdl.cabexam.beans.ManagerInfo;
import com.csdl.cabexam.beans.ScoreExcel;
import com.csdl.cabexam.dao.CertificateInfoDao;
import com.csdl.cabexam.dao.CommonDao;
import com.csdl.cabexam.dao.MutiTableDao;
import com.csdl.cabexam.dao.imp.CertificateInfoDaoImp;
import com.csdl.cabexam.dao.imp.CommonDaoImp;
import com.csdl.cabexam.dao.imp.MutiTableDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class getCertInfoByConditon extends ActionSupport{
		private String c_realName;
		private String c_certnum;
		private String c_idnum;
		//根据考生姓名、籍贯、工作单位组合查询考生成绩信息
		public String queryCertByCondition(){
			HttpServletRequest request=ServletActionContext.getRequest();
			try {
				
				String realName = request.getParameter("realName");
				String certnum = request.getParameter("certnum");
				String idnum = request.getParameter("idnum");
				if(null!=realName&&!"".equals(realName)){			
					realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
					c_realName=realName;
				}
				if(null!=certnum&&!"".equals(certnum)){			
					certnum = new String(certnum.getBytes("iso-8859-1"),"utf-8");
					c_certnum=certnum;
				}
				if(null!=idnum&&!"".equals(idnum)){			
					idnum = new String(idnum.getBytes("iso-8859-1"),"utf-8");
					c_idnum=idnum;
				}
			    CertificateInfoDao ctd = new CertificateInfoDaoImp();
				List<CertificateInfo> list =  ctd.getCertInfosByCondition(c_realName, c_certnum, c_idnum);
				request.setAttribute("certInfoList", list);
				request.setAttribute("realName", c_realName);
				request.setAttribute("certnum", c_certnum);
				request.setAttribute("idnum", c_idnum);
				return "success";
			} catch (Exception e) {
				return "error";
			}
			
			
		}
		public String getC_realName() {
			return c_realName;
		}
		public void setC_realName(String c_realName) {
			this.c_realName = c_realName;
		}
		public String getC_certnum() {
			return c_certnum;
		}
		public void setC_certnum(String c_certnum) {
			this.c_certnum = c_certnum;
		}
		public String getC_idnum() {
			return c_idnum;
		}
		public void setC_idnum(String c_idnum) {
			this.c_idnum = c_idnum;
		}

}
