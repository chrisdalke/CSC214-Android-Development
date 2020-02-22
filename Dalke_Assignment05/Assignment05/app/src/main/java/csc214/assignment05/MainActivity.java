////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 5
////////////////////////////////////////

package csc214.assignment05;

////////////////////////////////////////
// Module imports
////////////////////////////////////////

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

////////////////////////////////////////
// Main Activity
////////////////////////////////////////

public class MainActivity extends AppCompatActivity implements TopFragment.MessageResponseListener {

    //Instance variables
    private EditText mSendMessageText;
    private Button mSendMessageButton;
    private TopFragment mTopFragment;
    private BottomFragment mBottomFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get references to UI elements
        mSendMessageText = (EditText)findViewById(R.id.sendMessageText);
        mSendMessageButton = (Button)findViewById(R.id.sendMessageButton);

        //Add onclick listener for center button
        //The button will generate a new top fragment with the message from the edit pane
        mSendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When button is clicked, refresh the top panel fragment
                mTopFragment = new TopFragment();

                //Add message parameter to bundle and pass into the fragment
                Bundle tempBundle = new Bundle();
                tempBundle.putString("message",mSendMessageText.getText().toString());
                mTopFragment.setArguments(tempBundle);
                getFragmentManager().
                        beginTransaction()
                        .add(R.id.topPanel,mTopFragment)
                        .commit();
            }
        });

        //Generate top and bottom fragments
        mTopFragment = new TopFragment();
        mBottomFragment = new BottomFragment();

        //Add fragments to the top and bottom panels
        getFragmentManager().
                beginTransaction()
                .add(R.id.topPanel,mTopFragment)
                .commit();
        getFragmentManager().
                beginTransaction()
                .add(R.id.bottomPanel,mBottomFragment)
                .commit();
    }

    //Implementation of the callback listener which relays message from top fragment to bottom fragment
    @Override
    public void messageResponse(CharSequence message) {
        mBottomFragment.messageSentBack(message);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////