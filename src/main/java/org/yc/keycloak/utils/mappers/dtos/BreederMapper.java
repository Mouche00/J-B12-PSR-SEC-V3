package org.yc.keycloak.utils.mappers.dtos;

import org.yc.keycloak.dtos.BreederDTO;
import org.yc.keycloak.models.Breeder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BreederMapper {
    BreederMapper INSTANCE = Mappers.getMapper(BreederMapper.class);

    // ResponseDTO
    @Mapping(target = "password", ignore = true)
    BreederDTO toDTO(Breeder breeder);

    // RequestDTO
    @Mapping(target = "id", ignore = true)
    Breeder toBreeder(BreederDTO breederDTO);
}
