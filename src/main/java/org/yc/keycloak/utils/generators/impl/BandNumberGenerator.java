package org.yc.keycloak.utils.generators.impl;

import org.yc.keycloak.utils.enums.Gender;
import org.yc.keycloak.utils.generators.Generator;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class BandNumberGenerator implements Generator<Object, String> {

    private static final String FEMALE_STRING = "F";
    private static final String MALE_STRING = "M";
    private static final SecureRandom random = new SecureRandom();

    @Override
    public String generate(Object... payload) {
        if (payload.length < 2) {
            throw new IllegalArgumentException("Gender and birth year must be provided");
        } else if (!(payload[0] instanceof Gender)) {
            throw new IllegalArgumentException("Gender is invalid");
        } else if (!(payload[1] instanceof String)) {
            throw new IllegalArgumentException("Birth year is invalid");
        }

        String gender = parseGender((Gender) payload[0]);
        String birthYear = parseBirthYear((String) payload[1]);

        return String.format("%s-%s-%s", gender, randomString(), birthYear);
    }

    private String parseGender(Gender genderStr) {
        return Gender.FEMALE.equals(genderStr) ? FEMALE_STRING : MALE_STRING;
    }

    private String parseBirthYear(String birthYear) {
        return birthYear.substring(2);
    }

    // Generates a random string of 12 characters
    private String randomString() {
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        return String.format("%012x", new java.math.BigInteger(1, bytes)).toUpperCase();
    }
}