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

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import csc214.project01.Fragments.ScoreboardFragment;
import csc214.project01.Fragments.ScoreboardModel;
import csc214.project01.Games.Connect4.Connect4Model;
import csc214.project01.Games.Hangman.HangmanModel;
import csc214.project01.Games.HotterColder.HotterColderModel;

////////////////////////////////////////
// Main Activity
////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    private ScoreboardFragment mScoreboardFragment;
    private ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    private Button mConnect4Button;
    private Button mHangmanButton;
    private Button mHotterColderButton;
    private Button mTicTacToeButton;
    private Button mSetNamesButton;
    private Button mResetScoreButton;
    private Connect4Model mConnect4Model = Connect4Model.getInstance();
    private HangmanModel mHangmanModel = HangmanModel.getInstance();
    private HotterColderModel mHotterColderModel = HotterColderModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConnect4Button = (Button)findViewById(R.id.connect4Button);
        mHangmanButton = (Button)findViewById(R.id.hangmanButton);
        mHotterColderButton = (Button)findViewById(R.id.hotterColderButton);
        mTicTacToeButton = (Button)findViewById(R.id.ticTacToeButton);
        mSetNamesButton = (Button)findViewById(R.id.setNamesButton);
        mResetScoreButton = (Button)findViewById(R.id.resetScoreButton);

        mScoreboardFragment = new ScoreboardFragment();
        getFragmentManager().
                beginTransaction()
                .add(R.id.scoreboardFragmentHolder,mScoreboardFragment)
                .commit();

        //Create button click listeners
        mConnect4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(getBaseContext(), GameActivity.class);
                gameIntent.putExtra("gameType",GameConstants.CONNECT_4);
                mConnect4Model.reset();
                startActivity(gameIntent);
            }
        });
        mHangmanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(getBaseContext(), GameActivity.class);
                gameIntent.putExtra("gameType",GameConstants.HANGMAN);
                mHangmanModel.reset();
                startActivity(gameIntent);
            }
        });
        mHotterColderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(getBaseContext(), GameActivity.class);
                gameIntent.putExtra("gameType",GameConstants.HOTTER_COLDER);
                mHotterColderModel.reset();
                startActivity(gameIntent);
            }
        });
        mTicTacToeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(getBaseContext(), GameActivity.class);
                gameIntent.putExtra("gameType",GameConstants.TICTACTOE);
                startActivity(gameIntent);
            }
        });
        mSetNamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SetNamesActivity.class));
            }
        });
        mResetScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResetScoreDialog();
            }
        });
    }

    private void showResetScoreDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                mScoreboardModel.reset();
                mScoreboardFragment.updateScoreboardView();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.setMessage("Are you sure you want to reset the scores?")
                .setTitle("Reset Scores");
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        mScoreboardModel.setPlayerOneHighlight(false);
        mScoreboardModel.setPlayerTwoHighlight(false);
        mScoreboardFragment.updateScoreboardView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //We don't need to save any state since the main activity is only viewing
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////