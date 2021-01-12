package com.wktech.bancosangue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TipoSanguineo.
 */
@Entity
@Table(name = "tipo_sanguineo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TipoSanguineo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "tipoSanguineo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Candidato> listaCandidatos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "listaTipoSanguineos", allowSetters = true)
    private DoacaoTipoSanguineo doacaoTipoSanguineo;

    @ManyToOne
    @JsonIgnoreProperties(value = "listaTipoSanguineos", allowSetters = true)
    private RecepcaoTipoSanguineo recepcaoTipoSanguineo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoSanguineo descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Candidato> getListaCandidatos() {
        return listaCandidatos;
    }

    public TipoSanguineo listaCandidatos(Set<Candidato> candidatoes) {
        this.listaCandidatos = candidatoes;
        return this;
    }

    public TipoSanguineo addListaCandidatos(Candidato candidato) {
        this.listaCandidatos.add(candidato);
        candidato.setTipoSanguineo(this);
        return this;
    }

    public TipoSanguineo removeListaCandidatos(Candidato candidato) {
        this.listaCandidatos.remove(candidato);
        candidato.setTipoSanguineo(null);
        return this;
    }

    public void setListaCandidatos(Set<Candidato> candidatoes) {
        this.listaCandidatos = candidatoes;
    }

    public DoacaoTipoSanguineo getDoacaoTipoSanguineo() {
        return doacaoTipoSanguineo;
    }

    public TipoSanguineo doacaoTipoSanguineo(DoacaoTipoSanguineo doacaoTipoSanguineo) {
        this.doacaoTipoSanguineo = doacaoTipoSanguineo;
        return this;
    }

    public void setDoacaoTipoSanguineo(DoacaoTipoSanguineo doacaoTipoSanguineo) {
        this.doacaoTipoSanguineo = doacaoTipoSanguineo;
    }

    public RecepcaoTipoSanguineo getRecepcaoTipoSanguineo() {
        return recepcaoTipoSanguineo;
    }

    public TipoSanguineo recepcaoTipoSanguineo(RecepcaoTipoSanguineo recepcaoTipoSanguineo) {
        this.recepcaoTipoSanguineo = recepcaoTipoSanguineo;
        return this;
    }

    public void setRecepcaoTipoSanguineo(RecepcaoTipoSanguineo recepcaoTipoSanguineo) {
        this.recepcaoTipoSanguineo = recepcaoTipoSanguineo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoSanguineo)) {
            return false;
        }
        return id != null && id.equals(((TipoSanguineo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoSanguineo{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
