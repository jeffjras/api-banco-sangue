package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.RelatorioQtdCandPorEstado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RelatorioQtdCandPorEstado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelatorioQtdCandPorEstadoRepository
    extends JpaRepository<RelatorioQtdCandPorEstado, Long>, JpaSpecificationExecutor<RelatorioQtdCandPorEstado> {}
