package projekpati.com.projekpati.BeritaOnline;

import androidx.appcompat.app.AppCompatActivity;

public class DataSaringBeritaOnlineActivity extends AppCompatActivity {
    /*ListView listView;
    Integer nextPage = 1;
    List<ListBeritaOnline> list = new ArrayList<>();
    List<ListBeritaOnline> listsaring = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_saring_berita_online);

        toolbar = findViewById(R.id.beritaOnlineToolbar);
        listView = (ListView) findViewById(R.id.listBeritaOnline);
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
                Intent intent = new Intent(DataSaringBeritaOnlineActivity.this, DetilBeritaOnline.class);
                intent.putExtra("id_beritaOnline", list.get(position).getId());
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
            Call<BeritaOnlineModel> call = api.tampilSemuaBeritaOnline();

            call.enqueue(new Callback<BeritaOnlineModel>() {
                @Override
                public void onResponse(Call<BeritaOnlineModel> call, final Response<BeritaOnlineModel> response) {
                    Map<String, ListBeritaOnline> data = response.body().getData();

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
                    listView.setAdapter(new BeritaOnlineAdapter(DataSaringBeritaOnlineActivity.this, R.layout.beritaOnline_adapter, list));
                    Toast.makeText(DataSaringBeritaOnlineActivity.this.getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    if(nextPage!=0)
                    {
                        loadmore();

                    }
                }

                @Override
                public void onFailure(Call<BeritaOnlineModel> call, Throwable t) {
                    Toast.makeText(DataSaringBeritaOnlineActivity.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", t.toString());
                }
            });
        }
        else
        {
            API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
            Call<BeritaOnlineModel> call = api.cariBeritaOnlinebyAPI(namasaring);
            call.enqueue(new Callback<BeritaOnlineModel>() {
                @Override
                public void onResponse(Call<BeritaOnlineModel> call, Response<BeritaOnlineModel> response) {
                    Map<String, ListBeritaOnline> data = response.body().getData();

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
                    listView.setAdapter(new BeritaOnlineAdapter(DataSaringBeritaOnlineActivity.this, R.layout.beritaOnline_adapter, list));
                    Toast.makeText(DataSaringBeritaOnlineActivity.this.getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    if(nextPage!=0)
                    {
                        loadmore();

                    }
                }

                @Override
                public void onFailure(Call<BeritaOnlineModel> call, Throwable t) {
                    Toast.makeText(DataSaringBeritaOnlineActivity.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", t.toString());
                }
            });
        }
    }


    public void loadmore() {
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<BeritaOnlineModel> a = api.loadMoreBeritaOnline(String.valueOf(nextPage));
        a.enqueue(new Callback<BeritaOnlineModel>() {

            @Override
            public void onResponse(Call<BeritaOnlineModel> call, final Response<BeritaOnlineModel> response) {
                Map<String, ListBeritaOnline> data = response.body().getData();
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
                listView.setAdapter(new BeritaOnlineAdapter(DataSaringBeritaOnlineActivity.this, R.layout.beritaOnline_adapter, list));
                Toast.makeText(DataSaringBeritaOnlineActivity.this.getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                if(nextPage!=0)
                {
                    loadmore();

                }
            }

            @Override
            public void onFailure(Call<BeritaOnlineModel> call, Throwable t) {
                Toast.makeText(DataSaringBeritaOnlineActivity.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
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
    }*/
}
