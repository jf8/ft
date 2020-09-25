package com.kyanite.ft.dingtalk.eventchange;


import com.alibaba.fastjson.JSONObject;
import com.kyanite.ft.toolbox.service.ApiInvokeService;
import com.kyanite.ft.toolbox.service.HttpResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.List;

/**
 * 通讯录回调相关事件
 * <p>
 * https://open-doc.dingtalk.com/docs/doc.htm?treeId=371&articleId=104975&docType=1
 */
public class eventChangeHelper {

    @Autowired
    private Environment environment;

    @Autowired
    ApiInvokeService apiInvokeService;

    String OAPI_HOST= environment.getProperty("dingtalk.OAPI_HOST");

    /**
     * 注册事件回调接口
     */
    public HttpResultDTO registerEventChange(String accessToken, List<String> callBackTag, String token, String aesKey, String url) throws IOException {
        String signUpUrl = OAPI_HOST + "/call_back/register_call_back?" +
                "access_token=" + accessToken;
        JSONObject args = new JSONObject();
        args.put("call_back_tag", callBackTag);
        args.put("token", token);
        args.put("aes_key", aesKey);
        args.put("url", url);
        HttpResultDTO dto = apiInvokeService.doPost(signUpUrl,args);
//        JSONObject response = HttpHelper.httpPost(signUpUrl, args);
        return dto;
    }

    //查询事件回调接口
    public String getEventChange(String accessToken) throws IOException {
        String url = OAPI_HOST + "/call_back/get_call_back?" +
                "access_token=" + accessToken;
        return apiInvokeService.doGet(url);
    }

    //更新事件回调接口
    public  HttpResultDTO updateEventChange(String accessToken, List<String> callBackTag, String token, String aesKey, String url) throws IOException {
        String signUpUrl = OAPI_HOST + "/call_back/update_call_back?" +
                "access_token=" + accessToken;
        JSONObject args = new JSONObject();
        args.put("call_back_tag", callBackTag);
        args.put("token", token);
        args.put("aes_key", aesKey);
        args.put("url", url);

        HttpResultDTO response = apiInvokeService.doPost(signUpUrl, args);
        return response;

    }

    //删除事件回调接口
    public String deleteEventChange(String accessToken) throws IOException {
        String url = OAPI_HOST + "/call_back/delete_call_back?" +
                "access_token=" + accessToken;
        return apiInvokeService.doGet(url);
    }


    public String getFailedResult(String accessToken) throws IOException {
        String url = OAPI_HOST + "/call_back/get_call_back_failed_result?" +
                "access_token=" + accessToken;
        return apiInvokeService.doGet(url);
    }


}
