package com.desafio.cartas.util.mappers;

import com.desafio.cartas.domain.Mao;
import com.desafio.cartas.infrastructure.adapters.out.entity.JpaMaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { JogoMapper.class, JogadorMapper.class, CartaMapper.class })
public interface MaoMapper {

    Mao jpaMaoEntityToMao(JpaMaoEntity jpaMaoEntity);

    JpaMaoEntity maoToJpaMaoEntity(Mao mao);
}
