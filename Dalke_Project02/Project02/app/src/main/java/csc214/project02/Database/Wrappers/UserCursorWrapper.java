package csc214.project02.Database.Wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import csc214.project02.Database.TwiddlerDbSchema;
import csc214.project02.Model.Favorite;
import csc214.project02.Model.User;


public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {

        String email = getString(getColumnIndex(TwiddlerDbSchema.UserTable.Cols.EMAIL));
        String hashedPassword = getString(getColumnIndex(TwiddlerDbSchema.UserTable.Cols.HASHED_PASSWORD));
        String username = getString(getColumnIndex(TwiddlerDbSchema.UserTable.Cols.USERNAME));
        String fullName = getString(getColumnIndex(TwiddlerDbSchema.UserTable.Cols.FULL_NAME));
        String homeLocation = getString(getColumnIndex(TwiddlerDbSchema.UserTable.Cols.HOME_LOCATION));
        String bio = getString(getColumnIndex(TwiddlerDbSchema.UserTable.Cols.BIO));
        String photoFile = getString(getColumnIndex(TwiddlerDbSchema.UserTable.Cols.PHOTO_FILE));

        User returnUser = new User();
        returnUser.setEmail(email);
        returnUser.setHashedPassword(hashedPassword);
        returnUser.setUsername(username);
        returnUser.setFullName(fullName);
        returnUser.setHomeLocation(homeLocation);
        returnUser.setBio(bio);
        returnUser.setPhotoFile(photoFile);

        return returnUser;
    }
}
