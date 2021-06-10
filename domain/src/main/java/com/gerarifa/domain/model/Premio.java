package com.gerarifa.domain.model;

import com.gerarifa.domain.model.status.StatusPremio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Premio {

    private String id;

    private String nome;

    private String descricao;

    private String foto;

    private StatusPremio status = StatusPremio.DISPONIVEL;

}