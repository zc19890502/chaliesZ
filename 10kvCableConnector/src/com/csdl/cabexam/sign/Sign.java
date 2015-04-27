package com.csdl.cabexam.sign;

import org.apache.log4j.Logger;

import com.csdl.cabexam.util.GetPath;


public class Sign {
	private static final Logger logger = Logger.getLogger(Sign.class);

	/**
	 * 字符串签名
	 * 
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public String sign(String merId, String data) throws Exception {
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		chinapay.SecureLink t;
		boolean flag;
		String ChkValue2;
		String path = GetPath.getMerPriKeyPath();
		if (path == null || "".equals(path)) {
			logger.error("找不到商户私钥存放路径!");
			return null;
		}
		path += "MerPrK.key";
		logger.error(path);
		System.out.println("merId: "+merId);
		System.out.println("path: "+path);
		flag = key.buildKey(merId, 0, path);
		if (flag == false) {
			logger.error("build key error!");
			return null;
		}
		t = new chinapay.SecureLink(key);
		logger.info(data);
		String data0 = new String(Base64.encode(data.getBytes(("UTF-8"))));
		logger.info(data0);
		ChkValue2 = t.Sign(data0); // Value2为签名后的字符串
		logger.info("sign(" + data0 + ")=" + ChkValue2);
		return ChkValue2;
	}

	/**
	 * 调用CP公钥，验证CP的响应信息
	 * 
	 * @param data
	 * @param chkValue
	 * @return
	 */
	public boolean check(String data, String chkValue) {
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		chinapay.SecureLink t;
		boolean flag;
		String path = GetPath.getChinaPayPubKeyPath();
		if (path == null || "".equals(path)) {
			System.out.println("找不到CP公钥存放路径!");
			return false;
		}
		path += "PgPubk.key";
		flag = key.buildKey("999999999999999", 0, path);
		if (flag == false) {
			System.out.println("build key error!");
			return false;
		}
		String data0 = new String(Base64.encode(data.getBytes()));
		t = new chinapay.SecureLink(key);
		return t.verifyAuthToken(data0, chkValue);
	}

	/**
	 * 
	 * 测试
	 * @throws Exception 
	 */
	public static void main(String args[]) throws Exception {
		//String path = GetPath.getMerPriKeyPath();
		//System.out.println(path);
		
		//String s = "80808029000000100010001315620100401http://bianmin-test.chinapay.com/edumer/PayBg.dohttp://bianmin-test.chinapay.com/edumer/PayPg.do1106201116570000000100010001^1;00010002^1;00010003^1;测试订单127.0.0.1";
		//String data0 = new String(Base64.encode(s.getBytes("UTF-8")));
		//String data1 = new String(Base64.encode(s.getBytes()));
		//System.out.println(data0);
		//System.out.println(data1);
		
		//Sign sign = new Sign();
		//sign.sign("808080290000001", s);

	}
}
