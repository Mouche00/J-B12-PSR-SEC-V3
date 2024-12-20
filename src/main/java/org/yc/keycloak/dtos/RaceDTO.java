package org.yc.keycloak.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.yc.keycloak.utils.records.Coordinates;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record RaceDTO(
        String id,
        @NotBlank String name,
        @NotNull Coordinates startCoordinates,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime startDate,
        @NotNull double targetDistance,
        double tolerance,
        double avgDistance,
        boolean autoAdj,
        @NotNull LocalDateTime closedAt,
//        @RefExists(collection = "competitions")
        List<RankingDTO> rankings ,
        CompetitionDTO competition){

    public RaceDTO withCompetitionId(String competitionId) {
        return new RaceDTO(id, name, startCoordinates, startDate, targetDistance, tolerance, avgDistance, autoAdj, closedAt, rankings,
                CompetitionDTO.builder().id(competitionId).build());
    }

    

}
