package org.yc.keycloak.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalTime;

@Builder
public record RankingDTO(
        String id,
        @NotNull LocalTime startTime,
        @NotNull double distance,
        @NotNull double adjustedSpeed,
        @NotNull double score,
        @NotNull PigeonDTO pigeon,
        @NotNull RaceDTO race) {


    public RankingDTO withPigeonAndRaceIds(String pigeonId, String raceId) {
        return new RankingDTO(id, startTime, distance, adjustedSpeed, score,
                PigeonDTO.builder().id(pigeonId).build(),
                RaceDTO.builder().id(raceId).build());
    }
}
