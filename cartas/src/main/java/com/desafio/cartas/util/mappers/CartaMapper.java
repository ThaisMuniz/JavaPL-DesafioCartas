package com.desafio.cartas.util.mappers;

import com.desafio.cartas.domain.Carta;
import com.desafio.cartas.infrastructure.adapters.out.entity.JpaCartaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartaMapper {

    Carta jpaCartaEntityToCarta(JpaCartaEntity jpaCartaEntity);

    JpaCartaEntity cartaToJpaCartaEntity(Carta carta);
}
