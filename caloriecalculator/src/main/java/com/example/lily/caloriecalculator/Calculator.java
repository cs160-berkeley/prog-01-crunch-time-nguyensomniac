package com.example.lily.caloriecalculator;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lily on 2/3/16.
 */
public class Calculator extends Application {
    @Override
    public void onCreate()  {
        super.onCreate();
        quantity = -1;
        activity = null;
    }
//
//    Calculator()    {
//        quantity = -1;
//        activity = null;
//    }
//
//    Calculator(Exercise e, long q)   {
//        activity = e;
//        quantity = q;
//    }

    public Exercise getActivity()   {
        return activity;
    }

    public void setQuantity(long q)   {
        quantity = q;
    }

    public void setActivity(Exercise e) {
        activity = e;
    }

    public int calculateCalories()  {
        if (quantity < 0 || activity == null) {
            return 0;
        } else  {
            return quantityToCalories(quantity, activity);
        }
    }

    public static int caloriesToTime(int c, Exercise e) {
        return Math.round(c / e.getRatio());
    }

    public static int quantityToCalories(long q, Exercise e) {
        return Math.round(e.getRatio() * q);
    }

    public HashMap<Type, ArrayList<ExerciseCalculation>> otherActivities(int calories)   {
        if (activity != null) {
            ArrayList<Exercise> exercises = new ArrayList<Exercise>();
            exercises.addAll(Arrays.asList(Exercise.values()));
            exercises.remove(activity);
            HashMap<Type, ArrayList<ExerciseCalculation>> categorizedExercises = new HashMap<Type, ArrayList<ExerciseCalculation>>();
            for (Type t : Type.values())    {
                categorizedExercises.put(t, new ArrayList<ExerciseCalculation>());
            }
            for (int i = 0; i < exercises.size(); i++)    {
                Exercise e = exercises.get(i);
                categorizedExercises.get(e.getType()).add(new ExerciseCalculation(e, caloriesToTime(calories, e)));
            }
            return categorizedExercises;
        }
        return null;
    }

    private Exercise activity;
    private long quantity;
}
