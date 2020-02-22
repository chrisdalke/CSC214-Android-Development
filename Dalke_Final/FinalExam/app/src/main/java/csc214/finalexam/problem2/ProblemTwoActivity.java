////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Final Project
////////////////////////////////////////

package csc214.finalexam.problem2;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import csc214.finalexam.R;

////////////////////////////////////////
// Problem Two Activity
////////////////////////////////////////

public class ProblemTwoActivity extends AppCompatActivity implements ProblemTwoBottomFragment.BottomCallbackListener, ProblemTwoTopFragment.TopCallbackListener{

    private ProblemTwoTopFragment topFragment;
    private ProblemTwoBottomFragment bottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_two);

        topFragment = new ProblemTwoTopFragment();
        getFragmentManager().beginTransaction().replace(R.id.fl_top,topFragment).commit();

    }

    //Callback interface methods

    @Override
    public void sendBelow(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("MESSAGE", text);
        bottomFragment = new ProblemTwoBottomFragment();
        bottomFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fl_bottom,bottomFragment).commit();

    }

    @Override
    public void sendTop(String text) {
        topFragment.updateTextMessage(text);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////