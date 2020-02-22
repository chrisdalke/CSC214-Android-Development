package csc214.project02.Database.Wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import csc214.project02.Database.TwiddlerDbSchema;
import csc214.project02.Model.Favorite;

public class FavoriteCursorWrapper extends CursorWrapper {
    public FavoriteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Favorite getFavorite() {

        String followerUser = getString(getColumnIndex(TwiddlerDbSchema.FavoriteTable.Cols.FOLLOWER_USER));
        String followingUser = getString(getColumnIndex(TwiddlerDbSchema.FavoriteTable.Cols.FOLLOWING_USER));

        Favorite returnFavorite = new Favorite();
        returnFavorite.setFollowerUser(followerUser);
        returnFavorite.setFollowingUser(followingUser);
        return returnFavorite;
    }
}

