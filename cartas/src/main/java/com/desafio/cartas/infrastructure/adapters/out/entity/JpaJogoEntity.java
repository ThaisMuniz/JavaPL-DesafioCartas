package com.desafio.cartas.infrastructure.adapters.out.entity;

import com.desafio.cartas.domain.Jogador;
import com.desafio.cartas.domain.Jogo;
import jakarta.persistence.*;

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
        this.vencedores = jogo.getVencedores().stream().map(Jogador::nome).toList().toString();
    }

}
