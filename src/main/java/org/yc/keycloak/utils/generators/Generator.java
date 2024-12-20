package org.yc.keycloak.utils.generators;

public interface Generator<T, R> {
    R generate (T ...payload);
}
