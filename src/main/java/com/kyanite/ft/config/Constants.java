package com.kyanite.ft.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "zh-cn";
    public static final String ANONYMOUS_USER = "anonymoususer";


    public static final String ACCESS_TOKEN = "accessToken";
    public static final String JSAPI_TICKET = "jsapiTicket";
    public static final String REPEAT_LOGIN = "repeatLogin";
    public static final String COMMEND_STATUS = "commendStatus";
    public static final String COLLECT_STATUS = "collectStatus";
    public static final String CALC_CYCLE_RESULT_CACHE = "calc-cycle-result-cache";
    public static final String CACHE_NAME = "has-sub-element-cache";
    public static final String DEFAULT_DD_PASSWORD = "123456";
    public static final String BUCKET_NAME = "hq37-vd";
    public static final int ACCESS_TOKEN_SECONDS = 6600; //默认1个小时50分钟
    public static final int JSAPI_TICKET_CACHE_TIME = 6600;
    public static final int REPEAT_LOGIN_CACHE_TIME = 3;
    public static final int COMMEND_STATUS_CACHE_TIME = 86400;
    public static final int COLLECT_STATUS_CACHE_TIME = 86400;
    public static final int CALC_CYCLE_RESULT_CACHE_TIME = 86400;

    public static final String  GOURP_HEADER_URL = "https://static.dingtalk.com/media/%s_600_600.jpg_620x10000q90g.jpg";
    public static final String  GOURP_MESSAGE_PNG_URL = "https://static.dingtalk.com/media/%s_600_300.png_620x10000q90.jpg";
    public static final String  GOURP_MESSAGE_JPG_URL = "https://static.dingtalk.com/media/%s_600_300.jpg_620x10000q90.jpg";


    private Constants() {
    }
}
