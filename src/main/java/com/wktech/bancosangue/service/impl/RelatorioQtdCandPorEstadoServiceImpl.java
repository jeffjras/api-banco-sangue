package com.wktech.bancosangue.service.impl;

import com.wktech.bancosangue.domain.RelatorioQtdCandPorEstado;
import com.wktech.bancosangue.repository.RelatorioQtdCandPorEstadoRepository;
import com.wktech.bancosangue.service.RelatorioQtdCandPorEstadoService;
import com.wktech.bancosangue.service.dto.RelatorioQtdCandPorEstadoDTO;
import com.wktech.bancosangue.service.mapper.RelatorioQtdCandPorEstadoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RelatorioQtdCandPorEstado}.
 */
@Service
@Transactional
public class RelatorioQtdCandPorEstadoServiceImpl implements RelatorioQtdCandPorEstadoService {
    private final Logger log = LoggerFactory.getLogger(RelatorioQtdCandPorEstadoServiceImpl.class);

    private final RelatorioQtdCandPorEstadoRepository relatorioQtdCandPorEstadoRepository;

    private final RelatorioQtdCandPorEstadoMapper relatorioQtdCandPorEstadoMapper;

    public RelatorioQtdCandPorEstadoServiceImpl(
        RelatorioQtdCandPorEstadoRepository relatorioQtdCandPorEstadoRepository,
        RelatorioQtdCandPorEstadoMapper relatorioQtdCandPorEstadoMapper
    ) {
        this.relatorioQtdCandPorEstadoRepository = relatorioQtdCandPorEstadoRepository;
        this.relatorioQtdCandPorEstadoMapper = relatorioQtdCandPorEstadoMapper;
    }

    @Override
    public RelatorioQtdCandPorEstadoDTO save(RelatorioQtdCandPorEstadoDTO relatorioQtdCandPorEstadoDTO) {
        log.debug("Request to save RelatorioQtdCandPorEstado : {}", relatorioQtdCandPorEstadoDTO);
        RelatorioQtdCandPorEstado relatorioQtdCandPorEstado = relatorioQtdCandPorEstadoMapper.toEntity(relatorioQtdCandPorEstadoDTO);
        relatorioQtdCandPorEstado = relatorioQtdCandPorEstadoRepository.save(relatorioQtdCandPorEstado);
        return relatorioQtdCandPorEstadoMapper.toDto(relatorioQtdCandPorEstado);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelatorioQtdCandPorEstadoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelatorioQtdCandPorEstados");
        return relatorioQtdCandPorEstadoRepository.findAll(pageable).map(relatorioQtdCandPorEstadoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RelatorioQtdCandPorEstadoDTO> findOne(Long id) {
        log.debug("Request to get RelatorioQtdCandPorEstado : {}", id);
        return relatorioQtdCandPorEstadoRepository.findById(id).map(relatorioQtdCandPorEstadoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelatorioQtdCandPorEstado : {}", id);
        relatorioQtdCandPorEstadoRepository.deleteById(id);
    }
}
