package com.wktech.bancosangue.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RelatorioQtdCandPorEstadoMapperTest {
    private RelatorioQtdCandPorEstadoMapper relatorioQtdCandPorEstadoMapper;

    @BeforeEach
    public void setUp() {
        relatorioQtdCandPorEstadoMapper = new RelatorioQtdCandPorEstadoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(relatorioQtdCandPorEstadoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relatorioQtdCandPorEstadoMapper.fromId(null)).isNull();
    }
}
