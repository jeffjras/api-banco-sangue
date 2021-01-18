package com.wktech.bancosangue.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RelatorioMediaIdadeTipoSangueMapperTest {
    private RelatorioMediaIdadeTipoSangueMapper relatorioMediaIdadeTipoSangueMapper;

    @BeforeEach
    public void setUp() {
        relatorioMediaIdadeTipoSangueMapper = new RelatorioMediaIdadeTipoSangueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(relatorioMediaIdadeTipoSangueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relatorioMediaIdadeTipoSangueMapper.fromId(null)).isNull();
    }
}
