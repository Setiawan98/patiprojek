package projekpati.com.projekpati.Otomotif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Kuliner.ViewPagerAdapter;
import projekpati.com.projekpati.Model.KomentarLengkap;
import projekpati.com.projekpati.Model.KomentarParent;
import projekpati.com.projekpati.Model.Otomotif.DetilOtomotifModel;
import projekpati.com.projekpati.Model.Otomotif.GambarOtomotifDetil;
import projekpati.com.projekpati.Model.postKomentar;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DetilOtomotif extends AppCompatActivity {
    TextView textNama, textAlamat, textTelepon, textHarga, textKondisi, textModel, textKilometer, textWarna, textTahun,
            textTransmisi, textHabis, textDeskripsi, textEmail, textPenjual, textWebsite, refnama, ratingsum, ratingpeople;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView title;
    Toolbar toolbar;
    List<KomentarParent> list = new ArrayList<>();
    List<GambarOtomotifDetil> gambarList = new ArrayList<>();
    Map<String,List<KomentarParent>> responseChild;
    float lat;
    float longt;
    String parentID;
    LinearLayout listOtomotif, btnMap;
    TabLayout tabLayout;
    TextView btnKomen;
    RatingBar ratingstar;
    EditText komentar;
    LinearLayout pbKomen;
    String id;
    ViewPager pager;
    FrameLayout frameLayout;
    ImageView btnEdit;
    String userid, isi, nama,email,telp,website;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_otomotif);
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("user_id","");
        nama = sharedPreferences.getString("user_nama","");
        email = sharedPreferences.getString("user_email","");
        telp = sharedPreferences.getString("user_telp","");
        website = sharedPreferences.getString("user_website","");

        toolbar = (Toolbar) findViewById(R.id.otomotifToolbar);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);

        ratingpeople = findViewById(R.id.ratingpeople);
        ratingsum = findViewById(R.id.ratingsum);
        refnama = findViewById(R.id.refnama);
        textNama = findViewById(R.id.mNama);
        textAlamat = findViewById(R.id.mAlamat);
        textTelepon = findViewById(R.id.mTelpon);
        textPenjual = findViewById(R.id.mPenjual);
        textDeskripsi = findViewById(R.id.mDeskripsi);
        tabLayout = findViewById(R.id.tabDots);
        textEmail = findViewById(R.id.mEmail);
        textWebsite = findViewById(R.id.mWebsite);
        textHarga = findViewById(R.id.mHarga);
        textKondisi = findViewById(R.id.mKondisi);
        textModel = findViewById(R.id.mModel);
        textKilometer = findViewById(R.id.mKilometer);
        textWarna = findViewById(R.id.mWarna);
        textTahun = findViewById(R.id.mThnPembuatan);
        textTransmisi = findViewById(R.id.mTransmisi);
        textHabis = findViewById(R.id.mHabisTerjual);
        btnEdit = findViewById(R.id.btnEdit);
        listOtomotif = findViewById(R.id.listOtomotif);
        btnMap = findViewById(R.id.btnMap);
        komentar = findViewById(R.id.komentar);
        btnKomen = findViewById(R.id.btnKomen);
        pager = findViewById(R.id.view_pager);
        ratingstar = findViewById(R.id.ratingstar);
        pbKomen = findViewById(R.id.progress_bar);
        ratingstar.setMax(5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        title.setText("Detil Otomotif");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id_otomotif");
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
                if(userid.equals(""))
                {
                    Toast.makeText(DetilOtomotif.this,"Silahkan login terlebih dahulu untuk tambah komentar",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final Bundle bundle = getIntent().getExtras();
                    final String id = bundle.getString("id_otomotif");
                    final float hasil = ratingstar.getRating();
                    isi = komentar.getText().toString();

                    API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
                    Call<postKomentar> call = api.addKomentar( id, "otomotif",nama, email, telp, website, isi, String.valueOf(hasil), userid);
                    call.enqueue(new Callback<postKomentar>() {
                        @Override
                        public void onResponse(Call<postKomentar> call, Response<postKomentar> response) {
                            //Toast.makeText(DetilKuliner.this, "Sukses Berkomentar", Toast.LENGTH_SHORT).show();
                            parentID= response.body().getDataid();
                            addViewKomentar(id,  nama, "now",  telp, email,  website,isi,parentID, hasil);
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

        frameLayout = findViewById(R.id.fragmentEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeRefreshLayout.setVisibility(View.GONE);
                frameLayout.setVisibility(View.VISIBLE);
                Bundle bundle = new Bundle();
                bundle.putString("id_detil",id);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                EditOtomotif editOtomotif = new EditOtomotif();
                editOtomotif.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragmentEdit, editOtomotif);
                fragmentTransaction.commit();
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
                        if(userid.equals(""))
                        {
                            Toast.makeText(DetilOtomotif.this,"Silahkan login terlebih dahulu untuk tambah komentar",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            layoutBalas.setVisibility(View.VISIBLE);
                        }
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
                        addBalas(id, nama, email, telp,website, eKomenBalas.getText().toString(), kp.getKomentar_id(), userid);
                        final RelativeLayout adapterChild = (RelativeLayout) inflater.inflate(R.layout.komentarchild_adapter,null);
                        TextView txtChildNama = (TextView) adapterChild.findViewById(R.id.mNama);
                        TextView textChildKomentar = (TextView) adapterChild.findViewById(R.id.mKomentar);
                        TextView textChildWaktu = adapterChild.findViewById(R.id.mWaktu);
                        txtChildNama.setText(nama);
                        textChildWaktu.setText("now");
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

                listOtomotif.addView(adapter);


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
        Call<postKomentar> call = api.addKomentarBalasOtomotif( id, nama, email, telp, website, parentID, isi , userid);
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
        Call<DetilOtomotifModel> call = api.detailOtomotif(id);

        call.enqueue(new Callback<DetilOtomotifModel>() {
            @Override
            public void onResponse(Call<DetilOtomotifModel> call, Response<DetilOtomotifModel> response) {
                title.setText(response.body().getJudul());
                refnama.setText(response.body().getData().getRef_otomotif_nama());
                ratingsum.setText(String.format("%s/5",response.body().getData().getRating()));
                String tampung = response.body().getData().getRating_jumlah();
                ratingpeople.setText(String.format("(%s orang)", tampung));
                textNama.setText(response.body().getData().getNama());
                textPenjual.setText(response.body().getData().getPenjual());
                textHarga.setText(response.body().getData().getHarga());
                textKondisi.setText(response.body().getData().getKondisi());
                textModel.setText(response.body().getData().getModel());
                textKilometer.setText(response.body().getData().getKilometer());
                textWarna.setText(response.body().getData().getWarna());
                textTahun.setText(response.body().getData().getThn_pembuatan());
                textTransmisi.setText(response.body().getData().getTransmisi());
                textHabis.setText(response.body().getData().getHabis_terjual());
                textAlamat.setText(response.body().getData().getAlamat());
                textDeskripsi.setText(response.body().getData().getDeskripsi());
                textEmail.setText(response.body().getData().getEmail());
                textWebsite.setText(response.body().getData().getWebsite());
                textTelepon.setText(response.body().getData().getTelp());
                lat = Float.parseFloat(response.body().getData().getLatitude());
                longt = Float.parseFloat(response.body().getData().getLongitude());


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
                        stringArray[i] = gambarList.get(i).getFile_otomotif_img();
                    }



                }
                ViewPagerAdapter adapter = new ViewPagerAdapter(DetilOtomotif.this,stringArray);
                pager.setAdapter(adapter);
                tabLayout.setupWithViewPager(pager, true);

                btnMap.bringToFront();

            }

            @Override
            public void onFailure(Call<DetilOtomotifModel> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
            }
        });
    }

    public void getKomentar(){
        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KomentarLengkap> call2 = api2.getKomentarOtomotif(id);
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
                if(userid.equals(""))
                {
                    Toast.makeText(DetilOtomotif.this,"Silahkan login terlebih dahulu untuk tambah komentar",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    layoutBalas.setVisibility(View.VISIBLE);
                }
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
                addBalas(id, nama, email, telp,website, eKomenBalas.getText().toString(), pID, userid);
                final RelativeLayout adapterChild = (RelativeLayout) inflater.inflate(R.layout.komentarchild_adapter,null);
                TextView txtChildNama = (TextView) adapterChild.findViewById(R.id.mNama);
                TextView textChildKomentar = (TextView) adapterChild.findViewById(R.id.mKomentar);
                TextView textChildWaktu = adapterChild.findViewById(R.id.mWaktu);
                txtChildNama.setText(nama);
                textChildWaktu.setText("now");
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
                listOtomotif.addView(adapter,0);
                listOtomotif.invalidate();
            }
        },2000);

    }
}
