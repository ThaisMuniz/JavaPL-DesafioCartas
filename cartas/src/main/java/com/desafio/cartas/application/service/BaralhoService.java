package com.desafio.cartas.application.service;

import com.desafio.cartas.application.usecases.BaralhoUseCases;
import com.desafio.cartas.domain.Carta;
import com.desafio.cartas.domain.Jogador;
import com.desafio.cartas.domain.Jogo;
import com.desafio.cartas.domain.Mao;
import com.desafio.cartas.infrastructure.adapters.in.apiclient.BaralhoFeignClient;
import com.desafio.cartas.infrastructure.adapters.in.apiclient.CardDto;
import com.desafio.cartas.infrastructure.adapters.in.apiclient.DeckDto;
import com.desafio.cartas.infrastructure.adapters.in.apiclient.DrawDto;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BaralhoService implements BaralhoUseCases {

    private final BaralhoFeignClient baralhoFeignClient;

    public BaralhoService(BaralhoFeignClient baralhoFeignClient) {
        this.baralhoFeignClient = baralhoFeignClient;
    }

    @Override
    public List<Mao> recuperarMaos(Jogo jogo) throws BaralhoClientException {
        List<CardDto> cardsFromDeck = getCardsFromDeck(getDeckId(), jogo.getQtdJogadores()*jogo.getQtdCartasPorMao());
        return distribuirCartas(jogo, cardsFromDeck);
    }

    List<Mao> distribuirCartas(Jogo jogo, List<CardDto> cardDtoList) {
        List<Mao> maos = new ArrayList<>();
        int indexCartas = 0;
        while(maos.size() < jogo.getQtdJogadores()) {
            List<Carta> cartas = cardDtoList.subList(indexCartas, indexCartas+jogo.getQtdCartasPorMao()).stream()
                    .map(cardDto ->  new Carta(cardDto.suit(), cardDto.value())).toList();
            maos.add(new Mao(jogo, getJogador(), cartas));
            indexCartas = indexCartas+jogo.getQtdCartasPorMao();
        }
        return maos;
    }

    Jogador getJogador() {
        Random rand = new Random();
        return new Jogador("Jogador" + rand.nextInt(100));
    }

    String getDeckId() throws BaralhoClientException{
        ResponseEntity<DeckDto> retorno =  baralhoFeignClient.obterBaralhoID();
        if (retorno.getStatusCode() != HttpStatus.OK || retorno.getBody() == null) {
            throw new BaralhoClientException("Erro ao obter o deck de cartas.");
        }
        return retorno.getBody().deck_id();
    }

    List<CardDto> getCardsFromDeck(String deckId, int qtdCartas) throws BaralhoClientException {
        ResponseEntity<DrawDto> retorno =  baralhoFeignClient.obterCartas(deckId, qtdCartas);
        if (retorno.getStatusCode() != HttpStatus.OK || retorno.getBody() == null || !retorno.getBody().success()) {
            throw new BaralhoClientException("Erro ao obter as cartas do deck.");
        }
        return retorno.getBody().cards();
    }
}
