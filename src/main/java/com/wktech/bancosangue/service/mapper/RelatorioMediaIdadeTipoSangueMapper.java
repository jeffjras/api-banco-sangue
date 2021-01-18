package com.wktech.bancosangue.service.mapper;

import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.RelatorioMediaIdadeTipoSangueDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelatorioMediaIdadeTipoSangue} and its DTO {@link RelatorioMediaIdadeTipoSangueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RelatorioMediaIdadeTipoSangueMapper extends EntityMapper<RelatorioMediaIdadeTipoSangueDTO, RelatorioMediaIdadeTipoSangue> {
    default RelatorioMediaIdadeTipoSangue fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelatorioMediaIdadeTipoSangue relatorioMediaIdadeTipoSangue = new RelatorioMediaIdadeTipoSangue();
        relatorioMediaIdadeTipoSangue.setId(id);
        return relatorioMediaIdadeTipoSangue;
    }
}
