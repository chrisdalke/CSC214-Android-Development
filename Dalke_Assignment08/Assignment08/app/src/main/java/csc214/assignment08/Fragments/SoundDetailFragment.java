////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 8
////////////////////////////////////////

package csc214.assignment08.Fragments;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import csc214.assignment08.Model.Sound;
import csc214.assignment08.R;

////////////////////////////////////////
// Master/Detail View: Detail Fragment
////////////////////////////////////////

public class SoundDetailFragment extends Fragment {

    private Sound mSound;

    //Factory method
    public static SoundDetailFragment newInstance(Sound sound){
        SoundDetailFragment fragment = new SoundDetailFragment();
        fragment.mSound = sound;
        return fragment;
    }

    public SoundDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sound_detail, container, false);

        TextView soundLabel = (TextView) view.findViewById(R.id.detail_sound_name_text);
        try {
            soundLabel.setText(mSound.getName().split("\\.")[0]);

        } catch (Exception e){
            soundLabel.setText("Click a sound to play");

        }

        return view;
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////