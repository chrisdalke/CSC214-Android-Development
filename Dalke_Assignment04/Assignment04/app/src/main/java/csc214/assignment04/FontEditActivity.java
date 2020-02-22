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
// Mobile Imports
////////////////////////////////////////

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;

////////////////////////////////////////
// Font Edit Activity - edits font settings
////////////////////////////////////////

public class FontEditActivity extends AppCompatActivity {

    //Get instances of model singleton
    private FontModel mFontModel = FontModel.getInstance();

    //Member variables for UI elements
    private CheckBox mBoldToggle;
    private CheckBox mItalicToggle;
    private CheckBox mUnderlineToggle;
    private Spinner mTextColorSelection;
    private SeekBar mTextSizeSelection;
    private Button mCancelButton;
    private Button mConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_edit);

        //Set member variables to reference UI elements
        mBoldToggle = (CheckBox)findViewById(R.id.boldToggle);
        mItalicToggle = (CheckBox)findViewById(R.id.italicToggle);
        mUnderlineToggle = (CheckBox)findViewById(R.id.underlineToggle);
        mTextColorSelection = (Spinner)findViewById(R.id.textColorSelection);
        mTextSizeSelection = (SeekBar)findViewById(R.id.textSizeSelection);
        mCancelButton = (Button)findViewById(R.id.cancelButton);
        mConfirmButton = (Button)findViewById(R.id.confirmButton);

        //Load state from the singleton into UI elements
        mBoldToggle.setChecked(mFontModel.isBold());
        mItalicToggle.setChecked(mFontModel.isItalic());
        mUnderlineToggle.setChecked(mFontModel.isUnderline());

        //Set color spinner options
        String[] colorArray = {"Black","Red","Green","Blue"};
        mTextColorSelection.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,colorArray));
        mTextColorSelection.setSelection(mFontModel.getTextColor());

        //set text size seek bar options
        mTextSizeSelection.setLeft(8);
        mTextSizeSelection.setRight(40);
        mTextSizeSelection.setProgress(mFontModel.getTextSize());

        //Create button click listeners
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FontEditActivity.this.finish();
            }
        });
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save then finish
                saveSettings();
                FontEditActivity.this.finish();
            }
        });
    }

    private void saveSettings(){
        //Save all the properties we have edited into the font model
        mFontModel.setBold(mBoldToggle.isChecked());
        mFontModel.setItalic(mItalicToggle.isChecked());
        mFontModel.setUnderline(mUnderlineToggle.isChecked());
        mFontModel.setTextColor(mTextColorSelection.getSelectedItemPosition());
        mFontModel.setTextSize(mTextSizeSelection.getProgress());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //We've already saved if we clicked the save button, nothing to save
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////