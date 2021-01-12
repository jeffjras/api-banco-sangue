package com.wktech.bancosangue.service.mapper;


import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.DoacaoTipoSanguineoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DoacaoTipoSanguineo} and its DTO {@link DoacaoTipoSanguineoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DoacaoTipoSanguineoMapper extends EntityMapper<DoacaoTipoSanguineoDTO, DoacaoTipoSanguineo> {


    @Mapping(target = "listaTipoSanguineos", ignore = true)
    @Mapping(target = "removeListaTipoSanguineos", ignore = true)
    DoacaoTipoSanguineo toEntity(DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO);

    default DoacaoTipoSanguineo fromId(Long id) {
        if (id == null) {
            return null;
        }
        DoacaoTipoSanguineo doacaoTipoSanguineo = new DoacaoTipoSanguineo();
        doacaoTipoSanguineo.setId(id);
        return doacaoTipoSanguineo;
    }
}
