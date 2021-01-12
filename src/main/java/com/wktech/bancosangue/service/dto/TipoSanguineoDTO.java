package com.wktech.bancosangue.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.wktech.bancosangue.domain.TipoSanguineo} entity.
 */
public class TipoSanguineoDTO implements Serializable {
    
    private Long id;

    private String descricao;


    private Long doacaoTipoSanguineoId;

    private Long recepcaoTipoSanguineoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getDoacaoTipoSanguineoId() {
        return doacaoTipoSanguineoId;
    }

    public void setDoacaoTipoSanguineoId(Long doacaoTipoSanguineoId) {
        this.doacaoTipoSanguineoId = doacaoTipoSanguineoId;
    }

    public Long getRecepcaoTipoSanguineoId() {
        return recepcaoTipoSanguineoId;
    }

    public void setRecepcaoTipoSanguineoId(Long recepcaoTipoSanguineoId) {
        this.recepcaoTipoSanguineoId = recepcaoTipoSanguineoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoSanguineoDTO)) {
            return false;
        }

        return id != null && id.equals(((TipoSanguineoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoSanguineoDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", doacaoTipoSanguineoId=" + getDoacaoTipoSanguineoId() +
            ", recepcaoTipoSanguineoId=" + getRecepcaoTipoSanguineoId() +
            "}";
    }
}
