package org.yc.keycloak.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yc.keycloak.utils.enums.Gender;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pigeons")
public class Pigeon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String bandNumber;

    @NotEmpty
    private Gender gender;

    @NotBlank
    private String birthYear;
    private String color;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    @OneToMany(mappedBy = "pigeon", cascade = CascadeType.ALL)
    private List<Ranking> rankings;
}
