package com.kaisn.http;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.kaisn.utils.EncryptUtil;
import com.kaisn.utils.PropertiesUtil;
import com.kaisn.utils.StringUtils;  

public class ClientTrustManager implements X509TrustManager
{  
	
	private X509TrustManager x509TrustManager;
	
    public ClientTrustManager() throws Exception
    {
    	Properties properties = PropertiesUtil.getProperties("/certificateConfig.properties", PropertiesUtil.defaultType);
    	String keyStorePath=properties.getProperty("keyStorePath");
    	String keyStorePassword=properties.getProperty("keyStorePassword");
    	String keyStoreType=properties.getProperty("keyStoreType");
    	if(StringUtils.isNotBlack(keyStorePath) && StringUtils.isNotBlack(keyStorePassword) && StringUtils.isNotBlack(keyStoreType))
    	{
    		//�������
    		keyStorePassword=EncryptUtil.decrypt(keyStorePassword);
    		InputStream inputStream = ClientTrustManager.class.getResourceAsStream(keyStorePath);
        	KeyStore ks = KeyStore.getInstance(keyStoreType);
        	ks.load(inputStream, keyStorePassword.toCharArray());
        	TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        	tmf.init(ks);
        	TrustManager[] tms = tmf.getTrustManagers();
        	for (TrustManager trustManager : tms) 
        	{
    			if(trustManager instanceof X509TrustManager)
    			{
    				x509TrustManager=(X509TrustManager) trustManager;
    			}
    		}
    	}
    }

	public void checkClientTrusted(X509Certificate[] chain, String type) throws CertificateException 
	{
		if(x509TrustManager!=null)
		{
			x509TrustManager.checkServerTrusted(chain, type);
		}
	}

	public void checkServerTrusted(X509Certificate[] chain, String type) throws CertificateException 
	{
		if(x509TrustManager!=null)
		{
			x509TrustManager.checkClientTrusted(chain, type);
		}
	}

	public X509Certificate[] getAcceptedIssuers() 
	{
		if(x509TrustManager!=null)
		{
			return x509TrustManager.getAcceptedIssuers();
		}
		return null;
	}  
}