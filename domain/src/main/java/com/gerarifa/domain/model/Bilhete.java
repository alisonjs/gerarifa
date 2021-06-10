package com.gerarifa.domain.model;

import com.gerarifa.domain.model.exception.NegocioException;
import com.gerarifa.domain.model.status.StatusBilhete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Criado por Alison em 07/06/2021
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bilhete {

    private String id;

    private String codigo;

    private StatusBilhete status = StatusBilhete.DISPONIVEL;

    private Comprador comprador;

    private Sorteio sorteio;

    public boolean isVendido(){
        return this.status.equals(StatusBilhete.VENDIDO);
    }

    public boolean isDisponivel(){
        return this.status.equals(StatusBilhete.DISPONIVEL);
    }

    public void venderBilhete() throws NegocioException {

        if(!this.getStatus().equals(StatusBilhete.DISPONIVEL))
            throw new NegocioException("Bilhete indisponível para venda.");

        this.setStatus(StatusBilhete.VENDIDO);
    }

    public void devolverBilhete(){
        if(!this.getStatus().equals(StatusBilhete.VENDIDO))
            throw new NegocioException("Bilhete indisponível para devolução.");

        this.setStatus(StatusBilhete.DISPONIVEL);
    }

    public void removerBilhete(){
        if(!this.getStatus().equals(StatusBilhete.DISPONIVEL))
            throw new NegocioException("Bilhete indisponível para venda.");

        this.setStatus(StatusBilhete.REMOVIDO);
    }
}
