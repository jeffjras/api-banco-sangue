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
 * Criteria class for the {@link com.wktech.bancosangue.domain.RelatorioPercObesosHomens} entity. This class is used
 * in {@link com.wktech.bancosangue.web.rest.RelatorioPercObesosHomensResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /relatorio-perc-obesos-homens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RelatorioPercObesosHomensCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BigDecimalFilter percentual;

    public RelatorioPercObesosHomensCriteria() {}

    public RelatorioPercObesosHomensCriteria(RelatorioPercObesosHomensCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.percentual = other.percentual == null ? null : other.percentual.copy();
    }

    @Override
    public RelatorioPercObesosHomensCriteria copy() {
        return new RelatorioPercObesosHomensCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public BigDecimalFilter getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimalFilter percentual) {
        this.percentual = percentual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RelatorioPercObesosHomensCriteria that = (RelatorioPercObesosHomensCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(percentual, that.percentual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, percentual);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioPercObesosHomensCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (percentual != null ? "percentual=" + percentual + ", " : "") +
            "}";
    }
}
