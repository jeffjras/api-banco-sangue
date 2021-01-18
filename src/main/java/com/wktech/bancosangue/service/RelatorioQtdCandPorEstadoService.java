package com.wktech.bancosangue.service;

import com.wktech.bancosangue.service.dto.RelatorioQtdCandPorEstadoDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.wktech.bancosangue.domain.RelatorioQtdCandPorEstado}.
 */
public interface RelatorioQtdCandPorEstadoService {
    /**
     * Save a relatorioQtdCandPorEstado.
     *
     * @param relatorioQtdCandPorEstadoDTO the entity to save.
     * @return the persisted entity.
     */
    RelatorioQtdCandPorEstadoDTO save(RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO);

    /**
     * Get all the relatorioQtdCandPorEstados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelatorioQtdCandPorEstadoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" relatorioQtdCandPorEstado.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelatorioQtdCandPorEstadoDTO> findOne(Long id);

    /**
     * Delete the "id" relatorioQtdCandPorEstado.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
