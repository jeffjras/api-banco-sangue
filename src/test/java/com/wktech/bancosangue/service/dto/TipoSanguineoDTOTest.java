package com.wktech.bancosangue.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.wktech.bancosangue.web.rest.TestUtil;

public class TipoSanguineoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoSanguineoDTO.class);
        TipoSanguineoDTO tipoSanguineoDTO1 = new TipoSanguineoDTO();
        tipoSanguineoDTO1.setId(1L);
        TipoSanguineoDTO tipoSanguineoDTO2 = new TipoSanguineoDTO();
        assertThat(tipoSanguineoDTO1).isNotEqualTo(tipoSanguineoDTO2);
        tipoSanguineoDTO2.setId(tipoSanguineoDTO1.getId());
        assertThat(tipoSanguineoDTO1).isEqualTo(tipoSanguineoDTO2);
        tipoSanguineoDTO2.setId(2L);
        assertThat(tipoSanguineoDTO1).isNotEqualTo(tipoSanguineoDTO2);
        tipoSanguineoDTO1.setId(null);
        assertThat(tipoSanguineoDTO1).isNotEqualTo(tipoSanguineoDTO2);
    }
}
