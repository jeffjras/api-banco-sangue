package com.wktech.bancosangue.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.wktech.bancosangue.domain.RelatorioPercObesosMulheres} entity.
 */
public class RelatorioPercObesosMulheresDTO implements Serializable {
    private Long id;

    private BigDecimal percentual;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioPercObesosMulheresDTO)) {
            return false;
        }

        return id != null && id.equals(((RelatorioPercObesosMulheresDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioPercObesosMulheresDTO{" +
            "id=" + getId() +
            ", percentual=" + getPercentual() +
            "}";
    }
}
