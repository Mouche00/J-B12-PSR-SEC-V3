package org.yc.keycloak.utils.records;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Coordinates {
    private double longitude;
    private double latitude;
}
