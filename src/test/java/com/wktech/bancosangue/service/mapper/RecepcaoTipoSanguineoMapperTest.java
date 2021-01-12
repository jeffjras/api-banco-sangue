package com.wktech.bancosangue.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RecepcaoTipoSanguineoMapperTest {

    private RecepcaoTipoSanguineoMapper recepcaoTipoSanguineoMapper;

    @BeforeEach
    public void setUp() {
        recepcaoTipoSanguineoMapper = new RecepcaoTipoSanguineoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(recepcaoTipoSanguineoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(recepcaoTipoSanguineoMapper.fromId(null)).isNull();
    }
}
