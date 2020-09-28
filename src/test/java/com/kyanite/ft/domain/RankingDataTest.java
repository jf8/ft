package com.kyanite.ft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.ft.web.rest.TestUtil;

public class RankingDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RankingData.class);
        RankingData rankingData1 = new RankingData();
        rankingData1.setId(1L);
        RankingData rankingData2 = new RankingData();
        rankingData2.setId(rankingData1.getId());
        assertThat(rankingData1).isEqualTo(rankingData2);
        rankingData2.setId(2L);
        assertThat(rankingData1).isNotEqualTo(rankingData2);
        rankingData1.setId(null);
        assertThat(rankingData1).isNotEqualTo(rankingData2);
    }
}
