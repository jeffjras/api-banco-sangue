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
 * Criteria class for the {@link com.wktech.bancosangue.domain.RelatorioImcMedioFaixaIdade} entity. This class is used
 * in {@link com.wktech.bancosangue.web.rest.RelatorioImcMedioFaixaIdadeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /relatorio-imc-medio-faixa-idades?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RelatorioImcMedioFaixaIdadeCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter faixaEtaria;

    private BigDecimalFilter imcMedio;

    public RelatorioImcMedioFaixaIdadeCriteria() {}

    public RelatorioImcMedioFaixaIdadeCriteria(RelatorioImcMedioFaixaIdadeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.faixaEtaria = other.faixaEtaria == null ? null : other.faixaEtaria.copy();
        this.imcMedio = other.imcMedio == null ? null : other.imcMedio.copy();
    }

    @Override
    public RelatorioImcMedioFaixaIdadeCriteria copy() {
        return new RelatorioImcMedioFaixaIdadeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(StringFilter faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public BigDecimalFilter getImcMedio() {
        return imcMedio;
    }

    public void setImcMedio(BigDecimalFilter imcMedio) {
        this.imcMedio = imcMedio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RelatorioImcMedioFaixaIdadeCriteria that = (RelatorioImcMedioFaixaIdadeCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(faixaEtaria, that.faixaEtaria) && Objects.equals(imcMedio, that.imcMedio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faixaEtaria, imcMedio);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioImcMedioFaixaIdadeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (faixaEtaria != null ? "faixaEtaria=" + faixaEtaria + ", " : "") +
                (imcMedio != null ? "imcMedio=" + imcMedio + ", " : "") +
            "}";
    }
}
