package com.wktech.bancosangue.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.wktech.bancosangue.domain.RelatorioMediaIdadeTipoSangue} entity.
 */
public class RelatorioMediaIdadeTipoSangueDTO implements Serializable {
    private Long id;

    private String tipoSangue;

    private BigDecimal mediaTipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    public BigDecimal getMediaTipo() {
        return mediaTipo;
    }

    public void setMediaTipo(BigDecimal mediaTipo) {
        this.mediaTipo = mediaTipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioMediaIdadeTipoSangueDTO)) {
            return false;
        }

        return id != null && id.equals(((RelatorioMediaIdadeTipoSangueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioMediaIdadeTipoSangueDTO{" +
            "id=" + getId() +
            ", tipoSangue='" + getTipoSangue() + "'" +
            ", mediaTipo=" + getMediaTipo() +
            "}";
    }
}
