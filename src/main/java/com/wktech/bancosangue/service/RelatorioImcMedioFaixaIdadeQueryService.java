package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.*; // for static metamodels
import com.wktech.bancosangue.domain.RelatorioImcMedioFaixaIdade;
import com.wktech.bancosangue.repository.RelatorioImcMedioFaixaIdadeRepository;
import com.wktech.bancosangue.service.dto.RelatorioImcMedioFaixaIdadeCriteria;
import com.wktech.bancosangue.service.dto.RelatorioImcMedioFaixaIdadeDTO;
import com.wktech.bancosangue.service.mapper.RelatorioImcMedioFaixaIdadeMapper;
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
 * Service for executing complex queries for {@link RelatorioImcMedioFaixaIdade} entities in the database.
 * The main input is a {@link RelatorioImcMedioFaixaIdadeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RelatorioImcMedioFaixaIdadeDTO} or a {@link Page} of {@link RelatorioImcMedioFaixaIdadeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RelatorioImcMedioFaixaIdadeQueryService extends QueryService<RelatorioImcMedioFaixaIdade> {
    private final Logger log = LoggerFactory.getLogger(RelatorioImcMedioFaixaIdadeQueryService.class);

    private final RelatorioImcMedioFaixaIdadeRepository relatorioImcMedioFaixaIdadeRepository;

    private final RelatorioImcMedioFaixaIdadeMapper relatorioImcMedioFaixaIdadeMapper;

    public RelatorioImcMedioFaixaIdadeQueryService(
        RelatorioImcMedioFaixaIdadeRepository relatorioImcMedioFaixaIdadeRepository,
        RelatorioImcMedioFaixaIdadeMapper relatorioImcMedioFaixaIdadeMapper
    ) {
        this.relatorioImcMedioFaixaIdadeRepository = relatorioImcMedioFaixaIdadeRepository;
        this.relatorioImcMedioFaixaIdadeMapper = relatorioImcMedioFaixaIdadeMapper;
    }

    /**
     * Return a {@link List} of {@link RelatorioImcMedioFaixaIdadeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RelatorioImcMedioFaixaIdadeDTO> findByCriteria(RelatorioImcMedioFaixaIdadeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RelatorioImcMedioFaixaIdade> specification = createSpecification(criteria);
        return relatorioImcMedioFaixaIdadeMapper.toDto(relatorioImcMedioFaixaIdadeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RelatorioImcMedioFaixaIdadeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RelatorioImcMedioFaixaIdadeDTO> findByCriteria(RelatorioImcMedioFaixaIdadeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RelatorioImcMedioFaixaIdade> specification = createSpecification(criteria);
        return relatorioImcMedioFaixaIdadeRepository.findAll(specification, page).map(relatorioImcMedioFaixaIdadeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RelatorioImcMedioFaixaIdadeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RelatorioImcMedioFaixaIdade> specification = createSpecification(criteria);
        return relatorioImcMedioFaixaIdadeRepository.count(specification);
    }

    /**
     * Function to convert {@link RelatorioImcMedioFaixaIdadeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RelatorioImcMedioFaixaIdade> createSpecification(RelatorioImcMedioFaixaIdadeCriteria criteria) {
        Specification<RelatorioImcMedioFaixaIdade> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RelatorioImcMedioFaixaIdade_.id));
            }
            if (criteria.getFaixaEtaria() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getFaixaEtaria(), RelatorioImcMedioFaixaIdade_.faixaEtaria));
            }
            if (criteria.getImcMedio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getImcMedio(), RelatorioImcMedioFaixaIdade_.imcMedio));
            }
        }
        return specification;
    }
}
