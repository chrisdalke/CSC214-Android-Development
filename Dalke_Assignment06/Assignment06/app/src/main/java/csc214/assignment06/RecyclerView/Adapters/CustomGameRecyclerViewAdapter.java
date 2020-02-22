package csc214.assignment06.RecyclerView.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import csc214.assignment06.GameDialogFragment;
import csc214.assignment06.Model.Game;
import csc214.assignment06.R;


public class CustomGameRecyclerViewAdapter extends RecyclerView.Adapter<CustomGameRecyclerViewAdapter.GameHolder> {

    private List<Game> mGameList;

    public CustomGameRecyclerViewAdapter(List<Game> mGameList) {
        this.mGameList = mGameList;
    }

    public GameHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_game, parent, false);

        GameHolder holder = new GameHolder(view);
        return holder;
    }

    public int getItemCount() {
        return mGameList.size();
    }

    public void onBindViewHolder(GameHolder holder, int position) {
        Game game = mGameList.get(position);
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