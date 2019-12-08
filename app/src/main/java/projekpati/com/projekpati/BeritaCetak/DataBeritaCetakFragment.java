package projekpati.com.projekpati.BeritaCetak;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.BeritaCetak.BeritaCetakModel;
import projekpati.com.projekpati.Model.BeritaCetak.ListBeritaCetak;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataBeritaCetakFragment extends Fragment {
    ListView listView;
    Integer CountShowData;
    Integer nextPage;
    Integer npLatn=1;
    List<ListBeritaCetak> list = new ArrayList<>();
    ArrayList<ListBeritaCetak> listLatn = new ArrayList<ListBeritaCetak>();
    LinearLayout lp;
    MapView mapView;
    String apikey;
    ProgressDialog progressDialog;
    int Status=0;
    SwipeRefreshLayout swipeRefreshLayout;

    public DataBeritaCetakFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_berita_cetak, container, false);
        listView = (ListView) view.findViewById(R.id.listBeritaCetak);
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

//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
        getAllBeritaOnline();




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

    public void getAllBeritaOnline(){
        //defining a progress dialog to show while signing up
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<BeritaCetakModel> call = api.tampilSemuaBeritaCetak();

        call.enqueue(new Callback<BeritaCetakModel>() {
            @Override
            public void onResponse(Call<BeritaCetakModel> call, final Response<BeritaCetakModel> response) {
                Map<String, ListBeritaCetak> data = response.body().getData();


                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }
                nextPage = response.body().getHalaman_selanjutnya();

                listView.setAdapter(new BeritaCetakAdapter(getContext(), R.layout.berita_cetak_adapter, list));


            }

            @Override
            public void onFailure(Call<BeritaCetakModel> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetilBeritaCetak.class);
                intent.putExtra("id_berita_cetak",list.get(position).getId());
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
        Call<BeritaCetakModel> call = api.loadMoreBeritaCetak(String.valueOf(nextPage));

        call.enqueue(new Callback<BeritaCetakModel>() {
            @Override
            public void onResponse(Call<BeritaCetakModel> call, final Response<BeritaCetakModel> response) {
                Map<String, ListBeritaCetak> data = response.body().getData();
                Integer beforePage = nextPage;

                Log.w("ResponseLoad", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                    Log.d("value :", String.valueOf(i) );
                }


                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("next Page: ", String.valueOf(response.body().getHalaman_selanjutnya()));


                BeritaCetakAdapter adapter = new BeritaCetakAdapter(getContext(), R.layout.berita_cetak_adapter, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listView.setSelection(beforePage-CountShowData);
                Status=0;

            }

            @Override
            public void onFailure(Call<BeritaCetakModel> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),DetilBeritaCetak.class);
                intent.putExtra("id_berita_cetak",list.get(position).getId());
                startActivity(intent);
            }
        });
    }

}
