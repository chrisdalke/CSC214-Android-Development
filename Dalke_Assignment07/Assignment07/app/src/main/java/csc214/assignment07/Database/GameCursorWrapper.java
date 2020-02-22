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

import android.database.Cursor;
import android.database.CursorWrapper;
import csc214.assignment07.Model.Game;

////////////////////////////////////////
// Game Cursor Wrapper
////////////////////////////////////////

public class GameCursorWrapper extends CursorWrapper {
    public GameCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Game getGame() {

        String name = getString(getColumnIndex(GameDbSchema.GameTable.Cols.NAME));
        String publisherName = getString(getColumnIndex(GameDbSchema.GameTable.Cols.PUBLISHER));
        String genre = getString(getColumnIndex(GameDbSchema.GameTable.Cols.GENRE));
        String description = getString(getColumnIndex(GameDbSchema.GameTable.Cols.DESCRIPTION));
        int releaseYear = getInt(getColumnIndex(GameDbSchema.GameTable.Cols.RELEASE_YEAR));

        return new Game(name,publisherName,genre,description,releaseYear);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////