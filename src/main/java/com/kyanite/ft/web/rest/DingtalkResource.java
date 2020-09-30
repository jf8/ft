package com.kyanite.ft.web.rest;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiChatSendRequest;
import com.dingtalk.api.request.OapiReportListRequest;
import com.dingtalk.api.response.OapiChatSendResponse;
import com.dingtalk.api.response.OapiReportListResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.google.common.collect.Maps;
import com.kyanite.ft.config.Constants;
import com.kyanite.ft.dingtalk.aes.DingTalkEncryptException;
import com.kyanite.ft.dingtalk.auth.DingtalkAuthService;
import com.kyanite.ft.dingtalk.user.DingtalkUserService;
import com.kyanite.ft.domain.DdUser;
import com.kyanite.ft.domain.User;
import com.kyanite.ft.repository.DdUserRepository;
import com.kyanite.ft.repository.UserRepository;
import com.kyanite.ft.security.jwt.JWTFilter;
import com.kyanite.ft.security.jwt.TokenProvider;
import com.kyanite.ft.service.DdUserService;
import com.taobao.api.ApiException;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.kyanite.ft.config.Constants.DEFAULT_DD_PASSWORD;


@RestController
@RequestMapping("/api")
@CacheConfig(cacheNames = {Constants.REPEAT_LOGIN})
public class DingtalkResource {

    private final Logger log = LoggerFactory.getLogger(com.kyanite.ft.web.rest.DingtalkResource.class);

    @Autowired
    private DingtalkUserService dingtalkUserService;

    @Autowired
    private DingtalkAuthService dingtalkAuthService;

    @Autowired
    private DdUserRepository ddUserRepository;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    DdUserService ddUserService;

    @Autowired
    UserRepository userRepository;


    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;




//    @PostMapping("/register")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void switchAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
//        if (!checkPasswordLength(managedUserVM.getPassword())) {
//            throw new InvalidPasswordException();
//        }
//        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
//        mailService.sendActivationEmail(user);
//    }



    @GetMapping(value = "/dingtalk/jssdk/config",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(tags = "前端接口",value = "JSSDK Config 信息",notes = "此接口只能针对指定的URL授权，#号之前")
    public ResponseEntity getPageConfig(String urlString) throws ApiException, DingTalkEncryptException {
        try {
            return ResponseEntity.ok(dingtalkAuthService.getConfig(urlString));
        } catch (ApiException e) {
            e.printStackTrace();
            throw  e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }


    public String getJWT(String login){
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(login, DEFAULT_DD_PASSWORD);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = true;
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        return jwt;
    }

    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
        if (user.getEmail() != null) {
            Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
        }
    }


    @GetMapping("/dingtalk/login/trust")
    @ApiOperation(value ="个人钉钉账号信任登陆")
    public ResponseEntity trust(String jobCode) throws Exception {

        try {
            long startTime = Instant.now().toEpochMilli();
            String userid = jobCode;
            startTime = Instant.now().toEpochMilli();
            Optional<User> ddUserOptional =userRepository.findOneWithAuthoritiesByLogin(userid);
            DdUser user = new DdUser();
            if(ddUserOptional.isPresent()){
                user = (DdUser) ddUserOptional.get();
                log.info("get user by db: "+(Instant.now().toEpochMilli() - startTime) +"ms");
                ddUserService.updateDdUserFromDingtalk(userid);
            }else{
                //创建用户
                user = ddUserService.createDdUserFromDingtalk(userid);
                clearUserCaches(user);
            }
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getLogin(), DEFAULT_DD_PASSWORD);
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = false;
            String jwt = tokenProvider.createToken(authentication, rememberMe);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            log.info("make user to login : "+(Instant.now().toEpochMilli() - startTime) +"ms");
            log.info("用户「{}」登陆成功",tokenProvider.getAuthentication(jwt).getPrincipal());
            return new ResponseEntity<>(new UserJWTController.JWTToken(jwt), httpHeaders, HttpStatus.OK);
//            return ResponseEntity.ok().build();

        } catch (ApiException e) {
            log.error("钉钉接口异常",e);
        }
        return ResponseEntity.badRequest().build();

    }


