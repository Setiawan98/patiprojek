package projekpati.com.projekpati.Video;

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
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
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
import projekpati.com.projekpati.API.RetrofitClientInstanceDemo;
import projekpati.com.projekpati.Model.APIKey;
import projekpati.com.projekpati.Model.Video.VideoModel;
import projekpati.com.projekpati.Model.Video.ListVideo;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataVideoFragment extends Fragment {
    ListView listView;
    Integer CountShowData;
    Integer nextPage;
    Integer npLatn=1;
    List<ListVideo> list = new ArrayList<>();
    ArrayList<ListVideo> listLatn = new ArrayList<ListVideo>();
    LinearLayout lp;
    MapView mapView;
    String apikey;
    ProgressDialog progressDialog;
    int Status=0;
    SwipeRefreshLayout swipeRefreshLayout;


    public DataVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_video, container, false);
        listView = (ListView) view.findViewById(R.id.listVideo);
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
        getAllVideo();




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


    public void getAllVideo(){
        //defining a progress dialog to show while signing up
        API api = RetrofitClientInstanceDemo.getRetrofitInstance().create(API.class);
        Call<VideoModel> call = api.tampilSemuaVideo();

        call.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, final Response<VideoModel> response) {
                Map<String, ListVideo> data = response.body().getData();


                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }
                nextPage = response.body().getHalaman_selanjutnya();

                listView.setAdapter(new VideoAdapter(getContext(), R.layout.video_adapter, list));

                progressDialog.dismiss();


            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetilVideo.class);
                intent.putExtra("id_video",list.get(position).getKode());
                startActivity(intent);
            }
        });
    }

    public void loadMoreData(){
        //defining a progress dialog to show while signing up

        Status=1;
        CountShowData = (listView.getHeight()/161);
        Log.d("Height: ", String.valueOf(listView.getHeight()));
        Log.d("Height: ", String.valueOf(CountShowData));



        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<VideoModel> call = api.loadMoreVideo(String.valueOf(nextPage));

        call.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, final Response<VideoModel> response) {
                Map<String, ListVideo> data = response.body().getData();
                Integer beforePage = nextPage;

                Log.w("ResponseLoad", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                    Log.d("value :", String.valueOf(i) );
                }


                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("next Page: ", String.valueOf(response.body().getHalaman_selanjutnya()));


                VideoAdapter adapter = new VideoAdapter(getContext(), R.layout.video_adapter, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listView.setSelection(beforePage-CountShowData);
                Status=0;

            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),DetilVideo.class);
                intent.putExtra("id_video",list.get(position).getId());
                startActivity(intent);
            }
        });
    }



    public void loadmore(){


        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<VideoModel> a = api.loadMoreVideo(String.valueOf(npLatn));
        a.enqueue(new Callback<VideoModel>() {

            @Override
            public void onResponse(Call<VideoModel> call, final Response<VideoModel> response) {
                Map<String, ListVideo> data = response.body().getData();


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

                }


            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });


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
