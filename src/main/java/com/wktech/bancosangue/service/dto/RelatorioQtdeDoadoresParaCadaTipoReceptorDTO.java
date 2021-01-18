package com.wktech.bancosangue.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.wktech.bancosangue.domain.RelatorioQtdeDoadoresParaCadaTipoReceptor} entity.
 */
public class RelatorioQtdeDoadoresParaCadaTipoReceptorDTO implements Serializable {
    private Long id;

    private String sangue;

    private Integer totalDoador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSangue() {
        return sangue;
    }

    public void setSangue(String sangue) {
        this.sangue = sangue;
    }

    public Integer getTotalDoador() {
        return totalDoador;
    }

    public void setTotalDoador(Integer totalDoador) {
        this.totalDoador = totalDoador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioQtdeDoadoresParaCadaTipoReceptorDTO)) {
            return false;
        }

        return id != null && id.equals(((RelatorioQtdeDoadoresParaCadaTipoReceptorDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioQtdeDoadoresParaCadaTipoReceptorDTO{" +
            "id=" + getId() +
            ", sangue='" + getSangue() + "'" +
            ", totalDoador=" + getTotalDoador() +
            "}";
    }
}
