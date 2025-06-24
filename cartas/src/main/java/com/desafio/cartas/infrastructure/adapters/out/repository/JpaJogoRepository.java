package com.desafio.cartas.infrastructure.adapters.out.repository;

import com.desafio.cartas.infrastructure.adapters.out.entity.JpaJogoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJogoRepository extends JpaRepository<JpaJogoEntity, Long> {
}
