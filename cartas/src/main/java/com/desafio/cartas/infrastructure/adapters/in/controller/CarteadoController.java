package com.desafio.cartas.infrastructure.adapters.in.controller;

import com.desafio.cartas.application.usecases.JogoUseCases;
import com.desafio.cartas.application.service.JogoService;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carteado")
public class CarteadoController {

    private JogoUseCases jogoUseCases;

    public CarteadoController(JogoService jogoService) {
        this.jogoUseCases = jogoService;
    }

    @GetMapping("/jogar/{qtdJogadores}/{qtdCartasPorMao}")
    ResponseEntity<JogoResponseDto> jogar(@PathVariable int qtdJogadores,
                                          @PathVariable int qtdCartasPorMao) throws BaralhoClientException {

        return ResponseEntity.ok(jogoUseCases.jogar(qtdJogadores, qtdCartasPorMao));
    }
}
