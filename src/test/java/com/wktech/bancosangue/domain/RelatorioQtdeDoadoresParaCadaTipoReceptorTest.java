package com.wktech.bancosangue.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioQtdeDoadoresParaCadaTipoReceptorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioQtdeDoadoresParaCadaTipoReceptor.class);
        RelatorioQtdeDoadoresParaCadaTipoReceptor relatorioQtdeDoadoresParaCadaTipoReceptor1 = new RelatorioQtdeDoadoresParaCadaTipoReceptor();
        relatorioQtdeDoadoresParaCadaTipoReceptor1.setId(1L);
        RelatorioQtdeDoadoresParaCadaTipoReceptor relatorioQtdeDoadoresParaCadaTipoReceptor2 = new RelatorioQtdeDoadoresParaCadaTipoReceptor();
        relatorioQtdeDoadoresParaCadaTipoReceptor2.setId(relatorioQtdeDoadoresParaCadaTipoReceptor1.getId());
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptor1).isEqualTo(relatorioQtdeDoadoresParaCadaTipoReceptor2);
        relatorioQtdeDoadoresParaCadaTipoReceptor2.setId(2L);
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptor1).isNotEqualTo(relatorioQtdeDoadoresParaCadaTipoReceptor2);
        relatorioQtdeDoadoresParaCadaTipoReceptor1.setId(null);
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptor1).isNotEqualTo(relatorioQtdeDoadoresParaCadaTipoReceptor2);
    }
}
