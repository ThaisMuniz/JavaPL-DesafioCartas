package com.desafio.cartas.infrastructure.adapters.in.controller;

import com.desafio.cartas.application.usecases.JogoUseCases;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import com.desafio.cartas.infrastructure.exceptions.ParametrosDeJogoInvalidosException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Carteado API")
@RestController
@RequestMapping("/carteado")
public class CarteadoController {

    private final JogoUseCases jogoUseCases;

    public CarteadoController(JogoUseCases jogoUseCases) {
        this.jogoUseCases = jogoUseCases;
    }

    @Operation(summary = "Executa um jogo de carteado onde o jogador que tem a maior pontuação é o ganhador.",
            description = "Retorna o nome dos jogadores que ganharam. Em caso de empate, retorna o nome dos jogadores que empataram.", method = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogo executado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
            @ApiResponse(responseCode = "500", description = "Erro interno no sistema.")
    })
    @PostMapping(value = "/jogar/{qtdJogadores}/{qtdCartasPorMao}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<JogoResponseDto> jogar(@PathVariable int qtdJogadores,
                                          @PathVariable int qtdCartasPorMao) throws BaralhoClientException, ParametrosDeJogoInvalidosException {
        return ResponseEntity.ok(jogoUseCases.jogar(qtdJogadores, qtdCartasPorMao));
    }

}

