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
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BaralhoService implements BaralhoUseCases {

    private final BaralhoFeignClient baralhoFeignClient;

    public BaralhoService(BaralhoFeignClient baralhoFeignClient) {
        this.baralhoFeignClient = baralhoFeignClient;
    }

    @Override
    public List<Mao> recuperarMaos(Jogo jogo) throws BaralhoClientException {

        DrawDto drawDto = getCartas(getDeckId(), jogo.getQtdJogadores()*jogo.getQtdCartasPorMao());

        return distribuirCartas(jogo, drawDto.getCards());
    }

    private List<Mao> distribuirCartas(Jogo jogo, List<CardDto> cardDtoList) throws BaralhoClientException {

        List<Mao> maos = new ArrayList<>();
        int indexCartas = 0;
        while(maos.size() < jogo.getQtdJogadores()) {
            Mao mao = new Mao();
            mao.setJogo(jogo);
            mao.setJogador(getJogador());
            List<CardDto> cardDtos = cardDtoList.subList(indexCartas, indexCartas+jogo.getQtdCartasPorMao());
            apurarValor(cardDtos, mao);
            maos.add(mao);
            indexCartas = indexCartas+jogo.getQtdCartasPorMao();
        }

        return maos;
    }

    private Jogador getJogador() {
        Random rand = new Random();
        Jogador jogador = new Jogador();
        jogador.setNome("Jogador"+rand.nextInt(100));
        return jogador;
    }

    private void apurarValor(List<CardDto> cardDtos, Mao mao) throws BaralhoClientException {
        List<Carta> cartas = new ArrayList<>();
        AtomicInteger valorDaMao = new AtomicInteger();
        cardDtos.forEach(cardDto -> {
            valorDaMao.addAndGet(mapValorCarta(cardDto.getValue()));
            cartas.add(cardDtoToCarta(cardDto));
        });
        mao.setCartas(cartas);
        mao.setValor(valorDaMao.get());
    }

    private int mapValorCarta(String value) {
        return switch (value) {
            case "ACE" -> 1;
            case "JACK" -> 11;
            case "QUEEN" -> 12;
            case "KING" -> 13;
            default -> Integer.parseInt(value);
        };
    }

    private Carta cardDtoToCarta(CardDto cardDto) {
        Carta carta = new Carta();
        carta.setNaipe(cardDto.getSuit());
        carta.setValor(cardDto.getValue());
        return carta;
    }

    String getDeckId() throws BaralhoClientException{
        ResponseEntity<DeckDto> retorno =  baralhoFeignClient.obterBaralhoID();
        if (retorno.getStatusCode() != HttpStatus.OK || retorno.getBody() == null) {
            throw new BaralhoClientException();
        }
        return retorno.getBody().getDeck_id();
    }

    DrawDto getCartas(String deckId, int qtdCartas) throws BaralhoClientException {
        ResponseEntity<DrawDto> retorno =  baralhoFeignClient.obterCartas(deckId, qtdCartas);
        if (retorno.getStatusCode() != HttpStatus.OK) {
            throw new BaralhoClientException();
        }
        return retorno.getBody();
    }
}
