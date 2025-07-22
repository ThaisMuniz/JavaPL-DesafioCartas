package com.desafio.cartas.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MaoTests {

    @Test
    void testGetValorSucesso() {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("HEARTS","ACE"));
        cartas.add(new Carta("SPADES","10"));
        cartas.add(new Carta("CLUBS","8"));
        Mao mao = new Mao (null, cartas);

        Assertions.assertEquals(19,  mao.getValor());
    }

    @Test
    void testGetValorThrowsIllArgException() {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("HEARTS","A"));
        cartas.add(new Carta("SPADES","10"));
        cartas.add(new Carta("CLUBS","8"));
        Mao mao = new Mao (null, cartas);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, mao::getValor);

        assertFalse(thrown.getMessage().isEmpty());
    }

    @Test
    void testGetValorVazioSucesso() {
        Mao mao = new Mao (null, new ArrayList<>());

        Assertions.assertEquals(0,  mao.getValor());
    }
}
