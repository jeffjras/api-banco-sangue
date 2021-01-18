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
 * Criteria class for the {@link com.wktech.bancosangue.domain.RelatorioQtdCandPorEstado} entity. This class is used
 * in {@link com.wktech.bancosangue.web.rest.RelatorioQtdCandPorEstadoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /relatorio-qtd-cand-por-estados?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RelatorioQtdCandPorEstadoCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter qtde;

    private StringFilter estado;

    public RelatorioQtdCandPorEstadoCriteria() {}

    public RelatorioQtdCandPorEstadoCriteria(RelatorioQtdCandPorEstadoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.qtde = other.qtde == null ? null : other.qtde.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
    }

    @Override
    public RelatorioQtdCandPorEstadoCriteria copy() {
        return new RelatorioQtdCandPorEstadoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getQtde() {
        return qtde;
    }

    public void setQtde(IntegerFilter qtde) {
        this.qtde = qtde;
    }

    public StringFilter getEstado() {
        return estado;
    }

    public void setEstado(StringFilter estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RelatorioQtdCandPorEstadoCriteria that = (RelatorioQtdCandPorEstadoCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(qtde, that.qtde) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qtde, estado);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelatorioQtdCandPorEstadoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (qtde != null ? "qtde=" + qtde + ", " : "") +
                (estado != null ? "estado=" + estado + ", " : "") +
            "}";
    }
}
