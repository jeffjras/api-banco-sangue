package com.wktech.bancosangue.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RelatorioQtdeDoadoresParaCadaTipoReceptorMapperTest {
    private RelatorioQtdeDoadoresParaCadaTipoReceptorMapper relatorioQtdeDoadoresParaCadaTipoReceptorMapper;

    @BeforeEach
    public void setUp() {
        relatorioQtdeDoadoresParaCadaTipoReceptorMapper = new RelatorioQtdeDoadoresParaCadaTipoReceptorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorMapper.fromId(null)).isNull();
    }
}
