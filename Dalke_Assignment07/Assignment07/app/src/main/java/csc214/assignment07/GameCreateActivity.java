////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 7
////////////////////////////////////////

package csc214.assignment07;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import csc214.assignment07.Model.Game;
import csc214.assignment07.Model.GameListDatabaseCollection;

////////////////////////////////////////
// Game Creation Activity
////////////////////////////////////////

public class GameCreateActivity extends AppCompatActivity {

    private static final String KEY_NAME = "NAME";
    private static final String KEY_PUBLISHER = "PUBLISHER";
    private static final String KEY_DESCRIPTION = "DESCRIPTION";
    private static final String KEY_GENRE = "GENRE";
    private static final String KEY_YEAR = "YEAR";
    
    private EditText mNameField;
    private EditText mPublisherField;
    private EditText mReleaseYearField;
    private EditText mGenreField;
    private EditText mDescriptionField;
    private Button mConfirmButton;
    private Button mCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_create);

        //Get references to UI elements
        mNameField= (EditText)findViewById(R.id.game_create_field_name);
        mPublisherField= (EditText)findViewById(R.id.game_create_field_publisher);
        mReleaseYearField= (EditText)findViewById(R.id.game_create_field_year);
        mGenreField= (EditText)findViewById(R.id.game_create_field_genre);
        mDescriptionField= (EditText)findViewById(R.id.game_create_field_description);
        mConfirmButton= (Button)findViewById(R.id.confirmButton);
        mCancelButton= (Button)findViewById(R.id.cancelButton);

        // Get saved values for the fields if they exist
        if (savedInstanceState != null){
            mNameField.setText(savedInstanceState.getString(KEY_NAME,""));
            mPublisherField.setText(savedInstanceState.getString(KEY_PUBLISHER,""));
            mReleaseYearField.setText(savedInstanceState.getString(KEY_DESCRIPTION,""));
            mGenreField.setText(savedInstanceState.getString(KEY_GENRE,""));
            mDescriptionField.setText(savedInstanceState.getString(KEY_YEAR,""));
        }

        //Add cancel click listener
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close activity without saving anything
                finish();
            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //verify all fields work
                    if (mNameField.getText().toString().length() == 0){ throw new IllegalArgumentException(); }
                    if (mPublisherField.getText().toString().length() == 0){ throw new IllegalArgumentException(); }
                    if (mDescriptionField.getText().toString().length() == 0){ throw new IllegalArgumentException(); }
                    if (mGenreField.getText().toString().length() == 0){ throw new IllegalArgumentException(); }
                    if (mReleaseYearField.getText().toString().length() == 0){ throw new IllegalArgumentException(); }

                    //Build the attributes into a game object and add it to the game list
                    Game tempGame = new Game();
                    tempGame.setName(mNameField.getText().toString());
                    tempGame.setPublisherName(mPublisherField.getText().toString());
                    tempGame.setDescription(mDescriptionField.getText().toString());
                    tempGame.setGenre(mGenreField.getText().toString());
                    tempGame.setReleaseYear(Integer.parseInt(mReleaseYearField.getText().toString()));

                    (new GameListDatabaseCollection(getApplicationContext())).addGame(tempGame);

                    //close activity after save is finished
                    finish();
                } catch (Exception e){
                    //General catch of format or saving error, this is bad practice but just display an error
                    new AlertDialog.Builder(GameCreateActivity.this)
                            .setTitle("Error")
                            .setMessage("Could not add game. Please check that you have filled all fields!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Save contents of the edit fields so they persist
        outState.putString(KEY_NAME,mNameField.getText().toString());
        outState.putString(KEY_PUBLISHER,mPublisherField.getText().toString());
        outState.putString(KEY_DESCRIPTION,mDescriptionField.getText().toString());
        outState.putString(KEY_GENRE,mGenreField.getText().toString());
        outState.putString(KEY_YEAR,mReleaseYearField.getText().toString());
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////