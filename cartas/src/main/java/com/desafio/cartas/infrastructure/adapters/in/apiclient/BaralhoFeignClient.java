package com.desafio.cartas.infrastructure.adapters.in.apiclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "deckofcardsapi", url = "https://deckofcardsapi.com/api/deck")
public interface BaralhoFeignClient {

    @GetMapping("/new/shuffle/?deck_count=1")
    ResponseEntity<DeckDto> obterBaralhoID();

    @GetMapping("/{deck_id}/draw/?count={count}")
    ResponseEntity<DrawDto> obterCartas(@PathVariable String deck_id, @RequestParam int count);
}
