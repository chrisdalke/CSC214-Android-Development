////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 7
////////////////////////////////////////

package csc214.assignment07.Model;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import csc214.assignment07.Database.GameCursorWrapper;
import csc214.assignment07.Database.GameDatabaseHelper;
import csc214.assignment07.Database.GameDbSchema;

////////////////////////////////////////
// Game Database Collection
////////////////////////////////////////

public class GameListDatabaseCollection {

    private final Context mContext;
    private final SQLiteDatabase mDatabase;
    private ArrayList<Game> mList;

    public GameListDatabaseCollection(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new GameDatabaseHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(Game game) {
        ContentValues values = new ContentValues();
        values.put(GameDbSchema.GameTable.Cols.NAME, game.getName());
        values.put(GameDbSchema.GameTable.Cols.DESCRIPTION, game.getDescription());
        values.put(GameDbSchema.GameTable.Cols.PUBLISHER, game.getPublisherName());
        values.put(GameDbSchema.GameTable.Cols.GENRE, game.getGenre());
        values.put(GameDbSchema.GameTable.Cols.RELEASE_YEAR, game.getReleaseYear());
        return values;
    }

    private GameCursorWrapper queryGames(String where, String[] args) {
        Cursor cursor = mDatabase.query(GameDbSchema.GameTable.NAME,  null, where, args, null,null,null);
        return new GameCursorWrapper(cursor);
    }

    public List<Game> getGames() {

        if (mList == null){
            mList = new ArrayList<>();
        }
        mList.clear();

        GameCursorWrapper wrapper = queryGames(null, null);
        try {
            wrapper.moveToFirst();
            while(!wrapper.isAfterLast()) {
                mList.add(wrapper.getGame());
                wrapper.moveToNext();
            }
        } finally {
            wrapper.close();
        }
        return mList;
    }

    public void addGame(Game instance){
        mDatabase.insert(GameDbSchema.GameTable.NAME,null,getContentValues(instance));
    }

    public void updateGame(Game instance){
        mDatabase.update(GameDbSchema.GameTable.NAME,getContentValues(instance),"name=?",new String[]{instance.getName()});
    }

    public void removeGame(Game instance){
        mDatabase.delete(GameDbSchema.GameTable.NAME,"name=?",new String[]{instance.getName()});
    }

    public void removeAll(){
        mDatabase.delete(GameDbSchema.GameTable.NAME,null,null);
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////