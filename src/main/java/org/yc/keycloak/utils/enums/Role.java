package org.yc.keycloak.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    ORGANIZER("ROLE_ORGANIZER");

    private final String authority;
}
