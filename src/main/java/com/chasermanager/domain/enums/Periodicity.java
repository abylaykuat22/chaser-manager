package com.chasermanager.domain.enums;

public enum Periodicity {
    TWO_MIN, FIVE_MIN, FIFTEEN_MIN, HALF_HOUR, ONE_HOUR, TWO_HOUR;

    public static long calculate(Periodicity periodicity) {
        return switch (periodicity) {
            case TWO_MIN -> 60000*2;
            case FIVE_MIN -> 60000*5;
            case FIFTEEN_MIN -> 60000*15;
            case HALF_HOUR -> 60000*30;
            case ONE_HOUR -> 60000*60;
            default -> 60000*60*2;
        };
    }
}
