package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.*; // for static metamodels
import com.wktech.bancosangue.domain.RelatorioQtdeDoadoresParaCadaTipoReceptor;
import com.wktech.bancosangue.repository.RelatorioQtdeDoadoresParaCadaTipoReceptorRepository;
import com.wktech.bancosangue.service.dto.RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria;
import com.wktech.bancosangue.service.dto.RelatorioQtdeDoadoresParaCadaTipoReceptorDTO;
import com.wktech.bancosangue.service.mapper.RelatorioQtdeDoadoresParaCadaTipoReceptorMapper;
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
 * Service for executing complex queries for {@link RelatorioQtdeDoadoresParaCadaTipoReceptor} entities in the database.
 * The main input is a {@link RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RelatorioQtdeDoadoresParaCadaTipoReceptorDTO} or a {@link Page} of {@link RelatorioQtdeDoadoresParaCadaTipoReceptorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RelatorioQtdeDoadoresParaCadaTipoReceptorQueryService extends QueryService<RelatorioQtdeDoadoresParaCadaTipoReceptor> {
    private final Logger log = LoggerFactory.getLogger(RelatorioQtdeDoadoresParaCadaTipoReceptorQueryService.class);

    private final RelatorioQtdeDoadoresParaCadaTipoReceptorRepository relatorioQtdeDoadoresParaCadaTipoReceptorRepository;

    private final RelatorioQtdeDoadoresParaCadaTipoReceptorMapper relatorioQtdeDoadoresParaCadaTipoReceptorMapper;

    public RelatorioQtdeDoadoresParaCadaTipoReceptorQueryService(
        RelatorioQtdeDoadoresParaCadaTipoReceptorRepository relatorioQtdeDoadoresParaCadaTipoReceptorRepository,
        RelatorioQtdeDoadoresParaCadaTipoReceptorMapper relatorioQtdeDoadoresParaCadaTipoReceptorMapper
    ) {
        this.relatorioQtdeDoadoresParaCadaTipoReceptorRepository = relatorioQtdeDoadoresParaCadaTipoReceptorRepository;
        this.relatorioQtdeDoadoresParaCadaTipoReceptorMapper = relatorioQtdeDoadoresParaCadaTipoReceptorMapper;
    }

    /**
     * Return a {@link List} of {@link RelatorioQtdeDoadoresParaCadaTipoReceptorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> findByCriteria(RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RelatorioQtdeDoadoresParaCadaTipoReceptor> specification = createSpecification(criteria);
        return relatorioQtdeDoadoresParaCadaTipoReceptorMapper.toDto(
            relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findAll(specification)
        );
    }

    /**
     * Return a {@link Page} of {@link RelatorioQtdeDoadoresParaCadaTipoReceptorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> findByCriteria(
        RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria criteria,
        Pageable page
    ) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RelatorioQtdeDoadoresParaCadaTipoReceptor> specification = createSpecification(criteria);
        return relatorioQtdeDoadoresParaCadaTipoReceptorRepository
            .findAll(specification, page)
            .map(relatorioQtdeDoadoresParaCadaTipoReceptorMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RelatorioQtdeDoadoresParaCadaTipoReceptor> specification = createSpecification(criteria);
        return relatorioQtdeDoadoresParaCadaTipoReceptorRepository.count(specification);
    }

    /**
     * Function to convert {@link RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RelatorioQtdeDoadoresParaCadaTipoReceptor> createSpecification(
        RelatorioQtdeDoadoresParaCadaTipoReceptorCriteria criteria
    ) {
        Specification<RelatorioQtdeDoadoresParaCadaTipoReceptor> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RelatorioQtdeDoadoresParaCadaTipoReceptor_.id));
            }
            if (criteria.getSangue() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getSangue(), RelatorioQtdeDoadoresParaCadaTipoReceptor_.sangue));
            }
            if (criteria.getTotalDoador() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getTotalDoador(), RelatorioQtdeDoadoresParaCadaTipoReceptor_.totalDoador)
                    );
            }
        }
        return specification;
    }
}
