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
import android.os.Handler;
import android.os.SystemClock;
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
    Integer nextPage=1;
    Integer beforePage;
    List<ListKuliner> list = new ArrayList<>();
    Boolean isFinised=true;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_kuliner_by_jenis);

        listView = (ListView) findViewById(R.id.listKuliner);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        getAllData();


    }


    public void getAllData(){
        final Bundle bundle = getIntent().getExtras();
        final String kategori = bundle.getString("kategori");

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> call = api.tampilSemuaKuliner();

        call.enqueue(new Callback<KulinerModel>() {
            @Override
            public void onResponse(Call<KulinerModel> call, final Response<KulinerModel> response) {
                Map<String, ListKuliner> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    if(data.get(String.valueOf(i)).getTipe().equals(kategori))
                    {
                        list.add(data.get(String.valueOf(i)));
                    }
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

                   // SystemClock.sleep(2000);

                }
                else{

                    listView.setAdapter(new KulinerAdapter(TampilKulinerByJenis.this, R.layout.kuliner_adapter, list));
                    Toast.makeText(TampilKulinerByJenis.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
//                if(nextPage!=0 && beforePage!=nextPage)
//                {
//                    getAllData();
//                    beforePage = nextPage;
//                }

            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(TampilKulinerByJenis.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
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
                    if(data.get(String.valueOf(i)).getTipe().equals(kategori))
                    {
                        list.add(data.get(String.valueOf(i)));
                    }
                }
                // beforePage=nextPage;
                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("nextpage",String.valueOf(nextPage));
                if(nextPage!=0)
                {
                    loadmore();
                }
                else{

                    listView.setAdapter(new KulinerAdapter(TampilKulinerByJenis.this, R.layout.kuliner_adapter, list));
                    Toast.makeText(TampilKulinerByJenis.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


                isFinised=true;

            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                Toast.makeText(TampilKulinerByJenis.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });


    }


}
