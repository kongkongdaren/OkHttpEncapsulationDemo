package com.wen.asyl.okhttpencapsulationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wen.asyl.client.CommonOkHttpClient;
import com.wen.asyl.listener.DisposeDataHandler;
import com.wen.asyl.listener.DisposeDataListener;
import com.wen.asyl.request.RequestParams;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLogin,mBtnGetCookie;
    private TextView mTvCookieShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mBtnLogin= (Button) findViewById(R.id.btn_login);
        mBtnGetCookie= (Button) findViewById(R.id.btn_get_cookie);
        mTvCookieShow= (TextView) findViewById(R.id.tv_cookie_show);
        mBtnLogin.setOnClickListener(this);
        mBtnGetCookie.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.btn_login:
                 postRequest();
                 break;
             case R.id.btn_get_cookie:
                 getRequest();
                 break;
         }
    }

    /**
     * 发送一个post请求
     */
    private void postRequest() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "jwj");
        paramMap.put("password", "123456");
        RequestParams params = new RequestParams(paramMap);
        CommonOkHttpClient.post("http://192.168.1.92:8080/OkHttpServer/login", params, new DisposeDataHandler(new DisposeDataListener() {
            @Override
            public void onSuccess(Object response) {
                mTvCookieShow.setText(response.toString());
            }

            @Override
            public void onFailure(Object error) {
                mTvCookieShow.setText(error.toString());
            }
        }));
    }
    /**
     * 发送一个get请求
     */
    private void getRequest() {
        CommonOkHttpClient.get("https://www.imooc.com/video/13050",null,new DisposeDataHandler(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mTvCookieShow.setText(responseObj.toString());
            }

            @Override
            public void onFailure(Object error) {
                mTvCookieShow.setText(error.toString());
            }
        }));
    }


}
