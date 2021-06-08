package com.gerarifa.domain;

import com.gerarifa.domain.status.StatusBilhete;
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

    public boolean isVendido(){
        return this.status.equals(StatusBilhete.VENDIDO);
    }
}
