package projekpati.com.projekpati.bioskop;

import androidx.appcompat.app.AppCompatActivity;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Bioskop.BioskopModel;
import projekpati.com.projekpati.Model.Bioskop.ListBioskop;
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

public class CariBioskop extends AppCompatActivity {
    EditText textCari;
    ImageView buttonImage, imageBack;
    Integer CountShowData;
    Integer nextPage;
    List<ListBioskop> list = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_bioskop);

        textCari = findViewById(R.id.cari);
        buttonImage = findViewById(R.id.imageCari);
        imageBack = findViewById(R.id.imageBack);
        listView = findViewById(R.id.listBioskop);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CariBioskop.this,MenuBioskop.class);
                startActivity(intent);
            }
        });

        textCari.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    hideSoftKeyboard(CariBioskop.this);
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
                            Toast.makeText(CariBioskop.this, "No More Data", Toast.LENGTH_SHORT).show();
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
                Call<BioskopModel> call = api.cariBioskopbyAPI(textCari.getText().toString());
                call.enqueue(new Callback<BioskopModel>() {
                    @Override
                    public void onResponse(Call<BioskopModel> call, Response<BioskopModel> response) {
                        Map<String, ListBioskop> data = response.body().getData();
                        list.clear();
                        Log.w("ResponseCari", new Gson().toJson(response.body()));
                        for (int i = 1; i <= response.body().getJumlah_data(); i++) {
                            list.add(data.get(String.valueOf(i)));
                        }
                        nextPage = response.body().getHalaman_selanjutnya();
                        listView.setAdapter(new BioskopAdapter(CariBioskop.this, R.layout.bioskop_adapter, list));
                        Toast.makeText(CariBioskop.this.getApplicationContext(), "Ditemukan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<BioskopModel> call, Throwable t) {
                        Toast.makeText(CariBioskop.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("onResponse", t.toString());
                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(CariBioskop.this,DetilBioskop.class);
                        intent.putExtra("id_bioskop",list.get(position).getId());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void performSearch()
    {
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<BioskopModel> call = api.cariBioskopbyAPI(textCari.getText().toString());
        call.enqueue(new Callback<BioskopModel>() {
            @Override
            public void onResponse(Call<BioskopModel> call, Response<BioskopModel> response) {
                Map<String, ListBioskop> data = response.body().getData();
                list.clear();
                Log.w("ResponseCari", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++) {
                    list.add(data.get(String.valueOf(i)));
                }
                nextPage = response.body().getHalaman_selanjutnya();
                listView.setAdapter(new BioskopAdapter(CariBioskop.this, R.layout.bioskop_adapter, list));
                Toast.makeText(CariBioskop.this.getApplicationContext(), "Ditemukan", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BioskopModel> call, Throwable t) {
                Toast.makeText(CariBioskop.this.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CariBioskop.this,DetilBioskop.class);
                intent.putExtra("id_bioskop",list.get(position).getId());
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
        Call<BioskopModel> call = api.loadMoreBioskop(String.valueOf(nextPage));

        call.enqueue(new Callback<BioskopModel>() {
            @Override
            public void onResponse(Call<BioskopModel> call, final Response<BioskopModel> response) {
                Map<String, ListBioskop> data = response.body().getData();
                Integer beforePage = nextPage;

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = nextPage; i <= nextPage+response.body().getJumlah_data()-1; i++)
                {
                    list.add(data.get(String.valueOf(i)));
                    Log.d("value :", String.valueOf(i) );
                }


                nextPage = response.body().getHalaman_selanjutnya();
                Log.d("next Page: ", String.valueOf(response.body().getHalaman_selanjutnya()));


                BioskopAdapter adapter = new BioskopAdapter(CariBioskop.this, R.layout.bioskop_adapter, list);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(CariBioskop.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                listView.setSelection(beforePage-CountShowData);


            }

            @Override
            public void onFailure(Call<BioskopModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CariBioskop.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CariBioskop.this,DetilBioskop.class);
                intent.putExtra("id_bioskop",list.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
