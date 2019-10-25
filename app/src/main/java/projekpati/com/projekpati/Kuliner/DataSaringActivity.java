package projekpati.com.projekpati.Kuliner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSaringActivity extends AppCompatActivity {
    ListView listView;
    Integer nextPage = 1;
    Integer CountShowData;
    Integer beforePage;
    List<ListKuliner> list = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    Boolean isFinised = true;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_saring);

        toolbar = findViewById(R.id.kulinerToolbar);
        listView = (ListView) findViewById(R.id.listKuliner);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        title.setText("Saring");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        getAllData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DataSaringActivity.this, DetilKuliner.class);
                intent.putExtra("id_kuliner", list.get(position).getId());
                startActivity(intent);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                int threshold = 1;
                int count = listView.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (listView.getLastVisiblePosition() >= count - threshold) {
                        // Execute LoadMoreDataTask AsyncTask
                        //Toast.makeText(MenuKuliner.this, String.valueOf(nextPage),Toast.LENGTH_SHORT).show();
                        if (nextPage == 0) {
                            Toast.makeText(DataSaringActivity.this, "No More Data", Toast.LENGTH_SHORT).show();
                        } else {
                            loadmore();
                        }

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


            }
        });
    }


    public void getAllData() {
        final Bundle bundle = getIntent().getExtras();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> call = api.tampilSemuaKuliner();

        call.enqueue(new Callback<KulinerModel>() {
            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++) {
                    Bundle bundle = getIntent().getExtras();
                    Double longitude = bundle.getDouble("longitude");
                    Double latitude = bundle.getDouble("latitude");

                    if(latitude<Double.parseDouble(data.get(String.valueOf(i)).getLatitude()))
                    {
                        Location startPoint=new Location("locationA");
                        startPoint.setLatitude(Double.parseDouble(data.get(String.valueOf(i)).getLatitude()));
                        startPoint.setLongitude(Double.parseDouble(data.get(String.valueOf(i)).getLongitude()));

                        Location endPoint=new Location("locationB");
                        endPoint.setLatitude(latitude);
                        endPoint.setLongitude(longitude);
                        double distance = startPoint.distanceTo(endPoint);

                        if(distance <= 500)
                        {
                            list.add(data.get(String.valueOf(i)));
                        }
                    }
                    else
                    {
                        Location startPoint=new Location("locationA");
                        startPoint.setLatitude(latitude);
                        startPoint.setLongitude(longitude);

                        Location endPoint=new Location("locationB");
                        endPoint.setLatitude(Double.parseDouble(data.get(String.valueOf(i)).getLatitude()));
                        endPoint.setLongitude(Double.parseDouble(data.get(String.valueOf(i)).getLongitude()));
                        double distance = startPoint.distanceTo(endPoint);

                        if(distance <= 500)
                        {
                            list.add(data.get(String.valueOf(i)));
                        }
                    }
                }

                nextPage = response.body().getHalaman_selanjutnya();
                listView.setAdapter(new KulinerAdapter(DataSaringActivity.this, R.layout.kuliner_adapter, list));
                Toast.makeText(DataSaringActivity.this.getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(DataSaringActivity.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

    }


    public void loadmore() {
        CountShowData = (listView.getHeight() / 161) + 1;

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> a = api.loadMoreKuliner(String.valueOf(nextPage));
        a.enqueue(new Callback<KulinerModel>() {

            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();
                Integer beforePage = nextPage;

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage + response.body().getJumlah_data() - 1; i++) {
                    Bundle bundle = getIntent().getExtras();
                    Double longitude = bundle.getDouble("longitude");
                    Double latitude = bundle.getDouble("latitude");
                    if(latitude<Double.parseDouble(data.get(String.valueOf(i)).getLatitude()))
                    {
                        Location startPoint=new Location("locationA");
                        startPoint.setLatitude(Double.parseDouble(data.get(String.valueOf(i)).getLatitude()));
                        startPoint.setLongitude(Double.parseDouble(data.get(String.valueOf(i)).getLongitude()));

                        Location endPoint=new Location("locationB");
                        endPoint.setLatitude(latitude);
                        endPoint.setLongitude(longitude);
                        double distance = startPoint.distanceTo(endPoint);

                        if(distance <= 500)
                        {
                            list.add(data.get(String.valueOf(i)));
                        }
                    }
                    else
                    {
                        Location startPoint=new Location("locationA");
                        startPoint.setLatitude(latitude);
                        startPoint.setLongitude(longitude);

                        Location endPoint=new Location("locationB");
                        endPoint.setLatitude(Double.parseDouble(data.get(String.valueOf(i)).getLatitude()));
                        endPoint.setLongitude(Double.parseDouble(data.get(String.valueOf(i)).getLongitude()));
                        double distance = startPoint.distanceTo(endPoint);

                        if(distance <= 500)
                        {
                            list.add(data.get(String.valueOf(i)));
                        }
                    }
                }
                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("nextpage", String.valueOf(nextPage));
                listView.setAdapter(new KulinerAdapter(DataSaringActivity.this, R.layout.kuliner_adapter, list));
                Toast.makeText(DataSaringActivity.this.getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                listView.setSelection(beforePage - CountShowData);
                isFinised = true;

            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(DataSaringActivity.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });
    }
}
