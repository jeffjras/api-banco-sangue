package com.wktech.bancosangue.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DoacaoTipoSanguineo.
 */
@Entity
@Table(name = "doacao_tipo_sanguineo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DoacaoTipoSanguineo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pode_doar_para")
    private String podeDoarPara;

    @OneToMany(mappedBy = "doacaoTipoSanguineo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TipoSanguineo> listaTipoSanguineos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPodeDoarPara() {
        return podeDoarPara;
    }

    public DoacaoTipoSanguineo podeDoarPara(String podeDoarPara) {
        this.podeDoarPara = podeDoarPara;
        return this;
    }

    public void setPodeDoarPara(String podeDoarPara) {
        this.podeDoarPara = podeDoarPara;
    }

    public Set<TipoSanguineo> getListaTipoSanguineos() {
        return listaTipoSanguineos;
    }

    public DoacaoTipoSanguineo listaTipoSanguineos(Set<TipoSanguineo> tipoSanguineos) {
        this.listaTipoSanguineos = tipoSanguineos;
        return this;
    }

    public DoacaoTipoSanguineo addListaTipoSanguineos(TipoSanguineo tipoSanguineo) {
        this.listaTipoSanguineos.add(tipoSanguineo);
        tipoSanguineo.setDoacaoTipoSanguineo(this);
        return this;
    }

    public DoacaoTipoSanguineo removeListaTipoSanguineos(TipoSanguineo tipoSanguineo) {
        this.listaTipoSanguineos.remove(tipoSanguineo);
        tipoSanguineo.setDoacaoTipoSanguineo(null);
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
        if (!(o instanceof DoacaoTipoSanguineo)) {
            return false;
        }
        return id != null && id.equals(((DoacaoTipoSanguineo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoacaoTipoSanguineo{" +
            "id=" + getId() +
            ", podeDoarPara='" + getPodeDoarPara() + "'" +
            "}";
    }
}
