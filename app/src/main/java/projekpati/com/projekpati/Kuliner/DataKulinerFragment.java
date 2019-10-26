package projekpati.com.projekpati.Kuliner;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientApiKey;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.APIKey;
import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class DataKulinerFragment extends Fragment implements OnMapReadyCallback {
    ListView listView;
    Integer CountShowData;
    Integer nextPage;
    Integer npLatn=1;
    List<ListKuliner> list = new ArrayList<>();
    ArrayList<ListKuliner> listLatn = new ArrayList<ListKuliner>();
    LinearLayout lp;
    MapView mapView;
    String apikey;


    public DataKulinerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_kuliner, container, false);
        listView = (ListView) view.findViewById(R.id.listKuliner);

        getAllKuliner();
        getAllData();




        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                int threshold = 1;
                int count = listView.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (listView.getLastVisiblePosition() >= count - threshold) {
                        // Execute LoadMoreDataTask AsyncTask
                        //Toast.makeText(MenuKuliner.this, String.valueOf(nextPage),Toast.LENGTH_SHORT).show();
                        if (nextPage==0){
                            Toast.makeText(getContext(), "No More Data",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadMoreData();
                        }

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


            }
        });
        return view;
    }


    public void getAllKuliner(){
        //defining a progress dialog to show while signing up
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> call = api.tampilSemuaKuliner();

        call.enqueue(new Callback<KulinerModel>() {
            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();



                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }
                nextPage = response.body().getHalaman_selanjutnya();

                listView.setAdapter(new KulinerAdapter(getContext(), R.layout.kuliner_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),DetilKuliner.class);
                intent.putExtra("id_kuliner",list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    public void loadMoreData(){
        //defining a progress dialog to show while signing up

        CountShowData = (listView.getHeight()/161);
        Log.d("Height: ", String.valueOf(listView.getHeight()));
        Log.d("Height: ", String.valueOf(CountShowData));


        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> call = api.loadMoreKuliner(String.valueOf(nextPage));

        call.enqueue(new Callback<KulinerModel>() {
            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();
                Integer beforePage = nextPage;

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                    Log.d("value :", String.valueOf(i) );
                }


                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("next Page: ", String.valueOf(response.body().getHalaman_selanjutnya()));


                KulinerAdapter adapter = new KulinerAdapter(getContext(), R.layout.kuliner_adapter, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                listView.setSelection(beforePage-CountShowData);


            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),DetilKuliner.class);
                intent.putExtra("id_kuliner",list.get(position).getId());
                startActivity(intent);
            }
        });
    }


    public void getAllData(){

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> call = api.tampilSemuaKuliner();

        call.enqueue(new Callback<KulinerModel>() {
            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();

                Log.w("Response1", new Gson().toJson(response.body()));
                for (int i = npLatn; i <= npLatn+response.body().getJumlah_data()-1; i++)
                {

                    listLatn.add(data.get(String.valueOf(i)));

                }
                Log.w("List", new Gson().toJson((listLatn)));
                npLatn = response.body().getHalaman_selanjutnya();



                Integer np=1;
                Log.d("next: ",String.valueOf(npLatn));
                if(npLatn!=0)
                {
                    loadmore();

                }
                else {

                    loadMap();
                }


            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

    }

    public void loadmore(){

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> a = api.loadMoreKuliner(String.valueOf(npLatn));
        a.enqueue(new Callback<KulinerModel>() {

            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();


                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = npLatn; i <= npLatn+response.body().getJumlah_data()-1; i++)
                {

                        listLatn.add(data.get(String.valueOf(i)));

                }
                // beforePage=nextPage;
                npLatn = response.body().getHalaman_selanjutnya();
                Log.d("nextpage",String.valueOf(npLatn));
                if(npLatn!=0)
                {
                    loadmore();
                }
                else {
                    Log.w("List", new Gson().toJson((listLatn)));
                    loadMap();
                }


            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent i = new Intent(getContext(), DetailMap.class);
                Bundle bundle = new Bundle();

               // bundle.putParcelableArrayList("list",listLatn);

                i.putParcelableArrayListExtra("list",listLatn);
                startActivity(i);
            }
        });

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.clear();

        CameraPosition googlePlex = CameraPosition.builder()
            .target(new LatLng(-6.7487,111.0379))
            .zoom(15)
            .bearing(0)
            .build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex),100,null);

        for(ListKuliner lk : listLatn)
        {
            Log.d("masuk","a");
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Float.parseFloat(lk.getLatitude()),Float.parseFloat(lk.getLongitude())))
                    .title(lk.getNama())
                    .snippet(lk.getAlamat())
                    .icon(bitmapDescriptor(getContext(),R.drawable.ic_location_on_black_24dp)));
        }

//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(48.8584,2.2945))
//                .title("c")
//                .snippet("d")
//                .icon(bitmapDescriptor(getContext(),R.drawable.ic_location_on_black_24dp)));

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

    public void loadMap()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


    }

    public void getApiKey(){
        //defining a progress dialog to show while signing up

        final API api = RetrofitClientApiKey.getRetrofitInstance().create(API.class);
        Call<APIKey> call = api.getAPIkey();

        call.enqueue(new Callback<APIKey>() {
            @Override
            public void onResponse(Call<APIKey> call, final Response<APIKey> response) {

                Log.w("Response", response.body().getKey());
                apikey=response.body().getKey();
            }

            @Override
            public void onFailure(Call<APIKey> call, Throwable t) {
                Log.d("onResponse", t.toString());
            }
        });

    }
}