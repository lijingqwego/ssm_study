package com.kaisn.http;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

import org.apache.http.conn.ssl.DefaultHostnameVerifier;

public class HostnameVerifierUtil {

	public static HostnameVerifier getHostnameVerifier(){
		
		HostnameVerifier hostnameVerifier=new HostnameVerifier() {
			
			public boolean verify(String hostName, SSLSession sesion) {
				try {
					DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier();
					final Certificate[] certificates = sesion.getPeerCertificates();
					final X509Certificate certificate = (X509Certificate)certificates[0];
					hostnameVerifier.verify(hostName, certificate);
				} catch (SSLPeerUnverifiedException e) {
					e.printStackTrace();
				} catch (SSLException e) {
					e.printStackTrace();
				}
				return false;
			}
		};
		return hostnameVerifier;
	}
}
