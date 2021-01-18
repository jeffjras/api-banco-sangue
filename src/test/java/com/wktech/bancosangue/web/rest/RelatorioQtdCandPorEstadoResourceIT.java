package com.wktech.bancosangue.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.RelatorioQtdCandPorEstado;
import com.wktech.bancosangue.repository.RelatorioQtdCandPorEstadoRepository;
import com.wktech.bancosangue.service.RelatorioQtdCandPorEstadoQueryService;
import com.wktech.bancosangue.service.RelatorioQtdCandPorEstadoService;
import com.wktech.bancosangue.service.dto.RelatorioQtdCandPorEstadoCriteria;
import com.wktech.bancosangue.service.dto.RelatorioQtdCandPorEstadoDTO;
import com.wktech.bancosangue.service.mapper.RelatorioQtdCandPorEstadoMapper;
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
 * Integration tests for the {@link RelatorioQtdCandPorEstadoResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RelatorioQtdCandPorEstadoResourceIT {
    private static final Integer DEFAULT_QTDE = 1;
    private static final Integer UPDATED_QTDE = 2;
    private static final Integer SMALLER_QTDE = 1 - 1;

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    @Autowired
    private RelatorioQtdCandPorEstadoRepository relatorioQtdCandPorEstadoRepository;

    @Autowired
    private RelatorioQtdCandPorEstadoMapper relatorioQtdCandPorEstadoMapper;

    @Autowired
    private RelatorioQtdCandPorEstadoService relatorioQtdCandPorEstadoService;

    @Autowired
    private RelatorioQtdCandPorEstadoQueryService relatorioQtdCandPorEstadoQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelatorioQtdCandPorEstadoMockMvc;

    private RelatorioQtdCandPorEstado relatorioQtdCandPorEstado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioQtdCandPorEstado createEntity(EntityManager em) {
        RelatorioQtdCandPorEstado relatorioQtdCandPorEstado = new RelatorioQtdCandPorEstado().qtde(DEFAULT_QTDE).estado(DEFAULT_ESTADO);
        return relatorioQtdCandPorEstado;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelatorioQtdCandPorEstado createUpdatedEntity(EntityManager em) {
        RelatorioQtdCandPorEstado relatorioQtdCandPorEstado = new RelatorioQtdCandPorEstado().qtde(UPDATED_QTDE).estado(UPDATED_ESTADO);
        return relatorioQtdCandPorEstado;
    }

    @BeforeEach
    public void initTest() {
        relatorioQtdCandPorEstado = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelatorioQtdCandPorEstado() throws Exception {
        int databaseSizeBeforeCreate = relatorioQtdCandPorEstadoRepository.findAll().size();
        // Create the RelatorioQtdCandPorEstado
        RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO = relatorioQtdCandPorEstadoMapper.toDto(relatorioQtdCandPorEstado);
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(
                post("/api/relatorio-qtd-cand-por-estados")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioQtdCandPorEstadoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RelatorioQtdCandPorEstado in the database
        List<RelatorioQtdCandPorEstado> relatorioQtdCandPorEstadoList = relatorioQtdCandPorEstadoRepository.findAll();
        assertThat(relatorioQtdCandPorEstadoList).hasSize(databaseSizeBeforeCreate + 1);
        RelatorioQtdCandPorEstado testRelatorioQtdCandPorEstado = relatorioQtdCandPorEstadoList.get(
            relatorioQtdCandPorEstadoList.size() - 1
        );
        assertThat(testRelatorioQtdCandPorEstado.getQtde()).isEqualTo(DEFAULT_QTDE);
        assertThat(testRelatorioQtdCandPorEstado.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createRelatorioQtdCandPorEstadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relatorioQtdCandPorEstadoRepository.findAll().size();

        // Create the RelatorioQtdCandPorEstado with an existing ID
        relatorioQtdCandPorEstado.setId(1L);
        RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO = relatorioQtdCandPorEstadoMapper.toDto(relatorioQtdCandPorEstado);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(
                post("/api/relatorio-qtd-cand-por-estados")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioQtdCandPorEstadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioQtdCandPorEstado in the database
        List<RelatorioQtdCandPorEstado> relatorioQtdCandPorEstadoList = relatorioQtdCandPorEstadoRepository.findAll();
        assertThat(relatorioQtdCandPorEstadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstados() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(get("/api/relatorio-qtd-cand-por-estados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioQtdCandPorEstado.getId().intValue())))
            .andExpect(jsonPath("$.[*].qtde").value(hasItem(DEFAULT_QTDE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));
    }

    @Test
    @Transactional
    public void getRelatorioQtdCandPorEstado() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get the relatorioQtdCandPorEstado
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(get("/api/relatorio-qtd-cand-por-estados/{id}", relatorioQtdCandPorEstado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relatorioQtdCandPorEstado.getId().intValue()))
            .andExpect(jsonPath("$.qtde").value(DEFAULT_QTDE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO));
    }

    @Test
    @Transactional
    public void getRelatorioQtdCandPorEstadosByIdFiltering() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        Long id = relatorioQtdCandPorEstado.getId();

        defaultRelatorioQtdCandPorEstadoShouldBeFound("id.equals=" + id);
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("id.notEquals=" + id);

        defaultRelatorioQtdCandPorEstadoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("id.greaterThan=" + id);

        defaultRelatorioQtdCandPorEstadoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByQtdeIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where qtde equals to DEFAULT_QTDE
        defaultRelatorioQtdCandPorEstadoShouldBeFound("qtde.equals=" + DEFAULT_QTDE);

        // Get all the relatorioQtdCandPorEstadoList where qtde equals to UPDATED_QTDE
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("qtde.equals=" + UPDATED_QTDE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByQtdeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where qtde not equals to DEFAULT_QTDE
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("qtde.notEquals=" + DEFAULT_QTDE);

        // Get all the relatorioQtdCandPorEstadoList where qtde not equals to UPDATED_QTDE
        defaultRelatorioQtdCandPorEstadoShouldBeFound("qtde.notEquals=" + UPDATED_QTDE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByQtdeIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where qtde in DEFAULT_QTDE or UPDATED_QTDE
        defaultRelatorioQtdCandPorEstadoShouldBeFound("qtde.in=" + DEFAULT_QTDE + "," + UPDATED_QTDE);

        // Get all the relatorioQtdCandPorEstadoList where qtde equals to UPDATED_QTDE
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("qtde.in=" + UPDATED_QTDE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByQtdeIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where qtde is not null
        defaultRelatorioQtdCandPorEstadoShouldBeFound("qtde.specified=true");

        // Get all the relatorioQtdCandPorEstadoList where qtde is null
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("qtde.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByQtdeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where qtde is greater than or equal to DEFAULT_QTDE
        defaultRelatorioQtdCandPorEstadoShouldBeFound("qtde.greaterThanOrEqual=" + DEFAULT_QTDE);

        // Get all the relatorioQtdCandPorEstadoList where qtde is greater than or equal to UPDATED_QTDE
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("qtde.greaterThanOrEqual=" + UPDATED_QTDE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByQtdeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where qtde is less than or equal to DEFAULT_QTDE
        defaultRelatorioQtdCandPorEstadoShouldBeFound("qtde.lessThanOrEqual=" + DEFAULT_QTDE);

        // Get all the relatorioQtdCandPorEstadoList where qtde is less than or equal to SMALLER_QTDE
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("qtde.lessThanOrEqual=" + SMALLER_QTDE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByQtdeIsLessThanSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where qtde is less than DEFAULT_QTDE
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("qtde.lessThan=" + DEFAULT_QTDE);

        // Get all the relatorioQtdCandPorEstadoList where qtde is less than UPDATED_QTDE
        defaultRelatorioQtdCandPorEstadoShouldBeFound("qtde.lessThan=" + UPDATED_QTDE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByQtdeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where qtde is greater than DEFAULT_QTDE
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("qtde.greaterThan=" + DEFAULT_QTDE);

        // Get all the relatorioQtdCandPorEstadoList where qtde is greater than SMALLER_QTDE
        defaultRelatorioQtdCandPorEstadoShouldBeFound("qtde.greaterThan=" + SMALLER_QTDE);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where estado equals to DEFAULT_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the relatorioQtdCandPorEstadoList where estado equals to UPDATED_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where estado not equals to DEFAULT_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("estado.notEquals=" + DEFAULT_ESTADO);

        // Get all the relatorioQtdCandPorEstadoList where estado not equals to UPDATED_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldBeFound("estado.notEquals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the relatorioQtdCandPorEstadoList where estado equals to UPDATED_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where estado is not null
        defaultRelatorioQtdCandPorEstadoShouldBeFound("estado.specified=true");

        // Get all the relatorioQtdCandPorEstadoList where estado is null
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("estado.specified=false");
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByEstadoContainsSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where estado contains DEFAULT_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldBeFound("estado.contains=" + DEFAULT_ESTADO);

        // Get all the relatorioQtdCandPorEstadoList where estado contains UPDATED_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("estado.contains=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllRelatorioQtdCandPorEstadosByEstadoNotContainsSomething() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        // Get all the relatorioQtdCandPorEstadoList where estado does not contain DEFAULT_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldNotBeFound("estado.doesNotContain=" + DEFAULT_ESTADO);

        // Get all the relatorioQtdCandPorEstadoList where estado does not contain UPDATED_ESTADO
        defaultRelatorioQtdCandPorEstadoShouldBeFound("estado.doesNotContain=" + UPDATED_ESTADO);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRelatorioQtdCandPorEstadoShouldBeFound(String filter) throws Exception {
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(get("/api/relatorio-qtd-cand-por-estados?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatorioQtdCandPorEstado.getId().intValue())))
            .andExpect(jsonPath("$.[*].qtde").value(hasItem(DEFAULT_QTDE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));

        // Check, that the count call also returns 1
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(get("/api/relatorio-qtd-cand-por-estados/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRelatorioQtdCandPorEstadoShouldNotBeFound(String filter) throws Exception {
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(get("/api/relatorio-qtd-cand-por-estados?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(get("/api/relatorio-qtd-cand-por-estados/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingRelatorioQtdCandPorEstado() throws Exception {
        // Get the relatorioQtdCandPorEstado
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(get("/api/relatorio-qtd-cand-por-estados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelatorioQtdCandPorEstado() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        int databaseSizeBeforeUpdate = relatorioQtdCandPorEstadoRepository.findAll().size();

        // Update the relatorioQtdCandPorEstado
        RelatorioQtdCandPorEstado updatedRelatorioQtdCandPorEstado = relatorioQtdCandPorEstadoRepository
            .findById(relatorioQtdCandPorEstado.getId())
            .get();
        // Disconnect from session so that the updates on updatedRelatorioQtdCandPorEstado are not directly saved in db
        em.detach(updatedRelatorioQtdCandPorEstado);
        updatedRelatorioQtdCandPorEstado.qtde(UPDATED_QTDE).estado(UPDATED_ESTADO);
        RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO = relatorioQtdCandPorEstadoMapper.toDto(updatedRelatorioQtdCandPorEstado);

        restRelatorioQtdCandPorEstadoMockMvc
            .perform(
                put("/api/relatorio-qtd-cand-por-estados")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioQtdCandPorEstadoDTO))
            )
            .andExpect(status().isOk());

        // Validate the RelatorioQtdCandPorEstado in the database
        List<RelatorioQtdCandPorEstado> relatorioQtdCandPorEstadoList = relatorioQtdCandPorEstadoRepository.findAll();
        assertThat(relatorioQtdCandPorEstadoList).hasSize(databaseSizeBeforeUpdate);
        RelatorioQtdCandPorEstado testRelatorioQtdCandPorEstado = relatorioQtdCandPorEstadoList.get(
            relatorioQtdCandPorEstadoList.size() - 1
        );
        assertThat(testRelatorioQtdCandPorEstado.getQtde()).isEqualTo(UPDATED_QTDE);
        assertThat(testRelatorioQtdCandPorEstado.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingRelatorioQtdCandPorEstado() throws Exception {
        int databaseSizeBeforeUpdate = relatorioQtdCandPorEstadoRepository.findAll().size();

        // Create the RelatorioQtdCandPorEstado
        RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO = relatorioQtdCandPorEstadoMapper.toDto(relatorioQtdCandPorEstado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(
                put("/api/relatorio-qtd-cand-por-estados")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relatorioQtdCandPorEstadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelatorioQtdCandPorEstado in the database
        List<RelatorioQtdCandPorEstado> relatorioQtdCandPorEstadoList = relatorioQtdCandPorEstadoRepository.findAll();
        assertThat(relatorioQtdCandPorEstadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelatorioQtdCandPorEstado() throws Exception {
        // Initialize the database
        relatorioQtdCandPorEstadoRepository.saveAndFlush(relatorioQtdCandPorEstado);

        int databaseSizeBeforeDelete = relatorioQtdCandPorEstadoRepository.findAll().size();

        // Delete the relatorioQtdCandPorEstado
        restRelatorioQtdCandPorEstadoMockMvc
            .perform(
                delete("/api/relatorio-qtd-cand-por-estados/{id}", relatorioQtdCandPorEstado.getId()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelatorioQtdCandPorEstado> relatorioQtdCandPorEstadoList = relatorioQtdCandPorEstadoRepository.findAll();
        assertThat(relatorioQtdCandPorEstadoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
