package com.wktech.bancosangue.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.wktech.bancosangue.domain.RelatorioMediaIdadeTipoSangue} entity. This class is used
 * in {@link com.wktech.bancosangue.web.rest.RelatorioMediaIdadeTipoSangueResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /relatorio-media-idade-tipo-sangues?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RelatorioMediaIdadeTipoSangueCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter tipoSangue;

    private BigDecimalFilter mediaTipo;

    public RelatorioMediaIdadeTipoSangueCriteria() {}

    public RelatorioMediaIdadeTipoSangueCriteria(RelatorioMediaIdadeTipoSangueCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.tipoSangue = other.tipoSangue == null ? null : other.tipoSangue.copy();
        this.mediaTipo = other.mediaTipo == null ? null : other.mediaTipo.copy();
    }

    @Override
    public RelatorioMediaIdadeTipoSangueCriteria copy() {
        return new RelatorioMediaIdadeTipoSangueCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTipoSangue() {
        return tipoSangue;
    }

    public void setTipoSangue(StringFilter tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    public BigDecimalFilter getMediaTipo() {
        return mediaTipo;
    }

    public void setMediaTipo(BigDecimalFilter mediaTipo) {
        this.mediaTipo = mediaTipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RelatorioMediaIdadeTipoSangueCriteria that = (RelatorioMediaIdadeTipoSangueCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(tipoSangue, that.tipoSangue) && Objects.equals(mediaTipo, that.mediaTipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoSangue, mediaTipo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioMediaIdadeTipoSangueCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tipoSangue != null ? "tipoSangue=" + tipoSangue + ", " : "") +
                (mediaTipo != null ? "mediaTipo=" + mediaTipo + ", " : "") +
            "}";
    }
}
