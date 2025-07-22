package com.desafio.cartas.infrastructure.adapters.in.controller;

import com.desafio.cartas.application.usecases.JogoUseCases;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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
    void testJogarSucesso() throws Exception {

        mockMvc.perform(post("/carteado/jogar/{qtdJogadores}/{qtdCartasPorMao}", 1, 5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testJogarNotFound() throws Exception {

        mockMvc.perform(post("/carteado/jogar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
