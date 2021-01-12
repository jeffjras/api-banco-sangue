package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.service.RecepcaoTipoSanguineoService;
import com.wktech.bancosangue.web.rest.errors.BadRequestAlertException;
import com.wktech.bancosangue.service.dto.RecepcaoTipoSanguineoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.wktech.bancosangue.domain.RecepcaoTipoSanguineo}.
 */
@RestController
@RequestMapping("/api")
public class RecepcaoTipoSanguineoResource {

    private final Logger log = LoggerFactory.getLogger(RecepcaoTipoSanguineoResource.class);

    private static final String ENTITY_NAME = "recepcaoTipoSanguineo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecepcaoTipoSanguineoService recepcaoTipoSanguineoService;

    public RecepcaoTipoSanguineoResource(RecepcaoTipoSanguineoService recepcaoTipoSanguineoService) {
        this.recepcaoTipoSanguineoService = recepcaoTipoSanguineoService;
    }

    /**
     * {@code POST  /recepcao-tipo-sanguineos} : Create a new recepcaoTipoSanguineo.
     *
     * @param recepcaoTipoSanguineoDTO the recepcaoTipoSanguineoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recepcaoTipoSanguineoDTO, or with status {@code 400 (Bad Request)} if the recepcaoTipoSanguineo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/recepcao-tipo-sanguineos")
    public ResponseEntity<RecepcaoTipoSanguineoDTO> createRecepcaoTipoSanguineo(@RequestBody RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO) throws URISyntaxException {
        log.debug("REST request to save RecepcaoTipoSanguineo : {}", recepcaoTipoSanguineoDTO);
        if (recepcaoTipoSanguineoDTO.getId() != null) {
            throw new BadRequestAlertException("A new recepcaoTipoSanguineo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecepcaoTipoSanguineoDTO result = recepcaoTipoSanguineoService.save(recepcaoTipoSanguineoDTO);
        return ResponseEntity.created(new URI("/api/recepcao-tipo-sanguineos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /recepcao-tipo-sanguineos} : Updates an existing recepcaoTipoSanguineo.
     *
     * @param recepcaoTipoSanguineoDTO the recepcaoTipoSanguineoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recepcaoTipoSanguineoDTO,
     * or with status {@code 400 (Bad Request)} if the recepcaoTipoSanguineoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recepcaoTipoSanguineoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/recepcao-tipo-sanguineos")
    public ResponseEntity<RecepcaoTipoSanguineoDTO> updateRecepcaoTipoSanguineo(@RequestBody RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO) throws URISyntaxException {
        log.debug("REST request to update RecepcaoTipoSanguineo : {}", recepcaoTipoSanguineoDTO);
        if (recepcaoTipoSanguineoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecepcaoTipoSanguineoDTO result = recepcaoTipoSanguineoService.save(recepcaoTipoSanguineoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recepcaoTipoSanguineoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /recepcao-tipo-sanguineos} : get all the recepcaoTipoSanguineos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recepcaoTipoSanguineos in body.
     */
    @GetMapping("/recepcao-tipo-sanguineos")
    public List<RecepcaoTipoSanguineoDTO> getAllRecepcaoTipoSanguineos() {
        log.debug("REST request to get all RecepcaoTipoSanguineos");
        return recepcaoTipoSanguineoService.findAll();
    }

    /**
     * {@code GET  /recepcao-tipo-sanguineos/:id} : get the "id" recepcaoTipoSanguineo.
     *
     * @param id the id of the recepcaoTipoSanguineoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recepcaoTipoSanguineoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recepcao-tipo-sanguineos/{id}")
    public ResponseEntity<RecepcaoTipoSanguineoDTO> getRecepcaoTipoSanguineo(@PathVariable Long id) {
        log.debug("REST request to get RecepcaoTipoSanguineo : {}", id);
        Optional<RecepcaoTipoSanguineoDTO> recepcaoTipoSanguineoDTO = recepcaoTipoSanguineoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recepcaoTipoSanguineoDTO);
    }

    /**
     * {@code DELETE  /recepcao-tipo-sanguineos/:id} : delete the "id" recepcaoTipoSanguineo.
     *
     * @param id the id of the recepcaoTipoSanguineoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recepcao-tipo-sanguineos/{id}")
    public ResponseEntity<Void> deleteRecepcaoTipoSanguineo(@PathVariable Long id) {
        log.debug("REST request to delete RecepcaoTipoSanguineo : {}", id);
        recepcaoTipoSanguineoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
