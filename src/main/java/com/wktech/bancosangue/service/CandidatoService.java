package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.Candidato;
import com.wktech.bancosangue.repository.CandidatoRepository;
import com.wktech.bancosangue.service.dto.CandidatoDTO;
import com.wktech.bancosangue.service.mapper.CandidatoMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Candidato}.
 */
@Service
@Transactional
public class CandidatoService {
    private final Logger log = LoggerFactory.getLogger(CandidatoService.class);

    private final CandidatoRepository candidatoRepository;

    private final CandidatoMapper candidatoMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public CandidatoService(CandidatoRepository candidatoRepository, CandidatoMapper candidatoMapper) {
        this.candidatoRepository = candidatoRepository;
        this.candidatoMapper = candidatoMapper;
    }

    /**
     * Save a candidato.
     *
     * @param candidatoDTO the entity to save.
     * @return the persisted entity.
     */
    public CandidatoDTO save(CandidatoDTO candidatoDTO) {
        log.debug("Request to save Candidato : {}", candidatoDTO);
        Candidato candidato = candidatoMapper.toEntity(candidatoDTO);
        candidato = candidatoRepository.save(candidato);
        return candidatoMapper.toDto(candidato);
    }

    public String dataParaMySQL(String data) {
        String ret = " - - ";
        try {
            data = data.replace("/", "");
        } catch (Exception e) {}
        try {
            data = data.replace("-", "");
        } catch (Exception e) {}
        try {
            data = data.substring(4, 8) + "-" + data.substring(2, 4) + "-" + data.substring(0, 2);
        } catch (Exception e) {}
        ret = data;
        return ret;
    }

    public CandidatoDTO importar(List<CandidatoDTO> candidatoDTO) {
        log.debug("Request to save Candidato : {}", candidatoDTO);

        Candidato candidato = null;

        if (candidatoDTO.size() > 1) {
            for (int i = 0; i < candidatoDTO.size(); i++) {
                candidato = candidatoMapper.toEntity(candidatoDTO.get(i));
                candidato = candidatoRepository.save(candidato);
            }
        } else {
            candidato = candidatoMapper.toEntity(candidatoDTO.get(0));
            candidato = candidatoRepository.save(candidato);
        }

        return candidatoMapper.toDto(candidato);
    }

    /**
     * Get all the candidatoes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Candidatoes");
        return candidatoRepository.findAll(pageable).map(candidatoMapper::toDto);
    }

    /**
     * Get one candidato by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CandidatoDTO> findOne(Long id) {
        log.debug("Request to get Candidato : {}", id);
        return candidatoRepository.findById(id).map(candidatoMapper::toDto);
    }

    /**
     * Delete the candidato by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Candidato : {}", id);
        candidatoRepository.deleteById(id);
    }

    /**
     *
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */

    //@Transactional(readOnly = true)
    public Page<CandidatoDTO> findQuantidadeCandidatoPorEstado(Pageable pageable) {
        log.debug("Busca a quantidade de candidatos por estado");

        List<Object[]> splitUpNames = Arrays
            .asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
            .stream()
            .map(name -> name.split(" "))
            .collect(Collectors.toList());

        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        /*
         * jdbcTemplate.query(
         * "SELECT estado, count(nome) as qtde FROM candidato group by estado ", new
         * Object[] { "Resultado" }, (rs, rowNum) -> new
         * CandidatoDTO(rs.getString("nome")) ).forEach(customer ->
         * log.info(customer.toString()));
         */

        /*
         * jdbcTemplate.queryForMap(
         * "SELECT estado, count(nome) as qtde FROM candidato group by estado ", "","");
         */

        /*
         * int result = jdbcTemplate.queryForObject(
         * "SELECT estado, count(nome) as qtde FROM candidato group by estado",
         * Integer.class);
         */

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", 1);
        jdbcTemplate.queryForList("SELECT estado as qtde FROM candidato ", String.class);

        /*
         * SqlParameterSource namedParameters = new
         * MapSqlParameterSource().addValue("id", "MG"); jdbcTemplate.queryForMap(
         * " SELECT estado, count(nome) as qtde FROM candidato where estado = :id ",
         * namedParameters, String.class);
         */

        /*
         * jdbcTemplate.query(
         * "SELECT estado, count(nome) as qtde FROM candidato group by estado ", new
         * Object[] { "Resultado" }, (rs, rowNum) -> new
         * CandidatoDTO(rs.getString("nome")) ).forEach(customer ->
         * log.info(customer.toString()));
         */

        //return (Page<CandidatoDTO>) jdbcTemplate;
        //return candidatoRepository.findQuantidadeCandidatoPorEstado(pageable).map(candidatoMapper::toDto);
        /*
         * List<Candidato> listCand =
         * candidatoRepository.findQuantidadeCandidatoPorEstado(pageable);
         * List<CandidatoDTO> listCandidatoDTO = new ArrayList<CandidatoDTO>(); for
         * (Candidato cand : listCand) { CandidatoDTO candDTO =
         * candidatoMapper.toDto(cand); listCandidatoDTO.add(candDTO); }
         */
        return candidatoRepository.findQuantidadeCandidatoPorEstado(pageable).map(candidatoMapper::toDto);
    }

    /**
     *
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatoDTO> findImcMedioEmCadaFaixa(Pageable pageable) {
        log.debug("Request to get all Candidatoes com Imc medio em cada faixa");
        return candidatoRepository.findImcMedioEmCadaFaixaIdade(pageable).map(candidatoMapper::toDto);
    }

    /**
     *
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatoDTO> findPercentualObesosHomens(Pageable pageable) {
        log.debug("Request to get all Candidatoes com Imc medio em cada faixa");
        return candidatoRepository.findPercentualObesosHomens(pageable).map(candidatoMapper::toDto);
    }

    /**
     *
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatoDTO> findPercentualObesosMulheres(Pageable pageable) {
        log.debug("Request to get all Candidatoes com Imc medio em cada faixa");
        return candidatoRepository.findPercentualObesosMulheres(pageable).map(candidatoMapper::toDto);
    }

    /**
     *
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatoDTO> findMediaIdadePorTipoSanguineo(Pageable pageable) {
        log.debug("Request to get all Candidatoes");
        return candidatoRepository.findMediaIdadeEmCadaTipoSanguineo(pageable).map(candidatoMapper::toDto);
    }

    /**
     *
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatoDTO> findQuantidadeDoadoresParaCadaTipoReceptor(Pageable pageable) {
        log.debug("Request to get all Candidatoes");
        return candidatoRepository.findAll(pageable).map(candidatoMapper::toDto);
    }
}
