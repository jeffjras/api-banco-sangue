package com.wktech.bancosangue.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.RelatorioImcMedioFaixaIdade;
import com.wktech.bancosangue.repository.RelatorioImcMedioFaixaIdadeRepository;
import com.wktech.bancosangue.service.RelatorioImcMedioFaixaIdadeQueryService;
import com.wktech.bancosangue.service.RelatorioImcMedioFaixaIdadeService;
import com.wktech.bancosangue.service.dto.RelatorioImcMedioFaixaIdadeCriteria;
import com.wktech.bancosangue.service.dto.RelatorioImcMedioFaixaIdadeDTO;
import com.wktech.bancosangue.service.mapper.RelatorioImcMedioFaixaIdadeMapper;
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
 * Integration tests for the {@link RelatorioImcMedioFaixaIdadeResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RelatorioImcMedioFaixaIdadeResourceIT {
    private static final String DEFAULT_FAIXA_ETARIA = "AAAAAAAAAA";
    private static final String UPDATED_FAIXA_ETARIA = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_IMC_MEDIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_IMC_MEDIO = new BigDecimal(2);
    private static final BigDecimal SMALLER_IMC_MEDIO = new BigDecimal(1 - 1);

    @Autowired
    private RelatorioImcMedioFaixaIdadeRepository relatorioImcMedioFaixaIdadeRepository;

    @Autowired
    private RelatorioImcMedioFaixaIdadeMapper relatorioImcMedioFaixaIdadeMapper;

    @Autowired
    private RelatorioImcMedioFaixaIdadeService relatorioImcMedioFaixaIdadeService;

    @Autowired
    private RelatorioImcMedioFaixaIdadeQueryService relatorioImcMedioFaixaIdadeQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelatorioImcMedioFaixaIdadeMockMvc;

    private RelatorioImcMedioFaixaIdade relatorioImcMedioFaixaIdade;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioImcMedioFaixaIdade createEntity(EntityManager em) {
        RelatorioImcMedioFaixaIdade relatorioImcMedioFaixaIdade = new RelatorioImcMedioFaixaIdade()
            .faixaEtaria(DEFAULT_FAIXA_ETARIA)
            .imcMedio(DEFAULT_IMC_MEDIO);
        return relatorioImcMedioFaixaIdade;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioImcMedioFaixaIdade createUpdatedEntity(EntityManager em) {
        RelatorioImcMedioFaixaIdade relatorioImcMedioFaixaIdade = new RelatorioImcMedioFaixaIdade()
            .faixaEtaria(UPDATED_FAIXA_ETARIA)
            .imcMedio(UPDATED_IMC_MEDIO);
        return relatorioImcMedioFaixaIdade;
    }

    @BeforeEach
    public void initTest() {
        relatorioImcMedioFaixaIdade = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelatorioImcMedioFaixaIdade() throws Exception {
        int databaseSizeBeforeCreate = relatorioImcMedioFaixaIdadeRepository.findAll().size();
        // Create the RelatorioImcMedioFaixaIdade
        RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO = relatorioImcMedioFaixaIdadeMapper.toDto(
            relatorioImcMedioFaixaIdade
        );
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(
                post("/api/relatorio-imc-medio-faixa-idades")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioImcMedioFaixaIdadeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RelatorioImcMedioFaixaIdade in the database
        List<RelatorioImcMedioFaixaIdade> relatorioImcMedioFaixaIdadeList = relatorioImcMedioFaixaIdadeRepository.findAll();
        assertThat(relatorioImcMedioFaixaIdadeList).hasSize(databaseSizeBeforeCreate + 1);
        RelatorioImcMedioFaixaIdade testRelatorioImcMedioFaixaIdade = relatorioImcMedioFaixaIdadeList.get(
            relatorioImcMedioFaixaIdadeList.size() - 1
        );
        assertThat(testRelatorioImcMedioFaixaIdade.getFaixaEtaria()).isEqualTo(DEFAULT_FAIXA_ETARIA);
        assertThat(testRelatorioImcMedioFaixaIdade.getImcMedio()).isEqualTo(DEFAULT_IMC_MEDIO);
    }

    @Test
    @Transactional
    public void createRelatorioImcMedioFaixaIdadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relatorioImcMedioFaixaIdadeRepository.findAll().size();

        // Create the RelatorioImcMedioFaixaIdade with an existing ID
        relatorioImcMedioFaixaIdade.setId(1L);
        RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO = relatorioImcMedioFaixaIdadeMapper.toDto(
            relatorioImcMedioFaixaIdade
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(
                post("/api/relatorio-imc-medio-faixa-idades")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioImcMedioFaixaIdadeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioImcMedioFaixaIdade in the database
        List<RelatorioImcMedioFaixaIdade> relatorioImcMedioFaixaIdadeList = relatorioImcMedioFaixaIdadeRepository.findAll();
        assertThat(relatorioImcMedioFaixaIdadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdades() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(get("/api/relatorio-imc-medio-faixa-idades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioImcMedioFaixaIdade.getId().intValue())))
            .andExpect(jsonPath("$.[*].faixaEtaria").value(hasItem(DEFAULT_FAIXA_ETARIA)))
            .andExpect(jsonPath("$.[*].imcMedio").value(hasItem(DEFAULT_IMC_MEDIO.intValue())));
    }

    @Test
    @Transactional
    public void getRelatorioImcMedioFaixaIdade() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get the relatorioImcMedioFaixaIdade
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(get("/api/relatorio-imc-medio-faixa-idades/{id}", relatorioImcMedioFaixaIdade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relatorioImcMedioFaixaIdade.getId().intValue()))
            .andExpect(jsonPath("$.faixaEtaria").value(DEFAULT_FAIXA_ETARIA))
            .andExpect(jsonPath("$.imcMedio").value(DEFAULT_IMC_MEDIO.intValue()));
    }

    @Test
    @Transactional
    public void getRelatorioImcMedioFaixaIdadesByIdFiltering() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        Long id = relatorioImcMedioFaixaIdade.getId();

        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("id.equals=" + id);
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("id.notEquals=" + id);

        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("id.greaterThan=" + id);

        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByFaixaEtariaIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria equals to DEFAULT_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("faixaEtaria.equals=" + DEFAULT_FAIXA_ETARIA);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria equals to UPDATED_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("faixaEtaria.equals=" + UPDATED_FAIXA_ETARIA);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByFaixaEtariaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria not equals to DEFAULT_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("faixaEtaria.notEquals=" + DEFAULT_FAIXA_ETARIA);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria not equals to UPDATED_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("faixaEtaria.notEquals=" + UPDATED_FAIXA_ETARIA);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByFaixaEtariaIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria in DEFAULT_FAIXA_ETARIA or UPDATED_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("faixaEtaria.in=" + DEFAULT_FAIXA_ETARIA + "," + UPDATED_FAIXA_ETARIA);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria equals to UPDATED_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("faixaEtaria.in=" + UPDATED_FAIXA_ETARIA);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByFaixaEtariaIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria is not null
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("faixaEtaria.specified=true");

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria is null
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("faixaEtaria.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByFaixaEtariaContainsSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria contains DEFAULT_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("faixaEtaria.contains=" + DEFAULT_FAIXA_ETARIA);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria contains UPDATED_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("faixaEtaria.contains=" + UPDATED_FAIXA_ETARIA);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByFaixaEtariaNotContainsSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria does not contain DEFAULT_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("faixaEtaria.doesNotContain=" + DEFAULT_FAIXA_ETARIA);

        // Get all the relatorioImcMedioFaixaIdadeList where faixaEtaria does not contain UPDATED_FAIXA_ETARIA
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("faixaEtaria.doesNotContain=" + UPDATED_FAIXA_ETARIA);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByImcMedioIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio equals to DEFAULT_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("imcMedio.equals=" + DEFAULT_IMC_MEDIO);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio equals to UPDATED_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("imcMedio.equals=" + UPDATED_IMC_MEDIO);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByImcMedioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio not equals to DEFAULT_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("imcMedio.notEquals=" + DEFAULT_IMC_MEDIO);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio not equals to UPDATED_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("imcMedio.notEquals=" + UPDATED_IMC_MEDIO);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByImcMedioIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio in DEFAULT_IMC_MEDIO or UPDATED_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("imcMedio.in=" + DEFAULT_IMC_MEDIO + "," + UPDATED_IMC_MEDIO);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio equals to UPDATED_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("imcMedio.in=" + UPDATED_IMC_MEDIO);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByImcMedioIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is not null
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("imcMedio.specified=true");

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is null
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("imcMedio.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByImcMedioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is greater than or equal to DEFAULT_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("imcMedio.greaterThanOrEqual=" + DEFAULT_IMC_MEDIO);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is greater than or equal to UPDATED_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("imcMedio.greaterThanOrEqual=" + UPDATED_IMC_MEDIO);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByImcMedioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is less than or equal to DEFAULT_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("imcMedio.lessThanOrEqual=" + DEFAULT_IMC_MEDIO);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is less than or equal to SMALLER_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("imcMedio.lessThanOrEqual=" + SMALLER_IMC_MEDIO);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByImcMedioIsLessThanSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is less than DEFAULT_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("imcMedio.lessThan=" + DEFAULT_IMC_MEDIO);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is less than UPDATED_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("imcMedio.lessThan=" + UPDATED_IMC_MEDIO);
    }

    @Test
    @Transactional
    public void getAllRelatorioImcMedioFaixaIdadesByImcMedioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is greater than DEFAULT_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound("imcMedio.greaterThan=" + DEFAULT_IMC_MEDIO);

        // Get all the relatorioImcMedioFaixaIdadeList where imcMedio is greater than SMALLER_IMC_MEDIO
        defaultRelatorioImcMedioFaixaIdadeShouldBeFound("imcMedio.greaterThan=" + SMALLER_IMC_MEDIO);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRelatorioImcMedioFaixaIdadeShouldBeFound(String filter) throws Exception {
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(get("/api/relatorio-imc-medio-faixa-idades?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioImcMedioFaixaIdade.getId().intValue())))
            .andExpect(jsonPath("$.[*].faixaEtaria").value(hasItem(DEFAULT_FAIXA_ETARIA)))
            .andExpect(jsonPath("$.[*].imcMedio").value(hasItem(DEFAULT_IMC_MEDIO.intValue())));

        // Check, that the count call also returns 1
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(get("/api/relatorio-imc-medio-faixa-idades/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRelatorioImcMedioFaixaIdadeShouldNotBeFound(String filter) throws Exception {
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(get("/api/relatorio-imc-medio-faixa-idades?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(get("/api/relatorio-imc-medio-faixa-idades/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingRelatorioImcMedioFaixaIdade() throws Exception {
        // Get the relatorioImcMedioFaixaIdade
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(get("/api/relatorio-imc-medio-faixa-idades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelatorioImcMedioFaixaIdade() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        int databaseSizeBeforeUpdate = relatorioImcMedioFaixaIdadeRepository.findAll().size();

        // Update the relatorioImcMedioFaixaIdade
        RelatorioImcMedioFaixaIdade updatedRelatorioImcMedioFaixaIdade = relatorioImcMedioFaixaIdadeRepository
            .findById(relatorioImcMedioFaixaIdade.getId())
            .get();
        // Disconnect from session so that the updates on updatedRelatorioImcMedioFaixaIdade are not directly saved in db
        em.detach(updatedRelatorioImcMedioFaixaIdade);
        updatedRelatorioImcMedioFaixaIdade.faixaEtaria(UPDATED_FAIXA_ETARIA).imcMedio(UPDATED_IMC_MEDIO);
        RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO = relatorioImcMedioFaixaIdadeMapper.toDto(
            updatedRelatorioImcMedioFaixaIdade
        );

        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(
                put("/api/relatorio-imc-medio-faixa-idades")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioImcMedioFaixaIdadeDTO))
            )
            .andExpect(status().isOk());

        // Validate the RelatorioImcMedioFaixaIdade in the database
        List<RelatorioImcMedioFaixaIdade> relatorioImcMedioFaixaIdadeList = relatorioImcMedioFaixaIdadeRepository.findAll();
        assertThat(relatorioImcMedioFaixaIdadeList).hasSize(databaseSizeBeforeUpdate);
        RelatorioImcMedioFaixaIdade testRelatorioImcMedioFaixaIdade = relatorioImcMedioFaixaIdadeList.get(
            relatorioImcMedioFaixaIdadeList.size() - 1
        );
        assertThat(testRelatorioImcMedioFaixaIdade.getFaixaEtaria()).isEqualTo(UPDATED_FAIXA_ETARIA);
        assertThat(testRelatorioImcMedioFaixaIdade.getImcMedio()).isEqualTo(UPDATED_IMC_MEDIO);
    }

    @Test
    @Transactional
    public void updateNonExistingRelatorioImcMedioFaixaIdade() throws Exception {
        int databaseSizeBeforeUpdate = relatorioImcMedioFaixaIdadeRepository.findAll().size();

        // Create the RelatorioImcMedioFaixaIdade
        RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO = relatorioImcMedioFaixaIdadeMapper.toDto(
            relatorioImcMedioFaixaIdade
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(
                put("/api/relatorio-imc-medio-faixa-idades")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioImcMedioFaixaIdadeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioImcMedioFaixaIdade in the database
        List<RelatorioImcMedioFaixaIdade> relatorioImcMedioFaixaIdadeList = relatorioImcMedioFaixaIdadeRepository.findAll();
        assertThat(relatorioImcMedioFaixaIdadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelatorioImcMedioFaixaIdade() throws Exception {
        // Initialize the database
        relatorioImcMedioFaixaIdadeRepository.saveAndFlush(relatorioImcMedioFaixaIdade);

        int databaseSizeBeforeDelete = relatorioImcMedioFaixaIdadeRepository.findAll().size();

        // Delete the relatorioImcMedioFaixaIdade
        restRelatorioImcMedioFaixaIdadeMockMvc
            .perform(
                delete("/api/relatorio-imc-medio-faixa-idades/{id}", relatorioImcMedioFaixaIdade.getId()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelatorioImcMedioFaixaIdade> relatorioImcMedioFaixaIdadeList = relatorioImcMedioFaixaIdadeRepository.findAll();
        assertThat(relatorioImcMedioFaixaIdadeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
