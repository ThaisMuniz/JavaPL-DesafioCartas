package com.desafio.cartas.controller;

import com.desafio.cartas.application.usecases.JogoUseCases;
import com.desafio.cartas.domain.jogo.JogoResponseDTO;
import com.desafio.cartas.application.service.JogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    private JogoUseCases jogoUseCases;

    public JogoController(JogoService jogoService) {
        this.jogoUseCases = jogoService;
    }

    @PostMapping("/{qtdJogadores}/{qtdCartasPorMao}")
    ResponseEntity<JogoResponseDTO> jogar(@PathVariable int qtdJogadores,
                                         @PathVariable int qtdCartasPorMao) {

        return ResponseEntity.ok(jogoUseCases.jogar(qtdJogadores, qtdCartasPorMao));
    }
}
