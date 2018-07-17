package com.wen.asyl.listener;

/**
 * Description：xx <br/>
 * Copyright (c) 2018<br/>
 * This program is protected by copyright laws <br/>
 * Date:2018-07-16 15:59
 *
 * @author 姜文莒
 * @version : 1.0
 */
public interface DisposeDataListener {
    public void onSuccess(Object responseObj);
    public void onFailure(Object error);
}
