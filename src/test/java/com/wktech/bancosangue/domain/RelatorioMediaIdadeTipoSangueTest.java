package com.wktech.bancosangue.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioMediaIdadeTipoSangueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioMediaIdadeTipoSangue.class);
        RelatorioMediaIdadeTipoSangue relatorioMediaIdadeTipoSangue1 = new RelatorioMediaIdadeTipoSangue();
        relatorioMediaIdadeTipoSangue1.setId(1L);
        RelatorioMediaIdadeTipoSangue relatorioMediaIdadeTipoSangue2 = new RelatorioMediaIdadeTipoSangue();
        relatorioMediaIdadeTipoSangue2.setId(relatorioMediaIdadeTipoSangue1.getId());
        assertThat(relatorioMediaIdadeTipoSangue1).isEqualTo(relatorioMediaIdadeTipoSangue2);
        relatorioMediaIdadeTipoSangue2.setId(2L);
        assertThat(relatorioMediaIdadeTipoSangue1).isNotEqualTo(relatorioMediaIdadeTipoSangue2);
        relatorioMediaIdadeTipoSangue1.setId(null);
        assertThat(relatorioMediaIdadeTipoSangue1).isNotEqualTo(relatorioMediaIdadeTipoSangue2);
    }
}
