package com.gerarifa.domain.ports.services;

import com.gerarifa.domain.model.ResultadoSorteio;

import java.util.Set;

public interface ResultadoSorteioService {

    public void save(ResultadoSorteio resultadoSorteio);

    Set<ResultadoSorteio> getAll(String idSorteio);
}
