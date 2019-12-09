package projekpati.com.projekpati.Aspirasi;

import androidx.appcompat.app.AppCompatActivity;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Aspirasi.AspirasiModel;
import projekpati.com.projekpati.Model.Aspirasi.ListAspirasi;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CariAspirasi extends AppCompatActivity {
    EditText textCari;
    ImageView buttonImage, imageBack;
    Integer CountShowData;
    Integer nextPage;
    List<ListAspirasi> list = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_aspirasi);
        textCari = findViewById(R.id.cari);
        buttonImage = findViewById(R.id.imageCari);
        imageBack = findViewById(R.id.imageBack);
        listView = findViewById(R.id.listAspirasi);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CariAspirasi.this,MenuAspirasi.class);
                startActivity(intent);
            }
        });

        textCari.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    hideSoftKeyboard(CariAspirasi.this);
                    return true;
                }
                return false;
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
                            Toast.makeText(CariAspirasi.this, "No More Data", Toast.LENGTH_SHORT).show();
                        } else {
                            loadMoreData();
                        }

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


            }
        });

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
                Call<AspirasiModel> call = api.cariAspirasibyAPI(textCari.getText().toString());
                call.enqueue(new Callback<AspirasiModel>() {
                    @Override
                    public void onResponse(Call<AspirasiModel> call, Response<AspirasiModel> response) {
                        Map<String, ListAspirasi> data = response.body().getData();
                        list.clear();
                        Log.w("ResponseCari", new Gson().toJson(response.body()));
                        for (int i = 1; i <= response.body().getJumlah_data(); i++) {
                            list.add(data.get(String.valueOf(i)));
                        }
                        nextPage = response.body().getHalaman_selanjutnya();
                        listView.setAdapter(new AspirasiAdapter(CariAspirasi.this, R.layout.aspirasi_adapter, list));
                        Toast.makeText(CariAspirasi.this.getApplicationContext(), "Ditemukan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AspirasiModel> call, Throwable t) {
                        Toast.makeText(CariAspirasi.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("onResponse", t.toString());
                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(CariAspirasi.this,DetilAspirasi.class);
                        intent.putExtra("id_aspirasi",list.get(position).getId());
                        startActivity(intent);
                    }
                });
            }
        });
        
    }

    public void performSearch()
    {
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<AspirasiModel> call = api.cariAspirasibyAPI(textCari.getText().toString());
        call.enqueue(new Callback<AspirasiModel>() {
            @Override
            public void onResponse(Call<AspirasiModel> call, Response<AspirasiModel> response) {
                Map<String, ListAspirasi> data = response.body().getData();
                list.clear();
                Log.w("ResponseCari", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++) {
                    list.add(data.get(String.valueOf(i)));
                }
                nextPage = response.body().getHalaman_selanjutnya();
                listView.setAdapter(new AspirasiAdapter(CariAspirasi.this, R.layout.aspirasi_adapter, list));
                Toast.makeText(CariAspirasi.this.getApplicationContext(), "Ditemukan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AspirasiModel> call, Throwable t) {
                Toast.makeText(CariAspirasi.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CariAspirasi.this,DetilAspirasi.class);
                intent.putExtra("id_aspirasi",list.get(position).getId());
                startActivity(intent);
            }
        });
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void loadMoreData(){
        //defining a progress dialog to show while signing up

        CountShowData = (listView.getHeight()/161)+1;
        Log.d("Height: ", String.valueOf(listView.getHeight()));
        Log.d("Height: ", String.valueOf(CountShowData));


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<AspirasiModel> call = api.loadMoreAspirasi(String.valueOf(nextPage));

        call.enqueue(new Callback<AspirasiModel>() {
            @Override
            public void onResponse(Call<AspirasiModel> call, final Response<AspirasiModel> response) {
                Map<String, ListAspirasi> data = response.body().getData();
                Integer beforePage = nextPage;

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                    Log.d("value :", String.valueOf(i) );
                }


                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("next Page: ", String.valueOf(response.body().getHalaman_selanjutnya()));


                AspirasiAdapter adapter = new AspirasiAdapter(CariAspirasi.this, R.layout.aspirasi_adapter, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(CariAspirasi.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                listView.setSelection(beforePage-CountShowData);


            }

            @Override
            public void onFailure(Call<AspirasiModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CariAspirasi.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CariAspirasi.this,DetilAspirasi.class);
                intent.putExtra("id_aspirasi",list.get(position).getId());
                startActivity(intent);
            }
        });
    }
}