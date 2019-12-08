package projekpati.com.projekpati.BeritaCetak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Kuliner.ViewPagerAdapter;
import projekpati.com.projekpati.Model.BeritaCetak.DetilBeritaCetakModel;
import projekpati.com.projekpati.Model.BeritaCetak.GambarBeritaCetakDetil;
import projekpati.com.projekpati.Model.KomentarLengkap;
import projekpati.com.projekpati.Model.KomentarParent;
import projekpati.com.projekpati.Model.postKomentar;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DetilBeritaCetak extends AppCompatActivity {
    TextView textJudul, textDate, textDeskripsi, ratingsum, ratingpeople;
    SwipeRefreshLayout swipeRefreshLayout;
    FrameLayout frameLayout;
    TextView title;
    Toolbar toolbar;
    List<KomentarParent> list = new ArrayList<>();
    List<GambarBeritaCetakDetil> gambarList = new ArrayList<>();
    Map<String,List<KomentarParent>> responseChild;
    float lat;
    float longt;
    String parentID;
    LinearLayout listPendidikan;
    TabLayout tabLayout;
    TextView btnKomen;
    RatingBar ratingstar;
    EditText komentar;
    LinearLayout pbKomen;
    String id;
    ViewPager pager;
    ImageView btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_berita_cetak);

        toolbar = (Toolbar) findViewById(R.id.beritaCetakToolbar);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);


        ratingpeople = findViewById(R.id.ratingpeople);
        ratingsum = findViewById(R.id.ratingsum);
        textJudul = findViewById(R.id.mNama);
        textDate = findViewById(R.id.mTime);
        textDeskripsi = findViewById(R.id.mDeskripsi);
        tabLayout = findViewById(R.id.tabDots);
        btnEdit = findViewById(R.id.btnEdit);

        listPendidikan = findViewById(R.id.listPendidikan);
        komentar = findViewById(R.id.komentar);
        btnKomen = findViewById(R.id.btnKomen);
        pager = findViewById(R.id.view_pager);
        ratingstar = findViewById(R.id.ratingstar);
        pbKomen = findViewById(R.id.progress_bar);
        ratingstar.setMax(5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        title.setText("Detil Pendidikan");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id_berita_cetak");
        Log.d("idwoy",id);

        getDataDetail();

        getKomentar();

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                finish();
                overridePendingTransition(0,0);
                startActivity(getIntent());
                overridePendingTransition(0,0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });

        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                swipeRefreshLayout.setEnabled(false);
                switch (event.getAction()){

                    case MotionEvent.ACTION_UP:
                        swipeRefreshLayout.setEnabled(true);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        swipeRefreshLayout.setEnabled(true);
                        break;

                }
                return false;
            }
        });

        btnKomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle bundle = getIntent().getExtras();
                final String id = bundle.getString("id_berita_cetak");
                final float hasil = ratingstar.getRating();
                final String isi,waktu=null, nama=null,email=null,telp=null,website=null,userid=null;
                isi = komentar.getText().toString();

                API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
                Call<postKomentar> call = api.addKomentar( id, "pendidikan",nama, email, telp, website, isi, String.valueOf(hasil), userid);
                call.enqueue(new Callback<postKomentar>() {
                    @Override
                    public void onResponse(Call<postKomentar> call, Response<postKomentar> response) {
                        //Toast.makeText(DetilKuliner.this, "Sukses Berkomentar", Toast.LENGTH_SHORT).show();
                        parentID= response.body().getDataid();
                        addViewKomentar(id,  nama, waktu,  telp, email,  website,isi,parentID, hasil);
                        pbKomen.setVisibility(View.VISIBLE);
                        btnKomen.setVisibility(View.GONE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pbKomen.setVisibility(View.GONE);
                                btnKomen.setVisibility(View.VISIBLE);
                            }
                        },2000);

                    }

                    @Override
                    public void onFailure(Call<postKomentar> call, Throwable t) {
                        Log.d("error", t.toString());
                    }
                });

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
        final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        int size = list.size();
        Log.d("size",String.valueOf(size));
        int index=size-1;

        for(int i =size-1;i>=0;i--)
        {
            final KomentarParent kp = list.get(i);
            Log.d("nama",kp.getKomentar_nama());

            if(kp!=null)
            {
                final RelativeLayout adapter = (RelativeLayout) inflater.inflate(R.layout.komentar_adapter,null);
                TextView txtNama = (TextView) adapter.findViewById(R.id.mNama);
                TextView txtKomentar = (TextView) adapter.findViewById(R.id.mKomentar);
                TextView txtWaktu = adapter.findViewById(R.id.mWaktu);
                RatingBar ratestar = adapter.findViewById(R.id.ratingstar);
                TextView btnBalas = adapter.findViewById(R.id.btnBalas);
                Button btnBatal = adapter.findViewById(R.id.btnBatal);
                Button btnKirim = adapter.findViewById(R.id.btnKirim);
                final LinearLayout layoutChild = adapter.findViewById(R.id.listChild);
                final EditText eKomenBalas = adapter.findViewById(R.id.eKomentar);
                final RelativeLayout layoutBalas = adapter.findViewById(R.id.layoutBalas);

                ratestar.setMax(5);
                txtWaktu.setText(kp.getKomentar_waktu());
                txtNama.setText(kp.getKomentar_nama());
                txtKomentar.setText(kp.getKomentar_isi());
                Float ratingbos = Float.parseFloat(kp.getKomentar_rating());
                if(ratingbos == 1)
                {
                    ratestar.setRating(1);
                }
                else if(ratingbos == 2)
                {
                    ratestar.setRating(2);
                }
                else if(ratingbos == 3)
                {
                    ratestar.setRating(3);
                }
                else if(ratingbos == 4)
                {
                    ratestar.setRating(4);
                }
                else if(ratingbos == 5)
                {
                    ratestar.setRating(5);
                }

                btnBalas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layoutBalas.setVisibility(View.VISIBLE);
                    }
                });
                btnBatal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layoutBalas.setVisibility(View.GONE);
                    }
                });

                btnKirim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressBar pg = adapter.findViewById(R.id.progress_bar);
                        pg.setVisibility(View.VISIBLE);
                        addBalas(kp.getData_id(), kp.getKomentar_nama(), kp.getKomentar_email(), kp.getKomentar_telp(),kp.getKomentar_website(), eKomenBalas.getText().toString(), kp.getKomentar_id(), null);
                        final RelativeLayout adapterChild = (RelativeLayout) inflater.inflate(R.layout.komentarchild_adapter,null);
                        TextView txtChildNama = (TextView) adapterChild.findViewById(R.id.mNama);
                        TextView textChildKomentar = (TextView) adapterChild.findViewById(R.id.mKomentar);
                        TextView textChildWaktu = adapterChild.findViewById(R.id.mWaktu);
                        txtChildNama.setText(kp.getKomentar_nama());
                        textChildWaktu.setText(kp.getKomentar_waktu());
                        textChildKomentar.setText(eKomenBalas.getText().toString());
                        layoutBalas.setBackgroundColor(getResources().getColor(R.color.progress));

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                layoutChild.addView(adapterChild);
                                layoutChild.invalidate();
                                layoutBalas.setVisibility(View.GONE);
                                layoutBalas.setBackgroundColor(getResources().getColor(R.color.progress_after));
                                pg.setVisibility(View.GONE);
                            }
                        },2000);

                    }
                });

                listPendidikan.addView(adapter);


                if(responseChild.get(kp.getKomentar_id())!=null)
                {
                    List<KomentarParent> listChildKomentar = responseChild.get(kp.getKomentar_id());

                    for(KomentarParent kc : listChildKomentar)
                    {
                        RelativeLayout adapterChild = (RelativeLayout) inflater.inflate(R.layout.komentarchild_adapter,null);
                        TextView txtChildNama = (TextView) adapterChild.findViewById(R.id.mNama);
                        TextView textChildKomentar = (TextView) adapterChild.findViewById(R.id.mKomentar);
                        TextView textChildWaktu = adapterChild.findViewById(R.id.mWaktu);
                        textChildWaktu.setText(kc.getKomentar_waktu());
                        txtChildNama.setText(kc.getKomentar_nama());
                        textChildKomentar.setText(kc.getKomentar_isi());
                        layoutChild.addView(adapterChild);
                    }
                }

            }


        }

    }

    public void addBalas(String id, String nama, String email, String telp, String website, String isi, String parentID, String userid)
    {

        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<postKomentar> call = api.addKomentarBalasPendidikan( id, nama, email, telp, website, parentID, isi , userid);
        call.enqueue(new Callback<postKomentar>() {
            @Override
            public void onResponse(Call<postKomentar> call, Response<postKomentar> response) {
            }

            @Override
            public void onFailure(Call<postKomentar> call, Throwable t) {
                Log.d("onResponse", t.toString());
            }
        });
    }

    public void getDataDetail(){
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<DetilBeritaCetakModel> call = api.detailBeritaCetak(id);

        call.enqueue(new Callback<DetilBeritaCetakModel>() {
            @Override
            public void onResponse(Call<DetilBeritaCetakModel> call, Response<DetilBeritaCetakModel> response) {
                title.setText(response.body().getJudul());
                ratingsum.setText(String.format("%s/5",response.body().getData().getRating()));
                String tampung = response.body().getData().getRating_jumlah();
                ratingpeople.setText(String.format("(%s orang)", tampung));
                textDeskripsi.setText(response.body().getData().getDeskripsi());
                textJudul.setText(response.body().getData().getJudul());
                textDate.setText(response.body().getData().getTime());
                gambarList = response.body().getData().getGambar();
                String[] stringArray;

                if(gambarList.size() == 0)
                {
                    stringArray = new String[gambarList.size()+1];
                    stringArray[0] = response.body().getIcon();

                }
                else
                {
                    stringArray = new String[gambarList.size()];

                    for(int i=0 ; i<gambarList.size() ; i++)
                    {
                        stringArray[i] = gambarList.get(i).getFile_berita_cetak_img();
                    }



                }
                ViewPagerAdapter adapter = new ViewPagerAdapter(DetilBeritaCetak.this,stringArray);
                pager.setAdapter(adapter);
                tabLayout.setupWithViewPager(pager, true);

            }

            @Override
            public void onFailure(Call<DetilBeritaCetakModel> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
            }
        });
    }

    public void getKomentar(){
        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KomentarLengkap> call2 = api2.getKomentarPendidikan(id);
        call2.enqueue(new Callback<KomentarLengkap>() {
            @Override
            public void onResponse(Call<KomentarLengkap> call, Response<KomentarLengkap> response) {
                list = response.body().getKomentar_parent();
                responseChild = response.body().getKomentar_child();

                Log.w("Responselist", new Gson().toJson(response.body().getKomentar_parent()));
                Log.w("Responsechild", new Gson().toJson(response.body().getKomentar_child()));
                //listChild = response.body().getKomentar_child();

                listKomentar();

            }

            @Override
            public void onFailure(Call<KomentarLengkap> call, Throwable t) {
            }
        });
    }


    public void addViewKomentar(final String id, final String nama, final String waktu, final String telp, final String email, final String website, String isi, final String pID, Float hasil){
        final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final RelativeLayout adapter = (RelativeLayout) inflater.inflate(R.layout.komentar_adapter,null);
        TextView txtNama = (TextView) adapter.findViewById(R.id.mNama);
        TextView txtKomentar = (TextView) adapter.findViewById(R.id.mKomentar);
        TextView txtWaktu = adapter.findViewById(R.id.mWaktu);
        RatingBar ratestar = adapter.findViewById(R.id.ratingstar);
        Button btnBatal = adapter.findViewById(R.id.btnBatal);
        Button btnKirim = adapter.findViewById(R.id.btnKirim);
        TextView btnBalas = adapter.findViewById(R.id.btnBalas);
        final LinearLayout layoutChild = adapter.findViewById(R.id.listChild);
        final EditText eKomenBalas = adapter.findViewById(R.id.eKomentar);
        final RelativeLayout layoutBalas = adapter.findViewById(R.id.layoutBalas);
        txtNama.setText(nama);
        txtKomentar.setText(isi);
        txtWaktu.setText(waktu);
        ratestar.setMax(5);
        Float ratingbos = hasil;
        if(ratingbos == 1)
        {
            ratestar.setRating(1);
        }
        else if(ratingbos == 2)
        {
            ratestar.setRating(2);
        }
        else if(ratingbos == 3)
        {
            ratestar.setRating(3);
        }
        else if(ratingbos == 4)
        {
            ratestar.setRating(4);
        }
        else if(ratingbos == 5)
        {
            ratestar.setRating(5);
        }
        btnBalas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBalas.setVisibility(View.VISIBLE);
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutBalas.setVisibility(View.GONE);
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressBar pg = adapter.findViewById(R.id.progress_bar);
                pg.setVisibility(View.VISIBLE);
                addBalas(id, nama, email, telp,website, eKomenBalas.getText().toString(), pID, null);
                final RelativeLayout adapterChild = (RelativeLayout) inflater.inflate(R.layout.komentarchild_adapter,null);
                TextView txtChildNama = (TextView) adapterChild.findViewById(R.id.mNama);
                TextView textChildKomentar = (TextView) adapterChild.findViewById(R.id.mKomentar);
                txtChildNama.setText(nama);
                textChildKomentar.setText(eKomenBalas.getText().toString());
                layoutBalas.setBackgroundColor(getResources().getColor(R.color.progress));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layoutChild.addView(adapterChild);
                        layoutChild.invalidate();
                        layoutBalas.setVisibility(View.GONE);
                        layoutBalas.setBackgroundColor(getResources().getColor(R.color.progress_after));
                        pg.setVisibility(View.GONE);
                    }
                },2000);

            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listPendidikan.addView(adapter,0);
                listPendidikan.invalidate();
            }
        },2000);

    }
}
