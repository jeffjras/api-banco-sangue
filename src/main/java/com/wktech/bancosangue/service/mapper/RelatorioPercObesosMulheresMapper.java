package com.wktech.bancosangue.service.mapper;

import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosMulheresDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelatorioPercObesosMulheres} and its DTO {@link RelatorioPercObesosMulheresDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RelatorioPercObesosMulheresMapper extends EntityMapper<RelatorioPercObesosMulheresDTO, RelatorioPercObesosMulheres> {
    default RelatorioPercObesosMulheres fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelatorioPercObesosMulheres relatorioPercObesosMulheres = new RelatorioPercObesosMulheres();
        relatorioPercObesosMulheres.setId(id);
        return relatorioPercObesosMulheres;
    }
}
