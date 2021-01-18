package com.wktech.bancosangue.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.RelatorioQtdeDoadoresParaCadaTipoReceptor;
import com.wktech.bancosangue.repository.RelatorioQtdeDoadoresParaCadaTipoReceptorRepository;
import com.wktech.bancosangue.service.RelatorioQtdeDoadoresParaCadaTipoReceptorQueryService;
import com.wktech.bancosangue.service.RelatorioQtdeDoadoresParaCadaTipoReceptorService;
import com.wktech.bancosangue.service.dto.RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria;
import com.wktech.bancosangue.service.dto.RelatorioQtdeDoadoresParaCadaTipoReceptorDTO;
import com.wktech.bancosangue.service.mapper.RelatorioQtdeDoadoresParaCadaTipoReceptorMapper;
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
 * Integration tests for the {@link RelatorioQtdeDoadoresParaCadaTipoReceptorResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RelatorioQtdeDoadoresParaCadaTipoReceptorResourceIT {
    private static final String DEFAULT_SANGUE = "AAAAAAAAAA";
    private static final String UPDATED_SANGUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_DOADOR = 1;
    private static final Integer UPDATED_TOTAL_DOADOR = 2;
    private static final Integer SMALLER_TOTAL_DOADOR = 1 - 1;

    @Autowired
    private RelatorioQtdeDoadoresParaCadaTipoReceptorRepository relatorioQtdeDoadoresParaCadaTipoReceptorRepository;

    @Autowired
    private RelatorioQtdeDoadoresParaCadaTipoReceptorMapper relatorioQtdeDoadoresParaCadaTipoReceptorMapper;

    @Autowired
    private RelatorioQtdeDoadoresParaCadaTipoReceptorService relatorioQtdeDoadoresParaCadaTipoReceptorService;

    @Autowired
    private RelatorioQtdeDoadoresParaCadaTipoReceptorQueryService relatorioQtdeDoadoresParaCadaTipoReceptorQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc;

    private RelatorioQtdeDoadoresParaCadaTipoReceptor relatorioQtdeDoadoresParaCadaTipoReceptor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioQtdeDoadoresParaCadaTipoReceptor createEntity(EntityManager em) {
        RelatorioQtdeDoadoresParaCadaTipoReceptor relatorioQtdeDoadoresParaCadaTipoReceptor = new RelatorioQtdeDoadoresParaCadaTipoReceptor()
            .sangue(DEFAULT_SANGUE)
            .totalDoador(DEFAULT_TOTAL_DOADOR);
        return relatorioQtdeDoadoresParaCadaTipoReceptor;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioQtdeDoadoresParaCadaTipoReceptor createUpdatedEntity(EntityManager em) {
        RelatorioQtdeDoadoresParaCadaTipoReceptor relatorioQtdeDoadoresParaCadaTipoReceptor = new RelatorioQtdeDoadoresParaCadaTipoReceptor()
            .sangue(UPDATED_SANGUE)
            .totalDoador(UPDATED_TOTAL_DOADOR);
        return relatorioQtdeDoadoresParaCadaTipoReceptor;
    }

    @BeforeEach
    public void initTest() {
        relatorioQtdeDoadoresParaCadaTipoReceptor = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelatorioQtdeDoadoresParaCadaTipoReceptor() throws Exception {
        int databaseSizeBeforeCreate = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll().size();
        // Create the RelatorioQtdeDoadoresParaCadaTipoReceptor
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO = relatorioQtdeDoadoresParaCadaTipoReceptorMapper.toDto(
            relatorioQtdeDoadoresParaCadaTipoReceptor
        );
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(
                post("/api/relatorio-qtde-doadores-para-cada-tipo-receptors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioQtdeDoadoresParaCadaTipoReceptorDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RelatorioQtdeDoadoresParaCadaTipoReceptor in the database
        List<RelatorioQtdeDoadoresParaCadaTipoReceptor> relatorioQtdeDoadoresParaCadaTipoReceptorList = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll();
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorList).hasSize(databaseSizeBeforeCreate + 1);
        RelatorioQtdeDoadoresParaCadaTipoReceptor testRelatorioQtdeDoadoresParaCadaTipoReceptor = relatorioQtdeDoadoresParaCadaTipoReceptorList.get(
            relatorioQtdeDoadoresParaCadaTipoReceptorList.size() - 1
        );
        assertThat(testRelatorioQtdeDoadoresParaCadaTipoReceptor.getSangue()).isEqualTo(DEFAULT_SANGUE);
        assertThat(testRelatorioQtdeDoadoresParaCadaTipoReceptor.getTotalDoador()).isEqualTo(DEFAULT_TOTAL_DOADOR);
    }

    @Test
    @Transactional
    public void createRelatorioQtdeDoadoresParaCadaTipoReceptorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll().size();

        // Create the RelatorioQtdeDoadoresParaCadaTipoReceptor with an existing ID
        relatorioQtdeDoadoresParaCadaTipoReceptor.setId(1L);
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO = relatorioQtdeDoadoresParaCadaTipoReceptorMapper.toDto(
            relatorioQtdeDoadoresParaCadaTipoReceptor
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(
                post("/api/relatorio-qtde-doadores-para-cada-tipo-receptors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioQtdeDoadoresParaCadaTipoReceptorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioQtdeDoadoresParaCadaTipoReceptor in the database
        List<RelatorioQtdeDoadoresParaCadaTipoReceptor> relatorioQtdeDoadoresParaCadaTipoReceptorList = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll();
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptors() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(get("/api/relatorio-qtde-doadores-para-cada-tipo-receptors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioQtdeDoadoresParaCadaTipoReceptor.getId().intValue())))
            .andExpect(jsonPath("$.[*].sangue").value(hasItem(DEFAULT_SANGUE)))
            .andExpect(jsonPath("$.[*].totalDoador").value(hasItem(DEFAULT_TOTAL_DOADOR)));
    }

    @Test
    @Transactional
    public void getRelatorioQtdeDoadoresParaCadaTipoReceptor() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get the relatorioQtdeDoadoresParaCadaTipoReceptor
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(get("/api/relatorio-qtde-doadores-para-cada-tipo-receptors/{id}", relatorioQtdeDoadoresParaCadaTipoReceptor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relatorioQtdeDoadoresParaCadaTipoReceptor.getId().intValue()))
            .andExpect(jsonPath("$.sangue").value(DEFAULT_SANGUE))
            .andExpect(jsonPath("$.totalDoador").value(DEFAULT_TOTAL_DOADOR));
    }

    @Test
    @Transactional
    public void getRelatorioQtdeDoadoresParaCadaTipoReceptorsByIdFiltering() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        Long id = relatorioQtdeDoadoresParaCadaTipoReceptor.getId();

        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("id.equals=" + id);
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("id.notEquals=" + id);

        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("id.greaterThan=" + id);

        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("id.lessThanOrEqual=" + id);
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsBySangueIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue equals to DEFAULT_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("sangue.equals=" + DEFAULT_SANGUE);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue equals to UPDATED_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("sangue.equals=" + UPDATED_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsBySangueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue not equals to DEFAULT_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("sangue.notEquals=" + DEFAULT_SANGUE);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue not equals to UPDATED_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("sangue.notEquals=" + UPDATED_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsBySangueIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue in DEFAULT_SANGUE or UPDATED_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("sangue.in=" + DEFAULT_SANGUE + "," + UPDATED_SANGUE);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue equals to UPDATED_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("sangue.in=" + UPDATED_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsBySangueIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue is not null
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("sangue.specified=true");

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue is null
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("sangue.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsBySangueContainsSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue contains DEFAULT_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("sangue.contains=" + DEFAULT_SANGUE);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue contains UPDATED_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("sangue.contains=" + UPDATED_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsBySangueNotContainsSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue does not contain DEFAULT_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("sangue.doesNotContain=" + DEFAULT_SANGUE);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where sangue does not contain UPDATED_SANGUE
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("sangue.doesNotContain=" + UPDATED_SANGUE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsByTotalDoadorIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador equals to DEFAULT_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("totalDoador.equals=" + DEFAULT_TOTAL_DOADOR);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador equals to UPDATED_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("totalDoador.equals=" + UPDATED_TOTAL_DOADOR);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsByTotalDoadorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador not equals to DEFAULT_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("totalDoador.notEquals=" + DEFAULT_TOTAL_DOADOR);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador not equals to UPDATED_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("totalDoador.notEquals=" + UPDATED_TOTAL_DOADOR);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsByTotalDoadorIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador in DEFAULT_TOTAL_DOADOR or UPDATED_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound(
            "totalDoador.in=" + DEFAULT_TOTAL_DOADOR + "," + UPDATED_TOTAL_DOADOR
        );

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador equals to UPDATED_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("totalDoador.in=" + UPDATED_TOTAL_DOADOR);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsByTotalDoadorIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is not null
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("totalDoador.specified=true");

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is null
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("totalDoador.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsByTotalDoadorIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is greater than or equal to DEFAULT_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("totalDoador.greaterThanOrEqual=" + DEFAULT_TOTAL_DOADOR);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is greater than or equal to UPDATED_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("totalDoador.greaterThanOrEqual=" + UPDATED_TOTAL_DOADOR);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsByTotalDoadorIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is less than or equal to DEFAULT_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("totalDoador.lessThanOrEqual=" + DEFAULT_TOTAL_DOADOR);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is less than or equal to SMALLER_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("totalDoador.lessThanOrEqual=" + SMALLER_TOTAL_DOADOR);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsByTotalDoadorIsLessThanSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is less than DEFAULT_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("totalDoador.lessThan=" + DEFAULT_TOTAL_DOADOR);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is less than UPDATED_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("totalDoador.lessThan=" + UPDATED_TOTAL_DOADOR);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdeDoadoresParaCadaTipoReceptorsByTotalDoadorIsGreaterThanSomething() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is greater than DEFAULT_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound("totalDoador.greaterThan=" + DEFAULT_TOTAL_DOADOR);

        // Get all the relatorioQtdeDoadoresParaCadaTipoReceptorList where totalDoador is greater than SMALLER_TOTAL_DOADOR
        defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound("totalDoador.greaterThan=" + SMALLER_TOTAL_DOADOR);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldBeFound(String filter) throws Exception {
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(get("/api/relatorio-qtde-doadores-para-cada-tipo-receptors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioQtdeDoadoresParaCadaTipoReceptor.getId().intValue())))
            .andExpect(jsonPath("$.[*].sangue").value(hasItem(DEFAULT_SANGUE)))
            .andExpect(jsonPath("$.[*].totalDoador").value(hasItem(DEFAULT_TOTAL_DOADOR)));

        // Check, that the count call also returns 1
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(get("/api/relatorio-qtde-doadores-para-cada-tipo-receptors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRelatorioQtdeDoadoresParaCadaTipoReceptorShouldNotBeFound(String filter) throws Exception {
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(get("/api/relatorio-qtde-doadores-para-cada-tipo-receptors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(get("/api/relatorio-qtde-doadores-para-cada-tipo-receptors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingRelatorioQtdeDoadoresParaCadaTipoReceptor() throws Exception {
        // Get the relatorioQtdeDoadoresParaCadaTipoReceptor
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(get("/api/relatorio-qtde-doadores-para-cada-tipo-receptors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelatorioQtdeDoadoresParaCadaTipoReceptor() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        int databaseSizeBeforeUpdate = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll().size();

        // Update the relatorioQtdeDoadoresParaCadaTipoReceptor
        RelatorioQtdeDoadoresParaCadaTipoReceptor updatedRelatorioQtdeDoadoresParaCadaTipoReceptor = relatorioQtdeDoadoresParaCadaTipoReceptorRepository
            .findById(relatorioQtdeDoadoresParaCadaTipoReceptor.getId())
            .get();
        // Disconnect from session so that the updates on updatedRelatorioQtdeDoadoresParaCadaTipoReceptor are not directly saved in db
        em.detach(updatedRelatorioQtdeDoadoresParaCadaTipoReceptor);
        updatedRelatorioQtdeDoadoresParaCadaTipoReceptor.sangue(UPDATED_SANGUE).totalDoador(UPDATED_TOTAL_DOADOR);
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO = relatorioQtdeDoadoresParaCadaTipoReceptorMapper.toDto(
            updatedRelatorioQtdeDoadoresParaCadaTipoReceptor
        );

        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(
                put("/api/relatorio-qtde-doadores-para-cada-tipo-receptors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioQtdeDoadoresParaCadaTipoReceptorDTO))
            )
            .andExpect(status().isOk());

        // Validate the RelatorioQtdeDoadoresParaCadaTipoReceptor in the database
        List<RelatorioQtdeDoadoresParaCadaTipoReceptor> relatorioQtdeDoadoresParaCadaTipoReceptorList = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll();
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorList).hasSize(databaseSizeBeforeUpdate);
        RelatorioQtdeDoadoresParaCadaTipoReceptor testRelatorioQtdeDoadoresParaCadaTipoReceptor = relatorioQtdeDoadoresParaCadaTipoReceptorList.get(
            relatorioQtdeDoadoresParaCadaTipoReceptorList.size() - 1
        );
        assertThat(testRelatorioQtdeDoadoresParaCadaTipoReceptor.getSangue()).isEqualTo(UPDATED_SANGUE);
        assertThat(testRelatorioQtdeDoadoresParaCadaTipoReceptor.getTotalDoador()).isEqualTo(UPDATED_TOTAL_DOADOR);
    }

    @Test
    @Transactional
    public void updateNonExistingRelatorioQtdeDoadoresParaCadaTipoReceptor() throws Exception {
        int databaseSizeBeforeUpdate = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll().size();

        // Create the RelatorioQtdeDoadoresParaCadaTipoReceptor
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO = relatorioQtdeDoadoresParaCadaTipoReceptorMapper.toDto(
            relatorioQtdeDoadoresParaCadaTipoReceptor
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(
                put("/api/relatorio-qtde-doadores-para-cada-tipo-receptors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioQtdeDoadoresParaCadaTipoReceptorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioQtdeDoadoresParaCadaTipoReceptor in the database
        List<RelatorioQtdeDoadoresParaCadaTipoReceptor> relatorioQtdeDoadoresParaCadaTipoReceptorList = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll();
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelatorioQtdeDoadoresParaCadaTipoReceptor() throws Exception {
        // Initialize the database
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.saveAndFlush(relatorioQtdeDoadoresParaCadaTipoReceptor);

        int databaseSizeBeforeDelete = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll().size();

        // Delete the relatorioQtdeDoadoresParaCadaTipoReceptor
        restRelatorioQtdeDoadoresParaCadaTipoReceptorMockMvc
            .perform(
                delete("/api/relatorio-qtde-doadores-para-cada-tipo-receptors/{id}", relatorioQtdeDoadoresParaCadaTipoReceptor.getId())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelatorioQtdeDoadoresParaCadaTipoReceptor> relatorioQtdeDoadoresParaCadaTipoReceptorList = relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll();
        assertThat(relatorioQtdeDoadoresParaCadaTipoReceptorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
