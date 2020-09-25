package com.kyanite.ft.dingtalk.approval;

import com.kyanite.ft.dingtalk.auth.DingtalkAuthService;
import com.kyanite.ft.dingtalk.user.DingtalkUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service
public class DingtalkApprovalService {


    private final Logger log = LoggerFactory.getLogger(DingtalkApprovalService.class);


    @Autowired
    private Environment env;

    @Autowired
    DingtalkAuthService dingtalkAuthService;

    @Autowired
    DingtalkUserService dingtalkUserService;







}
