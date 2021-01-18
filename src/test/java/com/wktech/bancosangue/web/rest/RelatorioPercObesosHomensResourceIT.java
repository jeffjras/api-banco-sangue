package com.wktech.bancosangue.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.RelatorioPercObesosHomens;
import com.wktech.bancosangue.repository.RelatorioPercObesosHomensRepository;
import com.wktech.bancosangue.service.RelatorioPercObesosHomensQueryService;
import com.wktech.bancosangue.service.RelatorioPercObesosHomensService;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosHomensCriteria;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosHomensDTO;
import com.wktech.bancosangue.service.mapper.RelatorioPercObesosHomensMapper;
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
 * Integration tests for the {@link RelatorioPercObesosHomensResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RelatorioPercObesosHomensResourceIT {
    private static final BigDecimal DEFAULT_PERCENTUAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERCENTUAL = new BigDecimal(2);
    private static final BigDecimal SMALLER_PERCENTUAL = new BigDecimal(1 - 1);

    @Autowired
    private RelatorioPercObesosHomensRepository relatorioPercObesosHomensRepository;

    @Autowired
    private RelatorioPercObesosHomensMapper relatorioPercObesosHomensMapper;

    @Autowired
    private RelatorioPercObesosHomensService relatorioPercObesosHomensService;

    @Autowired
    private RelatorioPercObesosHomensQueryService relatorioPercObesosHomensQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelatorioPercObesosHomensMockMvc;

    private RelatorioPercObesosHomens relatorioPercObesosHomens;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioPercObesosHomens createEntity(EntityManager em) {
        RelatorioPercObesosHomens relatorioPercObesosHomens = new RelatorioPercObesosHomens().percentual(DEFAULT_PERCENTUAL);
        return relatorioPercObesosHomens;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioPercObesosHomens createUpdatedEntity(EntityManager em) {
        RelatorioPercObesosHomens relatorioPercObesosHomens = new RelatorioPercObesosHomens().percentual(UPDATED_PERCENTUAL);
        return relatorioPercObesosHomens;
    }

    @BeforeEach
    public void initTest() {
        relatorioPercObesosHomens = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelatorioPercObesosHomens() throws Exception {
        int databaseSizeBeforeCreate = relatorioPercObesosHomensRepository.findAll().size();
        // Create the RelatorioPercObesosHomens
        RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO = relatorioPercObesosHomensMapper.toDto(relatorioPercObesosHomens);
        restRelatorioPercObesosHomensMockMvc
            .perform(
                post("/api/relatorio-perc-obesos-homens")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioPercObesosHomensDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RelatorioPercObesosHomens in the database
        List<RelatorioPercObesosHomens> relatorioPercObesosHomensList = relatorioPercObesosHomensRepository.findAll();
        assertThat(relatorioPercObesosHomensList).hasSize(databaseSizeBeforeCreate + 1);
        RelatorioPercObesosHomens testRelatorioPercObesosHomens = relatorioPercObesosHomensList.get(
            relatorioPercObesosHomensList.size() - 1
        );
        assertThat(testRelatorioPercObesosHomens.getPercentual()).isEqualTo(DEFAULT_PERCENTUAL);
    }

    @Test
    @Transactional
    public void createRelatorioPercObesosHomensWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relatorioPercObesosHomensRepository.findAll().size();

        // Create the RelatorioPercObesosHomens with an existing ID
        relatorioPercObesosHomens.setId(1L);
        RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO = relatorioPercObesosHomensMapper.toDto(relatorioPercObesosHomens);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelatorioPercObesosHomensMockMvc
            .perform(
                post("/api/relatorio-perc-obesos-homens")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioPercObesosHomensDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioPercObesosHomens in the database
        List<RelatorioPercObesosHomens> relatorioPercObesosHomensList = relatorioPercObesosHomensRepository.findAll();
        assertThat(relatorioPercObesosHomensList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosHomens() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get all the relatorioPercObesosHomensList
        restRelatorioPercObesosHomensMockMvc
            .perform(get("/api/relatorio-perc-obesos-homens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioPercObesosHomens.getId().intValue())))
            .andExpect(jsonPath("$.[*].percentual").value(hasItem(DEFAULT_PERCENTUAL.intValue())));
    }

    @Test
    @Transactional
    public void getRelatorioPercObesosHomens() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get the relatorioPercObesosHomens
        restRelatorioPercObesosHomensMockMvc
            .perform(get("/api/relatorio-perc-obesos-homens/{id}", relatorioPercObesosHomens.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relatorioPercObesosHomens.getId().intValue()))
            .andExpect(jsonPath("$.percentual").value(DEFAULT_PERCENTUAL.intValue()));
    }

    @Test
    @Transactional
    public void getRelatorioPercObesosHomensByIdFiltering() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        Long id = relatorioPercObesosHomens.getId();

        defaultRelatorioPercObesosHomensShouldBeFound("id.equals=" + id);
        defaultRelatorioPercObesosHomensShouldNotBeFound("id.notEquals=" + id);

        defaultRelatorioPercObesosHomensShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultRelatorioPercObesosHomensShouldNotBeFound("id.greaterThan=" + id);

        defaultRelatorioPercObesosHomensShouldBeFound("id.lessThanOrEqual=" + id);
        defaultRelatorioPercObesosHomensShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosHomensByPercentualIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get all the relatorioPercObesosHomensList where percentual equals to DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldBeFound("percentual.equals=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosHomensList where percentual equals to UPDATED_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldNotBeFound("percentual.equals=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosHomensByPercentualIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get all the relatorioPercObesosHomensList where percentual not equals to DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldNotBeFound("percentual.notEquals=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosHomensList where percentual not equals to UPDATED_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldBeFound("percentual.notEquals=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosHomensByPercentualIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get all the relatorioPercObesosHomensList where percentual in DEFAULT_PERCENTUAL or UPDATED_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldBeFound("percentual.in=" + DEFAULT_PERCENTUAL + "," + UPDATED_PERCENTUAL);

        // Get all the relatorioPercObesosHomensList where percentual equals to UPDATED_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldNotBeFound("percentual.in=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosHomensByPercentualIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get all the relatorioPercObesosHomensList where percentual is not null
        defaultRelatorioPercObesosHomensShouldBeFound("percentual.specified=true");

        // Get all the relatorioPercObesosHomensList where percentual is null
        defaultRelatorioPercObesosHomensShouldNotBeFound("percentual.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosHomensByPercentualIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get all the relatorioPercObesosHomensList where percentual is greater than or equal to DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldBeFound("percentual.greaterThanOrEqual=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosHomensList where percentual is greater than or equal to UPDATED_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldNotBeFound("percentual.greaterThanOrEqual=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosHomensByPercentualIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get all the relatorioPercObesosHomensList where percentual is less than or equal to DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldBeFound("percentual.lessThanOrEqual=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosHomensList where percentual is less than or equal to SMALLER_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldNotBeFound("percentual.lessThanOrEqual=" + SMALLER_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosHomensByPercentualIsLessThanSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get all the relatorioPercObesosHomensList where percentual is less than DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldNotBeFound("percentual.lessThan=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosHomensList where percentual is less than UPDATED_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldBeFound("percentual.lessThan=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosHomensByPercentualIsGreaterThanSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        // Get all the relatorioPercObesosHomensList where percentual is greater than DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldNotBeFound("percentual.greaterThan=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosHomensList where percentual is greater than SMALLER_PERCENTUAL
        defaultRelatorioPercObesosHomensShouldBeFound("percentual.greaterThan=" + SMALLER_PERCENTUAL);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRelatorioPercObesosHomensShouldBeFound(String filter) throws Exception {
        restRelatorioPercObesosHomensMockMvc
            .perform(get("/api/relatorio-perc-obesos-homens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioPercObesosHomens.getId().intValue())))
            .andExpect(jsonPath("$.[*].percentual").value(hasItem(DEFAULT_PERCENTUAL.intValue())));

        // Check, that the count call also returns 1
        restRelatorioPercObesosHomensMockMvc
            .perform(get("/api/relatorio-perc-obesos-homens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRelatorioPercObesosHomensShouldNotBeFound(String filter) throws Exception {
        restRelatorioPercObesosHomensMockMvc
            .perform(get("/api/relatorio-perc-obesos-homens?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRelatorioPercObesosHomensMockMvc
            .perform(get("/api/relatorio-perc-obesos-homens/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingRelatorioPercObesosHomens() throws Exception {
        // Get the relatorioPercObesosHomens
        restRelatorioPercObesosHomensMockMvc
            .perform(get("/api/relatorio-perc-obesos-homens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelatorioPercObesosHomens() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        int databaseSizeBeforeUpdate = relatorioPercObesosHomensRepository.findAll().size();

        // Update the relatorioPercObesosHomens
        RelatorioPercObesosHomens updatedRelatorioPercObesosHomens = relatorioPercObesosHomensRepository
            .findById(relatorioPercObesosHomens.getId())
            .get();
        // Disconnect from session so that the updates on updatedRelatorioPercObesosHomens are not directly saved in db
        em.detach(updatedRelatorioPercObesosHomens);
        updatedRelatorioPercObesosHomens.percentual(UPDATED_PERCENTUAL);
        RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO = relatorioPercObesosHomensMapper.toDto(updatedRelatorioPercObesosHomens);

        restRelatorioPercObesosHomensMockMvc
            .perform(
                put("/api/relatorio-perc-obesos-homens")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioPercObesosHomensDTO))
            )
            .andExpect(status().isOk());

        // Validate the RelatorioPercObesosHomens in the database
        List<RelatorioPercObesosHomens> relatorioPercObesosHomensList = relatorioPercObesosHomensRepository.findAll();
        assertThat(relatorioPercObesosHomensList).hasSize(databaseSizeBeforeUpdate);
        RelatorioPercObesosHomens testRelatorioPercObesosHomens = relatorioPercObesosHomensList.get(
            relatorioPercObesosHomensList.size() - 1
        );
        assertThat(testRelatorioPercObesosHomens.getPercentual()).isEqualTo(UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void updateNonExistingRelatorioPercObesosHomens() throws Exception {
        int databaseSizeBeforeUpdate = relatorioPercObesosHomensRepository.findAll().size();

        // Create the RelatorioPercObesosHomens
        RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO = relatorioPercObesosHomensMapper.toDto(relatorioPercObesosHomens);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelatorioPercObesosHomensMockMvc
            .perform(
                put("/api/relatorio-perc-obesos-homens")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioPercObesosHomensDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioPercObesosHomens in the database
        List<RelatorioPercObesosHomens> relatorioPercObesosHomensList = relatorioPercObesosHomensRepository.findAll();
        assertThat(relatorioPercObesosHomensList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelatorioPercObesosHomens() throws Exception {
        // Initialize the database
        relatorioPercObesosHomensRepository.saveAndFlush(relatorioPercObesosHomens);

        int databaseSizeBeforeDelete = relatorioPercObesosHomensRepository.findAll().size();

        // Delete the relatorioPercObesosHomens
        restRelatorioPercObesosHomensMockMvc
            .perform(delete("/api/relatorio-perc-obesos-homens/{id}", relatorioPercObesosHomens.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelatorioPercObesosHomens> relatorioPercObesosHomensList = relatorioPercObesosHomensRepository.findAll();
        assertThat(relatorioPercObesosHomensList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