    @PostMapping("/dingtalk/login")
    @ApiOperation(value ="个人钉钉账号登陆",tags = "前端接口")
    public ResponseEntity login(String code) throws Exception {
        try {
            long startTime = Instant.now().toEpochMilli();

            if(StringUtils.isBlank(code)){
                throw new Exception("code为空");
            }
            OapiUserGetuserinfoResponse response =  dingtalkUserService.getUserInfo(code);
            if(!response.isSuccess()){
                throw new Exception("获取code失败: "+JSON.toJSONString(response));
            }
            log.info("get userId by code: "+(Instant.now().toEpochMilli() - startTime) +"ms");
            String userid = response.getUserid();
            Cache repeatLoginCache = cacheManager.getCache(Constants.REPEAT_LOGIN);
            Cache.ValueWrapper valueWrapper = repeatLoginCache.get(code);
            if(valueWrapper==null){
                startTime = Instant.now().toEpochMilli();
                Optional<DdUser> ddUserOptional = ddUserRepository.findOneByLogin(userid);
                DdUser user = new DdUser();
                if(ddUserOptional.isPresent()){
                    user = ddUserOptional.get();
                    log.info("get user by db: "+(Instant.now().toEpochMilli() - startTime) +"ms");
                }else{
                    //创建用户
                    user = ddUserService.createDdUserFromDingtalk(response.getUserid());
                }
                UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getLogin(), DEFAULT_DD_PASSWORD);
                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                boolean rememberMe = false;
                String jwt = tokenProvider.createToken(authentication, rememberMe);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
                log.info("make user to login : "+(Instant.now().toEpochMilli() - startTime) +"ms");
                log.info("用户「{}」登陆成功",tokenProvider.getAuthentication(jwt).getPrincipal());
                return new ResponseEntity<>(new UserJWTController.JWTToken(jwt), httpHeaders, HttpStatus.OK);
            } else {
                log.error("登陆接口重复调用，code:{}",valueWrapper.get());
            }

        } catch (ApiException e) {
            log.error("钉钉接口异常",e);
        }
        return ResponseEntity.badRequest().build();

    }




    @PostMapping("/dingtalk/username")
    @Transactional
    @ApiOperation(value ="个人钉钉账号获取名称",tags = "前端接口")
    public ResponseEntity<Map> username(String code) throws Exception {

        try {
            long startTime = Instant.now().toEpochMilli();

            if(StringUtils.isBlank(code)){
                throw new Exception("code 不能为空");
//                return ResponseEntity.badRequest().body(ApiResultVM.failure("1","code 不能为空"));
            }
            OapiUserGetuserinfoResponse response =  dingtalkUserService.getUserInfo(code);
            if(!response.isSuccess()){
                throw new Exception(response.getErrmsg());
//                return ResponseEntity.badRequest().body(ApiResultVM.failure("-1",response.getErrmsg()));
            }
            log.info("get userId by code: "+(Instant.now().toEpochMilli() - startTime) +"ms");
            String userid = response.getUserid();
            OapiUserGetResponse rsp = dingtalkUserService.getUser(userid);
            Map reuslt = Maps.newHashMap();
            reuslt.put("name",rsp.getName());
            reuslt.put("avatar",rsp.getAvatar());
            return ResponseEntity.ok(reuslt);

        } catch (ApiException e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();

    }







    public static void main(String[] args) {
        String accessToken = "d28bbea7adca361c8298de47f2cc7bbb";
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/report/list");
        OapiReportListRequest request = new OapiReportListRequest();
//        request.setUserid("1226682231742708");
        request.setStartTime(System.currentTimeMillis()- TimeUnit.DAYS.toMillis(3));
        request.setEndTime(System.currentTimeMillis());
        request.setTemplateName("统计人员");
        request.setCursor(0L);
        request.setSize(10L);
        try {
            OapiReportListResponse response = client.execute(request, accessToken);
            System.out.println(JSON.toJSONString(response));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }





}
