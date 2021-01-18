package com.wktech.bancosangue.service;

import com.wktech.bancosangue.service.dto.RelatorioMediaIdadeTipoSangueDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.wktech.bancosangue.domain.RelatorioMediaIdadeTipoSangue}.
 */
public interface RelatorioMediaIdadeTipoSangueService {
    /**
     * Save a relatorioMediaIdadeTipoSangue.
     *
     * @param relatorioMediaIdadeTipoSangueDTO the entity to save.
     * @return the persisted entity.
     */
    RelatorioMediaIdadeTipoSangueDTO save(RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO);

    /**
     * Get all the relatorioMediaIdadeTipoSangues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelatorioMediaIdadeTipoSangueDTO> findAll(Pageable pageable);

    /**
     * Get the "id" relatorioMediaIdadeTipoSangue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelatorioMediaIdadeTipoSangueDTO> findOne(Long id);

    /**
     * Delete the "id" relatorioMediaIdadeTipoSangue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
