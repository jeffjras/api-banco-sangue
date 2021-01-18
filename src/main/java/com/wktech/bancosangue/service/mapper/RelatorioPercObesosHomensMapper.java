package com.wktech.bancosangue.service.mapper;

import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosHomensDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelatorioPercObesosHomens} and its DTO {@link RelatorioPercObesosHomensDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RelatorioPercObesosHomensMapper extends EntityMapper<RelatorioPercObesosHomensDTO, RelatorioPercObesosHomens> {
    default RelatorioPercObesosHomens fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelatorioPercObesosHomens relatorioPercObesosHomens = new RelatorioPercObesosHomens();
        relatorioPercObesosHomens.setId(id);
        return relatorioPercObesosHomens;
    }
}
