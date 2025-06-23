package com.desafio.cartas.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="jogo")
public class Jogo {

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
    private List<Mao> maos;

    @ManyToMany
    private List<Jogador> vencedores;

    public Jogo(int qtdJogadores, int qtdCartasPorMao) {
        this.dataHora = Calendar.getInstance();
        this.qtdJogadores = qtdJogadores;
        this.qtdCartasPorMao = qtdCartasPorMao;
        this.maos = new ArrayList<>();
        this.vencedores = new ArrayList<>();
    }

    public Jogo() {

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

    public List<Mao> getMaos() {
        return maos;
    }

    public void setMaos(List<Mao> maos) {
        this.maos = maos;
    }

    public List<Jogador> getVencedores() {
        return vencedores;
    }

    public void setVencedores(List<Jogador> vencedores) {
        this.vencedores = vencedores;
    }
}
