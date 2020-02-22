////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 7
////////////////////////////////////////

package csc214.assignment07.Database;

public class GameDbSchema {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "games_database.db";

    public static final class GameTable {
        public static final String NAME = "games";

        public static final class Cols {
            public static final String NAME = "name";
            public static final String PUBLISHER = "publisher";
            public static final String GENRE = "genre";
            public static final String DESCRIPTION = "description";
            public static final String RELEASE_YEAR = "release_year";
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////