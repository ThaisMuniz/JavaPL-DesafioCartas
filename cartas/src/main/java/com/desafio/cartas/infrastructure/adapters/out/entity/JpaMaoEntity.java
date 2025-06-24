package com.desafio.cartas.infrastructure.adapters.out.entity;

import com.desafio.cartas.domain.Carta;
import com.desafio.cartas.domain.Jogador;
import com.desafio.cartas.domain.Jogo;
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
        this.jogador = new JpaJogadorEntity(mao.getJogador().getNome());
        this.cartas = mao.getCartas().stream().map(carta -> new JpaCartaEntity(carta.getNaipe(), carta.getValor())).toList();
        this.valor = mao.getValor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
