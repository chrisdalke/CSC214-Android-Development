////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Activities;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.UUID;

import csc214.project03.Database.Collections.RunDatabaseCollection;
import csc214.project03.Dialogs.CustomDialogBuilder;
import csc214.project03.Fragments.HomeFragment;
import csc214.project03.Fragments.MapFragment;
import csc214.project03.Model.DateManager;
import csc214.project03.Model.GoalManager;
import csc214.project03.Model.Runs.Run;
import csc214.project03.R;
import csc214.project03.Service.LocationService;
import csc214.project03.Service.Receivers.LocationReceiver;
import csc214.project03.Service.Receivers.LocationReceiverCallback;
import csc214.project03.Service.Receivers.WeatherReceiver;
import csc214.project03.Service.Receivers.WeatherReceiverCallback;
import csc214.project03.Service.ServiceStrings;
import csc214.project03.Service.WeatherService;

////////////////////////////////////////
// Add Run Activity
////////////////////////////////////////

public class AddRunActivity extends AppCompatActivity implements WeatherReceiverCallback, LocationReceiverCallback {
    private Run mRun;

    public static final String KEY_RUN_IMAGE_FILENAME = "RUN_FILENAME";
    public static final String KEY_RUN_IMAGE_WEATHER = "RUN_WEATHER";
    public static final String KEY_RUN_IMAGE_HAS_LOC = "RUN_HAS_LOC";
    public static final String KEY_RUN_IMAGE_LOC_LAT = "RUN_LAT";
    public static final String KEY_RUN_IMAGE_LOC_LON = "RUN_LON";

    private final String KEY_DISTANCE = "DISTANCE";
    private final String KEY_TIME = "TIME";
    private final String KEY_PACE = "PACE";

    private TextView mAddRunDistanceView;
    private TextView mAddRunTimeView;
    private TextView mAddRunPaceView;
    private ImageButton mAddRunDistanceButton;
    private ImageButton mAddRunTimeButton;

    private TextView mWeeklyGoal;
    private TextView mMonthlyGoal;

    private Button mRunAddLocationButton;
    private Button mRunAddWeatherButton;
    private Button mRunAddPhotoButton;

    public MapFragment mRunAddLocationMap;
    public FrameLayout mRunAddLocationMapFrame;

    public ImageView mRunImageView;
    public File mRunImageFile;

