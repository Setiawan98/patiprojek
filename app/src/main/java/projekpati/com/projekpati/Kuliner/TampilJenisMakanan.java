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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TampilJenisMakanan extends AppCompatActivity {
    ListView listView;
    Integer nextPage=1;
    Integer beforePage;
    List<ListKuliner> list = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    Boolean isFinised=true;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_jenis_makanan);

        toolbar = findViewById(R.id.kulinerToolbar);
        listView = (ListView) findViewById(R.id.listKuliner);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);

        final Bundle bundle = getIntent().getExtras();
        final String nama = bundle.getString("makanan");


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        title.setText(nama);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        getAllData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TampilJenisMakanan.this,DetilKuliner.class);
                intent.putExtra("id_kuliner",list.get(position).getId());
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
                        if (nextPage==0){
                            Toast.makeText(TampilJenisMakanan.this, "No More Data",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            //loadMoreData();
                        }

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


            }
        });
    }

    public void getAllData(){
        final Bundle bundle = getIntent().getExtras();
        final String kategori = bundle.getString("id_kuliner");

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> call = api.cariKulinerJenisMakanan(kategori);

        call.enqueue(new Callback<KulinerModel>() {
            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }
                beforePage=nextPage;
                nextPage = response.body().getHalaman_selanjutnya();



                Integer np=1;
                Log.d("next: ",String.valueOf(nextPage));
                if(nextPage!=0)
                {
                    isFinised=false;
                    Log.d("Masuk: ","aaaa");

                    loadmore();


                }
                else{

                    listView.setAdapter(new KulinerAdapter(TampilJenisMakanan.this, R.layout.kuliner_adapter, list));
                    Toast.makeText(TampilJenisMakanan.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(TampilJenisMakanan.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

    }

    public void loadmore(){
        final Bundle bundle = getIntent().getExtras();
        final String kategori = bundle.getString("kategori");

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> a = api.loadMoreKuliner(String.valueOf(nextPage));
        a.enqueue(new Callback<KulinerModel>() {

            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();


                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }
                // beforePage=nextPage;
                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("nextpage",String.valueOf(nextPage));
                if(nextPage!=0)
                {
                    loadmore();
                }
                else{

                    listView.setAdapter(new KulinerAdapter(TampilJenisMakanan.this, R.layout.kuliner_adapter, list));
                    Toast.makeText(TampilJenisMakanan.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


                isFinised=true;

            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(TampilJenisMakanan.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(TampilJenisMakanan.this,MenuKuliner.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
