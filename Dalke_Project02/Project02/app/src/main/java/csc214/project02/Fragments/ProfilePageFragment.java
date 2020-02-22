package csc214.project02.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import csc214.project02.Activities.Account.AccountModifyActivity;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.User;
import csc214.project02.PostCreateActivity;
import csc214.project02.R;
import csc214.project02.RecyclerView.Adapters.PostRecyclerViewAdapter;
import csc214.project02.RecyclerView.Adapters.UserRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePageFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Button mEditProfileButton;
    private PagerAdapter mViewPagerAdapter;
    public TextView mAccountViewUsername;
    public TextView mAccountViewFullName;
    public TextView mAccountViewLocation;
    public TextView mAccountViewBio;
    public ImageView mAccountViewImage;
    private User mUser = ApplicationState.getInstance().getUser();


    public ProfilePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        //On resume, update the user properties
        mAccountViewUsername.setText(mUser.getUsername());
        mAccountViewFullName.setText(mUser.getFullName());
        mAccountViewLocation.setText(mUser.getHomeLocation());
        mAccountViewBio.setText(mUser.getBio());

        //Apply image (if it exists) to the account
        if (mUser.getPhotoFile() != null){
            try {
                Bitmap photo = PostCreateActivity.getScaledBitmap(mUser.getPhotoFile(), 256, 256);
                mAccountViewImage.setImageBitmap(photo);
                mAccountViewImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mAccountViewImage.setVisibility(View.VISIBLE);
            } catch (Exception e){
                mAccountViewImage.setImageResource(R.drawable.ic_default_profile_light);
                mAccountViewImage.setVisibility(View.VISIBLE);
            }
        } else {
            mAccountViewImage.setImageResource(R.drawable.ic_default_profile_light);
            mAccountViewImage.setVisibility(View.VISIBLE);
        }



    }

    public void linkUser(User user){
        mUser = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_view, container, false);

        ArrayList<Fragment> pageList = new ArrayList<>();
        FeedPageFragment feedPageFragment = new FeedPageFragment();
        feedPageFragment.setFilter(PostRecyclerViewAdapter.POST_FILTER_TYPE.Profile);
        feedPageFragment.linkUser(mUser);
        pageList.add(feedPageFragment);

        UsersPageFragment followersPage = new UsersPageFragment();
        followersPage.setFilter(UserRecyclerViewAdapter.USER_FILTER_TYPE.Followers);
        followersPage.linkUser(mUser);
        UsersPageFragment followingPage = new UsersPageFragment();
        followingPage.setFilter(UserRecyclerViewAdapter.USER_FILTER_TYPE.Following);
        followingPage.linkUser(mUser);

        pageList.add(followersPage);
        pageList.add(followingPage);

        mEditProfileButton = (Button)view.findViewById(R.id.edit_profile_button);
        mTabLayout = (TabLayout)view.findViewById(R.id.profile_tabs_layout);
        mViewPager = (ViewPager)view.findViewById(R.id.profile_tabs_pager);
        mViewPagerAdapter = new CustomPagerAdapter(getFragmentManager(),pageList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        if (mUser.equals(ApplicationState.getInstance().getUser())) {
            mEditProfileButton.setVisibility(View.VISIBLE);
            mEditProfileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start profile edit activity
                    startActivity(new Intent(getContext(), AccountModifyActivity.class));
                    //BAD HACK: Close this activity and recreate it later
                    getActivity().finish();
                }
            });
        } else {
            mEditProfileButton.setVisibility(View.GONE);
            mEditProfileButton.setOnClickListener(null);
        }

        mAccountViewUsername = (TextView) view.findViewById(R.id.account_view_username);
        mAccountViewFullName = (TextView) view.findViewById(R.id.account_view_full_name);
        mAccountViewLocation = (TextView) view.findViewById(R.id.account_view_location);
        mAccountViewBio = (TextView) view.findViewById(R.id.account_view_bio);
        mAccountViewImage = (ImageView) view.findViewById(R.id.account_view_image);

        return view;
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
            switch (position){
                case 0: {
                    return "Posts";
                }
                case 1: {
                    return "Followers";
                }
                case 2: {
                    return "Following";
                }
                default: {
                    return "null";
                }
            }
        }
    }
}
