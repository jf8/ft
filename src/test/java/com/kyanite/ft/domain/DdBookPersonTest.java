package com.kyanite.ft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.ft.web.rest.TestUtil;

public class DdBookPersonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DdBookPerson.class);
        DdBookPerson ddBookPerson1 = new DdBookPerson();
        ddBookPerson1.setId(1L);
        DdBookPerson ddBookPerson2 = new DdBookPerson();
        ddBookPerson2.setId(ddBookPerson1.getId());
        assertThat(ddBookPerson1).isEqualTo(ddBookPerson2);
        ddBookPerson2.setId(2L);
        assertThat(ddBookPerson1).isNotEqualTo(ddBookPerson2);
        ddBookPerson1.setId(null);
        assertThat(ddBookPerson1).isNotEqualTo(ddBookPerson2);
    }
}
