package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.service.RelatorioQtdCandPorEstadoQueryService;
import com.wktech.bancosangue.service.RelatorioQtdCandPorEstadoService;
import com.wktech.bancosangue.service.dto.RelatorioQtdCandPorEstadoCriteria;
import com.wktech.bancosangue.service.dto.RelatorioQtdCandPorEstadoDTO;
import com.wktech.bancosangue.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.wktech.bancosangue.domain.RelatorioQtdCandPorEstado}.
 */
@RestController
@RequestMapping("/api")
public class RelatorioQtdCandPorEstadoResource {
    private final Logger log = LoggerFactory.getLogger(RelatorioQtdCandPorEstadoResource.class);

    private static final String ENTITY_NAME = "relatorioQtdCandPorEstado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelatorioQtdCandPorEstadoService relatorioQtdCandPorEstadoService;

    private final RelatorioQtdCandPorEstadoQueryService relatorioQtdCandPorEstadoQueryService;

    public RelatorioQtdCandPorEstadoResource(
        RelatorioQtdCandPorEstadoService relatorioQtdCandPorEstadoService,
        RelatorioQtdCandPorEstadoQueryService relatorioQtdCandPorEstadoQueryService
    ) {
        this.relatorioQtdCandPorEstadoService = relatorioQtdCandPorEstadoService;
        this.relatorioQtdCandPorEstadoQueryService = relatorioQtdCandPorEstadoQueryService;
    }

    /**
     * {@code POST  /relatorio-qtd-cand-por-estados} : Create a new relatorioQtdCandPorEstado.
     *
     * @param relatorioQtdCandPorEstadoDTO the relatorioQtdCandPorEstadoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relatorioQtdCandPorEstadoDTO, or with status {@code 400 (Bad Request)} if the relatorioQtdCandPorEstado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relatorio-qtd-cand-por-estados")
    public ResponseEntity<RelatorioQtdCandPorEstadoDTO> createRelatorioQtdCandPorEstado(
        @RequestBody RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save RelatorioQtdCandPorEstado : {}", relatorioQtdCandPorEstadoDTO);
        if (relatorioQtdCandPorEstadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new relatorioQtdCandPorEstado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelatorioQtdCandPorEstadoDTO result = relatorioQtdCandPorEstadoService.save(relatorioQtdCandPorEstadoDTO);
        return ResponseEntity
            .created(new URI("/api/relatorio-qtd-cand-por-estados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relatorio-qtd-cand-por-estados} : Updates an existing relatorioQtdCandPorEstado.
     *
     * @param relatorioQtdCandPorEstadoDTO the relatorioQtdCandPorEstadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatorioQtdCandPorEstadoDTO,
     * or with status {@code 400 (Bad Request)} if the relatorioQtdCandPorEstadoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relatorioQtdCandPorEstadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relatorio-qtd-cand-por-estados")
    public ResponseEntity<RelatorioQtdCandPorEstadoDTO> updateRelatorioQtdCandPorEstado(
        @RequestBody RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update RelatorioQtdCandPorEstado : {}", relatorioQtdCandPorEstadoDTO);
        if (relatorioQtdCandPorEstadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelatorioQtdCandPorEstadoDTO result = relatorioQtdCandPorEstadoService.save(relatorioQtdCandPorEstadoDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relatorioQtdCandPorEstadoDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code GET  /relatorio-qtd-cand-por-estados} : get all the relatorioQtdCandPorEstados.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relatorioQtdCandPorEstados in body.
     */
    @GetMapping("/relatorio-qtd-cand-por-estados")
    public ResponseEntity<List<RelatorioQtdCandPorEstadoDTO>> getAllRelatorioQtdCandPorEstados(
        RelatorioQtdCandPorEstadoCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get RelatorioQtdCandPorEstados by criteria: {}", criteria);
        Page<RelatorioQtdCandPorEstadoDTO> page = relatorioQtdCandPorEstadoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relatorio-qtd-cand-por-estados/count} : count all the relatorioQtdCandPorEstados.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/relatorio-qtd-cand-por-estados/count")
    public ResponseEntity<Long> countRelatorioQtdCandPorEstados(RelatorioQtdCandPorEstadoCriteria criteria) {
        log.debug("REST request to count RelatorioQtdCandPorEstados by criteria: {}", criteria);
        return ResponseEntity.ok().body(relatorioQtdCandPorEstadoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /relatorio-qtd-cand-por-estados/:id} : get the "id" relatorioQtdCandPorEstado.
     *
     * @param id the id of the relatorioQtdCandPorEstadoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relatorioQtdCandPorEstadoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relatorio-qtd-cand-por-estados/{id}")
    public ResponseEntity<RelatorioQtdCandPorEstadoDTO> getRelatorioQtdCandPorEstado(@PathVariable Long id) {
        log.debug("REST request to get RelatorioQtdCandPorEstado : {}", id);
        Optional<RelatorioQtdCandPorEstadoDTO> relatorioQtdCandPorEstadoDTO = relatorioQtdCandPorEstadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relatorioQtdCandPorEstadoDTO);
    }

    /**
     * {@code DELETE  /relatorio-qtd-cand-por-estados/:id} : delete the "id" relatorioQtdCandPorEstado.
     *
     * @param id the id of the relatorioQtdCandPorEstadoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relatorio-qtd-cand-por-estados/{id}")
    public ResponseEntity<Void> deleteRelatorioQtdCandPorEstado(@PathVariable Long id) {
        log.debug("REST request to delete RelatorioQtdCandPorEstado : {}", id);
        relatorioQtdCandPorEstadoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
