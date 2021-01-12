package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.TipoSanguineo;
import com.wktech.bancosangue.repository.TipoSanguineoRepository;
import com.wktech.bancosangue.service.dto.TipoSanguineoDTO;
import com.wktech.bancosangue.service.mapper.TipoSanguineoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoSanguineo}.
 */
@Service
@Transactional
public class TipoSanguineoService {

    private final Logger log = LoggerFactory.getLogger(TipoSanguineoService.class);

    private final TipoSanguineoRepository tipoSanguineoRepository;

    private final TipoSanguineoMapper tipoSanguineoMapper;

    public TipoSanguineoService(TipoSanguineoRepository tipoSanguineoRepository, TipoSanguineoMapper tipoSanguineoMapper) {
        this.tipoSanguineoRepository = tipoSanguineoRepository;
        this.tipoSanguineoMapper = tipoSanguineoMapper;
    }

    /**
     * Save a tipoSanguineo.
     *
     * @param tipoSanguineoDTO the entity to save.
     * @return the persisted entity.
     */
    public TipoSanguineoDTO save(TipoSanguineoDTO tipoSanguineoDTO) {
        log.debug("Request to save TipoSanguineo : {}", tipoSanguineoDTO);
        TipoSanguineo tipoSanguineo = tipoSanguineoMapper.toEntity(tipoSanguineoDTO);
        tipoSanguineo = tipoSanguineoRepository.save(tipoSanguineo);
        return tipoSanguineoMapper.toDto(tipoSanguineo);
    }

    /**
     * Get all the tipoSanguineos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TipoSanguineoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoSanguineos");
        return tipoSanguineoRepository.findAll(pageable)
            .map(tipoSanguineoMapper::toDto);
    }


    /**
     * Get one tipoSanguineo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TipoSanguineoDTO> findOne(Long id) {
        log.debug("Request to get TipoSanguineo : {}", id);
        return tipoSanguineoRepository.findById(id)
            .map(tipoSanguineoMapper::toDto);
    }

    /**
     * Delete the tipoSanguineo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TipoSanguineo : {}", id);
        tipoSanguineoRepository.deleteById(id);
    }
}
