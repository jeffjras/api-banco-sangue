package com.wktech.bancosangue.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RelatorioPercObesosHomensMapperTest {
    private RelatorioPercObesosHomensMapper relatorioPercObesosHomensMapper;

    @BeforeEach
    public void setUp() {
        relatorioPercObesosHomensMapper = new RelatorioPercObesosHomensMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(relatorioPercObesosHomensMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relatorioPercObesosHomensMapper.fromId(null)).isNull();
    }
}
