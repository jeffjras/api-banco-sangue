package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.service.DoacaoTipoSanguineoService;
import com.wktech.bancosangue.web.rest.errors.BadRequestAlertException;
import com.wktech.bancosangue.service.dto.DoacaoTipoSanguineoDTO;

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
 * REST controller for managing {@link com.wktech.bancosangue.domain.DoacaoTipoSanguineo}.
 */
@RestController
@RequestMapping("/api")
public class DoacaoTipoSanguineoResource {

    private final Logger log = LoggerFactory.getLogger(DoacaoTipoSanguineoResource.class);

    private static final String ENTITY_NAME = "doacaoTipoSanguineo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DoacaoTipoSanguineoService doacaoTipoSanguineoService;

    public DoacaoTipoSanguineoResource(DoacaoTipoSanguineoService doacaoTipoSanguineoService) {
        this.doacaoTipoSanguineoService = doacaoTipoSanguineoService;
    }

    /**
     * {@code POST  /doacao-tipo-sanguineos} : Create a new doacaoTipoSanguineo.
     *
     * @param doacaoTipoSanguineoDTO the doacaoTipoSanguineoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new doacaoTipoSanguineoDTO, or with status {@code 400 (Bad Request)} if the doacaoTipoSanguineo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/doacao-tipo-sanguineos")
    public ResponseEntity<DoacaoTipoSanguineoDTO> createDoacaoTipoSanguineo(@RequestBody DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO) throws URISyntaxException {
        log.debug("REST request to save DoacaoTipoSanguineo : {}", doacaoTipoSanguineoDTO);
        if (doacaoTipoSanguineoDTO.getId() != null) {
            throw new BadRequestAlertException("A new doacaoTipoSanguineo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoacaoTipoSanguineoDTO result = doacaoTipoSanguineoService.save(doacaoTipoSanguineoDTO);
        return ResponseEntity.created(new URI("/api/doacao-tipo-sanguineos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /doacao-tipo-sanguineos} : Updates an existing doacaoTipoSanguineo.
     *
     * @param doacaoTipoSanguineoDTO the doacaoTipoSanguineoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doacaoTipoSanguineoDTO,
     * or with status {@code 400 (Bad Request)} if the doacaoTipoSanguineoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doacaoTipoSanguineoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/doacao-tipo-sanguineos")
    public ResponseEntity<DoacaoTipoSanguineoDTO> updateDoacaoTipoSanguineo(@RequestBody DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO) throws URISyntaxException {
        log.debug("REST request to update DoacaoTipoSanguineo : {}", doacaoTipoSanguineoDTO);
        if (doacaoTipoSanguineoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoacaoTipoSanguineoDTO result = doacaoTipoSanguineoService.save(doacaoTipoSanguineoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, doacaoTipoSanguineoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /doacao-tipo-sanguineos} : get all the doacaoTipoSanguineos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of doacaoTipoSanguineos in body.
     */
    @GetMapping("/doacao-tipo-sanguineos")
    public List<DoacaoTipoSanguineoDTO> getAllDoacaoTipoSanguineos() {
        log.debug("REST request to get all DoacaoTipoSanguineos");
        return doacaoTipoSanguineoService.findAll();
    }

    /**
     * {@code GET  /doacao-tipo-sanguineos/:id} : get the "id" doacaoTipoSanguineo.
     *
     * @param id the id of the doacaoTipoSanguineoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doacaoTipoSanguineoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/doacao-tipo-sanguineos/{id}")
    public ResponseEntity<DoacaoTipoSanguineoDTO> getDoacaoTipoSanguineo(@PathVariable Long id) {
        log.debug("REST request to get DoacaoTipoSanguineo : {}", id);
        Optional<DoacaoTipoSanguineoDTO> doacaoTipoSanguineoDTO = doacaoTipoSanguineoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doacaoTipoSanguineoDTO);
    }

    /**
     * {@code DELETE  /doacao-tipo-sanguineos/:id} : delete the "id" doacaoTipoSanguineo.
     *
     * @param id the id of the doacaoTipoSanguineoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/doacao-tipo-sanguineos/{id}")
    public ResponseEntity<Void> deleteDoacaoTipoSanguineo(@PathVariable Long id) {
        log.debug("REST request to delete DoacaoTipoSanguineo : {}", id);
        doacaoTipoSanguineoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
