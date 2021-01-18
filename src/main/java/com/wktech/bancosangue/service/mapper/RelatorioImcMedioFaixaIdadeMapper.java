package com.wktech.bancosangue.service.mapper;

import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.RelatorioImcMedioFaixaIdadeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelatorioImcMedioFaixaIdade} and its DTO {@link RelatorioImcMedioFaixaIdadeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RelatorioImcMedioFaixaIdadeMapper extends EntityMapper<RelatorioImcMedioFaixaIdadeDTO, RelatorioImcMedioFaixaIdade> {
    default RelatorioImcMedioFaixaIdade fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelatorioImcMedioFaixaIdade relatorioImcMedioFaixaIdade = new RelatorioImcMedioFaixaIdade();
        relatorioImcMedioFaixaIdade.setId(id);
        return relatorioImcMedioFaixaIdade;
    }
}
