package com.gerarifa.domain.ports.services.command;

import com.gerarifa.domain.model.Bilhete;
import com.gerarifa.domain.model.Premio;
import com.gerarifa.domain.model.Sorteio;

import java.time.LocalDate;

public interface RealizarSorteioCommand {

    void realizarSorteio(Sorteio sorteio, Bilhete bilhete, Premio premio, LocalDate dataRealizacaoSorteio);
}
