package com.desafio.cartas.infrastructure.adapters.out.repository;

import com.desafio.cartas.domain.Jogo;
import com.desafio.cartas.domain.JogoRepository;
import com.desafio.cartas.infrastructure.adapters.out.entity.JpaJogoEntity;
import com.desafio.cartas.infrastructure.adapters.out.entity.JpaMaoEntity;
import com.desafio.cartas.util.mappers.JogoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JogoRepositoryImpl implements JogoRepository {

    private final JpaJogoRepository jpaJogoRepository;
    private final JogoMapper jogoMapper;

    public JogoRepositoryImpl(JpaJogoRepository jpaJogoRepository, JogoMapper jogoMapper) {
        this.jpaJogoRepository = jpaJogoRepository;
        this.jogoMapper = jogoMapper;
    }

    @Override
    public void salvar(Jogo jogo) {
        JpaJogoEntity jpaJogoEntity = jogoMapper.jogoToJpaEntity(jogo);
        if (jpaJogoEntity.getMaos() != null) {
            for (JpaMaoEntity maoEntity : jpaJogoEntity.getMaos()) {
                maoEntity.setJogo(jpaJogoEntity);
            }
        }
        jpaJogoRepository.save(jpaJogoEntity);
    }
}
