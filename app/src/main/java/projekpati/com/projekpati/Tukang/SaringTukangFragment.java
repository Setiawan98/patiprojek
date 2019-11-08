package projekpati.com.projekpati.Tukang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


import projekpati.com.projekpati.R;

public class SaringTukangFragment extends Fragment {

    LocationManager locationManager;
    Boolean GpsStatus;
    Integer saring;
    EditText cari;
    double radius=3000;
    RadioGroup radio;
    RadioButton pick3, pick5, pick8, pick10;
    Button gps, terapkan, refresh;
    public SaringTukangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saring_tukang, container, false);
        gps = view.findViewById(R.id.gps);
        radio = view.findViewById(R.id.radio);
        pick3 = view.findViewById(R.id.pick3);
        pick5 = view.findViewById(R.id.pick5);
        pick8 = view.findViewById(R.id.pick8);
        pick10 = view.findViewById(R.id.pick10);
        terapkan = view.findViewById(R.id.terapkan);
        refresh = view.findViewById(R.id.refresh);
        cari = view.findViewById(R.id.pencarian);
        cari.setText("");

        saring=3;
        cari.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftKeyboard(getActivity());
                    return true;
                }
                return false;
            }
        });

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId)
                {
                    case R.id.pick3:
                        //radius nya dalam m, 1000 m = 1 km
                        radius=3000;
                        saring=3;
                        Toast.makeText(getContext(), "3 Km", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pick5:
                        radius=5000;
                        saring=5;
                        Toast.makeText(getContext(), "5 Km", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pick8:
                        radius=8000;
                        saring=8;
                        Toast.makeText(getContext(), "8 Km", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pick10:
                        radius=10000;
                        saring=10;
                        Toast.makeText(getContext(), "10 Km", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        CheckGpsStatus();
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                gps.setVisibility(View.INVISIBLE);
            }
        });

        terapkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                            Intent intent = new Intent(getContext(), DataSaringTukangActivity.class);
                            intent.putExtra("latitude",latitude);
                            intent.putExtra("longitude",longitude);
                            intent.putExtra("radius", radius);
                            intent.putExtra("SaringTitle",saring);
                            intent.putExtra("namasaring",cari.getText().toString());
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(SaringTukangFragment.this).attach(SaringTukangFragment.this).commit();
                pick3.setChecked(true);
                radius=3000;
                saring=3;
                pick5.setChecked(false);
                pick8.setChecked(false);
                pick10.setChecked(false);
                cari.setText("");
                cari.setHint("Masukkan nama Tempat Makan");
            }
        });

        return view;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
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
