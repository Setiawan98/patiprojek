package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuKuliner extends AppCompatActivity {

    ListView listView;
    List<ListKuliner> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kuliner);

        listView = (ListView) findViewById(R.id.listKuliner);
        getAllKuliner();
    }

    public void getAllKuliner(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KulinerModel> call = api.tampilSemuaKuliner();

        call.enqueue(new Callback<KulinerModel>() {
            @Override
            public void onResponse(Call<KulinerModel> call, Response<KulinerModel> response)  {


//                list = response.body()
                Log.d("onResponse", response.toString());
            //    listView.setAdapter(new KulinerAdapter(MenuKuliner.this, R.layout.kuliner_adapter, list));
                Toast.makeText(MenuKuliner.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<KulinerModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MenuKuliner.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

    }



}
