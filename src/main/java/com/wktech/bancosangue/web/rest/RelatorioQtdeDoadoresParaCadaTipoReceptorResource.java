package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.service.RelatorioQtdeDoadoresParaCadaTipoReceptorQueryService;
import com.wktech.bancosangue.service.RelatorioQtdeDoadoresParaCadaTipoReceptorService;
import com.wktech.bancosangue.service.dto.RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria;
import com.wktech.bancosangue.service.dto.RelatorioQtdeDoadoresParaCadaTipoReceptorDTO;
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
 * REST controller for managing {@link com.wktech.bancosangue.domain.RelatorioQtdeDoadoresParaCadaTipoReceptor}.
 */
@RestController
@RequestMapping("/api")
public class RelatorioQtdeDoadoresParaCadaTipoReceptorResource {
    private final Logger log = LoggerFactory.getLogger(RelatorioQtdeDoadoresParaCadaTipoReceptorResource.class);

    private static final String ENTITY_NAME = "relatorioQtdeDoadoresParaCadaTipoReceptor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelatorioQtdeDoadoresParaCadaTipoReceptorService relatorioQtdeDoadoresParaCadaTipoReceptorService;

    private final RelatorioQtdeDoadoresParaCadaTipoReceptorQueryService relatorioQtdeDoadoresParaCadaTipoReceptorQueryService;

    public RelatorioQtdeDoadoresParaCadaTipoReceptorResource(
        RelatorioQtdeDoadoresParaCadaTipoReceptorService relatorioQtdeDoadoresParaCadaTipoReceptorService,
        RelatorioQtdeDoadoresParaCadaTipoReceptorQueryService relatorioQtdeDoadoresParaCadaTipoReceptorQueryService
    ) {
        this.relatorioQtdeDoadoresParaCadaTipoReceptorService = relatorioQtdeDoadoresParaCadaTipoReceptorService;
        this.relatorioQtdeDoadoresParaCadaTipoReceptorQueryService = relatorioQtdeDoadoresParaCadaTipoReceptorQueryService;
    }

    /**
     * {@code POST  /relatorio-qtde-doadores-para-cada-tipo-receptors} : Create a new relatorioQtdeDoadoresParaCadaTipoReceptor.
     *
     * @param relatorioQtdeDoadoresParaCadaTipoReceptorDTO the relatorioQtdeDoadoresParaCadaTipoReceptorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relatorioQtdeDoadoresParaCadaTipoReceptorDTO, or with status {@code 400 (Bad Request)} if the relatorioQtdeDoadoresParaCadaTipoReceptor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relatorio-qtde-doadores-para-cada-tipo-receptors")
    public ResponseEntity<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> createRelatorioQtdeDoadoresParaCadaTipoReceptor(
        @RequestBody RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save RelatorioQtdeDoadoresParaCadaTipoReceptor : {}", relatorioQtdeDoadoresParaCadaTipoReceptorDTO);
        if (relatorioQtdeDoadoresParaCadaTipoReceptorDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new relatorioQtdeDoadoresParaCadaTipoReceptor cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO result = relatorioQtdeDoadoresParaCadaTipoReceptorService.save(
            relatorioQtdeDoadoresParaCadaTipoReceptorDTO
        );
        return ResponseEntity
            .created(new URI("/api/relatorio-qtde-doadores-para-cada-tipo-receptors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relatorio-qtde-doadores-para-cada-tipo-receptors} : Updates an existing relatorioQtdeDoadoresParaCadaTipoReceptor.
     *
     * @param relatorioQtdeDoadoresParaCadaTipoReceptorDTO the relatorioQtdeDoadoresParaCadaTipoReceptorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatorioQtdeDoadoresParaCadaTipoReceptorDTO,
     * or with status {@code 400 (Bad Request)} if the relatorioQtdeDoadoresParaCadaTipoReceptorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relatorioQtdeDoadoresParaCadaTipoReceptorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relatorio-qtde-doadores-para-cada-tipo-receptors")
    public ResponseEntity<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> updateRelatorioQtdeDoadoresParaCadaTipoReceptor(
        @RequestBody RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update RelatorioQtdeDoadoresParaCadaTipoReceptor : {}", relatorioQtdeDoadoresParaCadaTipoReceptorDTO);
        if (relatorioQtdeDoadoresParaCadaTipoReceptorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO result = relatorioQtdeDoadoresParaCadaTipoReceptorService.save(
            relatorioQtdeDoadoresParaCadaTipoReceptorDTO
        );
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    relatorioQtdeDoadoresParaCadaTipoReceptorDTO.getId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code GET  /relatorio-qtde-doadores-para-cada-tipo-receptors} : get all the relatorioQtdeDoadoresParaCadaTipoReceptors.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relatorioQtdeDoadoresParaCadaTipoReceptors in body.
     */
    @GetMapping("/relatorio-qtde-doadores-para-cada-tipo-receptors")
    public ResponseEntity<List<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO>> getAllRelatorioQtdeDoadoresParaCadaTipoReceptors(
        RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get RelatorioQtdeDoadoresParaCadaTipoReceptors by criteria: {}", criteria);
        Page<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> page = relatorioQtdeDoadoresParaCadaTipoReceptorQueryService.findByCriteria(
            criteria,
            pageable
        );
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relatorio-qtde-doadores-para-cada-tipo-receptors/count} : count all the relatorioQtdeDoadoresParaCadaTipoReceptors.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/relatorio-qtde-doadores-para-cada-tipo-receptors/count")
    public ResponseEntity<Long> countRelatorioQtdeDoadoresParaCadaTipoReceptors(
        RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria criteria
    ) {
        log.debug("REST request to count RelatorioQtdeDoadoresParaCadaTipoReceptors by criteria: {}", criteria);
        return ResponseEntity.ok().body(relatorioQtdeDoadoresParaCadaTipoReceptorQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /relatorio-qtde-doadores-para-cada-tipo-receptors/:id} : get the "id" relatorioQtdeDoadoresParaCadaTipoReceptor.
     *
     * @param id the id of the relatorioQtdeDoadoresParaCadaTipoReceptorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relatorioQtdeDoadoresParaCadaTipoReceptorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relatorio-qtde-doadores-para-cada-tipo-receptors/{id}")
    public ResponseEntity<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> getRelatorioQtdeDoadoresParaCadaTipoReceptor(
        @PathVariable Long id
    ) {
        log.debug("REST request to get RelatorioQtdeDoadoresParaCadaTipoReceptor : {}", id);
        Optional<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> relatorioQtdeDoadoresParaCadaTipoReceptorDTO = relatorioQtdeDoadoresParaCadaTipoReceptorService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(relatorioQtdeDoadoresParaCadaTipoReceptorDTO);
    }

    /**
     * {@code DELETE  /relatorio-qtde-doadores-para-cada-tipo-receptors/:id} : delete the "id" relatorioQtdeDoadoresParaCadaTipoReceptor.
     *
     * @param id the id of the relatorioQtdeDoadoresParaCadaTipoReceptorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relatorio-qtde-doadores-para-cada-tipo-receptors/{id}")
    public ResponseEntity<Void> deleteRelatorioQtdeDoadoresParaCadaTipoReceptor(@PathVariable Long id) {
        log.debug("REST request to delete RelatorioQtdeDoadoresParaCadaTipoReceptor : {}", id);
        relatorioQtdeDoadoresParaCadaTipoReceptorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
