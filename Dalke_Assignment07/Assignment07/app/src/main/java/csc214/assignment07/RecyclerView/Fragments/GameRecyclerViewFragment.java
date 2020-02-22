////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 7
////////////////////////////////////////

package csc214.assignment07.RecyclerView.Fragments;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import csc214.assignment07.R;
import csc214.assignment07.RecyclerView.Adapters.CustomGameRecyclerViewDbAdapter;

////////////////////////////////////////
// Game Recycler View Fragment
////////////////////////////////////////

public class GameRecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CustomGameRecyclerViewDbAdapter mRecyclerViewAdapter;

    public GameRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_recycler_view,container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.gameRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerViewAdapter = new CustomGameRecyclerViewDbAdapter(getContext());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        return view;
    }

    public void refreshList(){
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////