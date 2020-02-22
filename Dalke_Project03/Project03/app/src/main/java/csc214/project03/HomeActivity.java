////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import csc214.project03.Activities.AddRunActivity;
import csc214.project03.Activities.SettingsActivity;
import csc214.project03.Fragments.AchievementsFragment;
import csc214.project03.Fragments.HomeFragment;
import csc214.project03.Fragments.ViewHistoryFragment;

////////////////////////////////////////
// Home Activity
////////////////////////////////////////

public class HomeActivity extends AppCompatActivity {

    public boolean mTabletMode = false;

    private ListView mNavListView;
    private View mActivityHome;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mActivityHome = findViewById(R.id.activity_home);
        mTabletMode = mActivityHome.getTag().equals("tablet");
        mNavListView = (ListView)findViewById(R.id.home_nav_list);

        if (savedInstanceState == null){
            //Fresh activity. Set the fragment to default to home
            getFragmentManager().beginTransaction().replace(R.id.home_fragment_frame,new HomeFragment()).commit();
            setTitle("Home");
        } else {

        }

        /*
        Home
        Achievements
        Personal Coach
        Settings
         */
        String[] navArray = { "Home", "History", "Achievements","Settings"};
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, navArray);
        mNavListView.setAdapter(mAdapter);
        mNavListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(HomeActivity.this, "Chose menu item" + position, Toast.LENGTH_SHORT).show();

            switch (position){
                case 0:{
                    getFragmentManager().beginTransaction().replace(R.id.home_fragment_frame,new HomeFragment()).commit();
                    setTitle("Home");
                    break;
                }
                case 1:{
                    getFragmentManager().beginTransaction().replace(R.id.home_fragment_frame,new ViewHistoryFragment()).commit();
                    setTitle("History");
                    break;
                }
                case 2:{
                    getFragmentManager().beginTransaction().replace(R.id.home_fragment_frame,new AchievementsFragment()).commit();
                    setTitle("Achievements");
                    break;
                }
                case 3:{
                    startActivity(new Intent(getBaseContext(), SettingsActivity.class));
                    break;
                }
            }

            //Close drawer if we are not on tablet mode
            if (!mTabletMode){
                // Highlight the selected item, update the title, and close the drawer
                mNavListView.setItemChecked(position, true);
                closeDrawer();
            }
            }
        });

        if (!mTabletMode) {
            //Non-tablet mode, use the drawer layout

            //Add a menu item to toggle the drawer

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            DrawerLayout drawer = (DrawerLayout) mActivityHome;
            mDrawerToggle = new ActionBarDrawerToggle(this, drawer,null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerToggle.syncState();

            mDrawerToggle.setDrawerIndicatorEnabled(true);
            drawer.addDrawerListener(mDrawerToggle);

        } else {
            //Tablet mode, use a list view embedded directly in the layout

        }
    }

    @Override
    public void onBackPressed() {
        if (!mTabletMode) {
            DrawerLayout drawer = (DrawerLayout) mActivityHome;
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (!mTabletMode) {
            mDrawerToggle.syncState();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (!mTabletMode) {
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!mTabletMode) {
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
        }

        switch (item.getItemId()) {
            case R.id.menu_home_add: {
                startActivity(new Intent(getBaseContext(), AddRunActivity.class));
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    public void closeDrawer(){
        if (!mTabletMode) {
            DrawerLayout drawer = (DrawerLayout) mActivityHome;
            drawer.closeDrawer(GravityCompat.START);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

}

////////////////////////////////////////
// End of Module
////////////////////////////////////////