package com.desafio.cartas.infrastructure.adapters.in.apiclient;

import java.util.List;

public class DrawDto {

    private boolean success;
    private List<CardDto> cards;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<CardDto> getCards() {
        return cards;
    }

    public void setCards(List<CardDto> cards) {
        this.cards = cards;
    }
}
