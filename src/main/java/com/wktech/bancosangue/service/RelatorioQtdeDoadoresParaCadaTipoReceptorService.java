package com.wktech.bancosangue.service;

import com.wktech.bancosangue.service.dto.RelatorioQtdeDoadoresParaCadaTipoReceptorDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.wktech.bancosangue.domain.RelatorioQtdeDoadoresParaCadaTipoReceptor}.
 */
public interface RelatorioQtdeDoadoresParaCadaTipoReceptorService {
    /**
     * Save a relatorioQtdeDoadoresParaCadaTipoReceptor.
     *
     * @param relatorioQtdeDoadoresParaCadaTipoReceptorDTO the entity to save.
     * @return the persisted entity.
     */
    RelatorioQtdeDoadoresParaCadaTipoReceptorDTO save(
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO
    );

    /**
     * Get all the relatorioQtdeDoadoresParaCadaTipoReceptors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> findAll(Pageable pageable);

    /**
     * Get the "id" relatorioQtdeDoadoresParaCadaTipoReceptor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> findOne(Long id);

    /**
     * Delete the "id" relatorioQtdeDoadoresParaCadaTipoReceptor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
