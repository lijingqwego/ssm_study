package com.kaisn.https;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;  
/* 
 * ����HttpClient����post����Ĺ����� 
 */  
public class HttpsUtil {
	
		//����http����  requestUrlΪ�����ַ  requestMethod����ʽ��ֵΪ"GET"��"POST"
		public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
			StringBuffer buffer=null;
			try{
			URL url=new URL(requestUrl);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod(requestMethod);
			conn.connect();
			//����������д���� Ҳ���Ƿ���http������Ҫ���Ĳ���
			if(null!=outputStr){
				OutputStream os=conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}
			
			//��ȡ�������˷��ص�����
			InputStream is=conn.getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"utf-8");
			BufferedReader br=new BufferedReader(isr);
			buffer=new StringBuffer();
			String line=null;
			while((line=br.readLine())!=null){
				buffer.append(line);
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			return buffer.toString();
		}
	/*
	 * ����https GET/POST����
	 * �����ַ�����󷽷�������
	 * */
	public static String httpsRequest(String requestUrl,String requestMethod,String outputStr){
		StringBuffer buffer=null;
		try{
		//����SSLContext
		SSLContext sslContext=SSLContext.getInstance("SSL");
		TrustManager[] tm={new ClientTrustManager()};
		//��ʼ��
		sslContext.init(null, tm, new java.security.SecureRandom());;
		//��ȡSSLSocketFactory����
		SSLSocketFactory ssf=sslContext.getSocketFactory();
		URL url=new URL(requestUrl);
		HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod(requestMethod);
		//���õ�ǰʵ��ʹ�õ�SSLSoctetFactory
		conn.setSSLSocketFactory(ssf);
		//����֤����֤
		conn.setHostnameVerifier(HostnameVerifierUtil.getHostnameVerifier());
		conn.connect();
		//����������д����
		if(null!=outputStr){
			OutputStream os=conn.getOutputStream();
			os.write(outputStr.getBytes("utf-8"));
			os.close();
		}
		
		//��ȡ�������˷��ص�����
		InputStream is=conn.getInputStream();
		InputStreamReader isr=new InputStreamReader(is,"utf-8");
		BufferedReader br=new BufferedReader(isr);
		buffer=new StringBuffer();
		String line=null;
		while((line=br.readLine())!=null){
			buffer.append(line);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	public static void main(String[] args){
//		String s=httpRequest("https://www.cnblogs.com/wangkang0320/p/6874795.html","GET",null);
		String s=httpsRequest("https://localhost:8080/emp/list/1","POST",null);
		//String s=httpRequest("http://www.qq.com","GET",null);
		System.out.println(s);
	}
}