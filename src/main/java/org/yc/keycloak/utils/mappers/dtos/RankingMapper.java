package org.yc.keycloak.utils.mappers.dtos;

import org.yc.keycloak.dtos.RankingDTO;
import org.yc.keycloak.models.Ranking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RankingMapper {
    RankingMapper INSTANCE = Mappers.getMapper(RankingMapper.class);

    RankingDTO toDTO(Ranking ranking);

    @Mappings({
            @Mapping(target = "id", ignore = true), // Let the ID be auto-generated or set by the repository
//            @Mapping(target = "pigeon", source = "pigeon"), // Map Pigeon directly
//            @Mapping(target = "race", source = "race")    // Map Race directly
    })
    Ranking toRanking(RankingDTO rankingDTO);

    List<Ranking> toRankings(List<RankingDTO> rankingDTOs);
    List<RankingDTO> toRankingDTOs(List<Ranking> rankings);
}
