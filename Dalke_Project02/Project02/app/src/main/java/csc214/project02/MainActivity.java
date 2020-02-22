////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 2
////////////////////////////////////////

package csc214.project02;

////////////////////////////////////////
// Module Imports
////////////////////////////////////////

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import csc214.project02.Activities.Account.AccountViewActivity;
import csc214.project02.Fragments.FeedPageFragment;
import csc214.project02.Fragments.UsersPageFragment;
import csc214.project02.Model.ApplicationState;

////////////////////////////////////////
// Main Activity
////////////////////////////////////////

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private PagerAdapter mViewPagerAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_post: {
                startActivity(new Intent(getBaseContext(), PostCreateActivity.class));
                return true;
            }
            case R.id.menu_profile: {
                startActivity(AccountViewActivity.buildIntent(getApplicationContext(),ApplicationState.getInstance().getUser()));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Fragment> pageList = new ArrayList<>();
        pageList.add(new FeedPageFragment());
        pageList.add(new UsersPageFragment());
        mTabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        mViewPager = (ViewPager)findViewById(R.id.main_pager);
        mViewPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(),pageList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        //setTitle("Page 1");
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //Set the activity title based on the page position
                System.out.println(position);
                switch (position){
                    case 0: {
                        //setTitle("Feed");
                        break;
                    }
                    case 1: {
                        //setTitle("Profile");
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> pageList;
        public CustomPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> pageList) {
            super(fragmentManager);
            this.pageList = pageList;
        }

        @Override
        public Fragment getItem(int position) {
            return pageList.get(position);
        }

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            //Update page by calling onResume
            pageList.get(position).onResume();

            switch (position){
                case 0: {
                    return "Feed";
                }
                case 1: {
                    return "Users";
                }
                default: {
                    return "null";
                }
            }
        }
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////