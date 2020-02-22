////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02.RecyclerView.Adapters;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import csc214.project02.Database.Collections.FavoriteDatabaseCollection;
import csc214.project02.Database.Collections.UserDatabaseCollection;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.Favorite;
import csc214.project02.Model.User;
import csc214.project02.R;
import csc214.project02.Activities.Account.AccountViewActivity;

////////////////////////////////////////
// User Recycler ViewAdapter
////////////////////////////////////////

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserHolder> {

    public enum USER_FILTER_TYPE {
        All, Following, Followers, AllOthers
    }

    private UserDatabaseCollection mUserCollection;
    private FavoriteDatabaseCollection mFavoriteCollection;
    private USER_FILTER_TYPE filter;
    private User mUser;
    private Fragment parent;

    public UserRecyclerViewAdapter(Fragment parent, Context context, USER_FILTER_TYPE type, User user) {
        this.parent = parent;
        mUserCollection = new UserDatabaseCollection(context);
        mFavoriteCollection = new FavoriteDatabaseCollection(context);
        filter = type;
        mUser = user;
    }

    public UserHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_user, parent, false);

        UserHolder holder = new UserHolder(view);
        return holder;
    }

    public int getItemCount() {
        return getCollection().size();
    }

    public List<User> getCollection(){
        switch (filter){
            case All:{
                return mUserCollection.getAllUsers();
            }
            case Followers:{
                return mFavoriteCollection.getFollowerUsers(mUser);
            }
            case Following:{
                return mFavoriteCollection.getFollowingUsers(mUser);
            }
            case AllOthers:{
                return mUserCollection.getOtherUsers(mUser);
            }
            default:{

                return mUserCollection.getAllUsers();
            }
        }
    }

    public void onBindViewHolder(UserHolder holder, int position) {
        User User = getCollection().get(position);
        holder.bindUser(User);
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        private User mUser;
        private View mView;

        private TextView mUserText;
        private Button mUserButton;
        private Button mFollowButton;
        private TextView mUserLocation;

        public UserHolder(View view) {
            super(view);
            mView = view;

            mUserText = (TextView)view.findViewById(R.id.view_username_text);
            mUserButton = (Button)view.findViewById(R.id.view_username_button);
            mFollowButton = (Button)view.findViewById(R.id.follow_username_button);
            mUserLocation = (TextView)view.findViewById(R.id.view_username_location);
        }

        public void bindUser(User user) {
            mUser = user;
            mUserText.setText(mUser.getUsername()+" ("+mUser.getFullName()+")");
            mUserLocation.setText(mUser.getHomeLocation());

            final FavoriteDatabaseCollection followerDb = new FavoriteDatabaseCollection(parent.getContext());

            if (followerDb.getFollowingUsers(ApplicationState.getInstance().getUser()).contains(mUser)){
                mFollowButton.setText("Unfollow");
            } else {
                mFollowButton.setText("Follow");
            }

            if (!(filter == USER_FILTER_TYPE.All | filter == USER_FILTER_TYPE.AllOthers)){
                mFollowButton.setVisibility(View.GONE);
            } else {
                mFollowButton.setVisibility(View.VISIBLE);
            }


            mFollowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Favorite favorite = new Favorite();
                    favorite.setFollowingUser(mUser.getUsername());
                    favorite.setFollowerUser(ApplicationState.getInstance().getUser().getUsername());
                    if (followerDb.getFollowingUsers(ApplicationState.getInstance().getUser()).contains(mUser)){
                        followerDb.removeFavorite(favorite);
                    } else {
                        followerDb.addFavorite(favorite);
                    }

                    //Refresh UI element
                    bindUser(mUser);
                }
            });
            mUserButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.getActivity().startActivity(AccountViewActivity.buildIntent(parent.getContext(),mUser));
                }
            });
        }
    }

}

////////////////////////////////////////
// End of File
////////////////////////////////////////