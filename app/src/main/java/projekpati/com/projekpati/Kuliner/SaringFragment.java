package projekpati.com.projekpati.Kuliner;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import projekpati.com.projekpati.R;

import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaringFragment extends Fragment {

    LocationManager locationManager;
    Boolean GpsStatus;
    Button gps;
    public SaringFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saring, container, false);
        gps = view.findViewById(R.id.gps);
        //CheckGpsStatus();
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
//                gps.setVisibility(View.INVISIBLE);
                FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(getContext());
                mFusedLocation.getLastLocation().addOnSuccessListener((Activity) getContext(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null)
                        {
                            Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                            // Display in Toast
                            Toast.makeText(getContext(),
                                    "Lat : " + location.getLatitude() + " Long : " + location.getLongitude(),
                                    Toast.LENGTH_LONG).show();
                            final double latitude = location.getLatitude();
                            final double longitude = location.getLongitude();

                            Intent intent = new Intent(getContext(),DataSaringActivity.class);
                            intent.putExtra("latitude",latitude);
                            intent.putExtra("longitude",longitude);
                            startActivity(intent);
                        }
                    }
                });


            }
        });



        return view;
    }

    public void CheckGpsStatus(){

        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(GpsStatus == true)
        {
            gps.setVisibility(View.INVISIBLE);
        }else {
            gps.setVisibility(View.VISIBLE);
        }

    }

}
