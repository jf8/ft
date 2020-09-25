package com.kyanite.ft.dingtalk.department;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.kyanite.ft.dingtalk.auth.DingtalkAuthService;
import com.kyanite.ft.toolbox.service.ApiInvokeService;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门相关API
 *
 * https://open-doc.dingtalk.com/docs/doc.htm?treeId=371&articleId=106817&docType=1
 */
@Component
public class DingtalkDeptService {

    private final Logger log = LoggerFactory.getLogger(DingtalkDeptService.class);

    @Autowired
    DingtalkAuthService dingtalkAuthService;

    @Autowired
    Environment environment;

    @Autowired
    ApiInvokeService apiInvokeService;

    /**
     *  创建部门
     */
    public OapiDepartmentCreateResponse createDepartment(String name,
                                                         String parentId, String order, boolean createDeptGroup) throws ApiException {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/create");
        OapiDepartmentCreateRequest request = new OapiDepartmentCreateRequest();
        request.setParentid(parentId);
        request.setCreateDeptGroup(createDeptGroup);
        request.setOrder(order);
        request.setName(name);
        OapiDepartmentCreateResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        return response;
    }

    /**
     * 获取部门列表
     */
    public OapiDepartmentListResponse listDepartments(String parentDeptId) throws ApiException {

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(parentDeptId);
        request.setHttpMethod("GET");
        OapiDepartmentListResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        return response;

    }


    /**
     * 删除部门
     */
    public OapiDepartmentDeleteResponse deleteDepartment(Long id) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/delete");
        OapiDepartmentDeleteRequest request = new OapiDepartmentDeleteRequest();
        request.setId("123");
        request.setHttpMethod("GET");
        OapiDepartmentDeleteResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        return response;
    }

    /**
     * 更新部门
     */
    public OapiDepartmentUpdateResponse updateDepartment(long id, String name,
                                                         String parentId, String order, Boolean createDeptGroup,
                                                         boolean autoAddUser, String deptManagerUseridList, boolean deptHiding, String deptPerimits,
                                                         String userPerimits, Boolean outerDept, String outerPermitDepts,
                                                         String outerPermitUsers, String orgDeptOwner) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/update");
        OapiDepartmentUpdateRequest request = new OapiDepartmentUpdateRequest();
        request.setId(id);
        request.setParentid(parentId);
        request.setOrder(order);
        request.setName(name);
        request.setCreateDeptGroup(createDeptGroup);
        request.setAutoAddUser(autoAddUser);
        request.setDeptManagerUseridList(deptManagerUseridList);
        request.setDeptHiding(deptHiding);
        request.setDeptPerimits(deptPerimits);
        request.setUserPerimits(userPerimits);
        request.setOuterDept(outerDept);
        request.setOuterPermitDepts(outerPermitDepts);
        request.setOuterPermitUsers(outerPermitUsers);
        request.setOrgDeptOwner(orgDeptOwner);
        OapiDepartmentUpdateResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        return response;

    }

    /**
     * 更新部门
     */
    public OapiDepartmentUpdateResponse updateDepartment(OapiDepartmentUpdateRequest request) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/update");
        OapiDepartmentUpdateResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        return response;

    }

//    /**
//     * 更新部门
//     */
//    public  void updateDepartment(DingTalkDepartment detail) throws Exception {
//        CorpDepartmentService corpDepartmentService = ServiceFactory.getInstance().getOpenService(CorpDepartmentService.class);
//        corpDepartmentService.deptUpdate(authHelper.getAccessToken(), detail.getId(), detail.getName(), detail.getParentid(), detail.getOrder(), detail.isCreateDeptGroup(),
//            detail.isAutoAddUser(), detail.getDeptManagerUseridList(),  detail.isDeptHiding(),  detail.getDeptPerimits(), detail.getUserPerimits() ,
//            detail.isOuterDept() , detail.getOuterPermitDepts(), detail.getOuterPermitUsers() , detail.getOrgDeptOwner());
//
//    }


    /**
     * 更新部门是否隐藏状态
     */
    public Boolean updateDepartmentDeptHiding(Long id, Boolean isDeptHiding) throws ApiException {
        OapiDepartmentUpdateRequest request  =new OapiDepartmentUpdateRequest();
        request.setId(id);
        request.setDeptHiding(isDeptHiding);
        OapiDepartmentUpdateResponse rsp = updateDepartment(request);
        if(log.isInfoEnabled()){
            OapiDepartmentGetResponse departmentDetail = this.getDeptDetailBySdk(id.toString());
            log.info("更新钉钉部门信息，入参数：dept:{},isDeptHiding:{} ,出参:{}" , JSON.toJSONString(departmentDetail),isDeptHiding, JSON.toJSONString(rsp));
        }
        if(rsp.getErrcode()==0){
           return Boolean.TRUE;
        }else{
            log.error("更新钉钉部门隐藏状态时出错："+id);
        }
        return Boolean.FALSE;
    }






    /**
     * 获取部门详情
     */
    public OapiDepartmentGetResponse getDeptDetailBySdk(String id) throws ApiException {


        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/get");
        OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
        request.setId(id);
        request.setHttpMethod("GET");
        OapiDepartmentGetResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        log.debug("获取部门信息：{}",JSON.toJSONString(response));
        return response;

    }

    public List<Long> listParentDeptsByDept(String id) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list_parent_depts_by_dept");
        OapiDepartmentListParentDeptsByDeptRequest request = new OapiDepartmentListParentDeptsByDeptRequest();
        request.setId(id);
        request.setHttpMethod("GET");
        OapiDepartmentListParentDeptsByDeptResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        if(response.isSuccess()){
            return response.getParentIds();
        }
        return null;
    }

    public String listParentNameDeptsByDept(String id) throws ApiException {
        List<Long> ids =  listParentDeptsByDept(id);
        Collections.reverse(ids);
        List<String> names = ids.stream().map(aLong -> {
            try {
                return getDeptDetailBySdk(aLong+"").getName();
            } catch (ApiException e) {
                e.printStackTrace();
            }
            return "";
        }).collect(Collectors.toList());
       return StringUtils.join(names,"->");
    }




}
