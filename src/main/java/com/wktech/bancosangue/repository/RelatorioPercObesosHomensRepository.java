package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.RelatorioPercObesosHomens;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RelatorioPercObesosHomens entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelatorioPercObesosHomensRepository
    extends JpaRepository<RelatorioPercObesosHomens, Long>, JpaSpecificationExecutor<RelatorioPercObesosHomens> {}
