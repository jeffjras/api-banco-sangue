package com.wktech.bancosangue.service.impl;

import com.wktech.bancosangue.domain.RelatorioPercObesosMulheres;
import com.wktech.bancosangue.repository.RelatorioPercObesosMulheresRepository;
import com.wktech.bancosangue.service.RelatorioPercObesosMulheresService;
import com.wktech.bancosangue.service.dto.RelatorioPercObesosMulheresDTO;
import com.wktech.bancosangue.service.mapper.RelatorioPercObesosMulheresMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RelatorioPercObesosMulheres}.
 */
@Service
@Transactional
public class RelatorioPercObesosMulheresServiceImpl implements RelatorioPercObesosMulheresService {
    private final Logger log = LoggerFactory.getLogger(RelatorioPercObesosMulheresServiceImpl.class);

    private final RelatorioPercObesosMulheresRepository relatorioPercObesosMulheresRepository;

    private final RelatorioPercObesosMulheresMapper relatorioPercObesosMulheresMapper;

    public RelatorioPercObesosMulheresServiceImpl(
        RelatorioPercObesosMulheresRepository relatorioPercObesosMulheresRepository,
        RelatorioPercObesosMulheresMapper relatorioPercObesosMulheresMapper
    ) {
        this.relatorioPercObesosMulheresRepository = relatorioPercObesosMulheresRepository;
        this.relatorioPercObesosMulheresMapper = relatorioPercObesosMulheresMapper;
    }

    @Override
    public RelatorioPercObesosMulheresDTO save(RelatorioPercObesosMulheresDTO relatorioPercObesosMulheresDTO) {
        log.debug("Request to save RelatorioPercObesosMulheres : {}", relatorioPercObesosMulheresDTO);
        RelatorioPercObesosMulheres relatorioPercObesosMulheres = relatorioPercObesosMulheresMapper.toEntity(
            relatorioPercObesosMulheresDTO
        );
        relatorioPercObesosMulheres = relatorioPercObesosMulheresRepository.save(relatorioPercObesosMulheres);
        return relatorioPercObesosMulheresMapper.toDto(relatorioPercObesosMulheres);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelatorioPercObesosMulheresDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelatorioPercObesosMulheres");
        return relatorioPercObesosMulheresRepository.findAll(pageable).map(relatorioPercObesosMulheresMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelatorioPercObesosMulheresDTO> findOne(Long id) {
        log.debug("Request to get RelatorioPercObesosMulheres : {}", id);
        return relatorioPercObesosMulheresRepository.findById(id).map(relatorioPercObesosMulheresMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelatorioPercObesosMulheres : {}", id);
        relatorioPercObesosMulheresRepository.deleteById(id);
    }
}
