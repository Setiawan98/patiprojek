package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TampilKulinerByJenis extends AppCompatActivity {
    ListView listView;
    Integer CountShowData;
    Integer nextPage;
    List<ListKuliner> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_kuliner_by_jenis);

        listView = (ListView) findViewById(R.id.listKuliner);
        getAllKuliner();



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
                            Toast.makeText(TampilKulinerByJenis.this, "No More Data",Toast.LENGTH_SHORT).show();
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
    }

    public void getAllKuliner(){
        final Bundle bundle = getIntent().getExtras();
        final String kategori = bundle.getString("kategori");
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> call = api.tampilSemuaKuliner();

        call.enqueue(new Callback<KulinerModel>() {
            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= 20; i++)
                {
                    if(data.get(String.valueOf(i)).getTipe().equals(kategori))
                    {
                        list.add(data.get(String.valueOf(i)));
                    }

                }
                nextPage = response.body().getHalaman_selanjutnya();

                listView.setAdapter(new KulinerAdapter(TampilKulinerByJenis.this, R.layout.kuliner_adapter, list));
                Toast.makeText(TampilKulinerByJenis.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TampilKulinerByJenis.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TampilKulinerByJenis.this,DetilKuliner.class);
                intent.putExtra("id_kuliner",list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    public void loadMoreData(){
        //defining a progress dialog to show while signing up

        final Bundle bundle = getIntent().getExtras();
        final String kategori = bundle.getString("kategori");
        CountShowData = (listView.getHeight()/161)+1;
        Log.d("Height: ", String.valueOf(listView.getHeight()));
        Log.d("Height: ", String.valueOf(CountShowData));


        final ProgressDialog progressDialog = new ProgressDialog(this);
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
                    if(data.get(String.valueOf(i)).getTipe().equals(kategori))
                    {
                        list.add(data.get(String.valueOf(i)));
                    }
                    Log.d("value :", String.valueOf(i) );
                }


                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("next Page: ", String.valueOf(response.body().getHalaman_selanjutnya()));


                KulinerAdapter adapter = new KulinerAdapter(TampilKulinerByJenis.this, R.layout.kuliner_adapter, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(TampilKulinerByJenis.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                listView.setSelection(beforePage-CountShowData);


            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TampilKulinerByJenis.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TampilKulinerByJenis.this,DetilKuliner.class);
                intent.putExtra("id_kuliner",list.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
