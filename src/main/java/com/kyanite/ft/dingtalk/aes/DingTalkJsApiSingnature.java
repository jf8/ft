package com.kyanite.ft.dingtalk.aes;

import java.security.MessageDigest;
import java.util.Formatter;

/**
 * 钉钉jsapi签名工具类
 */
public class DingTalkJsApiSingnature {
    /**
     * 获取jsapi签名
     * @param url
     * @param nonce
     * @param timeStamp
     * @param jsTicket
     * @return
     * @throws DingTalkEncryptException
     */
    public static String getJsApiSingnature(String url, String nonce, Long timeStamp, String jsTicket) throws DingTalkEncryptException {
        String plainTex = "jsapi_ticket=" + jsTicket + "&noncestr=" + nonce + "&timestamp=" + timeStamp + "&url=" + url;
        String signature = "";

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(plainTex.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            return signature;
        } catch (Exception var7) {
            throw new DingTalkEncryptException(900006);
        }
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        byte[] var2 = hash;
        int var3 = hash.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            formatter.format("%02x", b);
        }

        String result = formatter.toString();
        formatter.close();
        return result;
    }


    public static void main1(String args[]) throws Exception {
        String url="http://10.62.53.138:3000/jsapi";
        String nonce="abcdefgh";
        Long timeStamp = 1437027269927L;
        String tikcet="zHoQdGJuH0ZDebwo7sLqLzHGUueLmkWCC4RycYgkuvDu3eoROgN5qhwnQLgfzwEXtuR9SDzh6BdhyVngzAjrxV";
        System.err.println(getJsApiSingnature(url,nonce,timeStamp,tikcet));
    }
}
