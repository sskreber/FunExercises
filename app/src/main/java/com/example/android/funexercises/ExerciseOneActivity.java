package com.example.android.funexercises;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExerciseOneActivity extends AppCompatActivity {

// Declares global variable: creates an array of integers with 10 element slots
    int[] list = new int[10];

    // Builds layout of the app and starts the activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_one);
    }

    // Method that creates a list of random numbers and display them as text views.
    public void createList(View v) {
        int itemNumber = 0;
        LinearLayout listView = (LinearLayout) findViewById(R.id.listView);
        while (itemNumber < 10) {
//             creating a random number between - 25 and 25.
            int randomNumber = (int) (Math.random() * 50 - 25);
            list[itemNumber] = randomNumber;
            TextView listNumberView = new TextView(this);
            listNumberView.setText(randomNumber);
            listView.addView(listNumberView);
            itemNumber++;
        }
    }

    // If the modulo of a given number is 1 (so it's an odd number), this method adds them up and calculates the sum's average.
    public void generateAverage(View listNumberView) {
        int itemNumber = 0;
        int average = 0;
        TextView averageView = (TextView) findViewById(R.id.averageView);
        int[] listOfOddNumbers = new int[10];
        while (itemNumber < 10) {
            if (list[itemNumber] % 2 == 1) {
                average = average + list[itemNumber];
                listOfOddNumbers[itemNumber] = list[itemNumber];
                itemNumber++;
            }
            average = average / listOfOddNumbers.length;
            averageView.setText(average);
        }
    }
}

