package com.example.lily.caloriecalculator;

/**
 * Created by lily on 1/31/16.
 */
public enum Type {
    UPPER("upper body"),
    CORE("core strength"),
    CARDIO("cardio");
    Type(String name)    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private final String name;
}
