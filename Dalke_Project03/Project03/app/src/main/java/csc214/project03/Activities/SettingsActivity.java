////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import csc214.project03.Database.Collections.RunDatabaseCollection;
import csc214.project03.Model.GoalManager;
import csc214.project03.R;

////////////////////////////////////////
// Settings Activity
////////////////////////////////////////

public class SettingsActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "PersonalCoachSharedPrefs";

    private CheckBox mCheckBoxSFX;
    private Button mResetRuns;
    private Button mResetGoals;
    private Button mResetAll;

    private final String KEY_SFX = "SFX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mCheckBoxSFX = (CheckBox) findViewById(R.id.checkbox_sound_effects);
        mResetRuns = (Button) findViewById(R.id.reset_runs);
        mResetGoals = (Button) findViewById(R.id.reset_goals);
        mResetAll = (Button) findViewById(R.id.reset_all);

        if (savedInstanceState != null){
            mCheckBoxSFX.setChecked(savedInstanceState.getBoolean(KEY_SFX));
        } else {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            mCheckBoxSFX.setChecked(settings.getBoolean(KEY_SFX,false));
        }

        mResetRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetRuns();
            }
        });

        mResetGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGoals();
            }
        });

        mResetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGoals();
                resetRuns();
            }
        });
    }

    private void resetRuns(){
        (new RunDatabaseCollection(this)).removeAll();
    }

    private void resetGoals(){
        new GoalManager(this).reset();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(KEY_SFX,mCheckBoxSFX.isChecked());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm_cancel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_confirm: {
                //Save checkbox status to settings

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean(KEY_SFX,mCheckBoxSFX.isChecked());
                editor.commit();

                finish();

                return true;
            }
            case R.id.menu_cancel: {
                finish();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
////////////////////////////////////////
// End of Module
////////////////////////////////////////