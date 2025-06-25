package com.desafio.cartas.application.service;

import com.desafio.cartas.domain.Jogo;
import com.desafio.cartas.domain.Mao;
import com.desafio.cartas.infrastructure.adapters.in.apiclient.BaralhoFeignClient;
import com.desafio.cartas.infrastructure.adapters.in.apiclient.CardDto;
import com.desafio.cartas.infrastructure.adapters.in.apiclient.DeckDto;
import com.desafio.cartas.infrastructure.adapters.in.apiclient.DrawDto;
import com.desafio.cartas.infrastructure.exceptions.BaralhoClientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BaralhoServiceTest {

    @Mock
    private BaralhoFeignClient baralhoFeignClient;

    @InjectMocks
    private BaralhoService baralhoService;

    @Test
    void testRecuperarMaosSucesso() throws BaralhoClientException {
        ResponseEntity<DeckDto> respondeDeckDto = new ResponseEntity<>(getDeckDto(), HttpStatus.OK);
        ResponseEntity<DrawDto> responseDrawDto = new ResponseEntity<>(getDrawDto(), HttpStatus.OK);

        when(baralhoFeignClient.obterBaralhoID()).thenReturn(respondeDeckDto);
        when(baralhoFeignClient.obterCartas(any(), anyInt())).thenReturn(responseDrawDto);
        List<Mao> maos = baralhoService.recuperarMaos(getJogo());

        Assertions.assertEquals(3, maos.size());
    }

    @Test
    void testRecuperarMaosObterBaralhoThrowsBaralhoCliException() {
        ResponseEntity<DeckDto> respondeDeckDto = new ResponseEntity<>(getDeckDto(), HttpStatus.BAD_REQUEST);

        when(baralhoFeignClient.obterBaralhoID()).thenReturn(respondeDeckDto);
        BaralhoClientException thrown = assertThrows(BaralhoClientException.class,
                () -> baralhoService.recuperarMaos(getJogo()));

        assertFalse(thrown.getMessage().isEmpty());
    }

    @Test
    void testRecuperarMaosObterCartasThrowsBaralhoCliException() {
        ResponseEntity<DeckDto> respondeDeckDto = new ResponseEntity<>(getDeckDto(), HttpStatus.OK);
        ResponseEntity<DrawDto> responseDrawDto = new ResponseEntity<>(getDrawDto(), HttpStatus.BAD_REQUEST);

        when(baralhoFeignClient.obterBaralhoID()).thenReturn(respondeDeckDto);
        when(baralhoFeignClient.obterCartas(any(), anyInt())).thenReturn(responseDrawDto);
        BaralhoClientException thrown = assertThrows(BaralhoClientException.class,
                () -> baralhoService.recuperarMaos(getJogo()));

        assertFalse(thrown.getMessage().isEmpty());
    }

    private static Jogo getJogo() {
        int qtdJogadores = 3;
        int qtdCartasPorMao = 3;
        return new Jogo(qtdJogadores, qtdCartasPorMao);
    }

    private static DeckDto getDeckDto() {
        return new DeckDto("", "", "", "");
    }

    private static DrawDto getDrawDto() {
        List<CardDto> listCardDto = new ArrayList<>();
        listCardDto.add(new CardDto("5", "SPADES"));
        listCardDto.add(new CardDto("QUEEN", "SPADES"));
        listCardDto.add(new CardDto("KING", "SPADES"));
        listCardDto.add(new CardDto("10", "SPADES"));
        listCardDto.add(new CardDto("6", "SPADES"));
        listCardDto.add(new CardDto("2", "SPADES"));
        listCardDto.add(new CardDto("ACE", "SPADES"));
        listCardDto.add(new CardDto("9", "SPADES"));
        listCardDto.add(new CardDto("7", "SPADES"));
        return new DrawDto(true, listCardDto);
    }

}
