package com.gerarifa.domain.adapter;

import com.gerarifa.domain.model.Sorteio;
import com.gerarifa.domain.model.exception.NegocioException;
import com.gerarifa.domain.model.status.StatusSorteio;
import com.gerarifa.domain.ports.repository.SorteioRepository;
import com.gerarifa.domain.ports.services.BilheteService;
import com.gerarifa.domain.ports.services.ResultadoSorteioService;
import com.gerarifa.domain.ports.services.SorteioService;
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

public class SorteioServiceImplTest {

    private SorteioService sorteioService;

    @Mock
    private BilheteService bilheteService;

    @Mock
    private SorteioRepository repository;

    @Mock
    private ResultadoSorteioService resultadoSorteioService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        sorteioService = new SorteioServiceImpl(repository, resultadoSorteioService, bilheteService);
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
                .premio("Uma cesta de chocolate")
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
                .premio("Uma cesta de chocolate")
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
                .premio("Uma cesta de chocolate")
                .build();
        Assertions.assertThrows(NegocioException.class, () -> sorteioService.criarSoteio(sorteio));
        Mockito.verify(repository, Mockito.times(0)).save(ArgumentMatchers.any());
    }

    @Test
    void cancelarSorteioTest(){
        Sorteio sorteio = Sorteio.builder()
                .id("A98U3HNOIJDUOA")
                .status(StatusSorteio.CRIADO)
                .nome("Sorteio Solidário")
                .descricao("Um sorteio com intuito de levar alegria para a vida das pessoas.")
                .precoBilhete(4D)
                .metaArrecadacao(2D)
                .dataInicio(LocalDate.now())
                .dataRealizacaoSorteio(LocalDate.now())
                .premio("Uma cesta de chocolate")
                .build();

        Mockito.lenient().when(repository.findById(ArgumentMatchers.anyString())).thenReturn(sorteio);
        Mockito.lenient().when(bilheteService.hasBilhetesVendidos(ArgumentMatchers.anyString())).thenReturn(false);

        sorteioService.cancelarSorteio(sorteio.getId());

        Mockito.verify(repository, Mockito.times(1)).updateStatus(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

    @Test
    void cancelarSorteioIniciadoTest(){
        Sorteio sorteio = Sorteio.builder()
                .id("A98U3HNOIJDUOA")
                .status(StatusSorteio.INICIADO)
                .nome("Sorteio Solidário")
                .descricao("Um sorteio com intuito de levar alegria para a vida das pessoas.")
                .precoBilhete(4D)
                .metaArrecadacao(2D)
                .dataInicio(LocalDate.now())
                .dataRealizacaoSorteio(LocalDate.now())
                .premio("Uma cesta de chocolate")
                .build();

        Mockito.lenient().when(repository.findById(ArgumentMatchers.anyString())).thenReturn(sorteio);
        Mockito.lenient().when(bilheteService.hasBilhetesVendidos(ArgumentMatchers.anyString())).thenReturn(false);

        sorteioService.cancelarSorteio(sorteio.getId());

        Mockito.verify(repository, Mockito.times(1)).updateStatus(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

    @Test
    void cancelarSorteioIniciadoComBilheteVendidoTest(){
        Sorteio sorteio = Sorteio.builder()
                .id("A98U3HNOIJDUOA")
                .status(StatusSorteio.INICIADO)
                .nome("Sorteio Solidário")
                .descricao("Um sorteio com intuito de levar alegria para a vida das pessoas.")
                .precoBilhete(4D)
                .metaArrecadacao(2D)
                .dataInicio(LocalDate.now())
                .dataRealizacaoSorteio(LocalDate.now())
                .premio("Uma cesta de chocolate")
                .build();

        Mockito.lenient().when(repository.findById(ArgumentMatchers.anyString())).thenReturn(sorteio);
        Mockito.lenient().when(bilheteService.hasBilhetesVendidos(ArgumentMatchers.anyString())).thenReturn(true);

        Assertions.assertThrows(NegocioException.class, () -> sorteioService.cancelarSorteio(sorteio.getId()));

        Mockito.verify(repository, Mockito.times(0)).updateStatus(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

}
