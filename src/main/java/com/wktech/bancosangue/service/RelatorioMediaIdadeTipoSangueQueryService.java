package com.wktech.bancosangue.service;

import com.wktech.bancosangue.domain.*; // for static metamodels
import com.wktech.bancosangue.domain.RelatorioMediaIdadeTipoSangue;
import com.wktech.bancosangue.repository.RelatorioMediaIdadeTipoSangueRepository;
import com.wktech.bancosangue.service.dto.RelatorioMediaIdadeTipoSangueCriteria;
import com.wktech.bancosangue.service.dto.RelatorioMediaIdadeTipoSangueDTO;
import com.wktech.bancosangue.service.mapper.RelatorioMediaIdadeTipoSangueMapper;
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
 * Service for executing complex queries for {@link RelatorioMediaIdadeTipoSangue} entities in the database.
 * The main input is a {@link RelatorioMediaIdadeTipoSangueCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RelatorioMediaIdadeTipoSangueDTO} or a {@link Page} of {@link RelatorioMediaIdadeTipoSangueDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RelatorioMediaIdadeTipoSangueQueryService extends QueryService<RelatorioMediaIdadeTipoSangue> {
    private final Logger log = LoggerFactory.getLogger(RelatorioMediaIdadeTipoSangueQueryService.class);

    private final RelatorioMediaIdadeTipoSangueRepository relatorioMediaIdadeTipoSangueRepository;

    private final RelatorioMediaIdadeTipoSangueMapper relatorioMediaIdadeTipoSangueMapper;

    public RelatorioMediaIdadeTipoSangueQueryService(
        RelatorioMediaIdadeTipoSangueRepository relatorioMediaIdadeTipoSangueRepository,
        RelatorioMediaIdadeTipoSangueMapper relatorioMediaIdadeTipoSangueMapper
    ) {
        this.relatorioMediaIdadeTipoSangueRepository = relatorioMediaIdadeTipoSangueRepository;
        this.relatorioMediaIdadeTipoSangueMapper = relatorioMediaIdadeTipoSangueMapper;
    }

    /**
     * Return a {@link List} of {@link RelatorioMediaIdadeTipoSangueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RelatorioMediaIdadeTipoSangueDTO> findByCriteria(RelatorioMediaIdadeTipoSangueCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RelatorioMediaIdadeTipoSangue> specification = createSpecification(criteria);
        return relatorioMediaIdadeTipoSangueMapper.toDto(relatorioMediaIdadeTipoSangueRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RelatorioMediaIdadeTipoSangueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RelatorioMediaIdadeTipoSangueDTO> findByCriteria(RelatorioMediaIdadeTipoSangueCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RelatorioMediaIdadeTipoSangue> specification = createSpecification(criteria);
        return relatorioMediaIdadeTipoSangueRepository.findAll(specification, page).map(relatorioMediaIdadeTipoSangueMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RelatorioMediaIdadeTipoSangueCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RelatorioMediaIdadeTipoSangue> specification = createSpecification(criteria);
        return relatorioMediaIdadeTipoSangueRepository.count(specification);
    }

    /**
     * Function to convert {@link RelatorioMediaIdadeTipoSangueCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RelatorioMediaIdadeTipoSangue> createSpecification(RelatorioMediaIdadeTipoSangueCriteria criteria) {
        Specification<RelatorioMediaIdadeTipoSangue> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RelatorioMediaIdadeTipoSangue_.id));
            }
            if (criteria.getTipoSangue() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getTipoSangue(), RelatorioMediaIdadeTipoSangue_.tipoSangue));
            }
            if (criteria.getMediaTipo() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getMediaTipo(), RelatorioMediaIdadeTipoSangue_.mediaTipo));
            }
        }
        return specification;
    }
}
