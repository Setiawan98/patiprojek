package projekpati.com.projekpati.Kuliner;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import projekpati.com.projekpati.API.API;
        import projekpati.com.projekpati.API.RetrofitClientInstance;
        import projekpati.com.projekpati.Model.DetilKulinerModel;
        import projekpati.com.projekpati.R;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.google.gson.Gson;
        import com.squareup.picasso.Picasso;

        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.Locale;

public class DetilKuliner extends AppCompatActivity {

    TextView textNama, textAlamat, textTelepon, textDeskripsi, textEmail, textWebsite, textPemilik;
    float lat;
    float longt;
    ImageView mImage, btnMap;
    Toolbar toolbar;
    TextView title;
    LinearLayout ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_kuliner);

        toolbar = (Toolbar) findViewById(R.id.kulinerToolbar);
        ly = findViewById(R.id.konten);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);
        textNama = findViewById(R.id.mNama);
        textAlamat = findViewById(R.id.mAlamat);
        textTelepon = findViewById(R.id.mTelpon);
        textDeskripsi = findViewById(R.id.mDeskripsi);
        textEmail = findViewById(R.id.mEmail);
        textWebsite = findViewById(R.id.mWebsite);
        textPemilik = findViewById(R.id.mPemilik);

        mImage = findViewById(R.id.mImage);
        btnMap = findViewById(R.id.btnMap);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        title.setText("Detil Kuliner");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


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
                textDeskripsi.setText(response.body().getData().getDeskripsi());
                textEmail.setText(response.body().getData().getEmail());
                textPemilik.setText(response.body().getData().getPemilik());
                textWebsite.setText(response.body().getData().getWebsite());
                textTelepon.setText(response.body().getData().getTelp());
                lat = Float.parseFloat(response.body().getData().getLatitude());
                longt = Float.parseFloat(response.body().getData().getLongitude());

                URL url = null;
                if(response.body().getData().getFile().equals(""))
                {
                    //tidak terjadi perubahan apapun
                }
                else
                {
                    try {
                       // LinearLayout ly = findViewById(R.id.konten);
                        Integer width= ly.getWidth();
                        Integer height =  width*65/100;
                        Log.d("layout width",String.valueOf(width));
                        Log.d("layout height",String.valueOf(height));
                        url = new URL(response.body().getData().getFile());
                        Picasso.with(getApplicationContext())
                                .load(String.valueOf(url))
                                .resize(width,height).noFade().into(mImage);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                btnMap.bringToFront();

            }

            @Override
            public void onFailure(Call<DetilKulinerModel> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
            }
        });


        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH,"http://maps.google.com/maps?daddr=%f,%f (%s)",lat,longt,"Your Search");
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetilKuliner.this,tampilFotoKuliner.class);
                intent.putExtra("id_kuliner",bundle.getString("id_kuliner"));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_toolbar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home)
        {
            Intent i = new Intent(DetilKuliner.this,MenuKuliner.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
