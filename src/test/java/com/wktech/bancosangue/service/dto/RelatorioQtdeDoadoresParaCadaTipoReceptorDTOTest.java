package com.wktech.bancosangue.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioQtdeDoadoresParaCadaTipoReceptorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioQtdeDoadoresParaCadaTipoReceptorDTO.class);
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO1 = new RelatorioQtdeDoadoresParaCadaTipoReceptorDTO();
        relatorioQtdeDoadoresParaCadaTipoReceptorDTO1.setId(1L);
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO2 = new RelatorioQtdeDoadoresParaCadaTipoReceptorDTO();
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorDTO1).isNotEqualTo(relatorioQtdeDoadoresParaCadaTipoReceptorDTO2);
        relatorioQtdeDoadoresParaCadaTipoReceptorDTO2.setId(relatorioQtdeDoadoresParaCadaTipoReceptorDTO1.getId());
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorDTO1).isEqualTo(relatorioQtdeDoadoresParaCadaTipoReceptorDTO2);
        relatorioQtdeDoadoresParaCadaTipoReceptorDTO2.setId(2L);
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorDTO1).isNotEqualTo(relatorioQtdeDoadoresParaCadaTipoReceptorDTO2);
        relatorioQtdeDoadoresParaCadaTipoReceptorDTO1.setId(null);
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorDTO1).isNotEqualTo(relatorioQtdeDoadoresParaCadaTipoReceptorDTO2);
    }
}
