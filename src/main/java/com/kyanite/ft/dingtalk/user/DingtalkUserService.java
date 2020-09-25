package com.kyanite.ft.dingtalk.user;


import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.kyanite.ft.dingtalk.auth.DingtalkAuthService;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 通讯录成员相关的接口调用
 */
@Component
public class DingtalkUserService {

    private final Logger log = LoggerFactory.getLogger(DingtalkUserService.class);

    @Autowired
    Environment environment;

    @Autowired
    DingtalkAuthService dingtalkAuthService;




    /**
     * 根据免登授权码查询免登用户userId
     *
     * @param code
     * @return
     * @throws ApiException
     */
    public OapiUserGetuserinfoResponse getUserInfo(String code) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(code);
        request.setHttpMethod("GET");
        return  client.execute(request,dingtalkAuthService.getAccessToken());
    }
    /**
     * 创建企业成员
     * <p>
     * https://open-doc.dingtalk.com/docs/doc.htm?treeId=385&articleId=106816&docType=1#s1
     */
    public OapiUserCreateResponse createUser(OapiUserCreateRequest request) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/create");
        OapiUserCreateResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        return response;
    }


    /**
     * 更新成员
     * <p>
     * https://open-doc.dingtalk.com/docs/doc.htm?treeId=385&articleId=106816&docType=1#s2
     */
    public OapiUserUpdateResponse updateUser(OapiUserUpdateRequest request) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/update");
        OapiUserUpdateResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        return response;
    }


    /**
     * 删除成员
     */
    public OapiUserDeleteResponse deleteUser(OapiUserDeleteRequest request) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/delete");
        return client.execute(request, dingtalkAuthService.getAccessToken());
    }


    //获取成员
    public OapiUserGetResponse getUser(String userId) throws ApiException {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod("GET");
        return client.execute(request, dingtalkAuthService.getAccessToken());


    }






    //批量删除成员
    public OapiUserBatchdeleteResponse batchDeleteUser(OapiUserBatchdeleteRequest request)
        throws ApiException {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/batchdelete");
        return client.execute(request, dingtalkAuthService.getAccessToken());

    }


    //获取部门成员
    public OapiUserSimplelistResponse getDepartmentUser(
            String accessToken,
            long departmentId,
            Long offset,
            Long size,
            String order)
            throws ApiException {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(departmentId);
        request.setOffset(offset);
        request.setSize(size);
        request.setOrder(order);
        request.setHttpMethod("GET");
        return client.execute(request, accessToken);

    }

    //获取部门成员
    public OapiUserListbypageResponse getListbypage(
        String accessToken,
        long departmentId,
        Long offset,
        Long size)
        throws ApiException {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/listbypage");
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        request.setDepartmentId(departmentId);
        request.setOffset(offset);
        request.setSize(size);
        request.setOrder("entry_desc");
        request.setHttpMethod("GET");
        return   client.execute(request,accessToken);


    }





    //获取部门成员（userId）
    public OapiUserGetDeptMemberResponse getDeptMember(String deptId)
        throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getDeptMember");
        OapiUserGetDeptMemberRequest req = new OapiUserGetDeptMemberRequest();
        req.setDeptId(deptId);
        req.setHttpMethod("GET");
        return client.execute(req, dingtalkAuthService.getAccessToken());
    }


    //获取部门成员（简洁）
    public OapiUserSimplelistResponse simplelist(Long deptId, Long offset , Long size)
        throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(deptId);
        request.setOffset(offset);
        request.setSize(size);
        request.setHttpMethod("GET");
        return client.execute(request, dingtalkAuthService.getAccessToken());

    }


    //获取部门成员（详情）
    public OapiUserListResponse list(Long deptId, Long offset , Long size)
            throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/list");
        OapiUserListRequest request = new OapiUserListRequest();
        request.setDepartmentId(deptId);
        request.setOffset(offset);
        request.setSize(size);
        request.setHttpMethod("GET");
        return  client.execute(request, dingtalkAuthService.getAccessToken());

    }


    /**
     * 管理后台免登时通过CODE换取微应用管理员的身份信息
     *
     * @return
     */
    public OapiSsoGetuserinfoResponse getAgentUserInfo(String code) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sso/getuserinfo");
        OapiSsoGetuserinfoRequest request =new OapiSsoGetuserinfoRequest();
        request.setCode(code);
        request.setHttpMethod("GET");
        return client.execute(request, dingtalkAuthService.getSsoToken());
    }





}
