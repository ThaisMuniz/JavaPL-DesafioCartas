package com.desafio.cartas.infrastructure.adapters.out.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

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
    private List<JpaMaoEntity> maos = new ArrayList<>();

    @Column
    private String vencedores;

    public JpaJogoEntity(){
        this.dataHora = Calendar.getInstance();
    }

    public Long getId() {
        return id;
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

    public void setVencedores(String vencedores) {
        this.vencedores = vencedores;
    }
}
