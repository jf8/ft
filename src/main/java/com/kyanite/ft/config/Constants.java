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


    public static final String ACCESS_TOKEN = "ft_accessToken";
    public static final String JSAPI_TICKET = "ft_jsapiTicket";
    public static final String REPEAT_LOGIN = "ft_repeatLogin";
    public static final String DEFAULT_DD_PASSWORD = "123456";
    public static final int ACCESS_TOKEN_SECONDS = 6600; //默认1个小时50分钟
    public static final int JSAPI_TICKET_CACHE_TIME = 6600;
    public static final int REPEAT_LOGIN_CACHE_TIME = 3;


    private Constants() {
    }
}
