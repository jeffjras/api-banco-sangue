package com.wktech.bancosangue.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioPercObesosHomensDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioPercObesosHomensDTO.class);
        RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO1 = new RelatorioPercObesosHomensDTO();
        relatorioPercObesosHomensDTO1.setId(1L);
        RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO2 = new RelatorioPercObesosHomensDTO();
        assertThat(relatorioPercObesosHomensDTO1).isNotEqualTo(relatorioPercObesosHomensDTO2);
        relatorioPercObesosHomensDTO2.setId(relatorioPercObesosHomensDTO1.getId());
        assertThat(relatorioPercObesosHomensDTO1).isEqualTo(relatorioPercObesosHomensDTO2);
        relatorioPercObesosHomensDTO2.setId(2L);
        assertThat(relatorioPercObesosHomensDTO1).isNotEqualTo(relatorioPercObesosHomensDTO2);
        relatorioPercObesosHomensDTO1.setId(null);
        assertThat(relatorioPercObesosHomensDTO1).isNotEqualTo(relatorioPercObesosHomensDTO2);
    }
}
