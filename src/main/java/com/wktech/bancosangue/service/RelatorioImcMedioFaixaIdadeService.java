package com.wktech.bancosangue.service;

import com.wktech.bancosangue.service.dto.RelatorioImcMedioFaixaIdadeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.wktech.bancosangue.domain.RelatorioImcMedioFaixaIdade}.
 */
public interface RelatorioImcMedioFaixaIdadeService {
    /**
     * Save a relatorioImcMedioFaixaIdade.
     *
     * @param relatorioImcMedioFaixaIdadeDTO the entity to save.
     * @return the persisted entity.
     */
    RelatorioImcMedioFaixaIdadeDTO save(RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO);

    /**
     * Get all the relatorioImcMedioFaixaIdades.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelatorioImcMedioFaixaIdadeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" relatorioImcMedioFaixaIdade.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelatorioImcMedioFaixaIdadeDTO> findOne(Long id);

    /**
     * Delete the "id" relatorioImcMedioFaixaIdade.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
