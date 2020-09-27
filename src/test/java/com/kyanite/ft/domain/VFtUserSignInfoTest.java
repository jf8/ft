package com.kyanite.ft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.ft.web.rest.TestUtil;

public class VFtUserSignInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VFtUserSignInfo.class);
        VFtUserSignInfo vFtUserSignInfo1 = new VFtUserSignInfo();
        vFtUserSignInfo1.setId(1L);
        VFtUserSignInfo vFtUserSignInfo2 = new VFtUserSignInfo();
        vFtUserSignInfo2.setId(vFtUserSignInfo1.getId());
        assertThat(vFtUserSignInfo1).isEqualTo(vFtUserSignInfo2);
        vFtUserSignInfo2.setId(2L);
        assertThat(vFtUserSignInfo1).isNotEqualTo(vFtUserSignInfo2);
        vFtUserSignInfo1.setId(null);
        assertThat(vFtUserSignInfo1).isNotEqualTo(vFtUserSignInfo2);
    }
}
