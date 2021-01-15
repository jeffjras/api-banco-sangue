package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.Candidato;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Candidato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    @Query(nativeQuery = true, value = " SELECT estado, count(nome) as qtde FROM candidato group by estado ")
    List<Candidato> findQuantidadeCandidatoPorEstado();
}
