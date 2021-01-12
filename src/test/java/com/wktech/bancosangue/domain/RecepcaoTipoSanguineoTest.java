package com.wktech.bancosangue.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.wktech.bancosangue.web.rest.TestUtil;

public class RecepcaoTipoSanguineoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecepcaoTipoSanguineo.class);
        RecepcaoTipoSanguineo recepcaoTipoSanguineo1 = new RecepcaoTipoSanguineo();
        recepcaoTipoSanguineo1.setId(1L);
        RecepcaoTipoSanguineo recepcaoTipoSanguineo2 = new RecepcaoTipoSanguineo();
        recepcaoTipoSanguineo2.setId(recepcaoTipoSanguineo1.getId());
        assertThat(recepcaoTipoSanguineo1).isEqualTo(recepcaoTipoSanguineo2);
        recepcaoTipoSanguineo2.setId(2L);
        assertThat(recepcaoTipoSanguineo1).isNotEqualTo(recepcaoTipoSanguineo2);
        recepcaoTipoSanguineo1.setId(null);
        assertThat(recepcaoTipoSanguineo1).isNotEqualTo(recepcaoTipoSanguineo2);
    }
}
