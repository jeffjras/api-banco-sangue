package com.wktech.bancosangue.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RelatorioQtdeDoadoresParaCadaTipoReceptor.
 */
@Entity
@Table(name = "relatorio_qtde_doadores_para_cada_tipo_receptor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelatorioQtdeDoadoresParaCadaTipoReceptor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sangue")
    private String sangue;

    @Column(name = "total_doador")
    private Integer totalDoador;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSangue() {
        return sangue;
    }

    public RelatorioQtdeDoadoresParaCadaTipoReceptor sangue(String sangue) {
        this.sangue = sangue;
        return this;
    }

    public void setSangue(String sangue) {
        this.sangue = sangue;
    }

    public Integer getTotalDoador() {
        return totalDoador;
    }

    public RelatorioQtdeDoadoresParaCadaTipoReceptor totalDoador(Integer totalDoador) {
        this.totalDoador = totalDoador;
        return this;
    }

    public void setTotalDoador(Integer totalDoador) {
        this.totalDoador = totalDoador;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioQtdeDoadoresParaCadaTipoReceptor)) {
            return false;
        }
        return id != null && id.equals(((RelatorioQtdeDoadoresParaCadaTipoReceptor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioQtdeDoadoresParaCadaTipoReceptor{" +
            "id=" + getId() +
            ", sangue='" + getSangue() + "'" +
            ", totalDoador=" + getTotalDoador() +
            "}";
    }
}
