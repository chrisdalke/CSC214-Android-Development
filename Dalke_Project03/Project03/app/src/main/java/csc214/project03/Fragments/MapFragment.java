////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Fragments;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

////////////////////////////////////////
// Map Fragment
////////////////////////////////////////

public class MapFragment extends SupportMapFragment {

    private GoogleMap mMap;

    float latitude = 0;
    float longitude = 0;
    boolean hasMarkerFlag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                mMap.setBuildingsEnabled(true);

                if (hasMarkerFlag){
                    LatLng location = new LatLng(latitude,longitude);
                    MarkerOptions locationMarker = new MarkerOptions().position(location);
                    mMap.clear();
                    mMap.addMarker(locationMarker);
                    CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location, 14.0f);
                    mMap.animateCamera(update);
                }


            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        //Disable input on map widget
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        view.setClickable(false);

        return view;
    }

    public void updateLocation(float lat, float lon) {
        if(mMap != null) {
            System.out.println("Setting marker to lat: "+lat+" lon: "+lon);

            LatLng location = new LatLng(lat,lon);
            MarkerOptions locationMarker = new MarkerOptions().position(location);
            mMap.clear();
            mMap.addMarker(locationMarker);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location, 14.0f);
            mMap.animateCamera(update);
        } else {
            latitude = lat;
            longitude = lon;
            hasMarkerFlag = true;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}

////////////////////////////////////////
// End of Module
////////////////////////////////////////