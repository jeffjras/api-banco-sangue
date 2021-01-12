package com.wktech.bancosangue.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DoacaoTipoSanguineoMapperTest {

    private DoacaoTipoSanguineoMapper doacaoTipoSanguineoMapper;

    @BeforeEach
    public void setUp() {
        doacaoTipoSanguineoMapper = new DoacaoTipoSanguineoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(doacaoTipoSanguineoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(doacaoTipoSanguineoMapper.fromId(null)).isNull();
    }
}
