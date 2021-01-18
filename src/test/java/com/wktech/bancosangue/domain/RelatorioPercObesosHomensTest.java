package com.wktech.bancosangue.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioPercObesosHomensTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioPercObesosHomens.class);
        RelatorioPercObesosHomens relatorioPercObesosHomens1 = new RelatorioPercObesosHomens();
        relatorioPercObesosHomens1.setId(1L);
        RelatorioPercObesosHomens relatorioPercObesosHomens2 = new RelatorioPercObesosHomens();
        relatorioPercObesosHomens2.setId(relatorioPercObesosHomens1.getId());
        assertThat(relatorioPercObesosHomens1).isEqualTo(relatorioPercObesosHomens2);
        relatorioPercObesosHomens2.setId(2L);
        assertThat(relatorioPercObesosHomens1).isNotEqualTo(relatorioPercObesosHomens2);
        relatorioPercObesosHomens1.setId(null);
        assertThat(relatorioPercObesosHomens1).isNotEqualTo(relatorioPercObesosHomens2);
    }
}
