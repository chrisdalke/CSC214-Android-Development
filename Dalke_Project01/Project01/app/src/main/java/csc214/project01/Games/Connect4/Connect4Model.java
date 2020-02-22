////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 01
////////////////////////////////////////

package csc214.project01.Games.Connect4;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import csc214.project01.Games.HotterColder.HotterColderModel;

////////////////////////////////////////
// Connect 4 Model
////////////////////////////////////////

public class Connect4Model {

    private static Connect4Model INSTANCE = null;

    public static Connect4Model getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Connect4Model();
        }
        return INSTANCE;
    }

    protected Connect4Model() {
        reset();
    }

    private int[][] board;
    private int currentPlayer;
    private int winner;

    public void reset(){
        board = new int[8][8];
        currentPlayer = 1;
        winner = 0;
    }

    public int getPosition(int x, int y){
        return board[x][y];
    }

    public void setPosition(int x, int y, int value){
        board[x][y] = value;
    }

    public boolean checkMoveValid(int column){
        return board[column][0] == 0;
    }

    public void makeMove(int column){
        if (checkMoveValid(column)) {
            int tempY = 0;
            while (tempY < 8 && board[column][tempY] == 0) {
                //This will reach the first non-zero board entry
                tempY = tempY + 1;
            }
            //Step back up to the last zero entry
            tempY = tempY - 1;
            //Update the board state and current player
            board[column][tempY] = currentPlayer;
            checkWinState();
            if (!hasWinner()) {
                if (currentPlayer == 1){
                    currentPlayer = 2;
                } else {
                    currentPlayer = 1;
                }
            }
        }
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void checkWinState(){
        //Checks the board for a win state
        //Check every direction from every player from every location
        int winState = 0;
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                for (int player = 1; player <= 2; player++){
                    boolean w1 = winCheckHelper(x,y,1,0,player);
                    boolean w2 = winCheckHelper(x,y,0,1,player);
                    boolean w3 = winCheckHelper(x,y,1,1,player);
                    boolean w4 = winCheckHelper(x,y,1,-1,player);
                    if (w1 | w2 | w3 | w4){
                        winState = player;
                    }
                }

            }
        }
        if (winState > 0){
            winner = winState;
        }
    }

    private boolean winCheckHelper(int x, int y, int dx, int dy, int player){
        for (int index = 0; index < 4; index++){
            if (x > 0 && x < 8 && y > 0 && y < 8){
                if (player != board[x][y]){
                    return false;
                }
            } else {
                return false;
            }
            x = x + dx;
            y = y + dy;
        }
        return true;
    }

    public boolean hasWinner(){
        return winner > 0;
    }

    public int getWinner(){
        return winner;
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////