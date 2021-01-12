package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.DoacaoTipoSanguineo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DoacaoTipoSanguineo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoacaoTipoSanguineoRepository extends JpaRepository<DoacaoTipoSanguineo, Long> {
}
