package com.desafio.cartas.domain;

import com.desafio.cartas.util.CardsValuesEnum;

public record Carta (String naipe,
                     String valor) {

    public int getValorInteiro () {
        return CardsValuesEnum.getValor(this.valor);
    }

}
