package com.kaisn.http;

import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class SSLClient extends DefaultHttpClient{
	
	public SSLClient() throws Exception{
		super();
		SSLContext ctx=SSLContext.getInstance("TLSv1.2");
		TrustManager[] tms={new ClientTrustManager()};
		ctx.init(null, tms, new SecureRandom());
		SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = this.getConnectionManager();
		SchemeRegistry registry = ccm.getSchemeRegistry();
		registry.register(new Scheme("https", 443, ssf));
	}

}
