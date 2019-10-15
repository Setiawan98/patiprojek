package projekpati.com.projekpati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    List<ListKuliner> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kuliner);

        Toolbar toolbar = (Toolbar) findViewById(R.id.kulinerToolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().
       // getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView bottomNavigationView = findViewById(R.id.menuKuliner);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id==R.id.beranda){
                    Toast.makeText(MenuKuliner.this, "Beranda Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.kategori){
                    Toast.makeText(MenuKuliner.this, "Kategori Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.tambah){
                    Toast.makeText(MenuKuliner.this, "Tambah Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.saring){
                    Toast.makeText(MenuKuliner.this, "Saring Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.dataku){
                    Toast.makeText(MenuKuliner.this, "Dataku Clicked",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

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
            public void onResponse(Call<KulinerModel> call, Response<KulinerModel> response) {


                //KulinerModel km=response.body();
                Map<String, ListKuliner> data = response.body().getData();

                //Log.d("onResponse", response.body().getData());
                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= 20; i++)
                {
                    //int a=i+1;
                    list.add(data.get(String.valueOf(i)));
                    Log.d("value",data.get(String.valueOf(i)).getNama());
                }



                listView.setAdapter(new KulinerAdapter(MenuKuliner.this, R.layout.kuliner_adapter, list));
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MenuKuliner.this,DetilKuliner.class);
                intent.putExtra("id_kuliner",list.get(position).getId());
                startActivity(intent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.btnSearch)
        {
            Toast.makeText(MenuKuliner.this, "Search Clicked",Toast.LENGTH_SHORT).show();
        }
        else if(id==android.R.id.home)
        {
            Intent i = new Intent(MenuKuliner.this,MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
