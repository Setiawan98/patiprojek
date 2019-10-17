package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CariKuliner extends AppCompatActivity {

    EditText textCari;
    ImageView buttonImage;
    List<ListKuliner> list = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_kuliner);

        textCari = findViewById(R.id.cari);
        buttonImage = findViewById(R.id.imageCari);
        listView = findViewById(R.id.listKuliner);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
                Call<KulinerModel> call = api.cariKulinerbyAPI(textCari.getText().toString());
                call.enqueue(new Callback<KulinerModel>() {
                    @Override
                    public void onResponse(Call<KulinerModel> call, Response<KulinerModel> response) {
                        Map<String, ListKuliner> data = response.body().getData();
                        list.clear();
                        Log.w("ResponseCari", new Gson().toJson(response.body()));
                        for (int i = 1; i <= 20; i++) {
                            if (data.get(String.valueOf(i)) == null) {
                                break;
                            } else {
                                list.add(data.get(String.valueOf(i)));
                            }

                            Log.d("value", data.get(String.valueOf(i)).getNama());
                        }
                        listView.setAdapter(new KulinerAdapter(CariKuliner.this, R.layout.kuliner_adapter, list));
                        Toast.makeText(CariKuliner.this.getApplicationContext(),"Ditemukan", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<KulinerModel> call, Throwable t) {
                        Toast.makeText(CariKuliner.this.getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("onResponse", t.toString());
                    }
                });
            }
        });
    }
}
