package com.kyanite.ft.web.rest.vm;

import com.kyanite.ft.domain.RankingData;
import com.kyanite.ft.domain.enumeration.RankFeild;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class RankDataVM {

    @ApiModelProperty("排序方式")
    RankFeild rankFeild;
    @ApiModelProperty("子数据")
    List<RankingData> rankingDataList;

    public RankFeild getRankFeild() {
        return rankFeild;
    }

    public void setRankFeild(RankFeild rankFeild) {
        this.rankFeild = rankFeild;
    }

    public List<RankingData> getRankingDataList() {
        return rankingDataList;
    }

    public void setRankingDataList(List<RankingData> rankingDataList) {
        this.rankingDataList = rankingDataList;
    }
}
