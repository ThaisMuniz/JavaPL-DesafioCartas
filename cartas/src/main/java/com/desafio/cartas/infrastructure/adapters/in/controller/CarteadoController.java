package com.desafio.cartas.infrastructure.adapters.in.controller;

import com.desafio.cartas.application.usecases.JogoUseCases;
import com.desafio.cartas.application.service.JogoService;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Carteado API")
@RestController
@RequestMapping("/carteado")
public class CarteadoController {

    private JogoUseCases jogoUseCases;

    public CarteadoController(JogoService jogoService) {
        this.jogoUseCases = jogoService;
    }

    @Operation(summary = "Executa um jogo de carteado onde o jogador que tem a maior pontuação é o ganhador.",
            description = "Retorna o nome dos jogadores que ganharam. Em caso de empate, retorna o nome dos jogadores que empataram.",
            method = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogo executado com sucesso.")
    })
    @GetMapping(value = "/jogar/{qtdJogadores}/{qtdCartasPorMao}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JogoResponseDto> jogar(@PathVariable int qtdJogadores,
                                          @PathVariable int qtdCartasPorMao) throws BaralhoClientException {

        return ResponseEntity.ok(jogoUseCases.jogar(qtdJogadores, qtdCartasPorMao));
    }
}
