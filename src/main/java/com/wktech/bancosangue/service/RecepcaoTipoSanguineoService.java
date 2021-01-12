package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.RecepcaoTipoSanguineo;
import com.wktech.bancosangue.repository.RecepcaoTipoSanguineoRepository;
import com.wktech.bancosangue.service.dto.RecepcaoTipoSanguineoDTO;
import com.wktech.bancosangue.service.mapper.RecepcaoTipoSanguineoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link RecepcaoTipoSanguineo}.
 */
@Service
@Transactional
public class RecepcaoTipoSanguineoService {

    private final Logger log = LoggerFactory.getLogger(RecepcaoTipoSanguineoService.class);

    private final RecepcaoTipoSanguineoRepository recepcaoTipoSanguineoRepository;

    private final RecepcaoTipoSanguineoMapper recepcaoTipoSanguineoMapper;

    public RecepcaoTipoSanguineoService(RecepcaoTipoSanguineoRepository recepcaoTipoSanguineoRepository, RecepcaoTipoSanguineoMapper recepcaoTipoSanguineoMapper) {
        this.recepcaoTipoSanguineoRepository = recepcaoTipoSanguineoRepository;
        this.recepcaoTipoSanguineoMapper = recepcaoTipoSanguineoMapper;
    }

    /**
     * Save a recepcaoTipoSanguineo.
     *
     * @param recepcaoTipoSanguineoDTO the entity to save.
     * @return the persisted entity.
     */
    public RecepcaoTipoSanguineoDTO save(RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO) {
        log.debug("Request to save RecepcaoTipoSanguineo : {}", recepcaoTipoSanguineoDTO);
        RecepcaoTipoSanguineo recepcaoTipoSanguineo = recepcaoTipoSanguineoMapper.toEntity(recepcaoTipoSanguineoDTO);
        recepcaoTipoSanguineo = recepcaoTipoSanguineoRepository.save(recepcaoTipoSanguineo);
        return recepcaoTipoSanguineoMapper.toDto(recepcaoTipoSanguineo);
    }

    /**
     * Get all the recepcaoTipoSanguineos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RecepcaoTipoSanguineoDTO> findAll() {
        log.debug("Request to get all RecepcaoTipoSanguineos");
        return recepcaoTipoSanguineoRepository.findAll().stream()
            .map(recepcaoTipoSanguineoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one recepcaoTipoSanguineo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RecepcaoTipoSanguineoDTO> findOne(Long id) {
        log.debug("Request to get RecepcaoTipoSanguineo : {}", id);
        return recepcaoTipoSanguineoRepository.findById(id)
            .map(recepcaoTipoSanguineoMapper::toDto);
    }

    /**
     * Delete the recepcaoTipoSanguineo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RecepcaoTipoSanguineo : {}", id);
        recepcaoTipoSanguineoRepository.deleteById(id);
    }
}
