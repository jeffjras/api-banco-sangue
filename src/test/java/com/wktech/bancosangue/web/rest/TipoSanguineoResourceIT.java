package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.TipoSanguineo;
import com.wktech.bancosangue.repository.TipoSanguineoRepository;
import com.wktech.bancosangue.service.TipoSanguineoService;
import com.wktech.bancosangue.service.dto.TipoSanguineoDTO;
import com.wktech.bancosangue.service.mapper.TipoSanguineoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TipoSanguineoResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TipoSanguineoResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private TipoSanguineoRepository tipoSanguineoRepository;

    @Autowired
    private TipoSanguineoMapper tipoSanguineoMapper;

    @Autowired
    private TipoSanguineoService tipoSanguineoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTipoSanguineoMockMvc;

    private TipoSanguineo tipoSanguineo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoSanguineo createEntity(EntityManager em) {
        TipoSanguineo tipoSanguineo = new TipoSanguineo()
            .descricao(DEFAULT_DESCRICAO);
        return tipoSanguineo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoSanguineo createUpdatedEntity(EntityManager em) {
        TipoSanguineo tipoSanguineo = new TipoSanguineo()
            .descricao(UPDATED_DESCRICAO);
        return tipoSanguineo;
    }

    @BeforeEach
    public void initTest() {
        tipoSanguineo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoSanguineo() throws Exception {
        int databaseSizeBeforeCreate = tipoSanguineoRepository.findAll().size();
        // Create the TipoSanguineo
        TipoSanguineoDTO tipoSanguineoDTO = tipoSanguineoMapper.toDto(tipoSanguineo);
        restTipoSanguineoMockMvc.perform(post("/api/tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoSanguineoDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoSanguineo in the database
        List<TipoSanguineo> tipoSanguineoList = tipoSanguineoRepository.findAll();
        assertThat(tipoSanguineoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoSanguineo testTipoSanguineo = tipoSanguineoList.get(tipoSanguineoList.size() - 1);
        assertThat(testTipoSanguineo.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createTipoSanguineoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoSanguineoRepository.findAll().size();

        // Create the TipoSanguineo with an existing ID
        tipoSanguineo.setId(1L);
        TipoSanguineoDTO tipoSanguineoDTO = tipoSanguineoMapper.toDto(tipoSanguineo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoSanguineoMockMvc.perform(post("/api/tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoSanguineoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoSanguineo in the database
        List<TipoSanguineo> tipoSanguineoList = tipoSanguineoRepository.findAll();
        assertThat(tipoSanguineoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTipoSanguineos() throws Exception {
        // Initialize the database
        tipoSanguineoRepository.saveAndFlush(tipoSanguineo);

        // Get all the tipoSanguineoList
        restTipoSanguineoMockMvc.perform(get("/api/tipo-sanguineos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoSanguineo.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getTipoSanguineo() throws Exception {
        // Initialize the database
        tipoSanguineoRepository.saveAndFlush(tipoSanguineo);

        // Get the tipoSanguineo
        restTipoSanguineoMockMvc.perform(get("/api/tipo-sanguineos/{id}", tipoSanguineo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoSanguineo.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }
    @Test
    @Transactional
    public void getNonExistingTipoSanguineo() throws Exception {
        // Get the tipoSanguineo
        restTipoSanguineoMockMvc.perform(get("/api/tipo-sanguineos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoSanguineo() throws Exception {
        // Initialize the database
        tipoSanguineoRepository.saveAndFlush(tipoSanguineo);

        int databaseSizeBeforeUpdate = tipoSanguineoRepository.findAll().size();

        // Update the tipoSanguineo
        TipoSanguineo updatedTipoSanguineo = tipoSanguineoRepository.findById(tipoSanguineo.getId()).get();
        // Disconnect from session so that the updates on updatedTipoSanguineo are not directly saved in db
        em.detach(updatedTipoSanguineo);
        updatedTipoSanguineo
            .descricao(UPDATED_DESCRICAO);
        TipoSanguineoDTO tipoSanguineoDTO = tipoSanguineoMapper.toDto(updatedTipoSanguineo);

        restTipoSanguineoMockMvc.perform(put("/api/tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoSanguineoDTO)))
            .andExpect(status().isOk());

        // Validate the TipoSanguineo in the database
        List<TipoSanguineo> tipoSanguineoList = tipoSanguineoRepository.findAll();
        assertThat(tipoSanguineoList).hasSize(databaseSizeBeforeUpdate);
        TipoSanguineo testTipoSanguineo = tipoSanguineoList.get(tipoSanguineoList.size() - 1);
        assertThat(testTipoSanguineo.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoSanguineo() throws Exception {
        int databaseSizeBeforeUpdate = tipoSanguineoRepository.findAll().size();

        // Create the TipoSanguineo
        TipoSanguineoDTO tipoSanguineoDTO = tipoSanguineoMapper.toDto(tipoSanguineo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoSanguineoMockMvc.perform(put("/api/tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoSanguineoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoSanguineo in the database
        List<TipoSanguineo> tipoSanguineoList = tipoSanguineoRepository.findAll();
        assertThat(tipoSanguineoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoSanguineo() throws Exception {
        // Initialize the database
        tipoSanguineoRepository.saveAndFlush(tipoSanguineo);

        int databaseSizeBeforeDelete = tipoSanguineoRepository.findAll().size();

        // Delete the tipoSanguineo
        restTipoSanguineoMockMvc.perform(delete("/api/tipo-sanguineos/{id}", tipoSanguineo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoSanguineo> tipoSanguineoList = tipoSanguineoRepository.findAll();
        assertThat(tipoSanguineoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
