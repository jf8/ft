package com.kyanite.ft.service;

import com.google.common.collect.Lists;
import com.kyanite.ft.domain.VFtUserSignInfo;
import com.kyanite.ft.toolbox.service.DataSourceService;
import com.kyanite.ft.web.rest.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;

@Service
public class ConfService {

    private final Logger log = LoggerFactory.getLogger(ConfService.class);

    @Autowired
    @Qualifier("confDataSourceService")
    DataSourceService confDataSourceService;

    @PostConstruct
    public List<VFtUserSignInfo> getSignInfos(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(confDataSourceService.getDataSource());
//        List<VFtUserSignInfo> signInfos  = Lists.newArrayList();
        String sql = "select *  from v_ft_user_sign_info ";
        RowMapper<VFtUserSignInfo> rowMapper = new BeanPropertyRowMapper<>(VFtUserSignInfo.class);
            List<VFtUserSignInfo> signInfos = jdbcTemplate.query(sql,rowMapper);
//        List<VFtUserSignInfo> signInfos = jdbcTemplate.queryForList(sql, rowMapper);
//        jdbcTemplate.query(, rs -> {
//            if(rs.next()){
//                VFtUserSignInfo signInfo = new VFtUserSignInfo();
//                signInfo.setId(rs.getLong("id"));
//                signInfo.setPhoneCode(rs.getString("phone_code"));
//                signInfo.setPhone(rs.getString("phone"));
//                signInfo.setEmail(rs.getString("email"));
//                signInfo.setSeat(rs.getString("seat"));
//                signInfo.setGroupIds(rs.getString("group_ids"));
//                signInfo.setStartTime(getInstant(rs.getTimestamp("start_time")));
//                signInfo.setStartTime(getInstant(rs.getTimestamp("start_time")));
//                signInfo.setEndTime(getInstant(rs.getTimestamp("end_time")));
//                signInfo.setNameCn(rs.getString("name_cn"));
//                signInfo.setNameEn(rs.getString("name_en"));
//                signInfo.setCompanyCn(rs.getString("company_cn"));
//                signInfo.setCompanyEn(rs.getString("company_en"));
//                signInfo.setTitleCn(rs.getString("title_cn"));
//                signInfo.setTitleEn(rs.getString("title_en"));
//                signInfo.setRemark(rs.getString("remark"));
//                signInfo.setDdid(rs.getString("dd_id"));
//                signInfo.setUpdateTime(getInstant(rs.getTimestamp("update_time")));
//                signInfo.setCreateTime(getInstant(rs.getTimestamp("create_time")));
//                signInfo.setSignTime(getInstant(rs.getTimestamp("sign_time")));
//                signInfo.setMeetId(rs.getLong("meet_id"));
//                signInfos.add(signInfo);
//            }
//        });
        return signInfos;
    }



    private Instant getInstant(java.sql.Timestamp time){
        if(time!=null){
            return time.toInstant();
        }
        return null;
    }


}
