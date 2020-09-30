package com.kyanite.ft.service.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.google.common.collect.Lists;
import com.kyanite.ft.dingtalk.auth.DingtalkAuthService;
import com.kyanite.ft.domain.DdBookDept;
import com.kyanite.ft.domain.DdBookPerson;
import com.kyanite.ft.domain.VFtUserSignInfo;
import com.kyanite.ft.repository.DdBookDeptRepository;
import com.kyanite.ft.service.DdBookDeptService;
import com.kyanite.ft.service.DdBookPersonService;
import com.kyanite.ft.toolbox.service.DataSourceService;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskExecutorService {

    private final Logger log = LoggerFactory.getLogger(TaskExecutorService.class);

    @Autowired
    DingtalkAuthService dingtalkAuthService;

    @Autowired
    DdBookDeptService ddBookDeptService;

    @Autowired
    DdBookDeptRepository ddBookDeptRepository;

    @Autowired
    DdBookPersonService ddBookPersonService;


//    @Scheduled(fixedRate = 1000*5)
    @Transactional
//    @Scheduled(fixedRate = 1000*60*60*1 , fixedDelay = 3000)
    public void execute() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId("1");
        request.setHttpMethod("GET");
        request.setFetchChild(Boolean.TRUE);
        OapiDepartmentListResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        List<OapiDepartmentListResponse.Department> departmentList = response.getDepartment();
        log.info("从钉钉获取部门数量："+ departmentList.size());
        departmentList.sort(Comparator.comparing(OapiDepartmentListResponse.Department::getId));
        for (OapiDepartmentListResponse.Department department : departmentList) {
            DdBookDept ddBookDept = new DdBookDept();
            BeanUtils.copyProperties(department,ddBookDept);
            ddBookDept.setParent(getParent(department.getParentid(), departmentList));
            ddBookDept.setIsLeaf(isLeaf(department.getId(),departmentList));
            ddBookDeptService.save(ddBookDept);
            List<OapiUserListbypageResponse.Userlist> userlists = Lists.newArrayList();
            userListbypage(department.getId(),0L ,userlists);
            for (OapiUserListbypageResponse.Userlist userinfo : userlists) {
                DdBookPerson ddBookPerson = new DdBookPerson();
                BeanUtils.copyProperties(userinfo,ddBookPerson);
                ddBookPerson.setId(userinfo.getUserid());
                ddBookDept.addDdBookPerson(ddBookPerson);
                String parentDeptsIdList = listParentDeptsForPerson(ddBookPerson);
                ddBookPerson.setParentDeptsIdList(parentDeptsIdList);
                ddBookPersonService.save(ddBookPerson);
                ddBookDeptService.save(ddBookDept);
            }
        }
    }

    private Boolean isLeaf(Long id,List<OapiDepartmentListResponse.Department> departmentList){
        for (OapiDepartmentListResponse.Department department : departmentList) {
            if(department.getParentid().equals(id)){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private String  listParentDeptsForPerson(DdBookPerson ddBookPerson) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list_parent_depts");
        OapiDepartmentListParentDeptsRequest request = new OapiDepartmentListParentDeptsRequest();
        request.setUserId(ddBookPerson.getId());
        request.setHttpMethod("GET");
        OapiDepartmentListParentDeptsResponse response = client.execute(request, dingtalkAuthService.getAccessToken());
        String department = response.getDepartment();
        return getNewIds(department);
    }

    public String getNewIds(String ids) {
        JSONArray objects = JSON.parseArray(ids);
        List<List> newIds = Lists.newArrayList();
        for (int i = 0; i < objects.size(); i++) {
            JSONArray line = (JSONArray) objects.get(i);
            List<String> newLine = Lists.newArrayList();
            for (int i1 = 0; i1 < line.size(); i1++) {
                newLine.add("#"+line.get(i1)+"#");
            }
            newIds.add(newLine);
        }
        return JSON.toJSONString(newIds);
    }

    private DdBookDept getParent(Long parentId,List<OapiDepartmentListResponse.Department> departmentList) throws ApiException {
        Optional<DdBookDept> one = ddBookDeptRepository.findById(parentId);
        if(one.isPresent()){
            return one.get();
        }else{
            for (OapiDepartmentListResponse.Department department : departmentList) {
                if(department.getId().equals(parentId)){
                    DdBookDept ddBookDept = new DdBookDept();
                    BeanUtils.copyProperties(department,ddBookDept);
                    ddBookDept.setParent(getParent(department.getParentid(),departmentList));
                    return ddBookDeptService.save(ddBookDept);
                }
            }
            return null;
        }
    }


    private void userListbypage(Long deptId,Long index,List<OapiUserListbypageResponse.Userlist> userlists) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/listbypage");
        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
        request.setDepartmentId(deptId);
        request.setOffset(index);
        request.setSize(100L);
        request.setOrder("entry_desc");
        request.setHttpMethod("GET");
        OapiUserListbypageResponse listbypageResponse = client.execute(request,dingtalkAuthService.getAccessToken());
        userlists.addAll(listbypageResponse.getUserlist());
        if(listbypageResponse.getHasMore()){
            userListbypage(deptId, index + 1, userlists);
        }
    }








}
