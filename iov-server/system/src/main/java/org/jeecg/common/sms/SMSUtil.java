package org.jeecg.common.sms;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SMSUtil {

    @Value("${sms.url}")
    String url;
    @Value("${sms.uid}")
    String uid;
    @Value("${sms.key}")
    String key;

    CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public void send(String smsMob, String smsText) {
        HttpPost httppost = new HttpPost(url);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("Uid", this.uid));
        formparams.add(new BasicNameValuePair("Key", this.key));
        formparams.add(new BasicNameValuePair("smsMob", smsMob));
        formparams.add(new BasicNameValuePair("smsText", smsText));
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();
        try {
            HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");
            httppost.setEntity(reqEntity);
            httppost.setConfig(requestConfig);
            httpClient.execute(httppost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
