package com.wktech.bancosangue.service.mapper;

import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.RelatorioQtdeDoadoresParaCadaTipoReceptorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelatorioQtdeDoadoresParaCadaTipoReceptor} and its DTO {@link RelatorioQtdeDoadoresParaCadaTipoReceptorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RelatorioQtdeDoadoresParaCadaTipoReceptorMapper
    extends EntityMapper<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO, RelatorioQtdeDoadoresParaCadaTipoReceptor> {
    default RelatorioQtdeDoadoresParaCadaTipoReceptor fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelatorioQtdeDoadoresParaCadaTipoReceptor relatorioQtdeDoadoresParaCadaTipoReceptor = new RelatorioQtdeDoadoresParaCadaTipoReceptor();
        relatorioQtdeDoadoresParaCadaTipoReceptor.setId(id);
        return relatorioQtdeDoadoresParaCadaTipoReceptor;
    }
}
