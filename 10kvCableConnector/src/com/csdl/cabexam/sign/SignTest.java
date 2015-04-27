package com.csdl.cabexam.sign;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SignTest {
	private static final Logger logger = Logger.getLogger(SignTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSign() throws Exception {
		Sign s = new Sign();
		String strData = s.sign("808080123194669", "testdata");
		logger.info("miwen:" + strData);
		logger.info("miwen.length:" + strData.length());
		// fail("Not yet implemented");
	}
	
	@Test
	public void testSign1() throws Exception {
		Sign s = new Sign();
		String merId = "808080050799521";
		String data  = "80808005079952195210003000000811000120100624120600201008111208002010081112080020100816120800";
		String strData = s.sign(merId, data);
		System.out.println(strData);
		//客户签名产生的字符串
		String str1 = "0AE7FFECB3F90C3E7B404CD57EEC201B3960EED8B6430F82D60AEC93203582B8BFE9436953AF70324A583BEE969D286237A9789598787536061CC3D76185D4EBAC95407FE035B7C63858AE625B8B373E44F0D6D8F7D3022BFDFF9537BBCCE2F66B8E7EEBFED22DC2A00D1EA414D80998232A570E7F82BB374E172467D72B40E7";
		System.out.println(str1);
		// fail("Not yet implemented");
	}

}
