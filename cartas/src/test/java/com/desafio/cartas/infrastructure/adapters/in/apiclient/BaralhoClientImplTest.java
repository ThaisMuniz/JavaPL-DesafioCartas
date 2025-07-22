package com.desafio.cartas.infrastructure.adapters.in.apiclient;

import com.desafio.cartas.domain.Carta;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BaralhoClientImplTest {

    @Mock
    private BaralhoFeignClient baralhoFeignClient;

    @InjectMocks
    private BaralhoClientImpl baralhoClientImpl;

    @Test
    void testObterCartasSucesso() throws BaralhoClientException {
        ResponseEntity<DeckDto> respondeDeckDto = new ResponseEntity<>(getDeckDto(), HttpStatus.OK);
        ResponseEntity<DrawDto> responseDrawDto = new ResponseEntity<>(getDrawDto(), HttpStatus.OK);

        when(baralhoFeignClient.getDeckId()).thenReturn(respondeDeckDto);
        when(baralhoFeignClient.getCardsFromDeck(any(), anyInt())).thenReturn(responseDrawDto);
        List<Carta> cartas = baralhoClientImpl.obterCartas(9);

        Assertions.assertEquals(9, cartas.size());
    }

    @Test
    void testRecuperarMaosObterBaralhoThrowsBaralhoCliException() {
        ResponseEntity<DeckDto> respondeDeckDto = new ResponseEntity<>(getDeckDto(), HttpStatus.BAD_REQUEST);

        when(baralhoFeignClient.getDeckId()).thenReturn(respondeDeckDto);
        BaralhoClientException thrown = assertThrows(BaralhoClientException.class,
                () -> baralhoClientImpl.obterCartas(9));

        assertFalse(thrown.getMessage().isEmpty());
    }

    @Test
    void testRecuperarMaosObterCartasThrowsBaralhoCliException() {
        ResponseEntity<DeckDto> respondeDeckDto = new ResponseEntity<>(getDeckDto(), HttpStatus.OK);
        ResponseEntity<DrawDto> responseDrawDto = new ResponseEntity<>(getDrawDto(), HttpStatus.BAD_REQUEST);

        when(baralhoFeignClient.getDeckId()).thenReturn(respondeDeckDto);
        when(baralhoFeignClient.getCardsFromDeck(any(), anyInt())).thenReturn(responseDrawDto);
        BaralhoClientException thrown = assertThrows(BaralhoClientException.class,
                () -> baralhoClientImpl.obterCartas(9));

        assertFalse(thrown.getMessage().isEmpty());
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
