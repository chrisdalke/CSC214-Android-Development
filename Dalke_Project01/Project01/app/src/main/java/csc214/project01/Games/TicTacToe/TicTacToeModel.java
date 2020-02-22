////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 01
////////////////////////////////////////

package csc214.project01.Games.TicTacToe;

////////////////////////////////////////
// Tic Tac Toe Model
////////////////////////////////////////

public class TicTacToeModel {

    private static TicTacToeModel INSTANCE = null;
    protected TicTacToeModel() {}

    public static TicTacToeModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TicTacToeModel();
        }
        return INSTANCE;
    }

    private int[][] mSquares = new int[3][3];
    private int mWinner = 0;
    private int mCurrentPlayer = 1;

    public void setSquare(int x, int y, int val){
        mSquares[x][y] = val;
    }

    public int getSquare(int x, int y){
        return mSquares[x][y];
    }

    public boolean hasWinner(){
        return mWinner > 0;
    }

    public int getWinner(){
        return mWinner;
    }

    public void makeMove(int x, int y){
        if (getSquare(x,y) == 0){
            setSquare(x,y, mCurrentPlayer);
            if (mCurrentPlayer == 1){
                mCurrentPlayer = 2;
            } else {
                mCurrentPlayer = 1;
            }
            checkWin();
        }

    }

    public void checkWin(){
        //Check all the possible win positions
        for (int curPlayer = 1; curPlayer <=2; curPlayer++) {
            //Horizontal
            if (mSquares[0][0] == curPlayer & mSquares[1][0] == curPlayer & mSquares[2][0] == curPlayer){
                mWinner = curPlayer;
            }
            if (mSquares[0][1] == curPlayer & mSquares[1][1] == curPlayer & mSquares[2][1] == curPlayer){
                mWinner = curPlayer;
            }
            if (mSquares[0][2] == curPlayer & mSquares[1][2] == curPlayer & mSquares[2][2] == curPlayer){
                mWinner = curPlayer;
            }
            //Vertical
            if (mSquares[0][0] == curPlayer & mSquares[0][1] == curPlayer & mSquares[0][2] == curPlayer){
                mWinner = curPlayer;
            }
            if (mSquares[1][0] == curPlayer & mSquares[1][1] == curPlayer & mSquares[1][2] == curPlayer){
                mWinner = curPlayer;
            }
            if (mSquares[2][0] == curPlayer & mSquares[2][1] == curPlayer & mSquares[2][2] == curPlayer){
                mWinner = curPlayer;
            }
            //Diagonal
            if (mSquares[0][0] == curPlayer & mSquares[1][1] == curPlayer & mSquares[2][2] == curPlayer){
                mWinner = curPlayer;
            }

            if (mSquares[0][2] == curPlayer & mSquares[1][1] == curPlayer & mSquares[2][0] == curPlayer){
                mWinner = curPlayer;
            }
        }

        //Check a tie condition
        int numFilledTiles = 0;
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                if (mSquares[x][y] != 0){
                    numFilledTiles++;
                }
            }
        }
        if (numFilledTiles == 9 & mWinner == 0){
            mWinner = 3;
        }
    }

    public int getCurrentPlayer() {
        return mCurrentPlayer;
    }

    public void reset(){
        mWinner = 0;
        mCurrentPlayer = 1;
        mSquares = new int[3][3];
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////