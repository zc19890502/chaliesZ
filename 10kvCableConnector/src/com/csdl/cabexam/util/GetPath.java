package com.csdl.cabexam.util;

import java.util.ResourceBundle;

/**
 * 得到支付提交，�?款提交，以及密钥存放路径
 * 
 * @author guobin
 * 
 */
public class GetPath {
	/**
	 * 得到EDU支付前台地址
	 * 
	 * @return
	 */
	public static String getEduPayPgUrl() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("EDU_PAY_PGURL");// 得到文件内表示的类的含义
	}

	/**
	 * 得到EDU�?��后台地址
	 * 
	 * @return
	 */
	public static String getEduRefBgUrl() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("EDU_REF_BGURL");// 得到文件内表示的类的含义
	}

	/**
	 * 得到EDU查询后台地址
	 * 
	 * @return
	 */
	public static String getEduQueryBgUrl() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("EDU_QUERY_BGURL");// 得到文件内表示的类的含义
	}

	/**
	 * 得到考试平台商户私钥存放路径
	 * 
	 * @return
	 */
	public static String getMerPriKeyPath() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("MER_PRI_KEY_PATH");// 得到文件内表示的类的含义
	}

	/**
	 * 得到考试平台CHINAPAY公钥存放路径
	 * 
	 * @return
	 */
	public static String getChinaPayPubKeyPath() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("CHINAPAY_PUB_KEY_PATH");// 得到文件内表示的类的含义
	}

	/**
	 * 返回商户前台地址
	 * 
	 * @return
	 */
	public static String getReturnMerPgUrl() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("MER_PAYRETURN_PGURL");// 得到文件内表示的类的含义
	}

	/**
	 * 返回商户后台地址
	 * 
	 * @return
	 */
	public static String getReturnMerBgUrl() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("MER_PAYRETURN_BGURL");// 得到文件内表示的类的含义
	}

	/******
	 * 返回商户业务后台地址
	 * 
	 * @return
	 */
	public static String getReturnMerBusiUrl() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("MER_BUSI_BGURL");// 得到文件内表示的类的含义
	}

	/******
	 * 返回商户业务后台地址
	 * 
	 * @return
	 */	
	public static String getMerId() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("MerId");// 得到文件内表示的类的含义
	}

	/******
	 * 返回商户业务后台地址
	 * 
	 * @return
	 */
	public static String getCuryId() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("CuryId");// 得到文件内表示的类的含义
	}

	/******
	 * 返回商户业务后台地址
	 * 
	 * @return
	 */
	public static String getVersion() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("Version");// 得到文件内表示的类的含义
	}

	/******
	 * 返回商户业务后台地址
	 * 
	 * @return
	 */
	public static String getShareType() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("ShareType");// 得到文件内表示的类的含义
	}

	/******
	 * 返回商户业务后台地址
	 * 
	 * @return
	 */
	public static String getShareData() {
		ResourceBundle rb = ResourceBundle.getBundle("path");// 得到配置文件
		return rb.getString("ShareData");// 得到文件内表示的类的含义
	}
	
	
}
