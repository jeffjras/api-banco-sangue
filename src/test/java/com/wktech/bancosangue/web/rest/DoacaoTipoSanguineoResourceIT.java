package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.DoacaoTipoSanguineo;
import com.wktech.bancosangue.repository.DoacaoTipoSanguineoRepository;
import com.wktech.bancosangue.service.DoacaoTipoSanguineoService;
import com.wktech.bancosangue.service.dto.DoacaoTipoSanguineoDTO;
import com.wktech.bancosangue.service.mapper.DoacaoTipoSanguineoMapper;

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
 * Integration tests for the {@link DoacaoTipoSanguineoResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DoacaoTipoSanguineoResourceIT {

    private static final String DEFAULT_PODE_DOAR_PARA = "AAAAAAAAAA";
    private static final String UPDATED_PODE_DOAR_PARA = "BBBBBBBBBB";

    @Autowired
    private DoacaoTipoSanguineoRepository doacaoTipoSanguineoRepository;

    @Autowired
    private DoacaoTipoSanguineoMapper doacaoTipoSanguineoMapper;

    @Autowired
    private DoacaoTipoSanguineoService doacaoTipoSanguineoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDoacaoTipoSanguineoMockMvc;

    private DoacaoTipoSanguineo doacaoTipoSanguineo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoacaoTipoSanguineo createEntity(EntityManager em) {
        DoacaoTipoSanguineo doacaoTipoSanguineo = new DoacaoTipoSanguineo()
            .podeDoarPara(DEFAULT_PODE_DOAR_PARA);
        return doacaoTipoSanguineo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoacaoTipoSanguineo createUpdatedEntity(EntityManager em) {
        DoacaoTipoSanguineo doacaoTipoSanguineo = new DoacaoTipoSanguineo()
            .podeDoarPara(UPDATED_PODE_DOAR_PARA);
        return doacaoTipoSanguineo;
    }

    @BeforeEach
    public void initTest() {
        doacaoTipoSanguineo = createEntity(em);
    }

    @Test
    @Transactional
    public void createDoacaoTipoSanguineo() throws Exception {
        int databaseSizeBeforeCreate = doacaoTipoSanguineoRepository.findAll().size();
        // Create the DoacaoTipoSanguineo
        DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO = doacaoTipoSanguineoMapper.toDto(doacaoTipoSanguineo);
        restDoacaoTipoSanguineoMockMvc.perform(post("/api/doacao-tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(doacaoTipoSanguineoDTO)))
            .andExpect(status().isCreated());

        // Validate the DoacaoTipoSanguineo in the database
        List<DoacaoTipoSanguineo> doacaoTipoSanguineoList = doacaoTipoSanguineoRepository.findAll();
        assertThat(doacaoTipoSanguineoList).hasSize(databaseSizeBeforeCreate + 1);
        DoacaoTipoSanguineo testDoacaoTipoSanguineo = doacaoTipoSanguineoList.get(doacaoTipoSanguineoList.size() - 1);
        assertThat(testDoacaoTipoSanguineo.getPodeDoarPara()).isEqualTo(DEFAULT_PODE_DOAR_PARA);
    }

    @Test
    @Transactional
    public void createDoacaoTipoSanguineoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = doacaoTipoSanguineoRepository.findAll().size();

        // Create the DoacaoTipoSanguineo with an existing ID
        doacaoTipoSanguineo.setId(1L);
        DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO = doacaoTipoSanguineoMapper.toDto(doacaoTipoSanguineo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoacaoTipoSanguineoMockMvc.perform(post("/api/doacao-tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(doacaoTipoSanguineoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoacaoTipoSanguineo in the database
        List<DoacaoTipoSanguineo> doacaoTipoSanguineoList = doacaoTipoSanguineoRepository.findAll();
        assertThat(doacaoTipoSanguineoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDoacaoTipoSanguineos() throws Exception {
        // Initialize the database
        doacaoTipoSanguineoRepository.saveAndFlush(doacaoTipoSanguineo);

        // Get all the doacaoTipoSanguineoList
        restDoacaoTipoSanguineoMockMvc.perform(get("/api/doacao-tipo-sanguineos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doacaoTipoSanguineo.getId().intValue())))
            .andExpect(jsonPath("$.[*].podeDoarPara").value(hasItem(DEFAULT_PODE_DOAR_PARA)));
    }
    
    @Test
    @Transactional
    public void getDoacaoTipoSanguineo() throws Exception {
        // Initialize the database
        doacaoTipoSanguineoRepository.saveAndFlush(doacaoTipoSanguineo);

        // Get the doacaoTipoSanguineo
        restDoacaoTipoSanguineoMockMvc.perform(get("/api/doacao-tipo-sanguineos/{id}", doacaoTipoSanguineo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(doacaoTipoSanguineo.getId().intValue()))
            .andExpect(jsonPath("$.podeDoarPara").value(DEFAULT_PODE_DOAR_PARA));
    }
    @Test
    @Transactional
    public void getNonExistingDoacaoTipoSanguineo() throws Exception {
        // Get the doacaoTipoSanguineo
        restDoacaoTipoSanguineoMockMvc.perform(get("/api/doacao-tipo-sanguineos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDoacaoTipoSanguineo() throws Exception {
        // Initialize the database
        doacaoTipoSanguineoRepository.saveAndFlush(doacaoTipoSanguineo);

        int databaseSizeBeforeUpdate = doacaoTipoSanguineoRepository.findAll().size();

        // Update the doacaoTipoSanguineo
        DoacaoTipoSanguineo updatedDoacaoTipoSanguineo = doacaoTipoSanguineoRepository.findById(doacaoTipoSanguineo.getId()).get();
        // Disconnect from session so that the updates on updatedDoacaoTipoSanguineo are not directly saved in db
        em.detach(updatedDoacaoTipoSanguineo);
        updatedDoacaoTipoSanguineo
            .podeDoarPara(UPDATED_PODE_DOAR_PARA);
        DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO = doacaoTipoSanguineoMapper.toDto(updatedDoacaoTipoSanguineo);

        restDoacaoTipoSanguineoMockMvc.perform(put("/api/doacao-tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(doacaoTipoSanguineoDTO)))
            .andExpect(status().isOk());

        // Validate the DoacaoTipoSanguineo in the database
        List<DoacaoTipoSanguineo> doacaoTipoSanguineoList = doacaoTipoSanguineoRepository.findAll();
        assertThat(doacaoTipoSanguineoList).hasSize(databaseSizeBeforeUpdate);
        DoacaoTipoSanguineo testDoacaoTipoSanguineo = doacaoTipoSanguineoList.get(doacaoTipoSanguineoList.size() - 1);
        assertThat(testDoacaoTipoSanguineo.getPodeDoarPara()).isEqualTo(UPDATED_PODE_DOAR_PARA);
    }

    @Test
    @Transactional
    public void updateNonExistingDoacaoTipoSanguineo() throws Exception {
        int databaseSizeBeforeUpdate = doacaoTipoSanguineoRepository.findAll().size();

        // Create the DoacaoTipoSanguineo
        DoacaoTipoSanguineoDTO doacaoTipoSanguineoDTO = doacaoTipoSanguineoMapper.toDto(doacaoTipoSanguineo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoacaoTipoSanguineoMockMvc.perform(put("/api/doacao-tipo-sanguineos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(doacaoTipoSanguineoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoacaoTipoSanguineo in the database
        List<DoacaoTipoSanguineo> doacaoTipoSanguineoList = doacaoTipoSanguineoRepository.findAll();
        assertThat(doacaoTipoSanguineoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDoacaoTipoSanguineo() throws Exception {
        // Initialize the database
        doacaoTipoSanguineoRepository.saveAndFlush(doacaoTipoSanguineo);

        int databaseSizeBeforeDelete = doacaoTipoSanguineoRepository.findAll().size();

        // Delete the doacaoTipoSanguineo
        restDoacaoTipoSanguineoMockMvc.perform(delete("/api/doacao-tipo-sanguineos/{id}", doacaoTipoSanguineo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DoacaoTipoSanguineo> doacaoTipoSanguineoList = doacaoTipoSanguineoRepository.findAll();
        assertThat(doacaoTipoSanguineoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
