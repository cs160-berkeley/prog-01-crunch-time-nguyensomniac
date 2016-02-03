package com.example.lily.caloriecalculator;

/**
 * Created by lily on 2/3/16.
 */
public class Calculator {
    Calculator()    {
        quantity = -1;
        activity = null;
    }

    Calculator(Exercise e, int q)   {
        activity = e;
        quantity = q;
    }

    public void setQuantity(int q)   {
        quantity = q;
        System.out.println(activity);
        System.out.println(quantity);
    }

    public void setActivity(Exercise e) {
        activity = e;
    }

    public int calculateCalories()  {
        if (quantity < 0 || activity == null) {
            return 0;
        } else  {
            System.out.println(activity.getRatio());
            return quantityToCalories(quantity, activity);
        }
    }

    public static int caloriesToTime(int c, Exercise e) {
        return Math.round(c / e.getRatio());
    }

    public static int quantityToCalories(int q, Exercise e) {
        return Math.round(e.getRatio() * q);
    }

    private Exercise activity;
    private int quantity;
}
