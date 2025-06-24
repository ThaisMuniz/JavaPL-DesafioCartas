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

    public JpaCartaEntity(String naipe, String valor) {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
