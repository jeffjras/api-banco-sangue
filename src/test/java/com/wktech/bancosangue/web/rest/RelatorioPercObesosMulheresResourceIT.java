package com.wktech.bancosangue.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.RelatorioPercObesosMulheres;
import com.wktech.bancosangue.repository.RelatorioPercObesosMulheresRepository;
import com.wktech.bancosangue.service.RelatorioPercObesosMulheresQueryService;
import com.wktech.bancosangue.service.RelatorioPercObesosMulheresService;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosMulheresCriteria;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosMulheresDTO;
import com.wktech.bancosangue.service.mapper.RelatorioPercObesosMulheresMapper;
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
 * Integration tests for the {@link RelatorioPercObesosMulheresResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RelatorioPercObesosMulheresResourceIT {
    private static final BigDecimal DEFAULT_PERCENTUAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERCENTUAL = new BigDecimal(2);
    private static final BigDecimal SMALLER_PERCENTUAL = new BigDecimal(1 - 1);

    @Autowired
    private RelatorioPercObesosMulheresRepository relatorioPercObesosMulheresRepository;

    @Autowired
    private RelatorioPercObesosMulheresMapper relatorioPercObesosMulheresMapper;

    @Autowired
    private RelatorioPercObesosMulheresService relatorioPercObesosMulheresService;

    @Autowired
    private RelatorioPercObesosMulheresQueryService relatorioPercObesosMulheresQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelatorioPercObesosMulheresMockMvc;

    private RelatorioPercObesosMulheres relatorioPercObesosMulheres;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioPercObesosMulheres createEntity(EntityManager em) {
        RelatorioPercObesosMulheres relatorioPercObesosMulheres = new RelatorioPercObesosMulheres().percentual(DEFAULT_PERCENTUAL);
        return relatorioPercObesosMulheres;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioPercObesosMulheres createUpdatedEntity(EntityManager em) {
        RelatorioPercObesosMulheres relatorioPercObesosMulheres = new RelatorioPercObesosMulheres().percentual(UPDATED_PERCENTUAL);
        return relatorioPercObesosMulheres;
    }

    @BeforeEach
    public void initTest() {
        relatorioPercObesosMulheres = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelatorioPercObesosMulheres() throws Exception {
        int databaseSizeBeforeCreate = relatorioPercObesosMulheresRepository.findAll().size();
        // Create the RelatorioPercObesosMulheres
        RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO = relatorioPercObesosMulheresMapper.toDto(
            relatorioPercObesosMulheres
        );
        restRelatorioPercObesosMulheresMockMvc
            .perform(
                post("/api/relatorio-perc-obesos-mulheres")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioPercObesosMulheresDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RelatorioPercObesosMulheres in the database
        List<RelatorioPercObesosMulheres> relatorioPercObesosMulheresList = relatorioPercObesosMulheresRepository.findAll();
        assertThat(relatorioPercObesosMulheresList).hasSize(databaseSizeBeforeCreate + 1);
        RelatorioPercObesosMulheres testRelatorioPercObesosMulheres = relatorioPercObesosMulheresList.get(
            relatorioPercObesosMulheresList.size() - 1
        );
        assertThat(testRelatorioPercObesosMulheres.getPercentual()).isEqualTo(DEFAULT_PERCENTUAL);
    }

    @Test
    @Transactional
    public void createRelatorioPercObesosMulheresWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relatorioPercObesosMulheresRepository.findAll().size();

        // Create the RelatorioPercObesosMulheres with an existing ID
        relatorioPercObesosMulheres.setId(1L);
        RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO = relatorioPercObesosMulheresMapper.toDto(
            relatorioPercObesosMulheres
        );

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelatorioPercObesosMulheresMockMvc
            .perform(
                post("/api/relatorio-perc-obesos-mulheres")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioPercObesosMulheresDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioPercObesosMulheres in the database
        List<RelatorioPercObesosMulheres> relatorioPercObesosMulheresList = relatorioPercObesosMulheresRepository.findAll();
        assertThat(relatorioPercObesosMulheresList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosMulheres() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get all the relatorioPercObesosMulheresList
        restRelatorioPercObesosMulheresMockMvc
            .perform(get("/api/relatorio-perc-obesos-mulheres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioPercObesosMulheres.getId().intValue())))
            .andExpect(jsonPath("$.[*].percentual").value(hasItem(DEFAULT_PERCENTUAL.intValue())));
    }

    @Test
    @Transactional
    public void getRelatorioPercObesosMulheres() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get the relatorioPercObesosMulheres
        restRelatorioPercObesosMulheresMockMvc
            .perform(get("/api/relatorio-perc-obesos-mulheres/{id}", relatorioPercObesosMulheres.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relatorioPercObesosMulheres.getId().intValue()))
            .andExpect(jsonPath("$.percentual").value(DEFAULT_PERCENTUAL.intValue()));
    }

    @Test
    @Transactional
    public void getRelatorioPercObesosMulheresByIdFiltering() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        Long id = relatorioPercObesosMulheres.getId();

        defaultRelatorioPercObesosMulheresShouldBeFound("id.equals=" + id);
        defaultRelatorioPercObesosMulheresShouldNotBeFound("id.notEquals=" + id);

        defaultRelatorioPercObesosMulheresShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultRelatorioPercObesosMulheresShouldNotBeFound("id.greaterThan=" + id);

        defaultRelatorioPercObesosMulheresShouldBeFound("id.lessThanOrEqual=" + id);
        defaultRelatorioPercObesosMulheresShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosMulheresByPercentualIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get all the relatorioPercObesosMulheresList where percentual equals to DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldBeFound("percentual.equals=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosMulheresList where percentual equals to UPDATED_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldNotBeFound("percentual.equals=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosMulheresByPercentualIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get all the relatorioPercObesosMulheresList where percentual not equals to DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldNotBeFound("percentual.notEquals=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosMulheresList where percentual not equals to UPDATED_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldBeFound("percentual.notEquals=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosMulheresByPercentualIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get all the relatorioPercObesosMulheresList where percentual in DEFAULT_PERCENTUAL or UPDATED_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldBeFound("percentual.in=" + DEFAULT_PERCENTUAL + "," + UPDATED_PERCENTUAL);

        // Get all the relatorioPercObesosMulheresList where percentual equals to UPDATED_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldNotBeFound("percentual.in=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosMulheresByPercentualIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get all the relatorioPercObesosMulheresList where percentual is not null
        defaultRelatorioPercObesosMulheresShouldBeFound("percentual.specified=true");

        // Get all the relatorioPercObesosMulheresList where percentual is null
        defaultRelatorioPercObesosMulheresShouldNotBeFound("percentual.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosMulheresByPercentualIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get all the relatorioPercObesosMulheresList where percentual is greater than or equal to DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldBeFound("percentual.greaterThanOrEqual=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosMulheresList where percentual is greater than or equal to UPDATED_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldNotBeFound("percentual.greaterThanOrEqual=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosMulheresByPercentualIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get all the relatorioPercObesosMulheresList where percentual is less than or equal to DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldBeFound("percentual.lessThanOrEqual=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosMulheresList where percentual is less than or equal to SMALLER_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldNotBeFound("percentual.lessThanOrEqual=" + SMALLER_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosMulheresByPercentualIsLessThanSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get all the relatorioPercObesosMulheresList where percentual is less than DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldNotBeFound("percentual.lessThan=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosMulheresList where percentual is less than UPDATED_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldBeFound("percentual.lessThan=" + UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void getAllRelatorioPercObesosMulheresByPercentualIsGreaterThanSomething() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        // Get all the relatorioPercObesosMulheresList where percentual is greater than DEFAULT_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldNotBeFound("percentual.greaterThan=" + DEFAULT_PERCENTUAL);

        // Get all the relatorioPercObesosMulheresList where percentual is greater than SMALLER_PERCENTUAL
        defaultRelatorioPercObesosMulheresShouldBeFound("percentual.greaterThan=" + SMALLER_PERCENTUAL);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRelatorioPercObesosMulheresShouldBeFound(String filter) throws Exception {
        restRelatorioPercObesosMulheresMockMvc
            .perform(get("/api/relatorio-perc-obesos-mulheres?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioPercObesosMulheres.getId().intValue())))
            .andExpect(jsonPath("$.[*].percentual").value(hasItem(DEFAULT_PERCENTUAL.intValue())));

        // Check, that the count call also returns 1
        restRelatorioPercObesosMulheresMockMvc
            .perform(get("/api/relatorio-perc-obesos-mulheres/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRelatorioPercObesosMulheresShouldNotBeFound(String filter) throws Exception {
        restRelatorioPercObesosMulheresMockMvc
            .perform(get("/api/relatorio-perc-obesos-mulheres?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRelatorioPercObesosMulheresMockMvc
            .perform(get("/api/relatorio-perc-obesos-mulheres/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingRelatorioPercObesosMulheres() throws Exception {
        // Get the relatorioPercObesosMulheres
        restRelatorioPercObesosMulheresMockMvc
            .perform(get("/api/relatorio-perc-obesos-mulheres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelatorioPercObesosMulheres() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        int databaseSizeBeforeUpdate = relatorioPercObesosMulheresRepository.findAll().size();

        // Update the relatorioPercObesosMulheres
        RelatorioPercObesosMulheres updatedRelatorioPercObesosMulheres = relatorioPercObesosMulheresRepository
            .findById(relatorioPercObesosMulheres.getId())
            .get();
        // Disconnect from session so that the updates on updatedRelatorioPercObesosMulheres are not directly saved in db
        em.detach(updatedRelatorioPercObesosMulheres);
        updatedRelatorioPercObesosMulheres.percentual(UPDATED_PERCENTUAL);
        RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO = relatorioPercObesosMulheresMapper.toDto(
            updatedRelatorioPercObesosMulheres
        );

        restRelatorioPercObesosMulheresMockMvc
            .perform(
                put("/api/relatorio-perc-obesos-mulheres")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioPercObesosMulheresDTO))
            )
            .andExpect(status().isOk());

        // Validate the RelatorioPercObesosMulheres in the database
        List<RelatorioPercObesosMulheres> relatorioPercObesosMulheresList = relatorioPercObesosMulheresRepository.findAll();
        assertThat(relatorioPercObesosMulheresList).hasSize(databaseSizeBeforeUpdate);
        RelatorioPercObesosMulheres testRelatorioPercObesosMulheres = relatorioPercObesosMulheresList.get(
            relatorioPercObesosMulheresList.size() - 1
        );
        assertThat(testRelatorioPercObesosMulheres.getPercentual()).isEqualTo(UPDATED_PERCENTUAL);
    }

    @Test
    @Transactional
    public void updateNonExistingRelatorioPercObesosMulheres() throws Exception {
        int databaseSizeBeforeUpdate = relatorioPercObesosMulheresRepository.findAll().size();

        // Create the RelatorioPercObesosMulheres
        RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO = relatorioPercObesosMulheresMapper.toDto(
            relatorioPercObesosMulheres
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelatorioPercObesosMulheresMockMvc
            .perform(
                put("/api/relatorio-perc-obesos-mulheres")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioPercObesosMulheresDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioPercObesosMulheres in the database
        List<RelatorioPercObesosMulheres> relatorioPercObesosMulheresList = relatorioPercObesosMulheresRepository.findAll();
        assertThat(relatorioPercObesosMulheresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelatorioPercObesosMulheres() throws Exception {
        // Initialize the database
        relatorioPercObesosMulheresRepository.saveAndFlush(relatorioPercObesosMulheres);

        int databaseSizeBeforeDelete = relatorioPercObesosMulheresRepository.findAll().size();

        // Delete the relatorioPercObesosMulheres
        restRelatorioPercObesosMulheresMockMvc
            .perform(
                delete("/api/relatorio-perc-obesos-mulheres/{id}", relatorioPercObesosMulheres.getId()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelatorioPercObesosMulheres> relatorioPercObesosMulheresList = relatorioPercObesosMulheresRepository.findAll();
        assertThat(relatorioPercObesosMulheresList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
