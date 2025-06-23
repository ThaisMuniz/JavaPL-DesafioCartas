package com.desafio.cartas.model.repository;

import com.desafio.cartas.model.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
}
