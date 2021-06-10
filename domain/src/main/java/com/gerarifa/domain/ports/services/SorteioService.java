package com.gerarifa.domain.ports.services;

import com.gerarifa.domain.model.Sorteio;

import java.time.LocalDate;
import java.util.List;

public interface SorteioService {

    void criarSoteio(Sorteio sorteio);

    void atualizarSoteio(Sorteio sorteio);

    void cancelarSorteio(String idSorteio);

    void alterarDataRealizacaoSorteio(String idSorteio, LocalDate novaData);

    void finalizarSorteio(String sorteio, LocalDate dataSorteio);

    Sorteio getById(String id);

    List<Sorteio> getAllAtivo();
}
