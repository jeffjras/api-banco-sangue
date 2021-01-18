package com.wktech.bancosangue.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioQtdCandPorEstadoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioQtdCandPorEstado.class);
        RelatorioQtdCandPorEstado relatorioQtdCandPorEstado1 = new RelatorioQtdCandPorEstado();
        relatorioQtdCandPorEstado1.setId(1L);
        RelatorioQtdCandPorEstado relatorioQtdCandPorEstado2 = new RelatorioQtdCandPorEstado();
        relatorioQtdCandPorEstado2.setId(relatorioQtdCandPorEstado1.getId());
        assertThat(relatorioQtdCandPorEstado1).isEqualTo(relatorioQtdCandPorEstado2);
        relatorioQtdCandPorEstado2.setId(2L);
        assertThat(relatorioQtdCandPorEstado1).isNotEqualTo(relatorioQtdCandPorEstado2);
        relatorioQtdCandPorEstado1.setId(null);
        assertThat(relatorioQtdCandPorEstado1).isNotEqualTo(relatorioQtdCandPorEstado2);
    }
}
