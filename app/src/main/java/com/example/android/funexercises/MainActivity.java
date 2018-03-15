package com.example.android.funexercises;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method that calls on the activity belonging to Exercise 1.

    public void startExerciseOne(View v){
        Intent exerciseOneIntent = new Intent(MainActivity.this, ExerciseOneActivity.class);
        // Start the new activity
        startActivity(exerciseOneIntent);
    }

    // Method that calls on the activity belonging to Exercise 2.

    public void startExerciseTwo(View v){
        Intent exerciseTwoIntent = new Intent(MainActivity.this, ExerciseTwoActivity.class);
        // Start the new activity
        startActivity(exerciseTwoIntent);
    }

    // Method that calls on the activity belonging to Exercise 3.

    public void startExerciseThree(View v){
        Intent exerciseThreeIntent = new Intent(MainActivity.this, ExerciseThreeActivity.class);
        // Start the new activity
        startActivity(exerciseThreeIntent);
    }

}
