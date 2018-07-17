package com.wen.asyl.client;

import android.os.Handler;
import android.os.Looper;


import com.wen.asyl.listener.DisposeDataHandler;
import com.wen.asyl.request.CommonRequest;
import com.wen.asyl.request.RequestParams;
import com.wen.asyl.response.CommonJsonCallBack;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
        /**
        * Description：用来发送get，post请求的工具类 <br/>
        * Copyright (c) 2018<br/>
        * This program is protected by copyright laws <br/>
        * Date:2018-07-16 16:02
        *
        * @author 姜文莒
        * @version : 1.0
        */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 5;
    private static OkHttpClient okHttpClient;

    private static Handler mDelieverHandler;

    static {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.followRedirects(true); // 允许自定项

        okHttpClient = okHttpBuilder.build();
        mDelieverHandler = new Handler(Looper.getMainLooper());
    }

    public static void post(String url, RequestParams params, final DisposeDataHandler handler){
        try{
            Request request = CommonRequest.createPostRequest(url, params);
            Call call = okHttpClient.newCall(request);
            call.enqueue(new CommonJsonCallBack(handler));
        }catch (final Exception e){
            mDelieverHandler.post(new Runnable() {
                @Override
                public void run() {
                    handler.mListener.onFailure(e);
                }
            });
        }
    }

    public static void get(String url, RequestParams params, final DisposeDataHandler handler){
        try {
            Request request = CommonRequest.createGetRequest(url, params);
            Call call = okHttpClient.newCall(request);
            call.enqueue(new CommonJsonCallBack(handler));
        }catch (final Exception e){
            mDelieverHandler.post(new Runnable() {
                @Override
                public void run() {
                    handler.mListener.onFailure(e);
                }
            });
        }
    }
}
