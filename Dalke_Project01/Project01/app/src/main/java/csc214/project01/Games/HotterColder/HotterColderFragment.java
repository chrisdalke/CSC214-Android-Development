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

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import csc214.project01.Fragments.ScoreboardModel;
import csc214.project01.GameFragment;
import csc214.project01.R;

////////////////////////////////////////
// Hotter-Colder Game Fragment
////////////////////////////////////////

public class HotterColderFragment extends GameFragment {

    private ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    private HotterColderModel hotterColderModel = HotterColderModel.getInstance();
    private TextView mGuessProximityLabel;
    private TextView mGuessNumLabel;
    private Button mGuessButton;
    private EditText mGuessInput;


    public HotterColderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGuessProximityLabel = (TextView) (getView().findViewById(R.id.guessProximityLabel));
        mGuessNumLabel = (TextView) (getView().findViewById(R.id.guessNumLabel));
        mGuessButton = (Button) (getView().findViewById(R.id.guessButton));
        mGuessInput = (EditText) (getView().findViewById(R.id.guessInput));
        mGuessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hotterColderModel.getWinner() == 0) {
                    try {
                        int currentPlayer = hotterColderModel.getCurrentPlayer();
                        hotterColderModel.submitGuess(Integer.parseInt(mGuessInput.getText().toString()));

                        if (hotterColderModel.getCurrentPlayer() != currentPlayer) {
                            //Clear the UI elements
                            mGuessInput.setText("");
                            //Clear the player marker
                            mScoreboardModel.setPlayerOneHighlight(false);
                            mScoreboardModel.setPlayerTwoHighlight(true);
                            mScoreboardModel.triggerUpdate();

                            //Display a toast indicating the player change
                            Toast.makeText(getContext(), "Player 1 guessed correctly!", Toast.LENGTH_SHORT).show();
                        }

                        if (hotterColderModel.getWinner() > 0){
                            gameOver();
                        }

                        updateUI();
                    } catch (NumberFormatException e) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                        builder.setMessage("Please enter a number 0-100 as a guess!")
                                .setTitle("Invalid Number");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }
        });


        //Set inital state for scoreboard
        mScoreboardModel.setPlayerOneHighlight(true);
        mScoreboardModel.setPlayerTwoHighlight(false);
        mScoreboardModel.triggerUpdate();

        updateUI();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotter_colder, container, false);
    }

    @Override
    public void reset() {
        hotterColderModel.reset();
        //Clear guess input
        mGuessInput.setText("");
        updateUI();
    }

    @Override
    public void updateUI() {
        if (hotterColderModel.getWinner() == 0) {
            mGuessNumLabel.setText(getResources().getString(R.string.number_of_guesses)+" "+hotterColderModel.getCurrentPlayerNumGuesses());
            mGuessProximityLabel.setText(hotterColderModel.getCurrentPlayerGuessLabel());
        } else if (hotterColderModel.getWinner() == 1) {
            mGuessNumLabel.setText(getResources().getString(R.string.number_of_guesses)+" "+hotterColderModel.getPlayerNumGuesses(0));
            mGuessProximityLabel.setText(mScoreboardModel.getPlayerOneName() + " wins!");
            mGuessInput.setText("");
        } else if (hotterColderModel.getWinner() == 2) {
            mGuessNumLabel.setText(getResources().getString(R.string.number_of_guesses)+" "+hotterColderModel.getPlayerNumGuesses(1));
            mGuessProximityLabel.setText(mScoreboardModel.getPlayerTwoName() + " wins!");
            mGuessInput.setText("");
        } else if (hotterColderModel.getWinner() == 3) {
            mGuessNumLabel.setText(getResources().getString(R.string.number_of_guesses)+" "+hotterColderModel.getPlayerNumGuesses(0));
            mGuessProximityLabel.setText("Tie!");
            mGuessInput.setText("");
        }
    }


    public void gameOver(){
        int scoreDiff = Math.abs(hotterColderModel.getPlayerNumGuesses(0)-hotterColderModel.getPlayerNumGuesses(1));
        if (hotterColderModel.getWinner() == 1) {
            Toast.makeText(getContext(), mScoreboardModel.getPlayerOneName() + " has won Hotter / Colder!", Toast.LENGTH_LONG).show();
            mScoreboardModel.setPlayerOneScore(mScoreboardModel.getPlayerOneScore()+(scoreDiff*100));
        } else if (hotterColderModel.getWinner() == 2){
            Toast.makeText(getContext(), mScoreboardModel.getPlayerTwoName() + " has won Hotter / Colder!", Toast.LENGTH_LONG).show();
            mScoreboardModel.setPlayerTwoScore(mScoreboardModel.getPlayerTwoScore()+(scoreDiff*100));
        } else if (hotterColderModel.getWinner() == 3){
            Toast.makeText(getContext(), "The game was a tie!", Toast.LENGTH_LONG).show();
        }
        mScoreboardModel.triggerUpdate();
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////