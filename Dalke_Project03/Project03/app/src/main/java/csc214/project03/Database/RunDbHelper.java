////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Database;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

////////////////////////////////////////
// Database Helper
////////////////////////////////////////

public class RunDbHelper extends SQLiteOpenHelper {
    public RunDbHelper(Context context) {
        super(context, RunDbSchema.DATABASE_NAME, null, RunDbSchema.VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RunDbSchema.RunTable.NAME
                + "(_id integer primary key autoincrement, "
                + RunDbSchema.RunTable.Cols.ID + ", "
                + RunDbSchema.RunTable.Cols.DISTANCE + ", "
                + RunDbSchema.RunTable.Cols.DURATION + ", "
                + RunDbSchema.RunTable.Cols.DAY + ", "
                + RunDbSchema.RunTable.Cols.WEEK + ", "
                + RunDbSchema.RunTable.Cols.MONTH + ", "
                + RunDbSchema.RunTable.Cols.YEAR + ", "
                + RunDbSchema.RunTable.Cols.HAS_EXTRA_IMAGE + ", "
                + RunDbSchema.RunTable.Cols.HAS_EXTRA_LOCATION + ", "
                + RunDbSchema.RunTable.Cols.HAS_EXTRA_WEATHER + ", "
                + RunDbSchema.RunTable.Cols.EXTRA_IMAGE + ", "
                + RunDbSchema.RunTable.Cols.EXTRA_WEATHER + ", "
                + RunDbSchema.RunTable.Cols.EXTRA_LOCATION_LAT + ", "
                + RunDbSchema.RunTable.Cols.EXTRA_LOCATION_LON + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////