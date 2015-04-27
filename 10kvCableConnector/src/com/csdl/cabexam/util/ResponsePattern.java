package com.csdl.cabexam.util;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 解析CP响应
 * 
 * @author guobin
 * 
 */
public class ResponsePattern {
	private static final Pattern pSplitReturn = Pattern.compile("\\&");
	private static final Pattern pSplitEqual = Pattern.compile("\\=");
	private static final Logger logger = Logger.getLogger(ResponsePattern.class);

	/**
	 * 将响应信息字符串转化为MAP
	 * 
	 * @param input
	 * @return Map
	 */
	public static Map<String, String> decodeForMap(String input) {
		Map<String, String> map = new TreeMap<String, String>();
		String[] rows = pSplitReturn.split(input);
		for (int i = 0; i < rows.length; i++) {
			String[] nvPair = pSplitEqual.split(rows[i], 2);
			map.put(nvPair[0], nvPair[1]);
		}
		return map;
	}

	public static String getBody(String resStr) {
		int a1 = resStr.indexOf("<body>");
		if (a1 == -1) {
			logger.error("获取响应信息BODY值失�?");
			return null;
		}
		int a2 = resStr.indexOf("</body>");
		if (a2 == -1) {
			logger.error("获取响应信息BODY值失�?");
			return null;
		}
		String body = "";
		try {
			body = resStr.substring(a1 + 6, a2).trim();
			if (body == null || "".equals(body)) {
				logger.error("获取响应信息BODY值失�?");
				return null;
			}
		} catch (Exception e) {
			logger.error("获取响应信息BODY值异�?");
			return null;
		}
		return body;
	}
}
