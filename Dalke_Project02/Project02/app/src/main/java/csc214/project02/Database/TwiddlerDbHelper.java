////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02.Database;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

////////////////////////////////////////
// Database Helper
////////////////////////////////////////

public class TwiddlerDbHelper extends SQLiteOpenHelper {
    public TwiddlerDbHelper(Context context) {
        super(context, TwiddlerDbSchema.DATABASE_NAME, null, TwiddlerDbSchema.VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TwiddlerDbSchema.UserTable.NAME
                + "(_id integer primary key autoincrement, "
                + TwiddlerDbSchema.UserTable.Cols.EMAIL + ", "
                + TwiddlerDbSchema.UserTable.Cols.HASHED_PASSWORD + ", "
                + TwiddlerDbSchema.UserTable.Cols.USERNAME + ", "
                + TwiddlerDbSchema.UserTable.Cols.FULL_NAME + ", "
                + TwiddlerDbSchema.UserTable.Cols.HOME_LOCATION + ", "
                + TwiddlerDbSchema.UserTable.Cols.BIO + ", "
                + TwiddlerDbSchema.UserTable.Cols.PHOTO_FILE + ")");

        db.execSQL("create table " + TwiddlerDbSchema.FavoriteTable.NAME
                + "(_id integer primary key autoincrement, "
                + TwiddlerDbSchema.FavoriteTable.Cols.FOLLOWER_USER + ", "
                + TwiddlerDbSchema.FavoriteTable.Cols.FOLLOWING_USER + ")");

        db.execSQL("create table " + TwiddlerDbSchema.PostTable.NAME
                + "(_id integer primary key autoincrement, "
                + TwiddlerDbSchema.PostTable.Cols.POST_ID + ", "
                + TwiddlerDbSchema.PostTable.Cols.POST_CONTENTS + ", "
                + TwiddlerDbSchema.PostTable.Cols.POST_DATE + ", "
                + TwiddlerDbSchema.PostTable.Cols.POST_IMAGE + ", "
                + TwiddlerDbSchema.PostTable.Cols.POST_IMAGE_FILENAME + ", "
                + TwiddlerDbSchema.PostTable.Cols.POST_USER + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////