package com.csdl.cabexam.actions.examee.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.csdl.cabexam.beans.ExamInfo;
import com.csdl.cabexam.beans.ExamineeInfo;
import com.csdl.cabexam.beans.OrderInfo;
import com.csdl.cabexam.dao.ExamInfoDao;
import com.csdl.cabexam.dao.ExamineeInfoDao;
import com.csdl.cabexam.dao.ExtraInfoDao;
import com.csdl.cabexam.dao.OrderInfoDao;
import com.csdl.cabexam.dao.imp.ExamInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExamineeInfoDaoImp;
import com.csdl.cabexam.dao.imp.ExtraInfoDaoImp;
import com.opensymphony.xwork2.ActionSupport;

public class ExamineeAction extends ActionSupport{
	private OrderInfo order;
	private ExamineeInfo examineeInfo;
	private String c_homesite;
	
	
	private String c_realName;
	private String c_company;
	
	//TODO ************添加查询
	private String c_skillLeval;
	
	private String checkState;

	public String queryAllExammer(){
		HttpServletRequest request=ServletActionContext.getRequest();
		ExamineeInfoDao examInfoDao = new ExamineeInfoDaoImp();
		List<ExamineeInfo> examineeInfoList = examInfoDao.getAllExammerInfo();
        request.setAttribute("examineeInfoList", examineeInfoList); 
        return SUCCESS;  	
	}
	
	
	public String queryExammerByState(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String checkState = request.getParameter("checkState");
		if(null!=checkState&&!"".equals(checkState)){
			ExamineeInfoDao examInfoDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> examineeInfoList = examInfoDao.getExamineeInfoByCheckState(checkState);
			request.setAttribute("examineeInfoList", examineeInfoList);
			request.setAttribute("checkState", checkState);
			if(checkState.equals("0")){
				return SUCCESS;
			}else if(checkState.equals("1")){
				return SUCCESS;
			}else if(checkState.equals("2")){
				return SUCCESS;
			}else if(checkState.equals("8")){
				return SUCCESS;
			}else if(checkState.equals("7")){
				return SUCCESS;
			}else{
				return ERROR;
			}
		}else{
			return ERROR;
		}
	}
	//调出满足理论缴费的名单
	public String queryTheoryFee(){
		HttpServletRequest request=ServletActionContext.getRequest();
			ExamineeInfoDao examInfoDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> examineeInfoList = examInfoDao.getExamineeInfoByCheckState("1");	
			List<ExamineeInfo> examineeInfoList1 = examInfoDao.getExamineeInfoByCheckState("4");
			List<ExamineeInfo> examineeInfoList2 = examInfoDao.getExamineeInfoByCheckState("3");
			List<ExamineeInfo> examineeInfoList3 = examInfoDao.getExamineeInfoByCheckState("11");
			List<ExamineeInfo> examineeInfoList4 = examInfoDao.getExamineeInfoByCheckState("12");
			examineeInfoList.addAll(examineeInfoList1);
			examineeInfoList.addAll(examineeInfoList2);
			examineeInfoList.addAll(examineeInfoList3);
			examineeInfoList.addAll(examineeInfoList4);
			request.setAttribute("examineeInfoList", examineeInfoList);
			return "success";
	}
	
