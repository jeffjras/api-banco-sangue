package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.*; // for static metamodels
import com.wktech.bancosangue.domain.RelatorioPercObesosMulheres;
import com.wktech.bancosangue.repository.RelatorioPercObesosMulheresRepository;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosMulheresCriteria;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosMulheresDTO;
import com.wktech.bancosangue.service.mapper.RelatorioPercObesosMulheresMapper;
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
 * Service for executing complex queries for {@link RelatorioPercObesosMulheres} entities in the database.
 * The main input is a {@link RelatorioPercObesosMulheresCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RelatorioPercObesosMulheresDTO} or a {@link Page} of {@link RelatorioPercObesosMulheresDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RelatorioPercObesosMulheresQueryService extends QueryService<RelatorioPercObesosMulheres> {
    private final Logger log = LoggerFactory.getLogger(RelatorioPercObesosMulheresQueryService.class);

    private final RelatorioPercObesosMulheresRepository relatorioPercObesosMulheresRepository;

    private final RelatorioPercObesosMulheresMapper relatorioPercObesosMulheresMapper;

    public RelatorioPercObesosMulheresQueryService(
        RelatorioPercObesosMulheresRepository relatorioPercObesosMulheresRepository,
        RelatorioPercObesosMulheresMapper relatorioPercObesosMulheresMapper
    ) {
        this.relatorioPercObesosMulheresRepository = relatorioPercObesosMulheresRepository;
        this.relatorioPercObesosMulheresMapper = relatorioPercObesosMulheresMapper;
    }

    /**
     * Return a {@link List} of {@link RelatorioPercObesosMulheresDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RelatorioPercObesosMulheresDTO> findByCriteria(RelatorioPercObesosMulheresCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RelatorioPercObesosMulheres> specification = createSpecification(criteria);
        return relatorioPercObesosMulheresMapper.toDto(relatorioPercObesosMulheresRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RelatorioPercObesosMulheresDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RelatorioPercObesosMulheresDTO> findByCriteria(RelatorioPercObesosMulheresCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RelatorioPercObesosMulheres> specification = createSpecification(criteria);
        return relatorioPercObesosMulheresRepository.findAll(specification, page).map(relatorioPercObesosMulheresMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RelatorioPercObesosMulheresCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RelatorioPercObesosMulheres> specification = createSpecification(criteria);
        return relatorioPercObesosMulheresRepository.count(specification);
    }

    /**
     * Function to convert {@link RelatorioPercObesosMulheresCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RelatorioPercObesosMulheres> createSpecification(RelatorioPercObesosMulheresCriteria criteria) {
        Specification<RelatorioPercObesosMulheres> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RelatorioPercObesosMulheres_.id));
            }
            if (criteria.getPercentual() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getPercentual(), RelatorioPercObesosMulheres_.percentual));
            }
        }
        return specification;
    }
}
