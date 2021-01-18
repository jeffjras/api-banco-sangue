package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.RelatorioPercObesosMulheres;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RelatorioPercObesosMulheres entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelatorioPercObesosMulheresRepository
    extends JpaRepository<RelatorioPercObesosMulheres, Long>, JpaSpecificationExecutor<RelatorioPercObesosMulheres> {}
