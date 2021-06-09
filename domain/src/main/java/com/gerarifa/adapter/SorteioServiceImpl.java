package com.gerarifa.adapter;

import com.gerarifa.model.ResultadoSorteio;
import com.gerarifa.model.Sorteio;
import com.gerarifa.model.exception.NegocioException;
import com.gerarifa.model.status.StatusSorteio;
import com.gerarifa.ports.repository.SorteioRepository;
import com.gerarifa.ports.services.ResultadoSorteioService;
import com.gerarifa.ports.services.SorteioService;

import java.time.LocalDate;
import java.util.Set;

public class SorteioServiceImpl implements SorteioService {


    private final SorteioRepository repository;

    private final ResultadoSorteioService resultadoSorteioService;

    public SorteioServiceImpl(SorteioRepository repository, ResultadoSorteioService resultadoSorteioService) {
        this.repository = repository;
        this.resultadoSorteioService = resultadoSorteioService;
    }

    /**
     * Criação de um sorteio
     * @param sorteio Sorteio a ser criado
     */
    @Override
    public void criarSoteio(Sorteio sorteio) {
        sorteio.sanitize();

        if(sorteio.getPremios() == null || sorteio.getPremios().isEmpty())
            throw new NegocioException("O sorteio precisa ter no mínimo um prêmio.");

        if(sorteio.getPrecoBilhete() <= 0)
            throw new NegocioException("O preço do bilhete precisa ser maior que 0");

        if(sorteio.getMetaArrecadacao() < sorteio.getPrecoBilhete())
            throw new NegocioException("A meta de arrecadação não pode ser menor que o preço do bilhete");

        sorteio.setDataCriacao(LocalDate.now());

        if(sorteio.getDataCriacao().isAfter(sorteio.getDataInicio()) || sorteio.getDataInicio().isAfter(sorteio.getDataRealizacaoSorteio()))
            throw new NegocioException("O sorteio não pode iniciar antes de sua criação ou após a realização do sorteio.");

        sorteio.setStatus(StatusSorteio.CRIADO);

        repository.save(sorteio);
    }

    /**
     * Atualização dos dados do sorteio
     * @param sorteio Sorteio a ser atualizado.
     */
    @Override
    public void atualizarSoteio(Sorteio sorteio) {
        if(sorteio.getStatus().equals(StatusSorteio.INICIADO))
            throw new NegocioException("Os dados do sorteio não podem ser alterados após o seu inínio.");

        if(sorteio.getStatus().equals(StatusSorteio.FINALIZADO))
            throw new NegocioException("Os dados do sorteio não podem ser alterados após a sua finalização.");
    }

    @Override
    public void cancelarSorteio(String idSorteio) {
        Sorteio sorteio = repository.findById(idSorteio);
        boolean hasBilhetesVendidos = repository.hasBilhetesVendidos(idSorteio);
        if(sorteio.getStatus().equals(StatusSorteio.INICIADO) && hasBilhetesVendidos)
            throw new NegocioException("Não é possível cancelar um sorteio em andamento com bilhetes vendidos.");

        repository.updateStatus(idSorteio, StatusSorteio.CANCELADO);
    }

    /**
     * Alterar data de realização do sorteio
     * @param idSorteio
     * @param novaData
     */
    @Override
    public void alterarDataRealizacaoSorteio(String idSorteio, LocalDate novaData) {
        Sorteio sorteio = repository.findById(idSorteio);
        if(novaData.isBefore(sorteio.getDataInicio()))
            throw new NegocioException("A data de realização do sorteio não pode ser anterior a data de início.");

        repository.updateDataRealizacaoSorteio(idSorteio, novaData);
    }

    /**
     * Após a realização so sorteio de todos os prêmios o sorteio é finalizado.
     * @param idSorteio
     * @param dataSorteio
     */
    @Override
    public void finalizarSorteio(String idSorteio, LocalDate dataSorteio) {
        Sorteio sorteio = repository.findById(idSorteio);
        Set<ResultadoSorteio> resultados = resultadoSorteioService.getAll(idSorteio);

        if(!sorteio.getDataRealizacaoSorteio().isEqual(dataSorteio))
            throw new NegocioException("Não está em período de realização do sorteio.");

        if(sorteio.getPremios().size() > resultados.size())
            throw new NegocioException("Ainda não foram sorteados todos os prêmios");

        repository.updateStatus(idSorteio, StatusSorteio.FINALIZADO);
    }

}
