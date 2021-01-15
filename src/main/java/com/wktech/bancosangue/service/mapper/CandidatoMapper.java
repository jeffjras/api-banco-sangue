package com.wktech.bancosangue.service.mapper;

import com.wktech.bancosangue.domain.*;
import com.wktech.bancosangue.service.dto.CandidatoDTO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Candidato} and its DTO {@link CandidatoDTO}.
 */
@Mapper(componentModel = "spring", uses = { TipoSanguineoMapper.class })
public interface CandidatoMapper extends EntityMapper<CandidatoDTO, Candidato> {
    @Mapping(source = "tipoSanguineo.id", target = "tipoSanguineoId")
    CandidatoDTO toDto(Candidato candidato);

    /*
     * @Mapping(source = "tipoSanguineo.id", target = "tipoSanguineoId")
     * CandidatoDTO toDtoList(Candidato candidato);
     */

    @Mapping(source = "tipoSanguineoId", target = "tipoSanguineo")
    Candidato toEntity(CandidatoDTO candidatoDTO);

    default Candidato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Candidato candidato = new Candidato();
        candidato.setId(id);
        return candidato;
    }
}
