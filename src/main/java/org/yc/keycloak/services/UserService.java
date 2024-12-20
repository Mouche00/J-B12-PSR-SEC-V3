package org.yc.keycloak.services;

import org.yc.keycloak.dtos.UserDTO;
import org.yc.keycloak.models.User;

public interface UserService {
    User findByUsername(String username);
    UserDTO register(UserDTO user);
    UserDTO updateRole(String id, UserDTO user);
}
