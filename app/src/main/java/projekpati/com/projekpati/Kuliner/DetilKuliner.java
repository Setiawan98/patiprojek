package projekpati.com.projekpati.Kuliner;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentTransaction;
        import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
        import androidx.viewpager.widget.ViewPager;
        import projekpati.com.projekpati.API.API;
        import projekpati.com.projekpati.API.RetrofitClientInstance;
        import projekpati.com.projekpati.Model.Kuliner.DetilKulinerBaru;
        import projekpati.com.projekpati.Model.Kuliner.DetilKulinerModel;
        import projekpati.com.projekpati.Model.Kuliner.GambarDetil;
        import projekpati.com.projekpati.Model.KomentarLengkap;
        import projekpati.com.projekpati.Model.KomentarParent;
        import projekpati.com.projekpati.Model.postKomentar;
        import projekpati.com.projekpati.Pendidikan.EditPendidikanActivity;
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
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.Menu;
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

public class DetilKuliner extends AppCompatActivity {

    TextView textNama, textAlamat, textTelepon, textDeskripsi, textEmail, textWebsite, textPemilik, refnama, ratingsum, ratingpeople;
    TextView senin, selasa, rabu, kamis, jumat, sabtu, minggu;
    FrameLayout frameLayout;
    EditText komentar;
    RatingBar ratingstar;
    Button btnDetil;
    LinearLayout btnJam, btnMap;
    ImageView btnMenu;
    TextView btnKomen;
    ViewPager pager;
    ImageView btnEdit;
    float lat;
    float longt;
    LinearLayout listKuliner, listMenu;
    LinearLayout linearDetil, linearJam, linearMenu;
    List<KomentarParent> list = new ArrayList<>();
    List<DetilKulinerBaru> Menu = new ArrayList<>();
    List<GambarDetil> gambarList = new ArrayList<>();
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
        setContentView(R.layout.activity_detil_kuliner);

        toolbar = (Toolbar) findViewById(R.id.kulinerToolbar);

