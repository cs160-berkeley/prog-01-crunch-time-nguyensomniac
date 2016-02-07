package com.example.lily.caloriecalculator;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Calculator c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = (Calculator)getApplicationContext();
        /* Set up custom fonts */
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AvenirNext-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /* Initialize Calligraphy */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setConversionHeight() {
        final RelativeLayout container = (RelativeLayout)findViewById(R.id.container_main);
        container.post(new Runnable() {
            @Override
            public void run() {
                final LinearLayout left = (LinearLayout) findViewById(R.id.conversion_left);
                final LinearLayout right = (LinearLayout) findViewById(R.id.conversion_right);
                LinearLayout max = (left.getHeight() > right.getHeight()) ? left : right;
                LinearLayout min = (left == max) ? right : left;
                LayoutParams p = min.getLayoutParams();
                p.height = max.getHeight();
            }
        });
    }

    public void addActivities() {
        ArrayAdapter<String> exercises = new ArrayAdapter<String>(this, R.layout.spinner_title);
        for (Exercise e : Exercise.values())    {
            exercises.add(e.toString());
        }
        exercises.notifyDataSetChanged();
        exercises.setDropDownViewResource(R.layout.spinner_item);
        Spinner s = (Spinner) findViewById(R.id.activity_list);
        s.setAdapter(exercises);
    }

    public void updateCalories()    {
        TextView calorieView = (TextView)findViewById(R.id.burned);
        calorieView.setText(Integer.toString(c.calculateCalories()));
        TextView minuteText = (TextView)findViewById(R.id.minutes_text);
        TextView repsText = (TextView)findViewById(R.id.reps_text);
        if (c.getActivity() == null)  {
            repsText.setTextColor(getResources().getColor(R.color.lighter_gray, getTheme()));
            minuteText.setTextColor(getResources().getColor(R.color.lighter_gray, getTheme()));
        }
        else if (c.getActivity().getUnit() == Unit.MINUTES)  {
            repsText.setTextColor(getResources().getColor(R.color.lighter_gray, getTheme()));
            minuteText.setTextColor(getResources().getColor(R.color.text, getTheme()));
        }
        else if (c.getActivity().getUnit() == Unit.REPS)    {
            repsText.setTextColor(getResources().getColor(R.color.text, getTheme()));
            minuteText.setTextColor(getResources().getColor(R.color.lighter_gray, getTheme()));
        }
        else    {
            repsText.setTextColor(getResources().getColor(R.color.lighter_gray, getTheme()));
            minuteText.setTextColor(getResources().getColor(R.color.lighter_gray, getTheme()));
        }
        ImageView activeIcon = (ImageView)findViewById(R.id.active_icon);
        if (c.getActivity() != null) {
            activeIcon.setImageResource(getResources().getIdentifier(c.getActivity().name().toLowerCase(), "drawable", getPackageName()));
        }
        drawRest();
    }

    public void drawRest()   {
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout list = (LinearLayout)findViewById(R.id.exercise_list);
        list.removeAllViews();
        HashMap<Type, ArrayList<ExerciseCalculation>> rest = c.otherActivities(c.calculateCalories());
        if (rest != null)   {
            Type[] types = rest.keySet().toArray(new Type[rest.keySet().size()]);

            for (int i = 0; i < types.length; i++)  {
                View categoryView = inflater.inflate(R.layout.exercise_category, list, false);
                TextView categoryName = (TextView)categoryView.findViewById(R.id.category);
                categoryName.setText(types[i].toString());
                int categoryColor = getResources().getIdentifier(types[i].name().toLowerCase(), "color", getPackageName());
                categoryName.setTextColor(
                        getResources().getColor(categoryColor, getTheme()));
                LinearLayout categoryList = (LinearLayout)categoryView.findViewById(R.id.exercise_wrapper);
                categoryList.removeAllViews();
                ExerciseCalculation[] exerciseValues = new ExerciseCalculation[rest.get(types[i]).size()];
                exerciseValues = rest.get(types[i]).toArray(exerciseValues);
                ExerciseAdapter e = new ExerciseAdapter(getBaseContext(), R.layout.exercise_list, exerciseValues);
                for (int j = 0; j < e.getCount(); j++)  {
                    categoryList.addView(e.getView(j, null, categoryList));
                }
                list.addView(categoryView);
            }
        }
    }

    public void addListeners()  {
        final EditText quantityInput = (EditText)findViewById(R.id.activity_quantity);
        Spinner activityInput = (Spinner) findViewById(R.id.activity_list);
        quantityInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String quantityString = quantityInput.getText().toString();
                try {
                    if (!quantityString.equals(""))   {
                        if (Long.parseLong(quantityString) > 9999)  {
                            quantityInput.setText(quantityString.substring(0, quantityString.length() - 1));
                        }
                        c.setQuantity(Long.parseLong(quantityString));
                    } else  {
                        c.setQuantity(-1);
                    }
                    updateCalories();
                } catch (NumberFormatException e) {
                    quantityInput.setText(quantityString.substring(0, quantityString.length() - 1));
                }
            }
        });
        activityInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c.setActivity(Exercise.values()[position]);
                updateCalories();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        addActivities();
        addListeners();
        updateCalories();
        setConversionHeight();
        drawRest();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.lily.caloriecalculator/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.lily.caloriecalculator/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
