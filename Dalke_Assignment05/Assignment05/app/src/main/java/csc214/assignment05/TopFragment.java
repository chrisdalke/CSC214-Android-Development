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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

////////////////////////////////////////
// Top Fragment
////////////////////////////////////////

public class TopFragment extends FragmentLifecycleLogger {

    // Instance variables
    private String mMessage = "No message received.";
    private TextView mMessageReceivedTextView;
    private EditText mMessageResponseEdit;
    private Button mMessageResponseButton;
    private MessageResponseListener mResponseListener;

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Get references to UI elements
        mMessageReceivedTextView = (TextView)(getView().findViewById(R.id.messageReceivedTextView));
        mMessageResponseEdit = (EditText) (getView().findViewById(R.id.messageResponseEdit));
        mMessageResponseButton = (Button)(getView().findViewById(R.id.messageResponseButton));

        //Set onclick listener for response button to call the callback listener function
        mMessageResponseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResponseListener.messageResponse(mMessageResponseEdit.getText().toString());
            }
        });

        //Update message text
        mMessageReceivedTextView.setText(mMessage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Get the message string out of the bundle arguments
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey("message")) {
                mMessage = args.getString("message");
            }
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    //Callback interface. Used by topFragment to communicate message to bottom fragment.
    //The activity will implement this
    public interface MessageResponseListener {
        void messageResponse(CharSequence message);
    }

    //Override the onAttach methods to get an instance of the listener.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mResponseListener = (MessageResponseListener)context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mResponseListener = (MessageResponseListener)activity;
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////