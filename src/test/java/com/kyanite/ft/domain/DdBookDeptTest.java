package com.kyanite.ft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.ft.web.rest.TestUtil;

public class DdBookDeptTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DdBookDept.class);
        DdBookDept ddBookDept1 = new DdBookDept();
        ddBookDept1.setId(1L);
        DdBookDept ddBookDept2 = new DdBookDept();
        ddBookDept2.setId(ddBookDept1.getId());
        assertThat(ddBookDept1).isEqualTo(ddBookDept2);
        ddBookDept2.setId(2L);
        assertThat(ddBookDept1).isNotEqualTo(ddBookDept2);
        ddBookDept1.setId(null);
        assertThat(ddBookDept1).isNotEqualTo(ddBookDept2);
    }
}
