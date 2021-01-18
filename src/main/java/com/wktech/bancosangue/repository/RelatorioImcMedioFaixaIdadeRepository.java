package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.RelatorioImcMedioFaixaIdade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RelatorioImcMedioFaixaIdade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelatorioImcMedioFaixaIdadeRepository
    extends JpaRepository<RelatorioImcMedioFaixaIdade, Long>, JpaSpecificationExecutor<RelatorioImcMedioFaixaIdade> {}
