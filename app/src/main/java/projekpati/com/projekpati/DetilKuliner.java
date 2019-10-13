package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
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

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<ListKuliner> call = api.detailKuliner("2");

        call.enqueue(new Callback<ListKuliner>() {
            @Override
            public void onResponse(Call<ListKuliner> call, Response<ListKuliner> response) {
                if(response.isSuccessful())
                {

                    Log.w("ResponseAsu", new Gson().toJson(response.body().getId()));
                    textNama.setText(response.body().getNama());
                    textAlamat.setText(response.body().getAlamat());
                    textJamBuka.setText(response.body().getJam_buka());
                    textTelepon.setText(response.body().getTelp());
//                    URL url = null;
//                    if(response.body().getFile().equals(""))
//                    {
//                        //tidak terjadi perubahan apapun
//                    }
//                    else
//                    {
//                        try {
//                            url = new URL(response.body().getFile());
//                            Picasso.with(getApplicationContext())
//                                    .load(String.valueOf(url))
//                                    .resize(300,200).noFade().into(mImage);
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
            }

            @Override
            public void onFailure(Call<ListKuliner> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
            }
        });
    }
}
