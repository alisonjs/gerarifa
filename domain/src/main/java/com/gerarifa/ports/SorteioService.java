package com.gerarifa.ports;

import com.gerarifa.domain.Bilhete;
import com.gerarifa.domain.Premio;
import com.gerarifa.domain.Sorteio;

import java.util.Date;

public interface SorteioService {

    void criarSoteio(Sorteio sorteio);

    void cancelarSorteio(String idSorteio);

    void alterarDataRealizacaoSorteio(String Sorteio, Date novaData);

    void finalizarSorteio(String sorteio, Date dataSorteio);

    void realizarSortei(Sorteio sorteio, Bilhete bilhete, Premio premio);

}
