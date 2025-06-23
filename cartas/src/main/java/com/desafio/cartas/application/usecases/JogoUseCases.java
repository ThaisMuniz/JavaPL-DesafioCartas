package com.desafio.cartas.application.usecases;

import com.desafio.cartas.domain.jogo.JogoResponseDTO;

public interface JogoUseCases {

    JogoResponseDTO jogar(int qtdJogadores, int qtdCartasPorMao);
}
