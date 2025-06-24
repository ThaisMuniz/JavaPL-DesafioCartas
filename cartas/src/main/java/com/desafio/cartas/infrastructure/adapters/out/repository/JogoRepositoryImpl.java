package com.desafio.cartas.infrastructure.adapters.out.repository;

import com.desafio.cartas.domain.Jogo;
import com.desafio.cartas.domain.JogoRepository;
import com.desafio.cartas.infrastructure.adapters.out.entity.JpaJogoEntity;
import org.springframework.stereotype.Repository;

@Repository
public class JogoRepositoryImpl implements JogoRepository {

    private final JpaJogoRepository jpaJogoRepository;

    public JogoRepositoryImpl(JpaJogoRepository jpaJogoRepository) {
        this.jpaJogoRepository = jpaJogoRepository;
    }

    @Override
    public void salvar(Jogo jogo) {
        jpaJogoRepository.save(new JpaJogoEntity(jogo));
    }
}
