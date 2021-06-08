package com.gerarifa.domain;

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

    private String codigoBilhete;

    private String sorteio;

    private String premio;

    private Date dataRealizacaoSorteio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultadoSorteio that = (ResultadoSorteio) o;
        return codigoBilhete.equals(that.codigoBilhete) && sorteio.equals(that.sorteio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoBilhete, sorteio);
    }
}
