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

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import csc214.project01.Fragments.ScoreboardModel;
import csc214.project01.GameFragment;
import csc214.project01.R;

////////////////////////////////////////
// Hangman Fragment
////////////////////////////////////////

public class HangmanFragment extends GameFragment {

    private HangmanModel mHangmanModel = HangmanModel.getInstance();
    private ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    private ImageView mHangmanImage;
    private TextView mHangmanText;
    private TextView mHangmanCharUsedLabel;
    private EditText mHangmanGuessInput;
    private Button mHangmanGuessButton;

    public HangmanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hangman, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHangmanImage = (ImageView)(getView().findViewById(R.id.hangmanImage));
        mHangmanText = (TextView) (getView().findViewById(R.id.hangmanText));
        mHangmanCharUsedLabel = (TextView) (getView().findViewById(R.id.hangmanCharUsedLabel));
        mHangmanGuessInput = (EditText)  (getView().findViewById(R.id.hangmanGuessInput));
        mHangmanGuessButton = (Button) (getView().findViewById(R.id.hangmanGuessButton));

        mHangmanGuessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHangmanModel.getWinner() == 0){
                    String guess = mHangmanGuessInput.getText().toString();
                    if (guess.length() == 1){
                        if (mHangmanModel.getCurrentPlayerObject().getLetterGuessed(guess.charAt(0))){
                            Toast.makeText(getContext(),"This letter has already been guessed!",Toast.LENGTH_SHORT).show();
                            updateUI();
                        } else {
                            mHangmanModel.guessLetter(guess.charAt(0));

                            if (mHangmanModel.getWinner() > 0) {
                                gameOver();
                            }
                            updateUI();
                        }

                    } else {
                        Toast.makeText(getContext(),"Please enter a single letter only!",Toast.LENGTH_SHORT).show();
                        updateUI();
                    }
                }
            }
        });

        updateUI();
    }

    @Override
    public void reset() {
        mHangmanModel.reset();
        updateUI();
    }

    @Override
    public void updateUI() {

        //Update the scoreboard with the current player
        if (mHangmanModel.getCurrentPlayer() == 0){
            mScoreboardModel.setPlayerOneHighlight(true);
            mScoreboardModel.setPlayerTwoHighlight(false);
        } else {
            mScoreboardModel.setPlayerOneHighlight(false);
            mScoreboardModel.setPlayerTwoHighlight(true);
        }
        mScoreboardModel.triggerUpdate();

        mHangmanGuessInput.setText("");
        mHangmanCharUsedLabel.setText("Characters Used: "+mHangmanModel.getCurrentPlayerObject().charactersUsed());
        mHangmanText.setText(mHangmanModel.getCurrentPlayerObject().getDisplayWord());
        switch (mHangmanModel.getCurrentPlayerObject().getNumGuesses()){
            case 0: {
                mHangmanImage.setImageResource(R.drawable.hangman0);
                break;
            }
            case 1: {
                mHangmanImage.setImageResource(R.drawable.hangman1);
                break;
            }
            case 2: {
                mHangmanImage.setImageResource(R.drawable.hangman2);
                break;
            }
            case 3: {
                mHangmanImage.setImageResource(R.drawable.hangman3);
                break;
            }
            case 4: {
                mHangmanImage.setImageResource(R.drawable.hangman4);
                break;
            }
            case 5: {
                mHangmanImage.setImageResource(R.drawable.hangman5);
                break;
            }
            case 6: {
                mHangmanImage.setImageResource(R.drawable.hangman6);
                break;
            }
            case 7: {
                mHangmanImage.setImageResource(R.drawable.hangman7);
                break;
            }
            case 8: {
                mHangmanImage.setImageResource(R.drawable.hangman8);
                break;
            }
        }

    }

    public void gameOver(){
        if (mHangmanModel.getWinner() == 1) {
            Toast.makeText(getContext(), mScoreboardModel.getPlayerOneName() + " has won Hangman!", Toast.LENGTH_LONG).show();
            mScoreboardModel.setPlayerOneScore(mScoreboardModel.getPlayerOneScore()+100);
        } else if (mHangmanModel.getWinner() == 2){
            Toast.makeText(getContext(), mScoreboardModel.getPlayerTwoName() + " has won Hangman!", Toast.LENGTH_LONG).show();
            mScoreboardModel.setPlayerTwoScore(mScoreboardModel.getPlayerTwoScore()+100);
        } else if (mHangmanModel.getWinner() == 3){
            Toast.makeText(getContext(), "The game was a tie!", Toast.LENGTH_LONG).show();
        }
        mScoreboardModel.triggerUpdate();
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////