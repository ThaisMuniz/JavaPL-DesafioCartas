package com.desafio.cartas.domain.jogo;

import com.desafio.cartas.model.entity.Jogador;

import java.util.List;

public class JogoResponseDTO{

    private List<Jogador> vencedores;

    public JogoResponseDTO(List<Jogador> vencedores) {
        this.vencedores = vencedores;
    }

    public List<Jogador> getVencedores() {
        return vencedores;
    }

    public void setVencedores(List<Jogador> vencedores) {
        this.vencedores = vencedores;
    }
}
