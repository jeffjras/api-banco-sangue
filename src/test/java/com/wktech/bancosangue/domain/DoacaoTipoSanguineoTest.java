package com.wktech.bancosangue.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.wktech.bancosangue.web.rest.TestUtil;

public class DoacaoTipoSanguineoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoacaoTipoSanguineo.class);
        DoacaoTipoSanguineo doacaoTipoSanguineo1 = new DoacaoTipoSanguineo();
        doacaoTipoSanguineo1.setId(1L);
        DoacaoTipoSanguineo doacaoTipoSanguineo2 = new DoacaoTipoSanguineo();
        doacaoTipoSanguineo2.setId(doacaoTipoSanguineo1.getId());
        assertThat(doacaoTipoSanguineo1).isEqualTo(doacaoTipoSanguineo2);
        doacaoTipoSanguineo2.setId(2L);
        assertThat(doacaoTipoSanguineo1).isNotEqualTo(doacaoTipoSanguineo2);
        doacaoTipoSanguineo1.setId(null);
        assertThat(doacaoTipoSanguineo1).isNotEqualTo(doacaoTipoSanguineo2);
    }
}
