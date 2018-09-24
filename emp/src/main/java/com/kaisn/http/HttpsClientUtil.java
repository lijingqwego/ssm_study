package com.kaisn.http;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaisn.utils.EncryptUtil;
import com.kaisn.utils.StringUtils;

/* 
 * 利用HttpClient进行post请求的工具类 
 */
public class HttpsClientUtil {
    public static String doPost(String url,Map<String,String> map,String token) throws Exception{
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
            if(StringUtils.isNotBlack(token)){
            	httpPost.addHeader("token", token);
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
    		param.put("userName", "lijing");
    		param.put("password", EncryptUtil.encrypt("asd3135"));
    		String result = doPost("http://192.168.109.129:8080/user/login", param,null);
    		System.out.println("返回结果："+result);
    		JSONObject jsonObject = JSON.parseObject(result);
    		JSONObject extend = jsonObject.getJSONObject("extend");
    		String token = extend.getString("token");
    		System.out.println("token："+token);
    		String userId = JavaWebToken.parserJavaWebToken(token);
    		System.out.println("userId："+userId);
    		String result2 = doGet("http://192.168.109.129:8080/emp/info/102");
    		System.out.println("返回结果2："+result2);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}