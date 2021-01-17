package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.service.CandidatoService;
import com.wktech.bancosangue.service.dto.CandidatoDTO;
import com.wktech.bancosangue.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.wktech.bancosangue.domain.Candidato}.
 */
@RestController
@RequestMapping("/api")
public class CandidatoResource {
    private final Logger log = LoggerFactory.getLogger(CandidatoResource.class);

    private static final String ENTITY_NAME = "candidato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CandidatoService candidatoService;

    public CandidatoResource(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    /**
     * {@code POST  /candidatoes} : Create a new candidato.
     *
     * @param candidatoDTO the candidatoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidatoDTO, or with status {@code 400 (Bad Request)} if the candidato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/candidatoes")
    public ResponseEntity<CandidatoDTO> createCandidato(@RequestBody CandidatoDTO candidatoDTO) throws URISyntaxException {
        log.debug("REST request to save Candidato : {}", candidatoDTO);
        if (candidatoDTO.getId() != null) {
            throw new BadRequestAlertException("Não é possível cadastrar candidato com mesmo ID", ENTITY_NAME, "idexists");
        }
        CandidatoDTO result = candidatoService.save(candidatoDTO);

        return ResponseEntity
            .created(new URI("/api/candidatoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/candidatoes/importar")
    public ResponseEntity<CandidatoDTO> importCandidato(@RequestBody List<CandidatoDTO> candidatoDTO) throws URISyntaxException {
        log.debug("REST request to save Candidato : {}", candidatoDTO);

        CandidatoDTO result = candidatoService.importar(candidatoDTO);

        return ResponseEntity
            .created(new URI("/api/candidatoes/importar" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /candidatoes} : Updates an existing candidato.
     *
     * @param candidatoDTO the candidatoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidatoDTO,
     * or with status {@code 400 (Bad Request)} if the candidatoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the candidatoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/candidatoes")
    public ResponseEntity<CandidatoDTO> updateCandidato(@RequestBody CandidatoDTO candidatoDTO) throws URISyntaxException {
        log.debug("REST request to update Candidato : {}", candidatoDTO);
        if (candidatoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CandidatoDTO result = candidatoService.save(candidatoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, candidatoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /candidatoes} : get all the candidatoes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidatoes in body.
     */
    @GetMapping("/candidatoes")
    public ResponseEntity<List<CandidatoDTO>> getAllCandidatoes(Pageable pageable) {
        log.debug("REST request to get a page of Candidatoes");
        Page<CandidatoDTO> page = candidatoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/candidatoes/qtde/estado")
    public ResponseEntity<List<CandidatoDTO>> getQuantidadeCandidatoesPorEstado(Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get a page of Candidatoes");

        @SuppressWarnings("unchecked")
        Page<CandidatoDTO> page = (Page<CandidatoDTO>) candidatoService.findQuantidadeCandidatoPorEstado(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/candidatoes/imc/faixa")
    public ResponseEntity<List<CandidatoDTO>> getImcMedioEmCadaFaixaEtaria(Pageable pageable) {
        log.debug("REST request to get a page of Candidatoes com imc médio por faixa");
        Page<CandidatoDTO> page = candidatoService.findImcMedioEmCadaFaixa(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/candidatoes/percentual/obesos/homens")
    public ResponseEntity<List<CandidatoDTO>> getPercentualObesosHomens(Pageable pageable) {
        log.debug("REST request to get a page of Candidatoes");
        Page<CandidatoDTO> page = candidatoService.findPercentualObesosHomens(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/candidatoes/percentual/obesos/mulheres")
    public ResponseEntity<List<CandidatoDTO>> getPercentualObesosMulheres(Pageable pageable) {
        log.debug("REST request to get a page of Candidatoes");
        Page<CandidatoDTO> page = candidatoService.findPercentualObesosMulheres(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/candidatoes/media/idade/tiposangue")
    public ResponseEntity<List<CandidatoDTO>> getMediaIdadeTipoSanguineo(Pageable pageable) {
        log.debug("REST request to get a page of Candidatoes");
        Page<CandidatoDTO> page = candidatoService.findMediaIdadePorTipoSanguineo(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/candidatoes/quantidade/doador/receptor")
    public ResponseEntity<List<CandidatoDTO>> getQuantidadeDoadorPorTipoReceptor(Pageable pageable) {
        log.debug("REST request to get a page of Candidatoes");
        Page<CandidatoDTO> page = candidatoService.findQuantidadeDoadoresParaCadaTipoReceptor(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /candidatoes/:id} : get the "id" candidato.
     *
     * @param id the id of the candidatoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the candidatoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/candidatoes/{id}")
    public ResponseEntity<CandidatoDTO> getCandidato(@PathVariable Long id) {
        log.debug("REST request to get Candidato : {}", id);
        Optional<CandidatoDTO> candidatoDTO = candidatoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(candidatoDTO);
    }

    /**
     * {@code DELETE  /candidatoes/:id} : delete the "id" candidato.
     *
     * @param id the id of the candidatoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/candidatoes/{id}")
    public ResponseEntity<Void> deleteCandidato(@PathVariable Long id) {
        log.debug("REST request to delete Candidato : {}", id);
        candidatoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
