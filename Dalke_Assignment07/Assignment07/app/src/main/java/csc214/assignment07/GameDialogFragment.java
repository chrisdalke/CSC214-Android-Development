////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 7
////////////////////////////////////////

package csc214.assignment07;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import csc214.assignment07.Model.Game;

////////////////////////////////////////
// Game Dialog Fragment
////////////////////////////////////////

public class GameDialogFragment extends DialogFragment {

    private static final String ARG_NAME = "ARG_NAME";
    private static final String ARG_DESC = "ARG_DESC";
    private static final String ARG_GENRE = "ARG_GENRE";
    private static final String ARG_PUBLISHER = "ARG_PUBLISHER";
    private static final String ARG_YEAR = "ARG_YEAR";

    public static GameDialogFragment newInstance(Game game) {

        GameDialogFragment dialog = new GameDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, game.getName());
        args.putString(ARG_DESC, game.getDescription());
        args.putString(ARG_GENRE, game.getGenre());
        args.putString(ARG_PUBLISHER, game.getPublisherName());
        args.putInt(ARG_YEAR, game.getReleaseYear());
        dialog.setArguments(args);

        return dialog;

    }

    public GameDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.game_dialog, null);
        Bundle args = getArguments();

        String name = args.getString(ARG_NAME);
        String description = args.getString(ARG_DESC);
        String genre = args.getString(ARG_GENRE);
        String publisher = args.getString(ARG_PUBLISHER);
        String year = Integer.toString(args.getInt(ARG_YEAR));

        String builtDialogLabel = "Genre: "+genre+"\n"+
                "Publisher: "+publisher+"\n"+
                "Year: "+year+"\n"+
                "Description: "+description;

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(name)
                .setMessage(builtDialogLabel)
                .setPositiveButton("Close", null)
                .create();

    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////