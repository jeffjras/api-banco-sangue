package com.wktech.bancosangue.service.impl;

import com.wktech.bancosangue.domain.RelatorioImcMedioFaixaIdade;
import com.wktech.bancosangue.repository.RelatorioImcMedioFaixaIdadeRepository;
import com.wktech.bancosangue.service.RelatorioImcMedioFaixaIdadeService;
import com.wktech.bancosangue.service.dto.RelatorioImcMedioFaixaIdadeDTO;
import com.wktech.bancosangue.service.mapper.RelatorioImcMedioFaixaIdadeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RelatorioImcMedioFaixaIdade}.
 */
@Service
@Transactional
public class RelatorioImcMedioFaixaIdadeServiceImpl implements RelatorioImcMedioFaixaIdadeService {
    private final Logger log = LoggerFactory.getLogger(RelatorioImcMedioFaixaIdadeServiceImpl.class);

    private final RelatorioImcMedioFaixaIdadeRepository relatorioImcMedioFaixaIdadeRepository;

    private final RelatorioImcMedioFaixaIdadeMapper relatorioImcMedioFaixaIdadeMapper;

    public RelatorioImcMedioFaixaIdadeServiceImpl(
        RelatorioImcMedioFaixaIdadeRepository relatorioImcMedioFaixaIdadeRepository,
        RelatorioImcMedioFaixaIdadeMapper relatorioImcMedioFaixaIdadeMapper
    ) {
        this.relatorioImcMedioFaixaIdadeRepository = relatorioImcMedioFaixaIdadeRepository;
        this.relatorioImcMedioFaixaIdadeMapper = relatorioImcMedioFaixaIdadeMapper;
    }

    @Override
    public RelatorioImcMedioFaixaIdadeDTO save(RelatorioImcMedioFaixaIdadeDTO relatorioImcMedioFaixaIdadeDTO) {
        log.debug("Request to save RelatorioImcMedioFaixaIdade : {}", relatorioImcMedioFaixaIdadeDTO);
        RelatorioImcMedioFaixaIdade relatorioImcMedioFaixaIdade = relatorioImcMedioFaixaIdadeMapper.toEntity(
            relatorioImcMedioFaixaIdadeDTO
        );
        relatorioImcMedioFaixaIdade = relatorioImcMedioFaixaIdadeRepository.save(relatorioImcMedioFaixaIdade);
        return relatorioImcMedioFaixaIdadeMapper.toDto(relatorioImcMedioFaixaIdade);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelatorioImcMedioFaixaIdadeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelatorioImcMedioFaixaIdades");
        return relatorioImcMedioFaixaIdadeRepository.findAll(pageable).map(relatorioImcMedioFaixaIdadeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelatorioImcMedioFaixaIdadeDTO> findOne(Long id) {
        log.debug("Request to get RelatorioImcMedioFaixaIdade : {}", id);
        return relatorioImcMedioFaixaIdadeRepository.findById(id).map(relatorioImcMedioFaixaIdadeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelatorioImcMedioFaixaIdade : {}", id);
        relatorioImcMedioFaixaIdadeRepository.deleteById(id);
    }
}
