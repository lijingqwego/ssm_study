package com.kaisn.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsUtil {

	private static final class DefaultTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

	private static HttpsURLConnection getHttpsURLConnection(String uri, String method) throws IOException {
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
			ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		SSLSocketFactory ssf = ctx.getSocketFactory();

		URL url = new URL(uri);
		HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
		httpsConn.setSSLSocketFactory(ssf);
		httpsConn.setHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		httpsConn.setRequestMethod(method);
		httpsConn.setDoInput(true);
		httpsConn.setDoOutput(true);
		return httpsConn;
	}

	private static byte[] getBytesFromStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] kb = new byte[1024];
		int len;
		while ((len = is.read(kb)) != -1) {
			baos.write(kb, 0, len);
		}
		byte[] bytes = baos.toByteArray();
		baos.close();
		is.close();
		return bytes;
	}

	private static void setBytesToStream(OutputStream os, byte[] bytes) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		byte[] kb = new byte[1024];
		int len;
		while ((len = bais.read(kb)) != -1) {
			os.write(kb, 0, len);
		}
		os.flush();
		os.close();
		bais.close();
	}

	public static byte[] doGet(String uri) throws IOException {
		HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "GET");
		return getBytesFromStream(httpsConn.getInputStream());
	}

	public static byte[] doPost(String uri, String data) throws IOException {
		HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "POST");
		setBytesToStream(httpsConn.getOutputStream(), data.getBytes());
		return getBytesFromStream(httpsConn.getInputStream());
	}
	
	public static void main(String[] args) throws IOException {
        String uri = "https://cn.bing.com/hpwp/356677bea977a9aa166a92ab94848f17";
        byte[] bytes = HttpsUtil.doGet(uri);
        FileOutputStream fos = new FileOutputStream("D:/bing.picture-of-day.jpg");
        fos.write(bytes);
        fos.close();
        System.out.println("done!");
    }
}