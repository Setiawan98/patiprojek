package projekpati.com.projekpati.FasilitasUmum;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientApiKey;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Kesehatan.DetilMapKesehatan;
import projekpati.com.projekpati.Kesehatan.KesehatanAdapter;
import projekpati.com.projekpati.Model.APIKey;
import projekpati.com.projekpati.Model.FasilitasUmum.FasilitasUmumModel;
import projekpati.com.projekpati.Model.FasilitasUmum.ListFasilitasUmum;
import projekpati.com.projekpati.Model.Kesehatan.KesehatanModel;
import projekpati.com.projekpati.Model.Kesehatan.ListKesehatan;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataFasilitasUmumFragment extends Fragment implements OnMapReadyCallback {
    ListView listView;
    Integer CountShowData;
    Integer nextPage;
    Integer npLatn=1;
    List<ListFasilitasUmum> list = new ArrayList<>();
    ArrayList<ListFasilitasUmum> listLatn = new ArrayList<ListFasilitasUmum>();
    LinearLayout lp;
    MapView mapView;
    String apikey;
    ProgressDialog progressDialog;
    int Status=0;
    SwipeRefreshLayout swipeRefreshLayout;


    public DataFasilitasUmumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_fasilitas_umum, container, false);
        listView = (ListView) view.findViewById(R.id.listFasilitasUmum);
        swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                startActivity(getActivity().getIntent());
                getActivity().overridePendingTransition(0,0);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        getAllFasilitasUmum();
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
                            if(Status==0)
                            {
                                loadMoreData();
                            }

                        }

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(listView.getChildAt(0)!=null)
                {
                    swipeRefreshLayout.setEnabled(listView.getFirstVisiblePosition() ==0 && listView.getChildAt(0).getTop() ==0);
                }

            }
        });
        return view;
    }


    public void getAllFasilitasUmum(){
        //defining a progress dialog to show while signing up
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<FasilitasUmumModel> call = api.tampilSemuaFasilitasUmum();

        call.enqueue(new Callback<FasilitasUmumModel>() {
            @Override
            public void onResponse(Call<FasilitasUmumModel> call, final Response<FasilitasUmumModel> response) {
                Map<String, ListFasilitasUmum> data = response.body().getData();


                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }
                nextPage = response.body().getHalaman_selanjutnya();

                listView.setAdapter(new FasilitasUmumAdapter(getContext(), R.layout.fasilitas_umum_adapter, list));


            }

            @Override
            public void onFailure(Call<FasilitasUmumModel> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getContext(), DetilPendidikan.class);
//                intent.putExtra("id_pendidikan",list.get(position).getId());
//                startActivity(intent);
//            }
//        });
    }

    public void loadMoreData(){
        //defining a progress dialog to show while signing up

        Status=1;
        CountShowData = (listView.getHeight()/161);
        Log.d("Height: ", String.valueOf(listView.getHeight()));
        Log.d("Height: ", String.valueOf(CountShowData));



        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<FasilitasUmumModel> call = api.loadMoreFasilitasUmum(String.valueOf(nextPage));

        call.enqueue(new Callback<FasilitasUmumModel>() {
            @Override
            public void onResponse(Call<FasilitasUmumModel> call, final Response<FasilitasUmumModel> response) {
                Map<String, ListFasilitasUmum> data = response.body().getData();
                Integer beforePage = nextPage;

                Log.w("ResponseLoad", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                    Log.d("value :", String.valueOf(i) );
                }


                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("next Page: ", String.valueOf(response.body().getHalaman_selanjutnya()));


                FasilitasUmumAdapter adapter = new FasilitasUmumAdapter(getContext(), R.layout.fasilitas_umum_adapter, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listView.setSelection(beforePage-CountShowData);
                Status=0;

            }

            @Override
            public void onFailure(Call<FasilitasUmumModel> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getContext(),DetilPendidikan.class);
//                intent.putExtra("id_pendidikan",list.get(position).getId());
//                startActivity(intent);
//            }
//        });
    }


    public void getAllData(){

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<FasilitasUmumModel> call = api.tampilSemuaFasilitasUmum();

        call.enqueue(new Callback<FasilitasUmumModel>() {
            @Override
            public void onResponse(Call<FasilitasUmumModel> call, final Response<FasilitasUmumModel> response) {
                Map<String, ListFasilitasUmum> data = response.body().getData();

                Log.w("Responsepend", new Gson().toJson(response.body()));
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
            public void onFailure(Call<FasilitasUmumModel> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

    }

    public void loadmore(){


        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<FasilitasUmumModel> a = api.loadMoreFasilitasUmum(String.valueOf(npLatn));
        a.enqueue(new Callback<FasilitasUmumModel>() {

            @Override
            public void onResponse(Call<FasilitasUmumModel> call, final Response<FasilitasUmumModel> response) {
                Map<String, ListFasilitasUmum> data = response.body().getData();


                Log.w("Responsepend", new Gson().toJson(response.body()));
                for (int i = npLatn; i <= npLatn+response.body().getJumlah_data()-1; i++)
                {

                    listLatn.add(data.get(String.valueOf(i)));
//                    if(data.get(String.valueOf(i)).getRef_kuliner_nama()!=null)
//                    {
//                        Log.d("ref: ",data.get(String.valueOf(i)).getRef_kuliner_nama());
//                    }

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
            public void onFailure(Call<FasilitasUmumModel> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {

                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                View view = getLayoutInflater().inflate(R.layout.infowindowmap,null);
                TextView name = view.findViewById(R.id.mNama);
                TextView alamat = view.findViewById(R.id.mAlamat);
                name.setText(marker.getTitle());
                alamat.setText(marker.getSnippet());
                return view;
            }
        });
//        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                String id = (String) marker.getTag();
//                Intent intent = new Intent(getContext(),DetilPendidikan.class);
//                intent.putExtra("id_pendidikan",id);
//                Log.d("idwoy1",id);
//                startActivity(intent);
//
//
//            }
//        });
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent i = new Intent(getContext(), DetilMapKesehatan.class);
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

        for(ListFasilitasUmum lp : listLatn)
        {
            Log.d("masuk","a");
            BitmapDescriptor icon = null;
//            if(lp.getRef_kesehatan_icon().equals(""))
//            {
            Drawable vectorDrawable = ContextCompat.getDrawable(getContext(),R.drawable.ic_location_on_black_24dp);
            vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
            Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.draw(canvas);
            icon = BitmapDescriptorFactory.fromBitmap(bitmap);
//            }
//            else {
//                try {
//                    URL url = new URL(lp.getRef_kesehatan_icon());
//                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                    Bitmap scaled = Bitmap.createScaledBitmap(bmp,50,50,true);
//                    icon =  BitmapDescriptorFactory.fromBitmap(scaled);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Float.parseFloat(lp.getLatitude()),Float.parseFloat(lp.getLongitude())))
                    .title(lp.getNama())
                    .snippet(lp.getAlamat())
                    .icon(icon));

            String id= lp.getId();
            marker.setTag(id);
        }

        progressDialog.dismiss();

//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(48.8584,2.2945))
//                .title("c")
//                .snippet("d")
//                .icon(bitmapDescriptor(getContext(),R.drawable.ic_location_on_black_24dp)));

    }

    private BitmapDescriptor bitmapDescriptor(BitmapDescriptor icon) {
        return null;
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
