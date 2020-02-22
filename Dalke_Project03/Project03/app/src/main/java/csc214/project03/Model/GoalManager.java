////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Model;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.Date;
import csc214.project03.Model.Achievements.Achievement;

////////////////////////////////////////
// Mileage Goal Manager
////////////////////////////////////////

public class GoalManager {

    public static final String PREFS_NAME = "PersonalCoachSharedPrefs";

    public Activity parentActivity;

    public GoalManager(Activity parent){
        parentActivity = parent;
    }

    public void reset(){

        SharedPreferences settings = parentActivity.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public int getCurrentWeeklyGoal(){
        return getWeeklyGoal(DateManager.getWeek(),DateManager.getYear());
    }

    public int getCurrentMonthlyGoal(){
        return getMonthlyGoal(DateManager.getMonth(),DateManager.getYear());
    }

    public void setCurrentWeeklyGoal(int value){
        setWeeklyGoal(DateManager.getWeek(),DateManager.getYear(),value);
    }

    public void setCurrentMonthlyGoal(int value){
        setMonthlyGoal(DateManager.getMonth(),DateManager.getYear(),value);
    }

    //Get the weekly goal for a given week.
    public int getWeeklyGoal(int week, int year){
        SharedPreferences settings = parentActivity.getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(DateManager.getUniqueWeekTag(week,year),0);
    }

    //Set the weekly goal for a given week.
    public void setWeeklyGoal(int week, int year, int value){
        SharedPreferences settings = parentActivity.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(DateManager.getUniqueWeekTag(week,year),value);
        editor.commit();
    }

    //Get the monthly goal for a given week.
    public int getMonthlyGoal(int month, int year){
        SharedPreferences settings = parentActivity.getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(DateManager.getUniqueMonthTag(month,year),0);
    }

    //Set a monthly goal for a given week.
    public void setMonthlyGoal(int month, int year, int value){
        SharedPreferences settings = parentActivity.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(DateManager.getUniqueMonthTag(month,year),value);
        editor.commit();
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////
