package org.yc.keycloak.dtos;

import lombok.Builder;
import org.yc.keycloak.utils.records.Coordinates;

import java.util.List;

@Builder
public record BreederDTO(String id,
                         String username,
                         String password,
                         String loftName,
                         Coordinates loftCoordinates,
                         double finalScore,
                         List<PigeonDTO> pigeons) {
}
