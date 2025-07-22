package com.desafio.cartas.application.service;

import com.desafio.cartas.domain.Carta;
import com.desafio.cartas.domain.Mao;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BaralhoServiceTest {

    @Mock
    private BaralhoClient baralhoClient;

    @InjectMocks
    private BaralhoService baralhoService;

    private final  int qtdJogadores = 3;
    private final int qtdCartasPorMao = 3;

    @Test
    void testRecuperarMaosSucesso() throws BaralhoClientException {
        when(baralhoClient.obterCartas(anyInt())).thenReturn(getCartas());
        List<Mao> maos = baralhoService.recuperarMaos(qtdJogadores, qtdCartasPorMao);

        Assertions.assertEquals(3, maos.size());
    }

    @Test
    void testRecuperarMaosObterBaralhoThrowsBaralhoCliException() throws BaralhoClientException {
        when(baralhoClient.obterCartas(anyInt())).thenThrow(BaralhoClientException.class);

        BaralhoClientException thrown = assertThrows(BaralhoClientException.class,
                () -> baralhoService.recuperarMaos(qtdJogadores, qtdCartasPorMao));

        assertNotNull(thrown);
    }

    private List<Carta> getCartas() {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("5", "SPADES"));
        cartas.add(new Carta("QUEEN", "SPADES"));
        cartas.add(new Carta("KING", "SPADES"));
        cartas.add(new Carta("10", "SPADES"));
        cartas.add(new Carta("6", "SPADES"));
        cartas.add(new Carta("2", "SPADES"));
        cartas.add(new Carta("ACE", "SPADES"));
        cartas.add(new Carta("9", "SPADES"));
        cartas.add(new Carta("7", "SPADES"));
        return cartas;
    }

}
