package projekpati.com.projekpati.BeritaOnline;

import androidx.appcompat.app.AppCompatActivity;

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

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.BeritaOnline.BeritaOnlineModel;
import projekpati.com.projekpati.Model.BeritaOnline.ListBeritaOnline;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CariBeritaOnline extends AppCompatActivity {
    EditText textCari;
    ImageView buttonImage, imageBack;
    Integer CountShowData;
    Integer nextPage;
    List<ListBeritaOnline> list = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_berita_online);

        textCari = findViewById(R.id.cari);
        buttonImage = findViewById(R.id.imageCari);
        imageBack = findViewById(R.id.imageBack);
        listView = findViewById(R.id.listBeritaOnline);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CariBeritaOnline.this, MenuBeritaOnline.class);
                startActivity(intent);
            }
        });

        textCari.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    hideSoftKeyboard(CariBeritaOnline.this);
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
                            Toast.makeText(CariBeritaOnline.this, "No More Data", Toast.LENGTH_SHORT).show();
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
                Call<BeritaOnlineModel> call = api.cariBeritaOnlinebyAPI(textCari.getText().toString());
                call.enqueue(new Callback<BeritaOnlineModel>() {
                    @Override
                    public void onResponse(Call<BeritaOnlineModel> call, Response<BeritaOnlineModel> response) {
                        Map<String, ListBeritaOnline> data = response.body().getData();
                        list.clear();
                        Log.w("ResponseCari", new Gson().toJson(response.body()));
                        for (int i = 1; i <= response.body().getJumlah_data(); i++) {
                            list.add(data.get(String.valueOf(i)));
                        }
                        nextPage = response.body().getHalaman_selanjutnya();
                        listView.setAdapter(new BeritaOnlineAdapter(CariBeritaOnline.this, R.layout.berita_online_adapter, list));
                        Toast.makeText(CariBeritaOnline.this.getApplicationContext(), "Ditemukan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<BeritaOnlineModel> call, Throwable t) {
                        Toast.makeText(CariBeritaOnline.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("onResponse", t.toString());
                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(CariBeritaOnline.this, DetilBeritaOnline.class);
                        intent.putExtra("id_beritaOnline",list.get(position).getId());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void performSearch()
    {
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<BeritaOnlineModel> call = api.cariBeritaOnlinebyAPI(textCari.getText().toString());
        call.enqueue(new Callback<BeritaOnlineModel>() {
            @Override
            public void onResponse(Call<BeritaOnlineModel> call, Response<BeritaOnlineModel> response) {
                Map<String, ListBeritaOnline> data = response.body().getData();
                list.clear();
                Log.w("ResponseCari", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++) {
                    list.add(data.get(String.valueOf(i)));
                }
                nextPage = response.body().getHalaman_selanjutnya();
                listView.setAdapter(new BeritaOnlineAdapter(CariBeritaOnline.this, R.layout.berita_online_adapter, list));
                Toast.makeText(CariBeritaOnline.this.getApplicationContext(), "Ditemukan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BeritaOnlineModel> call, Throwable t) {
                Toast.makeText(CariBeritaOnline.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CariBeritaOnline.this,DetilBeritaOnline.class);
                intent.putExtra("id_beritaOnline",list.get(position).getId());
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
        Call<BeritaOnlineModel> call = api.loadMoreBeritaOnline(String.valueOf(nextPage));

        call.enqueue(new Callback<BeritaOnlineModel>() {
            @Override
            public void onResponse(Call<BeritaOnlineModel> call, final Response<BeritaOnlineModel> response) {
                Map<String, ListBeritaOnline> data = response.body().getData();
                Integer beforePage = nextPage;

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                    Log.d("value :", String.valueOf(i) );
                }


                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("next Page: ", String.valueOf(response.body().getHalaman_selanjutnya()));


                BeritaOnlineAdapter adapter = new BeritaOnlineAdapter(CariBeritaOnline.this, R.layout.berita_online_adapter, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(CariBeritaOnline.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                listView.setSelection(beforePage-CountShowData);


            }

            @Override
            public void onFailure(Call<BeritaOnlineModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CariBeritaOnline.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CariBeritaOnline.this,DetilBeritaOnline.class);
                intent.putExtra("id_beritaOnline",list.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
