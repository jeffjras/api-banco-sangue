package com.wktech.bancosangue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Candidato.
 */
@Entity
@Table(name = "candidato")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "data_nasc")
    private LocalDate dataNasc;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "mae")
    private String mae;

    @Column(name = "pai")
    private String pai;

    @Column(name = "email")
    private String email;

    @Column(name = "cep")
    private String cep;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numero")
    private String numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "telefone_fixo")
    private String telefoneFixo;

    @Column(name = "celular")
    private String celular;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "tipo_sangue")
    private String tipoSangue;

    @ManyToOne
    @JsonIgnoreProperties(value = "listaCandidatos", allowSetters = true)
    private TipoSanguineo tipoSanguineo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Candidato nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Candidato cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public Candidato rg(String rg) {
        this.rg = rg;
        return this;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public Candidato dataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
        return this;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public Candidato sexo(String sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMae() {
        return mae;
    }

    public Candidato mae(String mae) {
        this.mae = mae;
        return this;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public Candidato pai(String pai) {
        this.pai = pai;
        return this;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getEmail() {
        return email;
    }

    public Candidato email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public Candidato cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public Candidato endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public Candidato numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public Candidato bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public Candidato cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Candidato estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public Candidato telefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
        return this;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getCelular() {
        return celular;
    }

    public Candidato celular(String celular) {
        this.celular = celular;
        return this;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Double getAltura() {
        return altura;
    }

    public Candidato altura(Double altura) {
        this.altura = altura;
        return this;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public Candidato peso(Double peso) {
        this.peso = peso;
        return this;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public Candidato tipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
        return this;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public Candidato tipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
        return this;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Candidato)) {
            return false;
        }
        return id != null && id.equals(((Candidato) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Candidato{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", rg='" + getRg() + "'" +
            ", dataNasc='" + getDataNasc() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", mae='" + getMae() + "'" +
            ", pai='" + getPai() + "'" +
            ", email='" + getEmail() + "'" +
            ", cep='" + getCep() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", numero='" + getNumero() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", telefoneFixo='" + getTelefoneFixo() + "'" +
            ", celular='" + getCelular() + "'" +
            ", altura=" + getAltura() +
            ", peso=" + getPeso() +
            ", tipoSangue='" + getTipoSangue() + "'" +
            "}";
    }
}
