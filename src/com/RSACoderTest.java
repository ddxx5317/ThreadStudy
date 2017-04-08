package com;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * 
 * @author ����
 * @version 1.0
 * @since 1.0
 */
public class RSACoderTest {
	private String publicKey;
	private String privateKey;

	@Before
	public void setUp() throws Exception {
		Map<String, Object> keyMap = RSACoder.initKey();

		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		System.err.println("公钥: \n\r" + publicKey);
		System.err.println("私钥：\n\r" + privateKey);
	}

	@Test
	public void test() throws Exception {
		String inputStr = "abc";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);
		
		System.out.println("密文: " + new String(encodedData,"utf-8") + "\n\r");
		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,privateKey);

		String outputStr = new String(decodedData);
		 System.err.println("解密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

	}

	@Test
	public void testSign() throws Exception {
		String inputStr = "sign";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

		byte[] decodedData = RSACoder
				.decryptByPublicKey(encodedData, publicKey);

		String outputStr = new String(decodedData);
		 System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

		// ����ǩ��
		String sign = RSACoder.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);

		// ��֤ǩ��
		boolean status = RSACoder.verify(encodedData, publicKey, sign);
		System.err.println("״̬:\r" + status);
		assertTrue(status);

	}

}
