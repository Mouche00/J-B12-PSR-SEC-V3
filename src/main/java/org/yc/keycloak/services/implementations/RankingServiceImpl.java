package org.yc.keycloak.services.implementations;

import com.opencsv.bean.CsvToBeanBuilder;
import org.yc.keycloak.dtos.RankingCSVDTO;
import org.yc.keycloak.dtos.RankingDTO;
import org.yc.keycloak.events.RaceClosedEvent;
import org.yc.keycloak.models.Ranking;
import org.yc.keycloak.repositories.RankingRepository;
import org.yc.keycloak.services.PigeonService;
import org.yc.keycloak.services.RankingService;
import org.yc.keycloak.utils.mappers.dtos.PigeonMapper;
import org.yc.keycloak.utils.mappers.dtos.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final RankingMapper rankingMapper;
    private final PigeonMapper pigeonMapper;
    private final PigeonService pigeonService;

    @Autowired
    public RankingServiceImpl(PigeonMapper pigeonMapper, RankingRepository rankingRepository,RankingMapper rankingMapper,PigeonService pigeonService) {
        this.rankingRepository = rankingRepository;
        this.rankingMapper = rankingMapper;
        this.pigeonService = pigeonService;
        this.pigeonMapper = pigeonMapper;
    }

    public RankingDTO addPigeonToRace(RankingDTO rankingDTO) {
        Ranking ranking = rankingMapper.toRanking(rankingDTO);
        Ranking savedRanking = rankingRepository.save(ranking);
        return rankingMapper.toDTO(savedRanking);
    }


    @Override
    public List<RankingDTO> saveAll(RankingCSVDTO rankingCSVDTO) {
        List<Ranking> rankings = convertCSV(rankingCSVDTO.csv());
        rankings.forEach(ranking -> {
            if (ranking.getPigeon() != null) {
                pigeonService.save(pigeonMapper.toDTO(ranking.getPigeon()));
            }
        });
        return rankings.stream()
                .map(rankingMapper::toDTO)
                .map(this::addPigeonToRace)
                .toList();
    }

    @EventListener
    public List<RankingDTO> handleRaceClosedEvent(RaceClosedEvent raceClosedEvent) {
        String raceId = raceClosedEvent.getRace().id();
        if(raceId != null) {
            return calculateAndRank(raceId);
        }
        return Collections.emptyList();
    }

    @Override
    public List<RankingDTO> calculateAndRank(String raceId) {
        return rankingMapper.toRankingDTOs(rankingRepository.findByRaceId(UUID.fromString(raceId)));
    }


    @Override
    public List<Ranking> convertCSV(MultipartFile csv){
//        try {
//            List<Ranking> rankings = new CsvToBeanBuilder<Ranking>(new FileReader(csvFile)).withType(Ranking.class).build().parse();
//            return rankings;
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        try (InputStreamReader reader = new InputStreamReader(csv.getInputStream())) {
            return new CsvToBeanBuilder<Ranking>(reader)
                    .withType(Ranking.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException("Failed to process CSV file", e);
        }
    }












}
