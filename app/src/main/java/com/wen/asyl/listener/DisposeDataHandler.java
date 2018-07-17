package com.wen.asyl.listener;

/**
 * Description：xx <br/>
 * Copyright (c) 2018<br/>
 * This program is protected by copyright laws <br/>
 * Date:2018-07-16 16:02
 *
 * @author 姜文莒
 * @version : 1.0
 */
public class DisposeDataHandler {
    public DisposeDataListener mListener=null;
    public Class<?> mClass=null;

    public DisposeDataHandler(DisposeDataListener listener) {
        this.mListener = listener;
    }

    public DisposeDataHandler(DisposeDataListener listener, Class<?> clazz) {
        this.mListener = listener;
        this.mClass = clazz;
    }
}
