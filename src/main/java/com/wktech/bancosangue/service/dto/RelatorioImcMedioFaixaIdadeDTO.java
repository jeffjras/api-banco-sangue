package com.wktech.bancosangue.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.wktech.bancosangue.domain.RelatorioImcMedioFaixaIdade} entity.
 */
public class RelatorioImcMedioFaixaIdadeDTO implements Serializable {
    private Long id;

    private String faixaEtaria;

    private BigDecimal imcMedio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public BigDecimal getImcMedio() {
        return imcMedio;
    }

    public void setImcMedio(BigDecimal imcMedio) {
        this.imcMedio = imcMedio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioImcMedioFaixaIdadeDTO)) {
            return false;
        }

        return id != null && id.equals(((RelatorioImcMedioFaixaIdadeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioImcMedioFaixaIdadeDTO{" +
            "id=" + getId() +
            ", faixaEtaria='" + getFaixaEtaria() + "'" +
            ", imcMedio=" + getImcMedio() +
            "}";
    }
}
