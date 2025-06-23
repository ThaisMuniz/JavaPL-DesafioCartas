package com.desafio.cartas.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mao")
public class Mao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="jogo_id")
    private Jogo jogo;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="jogador_id")
    private Jogador jogador;

    @ManyToMany(cascade=CascadeType.PERSIST)
    private List<Carta> cartas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }
}
