package com.kaisn.test.javabase;

import com.kaisn.http.JavaWebToken;

public class UserTest {

	public static void main(String[] args) {
//		String encrypt = EncryptUtil.encrypt("asd3135");
//		String jsonString = JSON.toJSONString(new User("testus","asd3134","user"));
        String token = JavaWebToken.createJavaWebToken("12");// 根据存在用户的id生成token字符串
		System.out.println(token);
	}

}
