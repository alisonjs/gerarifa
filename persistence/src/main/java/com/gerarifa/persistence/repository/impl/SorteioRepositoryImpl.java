package com.gerarifa.persistence.repository.impl;

import com.gerarifa.domain.model.Sorteio;
import com.gerarifa.domain.model.status.StatusSorteio;
import com.gerarifa.domain.ports.repository.SorteioRepository;
import com.gerarifa.persistence.entity.mapper.SorteioEntityMapper;
import com.gerarifa.persistence.repository.SorteioJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SorteioRepositoryImpl implements SorteioRepository {

    private final SorteioJpaRepository repository;

    private final SorteioEntityMapper mapper;

    public SorteioRepositoryImpl(SorteioJpaRepository repository, SorteioEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void save(Sorteio sorteio) {
        repository.save(mapper.fromModel(sorteio));
    }

    @Override
    public Sorteio findById(String idSorteio) {
        return mapper.toModel(repository.findById(idSorteio).orElse(null));
    }

    @Override
    public void updateStatus(String idSorteio, StatusSorteio status) {
        repository.updateStatus(idSorteio, status);
    }

    @Override
    public void updateDataRealizacaoSorteio(String idSorteio, LocalDate novaData) {
        repository.updateDataRealizacaSorteio(idSorteio, novaData);
    }

    @Override
    public List<Sorteio> findAllAtivo() {
        return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
    }
}
