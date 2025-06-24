package com.desafio.cartas.infrastructure.adapters.out.entity;

import jakarta.persistence.*;

@Table
@Entity(name = "jogador")
public class JpaJogadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    public JpaJogadorEntity() {}

    public JpaJogadorEntity(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
