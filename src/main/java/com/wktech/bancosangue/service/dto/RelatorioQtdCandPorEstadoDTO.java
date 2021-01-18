package com.wktech.bancosangue.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.wktech.bancosangue.domain.RelatorioQtdCandPorEstado} entity.
 */
public class RelatorioQtdCandPorEstadoDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3791212050206425947L;

    private Long id;

    private Integer qtde;

    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelatorioQtdCandPorEstadoDTO)) {
            return false;
        }

        return id != null && id.equals(((RelatorioQtdCandPorEstadoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioQtdCandPorEstadoDTO{" +
            "id=" + getId() +
            ", qtde=" + getQtde() +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
