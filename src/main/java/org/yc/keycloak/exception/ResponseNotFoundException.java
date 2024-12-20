package org.yc.keycloak.exception;

public class ResponseNotFoundException extends RuntimeException {
    public ResponseNotFoundException(String message) {
        super(message);
    }
}
