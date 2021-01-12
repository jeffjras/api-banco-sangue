package com.wktech.bancosangue.service.mapper;


import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.TipoSanguineoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoSanguineo} and its DTO {@link TipoSanguineoDTO}.
 */
@Mapper(componentModel = "spring", uses = {DoacaoTipoSanguineoMapper.class, RecepcaoTipoSanguineoMapper.class})
public interface TipoSanguineoMapper extends EntityMapper<TipoSanguineoDTO, TipoSanguineo> {

    @Mapping(source = "doacaoTipoSanguineo.id", target = "doacaoTipoSanguineoId")
    @Mapping(source = "recepcaoTipoSanguineo.id", target = "recepcaoTipoSanguineoId")
    TipoSanguineoDTO toDto(TipoSanguineo tipoSanguineo);

    @Mapping(target = "listaCandidatos", ignore = true)
    @Mapping(target = "removeListaCandidatos", ignore = true)
    @Mapping(source = "doacaoTipoSanguineoId", target = "doacaoTipoSanguineo")
    @Mapping(source = "recepcaoTipoSanguineoId", target = "recepcaoTipoSanguineo")
    TipoSanguineo toEntity(TipoSanguineoDTO tipoSanguineoDTO);

    default TipoSanguineo fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoSanguineo tipoSanguineo = new TipoSanguineo();
        tipoSanguineo.setId(id);
        return tipoSanguineo;
    }
}
