package com.wktech.bancosangue.service.mapper;


import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.RecepcaoTipoSanguineoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RecepcaoTipoSanguineo} and its DTO {@link RecepcaoTipoSanguineoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecepcaoTipoSanguineoMapper extends EntityMapper<RecepcaoTipoSanguineoDTO, RecepcaoTipoSanguineo> {


    @Mapping(target = "listaTipoSanguineos", ignore = true)
    @Mapping(target = "removeListaTipoSanguineos", ignore = true)
    RecepcaoTipoSanguineo toEntity(RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO);

    default RecepcaoTipoSanguineo fromId(Long id) {
        if (id == null) {
            return null;
        }
        RecepcaoTipoSanguineo recepcaoTipoSanguineo = new RecepcaoTipoSanguineo();
        recepcaoTipoSanguineo.setId(id);
        return recepcaoTipoSanguineo;
    }
}
