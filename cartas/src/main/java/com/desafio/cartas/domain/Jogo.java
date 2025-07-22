package com.desafio.cartas.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Jogo {

    private Long id;
    private Calendar dataHora;
    private int qtdJogadores;
    private int qtdCartasPorMao;
    private List<Mao> maos;
    private List<Jogador> vencedores;

    public Jogo(){}

    public Jogo(int qtdJogadores, int qtdCartasPorMao) {
        this.dataHora = Calendar.getInstance();
        this.qtdJogadores = qtdJogadores;
        this.qtdCartasPorMao = qtdCartasPorMao;
        this.maos = new ArrayList<>();
        this.vencedores = new ArrayList<>();
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

    public List<Mao> getMaos() {
        return maos;
    }

    public List<Jogador> getVencedores() {
        return vencedores;
    }

    public void setVencedores(List<Jogador> vencedores) {
        this.vencedores = vencedores;
    }

    public void setMaos(List<Mao> maos) {
        this.maos = maos;
    }
}
