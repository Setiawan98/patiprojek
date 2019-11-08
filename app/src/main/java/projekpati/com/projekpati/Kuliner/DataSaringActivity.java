package projekpati.com.projekpati.Kuliner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Kuliner.KulinerModel;
import projekpati.com.projekpati.Model.Kuliner.ListKuliner;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSaringActivity extends AppCompatActivity {
    ListView listView;
    Integer nextPage = 1;
    List<ListKuliner> list = new ArrayList<>();
    List<ListKuliner> listsaring = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_saring);

        toolbar = findViewById(R.id.kulinerToolbar);
        listView = (ListView) findViewById(R.id.listKuliner);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);
        Bundle bundle = getIntent().getExtras();
        Integer judul = bundle.getInt("SaringTitle");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        if(judul==3)
        {
            title.setText("Saring 3 Km");
        }
        else if(judul==5)
        {
            title.setText("Saring 5 Km");
        }
        else if(judul==8)
        {
            title.setText("Saring 8 Km");
        }
        else if(judul==10)
        {
            title.setText("Saring 10 Km");
        }
        else
        {
            title.setText("Saring");
        }

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

    }


    public void getAllData() {
        final Bundle bundle = getIntent().getExtras();

        String namasaring = bundle.getString("namasaring");

        if(namasaring.equals(""))
        {
            API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
            Call<KulinerModel> call = api.tampilSemuaKuliner();

            call.enqueue(new Callback<KulinerModel>() {
                @Override
                public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                    Map<String, ListKuliner> data = response.body().getData();

                    Log.w("Response", new Gson().toJson(response.body()));
                    for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1 ; i++) {
                        Bundle bundle = getIntent().getExtras();
                        Double longitude = bundle.getDouble("longitude");
                        Double latitude = bundle.getDouble("latitude");
                        Double radius = bundle.getDouble("radius");

                        if(latitude<Double.parseDouble(data.get(String.valueOf(i)).getLatitude()))
                        {
                            Location startPoint=new Location("locationA");
                            startPoint.setLatitude(Double.parseDouble(data.get(String.valueOf(i)).getLatitude()));
                            startPoint.setLongitude(Double.parseDouble(data.get(String.valueOf(i)).getLongitude()));

                            Location endPoint=new Location("locationB");
                            endPoint.setLatitude(latitude);
                            endPoint.setLongitude(longitude);
                            double distance = startPoint.distanceTo(endPoint);

                            if(distance <= radius)
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

                            if(distance <= radius)
                            {
                                list.add(data.get(String.valueOf(i)));
                            }
                        }
                    }

                    nextPage = response.body().getHalaman_selanjutnya();
                    listView.setAdapter(new KulinerAdapter(DataSaringActivity.this, R.layout.kuliner_adapter, list));
                    Toast.makeText(DataSaringActivity.this.getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    if(nextPage!=0)
                    {
                        loadmore();

                    }
                }

                @Override
                public void onFailure(Call<KulinerModel> call, Throwable t) {
                    Toast.makeText(DataSaringActivity.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", t.toString());
                }
            });
        }
        else
        {
            API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
            Call<KulinerModel> call = api.cariKulinerbyAPI(namasaring);
            call.enqueue(new Callback<KulinerModel>() {
                @Override
                public void onResponse(Call<KulinerModel> call, Response<KulinerModel> response) {
                    Map<String, ListKuliner> data = response.body().getData();

                    Log.w("Response", new Gson().toJson(response.body()));
                    for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1 ; i++) {
                        Bundle bundle = getIntent().getExtras();
                        Double longitude = bundle.getDouble("longitude");
                        Double latitude = bundle.getDouble("latitude");
                        Double radius = bundle.getDouble("radius");

                        if(latitude<Double.parseDouble(data.get(String.valueOf(i)).getLatitude()))
                        {
                            Location startPoint=new Location("locationA");
                            startPoint.setLatitude(Double.parseDouble(data.get(String.valueOf(i)).getLatitude()));
                            startPoint.setLongitude(Double.parseDouble(data.get(String.valueOf(i)).getLongitude()));

                            Location endPoint=new Location("locationB");
                            endPoint.setLatitude(latitude);
                            endPoint.setLongitude(longitude);
                            double distance = startPoint.distanceTo(endPoint);

                            if(distance <= radius)
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

                            if(distance <= radius)
                            {
                                list.add(data.get(String.valueOf(i)));
                            }
                        }
                    }

                    nextPage = response.body().getHalaman_selanjutnya();
                    listView.setAdapter(new KulinerAdapter(DataSaringActivity.this, R.layout.kuliner_adapter, list));
                    Toast.makeText(DataSaringActivity.this.getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    if(nextPage!=0)
                    {
                        loadmore();

                    }
                }

                @Override
                public void onFailure(Call<KulinerModel> call, Throwable t) {
                    Toast.makeText(DataSaringActivity.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", t.toString());
                }
            });
        }
    }


    public void loadmore() {
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
                    Double radius = bundle.getDouble("radius");
                    if(latitude<Double.parseDouble(data.get(String.valueOf(i)).getLatitude()))
                    {
                        Location startPoint=new Location("locationA");
                        startPoint.setLatitude(Double.parseDouble(data.get(String.valueOf(i)).getLatitude()));
                        startPoint.setLongitude(Double.parseDouble(data.get(String.valueOf(i)).getLongitude()));

                        Location endPoint=new Location("locationB");
                        endPoint.setLatitude(latitude);
                        endPoint.setLongitude(longitude);
                        double distance = startPoint.distanceTo(endPoint);

                        if(distance <= radius)
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

                        if(distance <= radius)
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
                if(nextPage!=0)
                {
                    loadmore();

                }
            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(DataSaringActivity.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_toolbar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
