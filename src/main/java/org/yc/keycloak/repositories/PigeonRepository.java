package org.yc.keycloak.repositories;

import org.yc.keycloak.models.Pigeon;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PigeonRepository extends JpaRepository<Pigeon, String> {
}
