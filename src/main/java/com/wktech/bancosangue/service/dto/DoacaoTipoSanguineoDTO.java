package com.wktech.bancosangue.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.wktech.bancosangue.domain.DoacaoTipoSanguineo} entity.
 */
public class DoacaoTipoSanguineoDTO implements Serializable {
    
    private Long id;

    private String podeDoarPara;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPodeDoarPara() {
        return podeDoarPara;
    }

    public void setPodeDoarPara(String podeDoarPara) {
        this.podeDoarPara = podeDoarPara;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoacaoTipoSanguineoDTO)) {
            return false;
        }

        return id != null && id.equals(((DoacaoTipoSanguineoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DoacaoTipoSanguineoDTO{" +
            "id=" + getId() +
            ", podeDoarPara='" + getPodeDoarPara() + "'" +
            "}";
    }
}
