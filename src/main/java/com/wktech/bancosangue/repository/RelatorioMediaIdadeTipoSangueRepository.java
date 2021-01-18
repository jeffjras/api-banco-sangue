package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.RelatorioMediaIdadeTipoSangue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RelatorioMediaIdadeTipoSangue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelatorioMediaIdadeTipoSangueRepository
    extends JpaRepository<RelatorioMediaIdadeTipoSangue, Long>, JpaSpecificationExecutor<RelatorioMediaIdadeTipoSangue> {}
