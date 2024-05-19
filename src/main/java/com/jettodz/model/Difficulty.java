package com.jettodz.model;

public enum Difficulty {
    EASY(100, "Easy"),
    MEDIUM(65, "Medium"),
    HARD(25, "Hard");

    public final Integer delay;
    public final String name;

    Difficulty(Integer delay, String name) {
        this.delay = delay;
        this.name = name;
    }

    public static Difficulty resolveFromIndex(int index) {
        return switch (index) {
            case 0 -> EASY;
            case 1 -> MEDIUM;
            case 2 -> HARD;
            default -> EASY;
        };
    }

}
