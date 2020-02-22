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

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import csc214.finalexam.R;

////////////////////////////////////////
// Problem Two - Top Fragment
////////////////////////////////////////

public class ProblemTwoTopFragment extends Fragment {

    private static final String PROBLEM_2_TOP_FRAGMENT = "PROBLEM_2_TOP_FRAGMENT";

    private EditText editTextInput;
    private Button buttonSendBelow;
    private TextView textViewResponse;

    private TopCallbackListener callbackListener;

    public ProblemTwoTopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onCreateView.");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_problem_two_top, container, false);

        editTextInput = (EditText)view.findViewById(R.id.et_input);
        buttonSendBelow = (Button)view.findViewById(R.id.b_send_below);
        textViewResponse = (TextView)view.findViewById(R.id.tv_response);

        buttonSendBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackListener != null){
                    callbackListener.sendBelow(editTextInput.getText().toString());
                }
            }
        });

        return view;
    }

    public void updateTextMessage(String text){
        textViewResponse.setText(text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onCreate.");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onResume.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onStart.");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackListener = (TopCallbackListener) context;
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onAttach.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onPause.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onStop.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onDestroy.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onDestroyView.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbackListener = null;
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onDetach.");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onActivityCreated.");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i(PROBLEM_2_TOP_FRAGMENT,"Problem 2 Top Fragment - onViewStateRestored.");
    }

    public interface TopCallbackListener {
        void sendBelow(String text);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
