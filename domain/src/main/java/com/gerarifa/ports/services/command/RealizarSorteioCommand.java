package com.gerarifa.ports.services.command;

import com.gerarifa.model.Bilhete;
import com.gerarifa.model.Premio;
import com.gerarifa.model.Sorteio;

import java.time.LocalDate;

public interface RealizarSorteioCommand {

    void realizarSorteio(Sorteio sorteio, Bilhete bilhete, Premio premio, LocalDate dataRealizacaoSorteio);
}
