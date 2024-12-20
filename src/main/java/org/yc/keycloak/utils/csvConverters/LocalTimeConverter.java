package org.yc.keycloak.utils.csvConverters;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeConverter extends AbstractBeanField<LocalTime, String> {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    protected LocalTime convert(String value) {
        return LocalTime.parse(value, TIME_FORMATTER);
    }
}