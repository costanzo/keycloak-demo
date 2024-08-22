package org.acme.utils;

import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();
    public static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int index = RANDOM.nextInt(ALPHA_NUMERIC_STRING.length());
            sb.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return sb.toString();
    }
}
