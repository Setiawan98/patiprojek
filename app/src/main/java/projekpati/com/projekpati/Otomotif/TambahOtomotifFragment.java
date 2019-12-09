package projekpati.com.projekpati.Otomotif;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
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
import projekpati.com.projekpati.Model.Otomotif.DetilOtomotifBaru;
import projekpati.com.projekpati.Model.Otomotif.JenisOtomotif;
import projekpati.com.projekpati.Model.Otomotif.JenisOtomotifLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TambahOtomotifFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    String[] items_value;
    GoogleMap mMap1, mMap2;
    EditText eNama, eNomorTelp, eWebsite, eHarga, ePenjual, eModel, eKilometer, eAlamat, eWarna, eTahun, eDeskripsi, eEmail;
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
    Spinner mRefNama, mKondisi, mHabis, mTransmisi;
    String[] stringArray;
    Map<String, JenisOtomotif> dataMObil;
    Map<String, JenisOtomotif> dataMotor;
    int jumlahDataMotor;
    int jumlahDataMobil;
    int jumlahDataTotal;

    public TambahOtomotifFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tambah_otomotif, container, false);

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
        //editText
        eNama = view.findViewById(R.id.eNama);
        eNomorTelp = view.findViewById(R.id.mNomorTelp);
        eEmail = view.findViewById(R.id.mEmail);
        eWebsite = view.findViewById(R.id.mWebsite);
        eDeskripsi = view.findViewById(R.id.mDeskripsi);
        eHarga = view.findViewById(R.id.mHarga);
        eAlamat = view.findViewById(R.id.mAlamat);
        eModel = view.findViewById(R.id.mModel);
        eKilometer = view.findViewById(R.id.mKilometer);
        ePenjual = view.findViewById(R.id.mPenjual);
        eWarna = view.findViewById(R.id.mWarna);
        eTahun = view.findViewById(R.id.mThnPembuatan);

        //Spinner
        mRefNama = view.findViewById(R.id.mRefNama);
        mKondisi = view.findViewById(R.id.mKondisi);
        String kondisi[] = {"Baru","Bekas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, kondisi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mKondisi.setAdapter(adapter);
        mTransmisi = view.findViewById(R.id.mTransmisi);
        String transmisi[] = {"Manual","Matic"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, transmisi);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTransmisi.setAdapter(adapter2);
        mHabis = view.findViewById(R.id.mHabisTerjual);
        String Habis[] = {"Ya","Tidak"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Habis);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mHabis.setAdapter(adapter3);

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
                    Toast.makeText(getContext(),"*Nama Otomotif tidak bole kosong",Toast.LENGTH_SHORT).show();
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
        responseGetMobil();
    }

    public void responseGetMobil()
    {
        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisOtomotifLengkap> call2 = api2.tampilJenisMobilOtomotif();

        call2.enqueue(new Callback<JenisOtomotifLengkap>() {
            @Override
            public void onResponse(Call<JenisOtomotifLengkap> call, Response<JenisOtomotifLengkap> response) {
                dataMObil = response.body().getData();
                jumlahDataMobil = response.body().getJumlah_data();
                Log.w("ResponseMobil",new Gson().toJson(dataMObil));
                responseGetMotor();
              /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, stringArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mRefNama.setAdapter(adapter);*/
            }

            @Override
            public void onFailure(Call<JenisOtomotifLengkap> call, Throwable t) {

            }
        });
    }
    public void responseGetMotor()
    {
        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisOtomotifLengkap> call2 = api2.tampilJenisMotorOtomotif();

        call2.enqueue(new Callback<JenisOtomotifLengkap>() {
            @Override
            public void onResponse(Call<JenisOtomotifLengkap> call, Response<JenisOtomotifLengkap> response) {
                dataMotor = response.body().getData();
                jumlahDataMotor = response.body().getJumlah_data();
                Log.w("ResponseMotor",new Gson().toJson(dataMotor));
                jumlahDataTotal = jumlahDataMobil+jumlahDataMotor;
                Log.w("ResponseMotor",String.valueOf(jumlahDataTotal));

                stringArray = new String[jumlahDataTotal];
                items_value = new String[jumlahDataTotal];
                for (int i = 1; i <= jumlahDataTotal; i++)
                {
                    if(i<=jumlahDataMotor)
                    {
                        stringArray[i-1] = dataMotor.get(String.valueOf(i)).getNama();
                        items_value[i-1] = dataMotor.get(String.valueOf(i)).getId();
                    }
                    else {
                        stringArray[i-1] = dataMObil.get(String.valueOf(i-jumlahDataMotor)).getNama();
                        items_value[i-1] = dataMObil.get(String.valueOf(i-jumlahDataMotor)).getId();
                    }

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, stringArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mRefNama.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<JenisOtomotifLengkap> call, Throwable t) {

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
        String alamat = eAlamat.getText().toString();
        String harga = eHarga.getText().toString();
        String penjual = ePenjual.getText().toString();
        String model = eModel.getText().toString();
        String kilometer = eKilometer.getText().toString();
        String warna = eWarna.getText().toString();
        String tahun = eTahun.getText().toString();
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
        String value2 = items_value[mKondisi.getSelectedItemPosition()];
        String value3 = items_value[mTransmisi.getSelectedItemPosition()];
        String value4 = items_value[mHabis.getSelectedItemPosition()];
        Log.d("nama",nama);
        Log.d("telp",telp);
        Log.d("email",email);
        Log.d("website",website);
        Log.d("deskripsi",deskripsi);
        Log.d("cobain", mRefNama.getSelectedItem().toString());
        if(location!=null)
        {
            Log.d("latitude",latitude);
            Log.d("longitude",longitude);
        }


        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilOtomotifBaru> call = api.addDataOtomotif(nama,penjual,telp,email,website,alamat,harga,value2,model,warna,kilometer,tahun,value3,value4,deskripsi,latitude,longitude,"0",value);

        call.enqueue(new Callback<DetilOtomotifBaru>() {
            @Override
            public void onResponse(Call<DetilOtomotifBaru> call, final Response<DetilOtomotifBaru> response) {
                Toast.makeText(getContext(),"Sukses", Toast.LENGTH_SHORT).show();
                Log.w("Response", new Gson().toJson(response.body()));
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DetilOtomotifBaru> call, Throwable t) {
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