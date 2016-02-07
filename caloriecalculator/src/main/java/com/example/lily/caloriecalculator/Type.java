package com.example.lily.caloriecalculator;

/**
 * Created by lily on 1/31/16.
 */
public enum Type {
    UPPER("Upper Body"),
    CORE("Core Strength"),
    CARDIO("Cardio");
    Type(String name)    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private final String name;
}
