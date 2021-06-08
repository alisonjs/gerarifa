package com.gerarifa.domain;

import com.gerarifa.domain.exception.NegocioException;
import com.gerarifa.domain.status.StatusSorteio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
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

    private Date dataCriacao;

    private Date dataInicio;

    private Date dataRealizacaoSorteio;

    private StatusSorteio status;

    private Set<Bilhete> bilhetes;

    private Set<ResultadoSorteio> resultados;

    private List<Premio> premios;

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

    }

    public void criarSorteio() throws NegocioException {

        if(this.premios == null || this.premios.isEmpty())
            throw new NegocioException("");

        this.setDataCriacao(new Date());

        if(this.dataCriacao.after(dataInicio) || dataInicio.after(dataRealizacaoSorteio))
            throw new NegocioException("");

        this.setStatus(StatusSorteio.CRIADO);
    }

    public void cancelarSorteio(boolean hasBilhetesVendidos) throws NegocioException{
        if(this.status.equals(StatusSorteio.INICIADO) && hasBilhetesVendidos)
            throw new NegocioException("");

        this.setStatus(StatusSorteio.CANCELADO);
    }

    public void alterarDataRealizacaoSorteio(Date novaData) throws NegocioException{
        if(novaData.before(this.dataInicio))
            throw new NegocioException("");

        this.setDataRealizacaoSorteio(novaData);
    }

    /**
     * Após o sorteio de todos os prêmios o sorteio é finalizado.
     */
    public void finalizarSorteio(Date dataSorteio) throws NegocioException{
        if(!this.dataRealizacaoSorteio.equals(dataSorteio))
            throw new NegocioException("");

        if(premios.size() != resultados.size())
            throw new NegocioException("");

        this.setStatus(StatusSorteio.FINALIZADO);
    }

}
