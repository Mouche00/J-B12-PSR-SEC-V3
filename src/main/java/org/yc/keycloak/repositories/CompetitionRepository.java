package org.yc.keycloak.repositories;

import org.yc.keycloak.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
}
