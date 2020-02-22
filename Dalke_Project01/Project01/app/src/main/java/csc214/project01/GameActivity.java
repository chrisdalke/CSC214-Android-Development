////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 01
////////////////////////////////////////

package csc214.project01;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import csc214.project01.Fragments.ScoreboardFragment;
import csc214.project01.Fragments.ScoreboardModel;
import csc214.project01.Games.Connect4.Connect4Fragment;
import csc214.project01.Games.Hangman.HangmanFragment;
import csc214.project01.Games.HotterColder.HotterColderFragment;
import csc214.project01.Games.TicTacToe.TicTacToeFragment;

import static csc214.project01.GameConstants.CONNECT_4;
import static csc214.project01.GameConstants.HANGMAN;
import static csc214.project01.GameConstants.HOTTER_COLDER;
import static csc214.project01.GameConstants.TICTACTOE;

////////////////////////////////////////
// Game Activity
////////////////////////////////////////

public class GameActivity extends AppCompatActivity {

    private GameFragment mGameFragment;
    private ScoreboardFragment mScoreboardFragment;
    private ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    private Button mQuitGameButton;
    private boolean quitConfirm = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Get element ids
        mQuitGameButton = (Button)findViewById(R.id.quitGameButton);
        mQuitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quitConfirm){
                    finish();
                } else {
                    Toast.makeText(GameActivity.this,"Press Quit again to quit to menu.",Toast.LENGTH_SHORT).show();
                    quitConfirm = true;
                }
            }
        });

        //Add the game fragment based on the value specified in the intent
        Intent myIntent = getIntent();
        int gameType = myIntent.getIntExtra("gameType", CONNECT_4);
        switch (gameType) {
            case CONNECT_4: {
                setTitle("Connect 4");
                mGameFragment = new Connect4Fragment();
                break;
            }
            case HANGMAN: {
                setTitle("Hangman");
                mGameFragment = new HangmanFragment();
                break;
            }
            case HOTTER_COLDER: {
                setTitle("Hotter / Colder");
                mGameFragment = new HotterColderFragment();
                break;
            }
            case TICTACTOE: {
                setTitle("Tic Tac Toe");
                mGameFragment = new TicTacToeFragment();
                break;
            }
        }
        getFragmentManager().
                beginTransaction()
                .add(R.id.gameFragmentHolder, mGameFragment)
                .commit();
        reset();


        //Add scoreboard fragment
        mScoreboardFragment = new ScoreboardFragment();
        getFragmentManager().
                beginTransaction()
                .add(R.id.scoreboardFragmentHolder,mScoreboardFragment)
                .commit();

    }

    public void reset(){
        mScoreboardModel.setPlayerOneHighlight(true);
        mScoreboardModel.setPlayerOneHighlight(false);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////