package com.desafio.cartas.util.mappers;

import com.desafio.cartas.domain.Jogador;
import com.desafio.cartas.infrastructure.adapters.out.entity.JpaJogadorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JogadorMapper {

    Jogador jpaJogadorEntityToJogador(JpaJogadorEntity jpaJogadorEntity);

    JpaJogadorEntity jogadorToJpaEntity(Jogador jogador);
}
