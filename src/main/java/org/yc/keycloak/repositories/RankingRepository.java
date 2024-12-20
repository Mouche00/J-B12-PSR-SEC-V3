package org.yc.keycloak.repositories;

import org.yc.keycloak.models.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, String> {

    List<Ranking> findByRaceId(UUID race_id);
}
