////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02.Database.Collections;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import csc214.project02.Database.TwiddlerDbHelper;
import csc214.project02.Database.TwiddlerDbSchema;
import csc214.project02.Database.Wrappers.FavoriteCursorWrapper;
import csc214.project02.Model.Favorite;
import csc214.project02.Model.Post;
import csc214.project02.Model.User;

////////////////////////////////////////
// Favorites Database Collection
////////////////////////////////////////

public class FavoriteDatabaseCollection {

    private final Context mContext;
    private final SQLiteDatabase mDatabase;

    public FavoriteDatabaseCollection(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TwiddlerDbHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(Favorite favorite) {
        ContentValues values = new ContentValues();

        values.put(TwiddlerDbSchema.FavoriteTable.Cols.FOLLOWER_USER, favorite.getFollowerUser());
        values.put(TwiddlerDbSchema.FavoriteTable.Cols.FOLLOWING_USER, favorite.getFollowingUser());
        return values;
    }

    public void addFavorite(Favorite instance){
        mDatabase.insert(TwiddlerDbSchema.FavoriteTable.NAME,null,getContentValues(instance));
    }

    public void updateFavorite(Favorite instance){
        mDatabase.update(TwiddlerDbSchema.FavoriteTable.NAME,getContentValues(instance),"followingUser=? and followerUser=?",new String[]{instance.getFollowingUser(),instance.getFollowerUser()});
    }

    public void removeFavorite(Favorite instance){
        mDatabase.delete(TwiddlerDbSchema.FavoriteTable.NAME,"followingUser=? and followerUser=?",new String[]{instance.getFollowingUser(),instance.getFollowerUser()});
    }

    public void removeAll(){
        mDatabase.delete(TwiddlerDbSchema.FavoriteTable.NAME,null,null);
    }

    public ArrayList<User> getFollowerUsers(User user){
        ArrayList<User> following = new ArrayList<>();

        //Get list of users that the specified user is followed by
        //Don't include the current user in the list

        Cursor cursor = mDatabase.query(TwiddlerDbSchema.FavoriteTable.NAME,  null, "followingUser=?", new String[]{user.getUsername()}, null,null,null);
        FavoriteCursorWrapper wrapper = new FavoriteCursorWrapper(cursor);
        UserDatabaseCollection userDatabaseCollection = new UserDatabaseCollection(mContext);

        try {
            wrapper.moveToFirst();
            while(!wrapper.isAfterLast()) {
                following.add(userDatabaseCollection.getUserByUsername(wrapper.getFavorite().getFollowerUser()));
                wrapper.moveToNext();
            }
        } finally {
            wrapper.close();
        }
        return following;
    }

    public ArrayList<User> getFollowingUsers(User user){
        ArrayList<User> following = new ArrayList<>();

        //Get list of users that the specified user is following
        //Don't include the current user in the list

        Cursor cursor = mDatabase.query(TwiddlerDbSchema.FavoriteTable.NAME,  null, "followerUser=?", new String[]{user.getUsername()}, null,null,null);
        FavoriteCursorWrapper wrapper = new FavoriteCursorWrapper(cursor);
        UserDatabaseCollection userDatabaseCollection = new UserDatabaseCollection(mContext);

        try {
            wrapper.moveToFirst();
            while(!wrapper.isAfterLast()) {
                following.add(userDatabaseCollection.getUserByUsername(wrapper.getFavorite().getFollowingUser()));
                wrapper.moveToNext();
            }
        } finally {
            wrapper.close();
        }
        return following;
    }

    public List<User> getFeedUsers(User username){
        //Get list of users that the specified user is following
        //include the current user in the list since we want that user's posts as well

        ArrayList<User> following = getFollowingUsers(username);
        following.add(username);
        return following;

    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////