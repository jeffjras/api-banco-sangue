package com.wktech.bancosangue.service;

import com.wktech.bancosangue.service.dto.RelatorioPercObesosMulheresDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.wktech.bancosangue.domain.RelatorioPercObesosMulheres}.
 */
public interface RelatorioPercObesosMulheresService {
    /**
     * Save a relatorioPercObesosMulheres.
     *
     * @param relatorioPercObesosMulheresDTO the entity to save.
     * @return the persisted entity.
     */
    RelatorioPercObesosMulheresDTO save(RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO);

    /**
     * Get all the relatorioPercObesosMulheres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelatorioPercObesosMulheresDTO> findAll(Pageable pageable);

    /**
     * Get the "id" relatorioPercObesosMulheres.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelatorioPercObesosMulheresDTO> findOne(Long id);

    /**
     * Delete the "id" relatorioPercObesosMulheres.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
