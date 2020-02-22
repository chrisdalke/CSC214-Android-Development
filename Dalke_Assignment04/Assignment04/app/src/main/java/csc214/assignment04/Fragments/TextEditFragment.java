////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 4
////////////////////////////////////////

package csc214.assignment04.Fragments;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import csc214.assignment04.FontEditActivity;
import csc214.assignment04.FontModel;
import csc214.assignment04.R;

////////////////////////////////////////
// Text Edit Fragment:
// UI for editing of message displayed in main activity.
////////////////////////////////////////

public class TextEditFragment extends Fragment {

    //Get instances of model singleton
    private FontModel mFontModel = FontModel.getInstance();

    //Member variables for UI elements
    private EditText mEditMessageText;
    private Button mCancelButton;
    private Button mConfirmButton;

    public TextEditFragment() {
        // Required empty public constructor
    }

    public void saveText(){
        mFontModel.setText(mEditMessageText.getText().toString());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mEditMessageText = (EditText)(getView().findViewById(R.id.editMessageText));
        mCancelButton = (Button)(getView().findViewById(R.id.fragmentButtonCancel));
        mConfirmButton = (Button)(getView().findViewById(R.id.fragmentButtonConfirm));
        mEditMessageText.setText(mFontModel.getText());

        //Create button click listeners
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save then finish
                saveText();
                getActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_edit, container, false);
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////