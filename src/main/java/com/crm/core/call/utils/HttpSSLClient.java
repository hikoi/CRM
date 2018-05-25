package com.crm.core.call.utils;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by xiuxiu on 2017/4/25.
 */
public class HttpSSLClient {

    public static CloseableHttpClient createDefault(){
        try{
            TrustStrategy trust = new TrustStrategy(){
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException{
                    return true;
                }
            };

            SSLContext context = new SSLContextBuilder().loadTrustMaterial(null, trust).build();

            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(context);

            return HttpClients.custom().setSSLSocketFactory(factory).build();
        }catch(KeyManagementException e){
            e.printStackTrace();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(KeyStoreException e){
            e.printStackTrace();
        }
        return  HttpClients.createDefault();
    }
}
