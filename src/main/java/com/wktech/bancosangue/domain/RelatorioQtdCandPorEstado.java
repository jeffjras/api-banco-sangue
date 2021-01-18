package com.wktech.bancosangue.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RelatorioQtdCandPorEstado.
 */
@Entity
@Table(name = "relatorio_qtd_cand_por_estado")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelatorioQtdCandPorEstado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qtde")
    private Integer qtde;

    @Column(name = "estado")
    private String estado;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtde() {
        return qtde;
    }

    public RelatorioQtdCandPorEstado qtde(Integer qtde) {
        this.qtde = qtde;
        return this;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public String getEstado() {
        return estado;
    }

    public RelatorioQtdCandPorEstado estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioQtdCandPorEstado)) {
            return false;
        }
        return id != null && id.equals(((RelatorioQtdCandPorEstado) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioQtdCandPorEstado{" +
            "id=" + getId() +
            ", qtde=" + getQtde() +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
