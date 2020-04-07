package projekpati.com.projekpati.Agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
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

import java.util.Arrays;
import java.util.Map;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Agenda.DetilAgendaBaru;
import projekpati.com.projekpati.Model.Agenda.JenisAgenda;
import projekpati.com.projekpati.Model.Agenda.JenisAgendaLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TambahAgendaFragment  extends Fragment implements OnMapReadyCallback, LocationListener {


    public TambahAgendaFragment() {
        // Required empty public constructor
    }

    String[] items_value;
    GoogleMap mMap1, mMap2;
    EditText eNama, eNomorTelp, eWebsite, eDeskripsi, eEmail, eHargaTiket, eDDMulai, eMMMulai, eYYYYMulai,eDDSelesai, eMMSelesai, eYYYYSelesai;
    Button btnTambah, btnSetLocation;
    SupportMapFragment mapFragment;
    SupportMapFragment mapFragment1;
    PlacesClient placesClient;
    Marker currentLocation;
    LatLng startLatLng, currentLatLng;
    CardView cardLocation;
    Button simpanLocation;
    RelativeLayout setMap;
    ScrollView form;
    LatLng location;
    Spinner mRefNama;
    TextView btnFree;
    boolean isFree;
    int status=0;
    String userid,nama,email,telp,website;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tambah_agenda, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("user_id","");
        nama = sharedPreferences.getString("user_nama","");
        email = sharedPreferences.getString("user_email","");
        telp = sharedPreferences.getString("user_telp","");
        website = sharedPreferences.getString("user_website","");

        init(view);
        setSpinner();
        startLatLng = new LatLng(-6.7487,111.0379);
        currentLatLng = startLatLng;

        initMap();
        setupAutoCompleteFragment();

        // Inflate the layout for this fragment
        return view;
    }

    public void init(View view){
        //Spinner
        mRefNama = view.findViewById(R.id.mRefNama);

        //editText
        eNama = view.findViewById(R.id.eNama);
        eNomorTelp = view.findViewById(R.id.mNomorTelp);
        eEmail = view.findViewById(R.id.mEmail);
        eWebsite = view.findViewById(R.id.mWebsite);
        eDeskripsi = view.findViewById(R.id.mDeskripsi);
        eHargaTiket = view.findViewById(R.id.mHargaTiket);

        eDDMulai = view.findViewById(R.id.mDDMulai);
        eMMMulai = view.findViewById(R.id.mMMMulai);
        eYYYYMulai = view.findViewById(R.id.mYYYYMulai);

        eDDSelesai = view.findViewById(R.id.mDDSelesai);
        eMMSelesai = view.findViewById(R.id.mMMSelesai);
        eYYYYSelesai = view.findViewById(R.id.mYYYYSelesai);

        eNomorTelp.setText(telp);
        eEmail.setText(email);
        eWebsite.setText(website);

        btnFree = view.findViewById(R.id.btnFree);
        btnFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status==0)
                {
                    btnFree.setTextColor(getResources().getColor(R.color.blue));
                    eHargaTiket.setEnabled(false);
                    isFree = true;
                    status =1;
                }
                else
                {
                    btnFree.setTextColor(getResources().getColor(R.color.black));
                    eHargaTiket.setEnabled(true);
                    isFree = false;
                    status =0;
                }

            }
        });

        //Button
        btnSetLocation = view.findViewById(R.id.btnLocation);
        btnTambah = view.findViewById(R.id.btnTambah);
        setMap = view.findViewById(R.id.showMap);
        form = view.findViewById(R.id.form);
        cardLocation = (CardView) view.findViewById(R.id.btnSetLocations);
        simpanLocation = (Button) view.findViewById(R.id.btnSimpanLocation);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eNama.getText().toString().equals(""))
                {
                    eNama.setBackgroundTintList(getResources().getColorStateList(R.color.red));
                    eNama.setHintTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(getContext(),"*Nama Agenda tidak bole kosong",Toast.LENGTH_SHORT).show();
                }
                else if(eDDMulai.getText().toString().equals("") || eMMMulai.getText().toString().equals("") || eYYYYMulai.getText().toString().equals("") ||
                        eDDSelesai.getText().toString().equals("") || eMMSelesai.getText().toString().equals("") || eYYYYSelesai.getText().toString().equals(""))
                {

                    Toast.makeText(getContext(),"*Tidak boleh kosong",Toast.LENGTH_SHORT).show();

                }
                else {

                    addKuliner();
                }
            }
        });


        btnSetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                form.setVisibility(View.GONE);
                setMap.setVisibility(View.VISIBLE);
            }
        });

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
    }

    public void initMap()
    {
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

    public void setSpinner()
    {
        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisAgendaLengkap> call2 = api2.tampilJenisAgenda();

        call2.enqueue(new Callback<JenisAgendaLengkap>() {
            @Override
            public void onResponse(Call<JenisAgendaLengkap> call, Response<JenisAgendaLengkap> response) {
                Map<String, JenisAgenda> data = response.body().getData();

                String[] stringArray;
                stringArray = new String[response.body().getJumlah_data()];
                items_value = new String[response.body().getJumlah_data()];
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    stringArray[i-1] = data.get(String.valueOf(i)).getNama();
                    items_value[i-1] = data.get(String.valueOf(i)).getId();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, stringArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mRefNama.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JenisAgendaLengkap> call, Throwable t) {

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
        String telp = eNomorTelp.getText().toString();
        String email = eEmail.getText().toString();
        String website = eWebsite.getText().toString();
        String deskripsi = eDeskripsi.getText().toString();
        String hargaTiket;
        if(isFree == true)
        {
             hargaTiket = "Free";
        }
        else
        {
             hargaTiket = eHargaTiket.getText().toString();
        }


        String tglMulai = eYYYYMulai.getText().toString()+"-"+eMMMulai.getText().toString()+"-"+eDDMulai.getText().toString();
        String tglSelesai =  eYYYYSelesai.getText().toString()+"-"+eMMSelesai.getText().toString()+"-"+eDDSelesai.getText().toString();
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

        String value = items_value[mRefNama.getSelectedItemPosition()];

        Log.d("nama",nama);
        Log.d("telp",telp);
        Log.d("email",email);
        Log.d("website",website);
        Log.d("deskripsi",deskripsi);
        Log.d("hargaTiket",hargaTiket);
        Log.d("tglMulai",tglMulai);
        Log.d("tglSelesai",tglSelesai);
        if(location!=null)
        {
            Log.d("latitude",latitude);
            Log.d("longitude",longitude);
        }


        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilAgendaBaru> call = api.addDataAgenda(nama,telp,email,tglMulai,tglSelesai,hargaTiket,website,deskripsi,latitude,longitude,userid,value);

        call.enqueue(new Callback<DetilAgendaBaru>() {
            @Override
            public void onResponse(Call<DetilAgendaBaru> call, final Response<DetilAgendaBaru> response) {
                Toast.makeText(getContext(),"Sukses", Toast.LENGTH_SHORT).show();
                Log.w("Response", new Gson().toJson(response.body()));
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DetilAgendaBaru> call, Throwable t) {
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
