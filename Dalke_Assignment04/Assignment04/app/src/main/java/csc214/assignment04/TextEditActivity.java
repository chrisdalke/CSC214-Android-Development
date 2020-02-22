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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import csc214.assignment04.Fragments.TextEditFragment;

////////////////////////////////////////
// Text Editing Activity
////////////////////////////////////////

public class TextEditActivity extends AppCompatActivity {

    //Get instances of model singleton
    private FontModel mFontModel = FontModel.getInstance();
    private TextEditFragment mTextEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_edit);

        //Add fragment
        mTextEditFragment = new TextEditFragment();
        getFragmentManager().
                beginTransaction()
                .add(R.id.textFragmentHolder,mTextEditFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////