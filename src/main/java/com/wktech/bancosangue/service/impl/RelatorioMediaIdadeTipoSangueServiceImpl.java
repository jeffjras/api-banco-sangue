package com.wktech.bancosangue.service.impl;

import com.wktech.bancosangue.domain.RelatorioMediaIdadeTipoSangue;
import com.wktech.bancosangue.repository.RelatorioMediaIdadeTipoSangueRepository;
import com.wktech.bancosangue.service.RelatorioMediaIdadeTipoSangueService;
import com.wktech.bancosangue.service.dto.RelatorioMediaIdadeTipoSangueDTO;
import com.wktech.bancosangue.service.mapper.RelatorioMediaIdadeTipoSangueMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RelatorioMediaIdadeTipoSangue}.
 */
@Service
@Transactional
public class RelatorioMediaIdadeTipoSangueServiceImpl implements RelatorioMediaIdadeTipoSangueService {
    private final Logger log = LoggerFactory.getLogger(RelatorioMediaIdadeTipoSangueServiceImpl.class);

    private final RelatorioMediaIdadeTipoSangueRepository relatorioMediaIdadeTipoSangueRepository;

    private final RelatorioMediaIdadeTipoSangueMapper relatorioMediaIdadeTipoSangueMapper;

    public RelatorioMediaIdadeTipoSangueServiceImpl(
        RelatorioMediaIdadeTipoSangueRepository relatorioMediaIdadeTipoSangueRepository,
        RelatorioMediaIdadeTipoSangueMapper relatorioMediaIdadeTipoSangueMapper
    ) {
        this.relatorioMediaIdadeTipoSangueRepository = relatorioMediaIdadeTipoSangueRepository;
        this.relatorioMediaIdadeTipoSangueMapper = relatorioMediaIdadeTipoSangueMapper;
    }

    @Override
    public RelatorioMediaIdadeTipoSangueDTO save(RelatorioMediaIdadeTipoSangueDTO relatorioMediaIdadeTipoSangueDTO) {
        log.debug("Request to save RelatorioMediaIdadeTipoSangue : {}", relatorioMediaIdadeTipoSangueDTO);
        RelatorioMediaIdadeTipoSangue relatorioMediaIdadeTipoSangue = relatorioMediaIdadeTipoSangueMapper.toEntity(
            relatorioMediaIdadeTipoSangueDTO
        );
        relatorioMediaIdadeTipoSangue = relatorioMediaIdadeTipoSangueRepository.save(relatorioMediaIdadeTipoSangue);
        return relatorioMediaIdadeTipoSangueMapper.toDto(relatorioMediaIdadeTipoSangue);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelatorioMediaIdadeTipoSangueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelatorioMediaIdadeTipoSangues");
        return relatorioMediaIdadeTipoSangueRepository.findAll(pageable).map(relatorioMediaIdadeTipoSangueMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelatorioMediaIdadeTipoSangueDTO> findOne(Long id) {
        log.debug("Request to get RelatorioMediaIdadeTipoSangue : {}", id);
        return relatorioMediaIdadeTipoSangueRepository.findById(id).map(relatorioMediaIdadeTipoSangueMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelatorioMediaIdadeTipoSangue : {}", id);
        relatorioMediaIdadeTipoSangueRepository.deleteById(id);
    }
}
