package com.csdl.cabexam.util;

import java.util.List;
import org.apache.log4j.Logger;

/**
 * 发�?HTTP请求
 * @author guobin
 *
 */
public class HttpSend {
	private static final Logger logger = Logger.getLogger(HttpSend.class);

	/**
	 * 发�?HTTP请求到指定地�?
	 * @param url  请求地址
	 * @param list 该list为数组集�?
	 * @return
	 */
	public static String send(String url,List list){
		if(url == null || "".equals(url)){
			logger.error("HTTP请求地址为空!");
			return null;
		}
		String responseStr = null;
		try{
			String charSet = "utf-8";
			String timeOut = "30000";
			String nvPairs = HttpHelper.getNvPairs(list, charSet);
			responseStr = HttpHelper.doHttp(url, HttpHelper.POST, charSet, nvPairs, timeOut);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseStr;
	}
}
