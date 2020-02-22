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
// Weather Broadcast Receiver
////////////////////////////////////////

public class WeatherReceiver extends BroadcastReceiver {
    private static final String TAG = "WeatherReceiverLog";

    private WeatherReceiverCallback weatherCallback;

    public WeatherReceiver(WeatherReceiverCallback weatherCallback) {
        this.weatherCallback = weatherCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Intent received: " + intent.getAction());

        //Get weather data out of the intent.
        weatherCallback.getWeather(intent.getStringExtra(ServiceStrings.EXTRA_WEATHER));
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////