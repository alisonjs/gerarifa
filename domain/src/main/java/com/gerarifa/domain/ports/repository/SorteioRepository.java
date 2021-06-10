package com.gerarifa.domain.ports.repository;

import com.gerarifa.domain.model.Sorteio;
import com.gerarifa.domain.model.status.StatusSorteio;

import java.time.LocalDate;
import java.util.List;

public interface SorteioRepository {

    void save(Sorteio sorteio);

    Sorteio findById(String idSorteio);

    void updateStatus(String idSorteio, StatusSorteio status);

    void updateDataRealizacaoSorteio(String idSorteio, LocalDate novaData);

    List<Sorteio> findAllAtivo();
}
