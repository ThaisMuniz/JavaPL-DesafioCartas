package com.desafio.cartas.infrastructure.adapters.in.apiclient;

import com.desafio.cartas.application.service.BaralhoClient;
import com.desafio.cartas.domain.Carta;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaralhoClientImpl implements BaralhoClient {

    private final BaralhoFeignClient baralhoFeignClient;

    public BaralhoClientImpl(BaralhoFeignClient baralhoFeignClient) {
        this.baralhoFeignClient = baralhoFeignClient;
    }

    public List<Carta> obterCartas(int qtdCartas) throws BaralhoClientException {
        String deckId = getDeckId();
        ResponseEntity<DrawDto> retorno =  baralhoFeignClient.getCardsFromDeck(deckId, qtdCartas);
        if (retorno.getStatusCode() != HttpStatus.OK || retorno.getBody() == null || !retorno.getBody().success()) {
            throw new BaralhoClientException("Erro ao obter as cartas do deck.");
        }
        return retorno.getBody().cards().stream().map(card -> new Carta(card.suit(),card.value())).toList();
    }

    private String getDeckId() throws BaralhoClientException {
        ResponseEntity<DeckDto> retorno =  baralhoFeignClient.getDeckId();
        if (retorno.getStatusCode() != HttpStatus.OK || retorno.getBody() == null) {
            throw new BaralhoClientException("Erro ao obter o deck de cartas.");
        }
        return retorno.getBody().deck_id();
    }
}
