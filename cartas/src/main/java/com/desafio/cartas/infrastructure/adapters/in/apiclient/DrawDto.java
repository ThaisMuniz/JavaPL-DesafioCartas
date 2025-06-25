package com.desafio.cartas.infrastructure.adapters.in.apiclient;

import java.util.List;

public record DrawDto (boolean success,
                      List<CardDto> cards) {

}
