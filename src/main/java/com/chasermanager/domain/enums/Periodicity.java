package com.chasermanager.domain.enums;

public enum Periodicity {
    TWO_MIN, FIVE_MIN, FIFTEEN_MIN, HALF_HOUR, ONE_HOUR, TWO_HOUR;

    public static long mapToNumbers(Periodicity periodicity) {
        return switch (periodicity) {
            case TWO_MIN -> 60000 * 2;
            case FIVE_MIN -> 60000 * 5;
            case FIFTEEN_MIN -> 60000 * 15;
            case HALF_HOUR -> 60000 * 30;
            case ONE_HOUR -> 60000 * 60;
            default -> 60000 * 60 * 2;
        };
    }

    public static Enum<Periodicity> mapToWords(long periodicity) {
        return switch ((int) periodicity) {
            case 60000 * 2 -> TWO_MIN;
            case 60000 * 5 -> FIVE_MIN;
            case 60000 * 15 -> FIFTEEN_MIN;
            case 60000 * 30 -> HALF_HOUR;
            case 60000 * 60 -> ONE_HOUR;
            default -> TWO_HOUR;
        };
    }
}
