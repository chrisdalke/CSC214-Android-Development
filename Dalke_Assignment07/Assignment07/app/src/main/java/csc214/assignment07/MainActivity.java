////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 7
////////////////////////////////////////

package csc214.assignment07;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import csc214.assignment07.Model.GameListDatabaseCollection;
import csc214.assignment07.RecyclerView.Fragments.GameRecyclerViewFragment;

////////////////////////////////////////
// Main Activity
////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    private GameRecyclerViewFragment mRecyclerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewFragment = new GameRecyclerViewFragment();

        getSupportFragmentManager().
                beginTransaction()
                .add(R.id.activity_main, mRecyclerViewFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_new_game: {
                startNewGameActivity();
                return true;
            }
            case R.id.menu_reset: {
                (new GameListDatabaseCollection(getApplicationContext())).removeAll();
                mRecyclerViewFragment.refreshList();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    public void startNewGameActivity() {
        startActivity(new Intent(getBaseContext(), GameCreateActivity.class));
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////