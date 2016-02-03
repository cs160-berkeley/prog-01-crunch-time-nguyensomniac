package com.example.lily.caloriecalculator;

/**
 * Created by lily on 1/31/16.
 */
public enum  Exercise {
    PUSHUP("Push-ups", (float)100/350, Type.UPPER, Unit.REPS),
    SITUP("Sit-ups", (float)100/200, Type.CORE, Unit.REPS),
    SQUAT("Squats", (float)100/225, Type.CORE, Unit.REPS),
    LEGLIFT("Leg Lifts", (float)100/25, Type.CORE, Unit.MINUTES),
    PLANK("Plank", (float)100/25, Type.CORE, Unit.MINUTES),
    JUMPINGJACK("Jumping Jacks", (float)100/10, Type.CARDIO, Unit.MINUTES),
    PULLUP("Pull-ups", (float)100/100, Type.UPPER, Unit.REPS),
    CYCLING("Cycling", (float)100/12, Type.CARDIO, Unit.MINUTES),
    WALKING("Walking", (float)100/20, Type.CARDIO, Unit.MINUTES),
    JOGGING("Jogging", (float)100/12, Type.CARDIO, Unit.MINUTES),
    SWIMMING("Swimming", (float)100/13, Type.CARDIO, Unit.MINUTES),
    STAIRCLIMBING("Stair Climbing", (float)100/15, Type.CARDIO, Unit.MINUTES);

    Exercise(String name, float calRatio, Type t, Unit u)    {
        this.name = name;
        this.calRatio = calRatio;
        this.type = t;
        this.unit = u;
    }

    @Override
    public String toString() {
        return name;
    }

    public float getRatio()    {
        return calRatio;
    }

    private final String name;
    private final float calRatio;
    private final Type type;
    private final Unit unit;
}
