package com.desafio.cartas.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartaTests {

    @Test
    void testGetValorInteiroSucesso () {

        Carta carta = new Carta("", "7");
        int valorValido = carta.getValorInteiro();

        Assertions.assertEquals(7, valorValido);
    }

    @Test
    void testGetValorInteiroThrowsIllArgException () {

        Carta carta = new Carta("", "7C");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, carta::getValorInteiro);

        assertFalse(thrown.getMessage().isEmpty());
    }
}
