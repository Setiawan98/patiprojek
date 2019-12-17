package projekpati.com.projekpati.KodePos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.KodePos.DetilKodePos;
import projekpati.com.projekpati.Kuliner.ViewPagerAdapter;
import projekpati.com.projekpati.Model.KodePos.DetilKodePosBaru;
import projekpati.com.projekpati.Model.KodePos.DetilKodePosModel;
import projekpati.com.projekpati.Model.KodePos.GambarKodePosDetil;
import projekpati.com.projekpati.Model.KomentarLengkap;
import projekpati.com.projekpati.Model.KomentarParent;
import projekpati.com.projekpati.Model.postKomentar;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetilKodePos extends AppCompatActivity {

    TextView textNama,  textDeskripsi,  refnama, ratingsum, ratingpeople;
    TextView mTime;
    EditText komentar;
    RatingBar ratingstar;
    Button btnDetil;
    TextView btnKomen;
    ViewPager pager;

    LinearLayout listKodePos;

    List<KomentarParent> list = new ArrayList<>();
    List<DetilKodePosBaru> Menu = new ArrayList<>();
    List<GambarKodePosDetil> gambarList = new ArrayList<>();
    Map<String,List<KomentarParent>> responseChild;
    Toolbar toolbar;
    TabLayout tabLayout;
    TextView title;
    LinearLayout ly;
    String id;
    String parentID;
    LinearLayout pbKomen;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_kode_pos);

        toolbar = (Toolbar) findViewById(R.id.kode_posToolbar);

        refnama = findViewById(R.id.refnama);
        ly = findViewById(R.id.konten);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);

        ratingpeople = findViewById(R.id.ratingpeople);
        ratingsum = findViewById(R.id.ratingsum);

        textNama = findViewById(R.id.mNama);
        textDeskripsi = findViewById(R.id.mDeskripsi);
        tabLayout = findViewById(R.id.tabDots);
        mTime = findViewById(R.id.mTime);


        listKodePos = findViewById(R.id.listKodePos);

        komentar = findViewById(R.id.komentar);
        //komentar.clearFocus();
        /*komentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                komentar.setEnabled(true);
            }
        });*/
        btnKomen = findViewById(R.id.btnKomen);
        pager = findViewById(R.id.view_pager);
        ratingstar = findViewById(R.id.ratingstar);
        pbKomen = findViewById(R.id.progress_bar);
        ratingstar.setMax(5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        title.setText("Detil KodePos");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        final Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id_kode_pos");

        getDataDetail();

        //getKomentar();

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
                Log.d("clicked","1");
                final Bundle bundle = getIntent().getExtras();
                final String id = bundle.getString("id_beritaOnline");
                final float hasil = ratingstar.getRating();
                final String isi,waktu=null, nama=null,email=null,telp=null,website=null,userid=null;
                isi = komentar.getText().toString();

                API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
                Call<postKomentar> call = api.addKomentar( id, "kode_pos",nama, email, telp, website, isi, String.valueOf(hasil), userid);
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
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
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

                listKodePos.addView(adapter);


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
        Call<postKomentar> call = api.addKomentarBalasKodePos( id, nama, email, telp, website, parentID, isi , userid);
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
        Call<DetilKodePosModel> call = api.detailKodePos(id);

        call.enqueue(new Callback<DetilKodePosModel>() {
            @Override
            public void onResponse(Call<DetilKodePosModel> call, Response<DetilKodePosModel> response) {


                Log.w("ResponseAsu", new Gson().toJson(response.body()));
                title.setText(response.body().getJudul());
                refnama.setText(response.body().getData().getKecamatan());
                mTime.setText(response.body().getData().getKelurahan()+", "+response.body().getData().getKabupaten());
                ratingsum.setText(String.format("%s/5",response.body().getData().getRating()));
                String tampung = response.body().getData().getRating_jumlah();
                ratingpeople.setText(String.format("(%s orang)", tampung));
                textNama.setText(response.body().getData().getKode());
                textDeskripsi.setText(response.body().getData().getDeskripsi());

/*
                gambarList = response.body().getData().getGambar();
                String[] stringArray = null;


                if(gambarList.size() == 0)
                {
                    *//*stringArray = new String[gambarList.size()+1];
                    stringArray[0] = response.body().getIcon();*//*


                }
                else
                {
                    stringArray = new String[gambarList.size()];

                    for(int i=0 ; i<gambarList.size() ; i++)
                    {
                        stringArray[i] = gambarList.get(i).getFile_kode_pos_img();
                    }



                }
                ViewPagerAdapter adapter = new ViewPagerAdapter(DetilKodePos.this,stringArray);
                pager.setAdapter(adapter);
                tabLayout.setupWithViewPager(pager, true);*/



            }

            @Override
            public void onFailure(Call<DetilKodePosModel> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
            }
        });
    }

    public void getKomentar(){
        Log.d("idddd",String.valueOf(id));
        Toast.makeText(DetilKodePos.this.getApplicationContext(),id, Toast.LENGTH_SHORT).show();
        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KomentarLengkap> call2 = api2.getKomentarKodePos(id);
        call2.enqueue(new Callback<KomentarLengkap>() {
            @Override
            public void onResponse(Call<KomentarLengkap> call, Response<KomentarLengkap> response) {
                list = response.body().getKomentar_parent();
                responseChild = response.body().getKomentar_child();

                Log.w("Responselist", new Gson().toJson(response.body().getKomentar_parent()));
                Log.w("Responsechild", new Gson().toJson(response.body().getKomentar_child()));
                //listChild = response.body().getKomentar_child();

                listKomentar();

                Toast.makeText(DetilKodePos.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<KomentarLengkap> call, Throwable t) {
                //Toast.makeText(DetilKuliner.this.getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
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
                listKodePos.addView(adapter,0);
                listKodePos.invalidate();
            }
        },2000);

    }
}
