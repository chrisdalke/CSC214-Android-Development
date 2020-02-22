////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 6
////////////////////////////////////////

package csc214.assignment06.ListView.Fragments;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import csc214.assignment06.GameDialogFragment;
import csc214.assignment06.ListView.Adapters.CustomGameListAdapter;
import csc214.assignment06.Model.Game;
import csc214.assignment06.Model.GameListManager;

////////////////////////////////////////
// Custom List Fragment
////////////////////////////////////////

public class GameCustomListFragment extends ListFragment {

    private List<Game> mGameList;

    public GameCustomListFragment() {
        // required empty constructor
    }

    public void onCreate(Bundle state) {
        super.onCreate(state);
        mGameList = GameListManager.getGameList();

        //Set up list array adapter
        CustomGameListAdapter<Game> adapter = new CustomGameListAdapter<Game>(getActivity(), mGameList);
        setListAdapter(adapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameDialogFragment dialogFragment = GameDialogFragment.newInstance(mGameList.get(position));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                dialogFragment.show(fragmentManager,"Dialog");
            }
        });
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////