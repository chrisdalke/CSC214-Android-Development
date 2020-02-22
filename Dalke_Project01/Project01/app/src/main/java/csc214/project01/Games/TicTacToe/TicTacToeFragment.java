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
// Module Imports
////////////////////////////////////////

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import csc214.project01.Fragments.ScoreboardModel;
import csc214.project01.GameFragment;
import csc214.project01.Games.HotterColder.HotterColderModel;
import csc214.project01.R;

////////////////////////////////////////
// Tic Tac Toe Fragment
////////////////////////////////////////

public class TicTacToeFragment extends GameFragment {

    private ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    private TicTacToeModel ticTacToeModel = TicTacToeModel.getInstance();
    private ImageButton[][] tiles = new ImageButton[3][3];

    public TicTacToeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tiles[0][0] = (ImageButton) (getView().findViewById(R.id.ttt1));
        tiles[1][0] = (ImageButton) (getView().findViewById(R.id.ttt2));
        tiles[2][0] = (ImageButton) (getView().findViewById(R.id.ttt3));
        tiles[0][1] = (ImageButton) (getView().findViewById(R.id.ttt4));
        tiles[1][1] = (ImageButton) (getView().findViewById(R.id.ttt5));
        tiles[2][1] = (ImageButton) (getView().findViewById(R.id.ttt6));
        tiles[0][2] = (ImageButton) (getView().findViewById(R.id.ttt7));
        tiles[1][2] = (ImageButton) (getView().findViewById(R.id.ttt8));
        tiles[2][2] = (ImageButton) (getView().findViewById(R.id.ttt9));
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                final int posX = x;
                final int posY = y;
                tiles[x][y].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!ticTacToeModel.hasWinner()) {
                            ticTacToeModel.makeMove(posX,posY);
                            updateUI();
                            if (ticTacToeModel.hasWinner()){
                                //Winner state!
                                gameOver();
                            }
                        }
                    }
                });
            }
        }
        reset();


    }

    public void gameOver(){
        if (ticTacToeModel.getWinner() == 1) {
            Toast.makeText(getContext(), mScoreboardModel.getPlayerOneName() + " has won Tic Tac Toe!", Toast.LENGTH_LONG).show();
            mScoreboardModel.setPlayerOneScore(mScoreboardModel.getPlayerOneScore()+100);
        } else if (ticTacToeModel.getWinner() == 2){
            Toast.makeText(getContext(), mScoreboardModel.getPlayerTwoName() + " has won Tic Tac Toe!", Toast.LENGTH_LONG).show();
            mScoreboardModel.setPlayerTwoScore(mScoreboardModel.getPlayerTwoScore()+100);
        } else if (ticTacToeModel.getWinner() == 3){
            Toast.makeText(getContext(), "The game was a tie!", Toast.LENGTH_LONG).show();
        }
        mScoreboardModel.triggerUpdate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tictactoe, container, false);
    }

    @Override
    public void reset() {
        ticTacToeModel.reset();
        updateUI();
    }

    @Override
    public void updateUI() {


        //Update the scoreboard with the current player
        if (ticTacToeModel.getCurrentPlayer() == 1){
            mScoreboardModel.setPlayerOneHighlight(true);
            mScoreboardModel.setPlayerTwoHighlight(false);
        } else {
            mScoreboardModel.setPlayerOneHighlight(false);
            mScoreboardModel.setPlayerTwoHighlight(true);
        }
        mScoreboardModel.triggerUpdate();

        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                switch (ticTacToeModel.getSquare(x,y)) {
                    case 0: {
                        tiles[x][y].setImageResource(R.drawable.ic_tictactoe_clear);
                        break;
                    }
                    case 1: {
                        tiles[x][y].setImageResource(R.drawable.ic_tictactoe_x);
                        break;
                    }
                    case 2: {
                        tiles[x][y].setImageResource(R.drawable.ic_tictactoe_o);
                        break;
                    }
                }
            }
        }
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////