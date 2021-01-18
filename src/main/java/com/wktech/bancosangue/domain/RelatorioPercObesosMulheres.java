package com.wktech.bancosangue.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RelatorioPercObesosMulheres.
 */
@Entity
@Table(name = "relatorio_perc_obesos_mulheres")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelatorioPercObesosMulheres implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "percentual", precision = 21, scale = 2)
    private BigDecimal percentual;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public RelatorioPercObesosMulheres percentual(BigDecimal percentual) {
        this.percentual = percentual;
        return this;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioPercObesosMulheres)) {
            return false;
        }
        return id != null && id.equals(((RelatorioPercObesosMulheres) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioPercObesosMulheres{" +
            "id=" + getId() +
            ", percentual=" + getPercentual() +
            "}";
    }
}
