package com.wktech.bancosangue.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioQtdCandPorEstadoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioQtdCandPorEstadoDTO.class);
        RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO1 = new RelatorioQtdCandPorEstadoDTO();
        relatorioQtdCandPorEstadoDTO1.setId(1L);
        RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO2 = new RelatorioQtdCandPorEstadoDTO();
        assertThat(relatorioQtdCandPorEstadoDTO1).isNotEqualTo(relatorioQtdCandPorEstadoDTO2);
        relatorioQtdCandPorEstadoDTO2.setId(relatorioQtdCandPorEstadoDTO1.getId());
        assertThat(relatorioQtdCandPorEstadoDTO1).isEqualTo(relatorioQtdCandPorEstadoDTO2);
        relatorioQtdCandPorEstadoDTO2.setId(2L);
        assertThat(relatorioQtdCandPorEstadoDTO1).isNotEqualTo(relatorioQtdCandPorEstadoDTO2);
        relatorioQtdCandPorEstadoDTO1.setId(null);
        assertThat(relatorioQtdCandPorEstadoDTO1).isNotEqualTo(relatorioQtdCandPorEstadoDTO2);
    }
}
