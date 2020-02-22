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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import csc214.project01.Fragments.ScoreboardModel;

////////////////////////////////////////
// Set Names Activity
////////////////////////////////////////

public class SetNamesActivity extends AppCompatActivity {

    private ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    private Button mConfirmButton;
    private Button mCancelButton;
    private EditText mPlayer1Name;
    private EditText mPlayer2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_names);

        mConfirmButton = (Button)findViewById(R.id.confirmButton);
        mCancelButton = (Button)findViewById(R.id.cancelButton);
        mPlayer1Name = (EditText)findViewById(R.id.playerOneNameEdit);
        mPlayer2Name = (EditText)findViewById(R.id.playerTwoNameEdit);

        if (savedInstanceState != null) {
            mPlayer1Name.setText(savedInstanceState.getString("player1Name"));
            mPlayer2Name.setText(savedInstanceState.getString("player2Name"));
        } else {
            mPlayer1Name.setText(mScoreboardModel.getPlayerOneName());
            mPlayer2Name.setText(mScoreboardModel.getPlayerTwoName());
        }

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScoreboardModel.setPlayerOneName(mPlayer1Name.getText().toString());
                mScoreboardModel.setPlayerTwoName(mPlayer2Name.getText().toString());
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //We don't need to save any state since the main activity is only viewing
        outState.putString("player1Name",mPlayer1Name.getText().toString());
        outState.putString("player2Name",mPlayer2Name.getText().toString());
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////