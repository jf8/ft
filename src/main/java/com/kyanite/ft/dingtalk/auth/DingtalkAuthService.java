package com.kyanite.ft.dingtalk.auth;


import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGetJsapiTicketRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiSsoGettokenRequest;
import com.dingtalk.api.response.OapiGetJsapiTicketResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiSsoGettokenResponse;
import com.kyanite.ft.config.Constants;
import com.kyanite.ft.dingtalk.aes.DingTalkEncryptException;
import com.kyanite.ft.dingtalk.aes.DingTalkJsApiSingnature;
import com.kyanite.ft.dingtalk.aes.DingTalkJssdkConfig;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;


/**
 * AccessToken和jsticket的获取封装
 */
@Component
@CacheConfig(cacheNames = {Constants.ACCESS_TOKEN, Constants.JSAPI_TICKET})
public class DingtalkAuthService {

    private final Logger log = LoggerFactory.getLogger(DingtalkAuthService.class);

    @Autowired
    private Environment environment;


    @Autowired
    CacheManager cacheManager;


    /*
     * 在此方法中，为了避免频繁获取access_token，
     * 在距离上一次获取access_token时间在两个小时之内的情况，
     * 将直接从持久化存储中读取access_token
     *
     * 因为access_token和jsapi_ticket的过期时间都是7200秒
     * 所以在获取access_token的同时也去获取了jsapi_ticket
     * 注：jsapi_ticket是在前端页面JSAPI做权限验证配置的时候需要使用的
     * 具体信息请查看开发者文档--权限验证配置
     */
    public String getAccessToken() throws ApiException {

        long curTime = System.currentTimeMillis();
        Cache accessTokenCache = cacheManager.getCache(Constants.ACCESS_TOKEN);
        Cache.ValueWrapper valueWrapper =  accessTokenCache.get(environment.getProperty("dingtalk.APP_KEY"));
        String accToken = "";

        if (valueWrapper==null) {
            DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
//            request.setCorpid(environment.getProperty("dingtalk.CORP_ID"));
//            request.setCorpsecret(environment.getProperty("dingtalk.CORP_SECRET"));
            request.setAppkey(environment.getProperty("dingtalk.APP_KEY"));
            request.setAppsecret(environment.getProperty("dingtalk.APP_SECRET"));
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            accToken=response.getAccessToken();
            JSONObject jsontemp = new JSONObject();
            jsontemp.clear();
            jsontemp.put("access_token", accToken);
            jsontemp.put("begin_time", curTime);
            //真实项目中最好保存到数据库中
            cacheManager.getCache(Constants.ACCESS_TOKEN).put(environment.getProperty("dingtalk.APP_KEY"), jsontemp);
        } else {
            JSONObject accessTokenValue =  (JSONObject) valueWrapper.get();
            return accessTokenValue.getString("access_token");
        }
        return accToken;
    }

    /**
     * 获取JSTicket, 用于js的签名计算
     * 正常的情况下，jsapi_ticket的有效期为7200秒，所以开发者需要在某个地方设计一个定时器，定期去更新jsapi_ticket
     */
    public String getJsapiTicket(String accessToken) throws ApiException {

//        JSONObject jsTicketValue = (JSONObject) FileUtils.getValue("jsticket", environment.getProperty("dingtalk.CORP_ID"));

        Cache jsTicketCache = cacheManager.getCache(Constants.JSAPI_TICKET);
        Cache.ValueWrapper valueWrapper =  jsTicketCache.get(environment.getProperty("dingtalk.APP_KEY"));
        long curTime = System.currentTimeMillis();
        String jsTicket = "";

        if (valueWrapper == null) {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/get_jsapi_ticket");
            OapiGetJsapiTicketRequest req = new OapiGetJsapiTicketRequest();
            req.setHttpMethod("GET");
            OapiGetJsapiTicketResponse rsp = client.execute(req, accessToken);
            if(rsp.getErrcode()==0){
                jsTicketCache.put(environment.getProperty("dingtalk.APP_KEY"),rsp);
                return rsp.getTicket();
            }else{
                throw new ApiException(rsp.getErrmsg());
            }
        } else {
            OapiGetJsapiTicketResponse rsp =  (OapiGetJsapiTicketResponse) valueWrapper.get();
            return rsp.getTicket();
        }
    }


    public static String sign(String ticket, String nonceStr, long timeStamp, String url) throws  DingTalkEncryptException {
        return DingTalkJsApiSingnature.getJsApiSingnature(url, nonceStr, timeStamp, ticket);
    }


    /**
     * 计算当前请求的jsapi的签名数据<br/>
     * <p>
     * 如果签名数据是通过ajax异步请求的话，签名计算中的url必须是给用户展示页面的url
     *
     * @param request
     * @return
     */
    public String getConfig(HttpServletRequest request) throws ApiException, DingTalkEncryptException {
        String urlString = request.getRequestURL().toString();
        String queryString = request.getQueryString();

        String queryStringEncode = null;
        String url;
        if (queryString != null) {
            queryStringEncode = URLDecoder.decode(queryString);
            url = urlString + "?" + queryStringEncode;
        } else {
            url = urlString;
        }

        String nonceStr = "abcdefg";
        long timeStamp = System.currentTimeMillis() / 1000;
        String signedUrl = url;
        String accessToken = null;
        String ticket = null;
        String signature = null;
        String agentid = null;

        accessToken = getAccessToken();
        ticket = getJsapiTicket(accessToken);
        signature = DingtalkAuthService.sign(ticket, nonceStr, timeStamp, signedUrl);
        agentid = "";
        String configValue = "{jsticket:'" + ticket + "',signature:'" + signature + "',nonceStr:'" + nonceStr + "',timeStamp:'"
                + timeStamp + "',corpId:'" + environment.getProperty("dingtalk.CORP_ID") + "',agentid:'" + agentid + "'}";
        return configValue;
    }


    public DingTalkJssdkConfig getConfig(String url) throws ApiException, DingTalkEncryptException {
        String agentid = environment.getProperty("dingtalk.agentid");
        return getConfig(url,agentid);
    }

    public DingTalkJssdkConfig getConfig(String url, String agentid) throws ApiException, DingTalkEncryptException {
        String nonceStr = "abcdefg";
        long timeStamp = System.currentTimeMillis() / 1000;
        String signedUrl = url;
        String accessToken = null;
        String ticket = null;
        String signature = null;

        accessToken = getAccessToken();
        ticket = getJsapiTicket(accessToken);
        signature = DingtalkAuthService.sign(ticket, nonceStr, timeStamp, signedUrl);

        DingTalkJssdkConfig config =new DingTalkJssdkConfig();
        config.setAgentid(agentid);
        config.setCorpId(environment.getProperty("dingtalk.CORP_ID"));
        config.setJsticket(ticket);
        config.setSignature(signature);
        config.setNonceStr(nonceStr);
        config.setTimeStamp(timeStamp);
        return config;
    }

    public String getSsoToken() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sso/gettoken");
        OapiSsoGettokenRequest rqs = new OapiSsoGettokenRequest();
        rqs.setCorpid(environment.getProperty("dingtalk.CORP_ID"));
        rqs.setCorpsecret(environment.getProperty("dingtalk.SSO_Secret"));
        rqs.setHttpMethod("GET");
        OapiSsoGettokenResponse rsp = client.execute(rqs);
        if(rsp.getErrcode()==0){
            return rsp.getAccessToken();
        }else {
            throw new ApiException(rsp.getMessage());
        }

    }





}
