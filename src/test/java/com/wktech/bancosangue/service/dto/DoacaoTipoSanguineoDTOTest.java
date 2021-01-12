package com.wktech.bancosangue.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.wktech.bancosangue.web.rest.TestUtil;

public class DoacaoTipoSanguineoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoacaoTipoSanguineoDTO.class);
        DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO1 = new DoacaoTipoSanguineoDTO();
        doacaoTipoSanguineoDTO1.setId(1L);
        DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO2 = new DoacaoTipoSanguineoDTO();
        assertThat(doacaoTipoSanguineoDTO1).isNotEqualTo(doacaoTipoSanguineoDTO2);
        doacaoTipoSanguineoDTO2.setId(doacaoTipoSanguineoDTO1.getId());
        assertThat(doacaoTipoSanguineoDTO1).isEqualTo(doacaoTipoSanguineoDTO2);
        doacaoTipoSanguineoDTO2.setId(2L);
        assertThat(doacaoTipoSanguineoDTO1).isNotEqualTo(doacaoTipoSanguineoDTO2);
        doacaoTipoSanguineoDTO1.setId(null);
        assertThat(doacaoTipoSanguineoDTO1).isNotEqualTo(doacaoTipoSanguineoDTO2);
    }
}
