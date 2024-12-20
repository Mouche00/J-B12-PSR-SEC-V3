package org.yc.keycloak.repositories;

import org.yc.keycloak.models.Breeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreederRepository extends JpaRepository<Breeder, String> {
     Breeder findByUsername(String username);
}
