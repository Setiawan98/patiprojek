package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.DetilKulinerModel;
import projekpati.com.projekpati.Model.KulinerModel;
import projekpati.com.projekpati.Model.ListKuliner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetilKuliner extends AppCompatActivity {

    TextView textNama, textAlamat, textTelepon, textJamBuka;
    ImageView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_kuliner);
        textNama = findViewById(R.id.mNama);
        textAlamat = findViewById(R.id.mAlamat);
        textJamBuka = findViewById(R.id.mJamBuka);
        textTelepon = findViewById(R.id.mTelpon);
        mImage = findViewById(R.id.mImage);

        final Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id_kuliner");

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilKulinerModel> call = api.detailKuliner(id);

        call.enqueue(new Callback<DetilKulinerModel>() {
            @Override
            public void onResponse(Call<DetilKulinerModel> call, Response<DetilKulinerModel> response) {


                    Log.w("ResponseAsu", new Gson().toJson(response.body()));
                    textNama.setText(response.body().getData().getNama());
                    textAlamat.setText(response.body().getData().getAlamat());
                    textJamBuka.setText(response.body().getData().getJam_buka());
                    textTelepon.setText(response.body().getData().getTelp());
                    URL url = null;
                    if(response.body().getData().getFile().equals(""))
                    {
                        //tidak terjadi perubahan apapun
                    }
                    else
                    {
                        try {
                            url = new URL(response.body().getData().getFile());
                            Picasso.with(getApplicationContext())
                                    .load(String.valueOf(url))
                                    .resize(300,200).noFade().into(mImage);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }

            }

            @Override
            public void onFailure(Call<DetilKulinerModel> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
            }
        });
    }
}
