package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.RecepcaoTipoSanguineo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RecepcaoTipoSanguineo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecepcaoTipoSanguineoRepository extends JpaRepository<RecepcaoTipoSanguineo, Long> {
}
