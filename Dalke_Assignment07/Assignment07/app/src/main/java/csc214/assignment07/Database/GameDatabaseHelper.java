////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 7
////////////////////////////////////////

package csc214.assignment07.Database;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

////////////////////////////////////////
// Database Helper
////////////////////////////////////////

public class GameDatabaseHelper extends SQLiteOpenHelper {
    public GameDatabaseHelper(Context context) {
        super(context, GameDbSchema.DATABASE_NAME, null, GameDbSchema.VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + GameDbSchema.GameTable.NAME
                + "(_id integer primary key autoincrement, "
                + GameDbSchema.GameTable.Cols.NAME + ", "
                + GameDbSchema.GameTable.Cols.DESCRIPTION + ", "
                + GameDbSchema.GameTable.Cols.GENRE + ", "
                + GameDbSchema.GameTable.Cols.PUBLISHER + ", "
                + GameDbSchema.GameTable.Cols.RELEASE_YEAR + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////