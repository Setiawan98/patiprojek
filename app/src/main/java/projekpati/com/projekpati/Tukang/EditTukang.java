package projekpati.com.projekpati.Tukang;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Tukang.DetilTukangBaru;
import projekpati.com.projekpati.Model.Tukang.DetilTukangModel;
import projekpati.com.projekpati.Model.Tukang.GambarTukangDetil;
import projekpati.com.projekpati.Model.Tukang.JenisTukang;
import projekpati.com.projekpati.Model.Tukang.JenisTukangLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditTukang extends Fragment implements OnMapReadyCallback, LocationListener {

    String[] items_value;
    GoogleMap mMap1, mMap2;
    EditText eNama, eNomorTelp, eWebsite, eDeskripsi, eEmail;
    Button btnTambah, btnSetLocation;
    SupportMapFragment mapFragment;
    SupportMapFragment mapFragment1;
    PlacesClient placesClient;
    String[] stringArray;
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
    Spinner mRefNama;
    EditText eJamMingguBuka,eMenitMingguBuka,eJamSeninBuka,eMenitSeninBuka,eJamSelasaBuka,eMenitselasaBuka,
            eJamRabuBuka,eMenitRabuBuka,eJamKamisBuka,eMenitKamisBuka, eJamJumatBuka,eMenitJumatBuka,
            eJamSabtuBuka,eMenitSabtuBuka;

    EditText eJamMingguTutup,eMenitMingguTutup,eJamSeninTutup,eMenitSeninTutup,eJamSelasaTutup,eMenitselasaTutup,
            eJamRabuTutup,eMenitRabuTutup,eJamKamisTutup,eMenitKamisTutup, eJamJumatTutup,eMenitJumatTutup,
            eJamSabtuTutup,eMenitSabtuTutup;

    TextView btnLiburMinggu,btnLiburSenin,btnLiburSelasa,btnLiburRabu,btnLiburKamis,btnLiburJumat,btnLiburSabtu;
    int sLiburMinggu =0;
    int sLiburSenin =0;
    int sLiburSelasa =0;
    int sLiburRabu =0;
    int sLiburKamis =0;
    int sLiburJumat =0;
    int sLiburSabtu =0;
    private byte[] imageBytes1, imageBytes2,imageBytes3;
    LinearLayout loadLayout;
    ViewGroup vg;
    View tempView;
    String id;
    int count=0;

    TextView mFileName;
    ImageView btnAddGamabar;
    private byte[] imageBytes;
    private static final int REQUEST_GET_SINGLE_FILE = 202;
    int status;
    List<GambarTukangDetil> gambarList = new ArrayList<>();
    ProgressDialog progressDialogUpdate;
    public EditTukang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_tukang, container, false);
        tempView = view;
        vg=container;
        progressDialogUpdate = new ProgressDialog(getContext());
        init(view);
        setSpinner();
        startLatLng = new LatLng(-6.7487,111.0379);
        currentLatLng = startLatLng;
        final Bundle bundle = getActivity().getIntent().getExtras();
        id = getArguments().getString("id_detil");


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
        mFileName = view.findViewById(R.id.mFileName);
        loadLayout = view.findViewById(R.id.loadLayout);
        btnAddGamabar = view.findViewById(R.id.btnAddGambar);

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


        //Spinner
        mRefNama = view.findViewById(R.id.mRefNama);


        //Button
        btnSetLocation = view.findViewById(R.id.btnLocation);
        btnTambah = view.findViewById(R.id.btnTambah);
        setMap = view.findViewById(R.id.showMap);
        form = view.findViewById(R.id.form);
        setJam = view.findViewById(R.id.btnSetJamBuka);
        layoutJam = view.findViewById(R.id.layoutJam);
        cardLocation = (CardView) view.findViewById(R.id.btnSetLocations);
        simpanLocation = (Button) view.findViewById(R.id.btnSimpanLocation);
        btnLiburMinggu = (TextView) view.findViewById(R.id.btnLiburMinggu);
        btnLiburSenin = (TextView) view.findViewById(R.id.btnLiburSenin);
        btnLiburSelasa = (TextView) view.findViewById(R.id.btnLiburSelasa);
        btnLiburRabu = (TextView) view.findViewById(R.id.btnLiburRabu);
        btnLiburKamis = (TextView) view.findViewById(R.id.btnLiburKamis);
        btnLiburJumat = (TextView) view.findViewById(R.id.btnLiburJumat);
        btnLiburSabtu = (TextView) view.findViewById(R.id.btnLiburSabtu);

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
                if(eNama.getText().toString().equals(""))
                {
                    eNama.setBackgroundTintList(getResources().getColorStateList(R.color.red));
                    eNama.setHintTextColor(getResources().getColor(R.color.red));
                    Toast.makeText(getContext(),"*Nama tukang tidak bole kosong",Toast.LENGTH_SHORT).show();
                }
                else {
                    updateAllTukangWithGambar();
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


        //set Button Libur

        btnLiburMinggu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sLiburMinggu==0)
                {
                    eJamMingguBuka.setEnabled(false);
                    eMenitMingguBuka.setEnabled(false);
                    eJamMingguTutup.setEnabled(false);
                    eMenitMingguTutup.setEnabled(false);
                    sLiburMinggu=1;
                    btnLiburMinggu.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    eJamMingguBuka.setEnabled(true);
                    eMenitMingguBuka.setEnabled(true);
                    eJamMingguTutup.setEnabled(true);
                    eMenitMingguTutup.setEnabled(true);
                    sLiburMinggu=0;
                    btnLiburMinggu.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });

        btnLiburSenin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sLiburSenin==0)
                {
                    eJamSeninBuka.setEnabled(false);
                    eMenitSeninBuka.setEnabled(false);
                    eJamSeninTutup.setEnabled(false);
                    eMenitSeninTutup.setEnabled(false);
                    sLiburSenin=1;
                    btnLiburSenin.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    eJamSeninBuka.setEnabled(true);
                    eMenitSeninBuka.setEnabled(true);
                    eJamSeninTutup.setEnabled(true);
                    eMenitSeninTutup.setEnabled(true);
                    sLiburSenin=0;
                    btnLiburSenin.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });

        btnLiburSelasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sLiburSelasa==0)
                {
                    eJamSelasaBuka.setEnabled(false);
                    eMenitselasaBuka.setEnabled(false);
                    eJamSelasaTutup.setEnabled(false);
                    eMenitselasaTutup.setEnabled(false);
                    sLiburSelasa=1;
                    btnLiburSelasa.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    eJamSelasaBuka.setEnabled(true);
                    eMenitselasaBuka.setEnabled(true);
                    eJamSelasaTutup.setEnabled(true);
                    eMenitselasaTutup.setEnabled(true);
                    sLiburSelasa=0;
                    btnLiburSelasa.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });

        btnLiburRabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sLiburRabu==0)
                {
                    eJamRabuBuka.setEnabled(false);
                    eMenitRabuBuka.setEnabled(false);
                    eJamRabuTutup.setEnabled(false);
                    eMenitRabuTutup.setEnabled(false);
                    sLiburRabu=1;
                    btnLiburRabu.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    eJamRabuBuka.setEnabled(true);
                    eMenitRabuBuka.setEnabled(true);
                    eJamRabuTutup.setEnabled(true);
                    eMenitRabuTutup.setEnabled(true);
                    sLiburRabu=0;
                    btnLiburRabu.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });

        btnLiburKamis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sLiburKamis==0)
                {
                    eJamKamisBuka.setEnabled(false);
                    eMenitKamisBuka.setEnabled(false);
                    eJamKamisTutup.setEnabled(false);
                    eMenitKamisTutup.setEnabled(false);
                    sLiburKamis=1;
                    btnLiburKamis.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    eJamKamisBuka.setEnabled(true);
                    eMenitKamisBuka.setEnabled(true);
                    eJamKamisTutup.setEnabled(true);
                    eMenitKamisTutup.setEnabled(true);
                    sLiburKamis=0;
                    btnLiburKamis.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });

        btnLiburJumat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sLiburJumat==0)
                {
                    eJamJumatBuka.setEnabled(false);
                    eMenitJumatBuka.setEnabled(false);
                    eJamJumatTutup.setEnabled(false);
                    eMenitJumatTutup.setEnabled(false);
                    sLiburJumat=1;
                    btnLiburJumat.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    eJamJumatBuka.setEnabled(true);
                    eMenitJumatBuka.setEnabled(true);
                    eJamJumatTutup.setEnabled(true);
                    eMenitJumatTutup.setEnabled(true);
                    sLiburJumat=0;
                    btnLiburJumat.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });
        btnLiburSabtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sLiburSabtu==0)
                {
                    eJamSabtuBuka.setEnabled(false);
                    eMenitSabtuBuka.setEnabled(false);
                    eJamSabtuTutup.setEnabled(false);
                    eMenitSabtuTutup.setEnabled(false);
                    sLiburSabtu=1;
                    btnLiburSabtu.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    eJamSabtuBuka.setEnabled(true);
                    eMenitSabtuBuka.setEnabled(true);
                    eJamSabtuTutup.setEnabled(true);
                    eMenitSabtuTutup.setEnabled(true);
                    sLiburSabtu=0;
                    btnLiburSabtu.setTextColor(getResources().getColor(R.color.black));
                }

            }
        });

        btnAddGamabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                status = 1;
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_GET_SINGLE_FILE);
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
        Call<JenisTukangLengkap> call2 = api2.tampilJenisTukang();

        call2.enqueue(new Callback<JenisTukangLengkap>() {
            @Override
            public void onResponse(Call<JenisTukangLengkap> call, Response<JenisTukangLengkap> response) {
                Map<String, JenisTukang> data = response.body().getData();


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

                getDataDetail();
            }

            @Override
            public void onFailure(Call<JenisTukangLengkap> call, Throwable t) {

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


    public void updateTukangWithGambar(){
        //defining a progress dialog to show while signing up

        progressDialogUpdate.setMessage("Loading...");
        progressDialogUpdate.show();

        String uID = "0";
        RequestBody userId = RequestBody.create(MediaType.parse("multipart/form-data"), uID);
        RequestBody requestFile =null;
        if(imageBytes!=null)
        {
            Log.d("notNull","1");
            requestFile= RequestBody.create(MediaType.parse("image/*"), imageBytes);
        }

        RequestBody nama = RequestBody.create(MediaType.parse("multipart/form-data"), eNama.getText().toString());
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        MultipartBody.Part gambarTukang =null;
        MultipartBody.Part gambarTukangUtama = null;
        if(requestFile!=null) {
            Log.d("notNull","2");
            gambarTukang = MultipartBody.Part.createFormData("gambar", "a.jpg", requestFile);
            // gambarTukangUtama = MultipartBody.Part.createFormData("gambarutama", "b.jpg", requestFile);
        }
        Call<DetilTukangBaru> call = api.updateDataTukangWithGambar(id,gambarTukang,gambarTukangUtama,nama,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);

        call.enqueue(new Callback<DetilTukangBaru>() {
            @Override
            public void onResponse(Call<DetilTukangBaru> call, final Response<DetilTukangBaru> response) {
                Toast.makeText(getContext(),"Sukses", Toast.LENGTH_SHORT).show();
                //Log.w("Response", new Gson().toJson(response.body()));
                refreshLayout();
            }

            @Override
            public void onFailure(Call<DetilTukangBaru> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
                Log.d("error","1");
            }
        });

    }

    public void updateAllTukangWithGambar(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        RequestBody nama = RequestBody.create(MediaType.parse("multipart/form-data"), eNama.getText().toString());
        RequestBody telp = RequestBody.create(MediaType.parse("multipart/form-data"), eNomorTelp.getText().toString());
        RequestBody email = RequestBody.create(MediaType.parse("multipart/form-data"), eEmail.getText().toString());
        RequestBody website = RequestBody.create(MediaType.parse("multipart/form-data"), eWebsite.getText().toString());
        RequestBody deskripsi = RequestBody.create(MediaType.parse("multipart/form-data"), eDeskripsi.getText().toString());
        RequestBody latitude;
        RequestBody longitude;
        if(location==null)
        {
            latitude = null;
            longitude =null;
        }
        else{
            latitude = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(location.latitude));
            longitude = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(location.longitude));
        }
        RequestBody value = RequestBody.create(MediaType.parse("multipart/form-data"), items_value[mRefNama.getSelectedItemPosition()]);
        String uID = "0";
        RequestBody userId = RequestBody.create(MediaType.parse("multipart/form-data"), uID);
        String hari_0;
        if(eJamMingguBuka.getText().toString().equals("") || eMenitMingguBuka.getText().toString().equals("")
                || eJamMingguTutup.getText().toString().equals("") || eMenitMingguTutup.getText().toString().equals("")
                || sLiburMinggu==1)
        {
            hari_0=null;
        }
        else {

            hari_0 = eJamMingguBuka.getText().toString()+ ":"+ eMenitMingguBuka.getText().toString() + " - " +
                    eJamMingguTutup.getText().toString()+ ":"+ eMenitMingguTutup.getText().toString();
        }
        String hari_1;
        if( eJamSeninBuka.getText().toString().equals("") || eMenitSeninBuka.getText().toString().equals("") ||
                eJamSeninTutup.getText().toString().equals("") || eMenitSeninTutup.getText().toString().equals("")
                || sLiburSenin==1)
        {
            hari_1=null;
        }
        else {

            hari_1 = eJamSeninBuka.getText().toString()+ ":"+ eMenitSeninBuka.getText().toString() + " - " +
                    eJamSeninTutup.getText().toString()+ ":"+ eMenitSeninTutup.getText().toString();
        }

        String hari_2;
        if(eJamSelasaBuka.getText().toString().equals("") ||  eMenitselasaBuka.getText().toString().equals("") ||
                eJamSelasaTutup.getText().toString().equals("") || eMenitselasaTutup.getText().toString().equals("")
                || sLiburSelasa==1)
        {
            hari_2=null;
        }
        else {

            hari_2 = eJamSelasaBuka.getText().toString()+ ":"+ eMenitselasaBuka.getText().toString() + " - " +
                    eJamSelasaTutup.getText().toString()+ ":"+ eMenitselasaTutup.getText().toString();
        }
        String hari_3;
        if(eJamRabuBuka.getText().toString().equals("") ||  eMenitRabuBuka.getText().toString().equals("") ||
                eJamRabuTutup.getText().toString().equals("") ||  eMenitRabuTutup.getText().toString().equals("")
                || sLiburRabu==1)
        {
            hari_3=null;
        }
        else {

            hari_3 = eJamRabuBuka.getText().toString()+ ":"+ eMenitRabuBuka.getText().toString() + " - " +
                    eJamRabuTutup.getText().toString()+ ":"+ eMenitRabuTutup.getText().toString();
        }

        String hari_4;
        if(eJamKamisBuka.getText().toString().equals("") || eMenitKamisBuka.getText().toString().equals("") ||
                eJamKamisTutup.getText().toString().equals("") || eMenitKamisTutup.getText().toString().equals("")
                || sLiburKamis==1)
        {
            hari_4=null;
        }
        else {

            hari_4 = eJamKamisBuka.getText().toString()+ ":"+ eMenitKamisBuka.getText().toString() + " - " +
                    eJamKamisTutup.getText().toString()+ ":"+ eMenitKamisTutup.getText().toString();
        }
        String hari_5;
        if(eJamJumatBuka.getText().toString().equals("") || eMenitJumatBuka.getText().toString().equals("") ||
                eJamJumatTutup.getText().toString().equals("") || eMenitJumatTutup.getText().toString().equals("")
                || sLiburJumat==1)
        {
            hari_5=null;
        }
        else {

            hari_5 =  eJamJumatBuka.getText().toString()+ ":"+ eMenitJumatBuka.getText().toString() + " - " +
                    eJamJumatTutup.getText().toString()+ ":"+ eMenitJumatTutup.getText().toString();
        }
        String hari_6;
        if(eJamSabtuBuka.getText().toString().equals("") || eMenitSabtuBuka.getText().toString().equals("") ||
                eJamSabtuTutup.getText().toString().equals("") ||  eMenitSabtuTutup.getText().toString().equals("")
                || sLiburSabtu==1)
        {
            hari_6=null;
        }
        else {

            hari_6 =  eJamSabtuBuka.getText().toString()+ ":"+ eMenitSabtuBuka.getText().toString() + " - " +
                    eJamSabtuTutup.getText().toString()+ ":"+ eMenitSabtuTutup.getText().toString();
        }
        RequestBody rHari_0=null;
        if(hari_0!=null)
        {
            rHari_0 = RequestBody.create(MediaType.parse("multipart/form-data"), hari_0);
        }
        RequestBody rHari_1=null;
        if(hari_1!=null)
        {
            rHari_1 = RequestBody.create(MediaType.parse("multipart/form-data"), hari_1);
        }
        RequestBody rHari_2=null;
        if(hari_2!=null)
        {
            rHari_2 = RequestBody.create(MediaType.parse("multipart/form-data"), hari_2);
        }
        RequestBody rHari_3=null;
        if(hari_3!=null)
        {
            rHari_3 = RequestBody.create(MediaType.parse("multipart/form-data"), hari_3);
        }
        RequestBody rHari_4=null;
        if(hari_4!=null)
        {
            rHari_4 = RequestBody.create(MediaType.parse("multipart/form-data"), hari_4);
        }
        RequestBody rHari_5=null;
        if(hari_5!=null)
        {
            rHari_5 = RequestBody.create(MediaType.parse("multipart/form-data"), hari_5);
        }
        RequestBody rHari_6=null;
        if(hari_6!=null)
        {
            rHari_6 = RequestBody.create(MediaType.parse("multipart/form-data"), hari_6);
        }





        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);


        Call<DetilTukangBaru> call = api.updateDataTukangWithGambar(id,null,null,nama,telp,email,website,deskripsi,latitude,longitude,rHari_0,rHari_1,rHari_2,rHari_3,rHari_4,rHari_5,rHari_6,userId,value);

        call.enqueue(new Callback<DetilTukangBaru>() {
            @Override
            public void onResponse(Call<DetilTukangBaru> call, final Response<DetilTukangBaru> response) {
                Toast.makeText(getContext(),"Sukses", Toast.LENGTH_SHORT).show();
                Log.w("Response", new Gson().toJson(response.body()));
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DetilTukangBaru> call, Throwable t) {
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



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_GET_SINGLE_FILE) {

                    Uri selectedImageUri = data.getData();
                    Cursor returnCursor = getActivity().getContentResolver().query(selectedImageUri,null,null,null,null);
                    // Get the path from the Uri
                    final String path = getPathFromURI(selectedImageUri);
                    if (path != null) {
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);


                    }

                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    returnCursor.moveToFirst();
                    mFileName.setText(returnCursor.getString(nameIndex));
                    InputStream is = getActivity().getContentResolver().openInputStream(data.getData());

                    imageBytes = getBytes(is);
                    updateTukangWithGambar();
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }


    public void refreshGambarLoad()
    {
        for(int i =0;i<loadLayout.getChildCount()-1;i++)
        {
            loadLayout.removeViewAt(i);
        }
    }


    public void getDataDetail(){

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilTukangModel> call = api.detailTukang(id);

        call.enqueue(new Callback<DetilTukangModel>() {
            @Override
            public void onResponse(Call<DetilTukangModel> call, Response<DetilTukangModel> response) {
             /*   title.setText(response.body().getJudul());
                refnama.setText(response.body().getData().getRef_tukang_nama());
                ratingsum.setText(String.format("%s/5",response.body().getData().getRating()));
                String tampung = response.body().getData().getRating_jumlah();
                ratingpeople.setText(String.format("(%s orang)", tampung));*/
                eNama.setText(response.body().getData().getNama());
                //textAlamat.setText(response.body().getData().getAlamat());
                eDeskripsi.setText(response.body().getData().getDeskripsi());
                eEmail.setText(response.body().getData().getEmail());
                eWebsite.setText(response.body().getData().getWebsite());
                eNomorTelp.setText(response.body().getData().getTelp());


                for (int i =0 ;i<stringArray.length;i++)
                {
                    if(stringArray[i].equals(response.body().getData().getRef_tukang_nama()))
                    {
                        mRefNama.setSelection(i);
                    }
                }
                Float lat = Float.parseFloat(response.body().getData().getLatitude());
                Float longt = Float.parseFloat(response.body().getData().getLongitude());
                LatLng lokasi = new LatLng(lat,longt);
                //currentLatLng = lokasi;
                /*Bitmap image = getBitmapFromURL(response.body().getData().getFile());
                BitmapDrawable drawable1 = new BitmapDrawable(image);
                btnAddGamabar.setImageDrawable(drawable1);*/

                gambarList = response.body().getData().getGambar();


                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                for(int i= gambarList.size()-1 ; i>=0 ; i--)
                {
                    final int index = i;
                    final RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.activity_add_gambar_adapter,vg,false);
                   /* Bitmap image = getBitmapFromURL(gambarList.get(i).getFile_tukang_img());
                    BitmapDrawable drawable = new BitmapDrawable(image);*/
                    final ImageView gambarLayout = view.findViewById(R.id.btnAddGambar);
                    ImageView deleteGambar = view.findViewById(R.id.btnHapusGambar);

                    URL url = null;
                    if(gambarList.get(i).getFile_tukang_img().equals(""))
                    {
                        //tidak terjadi perubahan apapun
                    }
                    else
                    {
                        try {
                            url = new URL(gambarList.get(i).getFile_tukang_img());
                            Picasso.get()
                                    .load(String.valueOf(url))
                                    .into(gambarLayout);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                  /*  gambarLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tempGambar=gambarLayout;
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            intent.setType("image/*");
                            status = 0;
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_GET_SINGLE_FILE);

                        }
                    });*/
                    deleteGambar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            hapusGambar(gambarList.get(index).getFile_tukang_id());
                            view.setVisibility(View.GONE);
                            view.removeAllViews();

                        }
                    });
                    loadLayout.addView(view,0);
                }



                mMap1.addMarker(new MarkerOptions().position(lokasi));
                mMap1.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 15));
                mMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 15));


            }

            @Override
            public void onFailure(Call<DetilTukangModel> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
            }
        });
    }

    public void refreshLayout(){
//        refreshGambarLoad();
//        for(int i =0;i<loadLayout.getChildCount()-1;i++)
//        {
//            loadLayout.removeViewAt(i);
//        }
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilTukangModel> call = api.detailTukang(id);

        call.enqueue(new Callback<DetilTukangModel>() {
            @Override
            public void onResponse(Call<DetilTukangModel> call, Response<DetilTukangModel> response) {

                gambarList = response.body().getData().getGambar();


                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);

                final int index =gambarList.size()-1 ;
                final RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.activity_add_gambar_adapter,vg,false);
                   /* Bitmap image = getBitmapFromURL(gambarList.get(index).getFile_tukang_img());
                    BitmapDrawable drawable = new BitmapDrawable(image);*/
                final ImageView gambarLayout = view.findViewById(R.id.btnAddGambar);
                ImageView deleteGambar = view.findViewById(R.id.btnHapusGambar);
                URL url = null;
                if(gambarList.get(index).getFile_tukang_img().equals(""))
                {
                    //tidak terjadi perubahan apapun
                }
                else
                {
                    try {
                        url = new URL(gambarList.get(index).getFile_tukang_img());
                        Picasso.get()
                                .load(String.valueOf(url))
                                .into(gambarLayout);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }



                deleteGambar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        hapusGambar(gambarList.get(index).getFile_tukang_id());
                        view.setVisibility(View.GONE);
                        view.removeAllViews();
                    }
                });
                loadLayout.addView(view,loadLayout.getChildCount()-1);
                progressDialogUpdate.dismiss();
            }

            @Override
            public void onFailure(Call<DetilTukangModel> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
                progressDialogUpdate.dismiss();
            }
        });
    }

    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    public void hapusGambar(String id_gambar){
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilTukangModel> call = api.hapusGambarTukang(id_gambar);

        call.enqueue(new Callback<DetilTukangModel>() {
            @Override
            public void onResponse(Call<DetilTukangModel> call, Response<DetilTukangModel> response) {
                Toast.makeText(getContext(), "deleted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DetilTukangModel> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
            }
        });
    }
}
