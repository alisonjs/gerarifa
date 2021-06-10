package com.gerarifa.domain.ports.services;

import com.gerarifa.domain.model.Bilhete;

public interface BilheteService {

    void save(Bilhete bilhete);

    void venderBilhete(Bilhete bilhete);

    void devolverBilhete(Bilhete bilhete);

    boolean hasBilhetesVendidos(String sorteio);

}
