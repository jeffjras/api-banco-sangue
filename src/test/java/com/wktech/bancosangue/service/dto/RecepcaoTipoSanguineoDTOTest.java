package com.wktech.bancosangue.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.wktech.bancosangue.web.rest.TestUtil;

public class RecepcaoTipoSanguineoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecepcaoTipoSanguineoDTO.class);
        RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO1 = new RecepcaoTipoSanguineoDTO();
        recepcaoTipoSanguineoDTO1.setId(1L);
        RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO2 = new RecepcaoTipoSanguineoDTO();
        assertThat(recepcaoTipoSanguineoDTO1).isNotEqualTo(recepcaoTipoSanguineoDTO2);
        recepcaoTipoSanguineoDTO2.setId(recepcaoTipoSanguineoDTO1.getId());
        assertThat(recepcaoTipoSanguineoDTO1).isEqualTo(recepcaoTipoSanguineoDTO2);
        recepcaoTipoSanguineoDTO2.setId(2L);
        assertThat(recepcaoTipoSanguineoDTO1).isNotEqualTo(recepcaoTipoSanguineoDTO2);
        recepcaoTipoSanguineoDTO1.setId(null);
        assertThat(recepcaoTipoSanguineoDTO1).isNotEqualTo(recepcaoTipoSanguineoDTO2);
    }
}
