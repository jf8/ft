package com.kyanite.ft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.ft.web.rest.TestUtil;

public class LiveSharingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LiveSharing.class);
        LiveSharing liveSharing1 = new LiveSharing();
        liveSharing1.setId(1L);
        LiveSharing liveSharing2 = new LiveSharing();
        liveSharing2.setId(liveSharing1.getId());
        assertThat(liveSharing1).isEqualTo(liveSharing2);
        liveSharing2.setId(2L);
        assertThat(liveSharing1).isNotEqualTo(liveSharing2);
        liveSharing1.setId(null);
        assertThat(liveSharing1).isNotEqualTo(liveSharing2);
    }
}
