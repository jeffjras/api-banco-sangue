package com.wktech.bancosangue.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioMediaIdadeTipoSangueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioMediaIdadeTipoSangueDTO.class);
        RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO1 = new RelatorioMediaIdadeTipoSangueDTO();
        relatorioMediaIdadeTipoSangueDTO1.setId(1L);
        RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO2 = new RelatorioMediaIdadeTipoSangueDTO();
        assertThat(relatorioMediaIdadeTipoSangueDTO1).isNotEqualTo(relatorioMediaIdadeTipoSangueDTO2);
        relatorioMediaIdadeTipoSangueDTO2.setId(relatorioMediaIdadeTipoSangueDTO1.getId());
        assertThat(relatorioMediaIdadeTipoSangueDTO1).isEqualTo(relatorioMediaIdadeTipoSangueDTO2);
        relatorioMediaIdadeTipoSangueDTO2.setId(2L);
        assertThat(relatorioMediaIdadeTipoSangueDTO1).isNotEqualTo(relatorioMediaIdadeTipoSangueDTO2);
        relatorioMediaIdadeTipoSangueDTO1.setId(null);
        assertThat(relatorioMediaIdadeTipoSangueDTO1).isNotEqualTo(relatorioMediaIdadeTipoSangueDTO2);
    }
}
