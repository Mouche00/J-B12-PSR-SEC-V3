package org.yc.keycloak.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.yc.keycloak.utils.enums.Gender;

@Builder
public record PigeonDTO(String id,
                        String bandNumber,
                        Gender gender,
                        @NotBlank String birthYear,
                        String color,
                        String image,
//                        @RefExists(collection = "breeders")
                        BreederDTO breeder) {

    public PigeonDTO withBreederId(String breederId) {
        return new PigeonDTO(id, bandNumber, gender, birthYear, color, image, BreederDTO.builder().id(breederId).build());
    }
}
