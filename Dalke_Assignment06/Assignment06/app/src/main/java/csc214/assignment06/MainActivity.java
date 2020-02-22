package csc214.assignment06;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import csc214.assignment06.ListView.Fragments.GameCustomListFragment;
import csc214.assignment06.ListView.Fragments.GameSimpleListFragment;
import csc214.assignment06.RecyclerView.Fragments.GameRecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Fragment> pageList = new ArrayList<>();
        pageList.add(new GameSimpleListFragment());
        pageList.add(new GameCustomListFragment());
        pageList.add(new GameRecyclerViewFragment());

        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(),pageList);
        mViewPager.setAdapter(mViewPagerAdapter);

        setTitle("Basic List View");
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
                        setTitle("Basic List View");
                        break;
                    }
                    case 1: {
                        setTitle("Custom List View");
                        break;
                    }
                    case 2: {
                        setTitle("Recycler View");
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
    }
}
