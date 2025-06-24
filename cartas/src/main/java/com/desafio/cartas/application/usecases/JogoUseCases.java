package com.desafio.cartas.application.usecases;

import com.desafio.cartas.infrastructure.adapters.in.controller.JogoResponseDto;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;

public interface JogoUseCases {

    JogoResponseDto jogar(int qtdJogadores, int qtdCartasPorMao) throws BaralhoClientException;
}
