package com.wktech.bancosangue.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioImcMedioFaixaIdadeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioImcMedioFaixaIdadeDTO.class);
        RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO1 = new RelatorioImcMedioFaixaIdadeDTO();
        relatorioImcMedioFaixaIdadeDTO1.setId(1L);
        RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO2 = new RelatorioImcMedioFaixaIdadeDTO();
        assertThat(relatorioImcMedioFaixaIdadeDTO1).isNotEqualTo(relatorioImcMedioFaixaIdadeDTO2);
        relatorioImcMedioFaixaIdadeDTO2.setId(relatorioImcMedioFaixaIdadeDTO1.getId());
        assertThat(relatorioImcMedioFaixaIdadeDTO1).isEqualTo(relatorioImcMedioFaixaIdadeDTO2);
        relatorioImcMedioFaixaIdadeDTO2.setId(2L);
        assertThat(relatorioImcMedioFaixaIdadeDTO1).isNotEqualTo(relatorioImcMedioFaixaIdadeDTO2);
        relatorioImcMedioFaixaIdadeDTO1.setId(null);
        assertThat(relatorioImcMedioFaixaIdadeDTO1).isNotEqualTo(relatorioImcMedioFaixaIdadeDTO2);
    }
}
