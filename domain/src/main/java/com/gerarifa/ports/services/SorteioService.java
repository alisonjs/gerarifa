package com.gerarifa.ports.services;

import com.gerarifa.model.Sorteio;

import java.time.LocalDate;

public interface SorteioService {

    void criarSoteio(Sorteio sorteio);

    void atualizarSoteio(Sorteio sorteio);

    void cancelarSorteio(String idSorteio);

    void alterarDataRealizacaoSorteio(String idSorteio, LocalDate novaData);

    void finalizarSorteio(String sorteio, LocalDate dataSorteio);

}