        ly = findViewById(R.id.konten);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);
        linearDetil = findViewById(R.id.linearDetil);
        btnMenu = findViewById(R.id.btnMenu);
        linearMenu = findViewById(R.id.linearMenu);
        ratingpeople = findViewById(R.id.ratingpeople);
        listMenu = findViewById(R.id.listKulinerMenu);
        ratingsum = findViewById(R.id.ratingsum);
        refnama = findViewById(R.id.refnama);
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
        tabLayout = findViewById(R.id.tabDots);
        btnEdit = findViewById(R.id.btnEdit);
        textEmail = findViewById(R.id.mEmail);
        textWebsite = findViewById(R.id.mWebsite);
        textPemilik = findViewById(R.id.mPemilik);
        listKuliner = findViewById(R.id.listKuliner);
        btnMap = findViewById(R.id.btnMap);
        komentar = findViewById(R.id.komentar);
        btnKomen = findViewById(R.id.btnKomen);
        pager = findViewById(R.id.view_pager);
        ratingstar = findViewById(R.id.ratingstar);
        pbKomen = findViewById(R.id.progress_bar);
        ratingstar.setMax(5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        title.setText("Detil Kuliner");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        linearDetil.setVisibility(View.VISIBLE);
        linearJam.setVisibility(View.GONE);
        linearMenu.setVisibility(View.GONE);

        final Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id_kuliner");

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
                EditKuliner editKuliner = new EditKuliner();
                editKuliner.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragmentEdit, editKuliner);
                fragmentTransaction.commit();
            }
        });

        btnKomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle bundle = getIntent().getExtras();
                final String id = bundle.getString("id_kuliner");
                final float hasil = ratingstar.getRating();
                final String isi,waktu=null, nama=null,email=null,telp=null,website=null,userid=null;
                isi = komentar.getText().toString();

                API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
                Call<postKomentar> call = api.addKomentar( id, "kuliner",nama, email, telp, website, isi, String.valueOf(hasil), userid);
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


        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH,"http://maps.google.com/maps?daddr=%f,%f (%s)",lat,longt,"Your Search");
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });


        btnJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearDetil.setVisibility(View.GONE);
                linearMenu.setVisibility(View.GONE);
                linearJam.setVisibility(View.VISIBLE);
            }
        });


        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearDetil.setVisibility(View.GONE);
                linearMenu.setVisibility(View.VISIBLE);
                linearJam.setVisibility(View.GONE);
                API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
                Call<DetilKulinerModel> call = api.detailKuliner(id);
                call.enqueue(new Callback<DetilKulinerModel>() {
                    @Override
                    public void onResponse(Call<DetilKulinerModel> call, Response<DetilKulinerModel> response) {
                        final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        int size = response.body().getData().getMakanan().size();
                        Log.d("size",String.valueOf(size));
                        for(int i=0; i <size ; i++)
                        {
                                final LinearLayout adapter = (LinearLayout) inflater.inflate(R.layout.daftar_menu_adapter,null);
                                TextView txtNama = adapter.findViewById(R.id.mNama);
                                TextView txtHarga = adapter.findViewById(R.id.mHarga);
                                TextView txtDeskripsi = adapter.findViewById(R.id.mDeskripsi);
                                txtNama.setText(response.body().getData().getMakanan().get(i).getNama());
                                txtHarga.setText(response.body().getData().getMakanan().get(i).getHarga());
                                txtDeskripsi.setText(response.body().getData().getMakanan().get(i).getDeskripsi());

                                listMenu.addView(adapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<DetilKulinerModel> call, Throwable t) {

                    }
                });
            }
        });

        btnDetil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearJam.setVisibility(View.GONE);
                linearMenu.setVisibility(View.GONE);
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

                listKuliner.addView(adapter);


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
        Call<postKomentar> call = api.addKomentarBalas( id, nama, email, telp, website, parentID, isi , userid);
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
        Call<DetilKulinerModel> call = api.detailKuliner(id);

        call.enqueue(new Callback<DetilKulinerModel>() {
            @Override
            public void onResponse(Call<DetilKulinerModel> call, Response<DetilKulinerModel> response) {


                Log.w("ResponseAsu", new Gson().toJson(response.body()));
                title.setText(response.body().getJudul());
                refnama.setText(response.body().getData().getRef_kuliner_nama());
                ratingsum.setText(String.format("%s/5",response.body().getData().getRating()));
                String tampung = response.body().getData().getRating_jumlah();
                ratingpeople.setText(String.format("(%s orang)", tampung));
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
                        stringArray[i] = gambarList.get(i).getFile_kuliner_img();
                    }



                }
                ViewPagerAdapter adapter = new ViewPagerAdapter(DetilKuliner.this,stringArray);
                pager.setAdapter(adapter);
                tabLayout.setupWithViewPager(pager, true);

                btnMap.bringToFront();

            }

            @Override
            public void onFailure(Call<DetilKulinerModel> call, Throwable t) {
                Log.e("OnFailureDetil", t.getMessage().toString());
            }
        });
    }

    public void getKomentar(){
        Toast.makeText(DetilKuliner.this.getApplicationContext(),id, Toast.LENGTH_SHORT).show();
        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KomentarLengkap> call2 = api2.getKomentar(id);
        call2.enqueue(new Callback<KomentarLengkap>() {
            @Override
            public void onResponse(Call<KomentarLengkap> call, Response<KomentarLengkap> response) {
                list = response.body().getKomentar_parent();
                responseChild = response.body().getKomentar_child();

                Log.w("Responselist", new Gson().toJson(response.body().getKomentar_parent()));
                Log.w("Responsechild", new Gson().toJson(response.body().getKomentar_child()));
               //listChild = response.body().getKomentar_child();

                listKomentar();

                Toast.makeText(DetilKuliner.this.getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
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
                listKuliner.addView(adapter,0);
                listKuliner.invalidate();
            }
        },2000);

    }
}
