package com.desafio.cartas.infrastructure.adapters.out.entity;

import com.desafio.cartas.domain.Mao;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mao")
public class JpaMaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="jogo_id")
    private JpaJogoEntity jogo;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="jogador_id")
    private JpaJogadorEntity jogador;

    @ManyToMany(cascade=CascadeType.PERSIST)
    private List<JpaCartaEntity> cartas;

    @Column(nullable = false)
    private int valor;

    public JpaMaoEntity() {}

    public JpaMaoEntity(Mao mao) {
        this.jogador = new JpaJogadorEntity(mao.jogador().nome());
        this.cartas = mao.cartas().stream().map(carta -> new JpaCartaEntity(carta.naipe(), carta.valor())).toList();
        this.valor = mao.getValor();
    }

    public JpaJogadorEntity getJogador() {
        return jogador;
    }

    public void setJogador(JpaJogadorEntity jogador) {
        this.jogador = jogador;
    }

    public JpaJogoEntity getJogo() {
        return jogo;
    }

    public void setJogo(JpaJogoEntity jogo) {
        this.jogo = jogo;
    }

    public List<JpaCartaEntity> getCartas() {
        return cartas;
    }

    public void setCartas(List<JpaCartaEntity> cartas) {
        this.cartas = cartas;
    }

}
