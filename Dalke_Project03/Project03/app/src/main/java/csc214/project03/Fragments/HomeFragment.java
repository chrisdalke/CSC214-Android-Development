////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;

import csc214.project03.Database.Collections.RunDatabaseCollection;
import csc214.project03.Dialogs.CustomDialogBuilder;
import csc214.project03.Model.DateManager;
import csc214.project03.Model.GoalManager;
import csc214.project03.Model.Runs.Run;
import csc214.project03.R;

////////////////////////////////////////
// Home Fragment
////////////////////////////////////////

public class HomeFragment extends Fragment {

    private DonutProgress mDonutWeeklyProgress;
    private DonutProgress mDonutMonthlyProgress;

    private TextView mWeeklyGoalHeader;
    private TextView mMonthlyGoalHeader;
    private TextView mWeeklyGoalMessage;
    private TextView mWeeklyGoalMileage;
    private TextView mMonthlyGoalMessage;
    private TextView mMonthlyGoalMileage;
    private Button mWeeklyGoalButton;
    private Button mMonthlyGoalButton;

    private GoalManager goalManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mDonutWeeklyProgress = (DonutProgress)view.findViewById(R.id.donut_weekly_progress);
        mDonutMonthlyProgress = (DonutProgress)view.findViewById(R.id.donut_monthly_progress);
        mWeeklyGoalHeader = (TextView)view.findViewById(R.id.weekly_goal_header);
        mMonthlyGoalHeader = (TextView)view.findViewById(R.id.monthly_goal_header);
        mWeeklyGoalMessage = (TextView)view.findViewById(R.id.weekly_goal_message);
        mWeeklyGoalMileage = (TextView)view.findViewById(R.id.weekly_goal_mileage);
        mMonthlyGoalMessage = (TextView)view.findViewById(R.id.monthly_goal_message);
        mMonthlyGoalMileage = (TextView)view.findViewById(R.id.monthly_goal_mileage);
        mWeeklyGoalButton = (Button)view.findViewById(R.id.weekly_goal_button);
        mMonthlyGoalButton = (Button)view.findViewById(R.id.monthly_goal_button);

        mWeeklyGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogBuilder.buildNumberEntryDialog(getContext(), "Weekly Goal", "Please enter a weekly goal mileage!", new CustomDialogBuilder.numberEntryDialogCallback() {
                    @Override
                    public void handleResult(float result) {
                        goalManager.setCurrentWeeklyGoal(Math.round(result));
                        update();
                    }
                });
            }
        });

        mMonthlyGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogBuilder.buildNumberEntryDialog(getContext(), "Monthly Goal", "Please enter a monthly goal mileage!", new CustomDialogBuilder.numberEntryDialogCallback() {
                    @Override
                    public void handleResult(float result) {
                        goalManager.setCurrentMonthlyGoal(Math.round(result));
                        update();
                    }
                });
            }
        });

        goalManager = new GoalManager(getActivity());
        update();

        return view;
    }

    @Override
    public void onResume() {
        update();
        super.onResume();
    }

    public void update(){

        //Get weekly and monthly goal values
        int weeklyGoal = goalManager.getCurrentWeeklyGoal();
        int monthlyGoal = goalManager.getCurrentMonthlyGoal();

        RunDatabaseCollection runDb = new RunDatabaseCollection(getContext());

        System.out.println("All runs:");
        for (Run run : runDb.getAllRuns()){
            System.out.println(run.toString());
        }

        ArrayList<Run> runsWeek =runDb.getRunsForWeek(DateManager.getWeek(),DateManager.getYear());
        System.out.println("Getting runs for week / year:" + DateManager.getWeek()+" "+DateManager.getYear());
        System.out.println("Num items: "+runsWeek.size());

        for (Run run : runsWeek){
            System.out.println(run.toString());
        }

        float weeklyMileage = runDb.getMileageForWeek(DateManager.getWeek(),DateManager.getYear());
        float monthlyMileage = runDb.getMileageForMonth(DateManager.getMonth(),DateManager.getYear());

        int weeklyProgressPercent = Math.round((weeklyMileage / (float)weeklyGoal) * 100);
        int monthlyProgressPercent = Math.round((monthlyMileage / (float)monthlyGoal) * 100);

        if (weeklyGoal != 0){
            mDonutWeeklyProgress.setDonut_progress(weeklyProgressPercent+"");
            mWeeklyGoalMessage.setText("You are "+weeklyProgressPercent+"% to your weekly goal of "+weeklyGoal+" miles!");
            if (weeklyProgressPercent > 100){
                mDonutWeeklyProgress.setDonut_progress("100");
                mWeeklyGoalMessage.setText("You have achieved your weekly goal of "+weeklyGoal+" miles!");
            }

        } else {
            mDonutWeeklyProgress.setDonut_progress("0");
            mWeeklyGoalMessage.setText("You have not set a weekly goal!");
        }

        if (monthlyGoal != 0){
            mDonutMonthlyProgress.setDonut_progress(monthlyProgressPercent+"");
            mMonthlyGoalMessage.setText("You are "+monthlyProgressPercent+"% to your monthly goal of "+monthlyGoal+" miles!");
            if (monthlyProgressPercent > 100){
                mDonutMonthlyProgress.setDonut_progress("100");
                mMonthlyGoalMessage.setText("You have achieved your monthly goal of "+monthlyGoal+" miles!");
            }

        } else {
            mDonutMonthlyProgress.setDonut_progress("0");
            mMonthlyGoalMessage.setText("You have not set a monthly goal!");
        }

        //Update mileage counts
        mWeeklyGoalMileage.setText("You have completed "+String.format("%.1f",weeklyMileage)+" miles this week.");
        mMonthlyGoalMileage.setText("You have completed "+String.format("%.1f",monthlyMileage)+" miles this month.");

        mWeeklyGoalHeader.setText("Weekly Goal (Week "+ DateManager.getWeek()+")");
        mMonthlyGoalHeader.setText("Monthly Goal ("+ DateManager.getStringMonth()+")");
    }

}

////////////////////////////////////////
// End of Module
////////////////////////////////////////