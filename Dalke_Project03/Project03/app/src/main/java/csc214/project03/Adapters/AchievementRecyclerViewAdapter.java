////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Adapters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import csc214.project03.Model.Achievements.Achievement;
import csc214.project03.Model.Achievements.AchievementCollection;
import csc214.project03.R;

////////////////////////////////////////
// Achievement Recycler View
////////////////////////////////////////

public class AchievementRecyclerViewAdapter extends RecyclerView.Adapter<AchievementRecyclerViewAdapter.AchievementHolder> {

    private AchievementCollection mAchievementCollection;
    private Activity parent;

    public AchievementRecyclerViewAdapter(Activity parent) {
        this.parent = parent;
        mAchievementCollection = new AchievementCollection(parent);
    }

    public void update(){
        mAchievementCollection.update();
        notifyDataSetChanged();
    }

    public AchievementCollection getCollection(){
        return mAchievementCollection;
    }

    public AchievementHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_achievement, parent, false);

        AchievementHolder holder = new AchievementHolder(view);
        return holder;
    }




    public int getItemCount() {
        return mAchievementCollection.getList().size();
    }

    public void onBindViewHolder(AchievementHolder holder, int position) {
        Achievement achievement = mAchievementCollection.getList().get(position);
        holder.bindAchievement(achievement);
    }

    public class AchievementHolder extends RecyclerView.ViewHolder {

        private TextView mAchievementName;
        private TextView mAchievementStatus;
        private Achievement mAchievement;
        private View mView;

        public AchievementHolder(View view) {
            super(view);
            mView = view;
            mAchievementName = (TextView) view.findViewById(R.id.achievement_name);
            mAchievementStatus = (TextView) view.findViewById(R.id.achievement_status);
        }

        public void bindAchievement(Achievement achievement) {
            mAchievement = achievement;
            mAchievementName.setText(achievement.getName());
            mAchievementStatus.setText(achievement.isCompleted() ? "Unlocked!" : "Locked!");
            if (achievement.isCompleted()){
                mAchievementStatus.setTextColor(ContextCompat.getColor(parent,R.color.green));
            } else {
                mAchievementStatus.setTextColor(ContextCompat.getColor(parent,R.color.black));
            }
        }
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////