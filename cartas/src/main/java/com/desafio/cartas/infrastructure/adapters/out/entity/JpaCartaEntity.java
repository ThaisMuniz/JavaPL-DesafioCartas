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

    @Column(name="deckId")
    private String deckId;

    public JpaCartaEntity() {}

}
