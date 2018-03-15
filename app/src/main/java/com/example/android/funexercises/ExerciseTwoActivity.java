package com.example.android.funexercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ExerciseTwoActivity extends AppCompatActivity {

    // initiates global variable that'll be needed throughout the activity
    int shiftByThisNumber = 0;

    // Builds layout of the app and starts the activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_two);
    }

    public void changeText(View v) {
        // getting the content of the EditText view input by the user and pulling out its content into a String variable
        EditText inputText = findViewById(R.id.text);
        String inputTextString = inputText.getText().toString();
        // getting the length (the number of characters) of this String input by the user
        int textLength = inputTextString.length();
        // creating an array of characters into which the transformed characters of the original String (later char array) will be added into
        Character[] changedText = new Character[textLength];
        // creating an array of characters that is the same length as the input word itself,
        // to save the lowercased version of the input word in char form into.
        Character[] inputTextChar = new Character[textLength];
        int itemNumber = 0;
        // transforming the String text (from the EditView got from the user) into an array of characters, all in lower case:
        while (itemNumber < textLength) {
            inputTextChar[itemNumber] = inputTextString.charAt(itemNumber);
            inputTextChar[itemNumber] = Character.toLowerCase(inputTextChar[itemNumber]);
            itemNumber++;
        }
        Character firstCharOrigWord = inputTextChar[0];
        Character[] alphabetTwice = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int alphabetTwiceLength = alphabetTwice.length;
        int alphabetLength = alphabetTwiceLength / 2;
        itemNumber = 0;

        // finding the index of the letter of the alphabet the input word starts with
        // by comparing the first character of the input word to each letter of the alphabet on a character array.
        // If it is the same, that index is grabbed. That'll be the number we'll shift each letter by to the right in the alphabet later.
        while (itemNumber < alphabetLength) {
            if (firstCharOrigWord.equals(alphabetTwice[itemNumber])) {
                shiftByThisNumber = itemNumber + 1;
            } else itemNumber++;
        }

        // now changing all the other letter-based characters
        itemNumber = 0;
        int alphabetItemNumber = 0;
        // it iterates through all the characters of the input word, and compares each to characters of the alphabet
        // if checks if it is an element of the alphabet (a letter) or not (special characters)
        // if it IS a letter, if shifts it by the number the shiftByThisNumber variable gave us (from the index of the first character),
        // and adds it to the changedText character array.
        // if it ISN'T a letter, it adds it to the changedText character array without inflicting any change.
        // it goes on until it has dealt with all the characters of the input word.
        while (itemNumber < textLength) {
            // if the letter matches the first letter of the alphabet, it gets modified by shifting by a given number (shiftByThisNumber) in the alphabet

            if (inputTextChar[itemNumber].equals(alphabetTwice[alphabetItemNumber])) {
                inputTextChar[itemNumber] = alphabetTwice[alphabetItemNumber + shiftByThisNumber];
                // we update character array (the transformed version of the word) with this changed character
                changedText[itemNumber] = inputTextChar[itemNumber];
                // it can move onto checking the next character of the word.
                itemNumber++;
            }
            // if the letter doesn't match the given letter of the alphabet, it keeps checking the next letters of the alphabet
            else {
                // if we haven't checked every letter of the alphabet yet for a match for this character,
                // it increases alphabetItemNumber, goes back into checking the while loop's if part, & keeps searching for a match
                // // to be able to modify the input word's character accordingly.
                if (alphabetItemNumber < alphabetLength) {
                    alphabetItemNumber++;
                }
                // if we have already checked the whole alphabet, it means we are dealing with a special character,
                // so let's add this special character to the transformed version of the word without changing it
                else {
                    changedText[itemNumber] = inputTextChar[itemNumber];
                    // after adding this special character, we can move onto checking the next one of the lowercased version of the input word against the alphabet
                    itemNumber++;
                }
            }
        }

        // finding the textview that's to display the transformed word
        TextView changedTextView = (TextView) findViewById(R.id.changedTextView);
        // converting the Char array into a String
        String changedTextString = String.valueOf(changedText);
        //change the "changed_text" textview into the transformed text
        changedTextView.setText(changedTextString);
    }
}
