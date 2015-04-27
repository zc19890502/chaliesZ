package com.csdl.cabexam.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

public class HttpHelper {
	public static final String GET = "GET";
	public static final String POST = "POST";
	private static final Logger logger = Logger.getLogger(HttpHelper.class);

	public static String getNvPairs(List list, String charSet) {
		if (list == null || list.size() == 0) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			String[] nvPairStr = (String[]) list.get(i);
			try {
				if (i > 0) {
					stringBuffer.append("&");
				}
				stringBuffer.append(URLEncoder.encode(nvPairStr[0], charSet))
				.append("=").append(URLEncoder.encode(nvPairStr[1], charSet));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return stringBuffer.toString();
	}

	public static String doHttp(String urlStr, String method, String charSet,
			String postStr, String timeOut) {
		if (method == null
				|| (!GET.equalsIgnoreCase(method) && !POST
						.equalsIgnoreCase(method))) {
			logger.error("无效http方法[" + method + "]");
			return null;
		}
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
		if ("https".equalsIgnoreCase(urlStr.substring(0, 5))) {
			SSLContext sslContext = null;
			try {
				sslContext = SSLContext.getInstance("TLS");
				X509TrustManager xtmArray[] = { new HttpX509TrustManager() };
				sslContext.init(null, xtmArray, new SecureRandom());
			} catch (GeneralSecurityException gse) {
				gse.printStackTrace();
			}
			if (sslContext != null) {
				HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
						.getSocketFactory());
			}
			HttpsURLConnection
			.setDefaultHostnameVerifier(new HttpHostnameVerifier());
		}
		HttpURLConnection httpURLConnection = null;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
		System.setProperty("sun.net.client.defaultConnectTimeout", timeOut);
		System.setProperty("sun.net.client.defaultReadTimeout", timeOut);
		try {
			httpURLConnection.setRequestMethod(method.toUpperCase());
		} catch (ProtocolException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
		if (POST.equalsIgnoreCase(method)) {
			httpURLConnection.setDoOutput(true);
			PrintWriter printWriter = null;
			try {
				printWriter = new PrintWriter(new OutputStreamWriter(
						httpURLConnection.getOutputStream(), charSet));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error(e.toString());
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.toString());
				return null;
			}
			printWriter.write(postStr);
			printWriter.flush();
		}
		InputStream inputStream = null;
		try {
			inputStream = httpURLConnection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int data = 0;
		try {
			int statusCode = httpURLConnection.getResponseCode();
			if (statusCode < HttpURLConnection.HTTP_OK
					|| statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
				logger.error("失败返回码[" + statusCode + "]");
				return null;
			}
			while ((data = inputStream.read()) != -1) {
				byteArrayOutputStream.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
		byte[] returnBytes = byteArrayOutputStream.toByteArray();
		String returnStr = null;
		try {
			returnStr = new String(returnBytes, charSet);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
		return returnStr;
	}

}
