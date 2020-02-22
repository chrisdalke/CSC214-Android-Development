package csc214.assignment06.ListView.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import csc214.assignment06.Model.Game;
import csc214.assignment06.R;


public class CustomGameListAdapter<T extends Game> extends ArrayAdapter<T> {

    public CustomGameListAdapter(Context c, List<T> items) {

        super(c, 0, items);

    }

    public View getView(int position, View layout, ViewGroup parent) {
        if(layout == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            layout = inflater.inflate(R.layout.view_game, parent, false);
        }

        TextView textViewGameName = (TextView)layout.findViewById(R.id.textViewGameName);
        TextView textViewYear = (TextView)layout.findViewById(R.id.textViewYear);
        TextView textViewPublisher = (TextView)layout.findViewById(R.id.textViewPublisher);
        TextView textViewGenre = (TextView)layout.findViewById(R.id.textViewGenre);
        TextView textViewDescription = (TextView)layout.findViewById(R.id.textViewDescription);

        T game = getItem(position);
        textViewGameName.setText(game.getName());
        textViewYear.setText("("+Integer.toString(game.getReleaseYear())+")");
        textViewPublisher.setText(game.getPublisherName());
        textViewGenre.setText(game.getGenre());
        textViewDescription.setText(game.getDescription());
        return layout;

    }

}