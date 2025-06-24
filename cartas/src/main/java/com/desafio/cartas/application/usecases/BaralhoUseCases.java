package com.desafio.cartas.application.usecases;

import com.desafio.cartas.domain.Jogo;
import com.desafio.cartas.domain.Mao;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;

import java.util.List;

public interface BaralhoUseCases {

    List<Mao> recuperarMaos(Jogo jogo) throws BaralhoClientException;
}
