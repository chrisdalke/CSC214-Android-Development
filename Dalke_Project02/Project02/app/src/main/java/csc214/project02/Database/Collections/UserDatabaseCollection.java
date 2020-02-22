package csc214.project02.Database.Collections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import csc214.project02.Database.TwiddlerDbHelper;
import csc214.project02.Database.TwiddlerDbSchema;
import csc214.project02.Database.Wrappers.FavoriteCursorWrapper;
import csc214.project02.Database.Wrappers.UserCursorWrapper;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.Favorite;
import csc214.project02.Model.User;

public class UserDatabaseCollection {

    private final Context mContext;
    private final SQLiteDatabase mDatabase;

    public UserDatabaseCollection(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TwiddlerDbHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();

        values.put(TwiddlerDbSchema.UserTable.Cols.BIO, user.getBio());
        values.put(TwiddlerDbSchema.UserTable.Cols.EMAIL, user.getEmail());
        values.put(TwiddlerDbSchema.UserTable.Cols.FULL_NAME, user.getFullName());
        values.put(TwiddlerDbSchema.UserTable.Cols.HASHED_PASSWORD, user.getHashedPassword());
        values.put(TwiddlerDbSchema.UserTable.Cols.HOME_LOCATION, user.getHomeLocation());
        values.put(TwiddlerDbSchema.UserTable.Cols.PHOTO_FILE, user.getPhotoFile());
        values.put(TwiddlerDbSchema.UserTable.Cols.USERNAME, user.getUsername());
        return values;
    }

    public void addUser(User instance){
        mDatabase.insert(TwiddlerDbSchema.UserTable.NAME,null,getContentValues(instance));
    }

    public void updateUser(User instance){
        mDatabase.update(TwiddlerDbSchema.UserTable.NAME,getContentValues(instance),"username=?",new String[]{instance.getUsername()});
    }

    public void removeUser(User instance){
        mDatabase.delete(TwiddlerDbSchema.UserTable.NAME,"username=?",new String[]{instance.getUsername()});
    }

    public void removeAll(){
        mDatabase.delete(TwiddlerDbSchema.UserTable.NAME,null,null);
    }

    public User getUserByUsername(String username){
        Cursor cursor = mDatabase.query(TwiddlerDbSchema.UserTable.NAME,  null, "username=?", new String[]{username}, null,null,null);
        UserCursorWrapper wrapper = new UserCursorWrapper(cursor);
        User returnUser = null;
        try {
            wrapper.moveToFirst();
            returnUser = wrapper.getUser();
            wrapper.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return returnUser;
    }

    public User getUserByEmail(String email){
        Cursor cursor = mDatabase.query(TwiddlerDbSchema.UserTable.NAME,  null, "email=?", new String[]{email}, null,null,null);
        UserCursorWrapper wrapper = new UserCursorWrapper(cursor);
        User returnUser = null;
        try {
            wrapper.moveToFirst();
            returnUser = wrapper.getUser();
            wrapper.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return returnUser;
    }

    public User getUserLogin(String username, String hashedPassword){
        Cursor cursor = mDatabase.query(TwiddlerDbSchema.UserTable.NAME,  null, "username=? and hashedPassword=?", new String[]{username,hashedPassword}, null,null,null);
        UserCursorWrapper wrapper = new UserCursorWrapper(cursor);
        User returnUser = null;
        try {
            wrapper.moveToFirst();
            returnUser = wrapper.getUser();
            wrapper.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return returnUser;

    }

    public ArrayList<User> getAllUsers(){

        ArrayList<User> allUsers = new ArrayList<>();
        Cursor cursor = mDatabase.query(TwiddlerDbSchema.UserTable.NAME,  null, null, null, null,null,null);
        UserCursorWrapper wrapper = new UserCursorWrapper(cursor);

        try {
            wrapper.moveToFirst();
            while(!wrapper.isAfterLast()) {
                allUsers.add(wrapper.getUser());
                wrapper.moveToNext();
            }
        } finally {
            wrapper.close();
        }
        return allUsers;
    }

    public ArrayList<User> getOtherUsers(User user){
        ArrayList<User> temp = getAllUsers();
        temp.remove(user);
        return temp;
    }
}
