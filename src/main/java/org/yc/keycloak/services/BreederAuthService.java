package org.yc.keycloak.services;

import org.yc.keycloak.models.Breeder;

public interface BreederAuthService {
    String register(Breeder breeder);
    boolean login(String username, String password);
//    List<BreederDTO> findAll();
}
