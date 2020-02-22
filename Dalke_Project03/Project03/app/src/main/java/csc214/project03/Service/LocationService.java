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
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

////////////////////////////////////////
// Location Service
////////////////////////////////////////

public class LocationService extends IntentService {
    private static final int RC_GET_LOCATION = 2;

    private static final String TAG = "LocationServiceTag";

    private float latitude;
    private float longitude;
    private GoogleApiClient mClient;

    public LocationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Intent received: " + intent);

        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();

        System.out.println("Connecting to google api.");

        mClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(Bundle bundle) {

                System.out.println("Connected to google api.");

                LocationRequest request = LocationRequest.create();
                request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                request.setNumUpdates(1);
                request.setInterval(0);
                LocationServices.FusedLocationApi
                        .requestLocationUpdates(mClient, request, new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                System.out.println("Got a location update.");

                                latitude = (float)location.getLatitude();
                                longitude = (float)location.getLongitude();

                                System.out.println(latitude + " " + longitude);

                                //We grabbed the location! Now, we can safely respond with a broadcast intent
                                Intent intentOutput = new Intent(ServiceStrings.ACTION_LOCATION);
                                intentOutput.putExtra(ServiceStrings.EXTRA_LOCATION, "Location String");
                                intentOutput.putExtra(ServiceStrings.EXTRA_LOCATION_LAT, latitude);
                                intentOutput.putExtra(ServiceStrings.EXTRA_LOCATION_LON, longitude);
                                sendBroadcast(intentOutput);
                            }
                        });


            }

            @Override
            public void onConnectionSuspended(int i) {

            }
        });

        mClient.connect();
    }

    public static PendingIntent newPendingIntent(Context c) {
        Intent i = new Intent(c, LocationService.class);
        PendingIntent pi = PendingIntent.getService(c, RC_GET_LOCATION, i, 0);
        return pi;
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////