package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.RecepcaoTipoSanguineo;
import com.wktech.bancosangue.repository.RecepcaoTipoSanguineoRepository;
import com.wktech.bancosangue.service.RecepcaoTipoSanguineoService;
import com.wktech.bancosangue.service.dto.RecepcaoTipoSanguineoDTO;
import com.wktech.bancosangue.service.mapper.RecepcaoTipoSanguineoMapper;

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
 * Integration tests for the {@link RecepcaoTipoSanguineoResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RecepcaoTipoSanguineoResourceIT {

    private static final String DEFAULT_PODE_RECEBER_DE = "AAAAAAAAAA";
    private static final String UPDATED_PODE_RECEBER_DE = "BBBBBBBBBB";

    @Autowired
    private RecepcaoTipoSanguineoRepository recepcaoTipoSanguineoRepository;

    @Autowired
    private RecepcaoTipoSanguineoMapper recepcaoTipoSanguineoMapper;

    @Autowired
    private RecepcaoTipoSanguineoService recepcaoTipoSanguineoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRecepcaoTipoSanguineoMockMvc;

    private RecepcaoTipoSanguineo recepcaoTipoSanguineo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecepcaoTipoSanguineo createEntity(EntityManager em) {
        RecepcaoTipoSanguineo recepcaoTipoSanguineo = new RecepcaoTipoSanguineo()
            .podeReceberDe(DEFAULT_PODE_RECEBER_DE);
        return recepcaoTipoSanguineo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecepcaoTipoSanguineo createUpdatedEntity(EntityManager em) {
        RecepcaoTipoSanguineo recepcaoTipoSanguineo = new RecepcaoTipoSanguineo()
            .podeReceberDe(UPDATED_PODE_RECEBER_DE);
        return recepcaoTipoSanguineo;
    }

    @BeforeEach
    public void initTest() {
        recepcaoTipoSanguineo = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecepcaoTipoSanguineo() throws Exception {
        int databaseSizeBeforeCreate = recepcaoTipoSanguineoRepository.findAll().size();
        // Create the RecepcaoTipoSanguineo
        RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO = recepcaoTipoSanguineoMapper.toDto(recepcaoTipoSanguineo);
        restRecepcaoTipoSanguineoMockMvc.perform(post("/api/recepcao-tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recepcaoTipoSanguineoDTO)))
            .andExpect(status().isCreated());

        // Validate the RecepcaoTipoSanguineo in the database
        List<RecepcaoTipoSanguineo> recepcaoTipoSanguineoList = recepcaoTipoSanguineoRepository.findAll();
        assertThat(recepcaoTipoSanguineoList).hasSize(databaseSizeBeforeCreate + 1);
        RecepcaoTipoSanguineo testRecepcaoTipoSanguineo = recepcaoTipoSanguineoList.get(recepcaoTipoSanguineoList.size() - 1);
        assertThat(testRecepcaoTipoSanguineo.getPodeReceberDe()).isEqualTo(DEFAULT_PODE_RECEBER_DE);
    }

    @Test
    @Transactional
    public void createRecepcaoTipoSanguineoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recepcaoTipoSanguineoRepository.findAll().size();

        // Create the RecepcaoTipoSanguineo with an existing ID
        recepcaoTipoSanguineo.setId(1L);
        RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO = recepcaoTipoSanguineoMapper.toDto(recepcaoTipoSanguineo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecepcaoTipoSanguineoMockMvc.perform(post("/api/recepcao-tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recepcaoTipoSanguineoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecepcaoTipoSanguineo in the database
        List<RecepcaoTipoSanguineo> recepcaoTipoSanguineoList = recepcaoTipoSanguineoRepository.findAll();
        assertThat(recepcaoTipoSanguineoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRecepcaoTipoSanguineos() throws Exception {
        // Initialize the database
        recepcaoTipoSanguineoRepository.saveAndFlush(recepcaoTipoSanguineo);

        // Get all the recepcaoTipoSanguineoList
        restRecepcaoTipoSanguineoMockMvc.perform(get("/api/recepcao-tipo-sanguineos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recepcaoTipoSanguineo.getId().intValue())))
            .andExpect(jsonPath("$.[*].podeReceberDe").value(hasItem(DEFAULT_PODE_RECEBER_DE)));
    }
    
    @Test
    @Transactional
    public void getRecepcaoTipoSanguineo() throws Exception {
        // Initialize the database
        recepcaoTipoSanguineoRepository.saveAndFlush(recepcaoTipoSanguineo);

        // Get the recepcaoTipoSanguineo
        restRecepcaoTipoSanguineoMockMvc.perform(get("/api/recepcao-tipo-sanguineos/{id}", recepcaoTipoSanguineo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(recepcaoTipoSanguineo.getId().intValue()))
            .andExpect(jsonPath("$.podeReceberDe").value(DEFAULT_PODE_RECEBER_DE));
    }
    @Test
    @Transactional
    public void getNonExistingRecepcaoTipoSanguineo() throws Exception {
        // Get the recepcaoTipoSanguineo
        restRecepcaoTipoSanguineoMockMvc.perform(get("/api/recepcao-tipo-sanguineos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecepcaoTipoSanguineo() throws Exception {
        // Initialize the database
        recepcaoTipoSanguineoRepository.saveAndFlush(recepcaoTipoSanguineo);

        int databaseSizeBeforeUpdate = recepcaoTipoSanguineoRepository.findAll().size();

        // Update the recepcaoTipoSanguineo
        RecepcaoTipoSanguineo updatedRecepcaoTipoSanguineo = recepcaoTipoSanguineoRepository.findById(recepcaoTipoSanguineo.getId()).get();
        // Disconnect from session so that the updates on updatedRecepcaoTipoSanguineo are not directly saved in db
        em.detach(updatedRecepcaoTipoSanguineo);
        updatedRecepcaoTipoSanguineo
            .podeReceberDe(UPDATED_PODE_RECEBER_DE);
        RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO = recepcaoTipoSanguineoMapper.toDto(updatedRecepcaoTipoSanguineo);

        restRecepcaoTipoSanguineoMockMvc.perform(put("/api/recepcao-tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recepcaoTipoSanguineoDTO)))
            .andExpect(status().isOk());

        // Validate the RecepcaoTipoSanguineo in the database
        List<RecepcaoTipoSanguineo> recepcaoTipoSanguineoList = recepcaoTipoSanguineoRepository.findAll();
        assertThat(recepcaoTipoSanguineoList).hasSize(databaseSizeBeforeUpdate);
        RecepcaoTipoSanguineo testRecepcaoTipoSanguineo = recepcaoTipoSanguineoList.get(recepcaoTipoSanguineoList.size() - 1);
        assertThat(testRecepcaoTipoSanguineo.getPodeReceberDe()).isEqualTo(UPDATED_PODE_RECEBER_DE);
    }

    @Test
    @Transactional
    public void updateNonExistingRecepcaoTipoSanguineo() throws Exception {
        int databaseSizeBeforeUpdate = recepcaoTipoSanguineoRepository.findAll().size();

        // Create the RecepcaoTipoSanguineo
        RecepcaoTipoSanguineoDTO recepcaoTipoSanguineoDTO = recepcaoTipoSanguineoMapper.toDto(recepcaoTipoSanguineo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecepcaoTipoSanguineoMockMvc.perform(put("/api/recepcao-tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(recepcaoTipoSanguineoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RecepcaoTipoSanguineo in the database
        List<RecepcaoTipoSanguineo> recepcaoTipoSanguineoList = recepcaoTipoSanguineoRepository.findAll();
        assertThat(recepcaoTipoSanguineoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecepcaoTipoSanguineo() throws Exception {
        // Initialize the database
        recepcaoTipoSanguineoRepository.saveAndFlush(recepcaoTipoSanguineo);

        int databaseSizeBeforeDelete = recepcaoTipoSanguineoRepository.findAll().size();

        // Delete the recepcaoTipoSanguineo
        restRecepcaoTipoSanguineoMockMvc.perform(delete("/api/recepcao-tipo-sanguineos/{id}", recepcaoTipoSanguineo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RecepcaoTipoSanguineo> recepcaoTipoSanguineoList = recepcaoTipoSanguineoRepository.findAll();
        assertThat(recepcaoTipoSanguineoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
