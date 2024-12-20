package org.yc.keycloak.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yc.keycloak.utils.csvConverters.LocalTimeConverter;
import org.yc.keycloak.utils.csvConverters.PigeonConverter;
import org.yc.keycloak.utils.csvConverters.RaceConverter;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rankings")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @CsvBindByName
    private UUID id;

    @CsvCustomBindByName(converter = LocalTimeConverter.class)
    private LocalTime startTime;
    @CsvBindByName
    private double distance;
    @CsvBindByName
    private double adjustedSpeed;
    @CsvBindByName
    private double score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pigeon_id")
    @CsvCustomBindByName(converter = PigeonConverter.class)
    private Pigeon pigeon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    @CsvCustomBindByName(converter = RaceConverter.class)
    private Race race;
}
