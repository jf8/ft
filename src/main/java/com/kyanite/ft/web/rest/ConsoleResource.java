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
import com.kyanite.ft.service.task.TaskExecutorService;
import com.kyanite.ft.web.rest.vm.RankDataVM;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/c")
public class ConsoleResource {

    private final Logger log = LoggerFactory.getLogger(ConsoleResource.class);

    @Autowired
    ConfService confService;

    @Autowired
    DdBookDeptRepository ddBookDeptRepository;

    @Autowired
    TaskExecutorService taskExecutorService;


    @GetMapping("/sync")
    @Transactional
    public ResponseEntity<RankDataVM> sync() throws ApiException {
        taskExecutorService.execute();
        return ResponseEntity.ok().build();
    }
}
