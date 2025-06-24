package com.desafio.cartas.infrastructure.adapters.out.entity;

import com.desafio.cartas.domain.Jogador;
import com.desafio.cartas.domain.Jogo;
import com.desafio.cartas.domain.Mao;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="jogo")
public class JpaJogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", nullable = false)
    private Calendar dataHora;

    @Column(name = "qtd_jogadores", nullable = false)
    private int qtdJogadores;

    @Column(name = "qtd_cartas_por_mao", nullable = false)
    private int qtdCartasPorMao;

    @OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JpaMaoEntity> maos;

    @Column
    private String vencedores;

    public JpaJogoEntity(){}

    public JpaJogoEntity(Jogo jogo) {
        this.dataHora = jogo.getDataHora();
        this.qtdJogadores = jogo.getQtdJogadores();
        this.qtdCartasPorMao = jogo.getQtdCartasPorMao();
        this.maos = jogo.getMaos().stream().map(JpaMaoEntity::new).toList();
        this.vencedores = jogo.getVencedores().stream().map(Jogador::getNome).toList().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public int getQtdJogadores() {
        return qtdJogadores;
    }

    public int getQtdCartasPorMao() {
        return qtdCartasPorMao;
    }

    public List<JpaMaoEntity> getMaos() {
        return maos;
    }

    public String getVencedores() {
        return vencedores;
    }
}
