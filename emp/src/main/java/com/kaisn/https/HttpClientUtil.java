package com.kaisn.https;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/* 
 * 利用HttpClient进行post请求的工具类 
 */
public class HttpClientUtil {
    public static String doPost(String url,Map<String,String> map) throws Exception{
    	HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            if(map!=null){
            	//设置参数  
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (Map.Entry<String,String> elem : map.entrySet()) {
                	list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
    			}
                if(list.size() > 0){  
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");  
                    httpPost.setEntity(entity);  
                } 
            }
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,"UTF-8");  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }
    
    public static String doGet(String url) throws Exception{
    	HttpClient httpClient = null;  
        HttpGet httpGet = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,"UTF-8");  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }
    
    public static void main(String[] args){
    	try {
    		Map<String, String> param = new HashMap<String,String>();
    		param.put("token", "RTHS35661sf64se8t4a6d1far8eraddvaD13a54T8E844A6SD54aedukruysTGAdfA");
			System.out.println(doPost("https://localhost:8080/emp/list/1", null));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}