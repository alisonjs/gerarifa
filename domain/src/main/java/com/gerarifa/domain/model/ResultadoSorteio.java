package com.gerarifa.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * Criado por Alison em 07/06/2021
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoSorteio {

    private Bilhete bilhete;

    private Sorteio sorteio;

    private Premio premio;

    private Date dataRealizacaoSorteio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultadoSorteio that = (ResultadoSorteio) o;
        return bilhete.equals(that.bilhete) && sorteio.equals(that.sorteio) && premio.equals(that.premio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bilhete, sorteio, premio);
    }
}
