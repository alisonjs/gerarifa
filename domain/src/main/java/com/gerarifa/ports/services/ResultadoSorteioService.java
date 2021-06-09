package com.gerarifa.ports.services;

import com.gerarifa.model.ResultadoSorteio;

import java.util.Set;

public interface ResultadoSorteioService {

    public void save(ResultadoSorteio resultadoSorteio);

    Set<ResultadoSorteio> getAll(String idSorteio);
}
