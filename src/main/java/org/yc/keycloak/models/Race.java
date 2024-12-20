package org.yc.keycloak.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yc.keycloak.utils.records.Coordinates;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Embedded
    private Coordinates startCoordinates;

    private LocalDateTime startDate;
    private double targetDistance;
    private double tolerance;
    private double avgDistance;
    private LocalDateTime closedAt;
    private boolean autoAdj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private List<Ranking> rankings;
}
