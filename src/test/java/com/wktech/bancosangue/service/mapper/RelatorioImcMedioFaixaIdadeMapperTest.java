package com.wktech.bancosangue.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RelatorioImcMedioFaixaIdadeMapperTest {
    private RelatorioImcMedioFaixaIdadeMapper relatorioImcMedioFaixaIdadeMapper;

    @BeforeEach
    public void setUp() {
        relatorioImcMedioFaixaIdadeMapper = new RelatorioImcMedioFaixaIdadeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(relatorioImcMedioFaixaIdadeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relatorioImcMedioFaixaIdadeMapper.fromId(null)).isNull();
    }
}
