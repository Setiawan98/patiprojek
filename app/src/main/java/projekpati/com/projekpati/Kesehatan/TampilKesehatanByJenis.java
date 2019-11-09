package projekpati.com.projekpati.Kesehatan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Kesehatan.KesehatanModel;
import projekpati.com.projekpati.Model.Kesehatan.ListKesehatan;
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

public class TampilKesehatanByJenis extends AppCompatActivity {
    ListView listView;
    Integer CountShowData;
    Integer nextPage=1;
    List<ListKesehatan> list = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    Boolean isFinised=true;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_kesehatan_by_jenis);

        toolbar = findViewById(R.id.kesehatanToolbar);
        listView = (ListView) findViewById(R.id.listKesehatan);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);

        final Bundle bundle = getIntent().getExtras();
        final String nama = bundle.getString("kategori");

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        title.setText(nama);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        getAllData();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(TampilKesehatanByJenis.this,DetilTukang.class);
//                intent.putExtra("id_kesehatan",list.get(position).getId());
//                startActivity(intent);
//            }
//        });

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
                            Toast.makeText(TampilKesehatanByJenis.this, "No More Data",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
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

    public void getAllData(){
        final Bundle bundle = getIntent().getExtras();
        final String kategori = bundle.getString("id_kesehatan");

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KesehatanModel> call = api.cariKesehatanByJenis(kategori);

        call.enqueue(new Callback<KesehatanModel>() {
            @Override
            public void onResponse(Call<KesehatanModel> call, final Response<KesehatanModel> response) {
                Map<String, ListKesehatan> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }
                nextPage = response.body().getHalaman_selanjutnya();
                listView.setAdapter(new KesehatanAdapter(TampilKesehatanByJenis.this, R.layout.kesehatan_adapter, list));
                Toast.makeText(TampilKesehatanByJenis.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<KesehatanModel> call, Throwable t) {
                Toast.makeText(TampilKesehatanByJenis.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

    }

    public void loadmore(){
        CountShowData = (listView.getHeight()/161)+1;
        final Bundle bundle = getIntent().getExtras();

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KesehatanModel> a = api.loadMoreKesehatan(String.valueOf(nextPage));
        a.enqueue(new Callback<KesehatanModel>() {

            @Override
            public void onResponse(Call<KesehatanModel> call, final Response<KesehatanModel> response) {
                Map<String, ListKesehatan> data = response.body().getData();
                Integer beforePage = nextPage;

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }
                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("nextpage",String.valueOf(nextPage));
                listView.setAdapter(new KesehatanAdapter(TampilKesehatanByJenis.this, R.layout.kesehatan_adapter, list));
                Toast.makeText(TampilKesehatanByJenis.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

                listView.setSelection(beforePage-CountShowData);
                isFinised=true;

            }

            @Override
            public void onFailure(Call<KesehatanModel> call, Throwable t) {
                Toast.makeText(TampilKesehatanByJenis.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
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
