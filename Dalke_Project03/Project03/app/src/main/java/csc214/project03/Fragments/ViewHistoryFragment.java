////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csc214.project03.Adapters.AchievementRecyclerViewAdapter;
import csc214.project03.Adapters.RunHistoryRecyclerViewAdapter;
import csc214.project03.R;

////////////////////////////////////////
// View Run History List Fragment
////////////////////////////////////////

public class ViewHistoryFragment extends Fragment {

    private RunHistoryRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public ViewHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_history,container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.runHistoryRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RunHistoryRecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        mAdapter.notifyDataSetChanged();
        super.onResume();
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////