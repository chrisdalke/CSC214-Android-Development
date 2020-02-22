////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 01
////////////////////////////////////////

package csc214.project01.Fragments;

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
import android.widget.TextView;
import org.w3c.dom.Text;
import csc214.project01.R;

////////////////////////////////////////
// Scoreboard Fragment
////////////////////////////////////////

public class ScoreboardFragment extends Fragment {

    private ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    private TextView mPlayerOneName;
    private TextView mPlayerTwoName;
    private TextView mPlayerOneScore;
    private TextView mPlayerTwoScore;
    private View mPlayerOneAccent;
    private View mPlayerTwoAccent;

    public ScoreboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPlayerOneName = (TextView) (getView().findViewById(R.id.playerOneNameLabel));
        mPlayerTwoName = (TextView) (getView().findViewById(R.id.playerTwoNameLabel));
        mPlayerOneScore = (TextView) (getView().findViewById(R.id.playerOneScoreLabel));
        mPlayerTwoScore = (TextView) (getView().findViewById(R.id.playerTwoScoreLabel));
        mPlayerOneAccent = (getView().findViewById(R.id.playerOneAccent));
        mPlayerTwoAccent = (getView().findViewById(R.id.playerTwoAccent));

        mScoreboardModel.updateFunction = new Runnable() {
            @Override
            public void run() {
                updateScoreboardView();
            }
        };

        updateScoreboardView();
    }

    public void updateScoreboardView(){
        mPlayerOneName.setText(mScoreboardModel.getPlayerOneName());
        mPlayerTwoName.setText(mScoreboardModel.getPlayerTwoName());
        mPlayerOneScore.setText(String.valueOf(mScoreboardModel.getPlayerOneScore()));
        mPlayerTwoScore.setText(String.valueOf(mScoreboardModel.getPlayerTwoScore()));
        if (mScoreboardModel.isPlayerOneHighlight()){
            mPlayerOneAccent.setBackgroundColor(getResources().getColor(R.color.colorAccent,null));
        } else {
            mPlayerOneAccent.setBackgroundColor(getResources().getColor(R.color.white,null));
        }
        if (mScoreboardModel.isPlayerTwoHighlight()){
            mPlayerTwoAccent.setBackgroundColor(getResources().getColor(R.color.colorAccent,null));
        } else {
            mPlayerTwoAccent.setBackgroundColor(getResources().getColor(R.color.white,null));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scoreboard, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mScoreboardModel.updateFunction = null;
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////