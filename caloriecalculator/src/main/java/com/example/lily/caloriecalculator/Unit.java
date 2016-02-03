package com.example.lily.caloriecalculator;

/**
 * Created by lily on 1/31/16.
 */
public enum Unit {
    REPS("reps"), MINUTES("minutes");
    Unit(String name)   {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private final String name;
}
