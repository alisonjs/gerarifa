package com.gerarifa.domain.web.controller.form;

import com.gerarifa.domain.model.Bilhete;
import com.gerarifa.domain.model.ResultadoSorteio;
import com.gerarifa.domain.model.status.StatusSorteio;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SorteioForm {
    private String id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private Double precoBilhete;

    @NotNull
    private Double metaArrecadacao;

    private LocalDate dataCriacao;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataRealizacaoSorteio;

    private StatusSorteio status;

    private Set<Bilhete> bilhetes;

    private Set<ResultadoSorteio> resultados;

    @NotBlank
    private String premio;

    public boolean isApto(){
        return status.equals(StatusSorteio.INICIADO) && dataRealizacaoSorteio.isEqual(LocalDate.now());
    }
}
