package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.RelatorioQtdeDoadoresParaCadaTipoReceptor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RelatorioQtdeDoadoresParaCadaTipoReceptor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelatorioQtdeDoadoresParaCadaTipoReceptorRepository
    extends
        JpaRepository<RelatorioQtdeDoadoresParaCadaTipoReceptor, Long>,
        JpaSpecificationExecutor<RelatorioQtdeDoadoresParaCadaTipoReceptor> {}
