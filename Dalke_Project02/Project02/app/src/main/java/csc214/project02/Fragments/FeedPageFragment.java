package csc214.project02.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import csc214.project02.MainActivity;
import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.User;
import csc214.project02.R;
import csc214.project02.RecyclerView.Adapters.PostRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedPageFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PostRecyclerViewAdapter mRecyclerViewAdapter;
    private User mUser = ApplicationState.getInstance().getUser();
    private PostRecyclerViewAdapter.POST_FILTER_TYPE filter = PostRecyclerViewAdapter.POST_FILTER_TYPE.Feed;


    public FeedPageFragment() {
        // Required empty public constructor
    }

    public void setFilter(PostRecyclerViewAdapter.POST_FILTER_TYPE filter){
        this.filter = filter;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            refreshList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    public void linkUser(User user){
        mUser = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feed_page,container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.feed_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerViewAdapter = new PostRecyclerViewAdapter(this, getContext(), filter, mUser);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        return view;
    }

    public void refreshList(){
        if (mRecyclerViewAdapter != null) {
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}
