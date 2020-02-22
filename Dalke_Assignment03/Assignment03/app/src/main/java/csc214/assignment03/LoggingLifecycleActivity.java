////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 3
////////////////////////////////////////

package csc214.assignment03;

////////////////////////////////////////
// Module imports
////////////////////////////////////////

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

////////////////////////////////////////
// Main Activity
////////////////////////////////////////

public class LoggingLifecycleActivity extends AppCompatActivity {

    // Constants
    private static final String DALKE_LOG_TAG = "Dalke03";
    private static final String KEY_NUM_ORIENTATION_CHANGES = "NUM_ORIENTATION_CHANGES";
    private static final String KEY_LAST_RANDOM_NUMBER = "LAST_RANDOM_NUMBER";

    // Model Member Variables
    private int mNumOrientationChanges = 0;
    private int mLastRandomNumber = 1;

    // UI Member Variables
    private ImageButton mDiceRollButton;
    private ImageView mDiceImage;
    private TextView mNumOrientationChangesLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging_lifecycle);

        // Set Member variables for UI elements
        mDiceRollButton = (ImageButton)findViewById(R.id.diceRollButton);
        mDiceImage = (ImageView)findViewById(R.id.diceImage);
        mNumOrientationChangesLabel = (TextView)findViewById(R.id.numOrientationChangesLabel);

        // Set Click Listener for UI button
        mDiceRollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Roll the dice.
                rollDice();
                // Display a toast with the last rolled #
                Toast.makeText(LoggingLifecycleActivity.this,"Rolled a "+mLastRandomNumber+"!", Toast.LENGTH_SHORT).show();
            }
        });

        // Restore State from Bundle
        if (savedInstanceState != null){
            mNumOrientationChanges = savedInstanceState.getInt(KEY_NUM_ORIENTATION_CHANGES,0) + 1;
            mLastRandomNumber = savedInstanceState.getInt(KEY_LAST_RANDOM_NUMBER,1);
        }

        // Restore Dice Image
        setDiceImage();

        //Set the value of the Orientation Changes label based on the value of member variable
        mNumOrientationChangesLabel.setText("Display Rotated "+mNumOrientationChanges+" Times!");
    }

    @Override
    public void onSaveInstanceState(Bundle state){
        // Save the model variables to the bundle
        state.putInt(KEY_LAST_RANDOM_NUMBER,mLastRandomNumber);
        state.putInt(KEY_NUM_ORIENTATION_CHANGES,mNumOrientationChanges);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(DALKE_LOG_TAG,"Application Started.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(DALKE_LOG_TAG,"Application Resumed.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(DALKE_LOG_TAG,"Application Paused.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(DALKE_LOG_TAG,"Application Stopped.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(DALKE_LOG_TAG,"Application Destroyed.");
    }

    // Sets the image based on the dice number rolled
    private void setDiceImage(){
        //Switch the image resource based on the random choice
        switch (mLastRandomNumber){
            case 1:
                mDiceImage.setImageResource(R.drawable.dice1);
                break;
            case 2:
                mDiceImage.setImageResource(R.drawable.dice2);
                break;
            case 3:
                mDiceImage.setImageResource(R.drawable.dice3);
                break;
            case 4:
                mDiceImage.setImageResource(R.drawable.dice4);
                break;
            case 5:
                mDiceImage.setImageResource(R.drawable.dice5);
                break;
            case 6:
                mDiceImage.setImageResource(R.drawable.dice6);
                break;
        }
    }

    // Generates a random number and then sets the image
    private void rollDice(){
        Random r = new Random();
        mLastRandomNumber = r.nextInt(6) + 1;
        setDiceImage();
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////