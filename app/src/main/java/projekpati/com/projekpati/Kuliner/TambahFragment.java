package projekpati.com.projekpati.Kuliner;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.DetilKulinerBaru;
import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import projekpati.com.projekpati.Model.MyItem;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.gson.Gson;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class TambahFragment extends Fragment implements OnMapReadyCallback, LocationListener {


    public TambahFragment() {
        // Required empty public constructor
    }

    GoogleMap mMap1, mMap2;
    EditText eNama, ePemilik, eNomorTelp, eWebsite, eDeskripsi, eEmail;
    Button btnTambah, btnSetLocation;
    SupportMapFragment mapFragment;
    SupportMapFragment mapFragment1;
    PlacesClient placesClient;

    Marker currentLocation;
    LocationManager locationManager;
    LocationListener locationListener;
    LatLng startLatLng, currentLatLng;
    CardView cardLocation;
    Button simpanLocation;
    RelativeLayout setMap;
    ScrollView form;
    LatLng location;
    Marker markerLoc;
    LinearLayout layoutJam;
    Button setJam;
    int status=0;

    EditText eJamMingguBuka,eMenitMingguBuka,eJamSeninBuka,eMenitSeninBuka,eJamSelasaBuka,eMenitselasaBuka,
            eJamRabuBuka,eMenitRabuBuka,eJamKamisBuka,eMenitKamisBuka, eJamJumatBuka,eMenitJumatBuka,
            eJamSabtuBuka,eMenitSabtuBuka;

    EditText eJamMingguTutup,eMenitMingguTutup,eJamSeninTutup,eMenitSeninTutup,eJamSelasaTutup,eMenitselasaTutup,
            eJamRabuTutup,eMenitRabuTutup,eJamKamisTutup,eMenitKamisTutup, eJamJumatTutup,eMenitJumatTutup,
            eJamSabtuTutup,eMenitSabtuTutup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah, container, false);

        //editText
        eNama = view.findViewById(R.id.eNama);
        ePemilik = view.findViewById(R.id.mPemilik);
        eNomorTelp = view.findViewById(R.id.mNomorTelp);
        eEmail = view.findViewById(R.id.mEmail);
        eWebsite = view.findViewById(R.id.mWebsite);
        eDeskripsi = view.findViewById(R.id.mDeskripsi);

        //editText Jam
        eJamMingguBuka = view.findViewById(R.id.mJamMingguBuka);
        eMenitMingguBuka = view.findViewById(R.id.mMenitMingguBuka);
        eJamMingguTutup = view.findViewById(R.id.mJamMinggututup);
        eMenitMingguTutup = view.findViewById(R.id.mMenitMingguTutup);

        eJamSeninBuka = view.findViewById(R.id.mJamSeninBuka);
        eMenitSeninBuka = view.findViewById(R.id.mMenitSeninBuka);
        eJamSeninTutup = view.findViewById(R.id.mJamSenintutup);
        eMenitSeninTutup = view.findViewById(R.id.mMenitSeninTutup);

        eJamSelasaBuka = view.findViewById(R.id.mJamSelasaBuka);
        eMenitselasaBuka = view.findViewById(R.id.mMenitSelasaBuka);
        eJamSelasaTutup = view.findViewById(R.id.mJamSelasatutup);
        eMenitselasaTutup = view.findViewById(R.id.mMenitSelasaTutup);

        eJamRabuBuka = view.findViewById(R.id.mJamRabuBuka);
        eMenitRabuBuka = view.findViewById(R.id.mMenitRabuBuka);
        eJamRabuTutup = view.findViewById(R.id.mJamRabututup);
        eMenitRabuTutup = view.findViewById(R.id.mMenitRabuTutup);

        eJamKamisBuka = view.findViewById(R.id.mJamKamisBuka);
        eMenitKamisBuka = view.findViewById(R.id.mMenitKamisBuka);
        eJamKamisTutup = view.findViewById(R.id.mJamKamistutup);
        eMenitKamisTutup = view.findViewById(R.id.mMenitKamisTutup);

        eJamJumatBuka = view.findViewById(R.id.mJamJumatBuka);
        eMenitJumatBuka = view.findViewById(R.id.mMenitJumatBuka);
        eJamJumatTutup = view.findViewById(R.id.mJamJumattutup);
        eMenitJumatTutup = view.findViewById(R.id.mMenitJumatTutup);

        eJamSabtuBuka = view.findViewById(R.id.mJamSabtuBuka);
        eMenitSabtuBuka = view.findViewById(R.id.mMenitSabtuBuka);
        eJamSabtuTutup = view.findViewById(R.id.mJamSabtututup);
        eMenitSabtuTutup = view.findViewById(R.id.mMenitSabtuTutup);


        //Button
        btnSetLocation = view.findViewById(R.id.btnLocation);
        btnTambah = view.findViewById(R.id.btnTambah);
        setMap = view.findViewById(R.id.showMap);
        form = view.findViewById(R.id.form);
        setJam = view.findViewById(R.id.btnSetJamBuka);
        layoutJam = view.findViewById(R.id.layoutJam);

        setJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status==0)
                {
                    layoutJam.setVisibility(View.VISIBLE);
                    status=1;
                }
                else
                {
                    layoutJam.setVisibility(View.GONE);
                    status=0;
                }
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKuliner();
            }
        });

        btnSetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                form.setVisibility(View.GONE);
                setMap.setVisibility(View.VISIBLE);
            }
        });
        cardLocation = (CardView) view.findViewById(R.id.btnSetLocations);
        simpanLocation = (Button) view.findViewById(R.id.btnSimpanLocation);
        simpanLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                cardLocation.setVisibility(View.VISIBLE);
                simpanLocation.setVisibility(View.GONE);
                setMap.setVisibility(View.GONE);
                form.setVisibility(View.VISIBLE);
                location=currentLatLng;

                mMap1.clear();
                mMap1.addMarker(new MarkerOptions().position(location));
                mMap1.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

            }
        });
        cardLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardLocation.setVisibility(View.GONE);
                simpanLocation.setVisibility(View.VISIBLE);
               // Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), String.valueOf(currentLatLng.latitude),Toast.LENGTH_SHORT).show();
            }
        });




        startLatLng = new LatLng(-6.7487,111.0379);
        currentLatLng = startLatLng;


        mapFragment1 = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment1.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap1=googleMap;
                mMap1.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap1.clear();
                mMap1.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.7487,111.0379),15));

            }
        });

        mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap2=googleMap;
                mMap2.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap2.clear();
                mMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.7487,111.0379),15));

                mMap2.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override
                    public void onCameraMove() {
                        cardLocation.setVisibility(View.VISIBLE);
                        simpanLocation.setVisibility(View.GONE);
                    }
                });
                mMap2.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                    @Override
                    public void onCameraIdle() {

                        LatLng center = mMap2.getCameraPosition().target;
                        currentLatLng = center;
                        Log.d("location", String.valueOf(center.latitude));
                    }
                });
            }
        });





        setupAutoCompleteFragment();






        // Inflate the layout for this fragment
        return view;


    }

    private void setupAutoCompleteFragment() {
        String apikey = getString(R.string.api_key);
        if(!Places.isInitialized()){
            Places.initialize(getContext(),apikey);
        }

        placesClient = Places.createClient(getContext());
        final AutocompleteSupportFragment autocompleteSupportFragment=
                (AutocompleteSupportFragment)  this.getChildFragmentManager().findFragmentById(R.id.autoComplete_fragment);
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG));
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull final Place place) {
                if(place.getLatLng()!= null) {
                    mMap2.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                        @Override
                        public void onMapLoaded() {
                            mMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15));
                            //mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName()));
                            currentLatLng= place.getLatLng();
                        }
                    });
                }
            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });
    }



    private BitmapDescriptor bitmapDescriptor(Context context, int vectorID)
    {
        Drawable vectorDrawable = ContextCompat.getDrawable(context,vectorID);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


    }

    public void addKuliner(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String nama = eNama.getText().toString();
        String pemilik = ePemilik.getText().toString();
        String telp = eNomorTelp.getText().toString();
        String email = eEmail.getText().toString();
        String website = eWebsite.getText().toString();
        String deskripsi = eDeskripsi.getText().toString();
        String latitude;
        String longitude;
        if(location==null)
        {
            latitude = null;
            longitude =null;
        }
        else{
            latitude = String.valueOf(location.latitude);
            longitude = String.valueOf(location.longitude);
        }

        String hari_0 = eJamMingguBuka.getText().toString()+ ":"+ eMenitMingguBuka.getText().toString() + " - " +  eJamMingguTutup.getText().toString()+ ":"+ eMenitMingguTutup.getText().toString();
        String hari_1 = eJamSeninBuka.getText().toString()+ ":"+ eMenitSeninBuka.getText().toString() + " - " +  eJamSeninTutup.getText().toString()+ ":"+ eMenitSeninTutup.getText().toString();
        String hari_2 = eJamSelasaBuka.getText().toString()+ ":"+ eMenitselasaBuka.getText().toString() + " - " +  eJamSelasaTutup.getText().toString()+ ":"+ eMenitselasaTutup.getText().toString();
        String hari_3 = eJamRabuBuka.getText().toString()+ ":"+ eMenitRabuBuka.getText().toString() + " - " +  eJamRabuTutup.getText().toString()+ ":"+ eMenitRabuTutup.getText().toString();
        String hari_4 = eJamKamisBuka.getText().toString()+ ":"+ eMenitKamisBuka.getText().toString() + " - " +  eJamKamisTutup.getText().toString()+ ":"+ eMenitKamisTutup.getText().toString();
        String hari_5 = eJamJumatBuka.getText().toString()+ ":"+ eMenitJumatBuka.getText().toString() + " - " +  eJamJumatTutup.getText().toString()+ ":"+ eMenitJumatTutup.getText().toString();
        String hari_6 = eJamSabtuBuka.getText().toString()+ ":"+ eMenitSabtuBuka.getText().toString() + " - " +  eJamSabtuTutup.getText().toString()+ ":"+ eMenitSabtuTutup.getText().toString();

        Log.d("nama",nama);
        Log.d("pemilik",pemilik);
        Log.d("telp",telp);
        Log.d("email",email);
        Log.d("website",website);
        Log.d("deskripsi",deskripsi);
        if(location!=null)
        {
            Log.d("latitude",latitude);
            Log.d("longitude",longitude);
        }
        Log.d("hari_0",hari_0);
        Log.d("hari_1",hari_1);
        Log.d("hari_2",hari_2);
        Log.d("hari_3",hari_3);
        Log.d("hari_4",hari_4);
        Log.d("hari_5",hari_5);
        Log.d("hari_6",hari_6);


        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilKulinerBaru> call = api.addDataKuliner(nama,pemilik,telp,email,website,deskripsi,latitude,longitude,hari_0,hari_1,hari_2,hari_3,hari_4,hari_5,hari_6);

        call.enqueue(new Callback<DetilKulinerBaru>() {
            @Override
            public void onResponse(Call<DetilKulinerBaru> call, final Response<DetilKulinerBaru> response) {
                Toast.makeText(getContext(),"Sukses", Toast.LENGTH_SHORT).show();
                Log.w("Response", new Gson().toJson(response.body()));
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DetilKulinerBaru> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        if(currentLocation==null)
        {
            MarkerOptions options = new MarkerOptions();
            options.position(startLatLng);
            currentLocation = mMap2.addMarker(options);

            mMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng,15));
        }
        else {
            currentLocation.setPosition(startLatLng);
        }
        mMap2.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng center = mMap2.getCameraPosition().target;

                currentLocation.remove();
                mMap2.addMarker(new MarkerOptions().position(center));
                startLatLng = currentLocation.getPosition();
                Log.d("current location", String.valueOf(startLatLng.latitude));


            }
        });
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
