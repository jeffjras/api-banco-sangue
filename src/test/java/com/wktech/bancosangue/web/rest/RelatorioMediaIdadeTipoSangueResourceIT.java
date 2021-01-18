package com.wktech.bancosangue.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.RelatorioMediaIdadeTipoSangue;
import com.wktech.bancosangue.repository.RelatorioMediaIdadeTipoSangueRepository;
import com.wktech.bancosangue.service.RelatorioMediaIdadeTipoSangueQueryService;
import com.wktech.bancosangue.service.RelatorioMediaIdadeTipoSangueService;
import com.wktech.bancosangue.service.dto.RelatorioMediaIdadeTipoSangueCriteria;
import com.wktech.bancosangue.service.dto.RelatorioMediaIdadeTipoSangueDTO;
import com.wktech.bancosangue.service.mapper.RelatorioMediaIdadeTipoSangueMapper;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RelatorioMediaIdadeTipoSangueResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RelatorioMediaIdadeTipoSangueResourceIT {
    private static final String DEFAULT_TIPO_SANGUE = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_SANGUE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MEDIA_TIPO = new BigDecimal(1);
    private static final BigDecimal UPDATED_MEDIA_TIPO = new BigDecimal(2);
    private static final BigDecimal SMALLER_MEDIA_TIPO = new BigDecimal(1 - 1);

    @Autowired
    private RelatorioMediaIdadeTipoSangueRepository relatorioMediaIdadeTipoSangueRepository;

    @Autowired
    private RelatorioMediaIdadeTipoSangueMapper relatorioMediaIdadeTipoSangueMapper;

    @Autowired
    private RelatorioMediaIdadeTipoSangueService relatorioMediaIdadeTipoSangueService;

    @Autowired
    private RelatorioMediaIdadeTipoSangueQueryService relatorioMediaIdadeTipoSangueQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelatorioMediaIdadeTipoSangueMockMvc;

    private RelatorioMediaIdadeTipoSangue relatorioMediaIdadeTipoSangue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioMediaIdadeTipoSangue createEntity(EntityManager em) {
        RelatorioMediaIdadeTipoSangue relatorioMediaIdadeTipoSangue = new RelatorioMediaIdadeTipoSangue()
            .tipoSangue(DEFAULT_TIPO_SANGUE)
            .mediaTipo(DEFAULT_MEDIA_TIPO);
        return relatorioMediaIdadeTipoSangue;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioMediaIdadeTipoSangue createUpdatedEntity(EntityManager em) {
        RelatorioMediaIdadeTipoSangue relatorioMediaIdadeTipoSangue = new RelatorioMediaIdadeTipoSangue()
            .tipoSangue(UPDATED_TIPO_SANGUE)
            .mediaTipo(UPDATED_MEDIA_TIPO);
        return relatorioMediaIdadeTipoSangue;
    }

    @BeforeEach
    public void initTest() {
        relatorioMediaIdadeTipoSangue = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelatorioMediaIdadeTipoSangue() throws Exception {
        int databaseSizeBeforeCreate = relatorioMediaIdadeTipoSangueRepository.findAll().size();
        // Create the RelatorioMediaIdadeTipoSangue
        RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO = relatorioMediaIdadeTipoSangueMapper.toDto(
            relatorioMediaIdadeTipoSangue
        );
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(
                post("/api/relatorio-media-idade-tipo-sangues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioMediaIdadeTipoSangueDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RelatorioMediaIdadeTipoSangue in the database
        List<RelatorioMediaIdadeTipoSangue> relatorioMediaIdadeTipoSangueList = relatorioMediaIdadeTipoSangueRepository.findAll();
        assertThat(relatorioMediaIdadeTipoSangueList).hasSize(databaseSizeBeforeCreate + 1);
        RelatorioMediaIdadeTipoSangue testRelatorioMediaIdadeTipoSangue = relatorioMediaIdadeTipoSangueList.get(
            relatorioMediaIdadeTipoSangueList.size() - 1
        );
        assertThat(testRelatorioMediaIdadeTipoSangue.getTipoSangue()).isEqualTo(DEFAULT_TIPO_SANGUE);
        assertThat(testRelatorioMediaIdadeTipoSangue.getMediaTipo()).isEqualTo(DEFAULT_MEDIA_TIPO);
    }

    @Test
    @Transactional
    public void createRelatorioMediaIdadeTipoSangueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relatorioMediaIdadeTipoSangueRepository.findAll().size();

        // Create the RelatorioMediaIdadeTipoSangue with an existing ID
        relatorioMediaIdadeTipoSangue.setId(1L);
        RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO = relatorioMediaIdadeTipoSangueMapper.toDto(
            relatorioMediaIdadeTipoSangue
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(
                post("/api/relatorio-media-idade-tipo-sangues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioMediaIdadeTipoSangueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioMediaIdadeTipoSangue in the database
        List<RelatorioMediaIdadeTipoSangue> relatorioMediaIdadeTipoSangueList = relatorioMediaIdadeTipoSangueRepository.findAll();
        assertThat(relatorioMediaIdadeTipoSangueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSangues() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(get("/api/relatorio-media-idade-tipo-sangues?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioMediaIdadeTipoSangue.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoSangue").value(hasItem(DEFAULT_TIPO_SANGUE)))
            .andExpect(jsonPath("$.[*].mediaTipo").value(hasItem(DEFAULT_MEDIA_TIPO.intValue())));
    }

    @Test
    @Transactional
    public void getRelatorioMediaIdadeTipoSangue() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get the relatorioMediaIdadeTipoSangue
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(get("/api/relatorio-media-idade-tipo-sangues/{id}", relatorioMediaIdadeTipoSangue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relatorioMediaIdadeTipoSangue.getId().intValue()))
            .andExpect(jsonPath("$.tipoSangue").value(DEFAULT_TIPO_SANGUE))
            .andExpect(jsonPath("$.mediaTipo").value(DEFAULT_MEDIA_TIPO.intValue()));
    }

    @Test
    @Transactional
    public void getRelatorioMediaIdadeTipoSanguesByIdFiltering() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        Long id = relatorioMediaIdadeTipoSangue.getId();

        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("id.equals=" + id);
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("id.notEquals=" + id);

        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("id.greaterThan=" + id);

        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("id.lessThanOrEqual=" + id);
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByTipoSangueIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue equals to DEFAULT_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("tipoSangue.equals=" + DEFAULT_TIPO_SANGUE);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue equals to UPDATED_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("tipoSangue.equals=" + UPDATED_TIPO_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByTipoSangueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue not equals to DEFAULT_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("tipoSangue.notEquals=" + DEFAULT_TIPO_SANGUE);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue not equals to UPDATED_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("tipoSangue.notEquals=" + UPDATED_TIPO_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByTipoSangueIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue in DEFAULT_TIPO_SANGUE or UPDATED_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("tipoSangue.in=" + DEFAULT_TIPO_SANGUE + "," + UPDATED_TIPO_SANGUE);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue equals to UPDATED_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("tipoSangue.in=" + UPDATED_TIPO_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByTipoSangueIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue is not null
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("tipoSangue.specified=true");

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue is null
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("tipoSangue.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByTipoSangueContainsSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue contains DEFAULT_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("tipoSangue.contains=" + DEFAULT_TIPO_SANGUE);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue contains UPDATED_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("tipoSangue.contains=" + UPDATED_TIPO_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByTipoSangueNotContainsSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue does not contain DEFAULT_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("tipoSangue.doesNotContain=" + DEFAULT_TIPO_SANGUE);

        // Get all the relatorioMediaIdadeTipoSangueList where tipoSangue does not contain UPDATED_TIPO_SANGUE
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("tipoSangue.doesNotContain=" + UPDATED_TIPO_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByMediaTipoIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo equals to DEFAULT_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("mediaTipo.equals=" + DEFAULT_MEDIA_TIPO);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo equals to UPDATED_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("mediaTipo.equals=" + UPDATED_MEDIA_TIPO);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByMediaTipoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo not equals to DEFAULT_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("mediaTipo.notEquals=" + DEFAULT_MEDIA_TIPO);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo not equals to UPDATED_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("mediaTipo.notEquals=" + UPDATED_MEDIA_TIPO);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByMediaTipoIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo in DEFAULT_MEDIA_TIPO or UPDATED_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("mediaTipo.in=" + DEFAULT_MEDIA_TIPO + "," + UPDATED_MEDIA_TIPO);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo equals to UPDATED_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("mediaTipo.in=" + UPDATED_MEDIA_TIPO);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByMediaTipoIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is not null
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("mediaTipo.specified=true");

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is null
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("mediaTipo.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByMediaTipoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is greater than or equal to DEFAULT_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("mediaTipo.greaterThanOrEqual=" + DEFAULT_MEDIA_TIPO);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is greater than or equal to UPDATED_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("mediaTipo.greaterThanOrEqual=" + UPDATED_MEDIA_TIPO);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByMediaTipoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is less than or equal to DEFAULT_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("mediaTipo.lessThanOrEqual=" + DEFAULT_MEDIA_TIPO);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is less than or equal to SMALLER_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("mediaTipo.lessThanOrEqual=" + SMALLER_MEDIA_TIPO);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByMediaTipoIsLessThanSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is less than DEFAULT_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("mediaTipo.lessThan=" + DEFAULT_MEDIA_TIPO);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is less than UPDATED_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("mediaTipo.lessThan=" + UPDATED_MEDIA_TIPO);
    }

    @Test
    @Transactional
    public void getAllRelatorioMediaIdadeTipoSanguesByMediaTipoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is greater than DEFAULT_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound("mediaTipo.greaterThan=" + DEFAULT_MEDIA_TIPO);

        // Get all the relatorioMediaIdadeTipoSangueList where mediaTipo is greater than SMALLER_MEDIA_TIPO
        defaultRelatorioMediaIdadeTipoSangueShouldBeFound("mediaTipo.greaterThan=" + SMALLER_MEDIA_TIPO);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRelatorioMediaIdadeTipoSangueShouldBeFound(String filter) throws Exception {
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(get("/api/relatorio-media-idade-tipo-sangues?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioMediaIdadeTipoSangue.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoSangue").value(hasItem(DEFAULT_TIPO_SANGUE)))
            .andExpect(jsonPath("$.[*].mediaTipo").value(hasItem(DEFAULT_MEDIA_TIPO.intValue())));

        // Check, that the count call also returns 1
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(get("/api/relatorio-media-idade-tipo-sangues/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRelatorioMediaIdadeTipoSangueShouldNotBeFound(String filter) throws Exception {
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(get("/api/relatorio-media-idade-tipo-sangues?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(get("/api/relatorio-media-idade-tipo-sangues/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingRelatorioMediaIdadeTipoSangue() throws Exception {
        // Get the relatorioMediaIdadeTipoSangue
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(get("/api/relatorio-media-idade-tipo-sangues/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelatorioMediaIdadeTipoSangue() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        int databaseSizeBeforeUpdate = relatorioMediaIdadeTipoSangueRepository.findAll().size();

        // Update the relatorioMediaIdadeTipoSangue
        RelatorioMediaIdadeTipoSangue updatedRelatorioMediaIdadeTipoSangue = relatorioMediaIdadeTipoSangueRepository
            .findById(relatorioMediaIdadeTipoSangue.getId())
            .get();
        // Disconnect from session so that the updates on updatedRelatorioMediaIdadeTipoSangue are not directly saved in db
        em.detach(updatedRelatorioMediaIdadeTipoSangue);
        updatedRelatorioMediaIdadeTipoSangue.tipoSangue(UPDATED_TIPO_SANGUE).mediaTipo(UPDATED_MEDIA_TIPO);
        RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO = relatorioMediaIdadeTipoSangueMapper.toDto(
            updatedRelatorioMediaIdadeTipoSangue
        );

        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(
                put("/api/relatorio-media-idade-tipo-sangues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioMediaIdadeTipoSangueDTO))
            )
            .andExpect(status().isOk());

        // Validate the RelatorioMediaIdadeTipoSangue in the database
        List<RelatorioMediaIdadeTipoSangue> relatorioMediaIdadeTipoSangueList = relatorioMediaIdadeTipoSangueRepository.findAll();
        assertThat(relatorioMediaIdadeTipoSangueList).hasSize(databaseSizeBeforeUpdate);
        RelatorioMediaIdadeTipoSangue testRelatorioMediaIdadeTipoSangue = relatorioMediaIdadeTipoSangueList.get(
            relatorioMediaIdadeTipoSangueList.size() - 1
        );
        assertThat(testRelatorioMediaIdadeTipoSangue.getTipoSangue()).isEqualTo(UPDATED_TIPO_SANGUE);
        assertThat(testRelatorioMediaIdadeTipoSangue.getMediaTipo()).isEqualTo(UPDATED_MEDIA_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingRelatorioMediaIdadeTipoSangue() throws Exception {
        int databaseSizeBeforeUpdate = relatorioMediaIdadeTipoSangueRepository.findAll().size();

        // Create the RelatorioMediaIdadeTipoSangue
        RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO = relatorioMediaIdadeTipoSangueMapper.toDto(
            relatorioMediaIdadeTipoSangue
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(
                put("/api/relatorio-media-idade-tipo-sangues")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioMediaIdadeTipoSangueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioMediaIdadeTipoSangue in the database
        List<RelatorioMediaIdadeTipoSangue> relatorioMediaIdadeTipoSangueList = relatorioMediaIdadeTipoSangueRepository.findAll();
        assertThat(relatorioMediaIdadeTipoSangueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelatorioMediaIdadeTipoSangue() throws Exception {
        // Initialize the database
        relatorioMediaIdadeTipoSangueRepository.saveAndFlush(relatorioMediaIdadeTipoSangue);

        int databaseSizeBeforeDelete = relatorioMediaIdadeTipoSangueRepository.findAll().size();

        // Delete the relatorioMediaIdadeTipoSangue
        restRelatorioMediaIdadeTipoSangueMockMvc
            .perform(
                delete("/api/relatorio-media-idade-tipo-sangues/{id}", relatorioMediaIdadeTipoSangue.getId())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelatorioMediaIdadeTipoSangue> relatorioMediaIdadeTipoSangueList = relatorioMediaIdadeTipoSangueRepository.findAll();
        assertThat(relatorioMediaIdadeTipoSangueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
