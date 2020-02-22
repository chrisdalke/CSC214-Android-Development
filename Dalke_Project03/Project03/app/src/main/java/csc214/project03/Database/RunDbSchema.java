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
// Database Schema
////////////////////////////////////////

public class RunDbSchema {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "run_database.db";

    public static final class RunTable {
        public static final String NAME = "runs";

        public static final class Cols {
            public static final String ID = "id";
            public static final String DISTANCE = "distance";
            public static final String DURATION = "duration";
            public static final String DAY =  "day";
            public static final String WEEK =  "week";
            public static final String MONTH =  "month";
            public static final String YEAR =  "year";
            public static final String HAS_EXTRA_IMAGE =  "has_image";
            public static final String HAS_EXTRA_WEATHER =  "has_weather";
            public static final String HAS_EXTRA_LOCATION =  "has_location";
            public static final String EXTRA_IMAGE =  "image";
            public static final String EXTRA_WEATHER =  "weather";
            public static final String EXTRA_LOCATION_LAT =  "lat";
            public static final String EXTRA_LOCATION_LON =  "lon";
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////