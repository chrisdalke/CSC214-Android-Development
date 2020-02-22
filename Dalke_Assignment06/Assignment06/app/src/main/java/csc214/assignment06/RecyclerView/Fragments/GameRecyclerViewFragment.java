package csc214.assignment06.RecyclerView.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import csc214.assignment06.RecyclerView.Adapters.CustomGameRecyclerViewAdapter;
import csc214.assignment06.Model.Game;
import csc214.assignment06.Model.GameListManager;
import csc214.assignment06.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameRecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<Game> mGameList;

    public GameRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_recycler_view,container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.gameRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mGameList = GameListManager.getGameList();
        mRecyclerView.setAdapter(new CustomGameRecyclerViewAdapter(mGameList));

        return view;
    }

}
