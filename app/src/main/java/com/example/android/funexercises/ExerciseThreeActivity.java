package com.example.android.funexercises;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class ExerciseThreeActivity extends AppCompatActivity {

    /**
     * Setting the global variables that we are going to need in all methods
     * int numberOfCards: user's choice for how many cards should there be in the deck
     * int numberOfCardsChanged: tracks if numberOfCards has been reduced by 1 as a result of having drawn a card
     * ArrayList String mydeck: the randomly generated deck
     * ArrayList String mydeckColours: the colours generated for mydeck
     * ArrayList String mydeckValues: the values (the numbers on the cards) generated for mydeck
     * String drawnCard: the card that has been put away as a result of the user having drawn a card from the deck
     */

    int numberOfCards = 0;
    int numberOfCardsChanged = numberOfCards;
    ArrayList<String> mydeck = new ArrayList<String>();
    ArrayList<String> mydeckShuffled = new ArrayList<String>();
    ArrayList<String> mydeckColours = new ArrayList<String>();
    ArrayList<String> mydeckValues = new ArrayList<String>();
    String drawnCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_three);
    }

    // creating a method that takes a view as its parameter, which will be pulled out as an int variable
    public void createDeck(View view) {
        // getting the number user typed in and setting it as the number of cards the deck should consist of, by modifying the numberOfCards variable
        EditText numberOfCardsView = findViewById(R.id.number_of_cards);
        String numberOfCardsString = numberOfCardsView.getText().toString();
        numberOfCards = Integer.parseInt(numberOfCardsString);

        // placing elements into the mydeckValue arraylist (defining the values/numbers of the cards)
        mydeckValues.add("Ace of");
        mydeckValues.add("2");
        mydeckValues.add("3");
        mydeckValues.add("4");
        mydeckValues.add("5");
        mydeckValues.add("6");
        mydeckValues.add("7");
        mydeckValues.add("8");
        mydeckValues.add("9");
        mydeckValues.add("10");
        mydeckValues.add("Jack of");
        mydeckValues.add("Queen of");
        mydeckValues.add("King of");

        // placing elements into the mydeckColours arraylist (defining the colours of the cards)
        mydeckColours.add("Clubs");
        mydeckColours.add("Diamonds");
        mydeckColours.add("Hearts");
        mydeckColours.add("Spades");

        int itemNumber = 0;

        /**
         * if the generated deck was chosen to contain 4 or less cards, they all should be of different colours,
         * therefore if a colour is generated for a card, that colour should be removed from mydeckColours for the next cards.
         * The values (numbers) of the cards will be randomly generated.
         */
        ArrayList<String> unusedColours = new ArrayList<String>();
        // initiates an arraylist of colours which starts as the original but we can gradually remove from it to avoid repetition of colours in the generated deck
        unusedColours = mydeckColours;
        if (numberOfCards < 5) {
            while (itemNumber < numberOfCards) {
                // creating a random number between 1 and 13 for value with each loop
                int randomNumber = (int) (Math.random() * 13 + 1);
                // creating a random number between 1 and 4 for colour with each loop
                int randomNumber2 = (int) (Math.random() * 4 + 1);
                mydeck.add(itemNumber, mydeckValues.get(randomNumber) + " " + unusedColours.get(randomNumber2));
                // removes the generated colour from the removed colours' arraylist so that we can't generate the same colour twice, but don't have to modify the original colours arrraylist
                unusedColours.remove(randomNumber2);
                itemNumber++;
            }
        }
        /**
         * The cards generated might contain repetitions (the same card generated twice or more).
         * TODO: find a way to prevent card repetitions!
         */
        if (numberOfCards > 4) {
            while (itemNumber < numberOfCards) {
                // creating a random number between 1 and 13 for value with each loop
                int randomNumber = (int) (Math.random() * 13 + 1);
                // creating a random number between 1 and 4 for colour with each loop
                int randomNumber2 = (int) (Math.random() * 4 + 1);
                mydeck.add(itemNumber, mydeckValues.get(randomNumber) + " " + mydeckColours.get(randomNumber2));
                itemNumber++;
            }
        }
        // resetting the unused colours arraylist for future deck generations
        unusedColours = mydeckColours;
    }

    public void drawCard(View view) {
        // if there has never been a drawing before:
        if (numberOfCardsChanged == numberOfCards) {
            // creating a random number between 0 and the number of cards we have to randomly arrive at the drawn card's index
            int randomNumber = (int) (Math.random() * numberOfCards);
            // making that random number the index of the deck to arrive at a card
            drawnCard = mydeck.get(randomNumber);
            // remove that drawn card from deck
            mydeck.remove(randomNumber);

            // finding the TextView to be modified and displaying the drawn card there (e.g. TextView will read "6 of Spades")
            TextView numberOfCardsView = (TextView) findViewById(R.id.drawn_card_displayed);
            String drawnCardString = numberOfCardsView.getText().toString();
            numberOfCardsView.setText(drawnCardString);
            // reduced numberOfCardsChanged by 1 to track that the number of cards in the deck is not the same anymore, there has been a drawing
            numberOfCardsChanged--;
        }
        if (numberOfCardsChanged < numberOfCards) {
            // there has never been a drawing before, numberOfCardsChanged = numberOfCards - 1
            // putting back the previously drawn card into the deck:
            mydeck.add(numberOfCardsChanged, drawnCard);
            //resetting the number of cards the deck consist of to the original (basically adding 1):
            numberOfCardsChanged = numberOfCards;
            // then repeating the procedure of the previous IF statement to draw a random card:
            // creating a random number between 0 and the number of cards we have to randomly arrive at the drawn card's index
            int randomNumber = (int) (Math.random() * numberOfCards);
            // making that random number the index of the deck to arrive at a card
            drawnCard = mydeck.get(randomNumber);
            // remove that drawn card from deck
            mydeck.remove(randomNumber);

            // finding the TextView to be modified and displaying the drawn card there (e.g. TextView will read "6 of Spades")
            TextView numberOfCardsView = (TextView) findViewById(R.id.drawn_card_displayed);
            numberOfCardsView.setText(drawnCard);
            // reduced numberOfCardsChanged by 1 to track that the number of cards in the deck is not the same anymore, there has been a drawing
            numberOfCardsChanged--;
        }
    }

    public void shuffleDeck(View view) {
        // if there was no drawing before, so the deck has all the cards it originally had (number determined by the user):
        if ((numberOfCardsChanged == numberOfCards)) {
            int itemNumber = 0;
            // originalNumberOfCards tracks how many cards are there at a given moment in the original deck
            // It starts with the number of the full original deck (number generated by the user).
            int originalNumberOfCards = numberOfCards;
            while (originalNumberOfCards > 0) {
                int randomNumber = (int) (Math.random() * originalNumberOfCards);
                // it draws a random card from the deck, adds it to the shuffled deck (mydeckShuffled), and removes it from the previous deck
                // it continues doing this until all the cards from the original deck are gone.
                mydeckShuffled.add(itemNumber, mydeck.get(randomNumber));
                mydeck.remove(randomNumber);
                originalNumberOfCards--;
            }
        }
        // if there WAS a drawing before, therefore we have a card placed aside from the deck
        else if ((numberOfCardsChanged < numberOfCards)) {
            // first let's place that card back to the deck, as its last card (on top)
            mydeck.add(numberOfCards, drawnCard);
            // let's clear the TextView that's still displaying the colour and value of this drawn card, & set back its strings.xml value:
            TextView numberOfCardsView = (TextView) findViewById(R.id.drawn_card_displayed);
            numberOfCardsView.setText(getResources().getString(R.string.card_drawn));
            // Then the same procedure can be applied as in the previous IF statement to modify the now empty shuffled deck arraylist,
            // and with this create a newly ordered deck):
            int itemNumber = 0;
            // originalNumberOfCards tracks how many cards are there at a given moment in the original deck
            // It starts with the number of the full original deck (number generated by the user).
            int originalNumberOfCards = numberOfCards;
            while (originalNumberOfCards > 0) {
                int randomNumber = (int) (Math.random() * originalNumberOfCards);
                // it draws a random card from the deck, adds it to the shuffled deck (mydeckShuffled), and removes it from the previous deck
                // it continues doing this until all the cards from the original deck are gone.
                mydeckShuffled.add(itemNumber, mydeck.get(randomNumber));
                mydeck.remove(randomNumber);
                originalNumberOfCards--;
            }
        }
    }
}
