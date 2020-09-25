package com.kyanite.ft.toolbox.md5;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ApiSign {
    private String apiKey;
    private String timestamp;
    private String sign;

    public ApiSign() {
    }

    public ApiSign(String apiKey, String timestamp, String sign) {
        this.apiKey = apiKey;
        this.timestamp = timestamp;
        this.sign = sign;
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean isValid(String apiKey , String apiSecret){

        String inputUsername = this.apiKey;
        String inputUserTime = this.timestamp;
       if(inputUsername.equals(apiKey)){
           String md5Source = inputUsername.concat(inputUserTime);
           md5Source = md5Source.concat(apiSecret);
           String md5Sec = encode(md5Source);
           if(md5Sec.equals(this.sign)){
               return Boolean.TRUE;
           } else {
               return Boolean.FALSE;
           }
       }else{
            return Boolean.FALSE;
       }

    }

    private static String encode(byte[] data) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(data, 0, data.length);
            return new String(Hex.encodeHex(md5.digest()));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String encode(String data) {
        try {
            return encode(data.getBytes(StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    public static ApiSign buildVerifyApi(String apiKey, String timestamp, String apiSecret) {

        String md5Source = apiKey.concat(timestamp);
        md5Source = md5Source.concat(apiSecret);
        String md5Sec = encode(md5Source);

        String usernameEncode = apiKey;
        String userTimeEncode = timestamp;
        return new ApiSign(
            usernameEncode,
            userTimeEncode,
            md5Sec
        );
    }

    public static void main(String[] args) {
//        ApiSign sign = ApiSign.buildVerifyApi("8Hq5jpx1JhlY8Plni8eHNKIa", Instant.now().toEpochMilli() + "", "gOD3pfACHZNvxl7NqHoWHzJFS0ATZO9o");
        ApiSign sign = ApiSign.buildVerifyApi("8Hq5jpx1JhlY8Plni8eHNKIa",  "1597023693784", "gOD3pfACHZNvxl7NqHoWHzJFS0ATZO9o");
        System.out.println(JSON.toJSONString(sign));
    }

}
