package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.TipoSanguineo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TipoSanguineo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoSanguineoRepository extends JpaRepository<TipoSanguineo, Long> {
}
