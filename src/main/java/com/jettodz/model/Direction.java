package com.jettodz.model;

public enum Direction {
    UP(0),
    DOWN(2),
    LEFT(1),
    RIGHT(3);

    public final int key;

    Direction(int key) {
        this.key = key;
    }

}