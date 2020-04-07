package projekpati.com.projekpati.Kerjaan;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.ImageButton;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Kerjaan.DetilKerjaanBaru;
import projekpati.com.projekpati.Model.Kerjaan.JenisKerjaan;
import projekpati.com.projekpati.Model.Kerjaan.JenisKerjaanLengkap;
import projekpati.com.projekpati.Model.tempImageModel;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TambahKerjaanFragment extends Fragment implements OnMapReadyCallback, LocationListener{


    public TambahKerjaanFragment() {
        // Required empty public constructor
    }

    String[] items_value;
    GoogleMap mMap1, mMap2;
    EditText eNama, eNomorTelp, eWebsite, eDeskripsi, eEmail, eKantor, eGaji, ePengalaman;
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
    ImageView btnAddGamabar;
    TextView mFileName;
    private byte[] imageBytes1, imageBytes2,imageBytes3;
    String fileName1, fileName2, fileName3;

    private static final int REQUEST_GET_SINGLE_FILE = 202;
    LinearLayout loadLayout;
    ViewGroup vg;
    int count=0;
    int multiple=0;
    Map<String, tempImageModel> imageByte;
    String userid,nama,email,telp,website;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tambah_kerjaan, container, false);
        vg=container;

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
        //editText
        eNama = view.findViewById(R.id.eNama);
        eNomorTelp = view.findViewById(R.id.mNomorTelp);
        eEmail = view.findViewById(R.id.mEmail);
        eWebsite = view.findViewById(R.id.mWebsite);
        eDeskripsi = view.findViewById(R.id.mDeskripsi);
        eGaji = view.findViewById(R.id.mGaji);
        ePengalaman = view.findViewById(R.id.mPengalaman);
        eKantor = view.findViewById(R.id.mKantor);
        btnAddGamabar = view.findViewById(R.id.btnAddGambar);
        mFileName = view.findViewById(R.id.mFileName);
        loadLayout = view.findViewById(R.id.loadLayout);

        eNomorTelp.setText(telp);
        eEmail.setText(email);
        eWebsite.setText(website);

        //Spinner
        mRefNama = view.findViewById(R.id.mRefNama);


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
                    Toast.makeText(getContext(),"*Nama kerjaan tidak bole kosong",Toast.LENGTH_SHORT).show();
                }
                else {
                    addKerjaanWithGambar();
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

        btnAddGamabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
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
        Call<JenisKerjaanLengkap> call2 = api2.tampilJenisKerjaan();

        call2.enqueue(new Callback<JenisKerjaanLengkap>() {
            @Override
            public void onResponse(Call<JenisKerjaanLengkap> call, Response<JenisKerjaanLengkap> response) {
                Map<String, JenisKerjaan> data = response.body().getData();

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
            public void onFailure(Call<JenisKerjaanLengkap> call, Throwable t) {

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




    public void addKerjaanWithGambar(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        int j=0;

        for(int i=0;i<loadLayout.getChildCount()-1;i++)
        {

            if(loadLayout.getChildAt(i)!=null)
            {
                Log.d("test",String.valueOf(i));
                ImageView imageView = loadLayout.getChildAt(i).findViewById(R.id.btnAddGambar);
                if(imageView!= null)
                {

                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] imageinByte = baos.toByteArray();
                    if(j==0)
                    {
                        imageBytes1=imageinByte;
                    }
                    else if(j==1)
                    {
                        imageBytes2=imageinByte;
                    }
                    else if(j==2)
                    {
                        imageBytes3=imageinByte;
                    }
                    j++;

                }


            }

        }


        RequestBody nama = RequestBody.create(MediaType.parse("multipart/form-data"), eNama.getText().toString());
        RequestBody telp = RequestBody.create(MediaType.parse("multipart/form-data"), eNomorTelp.getText().toString());
        RequestBody email = RequestBody.create(MediaType.parse("multipart/form-data"), eEmail.getText().toString());
        RequestBody kantor = RequestBody.create(MediaType.parse("multipart/form-data"), eKantor.getText().toString());
        RequestBody gaji = RequestBody.create(MediaType.parse("multipart/form-data"), eGaji.getText().toString());
        RequestBody pengalaman = RequestBody.create(MediaType.parse("multipart/form-data"), ePengalaman.getText().toString());
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
        RequestBody userId = RequestBody.create(MediaType.parse("multipart/form-data"), userid);
        RequestBody requestFile1=null;
        RequestBody requestFile2=null;
        RequestBody requestFile3=null;
        if(imageBytes1!=null)
        {
            requestFile1 = RequestBody.create(MediaType.parse("image/*"), imageBytes1);
        }
        if(imageBytes2!=null)
        {
            requestFile2 = RequestBody.create(MediaType.parse("image/*"), imageBytes2);
        }
        if(imageBytes3!=null)
        {
            requestFile3 = RequestBody.create(MediaType.parse("image/*"), imageBytes3);
        }

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);

        MultipartBody.Part gambarKerjaanUtama=null;
        MultipartBody.Part gambarKerjaan1=null;
        MultipartBody.Part gambarKerjaan2=null;
        MultipartBody.Part gambarKerjaan3=null;
        if(imageBytes1!=null)
        {
            gambarKerjaan1 = MultipartBody.Part.createFormData("gambar", "a.jpg", requestFile1);
            gambarKerjaanUtama = MultipartBody.Part.createFormData("gambarutama", "a.jpg", requestFile1);
            Log.d("masuk","1");
        }else{
            Log.d("nullImage","1");
        }
        if(imageBytes2!=null)
        {
            gambarKerjaan2 = MultipartBody.Part.createFormData("gambar2", "b.jpg", requestFile2);
            Log.d("masuk","2");
        }else{
            Log.d("nullImage","2");
        }
        if(imageBytes3!=null)
        {
            gambarKerjaan3 = MultipartBody.Part.createFormData("gambar3", "c.jpg", requestFile3);
            Log.d("masuk","3");
        }else{
            Log.d("nullImage","3");
        }

        Call<DetilKerjaanBaru> call = api.addDataKerjaanWithGambar(gambarKerjaan1,gambarKerjaan2,gambarKerjaan3,gambarKerjaanUtama,nama,telp,email,kantor,gaji,pengalaman,website,deskripsi,latitude,longitude,userId,value);

        call.enqueue(new Callback<DetilKerjaanBaru>() {
            @Override
            public void onResponse(Call<DetilKerjaanBaru> call, final Response<DetilKerjaanBaru> response) {
                Toast.makeText(getContext(),"Sukses", Toast.LENGTH_SHORT).show();
                Log.w("Response", new Gson().toJson(response.body()));
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DetilKerjaanBaru> call, Throwable t) {
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
                    // Set the image in ImageView



                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                    final RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.activity_add_gambar_adapter,vg,false);
                    final ImageView gambarLayout = view.findViewById(R.id.btnAddGambar);
                    ImageView deleteGambar = view.findViewById(R.id.btnHapusGambar);

                    gambarLayout.setImageURI(selectedImageUri);

                    deleteGambar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnAddGamabar.setVisibility(View.VISIBLE);
                            view.setVisibility(View.GONE);
                            view.removeAllViewsInLayout();
                            count--;



                        }
                    });


                    int size = loadLayout.getChildCount();

                    loadLayout.addView(view, size-1);

                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    returnCursor.moveToFirst();
                    mFileName.setText(returnCursor.getString(nameIndex));
                    InputStream is = getActivity().getContentResolver().openInputStream(data.getData());
                    // Toast.makeText(getContext(),String.valueOf(count),Toast.LENGTH_SHORT).show();
                   /* tempImageModel gambar = new tempImageModel(String.valueOf(loadLayout.indexOfChild(view)),getBytes(is));
                    imageByte.put(String.valueOf(count),gambar);*/

                    count++;
                    if(count==3)
                    {
                        btnAddGamabar.setVisibility(View.GONE);
                    }
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
}
