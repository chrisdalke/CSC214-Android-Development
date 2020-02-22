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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import csc214.project03.Activities.ViewRunActivity;
import csc214.project03.Database.Collections.RunDatabaseCollection;
import csc214.project03.Model.Achievements.Achievement;
import csc214.project03.Model.Achievements.AchievementCollection;
import csc214.project03.Model.Runs.Run;
import csc214.project03.R;

////////////////////////////////////////
// Run History Recycler View
////////////////////////////////////////

public class RunHistoryRecyclerViewAdapter extends RecyclerView.Adapter<RunHistoryRecyclerViewAdapter.RunHolder> {

    private RunDatabaseCollection mRunCollection;
    private Activity parent;

    public RunHistoryRecyclerViewAdapter(Activity parent) {
        mRunCollection = new RunDatabaseCollection(parent);
        this.parent = parent;
    }

    public RunHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_run, parent, false);

        RunHolder holder = new RunHolder(view);
        return holder;
    }

    public int getItemCount() {
        return mRunCollection.getNumRuns();
    }

    public void onBindViewHolder(RunHolder holder, int position) {
        Run run = mRunCollection.getAllRuns().get(position);
        holder.bindRun(run);
    }

    public class RunHolder extends RecyclerView.ViewHolder {

        private TextView mRunDate;
        private TextView mRunMileage;
        private TextView mRunTime;
        private TextView mRunPace;
        private View mView;

        public RunHolder(View view) {
            super(view);
            mView = view;

            mRunDate = (TextView) view.findViewById(R.id.run_date);
            mRunMileage = (TextView) view.findViewById(R.id.run_mileage);
            mRunTime = (TextView) view.findViewById(R.id.run_time);
            mRunPace = (TextView) view.findViewById(R.id.run_pace);
        }

        public void bindRun(Run run) {
            mRunDate.setText(run.getMonth()+"/"+run.getDay()+"/"+run.getYear());
            mRunMileage.setText(String.format("%.2f",run.getDistance()) + " miles");
            mRunTime.setText("Time: "+run.getDuration());
            mRunPace.setText("Pace: "+run.calculatePace()+" / mile");

            final Run tempRun = run;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Start a new activity that allows viewing of the run
                    parent.startActivity(ViewRunActivity.buildIntent(parent,tempRun));
                }
            });
        }
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////