package com.desafio.cartas.infrastructure.adapters.in.apiclient;

public class DeckDto {

    private String success;
    private String deck_id;
    private String remaining;
    private String shuffled;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getShuffled() {
        return shuffled;
    }

    public void setShuffled(String shuffled) {
        this.shuffled = shuffled;
    }
}
