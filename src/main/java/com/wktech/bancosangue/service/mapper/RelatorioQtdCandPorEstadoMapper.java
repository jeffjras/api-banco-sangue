package com.wktech.bancosangue.service.mapper;

import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.RelatorioQtdCandPorEstadoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelatorioQtdCandPorEstado} and its DTO {@link RelatorioQtdCandPorEstadoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RelatorioQtdCandPorEstadoMapper extends EntityMapper<RelatorioQtdCandPorEstadoDTO, RelatorioQtdCandPorEstado> {
    default RelatorioQtdCandPorEstado fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelatorioQtdCandPorEstado relatorioQtdCandPorEstado = new RelatorioQtdCandPorEstado();
        relatorioQtdCandPorEstado.setId(id);
        return relatorioQtdCandPorEstado;
    }
}
