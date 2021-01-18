package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.service.RelatorioPercObesosHomensQueryService;
import com.wktech.bancosangue.service.RelatorioPercObesosHomensService;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosHomensCriteria;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosHomensDTO;
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
 * REST controller for managing {@link com.wktech.bancosangue.domain.RelatorioPercObesosHomens}.
 */
@RestController
@RequestMapping("/api")
public class RelatorioPercObesosHomensResource {
    private final Logger log = LoggerFactory.getLogger(RelatorioPercObesosHomensResource.class);

    private static final String ENTITY_NAME = "relatorioPercObesosHomens";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelatorioPercObesosHomensService relatorioPercObesosHomensService;

    private final RelatorioPercObesosHomensQueryService relatorioPercObesosHomensQueryService;

    public RelatorioPercObesosHomensResource(
        RelatorioPercObesosHomensService relatorioPercObesosHomensService,
        RelatorioPercObesosHomensQueryService relatorioPercObesosHomensQueryService
    ) {
        this.relatorioPercObesosHomensService = relatorioPercObesosHomensService;
        this.relatorioPercObesosHomensQueryService = relatorioPercObesosHomensQueryService;
    }

    /**
     * {@code POST  /relatorio-perc-obesos-homens} : Create a new relatorioPercObesosHomens.
     *
     * @param relatorioPercObesosHomensDTO the relatorioPercObesosHomensDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relatorioPercObesosHomensDTO, or with status {@code 400 (Bad Request)} if the relatorioPercObesosHomens has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relatorio-perc-obesos-homens")
    public ResponseEntity<RelatorioPercObesosHomensDTO> createRelatorioPercObesosHomens(
        @RequestBody RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save RelatorioPercObesosHomens : {}", relatorioPercObesosHomensDTO);
        if (relatorioPercObesosHomensDTO.getId() != null) {
            throw new BadRequestAlertException("A new relatorioPercObesosHomens cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelatorioPercObesosHomensDTO result = relatorioPercObesosHomensService.save(relatorioPercObesosHomensDTO);
        return ResponseEntity
            .created(new URI("/api/relatorio-perc-obesos-homens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relatorio-perc-obesos-homens} : Updates an existing relatorioPercObesosHomens.
     *
     * @param relatorioPercObesosHomensDTO the relatorioPercObesosHomensDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatorioPercObesosHomensDTO,
     * or with status {@code 400 (Bad Request)} if the relatorioPercObesosHomensDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relatorioPercObesosHomensDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relatorio-perc-obesos-homens")
    public ResponseEntity<RelatorioPercObesosHomensDTO> updateRelatorioPercObesosHomens(
        @RequestBody RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update RelatorioPercObesosHomens : {}", relatorioPercObesosHomensDTO);
        if (relatorioPercObesosHomensDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelatorioPercObesosHomensDTO result = relatorioPercObesosHomensService.save(relatorioPercObesosHomensDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relatorioPercObesosHomensDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code GET  /relatorio-perc-obesos-homens} : get all the relatorioPercObesosHomens.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relatorioPercObesosHomens in body.
     */
    @GetMapping("/relatorio-perc-obesos-homens")
    public ResponseEntity<List<RelatorioPercObesosHomensDTO>> getAllRelatorioPercObesosHomens(
        RelatorioPercObesosHomensCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get RelatorioPercObesosHomens by criteria: {}", criteria);
        Page<RelatorioPercObesosHomensDTO> page = relatorioPercObesosHomensQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relatorio-perc-obesos-homens/count} : count all the relatorioPercObesosHomens.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/relatorio-perc-obesos-homens/count")
    public ResponseEntity<Long> countRelatorioPercObesosHomens(RelatorioPercObesosHomensCriteria criteria) {
        log.debug("REST request to count RelatorioPercObesosHomens by criteria: {}", criteria);
        return ResponseEntity.ok().body(relatorioPercObesosHomensQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /relatorio-perc-obesos-homens/:id} : get the "id" relatorioPercObesosHomens.
     *
     * @param id the id of the relatorioPercObesosHomensDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relatorioPercObesosHomensDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relatorio-perc-obesos-homens/{id}")
    public ResponseEntity<RelatorioPercObesosHomensDTO> getRelatorioPercObesosHomens(@PathVariable Long id) {
        log.debug("REST request to get RelatorioPercObesosHomens : {}", id);
        Optional<RelatorioPercObesosHomensDTO> relatorioPercObesosHomensDTO = relatorioPercObesosHomensService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relatorioPercObesosHomensDTO);
    }

    /**
     * {@code DELETE  /relatorio-perc-obesos-homens/:id} : delete the "id" relatorioPercObesosHomens.
     *
     * @param id the id of the relatorioPercObesosHomensDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relatorio-perc-obesos-homens/{id}")
    public ResponseEntity<Void> deleteRelatorioPercObesosHomens(@PathVariable Long id) {
        log.debug("REST request to delete RelatorioPercObesosHomens : {}", id);
        relatorioPercObesosHomensService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
