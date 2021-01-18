package com.wktech.bancosangue.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioImcMedioFaixaIdadeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioImcMedioFaixaIdade.class);
        RelatorioImcMedioFaixaIdade relatorioImcMedioFaixaIdade1 = new RelatorioImcMedioFaixaIdade();
        relatorioImcMedioFaixaIdade1.setId(1L);
        RelatorioImcMedioFaixaIdade relatorioImcMedioFaixaIdade2 = new RelatorioImcMedioFaixaIdade();
        relatorioImcMedioFaixaIdade2.setId(relatorioImcMedioFaixaIdade1.getId());
        assertThat(relatorioImcMedioFaixaIdade1).isEqualTo(relatorioImcMedioFaixaIdade2);
        relatorioImcMedioFaixaIdade2.setId(2L);
        assertThat(relatorioImcMedioFaixaIdade1).isNotEqualTo(relatorioImcMedioFaixaIdade2);
        relatorioImcMedioFaixaIdade1.setId(null);
        assertThat(relatorioImcMedioFaixaIdade1).isNotEqualTo(relatorioImcMedioFaixaIdade2);
    }
}