    private WeatherReceiver mWeatherReceiver;
    private LocationReceiver mLocationReceiver;


    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filterWeather = new IntentFilter(ServiceStrings.ACTION_WEATHER);
        IntentFilter filterLocation = new IntentFilter(ServiceStrings.ACTION_LOCATION);
        registerReceiver(mWeatherReceiver, filterWeather);
        registerReceiver(mLocationReceiver, filterLocation);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mWeatherReceiver);
        unregisterReceiver(mLocationReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_run);

        mAddRunDistanceView = (TextView)findViewById(R.id.add_run_distance_view);
        mAddRunTimeView = (TextView)findViewById(R.id.add_run_time_view);
        mAddRunPaceView = (TextView)findViewById(R.id.add_run_pace_view);
        mAddRunDistanceButton = (ImageButton)findViewById(R.id.add_run_distance_button);
        mAddRunTimeButton = (ImageButton)findViewById(R.id.add_run_time_button);

        mWeeklyGoal = (TextView)findViewById(R.id.add_run_weekly_goal);
        mMonthlyGoal = (TextView)findViewById(R.id.add_run_monthly_goal);

        mRunAddLocationButton = (Button)findViewById(R.id.run_add_location_button);
        mRunAddWeatherButton = (Button)findViewById(R.id.run_add_weather_button);
        mRunAddPhotoButton = (Button)findViewById(R.id.run_add_photo_button);
        mRunImageView = (ImageView)findViewById(R.id.run_add_photo_view);

        mWeatherReceiver = new WeatherReceiver(this);
        mLocationReceiver = new LocationReceiver(this);

        mRunAddLocationMap = new MapFragment();
        mRunAddLocationMapFrame = (FrameLayout) findViewById(R.id.run_add_location_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.run_add_location_frame,mRunAddLocationMap).commit();

        mRun = new Run();

        if (savedInstanceState != null){
            mRun.setDistance(savedInstanceState.getFloat(KEY_DISTANCE));
            mRun.setDuration(savedInstanceState.getString(KEY_TIME));
            mRun.setExtraImage(savedInstanceState.getString(KEY_RUN_IMAGE_FILENAME));
            if (savedInstanceState.getString(KEY_RUN_IMAGE_FILENAME) != null){
                mRun.setExtraHasImage("true");
            } else {
                mRun.setExtraHasImage("false");
            }
            mRun.setExtraWeatherData(savedInstanceState.getString(KEY_RUN_IMAGE_WEATHER));
            if (savedInstanceState.getString(KEY_RUN_IMAGE_WEATHER) != null){
                mRun.setExtraHasWeather("true");
            } else {
                mRun.setExtraHasWeather("false");
            }
            mRun.setExtraHasLocation(savedInstanceState.getString(KEY_RUN_IMAGE_HAS_LOC));
            mRun.setLat(savedInstanceState.getFloat(KEY_RUN_IMAGE_LOC_LAT));
            mRun.setLon(savedInstanceState.getFloat(KEY_RUN_IMAGE_LOC_LON));
        }

        updateDisplay();
        updateImageUI();

        mAddRunDistanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogBuilder.buildNumberEntryDialog(AddRunActivity.this, "Enter Distance", "Please enter your run distance in miles.",new CustomDialogBuilder.numberEntryDialogCallback() {
                    @Override
                    public void handleResult(float result) {
                        setDistance(result);
                    }
                });
            }
        });

        mAddRunTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogBuilder.buildDateTimeEntryDialog(AddRunActivity.this, "Enter Time", "Please enter your run time (HH:MM:SS)",new CustomDialogBuilder.dateTimeEntryDialogCallback() {
                    @Override
                    public void handleResult(String result) {
                        setTime(result);
                    }
                });
            }
        });

        mRunAddLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mRun.getExtraHasLocation().equals("true")){
                    //Remove existing location string
                    mRun.setExtraHasLocation("false");
                    updateDisplay();
                } else {
                    //Broadcast a RequestIntent to the weather service, set the button string
                    mRunAddLocationButton.setText("Adding Location...");
                    try {
                        LocationService.newPendingIntent(AddRunActivity.this).send();
                    } catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Failed to launch pending intent to get location string.");
                    }
                }
            }
        });

        mRunAddWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mRun.getExtraHasWeather().equals("true")){
                    //Remove existing weather string
                    mRun.setExtraWeatherData(null);
                    mRun.setExtraHasWeather("false");
                    updateDisplay();
                } else {
                    //Broadcast a RequestIntent to the weather service, set the button string
                    mRunAddWeatherButton.setText("Adding Weather...");
                    try {
                        WeatherService.newPendingIntent(AddRunActivity.this).send();
                    } catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Failed to launch pending intent to get weather string.");
                    }
                }

            }
        });


        mRunAddPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mRun.getExtraHasImage().equals("true")) {
                    takePhoto();
                } else {
                    //Remove photo
                    removePhoto();
                }

            }
        });
    }

    @Override
    public void getLocation(float latitude, float longitude) {
        mRun.setExtraHasLocation("true");
        mRun.setLat(latitude);
        mRun.setLon(longitude);
        updateDisplay();
    }

    @Override
    public void getLocationString(String locationName) {

    }

    @Override
    public void getWeather(String weatherString) {
        mRun.setExtraWeatherData(weatherString);
        mRun.setExtraHasWeather("true");
        updateDisplay();
    }

    public void removePhoto(){
        mRunImageFile = null;
        mRun.setExtraImage(null);
        mRun.setExtraHasImage("false");
        updateImageUI();
    }

    public void takePhoto() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        String filename = "IMG_" + UUID.randomUUID().toString() + ".jpg";
        File picturesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mRunImageFile = new File(picturesDir, filename);
        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", mRunImageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        startActivityForResult(intent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            mRun.setExtraImage(mRunImageFile.getPath());
            mRun.setExtraHasImage("true");

            //Show the post image and display the image hide button
            updateImageUI();
        }
    }
    public void updateImageUI(){
        Log.i("PHOTOS","Updating Image UI...");
        if (mRun.getExtraHasImage().equals("true")){
            Log.i("PHOTOS","Adding image...");
            Log.i("PHOTOS","Filename="+mRun.getExtraImage());
            Bitmap photo = getScaledBitmap(mRun.getExtraImage(), 256,256);
            mRunImageView.setImageBitmap(photo);
            mRunImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mRunAddPhotoButton.setText("Remove Photo");
            mRunImageView.setVisibility(View.VISIBLE);
        } else {
            Log.i("PHOTOS","Removing image...");
            mRunAddPhotoButton.setText("Add Photo");
            mRunImageView.setImageDrawable(null);
            mRunImageView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat(KEY_DISTANCE,mRun.getDistance());
        outState.putString(KEY_TIME,mRun.getDuration());
        outState.putString(KEY_RUN_IMAGE_FILENAME,mRun.getExtraImage());
        outState.putString(KEY_RUN_IMAGE_WEATHER,mRun.getExtraWeatherData());
        outState.putString(KEY_RUN_IMAGE_HAS_LOC,mRun.getExtraHasLocation());
        outState.putFloat(KEY_RUN_IMAGE_LOC_LAT,mRun.getLat());
        outState.putFloat(KEY_RUN_IMAGE_LOC_LON,mRun.getLon());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm_cancel, menu);
        return true;
    }

    public void setDistance(float distance){
        mRun.setDistance(distance);
        updateDisplay();
    }

    public void setTime(String time){
        mRun.setDuration(time);
        updateDisplay();
    }

    public void updateDisplay(){
        mAddRunDistanceView.setText("Distance: "+String.format("%.2f",mRun.getDistance())+" mi");
        mAddRunTimeView.setText("Time: "+mRun.getDuration());
        mAddRunPaceView.setText("Pace: "+mRun.calculatePace()+" min/mi");

        GoalManager goalManager = new GoalManager(this);
        RunDatabaseCollection runDb = new RunDatabaseCollection(getApplicationContext());
        int mileageLeftWeek = Math.round(Math.max(goalManager.getCurrentWeeklyGoal() - runDb.getMileageForWeek(DateManager.getWeek(),DateManager.getYear()) - mRun.getDistance(),0));
        int mileageLeftMonth = Math.round(Math.max(goalManager.getCurrentMonthlyGoal() - runDb.getMileageForMonth(DateManager.getMonth(),DateManager.getYear()) - mRun.getDistance(),0));
        mWeeklyGoal.setText("+"+String.format("%.2f",mRun.getDistance()) + " miles towards your weekly goal. "+ mileageLeftWeek + " miles left this week!");
        mMonthlyGoal.setText("+"+String.format("%.2f",mRun.getDistance()) + " miles towards your monthly goal. "+ mileageLeftMonth + " miles left this month!");

        if (mRun.getExtraHasWeather().equals("true")){
            mRunAddWeatherButton.setText("Remove Weather ("+mRun.getExtraWeatherData()+")");
        } else {
            mRunAddWeatherButton.setText("Add Weather");
        }

        if (mRun.getExtraHasLocation().equals("true")){
            mRunAddLocationButton.setText("Remove Location");
            mRunAddLocationMapFrame.setVisibility(View.VISIBLE);
            mRunAddLocationMap.updateLocation(mRun.lat,mRun.lon);
        } else {
            mRunAddLocationButton.setText("Add Location");
            mRunAddLocationMapFrame.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_confirm: {

                if (mRun.getDuration().equals("00:00:00") | mRun.getDistance() == 0){
                    Toast.makeText(this,"Please enter a time and distance!",Toast.LENGTH_SHORT).show();
                } else {

                    RunDatabaseCollection runDb = new RunDatabaseCollection(getApplicationContext());
                    runDb.addRun(mRun);

                    finish();
                }
                return true;
            }
            case R.id.menu_cancel: {
                finish();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }


    //Function from Bobby's presentation to scale a bitmap
    public static Bitmap getScaledBitmap(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int sampleSize = 1;

        if(srcHeight > height || srcWidth > width ) {
            if(srcWidth > srcHeight) {
                sampleSize = Math.round(srcHeight / height);
            } else {
                sampleSize = Math.round(srcWidth / width);
            }
        }

        BitmapFactory.Options scaledOptions = new BitmapFactory.Options();
        scaledOptions.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(path, scaledOptions);
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////