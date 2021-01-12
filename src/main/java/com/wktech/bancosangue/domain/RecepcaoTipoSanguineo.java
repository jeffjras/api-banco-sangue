package com.wktech.bancosangue.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A RecepcaoTipoSanguineo.
 */
@Entity
@Table(name = "recepcao_tipo_sanguineo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RecepcaoTipoSanguineo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pode_receber_de")
    private String podeReceberDe;

    @OneToMany(mappedBy = "recepcaoTipoSanguineo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TipoSanguineo> listaTipoSanguineos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPodeReceberDe() {
        return podeReceberDe;
    }

    public RecepcaoTipoSanguineo podeReceberDe(String podeReceberDe) {
        this.podeReceberDe = podeReceberDe;
        return this;
    }

    public void setPodeReceberDe(String podeReceberDe) {
        this.podeReceberDe = podeReceberDe;
    }

    public Set<TipoSanguineo> getListaTipoSanguineos() {
        return listaTipoSanguineos;
    }

    public RecepcaoTipoSanguineo listaTipoSanguineos(Set<TipoSanguineo> tipoSanguineos) {
        this.listaTipoSanguineos = tipoSanguineos;
        return this;
    }

    public RecepcaoTipoSanguineo addListaTipoSanguineos(TipoSanguineo tipoSanguineo) {
        this.listaTipoSanguineos.add(tipoSanguineo);
        tipoSanguineo.setRecepcaoTipoSanguineo(this);
        return this;
    }

    public RecepcaoTipoSanguineo removeListaTipoSanguineos(TipoSanguineo tipoSanguineo) {
        this.listaTipoSanguineos.remove(tipoSanguineo);
        tipoSanguineo.setRecepcaoTipoSanguineo(null);
        return this;
    }

    public void setListaTipoSanguineos(Set<TipoSanguineo> tipoSanguineos) {
        this.listaTipoSanguineos = tipoSanguineos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecepcaoTipoSanguineo)) {
            return false;
        }
        return id != null && id.equals(((RecepcaoTipoSanguineo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecepcaoTipoSanguineo{" +
            "id=" + getId() +
            ", podeReceberDe='" + getPodeReceberDe() + "'" +
            "}";
    }
}
