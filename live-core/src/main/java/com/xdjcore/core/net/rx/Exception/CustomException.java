package com.xdjcore.core.net.rx.Exception;

import android.net.ParseException;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class CustomException {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;

    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;

    /**
     * 协议错误
     */
    public static final int HTTP_ERROR = 1003;

    public static ApiException handleException(Throwable e) {
        ApiException exception;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //解析错误
            exception = new ApiException(PARSE_ERROR, e.getMessage());
            return exception;
        } else if (e instanceof ConnectException) {
            //网络错误
            exception = new ApiException(NETWORK_ERROR, e.getMessage());
            return exception;
        } else if (e instanceof UnknownHostException || e instanceof SocketException) {
            //连接错误
            exception = new ApiException(HTTP_ERROR, e.getMessage());
            return exception;
        } else {
            exception = new ApiException(UNKNOWN, e.getMessage());
            return exception;
        }


    }


}
