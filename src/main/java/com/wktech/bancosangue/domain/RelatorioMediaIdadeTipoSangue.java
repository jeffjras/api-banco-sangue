package com.wktech.bancosangue.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RelatorioMediaIdadeTipoSangue.
 */
@Entity
@Table(name = "relatorio_media_idade_tipo_sangue")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelatorioMediaIdadeTipoSangue implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_sangue")
    private String tipoSangue;

    @Column(name = "media_tipo", precision = 21, scale = 2)
    private BigDecimal mediaTipo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public RelatorioMediaIdadeTipoSangue tipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
        return this;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    public BigDecimal getMediaTipo() {
        return mediaTipo;
    }

    public RelatorioMediaIdadeTipoSangue mediaTipo(BigDecimal mediaTipo) {
        this.mediaTipo = mediaTipo;
        return this;
    }

    public void setMediaTipo(BigDecimal mediaTipo) {
        this.mediaTipo = mediaTipo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioMediaIdadeTipoSangue)) {
            return false;
        }
        return id != null && id.equals(((RelatorioMediaIdadeTipoSangue) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioMediaIdadeTipoSangue{" +
            "id=" + getId() +
            ", tipoSangue='" + getTipoSangue() + "'" +
            ", mediaTipo=" + getMediaTipo() +
            "}";
    }
}
