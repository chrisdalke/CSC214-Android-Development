////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 7
////////////////////////////////////////

package csc214.assignment07.RecyclerView.Adapters;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import csc214.assignment07.GameDialogFragment;
import csc214.assignment07.Model.Game;
import csc214.assignment07.Model.GameListDatabaseCollection;
import csc214.assignment07.R;

////////////////////////////////////////
// Game Recycler ViewAdapter
////////////////////////////////////////

public class CustomGameRecyclerViewDbAdapter extends RecyclerView.Adapter<CustomGameRecyclerViewDbAdapter.GameHolder> {

    private GameListDatabaseCollection mGameListCollection;

    public CustomGameRecyclerViewDbAdapter(Context context) {
        mGameListCollection = new GameListDatabaseCollection(context);
    }

    public GameHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_game, parent, false);

        GameHolder holder = new GameHolder(view);
        return holder;
    }

    public int getItemCount() {
        return mGameListCollection.getGames().size();
    }

    public void onBindViewHolder(GameHolder holder, int position) {
        Game game = mGameListCollection.getGames().get(position);
        holder.bindGame(game);
    }

    public class GameHolder extends RecyclerView.ViewHolder {

        private TextView textViewGameName;
        private TextView textViewYear;
        private TextView textViewPublisher;
        private TextView textViewGenre;
        private TextView textViewDescription;
        private Game mGame;
        private View mView;

        public GameHolder(View view) {

            super(view);

            mView = view;

            textViewGameName = (TextView)view.findViewById(R.id.textViewGameName);
            textViewYear = (TextView)view.findViewById(R.id.textViewYear);
            textViewPublisher = (TextView)view.findViewById(R.id.textViewPublisher);
            textViewGenre = (TextView)view.findViewById(R.id.textViewGenre);
            textViewDescription = (TextView)view.findViewById(R.id.textViewDescription);

        }

        public void bindGame(Game game) {
            mGame = game;
            textViewGameName.setText(game.getName());
            textViewYear.setText("("+Integer.toString(game.getReleaseYear())+")");
            textViewPublisher.setText(game.getPublisherName());
            textViewGenre.setText(game.getGenre());
            textViewDescription.setText(game.getDescription());

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GameDialogFragment dialogFragment = GameDialogFragment.newInstance(mGame);

                    FragmentManager fragmentManager = ((FragmentActivity) mView.getContext()).getSupportFragmentManager();
                    dialogFragment.show(fragmentManager,"Dialog");
                }
            });
        }
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////