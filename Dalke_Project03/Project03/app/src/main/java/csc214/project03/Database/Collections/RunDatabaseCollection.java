////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Database.Collections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import csc214.project03.Database.RunDbHelper;
import csc214.project03.Database.RunDbSchema;
import csc214.project03.Database.Wrappers.RunCursorWrapper;
import csc214.project03.Model.Runs.Run;

////////////////////////////////////////
// Run Database Collection
////////////////////////////////////////

public class RunDatabaseCollection {

    private final Context mContext;
    private final SQLiteDatabase mDatabase;

    public RunDatabaseCollection(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new RunDbHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(Run run) {
        ContentValues values = new ContentValues();

        values.put(RunDbSchema.RunTable.Cols.ID,Integer.toString(run.getId()));
        values.put(RunDbSchema.RunTable.Cols.DISTANCE,Float.toString(run.getDistance()));
        values.put(RunDbSchema.RunTable.Cols.DURATION,run.getDuration());
        values.put(RunDbSchema.RunTable.Cols.DAY ,Integer.toString(run.getDay()));
        values.put(RunDbSchema.RunTable.Cols.WEEK, Integer.toString(run.getWeek()));
        values.put(RunDbSchema.RunTable.Cols.MONTH, Integer.toString(run.getMonth()));
        values.put(RunDbSchema.RunTable.Cols.YEAR ,Integer.toString(run.getYear()));
        values.put(RunDbSchema.RunTable.Cols.HAS_EXTRA_IMAGE,run.getExtraHasImage());
        values.put(RunDbSchema.RunTable.Cols.HAS_EXTRA_WEATHER,run.getExtraHasWeather());
        values.put(RunDbSchema.RunTable.Cols.HAS_EXTRA_LOCATION,run.getExtraHasLocation());
        values.put(RunDbSchema.RunTable.Cols.EXTRA_IMAGE,run.getExtraImage());
        values.put(RunDbSchema.RunTable.Cols.EXTRA_WEATHER,run.getExtraWeatherData());
        values.put(RunDbSchema.RunTable.Cols.EXTRA_LOCATION_LAT,run.getLat()+"");
        values.put(RunDbSchema.RunTable.Cols.EXTRA_LOCATION_LON,run.getLon()+"");

        return values;
    }

    public void addRun(Run instance){
        instance.setId(getNumRuns() + 1);
        mDatabase.insert(RunDbSchema.RunTable.NAME,null,getContentValues(instance));
    }

    public void updateRun(Run instance){
        mDatabase.update(RunDbSchema.RunTable.NAME,getContentValues(instance),"id=?",new String[]{Integer.toString(instance.getId())});
    }

    public void removeRun(Run instance){
        mDatabase.delete(RunDbSchema.RunTable.NAME,"id=?",new String[]{Integer.toString(instance.getId())});
    }

    public int getNumRuns(){
        Cursor cursor= mDatabase.rawQuery("SELECT COUNT (*) FROM " + RunDbSchema.RunTable.NAME,null);
        cursor.moveToFirst();
        int count=cursor.getInt(0);
        cursor.close();
        return count;
    }

    public Run getRunById(int id){

        Cursor cursor = mDatabase.query(RunDbSchema.RunTable.NAME,  null, "id=?", new String[]{Integer.toString(id)}, null,null,null);
        RunCursorWrapper wrapper = new RunCursorWrapper(cursor);
        Run returnRun = null;
        try {
            wrapper.moveToFirst();
            returnRun = wrapper.getRun();
            wrapper.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return returnRun;
    }

    public void removeAll(){
        mDatabase.delete(RunDbSchema.RunTable.NAME,null,null);
    }

    public ArrayList<Run> getAllRuns(){
        ArrayList<Run> runArray = new ArrayList<>();

        Cursor cursor = mDatabase.query(RunDbSchema.RunTable.NAME,  null, null, null, null,null,null);
        RunCursorWrapper wrapper = new RunCursorWrapper(cursor);

        try {
            wrapper.moveToFirst();
            while(!wrapper.isAfterLast()) {
                runArray.add(wrapper.getRun());
                wrapper.moveToNext();
            }
        } finally {
            wrapper.close();
        }
        sortArray(runArray);
        return runArray;
    }

    private void sortArray(ArrayList<Run> runArray){
        Collections.sort(runArray, new Comparator<Run>() {
            @Override
            public int compare(Run o1, Run o2) {
                try {
                    return -Integer.compare(o1.getId(),o2.getId());
                } catch (Exception e){
                    e.printStackTrace();
                    return 0;
                }
            }
        });
    }

    public boolean hasRunWithDistance(float distance){
        ArrayList<Run> runs = getAllRuns();
        for (Run run : runs){
            if (run.getDistance() >= distance){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Run> getRunsForWeek(int week, int year){
        ArrayList<Run> runArray = new ArrayList<>();

        Cursor cursor = mDatabase.query(RunDbSchema.RunTable.NAME,  null, "week=? and year=?", new String[]{Integer.toString(week),Integer.toString(year)}, null,null,null);
        RunCursorWrapper wrapper = new RunCursorWrapper(cursor);

        try {
            wrapper.moveToFirst();
            while (!wrapper.isAfterLast()) {
                runArray.add(wrapper.getRun());
                wrapper.moveToNext();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            wrapper.close();
        }
        sortArray(runArray);
        return runArray;
    }

    public ArrayList<Run> getRunsForMonth(int month, int year){
        ArrayList<Run> runArray = new ArrayList<>();

        Cursor cursor = mDatabase.query(RunDbSchema.RunTable.NAME,  null, "month=? and year=?", new String[]{Integer.toString(month),Integer.toString(year)}, null,null,null);
        RunCursorWrapper wrapper = new RunCursorWrapper(cursor);

        try {
            wrapper.moveToFirst();
            while(!wrapper.isAfterLast()) {
                runArray.add(wrapper.getRun());
                wrapper.moveToNext();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            wrapper.close();
        }
        sortArray(runArray);
        return runArray;
    }

    public float getMileageForWeek(int week, int year){
        float mileage = 0;
        for (Run run : getRunsForWeek(week, year)){
            mileage += run.getDistance();
        }
        return mileage;
    }

    public float getMileageForMonth(int month, int year){
        float mileage = 0;
        for (Run run : getRunsForMonth(month, year)){
            mileage += run.getDistance();
        }
        return mileage;
    }

    public float getTotalMileage(){
        float mileage = 0;
        for (Run run : getAllRuns()){
            mileage += run.getDistance();
        }
        return mileage;
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////