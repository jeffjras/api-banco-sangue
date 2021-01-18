package com.wktech.bancosangue.service;

import com.wktech.bancosangue.service.dto.RelatorioPercObesosHomensDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.wktech.bancosangue.domain.RelatorioPercObesosHomens}.
 */
public interface RelatorioPercObesosHomensService {
    /**
     * Save a relatorioPercObesosHomens.
     *
     * @param relatorioPercObesosHomensDTO the entity to save.
     * @return the persisted entity.
     */
    RelatorioPercObesosHomensDTO save(RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO);

    /**
     * Get all the relatorioPercObesosHomens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelatorioPercObesosHomensDTO> findAll(Pageable pageable);

    /**
     * Get the "id" relatorioPercObesosHomens.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelatorioPercObesosHomensDTO> findOne(Long id);

    /**
     * Delete the "id" relatorioPercObesosHomens.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
