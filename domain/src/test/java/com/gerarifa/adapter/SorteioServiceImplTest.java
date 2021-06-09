package com.gerarifa.adapter;

import com.gerarifa.model.Premio;
import com.gerarifa.model.Sorteio;
import com.gerarifa.model.exception.NegocioException;
import com.gerarifa.ports.repository.SorteioRepository;
import com.gerarifa.ports.services.ResultadoSorteioService;
import com.gerarifa.ports.services.SorteioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

public class SorteioServiceImplTest {

    private SorteioService sorteioService;

    @Mock
    private SorteioRepository repository;

    @Mock
    private ResultadoSorteioService resultadoSorteioService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        sorteioService = new SorteioServiceImpl(repository, resultadoSorteioService);
    }

    @Test
    void criarSorteioTest(){
        Sorteio sorteio = Sorteio.builder()
                .nome("Sorteio Solidário")
                .descricao("Um sorteio com intuito de levar alegria para a vida das pessoas.")
                .precoBilhete(2D)
                .metaArrecadacao(2000D)
                .dataInicio(LocalDate.now())
                .dataRealizacaoSorteio(LocalDate.now())
                .premios(List.of(Premio.builder().nome("Cesta de Chocolate").foto("url").descricao("Uma ceste de chocolate").build()))
                .build();
        sorteioService.criarSoteio(sorteio);
        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void criarSorteioSemPremiosTest(){
        Sorteio sorteio = Sorteio.builder()
                .nome("Sorteio Solidário")
                .descricao("Um sorteio com intuito de levar alegria para a vida das pessoas.")
                .precoBilhete(2D)
                .metaArrecadacao(2000D)
                .dataInicio(LocalDate.now())
                .dataRealizacaoSorteio(LocalDate.now())
                .build();
        Assertions.assertThrows(NegocioException.class, () -> sorteioService.criarSoteio(sorteio));
        Mockito.verify(repository, Mockito.times(0)).save(ArgumentMatchers.any());
    }

    @Test
    void criarSorteioMetaMenorQuePrecoBilheteTest(){
        Sorteio sorteio = Sorteio.builder()
                .nome("Sorteio Solidário")
                .descricao("Um sorteio com intuito de levar alegria para a vida das pessoas.")
                .precoBilhete(4D)
                .metaArrecadacao(2D)
                .dataInicio(LocalDate.now())
                .dataRealizacaoSorteio(LocalDate.now())
                .premios(List.of(Premio.builder().nome("Cesta de Chocolate").foto("url").descricao("Uma ceste de chocolate").build()))
                .build();
        Assertions.assertThrows(NegocioException.class, () -> sorteioService.criarSoteio(sorteio));
        Mockito.verify(repository, Mockito.times(0)).save(ArgumentMatchers.any());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -20})
    void criarSorteioPrecoBilheteValorInvalidoTest(double precoBilhete){
        Sorteio sorteio = Sorteio.builder()
                .nome("Sorteio Solidário")
                .descricao("Um sorteio com intuito de levar alegria para a vida das pessoas.")
                .precoBilhete(precoBilhete)
                .metaArrecadacao(2000D)
                .dataInicio(LocalDate.now())
                .dataRealizacaoSorteio(LocalDate.now())
                .premios(List.of(Premio.builder().nome("Cesta de Chocolate").foto("url").descricao("Uma ceste de chocolate").build()))
                .build();
        Assertions.assertThrows(NegocioException.class, () -> sorteioService.criarSoteio(sorteio));
        Mockito.verify(repository, Mockito.times(0)).save(ArgumentMatchers.any());
    }

}
