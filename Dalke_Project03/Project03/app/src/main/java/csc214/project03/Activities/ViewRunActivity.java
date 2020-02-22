////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import csc214.project03.Database.Collections.RunDatabaseCollection;
import csc214.project03.Fragments.MapFragment;
import csc214.project03.Model.Runs.Run;
import csc214.project03.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

////////////////////////////////////////
// View Run Activity
////////////////////////////////////////

public class ViewRunActivity extends AppCompatActivity {

    private Run mRun;

    public static final String INTENT_KEY_RUN_ID = "INTENT_KEY_RUN_ID";

    public static Intent buildIntent(Context context, Run run){
        Intent intent = new Intent(context, ViewRunActivity.class);
        intent.putExtra(INTENT_KEY_RUN_ID,run.getId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_run);

        mRun = (new RunDatabaseCollection(getApplicationContext())).getRunById(getIntent().getIntExtra(INTENT_KEY_RUN_ID,0));
        if (mRun == null){
            //This is bad, just cancel the activity
            finish();
        }

        TextView mViewRunDistanceView = (TextView)findViewById(R.id.view_run_distance_view);
        TextView mViewRunTimeView = (TextView)findViewById(R.id.view_run_time_view);
        TextView mViewRunPaceView = (TextView)findViewById(R.id.view_run_pace_view);
        View mViewRunWeatherWindow = (View)findViewById(R.id.view_run_weather_window);
        TextView mViewRunWeatherView = (TextView)findViewById(R.id.view_run_weather_view);
        View mViewRunLocationWindow = (View)findViewById(R.id.view_run_location_window);
        //(R.id.run_view_location_frame);
        View mViewRunPhotoWindow = (View)findViewById(R.id.view_run_photo_window);
        ImageView mViewRunPhotoView = (ImageView)findViewById(R.id.run_add_photo_view);

        mViewRunDistanceView.setText("Distance: "+String.format("%.2f",mRun.getDistance())+" mi");
        mViewRunTimeView.setText("Time: "+mRun.getDuration());
        mViewRunPaceView.setText("Pace: "+mRun.calculatePace()+" min/mi");

        if (mRun.getExtraHasWeather().equals("true")){
            mViewRunWeatherWindow.setVisibility(VISIBLE);
            mViewRunWeatherView.setText(mRun.getExtraWeatherData());
        } else {
            mViewRunWeatherWindow.setVisibility(GONE);
        }

        if (mRun.getExtraHasLocation().equals("true")){
            mViewRunLocationWindow.setVisibility(VISIBLE);
            MapFragment mapFragment = new MapFragment();
            mapFragment.updateLocation(mRun.getLat(),mRun.getLon());
            getSupportFragmentManager().beginTransaction().replace(R.id.run_view_location_frame,mapFragment).commit();
        } else {
            mViewRunLocationWindow.setVisibility(GONE);
        }

        if (mRun.getExtraHasImage().equals("true")){
            mViewRunPhotoWindow.setVisibility(VISIBLE);
            Bitmap photo = AddRunActivity.getScaledBitmap(mRun.getExtraImage(), 256,256);
            mViewRunPhotoView.setImageBitmap(photo);

        } else {
            mViewRunPhotoWindow.setVisibility(GONE);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back: {
                finish();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////