package com.kaisn.test.javabase;

import com.kaisn.http.JavaWebToken;

public class UserTest {

	public static void main(String[] args) {
//		String encrypt = EncryptUtil.encrypt("asd3135");
//		String jsonString = JSON.toJSONString(new User("testus","asd3134","user"));
        String token = JavaWebToken.createJavaWebToken("12");// ���ݴ����û���id����token�ַ���
		System.out.println(token);
	}

}
