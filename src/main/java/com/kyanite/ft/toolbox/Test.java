package com.kyanite.ft.toolbox;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {
    public static void main(String[] args) {
        System.out.println(URLEncoder.encode("http://baidu.com"));
        System.out.println(URLDecoder.decode("https%3A%2F%2Foapiuat.fosun.com%2Fdemo%2Foapi_v6_demo.html%3FredirectUrl%3Dhttp%253A%252F%252Fbaidu.com"));
    }
}
