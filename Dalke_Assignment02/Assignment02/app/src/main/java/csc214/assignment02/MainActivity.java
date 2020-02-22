////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 2
////////////////////////////////////////

package csc214.assignment02;

////////////////////////////////////////
// Module imports
////////////////////////////////////////

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.os.Bundle;
import java.util.Locale;

////////////////////////////////////////
// Main Activity
////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    // Member variables for all of the UI elements
    // Don't need getters for any of these variables since they aren't used elsewhere
    private Button mToastButton1;
    private Button mToastButton2;
    private Button mIncrementButton;
    private Button mDecrementButton;
    private Button mResetButton;
    private TextView mNumberDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Inflate layout

        //Get the pointers to all of the UI elements
        mToastButton1 = (Button)findViewById(R.id.toastButton1);
        mToastButton2 = (Button)findViewById(R.id.toastButton2);
        mIncrementButton = (Button)findViewById(R.id.numberIncrement);
        mDecrementButton = (Button)findViewById(R.id.numberDecrement);
        mResetButton = (Button)findViewById(R.id.numberReset);
        mNumberDisplay = (TextView)findViewById(R.id.numberDisplay);

        // Configure the Toast displays for the two buttons
        mToastButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"What do lawyers wear to court?", Toast.LENGTH_SHORT).show();
            }
        });
        mToastButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Lawsuits!", Toast.LENGTH_SHORT).show();
            }
        });

        // Configure listeners for the increment, decrement, reset buttons
        mIncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Increase the value on the label by 1
                setNumberDisplayValue(getNumberDisplayValue() + 1);
            }
        });
        mDecrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Decrease the value on the label by 1
                setNumberDisplayValue(getNumberDisplayValue() - 1);
            }
        });
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reset the value on the label to the default given by strings.xml
                setNumberDisplayValue(Integer.parseInt(getString(R.string.numberDefaultValue)));
            }
        });
    }

    // Helper function: Sets the value of the number display
    private void setNumberDisplayValue(int value) {
        mNumberDisplay.setText(String.format(Locale.US,"%d",value));
    }

    // Helper function: Gets the value of the number display
    private int getNumberDisplayValue() {
        return Integer.parseInt((String)mNumberDisplay.getText());
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
