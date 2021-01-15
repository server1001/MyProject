package com.example.http;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/11/23 09:51
 */
public class RequestUrlManager {

    public static final String BASE_URL="https://112.17.28.99:10443/aihome";
    public static final String BASE_URL01="https://172.21.42.29:10443";
    //获取token
    public static final String GET_TOKEN_URL="/device-app/user/login/sso";
    //刷新token
    public static final String REFRESH_TOKEN_URL="/device-app/user/login/refresh";
    //上报数据
    public static final String REPORT_DATA_URL="/device-app/device/data/report";

}
