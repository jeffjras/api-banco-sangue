package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.service.RelatorioImcMedioFaixaIdadeQueryService;
import com.wktech.bancosangue.service.RelatorioImcMedioFaixaIdadeService;
import com.wktech.bancosangue.service.dto.RelatorioImcMedioFaixaIdadeCriteria;
import com.wktech.bancosangue.service.dto.RelatorioImcMedioFaixaIdadeDTO;
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
 * REST controller for managing {@link com.wktech.bancosangue.domain.RelatorioImcMedioFaixaIdade}.
 */
@RestController
@RequestMapping("/api")
public class RelatorioImcMedioFaixaIdadeResource {
    private final Logger log = LoggerFactory.getLogger(RelatorioImcMedioFaixaIdadeResource.class);

    private static final String ENTITY_NAME = "relatorioImcMedioFaixaIdade";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelatorioImcMedioFaixaIdadeService relatorioImcMedioFaixaIdadeService;

    private final RelatorioImcMedioFaixaIdadeQueryService relatorioImcMedioFaixaIdadeQueryService;

    public RelatorioImcMedioFaixaIdadeResource(
        RelatorioImcMedioFaixaIdadeService relatorioImcMedioFaixaIdadeService,
        RelatorioImcMedioFaixaIdadeQueryService relatorioImcMedioFaixaIdadeQueryService
    ) {
        this.relatorioImcMedioFaixaIdadeService = relatorioImcMedioFaixaIdadeService;
        this.relatorioImcMedioFaixaIdadeQueryService = relatorioImcMedioFaixaIdadeQueryService;
    }

    /**
     * {@code POST  /relatorio-imc-medio-faixa-idades} : Create a new relatorioImcMedioFaixaIdade.
     *
     * @param relatorioImcMedioFaixaIdadeDTO the relatorioImcMedioFaixaIdadeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relatorioImcMedioFaixaIdadeDTO, or with status {@code 400 (Bad Request)} if the relatorioImcMedioFaixaIdade has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relatorio-imc-medio-faixa-idades")
    public ResponseEntity<RelatorioImcMedioFaixaIdadeDTO> createRelatorioImcMedioFaixaIdade(
        @RequestBody RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save RelatorioImcMedioFaixaIdade : {}", relatorioImcMedioFaixaIdadeDTO);
        if (relatorioImcMedioFaixaIdadeDTO.getId() != null) {
            throw new BadRequestAlertException("A new relatorioImcMedioFaixaIdade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelatorioImcMedioFaixaIdadeDTO result = relatorioImcMedioFaixaIdadeService.save(relatorioImcMedioFaixaIdadeDTO);
        return ResponseEntity
            .created(new URI("/api/relatorio-imc-medio-faixa-idades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relatorio-imc-medio-faixa-idades} : Updates an existing relatorioImcMedioFaixaIdade.
     *
     * @param relatorioImcMedioFaixaIdadeDTO the relatorioImcMedioFaixaIdadeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatorioImcMedioFaixaIdadeDTO,
     * or with status {@code 400 (Bad Request)} if the relatorioImcMedioFaixaIdadeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relatorioImcMedioFaixaIdadeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relatorio-imc-medio-faixa-idades")
    public ResponseEntity<RelatorioImcMedioFaixaIdadeDTO> updateRelatorioImcMedioFaixaIdade(
        @RequestBody RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update RelatorioImcMedioFaixaIdade : {}", relatorioImcMedioFaixaIdadeDTO);
        if (relatorioImcMedioFaixaIdadeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelatorioImcMedioFaixaIdadeDTO result = relatorioImcMedioFaixaIdadeService.save(relatorioImcMedioFaixaIdadeDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relatorioImcMedioFaixaIdadeDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code GET  /relatorio-imc-medio-faixa-idades} : get all the relatorioImcMedioFaixaIdades.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relatorioImcMedioFaixaIdades in body.
     */
    @GetMapping("/relatorio-imc-medio-faixa-idades")
    public ResponseEntity<List<RelatorioImcMedioFaixaIdadeDTO>> getAllRelatorioImcMedioFaixaIdades(
        RelatorioImcMedioFaixaIdadeCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get RelatorioImcMedioFaixaIdades by criteria: {}", criteria);
        Page<RelatorioImcMedioFaixaIdadeDTO> page = relatorioImcMedioFaixaIdadeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relatorio-imc-medio-faixa-idades/count} : count all the relatorioImcMedioFaixaIdades.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/relatorio-imc-medio-faixa-idades/count")
    public ResponseEntity<Long> countRelatorioImcMedioFaixaIdades(RelatorioImcMedioFaixaIdadeCriteria criteria) {
        log.debug("REST request to count RelatorioImcMedioFaixaIdades by criteria: {}", criteria);
        return ResponseEntity.ok().body(relatorioImcMedioFaixaIdadeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /relatorio-imc-medio-faixa-idades/:id} : get the "id" relatorioImcMedioFaixaIdade.
     *
     * @param id the id of the relatorioImcMedioFaixaIdadeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relatorioImcMedioFaixaIdadeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relatorio-imc-medio-faixa-idades/{id}")
    public ResponseEntity<RelatorioImcMedioFaixaIdadeDTO> getRelatorioImcMedioFaixaIdade(@PathVariable Long id) {
        log.debug("REST request to get RelatorioImcMedioFaixaIdade : {}", id);
        Optional<RelatorioImcMedioFaixaIdadeDTO> relatorioImcMedioFaixaIdadeDTO = relatorioImcMedioFaixaIdadeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relatorioImcMedioFaixaIdadeDTO);
    }

    /**
     * {@code DELETE  /relatorio-imc-medio-faixa-idades/:id} : delete the "id" relatorioImcMedioFaixaIdade.
     *
     * @param id the id of the relatorioImcMedioFaixaIdadeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relatorio-imc-medio-faixa-idades/{id}")
    public ResponseEntity<Void> deleteRelatorioImcMedioFaixaIdade(@PathVariable Long id) {
        log.debug("REST request to delete RelatorioImcMedioFaixaIdade : {}", id);
        relatorioImcMedioFaixaIdadeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
