package com.example.lily.caloriecalculator;

import android.app.ListActivity;

/**
 * Created by lily on 2/5/16.
 */
public class ExerciseCalculation {
    private Exercise e;
    private int duration;
    public ExerciseCalculation(Exercise e, int duration) {
        this.e = e;
        this.duration = duration;
    }
    public Exercise getExercise()    {
        return e;
    }
    public int getDuration()    {
        return duration;
    }
}
