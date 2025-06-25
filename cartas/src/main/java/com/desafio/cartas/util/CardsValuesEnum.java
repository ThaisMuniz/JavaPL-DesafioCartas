package com.desafio.cartas.util;

public enum CardsValuesEnum {

      ACE, JACK, QUEEN, KING;

      public static int getValor(String value) {
            if (value == null || value.isEmpty()) return 0;
            value = value.toUpperCase();
            return switch (value) {
                  case "ACE" -> 1;
                  case "JACK" -> 11;
                  case "QUEEN" -> 12;
                  case "KING" -> 13;
                  default -> {
                        try {
                              int intValue = Integer.parseInt(value);
                              if (intValue > 0 && intValue < 11) {
                                    yield intValue;
                              } else {
                                    throw new IllegalArgumentException("Valor de carta fora do intervalo esperado: " + value);
                              }
                        } catch (NumberFormatException e) {
                              throw new IllegalArgumentException("Valor de carta inválido: " + value);
                        }
                  }
            };
      }
}
