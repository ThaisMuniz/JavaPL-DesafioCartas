package com.desafio.cartas.application.service;

import com.desafio.cartas.application.usecases.BaralhoUseCases;
import com.desafio.cartas.domain.*;
import com.desafio.cartas.infrastructure.adapters.in.controller.JogoResponseDto;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import com.desafio.cartas.infrastructure.exceptions.ParametrosDeJogoInvalidosException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JogoServiceTest {

    @Mock
    private JogoRepository jogoRepository;
    @Mock
    private BaralhoUseCases baralhoUseCases;

    @InjectMocks
    private JogoService jogoService;

    @Test
    public void testJogarSucesso() throws BaralhoClientException, ParametrosDeJogoInvalidosException {
        List<Mao> maos = getMaos();

        when(baralhoUseCases.recuperarMaos(Mockito.anyInt(), Mockito.anyInt())).thenReturn(maos);
        doNothing().when(jogoRepository).salvar(Mockito.any());

        JogoResponseDto response = jogoService.jogar(3,3);

        Assertions.assertFalse(response.ganhadores().isEmpty());
    }

    @Test
    public void testJogarComQtdJogadoresInvalidathrowsParametrosDeJogoInvalidosException() throws ParametrosDeJogoInvalidosException {

        ParametrosDeJogoInvalidosException thrown = assertThrows(ParametrosDeJogoInvalidosException.class,
                () -> jogoService.jogar(1,5));

        assertEquals("Erro de validação nos parâmetros do jogo.", thrown.getMessage());
        assertEquals("Quantidade de jogadores (1) inválida!.", thrown.getErrors().get("qtdJogadores"));
    }

    @Test
    void testJogarComQtdCartasPorMaoInvalidathrowsParametrosDeJogoInvalidosException() throws ParametrosDeJogoInvalidosException {

        ParametrosDeJogoInvalidosException thrown = assertThrows(ParametrosDeJogoInvalidosException.class,
                () -> jogoService.jogar(4,0));

        assertEquals("Erro de validação nos parâmetros do jogo.", thrown.getMessage());
        assertEquals("Quantidade de cartas por jogador (0) inválida!.", thrown.getErrors().get("qtdCartasPorMao"));
    }

    @Test
    void testJogarThrowsBaralhoClientExceptionRetorna500() throws BaralhoClientException, ParametrosDeJogoInvalidosException {

        doThrow(new BaralhoClientException("Erro ao se comunicar com o serviço de baralho."))
                .when(baralhoUseCases).recuperarMaos(anyInt(), anyInt());

        BaralhoClientException thrown = assertThrows(BaralhoClientException.class,
                () -> jogoService.jogar(2,5));

        assertEquals("Erro ao se comunicar com o serviço de baralho.", thrown.getMessage());
    }

    private List<Mao> getMaos() {
        List<Mao> maos = new ArrayList<>();
        List<Carta> cartasJog1 = new ArrayList<>();
        cartasJog1.add(new Carta("HEARTS","9"));
        cartasJog1.add(new Carta("SPADES","10"));
        cartasJog1.add(new Carta("CLUBS","8"));
        Mao mao1 = new Mao(new Jogador("jogador1"), cartasJog1);
        maos.add(mao1);
        List<Carta> cartasJog2 = new ArrayList<>();
        cartasJog2.add(new Carta("MONDS","ACE"));
        cartasJog2.add(new Carta("CLUBS","8"));
        cartasJog2.add(new Carta("SPADES","2"));
        Mao mao2 = new Mao(new Jogador("jogador2"), cartasJog2);
        maos.add(mao2);
        List<Carta> cartasJog3 = new ArrayList<>();
        cartasJog3.add(new Carta("CLUBS","5"));
        cartasJog3.add(new Carta("HEARTS","4"));
        cartasJog3.add(new Carta("HEARTS","QUEEN"));
        Mao mao3 = new Mao(new Jogador("jogador3"), cartasJog3);
        maos.add(mao3);
        return maos;
    }
}
