package com.wen.asyl.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description：负责创建各种类型的请求对象，包括get,post,文件上传类型<br/>
 * Copyright (c) 2018<br/>
 * This program is protected by copyright laws <br/>
 * Date:2018-07-16 16:06
 *
 * @author 姜文莒
 * @version : 1.0
 */
public class CommonRequest {
    /**
     * 创建get请求
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params){
        StringBuilder urlBuilder = new StringBuilder();
        if(params != null){
            for(Map.Entry<String, String> entry : params.urlParams.entrySet()){
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }
        int end = urlBuilder.length() - 1;
        String fullUrl = url + urlBuilder.substring(0, (end > 0 ? end : 0)).toString();
        return new Request.Builder()
                .url(fullUrl)
                .get()
                .build();
    }
    /**
     * 创建post请求
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params){
        FormBody.Builder builder = new FormBody.Builder();
        if(params != null){
            for(Map.Entry<String, String> entry : params.urlParams.entrySet()){
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody body = builder.build();
        return new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }

    /**
     * 创建上传文件请求
     */
    public static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");
    public static Request createMultiPostRequest(String url, RequestParams params){
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if(params != null){
            for(Map.Entry<String, Object> entry : params.fileParams.entrySet()){
                if(entry.getValue() instanceof File){
                    builder.addPart(MultipartBody.Part.createFormData(entry.getKey(),
                            null, RequestBody.create(FILE_TYPE, (File)entry.getValue())));
                }else{
                    builder.addFormDataPart(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
        }
        return new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
    }

}
