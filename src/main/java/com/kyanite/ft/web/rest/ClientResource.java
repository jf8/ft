package com.kyanite.ft.web.rest;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.kyanite.ft.domain.DdBookDept;
import com.kyanite.ft.domain.DdBookPerson;
import com.kyanite.ft.domain.RankingData;
import com.kyanite.ft.domain.VFtUserSignInfo;
import com.kyanite.ft.domain.enumeration.RankFeild;
import com.kyanite.ft.repository.DdBookDeptRepository;
import com.kyanite.ft.repository.DdBookPersonRepository;
import com.kyanite.ft.service.ConfService;
import com.kyanite.ft.service.DdBookDeptService;
import com.kyanite.ft.web.rest.vm.RankDataVM;
import com.kyanite.ft.web.rest.vm.SearchVM;
import io.github.jhipster.web.util.PaginationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/c")
public class ClientResource {

    private final Logger log = LoggerFactory.getLogger(ClientResource.class);

    @Autowired
    ConfService confService;

    @Autowired
    DdBookDeptRepository ddBookDeptRepository;

    @Autowired
    DdBookPersonRepository ddBookPersonRepository;

    @GetMapping("/rank-data")
    @Transactional
    public ResponseEntity<RankDataVM> getAllDdBookPeople(Long parentId,@RequestParam(defaultValue = "SIGNED")  RankFeild rankFeild) {
        List<VFtUserSignInfo> signInfos = confService.getSignInfos();
        long sTime = System.currentTimeMillis();
        List<DdBookPerson> signedPersonList = signInfos.stream().map(vFtUserSignInfo -> {
            log.debug("vFtUserSignInfo.getDdid():"+vFtUserSignInfo.getDdid());
            Optional<DdBookPerson> byId = ddBookPersonRepository.findById(vFtUserSignInfo.getDdid());
            if(!byId.isPresent()){
                log.debug("用户姓名 {} ，用户userId: {} 在钉钉通讯录中不存在："+vFtUserSignInfo.getNameCn(),vFtUserSignInfo.getDdid());
            }
            return byId;
        }).filter(ddBookPersonOptional -> ddBookPersonOptional.isPresent()).map(ddBookPerson -> ddBookPerson.get()).collect(Collectors.toList());
        log.info("从会议系统回去数据并转换-耗时："+  (System.currentTimeMillis() - sTime) +" ms");
        List<DdBookDept> allByParentid = ddBookDeptRepository.findAllByParent(ddBookDeptRepository.findById(parentId).get());
        log.info("获取父节下的所有子部门-耗时："+  (System.currentTimeMillis() - sTime) +" ms");
        List<RankingData> results = Lists.newArrayList();
        for (DdBookDept ddBookDept : allByParentid) {
            RankingData rankingData = new RankingData();
            rankingData.setName(ddBookDept.getName());
            rankingData.setId(ddBookDept.getId());
            Set<DdBookPerson> everyoneInTheDepartment = getAllChildPersonV2(ddBookDept);
//            getAllChildPerson(ddBookDept,everyoneInTheDepartment);
            rankingData.setTotalPeople(Long.valueOf(everyoneInTheDepartment.size()));
            rankingData.setParentId(ddBookDept.getParent().getId());
            Long signedNum = hitCount(everyoneInTheDepartment, signedPersonList);
            rankingData.setSigndPeople(signedNum);
            rankingData.setIsLeaf(ddBookDept.isIsLeaf());
            if((rankingData.getTotalPeople()!=null&&rankingData.getTotalPeople()>0)){
                BigDecimal attendance = BigDecimal.valueOf(signedNum).divide(BigDecimal.valueOf(rankingData.getTotalPeople()), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                rankingData.setAttendance(attendance);
            }
            results.add(rankingData);
        }
        if(rankFeild==null) {
            rankFeild = RankFeild.SIGNED;
        }
        if(rankFeild.equals(RankFeild.SIGNED)) {
            results.sort(Comparator.comparing(RankingData::getSigndPeople).reversed());
        } else if(rankFeild.equals(RankFeild.RATE)) {
            results.sort(Comparator.comparing(RankingData::getAttendance).reversed());
        }
        RankDataVM vm = new RankDataVM();
        vm.setRankFeild(rankFeild);
        vm.setRankingDataList(results);
        for (int i = 0; i < results.size(); i++) {
            RankingData rankingData = results.get(i);
            rankingData.setOrderNum(Long.valueOf(i));
        }
        return ResponseEntity.ok(vm);
    }



    @GetMapping("/search")
    public ResponseEntity<List<SearchVM>>  search(String name){
        List<DdBookDept> allByNameLike = ddBookDeptRepository.findAllByNameLike("%" + name + "%");
        List<SearchVM> collect = allByNameLike.stream().map(ddBookDept -> {
            SearchVM vm = new SearchVM();
            vm.setId(ddBookDept.getId());
            ArrayList<String> nameList = Lists.newArrayList();
            getParentNamePath(ddBookDept, nameList);
            Collections.reverse(nameList);
            vm.setParentNamePath(StringUtils.join(nameList, "-"));
            vm.setFirstLevelId(getFirstLevelId(ddBookDept));
            return vm;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }


    private void getParentNamePath(DdBookDept ddBookDept , ArrayList<String> nameList){
        nameList.add(ddBookDept.getName());
        DdBookDept parent = ddBookDept.getParent();
        if(parent!=null){
            if(parent.getId()!=1){
                getParentNamePath(ddBookDept.getParent(),nameList);
            }
        }
    }

    private Long getFirstLevelId(DdBookDept ddBookDept){
        DdBookDept parent = ddBookDept.getParent();
        if(parent!=null){
            if(parent.getId()==1){
                return ddBookDept.getId();
            }else{
                return getFirstLevelId(parent);
            }
        }
        return null;
    }


    private void  getAllChildPerson(DdBookDept ddBookDept ,Set<DdBookPerson>  ddBookPersonSet){
        long sTime = System.currentTimeMillis();
        Set<DdBookPerson> ddBookPeople = ddBookDept.getDdBookPeople();
        ddBookPersonSet.addAll(ddBookPeople);
        Set<DdBookDept> children = ddBookDept.getChildren();
        for (DdBookDept child : children) {
            ddBookPersonSet.addAll(child.getDdBookPeople());
            getAllChildPerson(child,ddBookPersonSet);
        }
        log.info("方法：getAllChildPerson-耗时："+  (System.currentTimeMillis() - sTime) +" ms");
    }

    private Set<DdBookPerson> getAllChildPersonV2(DdBookDept ddBookDept){
        long sTime = System.currentTimeMillis();
        Set<DdBookPerson> allByParentDeptsIdListLike = ddBookPersonRepository.findAllByParentDeptsIdListLike("%#" + ddBookDept.getId() + "#%");
        log.info("方法：getAllChildPersonV2-耗时："+  (System.currentTimeMillis() - sTime) +" ms");
        return allByParentDeptsIdListLike;
    }


    private Long countAllByParentDeptsIdListLike(DdBookDept ddBookDept){
        long sTime = System.currentTimeMillis();
        Long count = ddBookPersonRepository.countAllByParentDeptsIdListLike("%#" + ddBookDept.getId() + "#%");
        log.info("方法：countAllByParentDeptsIdListLike-耗时："+  (System.currentTimeMillis() - sTime) +" ms");
        return count;
    }

    private Long hitCount(Set<DdBookPerson> deptPersonList, List<DdBookPerson> signedPersonList){
        long sTime = System.currentTimeMillis();
        Long count = 0L;
        for (DdBookPerson ddBookPerson : deptPersonList) {
            if(signedPersonList.contains(ddBookPerson)){
                count++;
            }
        }
        log.info("方法：hitCount-耗时："+  (System.currentTimeMillis() - sTime) +" ms");
        return count;
    }


}
