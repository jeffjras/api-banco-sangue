package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.*; // for static metamodels
import com.wktech.bancosangue.domain.RelatorioQtdCandPorEstado;
import com.wktech.bancosangue.repository.RelatorioQtdCandPorEstadoRepository;
import com.wktech.bancosangue.service.dto.RelatorioQtdCandPorEstadoCriteria;
import com.wktech.bancosangue.service.dto.RelatorioQtdCandPorEstadoDTO;
import com.wktech.bancosangue.service.mapper.RelatorioQtdCandPorEstadoMapper;
import io.github.jhipster.service.QueryService;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link RelatorioQtdCandPorEstado} entities in the database.
 * The main input is a {@link RelatorioQtdCandPorEstadoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RelatorioQtdCandPorEstadoDTO} or a {@link Page} of {@link RelatorioQtdCandPorEstadoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RelatorioQtdCandPorEstadoQueryService extends QueryService<RelatorioQtdCandPorEstado> {
    private final Logger log = LoggerFactory.getLogger(RelatorioQtdCandPorEstadoQueryService.class);

    private final RelatorioQtdCandPorEstadoRepository relatorioQtdCandPorEstadoRepository;

    private final RelatorioQtdCandPorEstadoMapper relatorioQtdCandPorEstadoMapper;

    public RelatorioQtdCandPorEstadoQueryService(
        RelatorioQtdCandPorEstadoRepository relatorioQtdCandPorEstadoRepository,
        RelatorioQtdCandPorEstadoMapper relatorioQtdCandPorEstadoMapper
    ) {
        this.relatorioQtdCandPorEstadoRepository = relatorioQtdCandPorEstadoRepository;
        this.relatorioQtdCandPorEstadoMapper = relatorioQtdCandPorEstadoMapper;
    }

    /**
     * Return a {@link List} of {@link RelatorioQtdCandPorEstadoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RelatorioQtdCandPorEstadoDTO> findByCriteria(RelatorioQtdCandPorEstadoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RelatorioQtdCandPorEstado> specification = createSpecification(criteria);
        return relatorioQtdCandPorEstadoMapper.toDto(relatorioQtdCandPorEstadoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RelatorioQtdCandPorEstadoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RelatorioQtdCandPorEstadoDTO> findByCriteria(RelatorioQtdCandPorEstadoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RelatorioQtdCandPorEstado> specification = createSpecification(criteria);
        return relatorioQtdCandPorEstadoRepository.findAll(specification, page).map(relatorioQtdCandPorEstadoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RelatorioQtdCandPorEstadoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RelatorioQtdCandPorEstado> specification = createSpecification(criteria);
        return relatorioQtdCandPorEstadoRepository.count(specification);
    }

    /**
     * Function to convert {@link RelatorioQtdCandPorEstadoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RelatorioQtdCandPorEstado> createSpecification(RelatorioQtdCandPorEstadoCriteria criteria) {
        Specification<RelatorioQtdCandPorEstado> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RelatorioQtdCandPorEstado_.id));
            }
            if (criteria.getQtde() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQtde(), RelatorioQtdCandPorEstado_.qtde));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEstado(), RelatorioQtdCandPorEstado_.estado));
            }
        }
        return specification;
    }
}
