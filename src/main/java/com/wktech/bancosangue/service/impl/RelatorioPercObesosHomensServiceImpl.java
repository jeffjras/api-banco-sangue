package com.wktech.bancosangue.service.impl;

import com.wktech.bancosangue.domain.RelatorioPercObesosHomens;
import com.wktech.bancosangue.repository.RelatorioPercObesosHomensRepository;
import com.wktech.bancosangue.service.RelatorioPercObesosHomensService;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosHomensDTO;
import com.wktech.bancosangue.service.mapper.RelatorioPercObesosHomensMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RelatorioPercObesosHomens}.
 */
@Service
@Transactional
public class RelatorioPercObesosHomensServiceImpl implements RelatorioPercObesosHomensService {
    private final Logger log = LoggerFactory.getLogger(RelatorioPercObesosHomensServiceImpl.class);

    private final RelatorioPercObesosHomensRepository relatorioPercObesosHomensRepository;

    private final RelatorioPercObesosHomensMapper relatorioPercObesosHomensMapper;

    public RelatorioPercObesosHomensServiceImpl(
        RelatorioPercObesosHomensRepository relatorioPercObesosHomensRepository,
        RelatorioPercObesosHomensMapper relatorioPercObesosHomensMapper
    ) {
        this.relatorioPercObesosHomensRepository = relatorioPercObesosHomensRepository;
        this.relatorioPercObesosHomensMapper = relatorioPercObesosHomensMapper;
    }

    @Override
    public RelatorioPercObesosHomensDTO save(RelatorioPercObesosHomensDTO relatorioPercObesosHomensDTO) {
        log.debug("Request to save RelatorioPercObesosHomens : {}", relatorioPercObesosHomensDTO);
        RelatorioPercObesosHomens relatorioPercObesosHomens = relatorioPercObesosHomensMapper.toEntity(relatorioPercObesosHomensDTO);
        relatorioPercObesosHomens = relatorioPercObesosHomensRepository.save(relatorioPercObesosHomens);
        return relatorioPercObesosHomensMapper.toDto(relatorioPercObesosHomens);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelatorioPercObesosHomensDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelatorioPercObesosHomens");
        return relatorioPercObesosHomensRepository.findAll(pageable).map(relatorioPercObesosHomensMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelatorioPercObesosHomensDTO> findOne(Long id) {
        log.debug("Request to get RelatorioPercObesosHomens : {}", id);
        return relatorioPercObesosHomensRepository.findById(id).map(relatorioPercObesosHomensMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelatorioPercObesosHomens : {}", id);
        relatorioPercObesosHomensRepository.deleteById(id);
    }
}
