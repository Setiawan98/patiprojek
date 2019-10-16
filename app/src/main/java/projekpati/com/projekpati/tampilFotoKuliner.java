package projekpati.com.projekpati;

import androidx.appcompat.app.AppCompatActivity;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.DetilKulinerModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.MalformedURLException;
import java.net.URL;

public class tampilFotoKuliner extends AppCompatActivity {
    PhotoView mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_foto_kuliner);

        mImage = findViewById(R.id.mImage);

        final Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id_kuliner");



        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilKulinerModel> call = api.detailKuliner(id);
        call.enqueue(new Callback<DetilKulinerModel>() {
            @Override
            public void onResponse(Call<DetilKulinerModel> call, Response<DetilKulinerModel> response) {
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
                                .resize(800,500).noFade().into(mImage);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DetilKulinerModel> call, Throwable t) {
                Log.e("onFailureTampilFoto", t.getMessage().toString());
            }
        });

    }
}
