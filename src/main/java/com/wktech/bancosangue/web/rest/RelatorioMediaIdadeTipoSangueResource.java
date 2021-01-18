package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.service.RelatorioMediaIdadeTipoSangueQueryService;
import com.wktech.bancosangue.service.RelatorioMediaIdadeTipoSangueService;
import com.wktech.bancosangue.service.dto.RelatorioMediaIdadeTipoSangueCriteria;
import com.wktech.bancosangue.service.dto.RelatorioMediaIdadeTipoSangueDTO;
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
 * REST controller for managing {@link com.wktech.bancosangue.domain.RelatorioMediaIdadeTipoSangue}.
 */
@RestController
@RequestMapping("/api")
public class RelatorioMediaIdadeTipoSangueResource {
    private final Logger log = LoggerFactory.getLogger(RelatorioMediaIdadeTipoSangueResource.class);

    private static final String ENTITY_NAME = "relatorioMediaIdadeTipoSangue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelatorioMediaIdadeTipoSangueService relatorioMediaIdadeTipoSangueService;

    private final RelatorioMediaIdadeTipoSangueQueryService relatorioMediaIdadeTipoSangueQueryService;

    public RelatorioMediaIdadeTipoSangueResource(
        RelatorioMediaIdadeTipoSangueService relatorioMediaIdadeTipoSangueService,
        RelatorioMediaIdadeTipoSangueQueryService relatorioMediaIdadeTipoSangueQueryService
    ) {
        this.relatorioMediaIdadeTipoSangueService = relatorioMediaIdadeTipoSangueService;
        this.relatorioMediaIdadeTipoSangueQueryService = relatorioMediaIdadeTipoSangueQueryService;
    }

    /**
     * {@code POST  /relatorio-media-idade-tipo-sangues} : Create a new relatorioMediaIdadeTipoSangue.
     *
     * @param relatorioMediaIdadeTipoSangueDTO the relatorioMediaIdadeTipoSangueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relatorioMediaIdadeTipoSangueDTO, or with status {@code 400 (Bad Request)} if the relatorioMediaIdadeTipoSangue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relatorio-media-idade-tipo-sangues")
    public ResponseEntity<RelatorioMediaIdadeTipoSangueDTO> createRelatorioMediaIdadeTipoSangue(
        @RequestBody RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save RelatorioMediaIdadeTipoSangue : {}", relatorioMediaIdadeTipoSangueDTO);
        if (relatorioMediaIdadeTipoSangueDTO.getId() != null) {
            throw new BadRequestAlertException("A new relatorioMediaIdadeTipoSangue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelatorioMediaIdadeTipoSangueDTO result = relatorioMediaIdadeTipoSangueService.save(relatorioMediaIdadeTipoSangueDTO);
        return ResponseEntity
            .created(new URI("/api/relatorio-media-idade-tipo-sangues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relatorio-media-idade-tipo-sangues} : Updates an existing relatorioMediaIdadeTipoSangue.
     *
     * @param relatorioMediaIdadeTipoSangueDTO the relatorioMediaIdadeTipoSangueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatorioMediaIdadeTipoSangueDTO,
     * or with status {@code 400 (Bad Request)} if the relatorioMediaIdadeTipoSangueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relatorioMediaIdadeTipoSangueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relatorio-media-idade-tipo-sangues")
    public ResponseEntity<RelatorioMediaIdadeTipoSangueDTO> updateRelatorioMediaIdadeTipoSangue(
        @RequestBody RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update RelatorioMediaIdadeTipoSangue : {}", relatorioMediaIdadeTipoSangueDTO);
        if (relatorioMediaIdadeTipoSangueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelatorioMediaIdadeTipoSangueDTO result = relatorioMediaIdadeTipoSangueService.save(relatorioMediaIdadeTipoSangueDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relatorioMediaIdadeTipoSangueDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code GET  /relatorio-media-idade-tipo-sangues} : get all the relatorioMediaIdadeTipoSangues.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relatorioMediaIdadeTipoSangues in body.
     */
    @GetMapping("/relatorio-media-idade-tipo-sangues")
    public ResponseEntity<List<RelatorioMediaIdadeTipoSangueDTO>> getAllRelatorioMediaIdadeTipoSangues(
        RelatorioMediaIdadeTipoSangueCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get RelatorioMediaIdadeTipoSangues by criteria: {}", criteria);
        Page<RelatorioMediaIdadeTipoSangueDTO> page = relatorioMediaIdadeTipoSangueQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relatorio-media-idade-tipo-sangues/count} : count all the relatorioMediaIdadeTipoSangues.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/relatorio-media-idade-tipo-sangues/count")
    public ResponseEntity<Long> countRelatorioMediaIdadeTipoSangues(RelatorioMediaIdadeTipoSangueCriteria criteria) {
        log.debug("REST request to count RelatorioMediaIdadeTipoSangues by criteria: {}", criteria);
        return ResponseEntity.ok().body(relatorioMediaIdadeTipoSangueQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /relatorio-media-idade-tipo-sangues/:id} : get the "id" relatorioMediaIdadeTipoSangue.
     *
     * @param id the id of the relatorioMediaIdadeTipoSangueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relatorioMediaIdadeTipoSangueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relatorio-media-idade-tipo-sangues/{id}")
    public ResponseEntity<RelatorioMediaIdadeTipoSangueDTO> getRelatorioMediaIdadeTipoSangue(@PathVariable Long id) {
        log.debug("REST request to get RelatorioMediaIdadeTipoSangue : {}", id);
        Optional<RelatorioMediaIdadeTipoSangueDTO> relatorioMediaIdadeTipoSangueDTO = relatorioMediaIdadeTipoSangueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relatorioMediaIdadeTipoSangueDTO);
    }

    /**
     * {@code DELETE  /relatorio-media-idade-tipo-sangues/:id} : delete the "id" relatorioMediaIdadeTipoSangue.
     *
     * @param id the id of the relatorioMediaIdadeTipoSangueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relatorio-media-idade-tipo-sangues/{id}")
    public ResponseEntity<Void> deleteRelatorioMediaIdadeTipoSangue(@PathVariable Long id) {
        log.debug("REST request to delete RelatorioMediaIdadeTipoSangue : {}", id);
        relatorioMediaIdadeTipoSangueService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
