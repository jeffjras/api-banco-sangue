package com.wktech.bancosangue.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RelatorioPercObesosMulheresMapperTest {
    private RelatorioPercObesosMulheresMapper relatorioPercObesosMulheresMapper;

    @BeforeEach
    public void setUp() {
        relatorioPercObesosMulheresMapper = new RelatorioPercObesosMulheresMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(relatorioPercObesosMulheresMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relatorioPercObesosMulheresMapper.fromId(null)).isNull();
    }
}
