package com.desafio.cartas.application.service;

import com.desafio.cartas.application.usecases.BaralhoUseCases;
import com.desafio.cartas.domain.*;
import com.desafio.cartas.infrastructure.adapters.in.controller.JogoResponseDto;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
    public void testApurarVencedoresSucesso() {
        Jogo jogo = getJogo();
        jogo.setMaos(getMaos(jogo));

        jogoService.apurarVencedores(jogo);

        Assertions.assertEquals(1, jogo.getVencedores().size());
    }

    @Test
    public void testJogarSucesso() throws BaralhoClientException {
        Jogo jogo = getJogo();
        List<Mao> maos = getMaos(jogo);

        when(baralhoUseCases.recuperarMaos(Mockito.any())).thenReturn(maos);
        doNothing().when(jogoRepository).salvar(Mockito.any());
        JogoResponseDto response = jogoService.jogar(3,3);

        Assertions.assertEquals("[jogador1]", response.ganhadores());
    }

    @Test
    public void testJogarParametrosInvalidos() throws BaralhoClientException {
        Jogo jogo = getJogo();
        List<Mao> maos = getMaos(jogo);

        when(baralhoUseCases.recuperarMaos(Mockito.any())).thenReturn(maos);
        doNothing().when(jogoRepository).salvar(Mockito.any());
        JogoResponseDto response = jogoService.jogar(1000,0);

        Assertions.assertEquals("[jogador1]", response.ganhadores());
    }


    private static Jogo getJogo() {
        int qtdJogadores = 3;
        int qtdCartasPorMao = 3;
        return new Jogo(qtdJogadores, qtdCartasPorMao);
    }

    private static List<Mao> getMaos(Jogo jogo) {
        List<Mao> maos = new ArrayList<>();
        List<Carta> cartasJog1 = new ArrayList<>();
        cartasJog1.add(new Carta("HEARTS","9"));
        cartasJog1.add(new Carta("SPADES","10"));
        cartasJog1.add(new Carta("CLUBS","8"));
        Mao mao1 = new Mao(jogo, new Jogador("jogador1"), cartasJog1);
        maos.add(mao1);
        List<Carta> cartasJog2 = new ArrayList<>();
        cartasJog2.add(new Carta("MONDS","ACE"));
        cartasJog2.add(new Carta("CLUBS","8"));
        cartasJog2.add(new Carta("SPADES","2"));
        Mao mao2 = new Mao(jogo, new Jogador("jogador2"), cartasJog2);
        maos.add(mao2);
        List<Carta> cartasJog3 = new ArrayList<>();
        cartasJog3.add(new Carta("CLUBS","5"));
        cartasJog3.add(new Carta("HEARTS","4"));
        cartasJog3.add(new Carta("HEARTS","QUEEN"));
        Mao mao3 = new Mao(jogo, new Jogador("jogador3"), cartasJog3);
        maos.add(mao3);
        return maos;
    }
}
