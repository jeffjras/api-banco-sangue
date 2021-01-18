package com.wktech.bancosangue.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.wktech.bancosangue.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RelatorioPercObesosMulheresTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatorioPercObesosMulheres.class);
        RelatorioPercObesosMulheres relatorioPercObesosMulheres1 = new RelatorioPercObesosMulheres();
        relatorioPercObesosMulheres1.setId(1L);
        RelatorioPercObesosMulheres relatorioPercObesosMulheres2 = new RelatorioPercObesosMulheres();
        relatorioPercObesosMulheres2.setId(relatorioPercObesosMulheres1.getId());
        assertThat(relatorioPercObesosMulheres1).isEqualTo(relatorioPercObesosMulheres2);
        relatorioPercObesosMulheres2.setId(2L);
        assertThat(relatorioPercObesosMulheres1).isNotEqualTo(relatorioPercObesosMulheres2);
        relatorioPercObesosMulheres1.setId(null);
        assertThat(relatorioPercObesosMulheres1).isNotEqualTo(relatorioPercObesosMulheres2);
    }
}
