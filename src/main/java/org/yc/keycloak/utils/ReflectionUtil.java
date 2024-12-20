package org.yc.keycloak.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReflectionUtil {

    public Object extractFieldByName(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return null;
        }
    }
}
