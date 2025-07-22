package com.desafio.cartas.application.service;

import com.desafio.cartas.domain.Carta;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;

import java.util.List;

public interface BaralhoClient {

    List<Carta> obterCartas(int qtdCartas) throws BaralhoClientException;
}
