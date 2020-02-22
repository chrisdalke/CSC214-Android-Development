package csc214.project02.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csc214.project02.Model.ApplicationState;
import csc214.project02.Model.User;
import csc214.project02.R;
import csc214.project02.RecyclerView.Adapters.PostRecyclerViewAdapter;
import csc214.project02.RecyclerView.Adapters.UserRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersPageFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private UserRecyclerViewAdapter mRecyclerViewAdapter;
    private User mUser = ApplicationState.getInstance().getUser();
    private UserRecyclerViewAdapter.USER_FILTER_TYPE filter = UserRecyclerViewAdapter.USER_FILTER_TYPE.AllOthers;

    public UsersPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    public void linkUser(User user){
        mUser = user;
    }

    public void setFilter(UserRecyclerViewAdapter.USER_FILTER_TYPE filter){
        this.filter = filter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users_page,container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.users_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerViewAdapter = new UserRecyclerViewAdapter(this, getContext(), filter, mUser);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        return view;
    }

    public void refreshList(){
        if (mRecyclerViewAdapter != null) {
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}
