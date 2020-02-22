////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import csc214.project03.Model.WeatherManager;

////////////////////////////////////////
// Weather Service
////////////////////////////////////////

public class WeatherService extends IntentService {
    private static final String TAG = "WeatherServiceTag";
    private static final int RC_GET_WEATHER = 1;
    private GoogleApiClient mClient;

    public WeatherService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Intent received: " + intent);

        //Connect to google services
        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();

        mClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(Bundle bundle) {

                LocationRequest request = LocationRequest.create();
                request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                request.setNumUpdates(1);
                request.setInterval(0);
                LocationServices.FusedLocationApi
                        .requestLocationUpdates(mClient, request, new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                float latitude = (float)location.getLatitude();
                                float longitude = (float)location.getLongitude();

                                System.out.println(latitude + " " + longitude);

                                new WeatherGrabTask().execute(latitude,longitude);

                            }
                        });


            }

            @Override
            public void onConnectionSuspended(int i) {

            }
        });

        mClient.connect();
    }

    private class WeatherGrabTask extends AsyncTask<Float, Void, Void> {
        @Override
        protected Void doInBackground(Float... params) {

            //find the weather string using WeatherManager code (do async)
            WeatherManager wm = new WeatherManager();
            String weatherString = wm.getWeatherForLatLon(params[0]+"",params[1]+"");

            Intent intentOutput = new Intent(ServiceStrings.ACTION_WEATHER);
            intentOutput.putExtra(ServiceStrings.EXTRA_WEATHER, weatherString);
            sendBroadcast(intentOutput);

            return null;
        }
    }

    public static PendingIntent newPendingIntent(Context c) {
        Intent i = new Intent(c, WeatherService.class);
        PendingIntent pi = PendingIntent.getService(c, RC_GET_WEATHER, i, 0);
        return pi;
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////