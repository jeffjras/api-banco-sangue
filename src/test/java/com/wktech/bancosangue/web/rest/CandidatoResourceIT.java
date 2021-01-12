package com.wktech.bancosangue.web.rest;

import com.wktech.bancosangue.BancoSangueApp;
import com.wktech.bancosangue.domain.Candidato;
import com.wktech.bancosangue.repository.CandidatoRepository;
import com.wktech.bancosangue.service.CandidatoService;
import com.wktech.bancosangue.service.dto.CandidatoDTO;
import com.wktech.bancosangue.service.mapper.CandidatoMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CandidatoResource} REST controller.
 */
@SpringBootTest(classes = BancoSangueApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CandidatoResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_CPF = "AAAAAAAAAA";
    private static final String UPDATED_CPF = "BBBBBBBBBB";

    private static final String DEFAULT_RG = "AAAAAAAAAA";
    private static final String UPDATED_RG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_NASC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_NASC = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SEXO = "AAAAAAAAAA";
    private static final String UPDATED_SEXO = "BBBBBBBBBB";

    private static final String DEFAULT_MAE = "AAAAAAAAAA";
    private static final String UPDATED_MAE = "BBBBBBBBBB";

    private static final String DEFAULT_PAI = "AAAAAAAAAA";
    private static final String UPDATED_PAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CEP = "AAAAAAAAAA";
    private static final String UPDATED_CEP = "BBBBBBBBBB";

    private static final String DEFAULT_ENDERECO = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE_FIXO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE_FIXO = "BBBBBBBBBB";

    private static final String DEFAULT_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_CELULAR = "BBBBBBBBBB";

    private static final Double DEFAULT_ALTURA = 1D;
    private static final Double UPDATED_ALTURA = 2D;

    private static final Double DEFAULT_PESO = 1D;
    private static final Double UPDATED_PESO = 2D;

    private static final String DEFAULT_TIPO_SANGUE = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_SANGUE = "BBBBBBBBBB";

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private CandidatoMapper candidatoMapper;

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCandidatoMockMvc;

    private Candidato candidato;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Candidato createEntity(EntityManager em) {
        Candidato candidato = new Candidato()
            .nome(DEFAULT_NOME)
            .cpf(DEFAULT_CPF)
            .rg(DEFAULT_RG)
            .dataNasc(DEFAULT_DATA_NASC)
            .sexo(DEFAULT_SEXO)
            .mae(DEFAULT_MAE)
            .pai(DEFAULT_PAI)
            .email(DEFAULT_EMAIL)
            .cep(DEFAULT_CEP)
            .endereco(DEFAULT_ENDERECO)
            .numero(DEFAULT_NUMERO)
            .bairro(DEFAULT_BAIRRO)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .telefoneFixo(DEFAULT_TELEFONE_FIXO)
            .celular(DEFAULT_CELULAR)
            .altura(DEFAULT_ALTURA)
            .peso(DEFAULT_PESO)
            .tipoSangue(DEFAULT_TIPO_SANGUE);
        return candidato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Candidato createUpdatedEntity(EntityManager em) {
        Candidato candidato = new Candidato()
            .nome(UPDATED_NOME)
            .cpf(UPDATED_CPF)
            .rg(UPDATED_RG)
            .dataNasc(UPDATED_DATA_NASC)
            .sexo(UPDATED_SEXO)
            .mae(UPDATED_MAE)
            .pai(UPDATED_PAI)
            .email(UPDATED_EMAIL)
            .cep(UPDATED_CEP)
            .endereco(UPDATED_ENDERECO)
            .numero(UPDATED_NUMERO)
            .bairro(UPDATED_BAIRRO)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .telefoneFixo(UPDATED_TELEFONE_FIXO)
            .celular(UPDATED_CELULAR)
            .altura(UPDATED_ALTURA)
            .peso(UPDATED_PESO)
            .tipoSangue(UPDATED_TIPO_SANGUE);
        return candidato;
    }

    @BeforeEach
    public void initTest() {
        candidato = createEntity(em);
    }

    @Test
    @Transactional
    public void createCandidato() throws Exception {
        int databaseSizeBeforeCreate = candidatoRepository.findAll().size();
        // Create the Candidato
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);
        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isCreated());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeCreate + 1);
        Candidato testCandidato = candidatoList.get(candidatoList.size() - 1);
        assertThat(testCandidato.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testCandidato.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testCandidato.getRg()).isEqualTo(DEFAULT_RG);
        assertThat(testCandidato.getDataNasc()).isEqualTo(DEFAULT_DATA_NASC);
        assertThat(testCandidato.getSexo()).isEqualTo(DEFAULT_SEXO);
        assertThat(testCandidato.getMae()).isEqualTo(DEFAULT_MAE);
        assertThat(testCandidato.getPai()).isEqualTo(DEFAULT_PAI);
        assertThat(testCandidato.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCandidato.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testCandidato.getEndereco()).isEqualTo(DEFAULT_ENDERECO);
        assertThat(testCandidato.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testCandidato.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testCandidato.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testCandidato.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testCandidato.getTelefoneFixo()).isEqualTo(DEFAULT_TELEFONE_FIXO);
        assertThat(testCandidato.getCelular()).isEqualTo(DEFAULT_CELULAR);
        assertThat(testCandidato.getAltura()).isEqualTo(DEFAULT_ALTURA);
        assertThat(testCandidato.getPeso()).isEqualTo(DEFAULT_PESO);
        assertThat(testCandidato.getTipoSangue()).isEqualTo(DEFAULT_TIPO_SANGUE);
    }

    @Test
    @Transactional
    public void createCandidatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = candidatoRepository.findAll().size();

        // Create the Candidato with an existing ID
        candidato.setId(1L);
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCandidatoes() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList
        restCandidatoMockMvc.perform(get("/api/candidatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidato.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].dataNasc").value(hasItem(DEFAULT_DATA_NASC.toString())))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO)))
            .andExpect(jsonPath("$.[*].mae").value(hasItem(DEFAULT_MAE)))
            .andExpect(jsonPath("$.[*].pai").value(hasItem(DEFAULT_PAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].telefoneFixo").value(hasItem(DEFAULT_TELEFONE_FIXO)))
            .andExpect(jsonPath("$.[*].celular").value(hasItem(DEFAULT_CELULAR)))
            .andExpect(jsonPath("$.[*].altura").value(hasItem(DEFAULT_ALTURA.doubleValue())))
            .andExpect(jsonPath("$.[*].peso").value(hasItem(DEFAULT_PESO.doubleValue())))
            .andExpect(jsonPath("$.[*].tipoSangue").value(hasItem(DEFAULT_TIPO_SANGUE)));
    }
    
    @Test
    @Transactional
    public void getCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get the candidato
        restCandidatoMockMvc.perform(get("/api/candidatoes/{id}", candidato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(candidato.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.rg").value(DEFAULT_RG))
            .andExpect(jsonPath("$.dataNasc").value(DEFAULT_DATA_NASC.toString()))
            .andExpect(jsonPath("$.sexo").value(DEFAULT_SEXO))
            .andExpect(jsonPath("$.mae").value(DEFAULT_MAE))
            .andExpect(jsonPath("$.pai").value(DEFAULT_PAI))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.telefoneFixo").value(DEFAULT_TELEFONE_FIXO))
            .andExpect(jsonPath("$.celular").value(DEFAULT_CELULAR))
            .andExpect(jsonPath("$.altura").value(DEFAULT_ALTURA.doubleValue()))
            .andExpect(jsonPath("$.peso").value(DEFAULT_PESO.doubleValue()))
            .andExpect(jsonPath("$.tipoSangue").value(DEFAULT_TIPO_SANGUE));
    }
    @Test
    @Transactional
    public void getNonExistingCandidato() throws Exception {
        // Get the candidato
        restCandidatoMockMvc.perform(get("/api/candidatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        int databaseSizeBeforeUpdate = candidatoRepository.findAll().size();

        // Update the candidato
        Candidato updatedCandidato = candidatoRepository.findById(candidato.getId()).get();
        // Disconnect from session so that the updates on updatedCandidato are not directly saved in db
        em.detach(updatedCandidato);
        updatedCandidato
            .nome(UPDATED_NOME)
            .cpf(UPDATED_CPF)
            .rg(UPDATED_RG)
            .dataNasc(UPDATED_DATA_NASC)
            .sexo(UPDATED_SEXO)
            .mae(UPDATED_MAE)
            .pai(UPDATED_PAI)
            .email(UPDATED_EMAIL)
            .cep(UPDATED_CEP)
            .endereco(UPDATED_ENDERECO)
            .numero(UPDATED_NUMERO)
            .bairro(UPDATED_BAIRRO)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .telefoneFixo(UPDATED_TELEFONE_FIXO)
            .celular(UPDATED_CELULAR)
            .altura(UPDATED_ALTURA)
            .peso(UPDATED_PESO)
            .tipoSangue(UPDATED_TIPO_SANGUE);
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(updatedCandidato);

        restCandidatoMockMvc.perform(put("/api/candidatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isOk());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeUpdate);
        Candidato testCandidato = candidatoList.get(candidatoList.size() - 1);
        assertThat(testCandidato.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCandidato.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testCandidato.getRg()).isEqualTo(UPDATED_RG);
        assertThat(testCandidato.getDataNasc()).isEqualTo(UPDATED_DATA_NASC);
        assertThat(testCandidato.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testCandidato.getMae()).isEqualTo(UPDATED_MAE);
        assertThat(testCandidato.getPai()).isEqualTo(UPDATED_PAI);
        assertThat(testCandidato.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCandidato.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testCandidato.getEndereco()).isEqualTo(UPDATED_ENDERECO);
        assertThat(testCandidato.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testCandidato.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testCandidato.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testCandidato.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testCandidato.getTelefoneFixo()).isEqualTo(UPDATED_TELEFONE_FIXO);
        assertThat(testCandidato.getCelular()).isEqualTo(UPDATED_CELULAR);
        assertThat(testCandidato.getAltura()).isEqualTo(UPDATED_ALTURA);
        assertThat(testCandidato.getPeso()).isEqualTo(UPDATED_PESO);
        assertThat(testCandidato.getTipoSangue()).isEqualTo(UPDATED_TIPO_SANGUE);
    }

    @Test
    @Transactional
    public void updateNonExistingCandidato() throws Exception {
        int databaseSizeBeforeUpdate = candidatoRepository.findAll().size();

        // Create the Candidato
        CandidatoDTO candidatoDTO = candidatoMapper.toDto(candidato);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCandidatoMockMvc.perform(put("/api/candidatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        int databaseSizeBeforeDelete = candidatoRepository.findAll().size();

        // Delete the candidato
        restCandidatoMockMvc.perform(delete("/api/candidatoes/{id}", candidato.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
