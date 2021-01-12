package com.wktech.bancosangue.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.wktech.bancosangue.domain.RecepcaoTipoSanguineo} entity.
 */
public class RecepcaoTipoSanguineoDTO implements Serializable {
    
    private Long id;

    private String podeReceberDe;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPodeReceberDe() {
        return podeReceberDe;
    }

    public void setPodeReceberDe(String podeReceberDe) {
        this.podeReceberDe = podeReceberDe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecepcaoTipoSanguineoDTO)) {
            return false;
        }

        return id != null && id.equals(((RecepcaoTipoSanguineoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecepcaoTipoSanguineoDTO{" +
            "id=" + getId() +
            ", podeReceberDe='" + getPodeReceberDe() + "'" +
            "}";
    }
}
