package com.wktech.bancosangue.service.dto;

import io.github.jhipster.service.Criteria;
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
 * Criteria class for the {@link com.wktech.bancosangue.domain.RelatorioQtdeDoadoresParaCadaTipoReceptor} entity. This class is used
 * in {@link com.wktech.bancosangue.web.rest.RelatorioQtdeDoadoresParaCadaTipoReceptorResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /relatorio-qtde-doadores-para-cada-tipo-receptors?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter sangue;

    private IntegerFilter totalDoador;

    public RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria() {}

    public RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria(RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sangue = other.sangue == null ? null : other.sangue.copy();
        this.totalDoador = other.totalDoador == null ? null : other.totalDoador.copy();
    }

    @Override
    public RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria copy() {
        return new RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSangue() {
        return sangue;
    }

    public void setSangue(StringFilter sangue) {
        this.sangue = sangue;
    }

    public IntegerFilter getTotalDoador() {
        return totalDoador;
    }

    public void setTotalDoador(IntegerFilter totalDoador) {
        this.totalDoador = totalDoador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria that = (RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(sangue, that.sangue) && Objects.equals(totalDoador, that.totalDoador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sangue, totalDoador);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sangue != null ? "sangue=" + sangue + ", " : "") +
                (totalDoador != null ? "totalDoador=" + totalDoador + ", " : "") +
            "}";
    }
}
