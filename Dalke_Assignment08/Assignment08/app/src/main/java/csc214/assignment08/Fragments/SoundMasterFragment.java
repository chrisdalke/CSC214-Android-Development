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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import csc214.assignment08.Model.SoundCollection;
import csc214.assignment08.Model.SoundManager;
import csc214.assignment08.R;

////////////////////////////////////////
// Master/Detail: Master (List) Fragment
////////////////////////////////////////

public class SoundMasterFragment extends Fragment {

    private SoundManager mSoundManager;
    private RecyclerView mRecyclerView;

    public SoundManager getSoundManager() {
        return mSoundManager;
    }

    public void clickSound(int id){
        //We clicked a sound with the given id
        //Use callback interface to tell main activity about it
        mListener.OnDetailItemClick(id);

        //Play the sound
        mSoundManager.play(SoundCollection.getSoundList().get(id));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sound_master,container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.sound_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new SoundRecyclerViewAdapter(this));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        mSoundManager = new SoundManager(getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSoundManager.release();
    }

    public SoundMasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (DetailItemClickListener)activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DetailItemClickListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface DetailItemClickListener {
        void OnDetailItemClick(int itemNumber);
    }

    private DetailItemClickListener mListener;

}

////////////////////////////////////////
// End of File
////////////////////////////////////////