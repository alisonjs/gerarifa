package com.gerarifa.ports.services;

import com.gerarifa.model.Bilhete;

public interface BilheteService {

    void save(Bilhete bilhete);

    void venderBilhete(Bilhete bilhete);

    void devolverBilhete(Bilhete bilhete);

    boolean hasBilhetesVendidos(String sorteio);

}
