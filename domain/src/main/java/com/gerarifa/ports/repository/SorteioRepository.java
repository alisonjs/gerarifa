package com.gerarifa.ports.repository;

import com.gerarifa.model.Sorteio;
import com.gerarifa.model.status.StatusSorteio;

import java.time.LocalDate;

public interface SorteioRepository {

    void save(Sorteio sorteio);

    Sorteio findById(String idSorteio);

    boolean hasBilhetesVendidos(String idSorteio);

    void updateStatus(String idSorteio, StatusSorteio cancelado);

    void updateDataRealizacaoSorteio(String idSorteio, LocalDate novaData);
}
