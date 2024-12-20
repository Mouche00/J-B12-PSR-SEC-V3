package org.yc.keycloak.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CompetitionDTO(
        String id,
        @NotBlank String name,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime closedAt,
        List<RaceDTO> races)
{

}
