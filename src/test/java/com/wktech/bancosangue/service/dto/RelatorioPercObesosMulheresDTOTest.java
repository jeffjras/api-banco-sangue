package com.wktech.bancosangue.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioPercObesosMulheresDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioPercObesosMulheresDTO.class);
        RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO1 = new RelatorioPercObesosMulheresDTO();
        relatorioPercObesosMulheresDTO1.setId(1L);
        RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO2 = new RelatorioPercObesosMulheresDTO();
        assertThat(relatorioPercObesosMulheresDTO1).isNotEqualTo(relatorioPercObesosMulheresDTO2);
        relatorioPercObesosMulheresDTO2.setId(relatorioPercObesosMulheresDTO1.getId());
        assertThat(relatorioPercObesosMulheresDTO1).isEqualTo(relatorioPercObesosMulheresDTO2);
        relatorioPercObesosMulheresDTO2.setId(2L);
        assertThat(relatorioPercObesosMulheresDTO1).isNotEqualTo(relatorioPercObesosMulheresDTO2);
        relatorioPercObesosMulheresDTO1.setId(null);
        assertThat(relatorioPercObesosMulheresDTO1).isNotEqualTo(relatorioPercObesosMulheresDTO2);
    }
}
