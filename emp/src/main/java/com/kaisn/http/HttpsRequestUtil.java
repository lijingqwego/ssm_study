package com.kaisn.http;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.Consts;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaisn.utils.EncryptUtil;

public class HttpsRequestUtil {
	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	private static SSLConnectionSocketFactory sslConectionSocketFactory = null;
	private static PoolingHttpClientConnectionManager connectionManager = null;
	static {
		try {
			SSLContext ctx=SSLContext.getInstance("TLSv1.2");
			TrustManager[] tms={new ClientTrustManager()};
			ctx.init(null, tms, new SecureRandom());
			sslConectionSocketFactory = new SSLConnectionSocketFactory(ctx,new String[] {"TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslConectionSocketFactory).build();
			connectionManager = new PoolingHttpClientConnectionManager(registry);
			connectionManager.setMaxTotal(200);// max connection
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * httpClient post����
	 * 
	 * @param url
	 *            ����url
	 * @param header
	 *            ͷ����Ϣ
	 * @param param
	 *            ������� form�ύ����
	 * @param entity
	 *            ����ʵ�� json/xml�ύ����
	 * @return ����Ϊ�� ��Ҫ����
	 * @throws Exception
	 *
	 */
	public static String doPost(String url, Map<String, String> header, Map<String, String> param, HttpEntity entity)
			throws Exception {
		String result = "";
		CloseableHttpClient httpClient = null;
		try {
			httpClient = getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			// ����ͷ��Ϣ
			if (null != header) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// �����������
			if (null != param) {
				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : param.entrySet()) {
					// ��������ֵ
					formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
				httpPost.setEntity(urlEncodedFormEntity);
			}
			// ����ʵ�� ���ȼ���
			if (entity != null) {
				httpPost.setEntity(entity);
			}
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = httpResponse.getEntity();
				result = EntityUtils.toString(resEntity);
			} else {
				readHttpResponse(httpResponse);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				httpClient.close();
			}
		}
		return result;
	}
	
	/**
	 * httpClient post����
	 * 
	 * @param url
	 *            ����url
	 * @param header
	 *            ͷ����Ϣ
	 * @param param
	 *            ������� form�ύ����
	 * @param entity
	 *            ����ʵ�� json/xml�ύ����
	 * @return ����Ϊ�� ��Ҫ����
	 * @throws Exception
	 *
	 */
	public static String doGet(String url, Map<String, String> header, HttpEntity entity)
			throws Exception {
		String result = "";
		CloseableHttpClient httpClient = null;
		try {
			httpClient = getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			// ����ͷ��Ϣ
			if (null != header) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// ����ʵ�� ���ȼ���
			if (entity != null) {
				httpPost.setEntity(entity);
			}
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity resEntity = httpResponse.getEntity();
				result = EntityUtils.toString(resEntity);
			} else {
				readHttpResponse(httpResponse);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null) {
				httpClient.close();
			}
		}
		return result;
	}

	public static CloseableHttpClient getHttpClient() throws Exception {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConectionSocketFactory).setConnectionManager(connectionManager)
				.setConnectionManagerShared(true).build();
		return httpClient;
	}

	public static String readHttpResponse(HttpResponse httpResponse) throws ParseException, IOException {
		StringBuilder builder = new StringBuilder();
		// ��ȡ��Ӧ��Ϣʵ��
		HttpEntity entity = httpResponse.getEntity();
		// ��Ӧ״̬
		builder.append("status:" + httpResponse.getStatusLine());
		builder.append("headers:");
		HeaderIterator iterator = httpResponse.headerIterator();
		while (iterator.hasNext()) {
			builder.append("\t" + iterator.next());
		}
		// �ж���Ӧʵ���Ƿ�Ϊ��
		if (entity != null) {
			String responseString = EntityUtils.toString(entity);
			builder.append("response length:" + responseString.length());
			builder.append("response content:" + responseString.replace("\r\n", ""));
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		
		try {
    		Map<String, String> param = new HashMap<String,String>();
    		param.put("userName", "lijing");
    		param.put("password", EncryptUtil.encrypt("asd3135"));
    		String result = doPost("https://192.168.109.129:8081/user/login", null, param, null);
    		System.out.println("���ؽ����"+result);
    		JSONObject jsonObject = JSON.parseObject(result);
    		JSONObject extend = jsonObject.getJSONObject("extend");
    		String token = extend.getString("token");
    		System.out.println("token��"+token);
    		String userId = JavaWebToken.parserJavaWebToken(token);
    		System.out.println("userId��"+userId);
    		/**********************************************************/
    		Map<String, String> header = new HashMap<String,String>();
    		header.put("token", token);
    		String result2 = doGet("https://192.168.109.129:8081/user/tokenConfirm",header,null);
    		System.out.println("���ؽ��2��"+result2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}