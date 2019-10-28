package projekpati.com.projekpati.Kuliner;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import projekpati.com.projekpati.API.API;
        import projekpati.com.projekpati.API.RetrofitClientInstance;
        import projekpati.com.projekpati.Model.DetilKulinerModel;
        import projekpati.com.projekpati.Model.KomentarLengkap;
        import projekpati.com.projekpati.Model.KomentarParent;
        import projekpati.com.projekpati.Model.ListKuliner;
        import projekpati.com.projekpati.R;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.model.CameraPosition;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.gson.Gson;
        import com.squareup.picasso.Picasso;

        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Locale;
        import java.util.Map;

public class DetilKuliner extends AppCompatActivity {

    TextView textNama, textAlamat, textTelepon, textDeskripsi, textEmail, textWebsite, textPemilik;
    TextView senin, selasa, rabu, kamis, jumat, sabtu, minggu;
    Button btnDetil, btnJam;
    float lat;
    float longt;
    LinearLayout listKuliner;
    ImageView mImage, btnMap;
    LinearLayout linearDetil, linearJam;
    List<KomentarParent> list = new ArrayList<>();
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
        linearDetil = findViewById(R.id.linearDetil);
        linearJam = findViewById(R.id.linearJam);
        senin = findViewById(R.id.mSenin);
        selasa = findViewById(R.id.mSelasa);
        rabu = findViewById(R.id.mRabu);
        kamis = findViewById(R.id.mKamis);
        jumat = findViewById(R.id.mJumat);
        sabtu = findViewById(R.id.mSabtu);
        minggu = findViewById(R.id.mMinggu);
        btnDetil = findViewById(R.id.buttonDetil);
        btnJam = findViewById(R.id.buttonJam);
        textNama = findViewById(R.id.mNama);
        textAlamat = findViewById(R.id.mAlamat);
        textTelepon = findViewById(R.id.mTelpon);
        textDeskripsi = findViewById(R.id.mDeskripsi);
        textEmail = findViewById(R.id.mEmail);
        textWebsite = findViewById(R.id.mWebsite);
        textPemilik = findViewById(R.id.mPemilik);
        listKuliner = findViewById(R.id.listKuliner);
        mImage = findViewById(R.id.mImage);
        btnMap = findViewById(R.id.btnMap);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        title.setText("Detil Kuliner");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        linearDetil.setVisibility(View.VISIBLE);
        linearJam.setVisibility(View.INVISIBLE);

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
                senin.setText(response.body().getData().getHari_1());
                selasa.setText(response.body().getData().getHari_2());
                rabu.setText(response.body().getData().getHari_3());
                kamis.setText(response.body().getData().getHari_4());
                jumat.setText(response.body().getData().getHari_5());
                sabtu.setText(response.body().getData().getHari_6());
                minggu.setText(response.body().getData().getHari_0());
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

        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KomentarLengkap> call2 = api2.getKomentar(id);
        call2.enqueue(new Callback<KomentarLengkap>() {
            @Override
            public void onResponse(Call<KomentarLengkap> call, Response<KomentarLengkap> response) {
                Map<String, KomentarParent> data = response.body().getKomentar_parent();

                Log.w("Komentar", new Gson().toJson(response.body()));
                boolean hasNext = true;
                int i=2;
                while (hasNext == true)
                {
                    list.add(data.get(String.valueOf(i)));
                    if(data.get(String.valueOf(i+1)) == null)
                    {
                        hasNext = false;
                    }
                    i++;
                }
                listKomentar();

                //listKuliner.setAdapter(new KomentarAdapter(DetilKuliner.this, R.layout.komentar_adapter, list));
                Toast.makeText(DetilKuliner.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<KomentarLengkap> call, Throwable t) {

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

        btnJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearDetil.setVisibility(View.INVISIBLE);
                linearJam.setVisibility(View.VISIBLE);
            }
        });

        btnDetil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearJam.setVisibility(View.INVISIBLE);
                linearDetil.setVisibility(View.VISIBLE);
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
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void listKomentar()
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        int size = list.size();
        Log.d("size",String.valueOf(size));

        for(int i =0;i<size;i++)
        {
            final KomentarParent kp = list.get(i);
            Log.d("nama",kp.getKomentar_nama());

            if(kp!=null)
            {
                RelativeLayout adapter = (RelativeLayout) inflater.inflate(R.layout.komentar_adapter,null);
                TextView txtNama = (TextView) adapter.findViewById(R.id.mNama);
                TextView txtRating = (TextView) adapter.findViewById(R.id.mRating);
                TextView txtKomentar = (TextView) adapter.findViewById(R.id.mKomentar);
                URL url = null;

                txtNama.setText(kp.getKomentar_nama());
                txtRating.setText(kp.getKomentar_rating());
                txtKomentar.setText(kp.getKomentar_isi());


                listKuliner.addView(adapter);

            }
        }

    }
}
