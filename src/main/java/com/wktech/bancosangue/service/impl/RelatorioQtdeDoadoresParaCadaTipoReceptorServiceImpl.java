package com.wktech.bancosangue.service.impl;

import com.wktech.bancosangue.domain.RelatorioQtdeDoadoresParaCadaTipoReceptor;
import com.wktech.bancosangue.repository.RelatorioQtdeDoadoresParaCadaTipoReceptorRepository;
import com.wktech.bancosangue.service.RelatorioQtdeDoadoresParaCadaTipoReceptorService;
import com.wktech.bancosangue.service.dto.RelatorioQtdeDoadoresParaCadaTipoReceptorDTO;
import com.wktech.bancosangue.service.mapper.RelatorioQtdeDoadoresParaCadaTipoReceptorMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RelatorioQtdeDoadoresParaCadaTipoReceptor}.
 */
@Service
@Transactional
public class RelatorioQtdeDoadoresParaCadaTipoReceptorServiceImpl implements RelatorioQtdeDoadoresParaCadaTipoReceptorService {
    private final Logger log = LoggerFactory.getLogger(RelatorioQtdeDoadoresParaCadaTipoReceptorServiceImpl.class);

    private final RelatorioQtdeDoadoresParaCadaTipoReceptorRepository relatorioQtdeDoadoresParaCadaTipoReceptorRepository;

    private final RelatorioQtdeDoadoresParaCadaTipoReceptorMapper relatorioQtdeDoadoresParaCadaTipoReceptorMapper;

    public RelatorioQtdeDoadoresParaCadaTipoReceptorServiceImpl(
        RelatorioQtdeDoadoresParaCadaTipoReceptorRepository relatorioQtdeDoadoresParaCadaTipoReceptorRepository,
        RelatorioQtdeDoadoresParaCadaTipoReceptorMapper relatorioQtdeDoadoresParaCadaTipoReceptorMapper
    ) {
        this.relatorioQtdeDoadoresParaCadaTipoReceptorRepository = relatorioQtdeDoadoresParaCadaTipoReceptorRepository;
        this.relatorioQtdeDoadoresParaCadaTipoReceptorMapper = relatorioQtdeDoadoresParaCadaTipoReceptorMapper;
    }

    @Override
    public RelatorioQtdeDoadoresParaCadaTipoReceptorDTO save(
        RelatorioQtdeDoadoresParaCadaTipoReceptorDTO relatorioQtdeDoadoresParaCadaTipoReceptorDTO
    ) {
        log.debug("Request to save RelatorioQtdeDoadoresParaCadaTipoReceptor : {}", relatorioQtdeDoadoresParaCadaTipoReceptorDTO);
        RelatorioQtdeDoadoresParaCadaTipoReceptor relatorioQtdeDoadoresParaCadaTipoReceptor = relatorioQtdeDoadoresParaCadaTipoReceptorMapper.toEntity(
            relatorioQtdeDoadoresParaCadaTipoReceptorDTO
        );
        relatorioQtdeDoadoresParaCadaTipoReceptor =
            relatorioQtdeDoadoresParaCadaTipoReceptorRepository.save(relatorioQtdeDoadoresParaCadaTipoReceptor);
        return relatorioQtdeDoadoresParaCadaTipoReceptorMapper.toDto(relatorioQtdeDoadoresParaCadaTipoReceptor);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelatorioQtdeDoadoresParaCadaTipoReceptors");
        return relatorioQtdeDoadoresParaCadaTipoReceptorRepository
            .findAll(pageable)
            .map(relatorioQtdeDoadoresParaCadaTipoReceptorMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelatorioQtdeDoadoresParaCadaTipoReceptorDTO> findOne(Long id) {
        log.debug("Request to get RelatorioQtdeDoadoresParaCadaTipoReceptor : {}", id);
        return relatorioQtdeDoadoresParaCadaTipoReceptorRepository.findById(id).map(relatorioQtdeDoadoresParaCadaTipoReceptorMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelatorioQtdeDoadoresParaCadaTipoReceptor : {}", id);
        relatorioQtdeDoadoresParaCadaTipoReceptorRepository.deleteById(id);
    }
}
