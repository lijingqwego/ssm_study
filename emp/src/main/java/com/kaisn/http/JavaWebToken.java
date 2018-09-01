package com.kaisn.http;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@SuppressWarnings("restriction")
public class JavaWebToken {

    //�÷���ʹ��HS256�㷨��Secret:bankgl����signKey
    private static Key getKeyInstance() {
        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("bankgl");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    //ʹ��HS256ǩ���㷨�����ɵ�signingKey���յ�Token,claims������Ч�غ�
    public static String createJavaWebToken(String subject) {
        return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    }

    //����Token��ͬʱҲ����֤Token������֤ʧ�ܷ���null
    public static String parserJavaWebToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}