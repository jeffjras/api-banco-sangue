package com.wktech.bancosangue.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.wktech.bancosangue.web.rest.TestUtil;

public class TipoSanguineoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoSanguineo.class);
        TipoSanguineo tipoSanguineo1 = new TipoSanguineo();
        tipoSanguineo1.setId(1L);
        TipoSanguineo tipoSanguineo2 = new TipoSanguineo();
        tipoSanguineo2.setId(tipoSanguineo1.getId());
        assertThat(tipoSanguineo1).isEqualTo(tipoSanguineo2);
        tipoSanguineo2.setId(2L);
        assertThat(tipoSanguineo1).isNotEqualTo(tipoSanguineo2);
        tipoSanguineo1.setId(null);
        assertThat(tipoSanguineo1).isNotEqualTo(tipoSanguineo2);
    }
}
