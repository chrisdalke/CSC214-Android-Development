////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 01
////////////////////////////////////////

package csc214.project01.Games.Connect4;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import csc214.project01.Fragments.ScoreboardModel;
import csc214.project01.GameActivity;
import csc214.project01.GameFragment;
import csc214.project01.R;
import csc214.project01.SetNamesActivity;

////////////////////////////////////////
// Connect 4 Fragment
////////////////////////////////////////

public class Connect4Fragment extends GameFragment {

    private Connect4Model mConnect4Model = Connect4Model.getInstance();
    private ScoreboardModel mScoreboardModel = ScoreboardModel.getInstance();
    private HashMap<Integer,Integer> gridIds;
    private ImageView[][] grid;
    private ImageButton mGridButton1;
    private ImageButton mGridButton2;
    private ImageButton mGridButton3;
    private ImageButton mGridButton4;
    private ImageButton mGridButton5;
    private ImageButton mGridButton6;
    private ImageButton mGridButton7;
    private ImageButton mGridButton8;

    public Connect4Fragment() {
        // Required empty public constructor
        gridIds = new HashMap<>();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Get button references
        mGridButton1 = (ImageButton)(getView().findViewById(R.id.gridButton1));
        mGridButton2 = (ImageButton)(getView().findViewById(R.id.gridButton2));
        mGridButton3 = (ImageButton)(getView().findViewById(R.id.gridButton3));
        mGridButton4 = (ImageButton)(getView().findViewById(R.id.gridButton4));
        mGridButton5 = (ImageButton)(getView().findViewById(R.id.gridButton5));
        mGridButton6 = (ImageButton)(getView().findViewById(R.id.gridButton6));
        mGridButton7 = (ImageButton)(getView().findViewById(R.id.gridButton7));
        mGridButton8 = (ImageButton)(getView().findViewById(R.id.gridButton8));
        
        updateGridIds();
        grid = new ImageView[8][8];
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                grid[x][y] = (ImageView)(getView().findViewById(gridIds.get(x+(y*8))));
            }
        }

        //Add button callback handlers
        mGridButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (mConnect4Model.getWinner() == 0) {
                if (mConnect4Model.checkMoveValid(0)) {
                    mConnect4Model.makeMove(0);
                    updateUI();
                } else {
                    showInvalidMoveToast();
                }
            }
            }
        });
        mGridButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (mConnect4Model.getWinner() == 0) {
                if (mConnect4Model.checkMoveValid(1)) {
                    mConnect4Model.makeMove(1);
                    updateUI();
                } else {
                    showInvalidMoveToast();
                }
            }
            }
        });
        mGridButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnect4Model.getWinner() == 0) {
                    if (mConnect4Model.checkMoveValid(2)) {
                        mConnect4Model.makeMove(2);
                        updateUI();
                    } else {
                        showInvalidMoveToast();
                    }
                }
            }
        });
        mGridButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnect4Model.getWinner() == 0) {
                    if (mConnect4Model.checkMoveValid(3)) {
                        mConnect4Model.makeMove(3);
                        updateUI();
                    } else {
                        showInvalidMoveToast();
                    }
                }
            }
        });
        mGridButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnect4Model.getWinner() == 0) {
                    if (mConnect4Model.checkMoveValid(4)) {
                        mConnect4Model.makeMove(4);
                        updateUI();
                    } else {
                        showInvalidMoveToast();
                    }
                }
            }
        });
        mGridButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnect4Model.getWinner() == 0) {
                    if (mConnect4Model.checkMoveValid(5)) {
                        mConnect4Model.makeMove(5);
                        updateUI();
                    } else {
                        showInvalidMoveToast();
                    }
                }
            }
        });
        mGridButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnect4Model.getWinner() == 0) {
                    if (mConnect4Model.checkMoveValid(6)) {
                        mConnect4Model.makeMove(6);
                        updateUI();
                    } else {
                        showInvalidMoveToast();
                    }
                }
            }
        });
        mGridButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnect4Model.getWinner() == 0) {
                    if (mConnect4Model.checkMoveValid(7)) {
                        mConnect4Model.makeMove(7);
                        updateUI();
                    } else {
                        showInvalidMoveToast();
                    }
                }
            }
        });

        updateUI();

    }

    public void gameOver(){
        if (mConnect4Model.getWinner() == 1) {
            Toast.makeText(getContext(), mScoreboardModel.getPlayerOneName() + " has won Connect 4!", Toast.LENGTH_LONG).show();
            mScoreboardModel.setPlayerOneScore(mScoreboardModel.getPlayerOneScore()+100);
        } else if (mConnect4Model.getWinner() == 2){
            Toast.makeText(getContext(), mScoreboardModel.getPlayerTwoName() + " has won Connect 4!", Toast.LENGTH_LONG).show();
            mScoreboardModel.setPlayerTwoScore(mScoreboardModel.getPlayerTwoScore()+100);
        } else if (mConnect4Model.getWinner() == 3){
            Toast.makeText(getContext(), "The game was a tie!", Toast.LENGTH_LONG).show();
        }
        mScoreboardModel.triggerUpdate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connect4, container, false);

    }

    @Override
    public void reset() {
        mConnect4Model.reset();
        updateUI();
    }

    @Override
    public void updateUI() {
        //Update the scoreboard with the current player
        if (mConnect4Model.getCurrentPlayer() == 1){
            mScoreboardModel.setPlayerOneHighlight(true);
            mScoreboardModel.setPlayerTwoHighlight(false);
        } else {
            mScoreboardModel.setPlayerOneHighlight(false);
            mScoreboardModel.setPlayerTwoHighlight(true);
        }
        mScoreboardModel.triggerUpdate();

        //Update the grid
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++) {
                switch (mConnect4Model.getPosition(x,y)){
                    case 1: {
                        grid[x][y].setImageResource(R.drawable.ic_red_circle);
                        break;
                    }
                    case 2: {
                        grid[x][y].setImageResource(R.drawable.ic_black_circle);
                        break;
                    }
                    default: {
                        grid[x][y].setImageResource(R.drawable.ic_gray_circle);
                        break;
                    }
                }
            }
        }

        //Check game over condition
        if (mConnect4Model.getWinner() > 0){
            gameOver();
        }
    }


    public void showInvalidMoveToast(){
        Toast.makeText(getContext(),"Invalid move!",Toast.LENGTH_SHORT).show();
    }
    
    public void updateGridIds(){
        int i = 0;
        gridIds.put(i++,R.id.g1);
        gridIds.put(i++,R.id.g2);
        gridIds.put(i++,R.id.g3);
        gridIds.put(i++,R.id.g4);
        gridIds.put(i++,R.id.g5);
        gridIds.put(i++,R.id.g6);
        gridIds.put(i++,R.id.g7);
        gridIds.put(i++,R.id.g8);
        gridIds.put(i++,R.id.g9);
        gridIds.put(i++,R.id.g10);
        gridIds.put(i++,R.id.g11);
        gridIds.put(i++,R.id.g12);
        gridIds.put(i++,R.id.g13);
        gridIds.put(i++,R.id.g14);
        gridIds.put(i++,R.id.g15);
        gridIds.put(i++,R.id.g16);
        gridIds.put(i++,R.id.g17);
        gridIds.put(i++,R.id.g18);
        gridIds.put(i++,R.id.g19);
        gridIds.put(i++,R.id.g20);
        gridIds.put(i++,R.id.g21);
        gridIds.put(i++,R.id.g22);
        gridIds.put(i++,R.id.g23);
        gridIds.put(i++,R.id.g24);
        gridIds.put(i++,R.id.g25);
        gridIds.put(i++,R.id.g26);
        gridIds.put(i++,R.id.g27);
        gridIds.put(i++,R.id.g28);
        gridIds.put(i++,R.id.g29);
        gridIds.put(i++,R.id.g30);
        gridIds.put(i++,R.id.g31);
        gridIds.put(i++,R.id.g32);
        gridIds.put(i++,R.id.g33);
        gridIds.put(i++,R.id.g34);
        gridIds.put(i++,R.id.g35);
        gridIds.put(i++,R.id.g36);
        gridIds.put(i++,R.id.g37);
        gridIds.put(i++,R.id.g38);
        gridIds.put(i++,R.id.g39);
        gridIds.put(i++,R.id.g40);
        gridIds.put(i++,R.id.g41);
        gridIds.put(i++,R.id.g42);
        gridIds.put(i++,R.id.g43);
        gridIds.put(i++,R.id.g44);
        gridIds.put(i++,R.id.g45);
        gridIds.put(i++,R.id.g46);
        gridIds.put(i++,R.id.g47);
        gridIds.put(i++,R.id.g48);
        gridIds.put(i++,R.id.g49);
        gridIds.put(i++,R.id.g50);
        gridIds.put(i++,R.id.g51);
        gridIds.put(i++,R.id.g52);
        gridIds.put(i++,R.id.g53);
        gridIds.put(i++,R.id.g54);
        gridIds.put(i++,R.id.g55);
        gridIds.put(i++,R.id.g56);
        gridIds.put(i++,R.id.g57);
        gridIds.put(i++,R.id.g58);
        gridIds.put(i++,R.id.g59);
        gridIds.put(i++,R.id.g60);
        gridIds.put(i++,R.id.g61);
        gridIds.put(i++,R.id.g62);
        gridIds.put(i++,R.id.g63);
        gridIds.put(i++,R.id.g64);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////