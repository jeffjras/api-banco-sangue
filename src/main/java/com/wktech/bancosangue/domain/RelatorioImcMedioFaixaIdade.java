package com.wktech.bancosangue.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RelatorioImcMedioFaixaIdade.
 */
@Entity
@Table(name = "relatorio_imc_medio_faixa_idade")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelatorioImcMedioFaixaIdade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "faixa_etaria")
    private String faixaEtaria;

    @Column(name = "imc_medio", precision = 21, scale = 2)
    private BigDecimal imcMedio;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public RelatorioImcMedioFaixaIdade faixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
        return this;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public BigDecimal getImcMedio() {
        return imcMedio;
    }

    public RelatorioImcMedioFaixaIdade imcMedio(BigDecimal imcMedio) {
        this.imcMedio = imcMedio;
        return this;
    }

    public void setImcMedio(BigDecimal imcMedio) {
        this.imcMedio = imcMedio;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioImcMedioFaixaIdade)) {
            return false;
        }
        return id != null && id.equals(((RelatorioImcMedioFaixaIdade) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioImcMedioFaixaIdade{" +
            "id=" + getId() +
            ", faixaEtaria='" + getFaixaEtaria() + "'" +
            ", imcMedio=" + getImcMedio() +
            "}";
    }
}
