package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.*; // for static metamodels
import com.wktech.bancosangue.domain.RelatorioPercObesosHomens;
import com.wktech.bancosangue.repository.RelatorioPercObesosHomensRepository;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosHomensCriteria;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosHomensDTO;
import com.wktech.bancosangue.service.mapper.RelatorioPercObesosHomensMapper;
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
 * Service for executing complex queries for {@link RelatorioPercObesosHomens} entities in the database.
 * The main input is a {@link RelatorioPercObesosHomensCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RelatorioPercObesosHomensDTO} or a {@link Page} of {@link RelatorioPercObesosHomensDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RelatorioPercObesosHomensQueryService extends QueryService<RelatorioPercObesosHomens> {
    private final Logger log = LoggerFactory.getLogger(RelatorioPercObesosHomensQueryService.class);

    private final RelatorioPercObesosHomensRepository relatorioPercObesosHomensRepository;

    private final RelatorioPercObesosHomensMapper relatorioPercObesosHomensMapper;

    public RelatorioPercObesosHomensQueryService(
        RelatorioPercObesosHomensRepository relatorioPercObesosHomensRepository,
        RelatorioPercObesosHomensMapper relatorioPercObesosHomensMapper
    ) {
        this.relatorioPercObesosHomensRepository = relatorioPercObesosHomensRepository;
        this.relatorioPercObesosHomensMapper = relatorioPercObesosHomensMapper;
    }

    /**
     * Return a {@link List} of {@link RelatorioPercObesosHomensDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RelatorioPercObesosHomensDTO> findByCriteria(RelatorioPercObesosHomensCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RelatorioPercObesosHomens> specification = createSpecification(criteria);
        return relatorioPercObesosHomensMapper.toDto(relatorioPercObesosHomensRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RelatorioPercObesosHomensDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RelatorioPercObesosHomensDTO> findByCriteria(RelatorioPercObesosHomensCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RelatorioPercObesosHomens> specification = createSpecification(criteria);
        return relatorioPercObesosHomensRepository.findAll(specification, page).map(relatorioPercObesosHomensMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RelatorioPercObesosHomensCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RelatorioPercObesosHomens> specification = createSpecification(criteria);
        return relatorioPercObesosHomensRepository.count(specification);
    }

    /**
     * Function to convert {@link RelatorioPercObesosHomensCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RelatorioPercObesosHomens> createSpecification(RelatorioPercObesosHomensCriteria criteria) {
        Specification<RelatorioPercObesosHomens> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RelatorioPercObesosHomens_.id));
            }
            if (criteria.getPercentual() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPercentual(), RelatorioPercObesosHomens_.percentual));
            }
        }
        return specification;
    }
}
