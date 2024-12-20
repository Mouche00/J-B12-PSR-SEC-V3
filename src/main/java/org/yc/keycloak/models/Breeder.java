package org.yc.keycloak.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yc.keycloak.utils.records.Coordinates;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "breeders")
public class Breeder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;
    private String password;
    private String loftName;

    @Embedded
    private Coordinates loftCoordinates;
    private double finalScore;

    @OneToMany(mappedBy = "breeder", cascade = CascadeType.ALL)
    private List<Pigeon> pigeons;
}