	//条件查询理论缴费名单
	public String queryTheoryFeeByConditon(){	
		HttpServletRequest request=ServletActionContext.getRequest();
		String realName = request.getParameter("realName");
		String company = request.getParameter("company");
		String skillLeval = request.getParameter("skillLeval");
		if(null!=realName&&!"".equals(realName)){
			try {
				realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
				c_realName = realName;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(null!=skillLeval&&!"".equals(skillLeval)){
			try {
				skillLeval = new String(skillLeval.getBytes("iso-8859-1"),"utf-8");
				c_skillLeval = skillLeval;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ERROR;
			} 
		}
		if(null!=company&&!"".equals(company)){
			try {
				company = new String(company.getBytes("iso-8859-1"),"utf-8");
				c_company = company;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ERROR;
			} 
		}
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		//TODO  条件查询出缴费列表
		try {
			List<ExamineeInfo> examineeInfoList = examineeDao.getExamineeInfoOfTheoryFeeByCondition(c_realName, c_skillLeval, c_company);
			request.setAttribute("examineeInfoList", examineeInfoList);
			request.setAttribute("realName", c_realName);
			request.setAttribute("skillLeval", c_skillLeval);
			request.setAttribute("company", c_company);
			return SUCCESS;	
			
		} catch (Exception e) {
			return ERROR;
		}
		

	}
	//条件查询操作缴费名单
	public String queryOperateFeeByConditon(){		
		HttpServletRequest request=ServletActionContext.getRequest();
		String realName = request.getParameter("realName");
		String company = request.getParameter("company");
		String skillLeval = request.getParameter("skillLeval");
		if(null!=realName&&!"".equals(realName)){
			try {
				realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
				c_realName = realName;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(null!=skillLeval&&!"".equals(skillLeval)){
			try {
				skillLeval = new String(skillLeval.getBytes("iso-8859-1"),"utf-8");
				c_skillLeval = skillLeval;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ERROR;
			} 
		}
		if(null!=company&&!"".equals(company)){
			try {
				company = new String(company.getBytes("iso-8859-1"),"utf-8");
				c_company = company;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ERROR;
			} 
		}
		ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
		//TODO  条件查询出缴费列表
		try {
			List<ExamineeInfo> examineeInfoList = examineeDao.getExamineeInfoOfOperateFeeByCondition(c_realName, c_skillLeval, c_company);
			request.setAttribute("examineeInfoList", examineeInfoList);
			request.setAttribute("realName", c_realName);
			request.setAttribute("skillLeval", c_skillLeval);
			request.setAttribute("company", c_company);
			return SUCCESS;	
			
		} catch (Exception e) {
			return ERROR;
		}
		
	}
	
	
	//调出满足操作缴费的名单
	public String queryOperateFee(){
		HttpServletRequest request=ServletActionContext.getRequest();
			ExamineeInfoDao examInfoDao = new ExamineeInfoDaoImp();
			//TODO 操作缴费名单
			List<ExamineeInfo> examineeInfoList = examInfoDao.getExamineeInfoByOperateFee();
			request.setAttribute("examineeInfoList", examineeInfoList);
			return "success";
	}
	
	public String queryExamineeFile(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String s_examineeId = request.getParameter("examineeId");
		if(null!=s_examineeId&&!"".equals(s_examineeId)){
			int examineeId = Integer.parseInt(s_examineeId);
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByID(examineeId);
			request.setAttribute("examineeInfo", examineeInfo);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	
	
	public String queryExamineeCheckedReady(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String s_examineeId = request.getParameter("examineeId");
		if(null!=s_examineeId&&!"".equals(s_examineeId)){
			int examineeId = Integer.parseInt(s_examineeId);
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByID(examineeId);
			request.setAttribute("examineeInfo", examineeInfo);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	public String queryExamineeTheoryFeeById(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String s_examineeId = request.getParameter("examineeId");
		if (s_examineeId==null) {
			int sessionExamineeId = (Integer) request.getSession().getAttribute("examineeId");
			request.getSession().removeAttribute("examineeId");
			s_examineeId = String.valueOf(sessionExamineeId);
		}
		System.out.println("s_examineeId: "+s_examineeId);
		if(null!=s_examineeId&&!"".equals(s_examineeId)){
			int examineeId = Integer.parseInt(s_examineeId);
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByID(examineeId);
			int userInfoId = examineeInfo.getUserInfo().getUserInfoId();
			String type = "0";
			ExtraInfoDao extraDao = new ExtraInfoDaoImp();			
			String batch = extraDao.getExtraInfo().getExamBatch();
			OrderInfoDao orderDao = new OrderInfoDao();
			order = orderDao.queryByIdOrderInfoId(userInfoId, type, batch);
			request.setAttribute("examineeInfo", examineeInfo);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}

	public String queryExamineeOperateFeeById(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String s_examineeId = request.getParameter("examineeId");
		if(null!=s_examineeId&&!"".equals(s_examineeId)){
			int examineeId = Integer.parseInt(s_examineeId);
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			ExamineeInfo examineeInfo = examineeDao.getExamineeInfoByID(examineeId);
			int userInfoId = examineeInfo.getUserInfo().getUserInfoId();
			String type = examineeInfo.getSkillLevel();
			ExtraInfoDao extraDao = new ExtraInfoDaoImp();			
			String batch = extraDao.getExtraInfo().getExamBatch();
			OrderInfoDao orderDao = new OrderInfoDao();
			order = orderDao.queryByIdOrderInfoId(userInfoId, type, batch);
			request.setAttribute("examineeInfo", examineeInfo);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	

	//根据考生姓名、籍贯、工作单位组合查询考生信息
		public String queryExamineeByCondition(){
			HttpServletRequest request=ServletActionContext.getRequest();
			String realName = request.getParameter("realName");
			String homesite = request.getParameter("homesite");
			String company = request.getParameter("company");
			if(null!=realName&&!"".equals(realName)){
				try {
					realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
					c_realName = realName;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=homesite&&!"".equals(homesite)){
				try {
					homesite = new String(homesite.getBytes("iso-8859-1"),"utf-8");
					c_homesite = homesite;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return ERROR;
				} 
			}
			if(null!=company&&!"".equals(company)){
				try {
					company = new String(company.getBytes("iso-8859-1"),"utf-8");
					c_company = company;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ERROR;
				} 
			}
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> examineeInfoList = examineeDao.getExamineeInfoByCondition(c_realName, c_homesite, c_company);
			request.setAttribute("examineeInfoList", examineeInfoList);
			request.setAttribute("realName", c_realName);
			request.setAttribute("homesite", c_homesite);
			request.setAttribute("company", c_company);
			return SUCCESS;		
		}
		//根据考生姓名、籍贯、工作单位组合查询考生信息
		public String queryFailedExamineeByCondition(){
			HttpServletRequest request=ServletActionContext.getRequest();
			String realName = request.getParameter("realName");
			String homesite = request.getParameter("homesite");
			String company = request.getParameter("company");
			if(null!=realName&&!"".equals(realName)){
				try {
					realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
					c_realName = realName;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=homesite&&!"".equals(homesite)){
				try {
					homesite = new String(homesite.getBytes("iso-8859-1"),"utf-8");
					c_homesite = homesite;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ERROR;
				} 
			}
			if(null!=company&&!"".equals(company)){
				try {
					company = new String(company.getBytes("iso-8859-1"),"utf-8");
					c_company = company;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ERROR;
				} 
			}
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> examineeInfoList = examineeDao.getFailedExamineeInfoByCondition(c_realName,c_homesite,c_company);
			request.setAttribute("examineeInfoList", examineeInfoList);
			request.setAttribute("realName", c_realName);
			request.setAttribute("homesite", c_homesite);
			request.setAttribute("company", c_company);
			return SUCCESS;		
		}
		//根据考生姓名、籍贯、工作单位组合查询考生信息
		public String queryPassExamineeByCondition(){
			HttpServletRequest request=ServletActionContext.getRequest();
			String realName = request.getParameter("realName");
			String homesite = request.getParameter("homesite");
			String company = request.getParameter("company");
			if(null!=realName&&!"".equals(realName)){
				try {
					realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
					c_realName = realName;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=homesite&&!"".equals(homesite)){
				try {
					homesite = new String(homesite.getBytes("iso-8859-1"),"utf-8");
					c_homesite = homesite;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ERROR;
				} 
			}
			if(null!=company&&!"".equals(company)){
				try {
					company = new String(company.getBytes("iso-8859-1"),"utf-8");
					c_company = company;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return ERROR;
				} 
			}
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> examineeInfoList = examineeDao.getcheckStateExamineeInfoByCondition(c_realName, c_homesite, c_company,checkState);
			request.setAttribute("examineeInfoList", examineeInfoList);
			request.setAttribute("realName", c_realName);
			request.setAttribute("homesite", c_homesite);
			request.setAttribute("company", c_company);
			return SUCCESS;		
		}
		public String getcheckStateExamineeInfoByCondition(){
			HttpServletRequest request=ServletActionContext.getRequest();
			String realName = request.getParameter("realName");
			String homesite = request.getParameter("homesite");
			String company = request.getParameter("company");
			String checkState = request.getParameter("checkState");
			if(null!=realName&&!"".equals(realName)){
				try {
					realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
					c_realName = realName;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=homesite&&!"".equals(homesite)){
				try {
					homesite = new String(homesite.getBytes("iso-8859-1"),"utf-8");
					c_homesite = homesite;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ERROR;
				} 
			}
			if(null!=company&&!"".equals(company)){
				try {
					company = new String(company.getBytes("iso-8859-1"),"utf-8");
					c_company = company;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return ERROR;
				} 
			}
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> examineeInfoList = examineeDao.getcheckStateExamineeInfoByCondition(c_realName, c_homesite, c_company,checkState);
			request.setAttribute("examineeInfoList", examineeInfoList);
			request.setAttribute("realName", c_realName);
			request.setAttribute("homesite", c_homesite);
			request.setAttribute("company", c_company);
			request.setAttribute("checkState", checkState);
			return SUCCESS;		
		}
		//根据考生姓名、籍贯、工作单位组合查询考生信息
		public String queryWaitExamineeByCondition(){
			HttpServletRequest request=ServletActionContext.getRequest();
			String realName = request.getParameter("realName");
			String homesite = request.getParameter("homesite");
			String company = request.getParameter("company");
			if(null!=realName&&!"".equals(realName)){
				try {
					realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
					c_realName = realName;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if(null!=homesite&&!"".equals(homesite)){
				try {
					homesite = new String(homesite.getBytes("iso-8859-1"),"utf-8");
					c_homesite = homesite;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return ERROR;
				} 
			}
			if(null!=company&&!"".equals(company)){
				try {
					company = new String(company.getBytes("iso-8859-1"),"utf-8");
					c_company = company;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return ERROR;
				} 
			}
			ExamineeInfoDao examineeDao = new ExamineeInfoDaoImp();
			List<ExamineeInfo> examineeInfoList = examineeDao.getWaitExamineeInfoByCondition(c_realName, c_homesite, c_company);
			request.setAttribute("examineeInfoList", examineeInfoList);
			request.setAttribute("realName", c_realName);
			request.setAttribute("homesite", c_homesite);
			request.setAttribute("company", c_company);
			return SUCCESS;		
		}
		
		
		
		
	
	public ExamineeInfo getExamineeInfo() {
		return examineeInfo;
	}

	
	public String queryExammerByRealName(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String realName = request.getParameter("realName");
		ExamInfoDao em = new ExamInfoDaoImp();
		List<ExamInfo> examList =  em.getExamInfoByRealName(realName);
        request.setAttribute("ExamList", examList); 
        return SUCCESS;  	
         	
	}


	public String getC_realName() {
		return c_realName;
	}


	public void setC_realName(String c_realName) {
		this.c_realName = c_realName;
	}


	public String getC_homesite() {
		return c_homesite;
	}


	public void setC_homesite(String c_homesite) {
		this.c_homesite = c_homesite;
	}


	public String getC_company() {
		return c_company;
	}


	public void setC_company(String c_company) {
		this.c_company = c_company;
	}
	
	public void setExamineeInfo(ExamineeInfo examineeInfo) {
		this.examineeInfo = examineeInfo;
	}
	
	
	public String getC_skillLeval() {
		return c_skillLeval;
	}
	
	
	public void setC_skillLeval(String c_skillLeval) {
		this.c_skillLeval = c_skillLeval;
	}


	public String getCheckState() {
		return checkState;
	}


	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}


	public OrderInfo getOrder() {
		return order;
	}


	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	
}
