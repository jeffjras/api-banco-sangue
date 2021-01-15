package com.wktech.bancosangue.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link com.wktech.bancosangue.domain.Candidato} entity.
 */
public class CandidatoDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("rg")
    private String rg;

    @JsonProperty("data_nasc")
    @JsonFormat(pattern = "dd/mm/yyyy", shape = JsonFormat.Shape.STRING)
    private Date dataNasc;

    @JsonProperty("sexo")
    private String sexo;

    @JsonProperty("mae")
    private String mae;

    @JsonProperty("pai")
    private String pai;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("endereco")
    private String endereco;

    @JsonProperty("numero")
    private String numero;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("telefone_fixo")
    private String telefoneFixo;

    @JsonProperty("celular")
    private String celular;

    @JsonProperty("altura")
    private Double altura;

    @JsonProperty("peso")
    private Double peso;

    @JsonProperty("tipo_sanguineo")
    private String tipoSangue;

    private Long tipoSanguineoId;

    public CandidatoDTO() {}

    public CandidatoDTO(String data) {
        String dataFormatada = dataParaMySQL(data);
        this.dataNasc = Date.valueOf(dataFormatada);
        System.out.println(this.dataNasc);
    }

    public String dataParaMySQL(String data) {
        String ret = " - - ";
        try {
            data = data.replace("/", "");
        } catch (Exception e) {}
        try {
            data = data.replace("-", "");
        } catch (Exception e) {}
        try {
            data = data.substring(4, 8) + "-" + data.substring(2, 4) + "-" + data.substring(0, 2);
        } catch (Exception e) {}
        ret = data;
        return ret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    public Long getTipoSanguineoId() {
        return tipoSanguineoId;
    }

    public void setTipoSanguineoId(Long tipoSanguineoId) {
        this.tipoSanguineoId = tipoSanguineoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CandidatoDTO)) {
            return false;
        }

        return id != null && id.equals(((CandidatoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CandidatoDTO{" +
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
            ", tipoSanguineoId=" + getTipoSanguineoId() +
            "}";
    }

    public static void main(String args[]) {
        CandidatoDTO can = new CandidatoDTO();
        System.out.println(can.dataParaMySQL("18/03/1990"));
    }
}
