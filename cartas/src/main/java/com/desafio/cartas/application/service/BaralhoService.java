package com.desafio.cartas.application.service;

import com.desafio.cartas.application.usecases.BaralhoUseCases;
import com.desafio.cartas.domain.Carta;
import com.desafio.cartas.domain.Jogador;
import com.desafio.cartas.domain.Mao;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BaralhoService implements BaralhoUseCases {

    private final BaralhoClient baralhoClient;

    public BaralhoService(BaralhoClient baralhoClient) {
        this.baralhoClient = baralhoClient;
    }

    @Override
    public List<Mao> recuperarMaos(int qtdJogadores, int qtdCartasPorMao) throws BaralhoClientException {
        List<Carta> cardsFromDeck = baralhoClient.obterCartas(qtdJogadores * qtdCartasPorMao);
        return distribuirCartas(qtdJogadores, qtdCartasPorMao, cardsFromDeck);
    }

    private List<Mao> distribuirCartas(int qtdJogadores, int qtdCartasPorMao, List<Carta> cardDtoList) {
        List<Mao> maos = new ArrayList<>();
        int indexCartas = 0;
        while(maos.size() < qtdJogadores) {
            List<Carta> cartas = cardDtoList.subList(indexCartas, indexCartas + qtdCartasPorMao).stream().toList();
            maos.add(new Mao(getJogador(), cartas));
            indexCartas = indexCartas + qtdCartasPorMao;
        }
        return maos;
    }

    private Jogador getJogador() {
        Random rand = new Random();
        return new Jogador("Jogador" + rand.nextInt(100));
    }

}
