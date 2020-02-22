////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02.Activities.Account;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import csc214.project02.Database.Collections.UserDatabaseCollection;
import csc214.project02.Fragments.ProfilePageFragment;
import csc214.project02.LoginActivity;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.User;
import csc214.project02.R;

////////////////////////////////////////
// Account View Activity
////////////////////////////////////////

public class AccountViewActivity extends AppCompatActivity {

    private ProfilePageFragment mProfilePageFragment;

    public static Intent buildIntent(Context context, User user){
        Intent intent = new Intent(context, AccountViewActivity.class);
        intent.putExtra(INTENT_KEY_USERNAME,user.getUsername());
        return intent;

    }

    public static final String INTENT_KEY_USERNAME = "INTENT_KEY_USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_view);

        User viewUser = (new UserDatabaseCollection(getApplicationContext())).getUserByUsername(getIntent().getStringExtra(INTENT_KEY_USERNAME));
        mProfilePageFragment = new ProfilePageFragment();
        if (viewUser != null){
            mProfilePageFragment.linkUser(viewUser);
        }

        getSupportFragmentManager().
                beginTransaction()
                .add(R.id.activity_view_profile, mProfilePageFragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            mProfilePageFragment.onResume();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_profile_back: {
                finish();
                return true;
            }
            case R.id.menu_logout: {

                ApplicationState.getInstance().logout();

                startActivity(new Intent(getBaseContext(), LoginActivity.class));

                //finish main activity so we cant go back to it
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
// End of File
////////////////////////////////////////