////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Service.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import csc214.project03.Service.ServiceStrings;

////////////////////////////////////////
// Location Broadcast Receiver
////////////////////////////////////////

public class LocationReceiver extends BroadcastReceiver {
    private static final String TAG = "LocationReceiverLog";

    private LocationReceiverCallback locationCallback;

    public LocationReceiver(LocationReceiverCallback locationCallback) {
        this.locationCallback = locationCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Intent received: " + intent.getAction());

        String locString = intent.getStringExtra(ServiceStrings.EXTRA_LOCATION);
        float lat = intent.getFloatExtra(ServiceStrings.EXTRA_LOCATION_LAT,0);
        float lon = intent.getFloatExtra(ServiceStrings.EXTRA_LOCATION_LON,0);

        //Get location data out of the intent.
        locationCallback.getLocation(lat,lon);
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////