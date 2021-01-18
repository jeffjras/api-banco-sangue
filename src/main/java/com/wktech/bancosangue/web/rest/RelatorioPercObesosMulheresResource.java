package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.service.RelatorioPercObesosMulheresQueryService;
import com.wktech.bancosangue.service.RelatorioPercObesosMulheresService;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosMulheresCriteria;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosMulheresDTO;
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
 * REST controller for managing {@link com.wktech.bancosangue.domain.RelatorioPercObesosMulheres}.
 */
@RestController
@RequestMapping("/api")
public class RelatorioPercObesosMulheresResource {
    private final Logger log = LoggerFactory.getLogger(RelatorioPercObesosMulheresResource.class);

    private static final String ENTITY_NAME = "relatorioPercObesosMulheres";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelatorioPercObesosMulheresService relatorioPercObesosMulheresService;

    private final RelatorioPercObesosMulheresQueryService relatorioPercObesosMulheresQueryService;

    public RelatorioPercObesosMulheresResource(
        RelatorioPercObesosMulheresService relatorioPercObesosMulheresService,
        RelatorioPercObesosMulheresQueryService relatorioPercObesosMulheresQueryService
    ) {
        this.relatorioPercObesosMulheresService = relatorioPercObesosMulheresService;
        this.relatorioPercObesosMulheresQueryService = relatorioPercObesosMulheresQueryService;
    }

    /**
     * {@code POST  /relatorio-perc-obesos-mulheres} : Create a new relatorioPercObesosMulheres.
     *
     * @param relatorioPercObesosMulheresDTO the relatorioPercObesosMulheresDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relatorioPercObesosMulheresDTO, or with status {@code 400 (Bad Request)} if the relatorioPercObesosMulheres has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relatorio-perc-obesos-mulheres")
    public ResponseEntity<RelatorioPercObesosMulheresDTO> createRelatorioPercObesosMulheres(
        @RequestBody RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save RelatorioPercObesosMulheres : {}", relatorioPercObesosMulheresDTO);
        if (relatorioPercObesosMulheresDTO.getId() != null) {
            throw new BadRequestAlertException("A new relatorioPercObesosMulheres cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelatorioPercObesosMulheresDTO result = relatorioPercObesosMulheresService.save(relatorioPercObesosMulheresDTO);
        return ResponseEntity
            .created(new URI("/api/relatorio-perc-obesos-mulheres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relatorio-perc-obesos-mulheres} : Updates an existing relatorioPercObesosMulheres.
     *
     * @param relatorioPercObesosMulheresDTO the relatorioPercObesosMulheresDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatorioPercObesosMulheresDTO,
     * or with status {@code 400 (Bad Request)} if the relatorioPercObesosMulheresDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relatorioPercObesosMulheresDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relatorio-perc-obesos-mulheres")
    public ResponseEntity<RelatorioPercObesosMulheresDTO> updateRelatorioPercObesosMulheres(
        @RequestBody RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update RelatorioPercObesosMulheres : {}", relatorioPercObesosMulheresDTO);
        if (relatorioPercObesosMulheresDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelatorioPercObesosMulheresDTO result = relatorioPercObesosMulheresService.save(relatorioPercObesosMulheresDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relatorioPercObesosMulheresDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code GET  /relatorio-perc-obesos-mulheres} : get all the relatorioPercObesosMulheres.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relatorioPercObesosMulheres in body.
     */
    @GetMapping("/relatorio-perc-obesos-mulheres")
    public ResponseEntity<List<RelatorioPercObesosMulheresDTO>> getAllRelatorioPercObesosMulheres(
        RelatorioPercObesosMulheresCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get RelatorioPercObesosMulheres by criteria: {}", criteria);
        Page<RelatorioPercObesosMulheresDTO> page = relatorioPercObesosMulheresQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relatorio-perc-obesos-mulheres/count} : count all the relatorioPercObesosMulheres.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/relatorio-perc-obesos-mulheres/count")
    public ResponseEntity<Long> countRelatorioPercObesosMulheres(RelatorioPercObesosMulheresCriteria criteria) {
        log.debug("REST request to count RelatorioPercObesosMulheres by criteria: {}", criteria);
        return ResponseEntity.ok().body(relatorioPercObesosMulheresQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /relatorio-perc-obesos-mulheres/:id} : get the "id" relatorioPercObesosMulheres.
     *
     * @param id the id of the relatorioPercObesosMulheresDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relatorioPercObesosMulheresDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relatorio-perc-obesos-mulheres/{id}")
    public ResponseEntity<RelatorioPercObesosMulheresDTO> getRelatorioPercObesosMulheres(@PathVariable Long id) {
        log.debug("REST request to get RelatorioPercObesosMulheres : {}", id);
        Optional<RelatorioPercObesosMulheresDTO> relatorioPercObesosMulheresDTO = relatorioPercObesosMulheresService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relatorioPercObesosMulheresDTO);
    }

    /**
     * {@code DELETE  /relatorio-perc-obesos-mulheres/:id} : delete the "id" relatorioPercObesosMulheres.
     *
     * @param id the id of the relatorioPercObesosMulheresDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relatorio-perc-obesos-mulheres/{id}")
    public ResponseEntity<Void> deleteRelatorioPercObesosMulheres(@PathVariable Long id) {
        log.debug("REST request to delete RelatorioPercObesosMulheres : {}", id);
        relatorioPercObesosMulheresService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
