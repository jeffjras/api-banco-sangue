package com.wktech.bancosangue.repository;

import com.wktech.bancosangue.domain.Candidato;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Candidato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    //SELECT c.estado, count(c.nome) as qtde FROM candidato c group by c.estado
    @Query(
        nativeQuery = true,
        value = " SELECT * FROM candidato ",
        countQuery = "SELECT c.estado, count(c.nome) as qtde FROM candidato c group by c.estado "
    )
    Page<Candidato> findQuantidadeCandidatoPorEstado(Pageable pageable);

    @Query(
        nativeQuery = true,
        value = " select a.faixa_etaria, avg(b.imc) as imc_medio FROM ( SELECT 10 idade_limite, '0 - 10' faixa_etaria" +
        " UNION SELECT 20 idade_limite, '11 - 20' faixa_etaria UNION SELECT 30 idade_limite, '21 - 30' faixa_etaria" +
        " UNION SELECT 40 idade_limite, '31 - 40' faixa_etaria UNION SELECT 50 idade_limite, '41 - 50' faixa_etaria" +
        " UNION SELECT 60 idade_limite, '51 - 60' faixa_etaria UNION SELECT 70 idade_limite, '61 - 70' faixa_etaria" +
        " UNION SELECT 70 idade_limite, '71 - 80' faixa_etaria" +
        " ) a LEFT JOIN idade_imc_candidato b ON b.idade <= a.idade_limite " +
        " GROUP BY a.faixa_etaria "
    )
    Page<Candidato> findImcMedioEmCadaFaixaIdade(Pageable pageable);

    @Query(nativeQuery = true, value = " select (qtd_obesos / total_homens)*100  AS percentual_masculino from qtd_obesos_total_homens ")
    Page<Candidato> findPercentualObesosHomens(Pageable pageable);

    @Query(nativeQuery = true, value = " select (qtd_obesos / total_mulheres)*100  AS percentual_feminino from qtd_obesos_total_mulheres ")
    Page<Candidato> findPercentualObesosMulheres(Pageable pageable);

    @Query(nativeQuery = true, value = " select tipo_sangue, avg(idade) as media from tipo_sangue_idade group by 1 ")
    Page<Candidato> findMediaIdadeEmCadaTipoSanguineo(Pageable pageable);

    @Query(
        nativeQuery = true,
        value = " select 'A+' as sangue, sum(QTDE) as total from tipo_sangue_qtd_candidato where tipo_sangue = 'A+' OR tipo_sangue = 'A-' " +
        " OR tipo_sangue = 'O+' OR tipo_sangue = 'O-' UNION" +
        " select 'A-' as sangue, sum(QTDE) as total from tipo_sangue_qtd_candidato where tipo_sangue = 'A-' OR tipo_sangue = 'O-' UNION " +
        " select 'B+' as sangue, sum(QTDE) as total from tipo_sangue_qtd_candidato where tipo_sangue = 'B+' OR tipo_sangue = 'B-' " +
        " OR tipo_sangue = 'O+' OR tipo_sangue = 'O-' UNION" +
        " select 'B-' as sangue, sum(QTDE) as total from tipo_sangue_qtd_candidato where tipo_sangue = 'B-' OR tipo_sangue = 'O-' UNION " +
        " select 'AB+' as sangue, sum(QTDE) as total from tipo_sangue_qtd_candidato where tipo_sangue = 'A+' OR tipo_sangue = 'B+' " +
        " OR tipo_sangue = 'O+' OR tipo_sangue = 'AB+' OR tipo_sangue = 'A-' OR tipo_sangue = 'B-' OR tipo_sangue = 'O-'" +
        " OR tipo_sangue = 'AB-' UNION" +
        " select 'AB-' as sangue, sum(QTDE) as total from tipo_sangue_qtd_candidato where tipo_sangue = 'A-' OR tipo_sangue = 'B-'" +
        " OR tipo_sangue = 'O-' OR tipo_sangue = 'AB-' UNION" +
        " select 'O+' as sangue, sum(QTDE) as total from tipo_sangue_qtd_candidato where tipo_sangue = 'O+' OR tipo_sangue = 'O-' UNION " +
        " select 'O-' as sangue, sum(QTDE) as total from tipo_sangue_qtd_candidato where tipo_sangue = 'O-' "
    )
    Page<Candidato> findQuantidadeDoadoresParaCadaTipoReceptor(Pageable pageable);
}
