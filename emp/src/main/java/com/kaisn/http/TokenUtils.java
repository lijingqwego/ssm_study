package com.kaisn.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import com.kaisn.pojo.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class TokenUtils {

	public static String createJWT(User user, Date expires, Key key) {
		String jwtString = null;
		if (user != null) {
			// ��ǩ���㷨HS256��˽Կkey����token
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			jwtString = Jwts.builder().setIssuer("Jersey-Security-Basic")// ���÷�����
					.setSubject(user.getUserName())// ���ó�������
					.setAudience(user.getAudience())// ���ý�ɫ
					.setExpiration(expires)// ����ʱ��
					.setIssuedAt(new Date())// ��������ʱ��
					.setId("V.1.0")// �汾1
					.signWith(signatureAlgorithm, key).compact();
		}
		return jwtString;
	}

	/**
	 * 
	 * @param jwsToken
	 * @param key
	 * @return
	 */
	public static String parseJWT(String jwsToken, Key key) {
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
			Claims body = claimsJws.getBody();
			String userName = body.getSubject();
			return userName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��key���л������أ��ӱ��ض�ȡkey
	 * @return
	 */
	public static Key getKey() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Key key = null;
		try {
			File file = new File(TokenUtils.class.getResource("/jwt.key").getPath());
			if (file != null) {
				if (file.exists()) {
					key = MacProvider.generateKey(SignatureAlgorithm.HS512);
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(key);
				}
				ois = new ObjectInputStream(new FileInputStream(file));
				key = (Key) ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return key;
	}

	public static void main(String[] args) {
		Key key = getKey();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 10);
		User user = new User();
		user.setUserName("lijing");
		user.setAudience("admin");
		String token = createJWT(user, c.getTime(), key);
		System.out.println("token��" + token);
		String userName = parseJWT(token, key);
		System.out.println("�û�����" + userName);
	}
}