package com.desafio.cartas.infrastructure.adapters.in.controller;

import com.desafio.cartas.application.usecases.JogoUseCases;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarteadoController.class)
public class CarteadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JogoUseCases jogoUseCases;

    @Test
    void testJogarComQtdJogadoresInvalidaRetorna400BadRequest() throws Exception {

        mockMvc.perform(post("/carteado/jogar/{qtdJogadores}/{qtdCartasPorMao}", 1, 5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Erro de validação nos parâmetros do jogo."))
                .andExpect(jsonPath("$.details.qtdJogadores").value("Quantidade de jogadores (1) inválida!."));
    }

    @Test
    void testJogarComQtdCartasPorMaoInvalidaRetorna400() throws Exception {

        mockMvc.perform(post("/carteado/jogar/{qtdJogadores}/{qtdCartasPorMao}", 4, 0)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Erro de validação nos parâmetros do jogo."))
                .andExpect(jsonPath("$.details.qtdCartasPorMao").value("Quantidade de cartas por jogador (0) inválida!."));
    }

    @Test
    void testJogarThrowsBaralhoClientExceptionRetorna500() throws Exception {

        doThrow(new BaralhoClientException("Erro ao se comunicar com o serviço de baralho."))
                .when(jogoUseCases).jogar(anyInt(), anyInt());

        mockMvc.perform(post("/carteado/jogar/{qtdJogadores}/{qtdCartasPorMao}", 4, 5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("Erro ao se comunicar com o serviço de baralho."));
    }

}
