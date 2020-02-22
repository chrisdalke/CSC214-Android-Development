////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 4
////////////////////////////////////////

package csc214.assignment04;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

////////////////////////////////////////
// Main Activity
////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    //Get instances of model singleton
    private FontModel mFontModel = FontModel.getInstance();
    //References to UI elements
    private TextView mTextPanel;
    private Button mButtonEditFont;
    private Button mButtonEditMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get references to elements and save in member variables
        mTextPanel = (TextView)findViewById(R.id.textPanel);
        mButtonEditFont = (Button)findViewById(R.id.buttonEditFont);
        mButtonEditMessage = (Button)findViewById(R.id.buttonEditMessage);

        //Create button click listeners
        mButtonEditFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), FontEditActivity.class));
            }
        });
        mButtonEditMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), TextEditActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Restore saved state from the model singleton
        //Set text string
        System.out.println(mFontModel.getText());
        mTextPanel.setText(mFontModel.getText());
        //Set text size
        mTextPanel.setTextSize(TypedValue.COMPLEX_UNIT_SP,mFontModel.getTextSize());
        //Set underline
        if (mFontModel.isUnderline()){
            mTextPanel.setPaintFlags(mTextPanel.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        } else {
            mTextPanel.setPaintFlags(0);
        }
        //Set italic and bold
        if (mFontModel.isBold() && mFontModel.isItalic()){
            mTextPanel.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC);
        }
        if (!mFontModel.isBold() && mFontModel.isItalic()){
            mTextPanel.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
        }
        if (mFontModel.isBold() && !mFontModel.isItalic()){
            mTextPanel.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        }
        if (!mFontModel.isBold() && !mFontModel.isItalic()){
            mTextPanel.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        }
        //Set text color
        switch (mFontModel.getTextColor()){
            case 0:{
                mTextPanel.setTextColor(Color.BLACK);
                break;
            }
            case 1:{
                mTextPanel.setTextColor(Color.RED);
                break;
            }
            case 2:{
                mTextPanel.setTextColor(Color.GREEN);
                break;
            }
            case 3:{
                mTextPanel.setTextColor(Color.BLUE);
                break;
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //We don't need to save any state since the main activity is only viewing
    }
}

////////////////////////////////////////
// End of Code
////////////////////////////////////////