package com.example.guidetozeur;



import com.example.guidetozeur.utils.GPSTracker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MapActivity extends Activity {
	// Google Map
    private GoogleMap googleMap;
 // GPS Location
    GPSTracker gps;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_map);
            initilizeMap();
         // latitude and longitude
            double latitude = 0;
            double longitude = 0;
             
         // creating GPS Class object
            gps = new GPSTracker(this);
     
            // check if GPS location can get
            if (gps.canGetLocation()) {
                Log.d("Your Location", "latitude:" + gps.getLatitude() + ", longitude: " + gps.getLongitude());
            } else {
                // Can't get user's current location
                // stop executing code by return
                return;
            }
            
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            
            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps ");
             
            // adding marker
            googleMap.addMarker(marker);
        }

            
    
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
