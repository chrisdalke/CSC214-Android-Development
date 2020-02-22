////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 01
////////////////////////////////////////

package csc214.project01.Fragments;

////////////////////////////////////////
// Scoreboard Model
////////////////////////////////////////

public class ScoreboardModel {

    public Runnable updateFunction;
    public void triggerUpdate(){
        if (updateFunction != null){
            updateFunction.run();
        }
    }

    private static ScoreboardModel INSTANCE = null;
    public static ScoreboardModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ScoreboardModel();
        }
        return INSTANCE;
    }

    protected ScoreboardModel() {
        playerOneName = "Player 1";
        playerTwoName = "Player 2";
        reset();
    }

    private int playerOneScore;
    private int playerTwoScore;
    private String playerOneName;
    private String playerTwoName;
    private boolean playerOneHighlight;
    private boolean playerTwoHighlight;

    public void reset(){
        playerOneScore = 0;
        playerTwoScore = 0;
        playerOneHighlight = false;
        playerTwoHighlight = false;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    public boolean isPlayerOneHighlight() {
        return playerOneHighlight;
    }

    public void setPlayerOneHighlight(boolean playerOneHighlight) {
        this.playerOneHighlight = playerOneHighlight;
    }

    public boolean isPlayerTwoHighlight() {
        return playerTwoHighlight;
    }

    public void setPlayerTwoHighlight(boolean playerTwoHighlight) {
        this.playerTwoHighlight = playerTwoHighlight;
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////