////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Database.Wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import csc214.project03.Database.RunDbSchema;
import csc214.project03.Model.Runs.Run;

////////////////////////////////////////
// Run Cursor Wrapper
////////////////////////////////////////

public class RunCursorWrapper extends CursorWrapper {
    public RunCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Run getRun(){
        int id = Integer.parseInt(getString(getColumnIndex(RunDbSchema.RunTable.Cols.ID)));
        float distance = Float.parseFloat(getString(getColumnIndex(RunDbSchema.RunTable.Cols.DISTANCE)));
        String duration = getString(getColumnIndex(RunDbSchema.RunTable.Cols.DURATION));
        int day = Integer.parseInt(getString(getColumnIndex(RunDbSchema.RunTable.Cols.DAY)));
        int week = Integer.parseInt(getString(getColumnIndex(RunDbSchema.RunTable.Cols.WEEK)));
        int month = Integer.parseInt(getString(getColumnIndex(RunDbSchema.RunTable.Cols.MONTH)));
        int year = Integer.parseInt(getString(getColumnIndex(RunDbSchema.RunTable.Cols.YEAR)));
        String hasExtraImage = getString(getColumnIndex(RunDbSchema.RunTable.Cols.HAS_EXTRA_IMAGE));
        String hasExtraWeather = getString(getColumnIndex(RunDbSchema.RunTable.Cols.HAS_EXTRA_WEATHER));
        String hasExtraLocation = getString(getColumnIndex(RunDbSchema.RunTable.Cols.HAS_EXTRA_LOCATION));
        String extraImage = getString(getColumnIndex(RunDbSchema.RunTable.Cols.EXTRA_IMAGE));
        String extraWeather = getString(getColumnIndex(RunDbSchema.RunTable.Cols.EXTRA_WEATHER));
        String extraLat = getString(getColumnIndex(RunDbSchema.RunTable.Cols.EXTRA_LOCATION_LAT));
        String extraLon = getString(getColumnIndex(RunDbSchema.RunTable.Cols.EXTRA_LOCATION_LON));

        Run returnRun = new Run();
        returnRun.setId(id);
        returnRun.setDistance(distance);
        returnRun.setDuration(duration);
        returnRun.setDay(day);
        returnRun.setWeek(week);
        returnRun.setMonth(month);
        returnRun.setYear(year);
        returnRun.setExtraHasImage(hasExtraImage);
        returnRun.setExtraHasWeather(hasExtraWeather);
        returnRun.setExtraHasLocation(hasExtraLocation);
        returnRun.setExtraImage(extraImage);
        returnRun.setExtraWeatherData(extraWeather);
        returnRun.setLat(Float.parseFloat(extraLat));
        returnRun.setLon(Float.parseFloat(extraLon));

        return returnRun;
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////