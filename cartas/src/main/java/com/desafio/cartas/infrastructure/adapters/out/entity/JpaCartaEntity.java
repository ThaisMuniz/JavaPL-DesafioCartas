package com.desafio.cartas.infrastructure.adapters.out.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carta")
public class JpaCartaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naipe")
    private String naipe;

    @Column(name = "valor")
    private String valor;

    public JpaCartaEntity() {}

    public JpaCartaEntity(String naipe, String valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

}
