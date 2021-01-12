package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.DoacaoTipoSanguineo;
import com.wktech.bancosangue.repository.DoacaoTipoSanguineoRepository;
import com.wktech.bancosangue.service.dto.DoacaoTipoSanguineoDTO;
import com.wktech.bancosangue.service.mapper.DoacaoTipoSanguineoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DoacaoTipoSanguineo}.
 */
@Service
@Transactional
public class DoacaoTipoSanguineoService {

    private final Logger log = LoggerFactory.getLogger(DoacaoTipoSanguineoService.class);

    private final DoacaoTipoSanguineoRepository doacaoTipoSanguineoRepository;

    private final DoacaoTipoSanguineoMapper doacaoTipoSanguineoMapper;

    public DoacaoTipoSanguineoService(DoacaoTipoSanguineoRepository doacaoTipoSanguineoRepository, DoacaoTipoSanguineoMapper doacaoTipoSanguineoMapper) {
        this.doacaoTipoSanguineoRepository = doacaoTipoSanguineoRepository;
        this.doacaoTipoSanguineoMapper = doacaoTipoSanguineoMapper;
    }

    /**
     * Save a doacaoTipoSanguineo.
     *
     * @param doacaoTipoSanguineoDTO the entity to save.
     * @return the persisted entity.
     */
    public DoacaoTipoSanguineoDTO save(DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO) {
        log.debug("Request to save DoacaoTipoSanguineo : {}", doacaoTipoSanguineoDTO);
        DoacaoTipoSanguineo doacaoTipoSanguineo = doacaoTipoSanguineoMapper.toEntity(doacaoTipoSanguineoDTO);
        doacaoTipoSanguineo = doacaoTipoSanguineoRepository.save(doacaoTipoSanguineo);
        return doacaoTipoSanguineoMapper.toDto(doacaoTipoSanguineo);
    }

    /**
     * Get all the doacaoTipoSanguineos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DoacaoTipoSanguineoDTO> findAll() {
        log.debug("Request to get all DoacaoTipoSanguineos");
        return doacaoTipoSanguineoRepository.findAll().stream()
            .map(doacaoTipoSanguineoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one doacaoTipoSanguineo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DoacaoTipoSanguineoDTO> findOne(Long id) {
        log.debug("Request to get DoacaoTipoSanguineo : {}", id);
        return doacaoTipoSanguineoRepository.findById(id)
            .map(doacaoTipoSanguineoMapper::toDto);
    }

    /**
     * Delete the doacaoTipoSanguineo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DoacaoTipoSanguineo : {}", id);
        doacaoTipoSanguineoRepository.deleteById(id);
    }
}
