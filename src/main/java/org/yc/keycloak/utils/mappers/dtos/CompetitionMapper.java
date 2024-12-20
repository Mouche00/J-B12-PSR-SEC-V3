package org.yc.keycloak.utils.mappers.dtos;

import org.yc.keycloak.dtos.CompetitionDTO;
import org.yc.keycloak.models.Competition;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    CompetitionDTO toDTO(Competition competition);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Competition toCompetition(CompetitionDTO competitionDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCompetitionFromDto(CompetitionDTO dto, @MappingTarget Competition entity);
}
