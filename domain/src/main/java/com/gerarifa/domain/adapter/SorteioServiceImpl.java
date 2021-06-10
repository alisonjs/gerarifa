package com.gerarifa.domain.adapter;

import com.gerarifa.domain.model.ResultadoSorteio;
import com.gerarifa.domain.model.Sorteio;
import com.gerarifa.domain.model.exception.NegocioException;
import com.gerarifa.domain.model.status.StatusSorteio;
import com.gerarifa.domain.ports.repository.SorteioRepository;
import com.gerarifa.domain.ports.services.BilheteService;
import com.gerarifa.domain.ports.services.ResultadoSorteioService;
import com.gerarifa.domain.ports.services.SorteioService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class SorteioServiceImpl implements SorteioService {


    private final SorteioRepository repository;

    private final BilheteService bilheteService;

    private final ResultadoSorteioService resultadoSorteioService;

    public SorteioServiceImpl(SorteioRepository repository, ResultadoSorteioService resultadoSorteioService, BilheteService bilheteService) {
        this.repository = repository;
        this.resultadoSorteioService = resultadoSorteioService;
        this.bilheteService = bilheteService;
    }

    /**
     * Criação de um sorteio
     * @param sorteio Sorteio a ser criado
     */
    @Override
    public void criarSoteio(Sorteio sorteio) {
        sorteio.sanitize();

        if(sorteio.getPremio() == null)
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

        if(sorteio.getPremio() == null)
            throw new NegocioException("O sorteio precisa ter no mínimo um prêmio.");

        if(sorteio.getPrecoBilhete() <= 0)
            throw new NegocioException("O preço do bilhete precisa ser maior que 0");

        if(sorteio.getMetaArrecadacao() < sorteio.getPrecoBilhete())
            throw new NegocioException("A meta de arrecadação não pode ser menor que o preço do bilhete");

        if(sorteio.getDataCriacao().isAfter(sorteio.getDataInicio()) || sorteio.getDataInicio().isAfter(sorteio.getDataRealizacaoSorteio()))
            throw new NegocioException("O sorteio não pode iniciar antes de sua criação ou após a realização do sorteio.");

        repository.save(sorteio);
    }

    @Override
    public void cancelarSorteio(String idSorteio) {
        Sorteio sorteio = repository.findById(idSorteio);
        boolean hasBilhetesVendidos = bilheteService.hasBilhetesVendidos(idSorteio);
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

        if(resultados == null || resultados.isEmpty())
            throw new NegocioException("Nenhum prêmio foi sorteado.");

        repository.updateStatus(idSorteio, StatusSorteio.FINALIZADO);
    }

    @Override
    public Sorteio getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Sorteio> getAllAtivo() {
        return repository.findAllAtivo();
    }

}
