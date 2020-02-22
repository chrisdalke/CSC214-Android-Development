////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 01
////////////////////////////////////////

package csc214.project01.Games.Hangman;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import csc214.project01.Games.Connect4.Connect4Model;

////////////////////////////////////////
// Hangman Model
////////////////////////////////////////

public class HangmanModel {

    // A list of random test words that I have chosen from a "common words" list
    public static final String[] WORD_LIST = {
            "advice","border","brick","coach","damage","fireplace",
            "mission","hunter","memory","police","satellites",
            "tree","instant","mathematics","science"};

    private static HangmanModel INSTANCE = null;

    public static HangmanModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new HangmanModel();
        }
        return INSTANCE;
    }

    protected HangmanModel() {
        reset();
    }

    private HangmanPlayerData mPlayerData[];
    private int currentPlayer;
    private int winner;

    public void reset(){
        String word = WORD_LIST[ThreadLocalRandom.current().nextInt(0,WORD_LIST.length)];
        mPlayerData = new HangmanPlayerData[2];
        mPlayerData[0] = new HangmanPlayerData(word);
        mPlayerData[1] = new HangmanPlayerData(word);
        currentPlayer = 0;
        winner = 0;
    }

    public void guessLetter(char letter){
        mPlayerData[currentPlayer].guessLetter(letter);
        if (mPlayerData[currentPlayer].wordFinished){
            if (currentPlayer == 0){
                currentPlayer = 1;
            } else {
                //Calculate the mWinner
                if (mPlayerData[0].isWordFinished() & mPlayerData[1].isWordFinished()){
                    //both players finished, compare guesses
                    if (mPlayerData[0].getNumGuesses() < mPlayerData[1].getNumGuesses()){
                        winner = 1;
                    } else if (mPlayerData[0].getNumGuesses() > mPlayerData[1].getNumGuesses()){
                        winner = 2;
                    } else {
                        //tie
                        winner = 3;
                    }
                } else if (mPlayerData[0].isWordFinished()){
                    //player 1 finished
                    winner = 1;
                } else if (mPlayerData[1].isWordFinished()){
                    //player 2 finished
                    winner = 2;
                } else {
                    //no player finished, draw
                    winner = 3;
                }
            }
        }
    }


    public int getWinner() {
        return winner;
    }

    public HangmanPlayerData getCurrentPlayerObject(){
        return mPlayerData[currentPlayer];
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public class HangmanPlayerData {
        public HashMap<Character,Boolean> letterGuessed;
        public int numGuesses;
        public String word;
        public boolean wordFinished;

        public HangmanPlayerData(String rWord){
            word = rWord;
            letterGuessed = new HashMap<>(); //initialize guesses to false for every letter
            numGuesses = 0;
            wordFinished = false;
        }

        public void guessLetter(char letter){
            String displayWord = getDisplayWord();
            letterGuessed.put(letter,true);
            if (displayWord.equals(getDisplayWord())) {
                numGuesses += 1;
            }
            getDisplayWord();
        }

        public int getNumGuesses() {
            return numGuesses;
        }

        public boolean isWordFinished() {
            return wordFinished;
        }

        public boolean getLetterGuessed(char letter){
            if (letterGuessed.containsKey(letter)){
                return letterGuessed.get(letter);
            } else {
                return false;
            }
        }

        public String charactersUsed(){
            String chars = "";
            for (Character character : letterGuessed.keySet()){
                if (letterGuessed.get(character)){
                    chars += character;
                }
            }
            return chars;
        }

        public String getDisplayWord(){
            String wordDisplay = "";
            wordFinished = true;
            for (int i = 0; i < word.length(); i++){
                char current = word.charAt(i);
                if (getLetterGuessed(current)){
                    wordDisplay += current;
                } else {
                    wordDisplay += "_";
                    wordFinished = false;
                }
                if (i < word.length()-1) {
                    wordDisplay += " ";
                }
            }
            return wordDisplay;
        }
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////