////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import csc214.project03.Adapters.AchievementRecyclerViewAdapter;
import csc214.project03.HomeActivity;
import csc214.project03.Model.Achievements.AchievementCollection;
import csc214.project03.R;

////////////////////////////////////////
// Achievements Fragment
////////////////////////////////////////

public class AchievementsFragment extends Fragment {

    private AchievementCollection mAchievementCollection;
    private RecyclerView mRecyclerView;
    private AchievementRecyclerViewAdapter mAdapter;
    private TextView mAchievementProgressText;

    public AchievementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achievements,container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.achievementRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new AchievementRecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mAchievementProgressText = (TextView)view.findViewById(R.id.achievement_progress_text);

        update();

        return view;
    }

    @Override
    public void onResume() {
        update();
        super.onResume();
    }

    public void update(){
        mAdapter.update();
        int numAchievements = mAdapter.getCollection().getNumAchievements();
        int numAchievementsSatisfied = mAdapter.getCollection().getNumSatisfiedAchievements();

        mAchievementProgressText.setText(numAchievementsSatisfied+"/"+numAchievements+" Achievements unlocked. Keep up the good work!");

    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////