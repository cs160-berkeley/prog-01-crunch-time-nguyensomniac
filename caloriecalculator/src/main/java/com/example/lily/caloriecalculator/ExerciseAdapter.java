package com.example.lily.caloriecalculator;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lily on 2/5/16.
 */
public class ExerciseAdapter extends ArrayAdapter<ExerciseCalculation> {
    private final Context context;
    private ExerciseCalculation[] values;
    private int layoutId;
    public ExerciseAdapter(Context c, int layoutResourceId, ExerciseCalculation[] v) {
        super(c, layoutResourceId, v);
        context = c;
        layoutId = layoutResourceId;
        values = v;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)   {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(layoutId, parent, false);
        if (rowView != null)    {
            TextView exerciseName = (TextView)rowView.findViewById(R.id.exercise_name);
            TextView exerciseDuration = (TextView)rowView.findViewById(R.id.exercise_duration);
            ImageView exerciseIcon = (ImageView)rowView.findViewById(R.id.exercise_icon);
            exerciseName.setText(values[position].getExercise().toString());
            exerciseDuration.setText(Integer.toString(values[position].getDuration()) + " " + values[position].getExercise().getUnit().toString());
            exerciseIcon.setImageResource(context.getResources().getIdentifier(values[position].getExercise().name().toLowerCase(), "drawable", context.getPackageName()));
            return rowView;
        }
        return null;
    }
}
