package com.desafio.cartas.domain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public record Mao (Jogo jogo,
                   Jogador jogador,
                   List<Carta> cartas) {

    public int getValor() {
        if (cartas == null || cartas.isEmpty()) {
            return 0;
        }
        AtomicInteger valorDaMao = new AtomicInteger();
        cartas.forEach(carta -> {
            valorDaMao.addAndGet(carta.getValorInteiro());
        });
        return valorDaMao.get();
    }

}
