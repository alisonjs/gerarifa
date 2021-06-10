package com.gerarifa.domain.model;

import com.gerarifa.domain.model.exception.NegocioException;
import com.gerarifa.domain.model.status.StatusBilhete;
import com.gerarifa.domain.model.status.StatusPremio;
import com.gerarifa.domain.model.status.StatusSorteio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Criado por Alison em 07/06/2021
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sorteio {

    private String id;

    private String nome;

    private String descricao;

    private Double precoBilhete;

    private Double metaArrecadacao;

    private LocalDate dataCriacao;

    private LocalDate dataInicio;

    private LocalDate dataRealizacaoSorteio;

    private StatusSorteio status;

    private Set<Bilhete> bilhetes;

    private Set<ResultadoSorteio> resultados;

    private String premio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sorteio sorteio = (Sorteio) o;
        return id.equals(sorteio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void sanitize(){
        /** Preenchimento correto dos campos. */
    }

    public void realizarSorteio(Bilhete bilhete, Premio premio) throws NegocioException {
        if(!this.status.equals(StatusSorteio.INICIADO))
            throw new NegocioException("Não é possível realizar sorteios com sorteios não iniciados.");

        if(!bilhete.getStatus().equals(StatusBilhete.DISPONIVEL))
            throw new NegocioException("Bilhete inválido para sorteio.");

        bilhete.setStatus(StatusBilhete.SORTEADO);
        premio.setStatus(StatusPremio.SORTEADO);
    }

}
