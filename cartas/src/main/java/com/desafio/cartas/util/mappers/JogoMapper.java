package com.desafio.cartas.util.mappers;

import com.desafio.cartas.domain.Jogador;
import com.desafio.cartas.domain.Jogo;
import com.desafio.cartas.infrastructure.adapters.out.entity.JpaJogoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { MaoMapper.class })
public interface JogoMapper {

    @Mapping(target = "vencedores", source = "vencedores", qualifiedByName = "stringToJogadorList")
    Jogo jpaJogoEntityToJogo(JpaJogoEntity jpaJogoEntity);

    @Mapping(target = "vencedores", source = "vencedores", qualifiedByName = "jogadorListToString")
    JpaJogoEntity jogoToJpaEntity(Jogo jogo);

    @Named("stringToJogadorList")
    default List<Jogador> stringToJogadorList(String vencedores) {
        return Collections.emptyList();
    }

    @Named("jogadorListToString")
    default String jogadorListToString(List<Jogador> vencedores) {
        if (vencedores == null || vencedores.isEmpty()) {
            return null;
        }
        return vencedores.stream().map(Jogador::nome).collect(Collectors.joining(","));
    }
}
