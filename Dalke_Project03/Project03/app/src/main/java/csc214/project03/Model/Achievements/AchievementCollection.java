////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Model.Achievements;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.ArrayList;

import csc214.project03.Database.Collections.RunDatabaseCollection;

////////////////////////////////////////
// Achievement Model - Collection
////////////////////////////////////////

public class AchievementCollection {

    public static final String PREFS_NAME = "PersonalCoachSharedPrefs";

    private ArrayList<Achievement> achievements;
    private Activity parentActivity;

    public AchievementCollection(Activity parent){
        parentActivity= parent;
        update();
    }

    public ArrayList<Achievement> update(){

        //Refresh achievements, check if they have been satisfied, return the result
        if (achievements == null){
            achievements = new ArrayList<>();
            achievements.add(new Achievement("3 Mile Run", "", new AchievementConditionCheck() {
                @Override
                public boolean conditionsMet(RunDatabaseCollection collection) {
                    return collection.hasRunWithDistance(3.0f);
                }
            }));
            achievements.add(new Achievement("6 Mile Run", "", new AchievementConditionCheck() {
                @Override
                public boolean conditionsMet(RunDatabaseCollection collection) {
                    return collection.hasRunWithDistance(6.0f);
                }
            }));
            achievements.add(new Achievement("8 Mile Run", "", new AchievementConditionCheck() {
                @Override
                public boolean conditionsMet(RunDatabaseCollection collection) {
                    return collection.hasRunWithDistance(8.0f);
                }
            }));
            achievements.add(new Achievement("Long Run", "", new AchievementConditionCheck() {
                @Override
                public boolean conditionsMet(RunDatabaseCollection collection) {
                    return collection.hasRunWithDistance(9.0f);
                }
            }));
            achievements.add(new Achievement("10 Mile Run", "", new AchievementConditionCheck() {
                @Override
                public boolean conditionsMet(RunDatabaseCollection collection) {
                    return collection.hasRunWithDistance(10.0f);
                }
            }));
            achievements.add(new Achievement("Full Month", "", new AchievementConditionCheck() {
                @Override
                public boolean conditionsMet(RunDatabaseCollection collection) {
                    return false;
                }
            }));
            achievements.add(new Achievement("1 Year", "", new AchievementConditionCheck() {
                @Override
                public boolean conditionsMet(RunDatabaseCollection collection) {
                    return false;
                }
            }));
            achievements.add(new Achievement("Half Marathon", "", new AchievementConditionCheck() {
                @Override
                public boolean conditionsMet(RunDatabaseCollection collection) {
                    return collection.hasRunWithDistance(13.1f);
                }
            }));
            achievements.add(new Achievement("Marathon", "", new AchievementConditionCheck() {
                @Override
                public boolean conditionsMet(RunDatabaseCollection collection) {
                    return collection.hasRunWithDistance(26.2f);
                }
            }));

        }

        //Refresh if achievements have been satisfied
        RunDatabaseCollection runDatabaseCollection = new RunDatabaseCollection(parentActivity);
        SharedPreferences settings = parentActivity.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        // Commit the edits!
        for (Achievement achievement : achievements){
            achievement.setCompleted(achievement.getConditionCheck().conditionsMet(runDatabaseCollection));
        }
        editor.commit();

        return achievements;
    }

    public int getNumAchievements(){
        return achievements.size();
    }

    public int getNumSatisfiedAchievements(){
        int sum = 0;
        for (Achievement achievement : achievements){
            sum += achievement.isCompleted() ? 1 : 0;
        }
        return sum;
    }

    public ArrayList<Achievement> getList() {
        return achievements;
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////