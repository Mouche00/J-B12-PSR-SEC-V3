package org.yc.keycloak.services;

import org.yc.keycloak.dtos.RankingCSVDTO;
import org.yc.keycloak.dtos.RankingDTO;
import org.yc.keycloak.models.Ranking;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RankingService {
    public RankingDTO addPigeonToRace(RankingDTO rankingDTO);
    public List<RankingDTO> saveAll(RankingCSVDTO rankingCSVDTO);
    List<RankingDTO> calculateAndRank(String raceId);
    List<Ranking>  convertCSV(MultipartFile file);
}
