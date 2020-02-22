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

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

////////////////////////////////////////
// Bottom Fragment
////////////////////////////////////////

public class BottomFragment extends FragmentLifecycleLogger {

    // Instance variables
    private TextView mBottomMessageText;

    public BottomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Get references to the text message
        mBottomMessageText = (TextView)(getView().findViewById(R.id.bottomMessageText));
    }

    //Called by the main activity to update message in the fragment.
    public void messageSentBack(CharSequence message){
        mBottomMessageText.setText(message);
        Log.i("BOTTOM_FRAGMENT","Updated bottom fragment message.");
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////