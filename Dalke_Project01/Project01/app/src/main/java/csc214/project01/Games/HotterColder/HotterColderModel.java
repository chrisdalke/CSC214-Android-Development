////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 01
////////////////////////////////////////

package csc214.project01.Games.HotterColder;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import java.util.concurrent.ThreadLocalRandom;

////////////////////////////////////////
// Hotter-Colder Model
////////////////////////////////////////

public class HotterColderModel {

    //Singleton Code

    private static HotterColderModel INSTANCE = null;
    protected HotterColderModel() {}

    public static HotterColderModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new HotterColderModel();
        }
        return INSTANCE;
    }


    public int mCurrentPlayer;
    public int mCurrentGuess;
    public int[] mCurrentGuessNumber;
    public int mTargetNumber;
    public int mGuessDelta;
    public int mWinner = 0;

    public int getWinner() {
        return mWinner;
    }

    public void reset(){
        mCurrentGuessNumber = new int[2];
        mCurrentPlayer = 0;
        mWinner = 0;
        mTargetNumber = ThreadLocalRandom.current().nextInt(0,100) + 1;
        mCurrentGuessNumber[mCurrentPlayer] = 0;
        mCurrentGuess = 0;
        mGuessDelta = 0;
    }

    public void submitGuess(int guess){
        mCurrentGuess = guess;
        mCurrentGuessNumber[mCurrentPlayer]++;
        if (guess == mTargetNumber){
            //The current player successfully guessed.
            //If we are on player 1, move to next player
            //If we are on player 2, check game end state
            if (mCurrentPlayer == 0){
                mCurrentPlayer = 1;
                mCurrentGuessNumber[mCurrentPlayer] = 0;
                mCurrentGuess = 0;
                mGuessDelta = 0;
            } else {
                checkEndState();
            }
        }
    }

    //Calculates which player has won.
    private void checkEndState(){
        if (mCurrentGuessNumber[0] < mCurrentGuessNumber[1]){
            //Player 1 win
            mWinner = 1;
        } else if (mCurrentGuessNumber[1] < mCurrentGuessNumber[0]){
            //Player 2 win
            mWinner = 2;
        } else {
            //Tie
            mWinner = 3;
        }
    }

    public int getCurrentPlayer() {
        return mCurrentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.mCurrentPlayer = currentPlayer;
    }

    public int getCurrentPlayerNumGuesses(){
        return mCurrentGuessNumber[mCurrentPlayer];
    }

    public int getPlayerNumGuesses(int index){
        return mCurrentGuessNumber[index];
    }

    public String getCurrentPlayerGuessLabel(){
        if (mCurrentGuessNumber[mCurrentPlayer] == 0){
            return "Make a Guess...";
        }
        if (mCurrentGuess == mTargetNumber){
            return "Correct!";
        } else {
            int numDelta = Math.abs(mCurrentGuess - mTargetNumber);
            if (numDelta > 40){
                return "Absolute Zero!";
            } else if (numDelta > 30){
                return "Freezing";
            } else if (numDelta > 25){
                return "Colder";
            } else if (numDelta > 20){
                return "Cold";
            } else if (numDelta > 15){
                return "Warm";
            } else if (numDelta > 10){
                return "Warmer";
            } else if (numDelta > 5){
                return "Hot";
            } else if (numDelta > 0){
                return "On Fire";
            } else {
                return "???";
            }
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
