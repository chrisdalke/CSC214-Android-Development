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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

////////////////////////////////////////
// Fragment Lifecycle Logger
////////////////////////////////////////

//Class extending generic fragment, logs all of the fragment lifecycle methods.
public class FragmentLifecycleLogger extends Fragment {

    //Get tag based on the classname, since this class gets extended the tag will be
    //the name of any subclass that then calls these methods.
    protected final String TAG = getClass().getName();

    public FragmentLifecycleLogger() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"Called onCreateView lifecycle method for fragment.");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.i(TAG,"Called onAttach lifecycle method for fragment.");
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG,"Called onAttach lifecycle method for fragment.");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"Called onCreate lifecycle method for fragment.");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG,"Called onActivityCreated lifecycle method for fragment.");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(TAG,"Called onStart lifecycle method for fragment.");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG,"Called onResume lifecycle method for fragment.");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG,"Called onPause lifecycle method for fragment.");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG,"Called onStop lifecycle method for fragment.");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG,"Called onDestroyView lifecycle method for fragment.");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"Called onDestroy lifecycle method for fragment.");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG,"Called onDetach lifecycle method for fragment.");
        super.onDetach();
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////