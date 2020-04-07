package projekpati.com.projekpati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.Calendar;

import projekpati.com.projekpati.Agenda.MenuAgenda;
import projekpati.com.projekpati.Aspirasi.MenuAspirasi;
import projekpati.com.projekpati.Bank.MenuBank;
import projekpati.com.projekpati.BeliMobil.MenuBeliMobil;
import projekpati.com.projekpati.BeliMotor.MenuBeliMotor;
import projekpati.com.projekpati.BeritaCetak.MenuBeritaCetak;
import projekpati.com.projekpati.BeritaOnline.MenuBeritaOnline;
import projekpati.com.projekpati.FasilitasUmum.MenuFasilitasUmum;
import projekpati.com.projekpati.Hotel.MenuHotel;
import projekpati.com.projekpati.Kerjaan.MenuKerjaan;
import projekpati.com.projekpati.Kesehatan.MenuKesehatan;
import projekpati.com.projekpati.Kesehatan.TampilKesehatanByJenis;
import projekpati.com.projekpati.KodePos.MenuKodePos;
import projekpati.com.projekpati.Koperasi.MenuKoperasi;
import projekpati.com.projekpati.Kuliner.MenuKuliner;
import projekpati.com.projekpati.Lapak.MenuLapak;
import projekpati.com.projekpati.Olahraga.MenuOlahraga;
import projekpati.com.projekpati.Otomotif.MenuOtomotif;
import projekpati.com.projekpati.Pangan.MenuPangan;
import projekpati.com.projekpati.Pariwisata.MenuPariwisata;
import projekpati.com.projekpati.Pendidikan.MenuPendidikan;
import projekpati.com.projekpati.PerangkatDaerah.MenuPerda;
import projekpati.com.projekpati.Polisi.MenuPolisi;
import projekpati.com.projekpati.Salon.MenuSalon;
import projekpati.com.projekpati.Spbu.MenuSpbu;
import projekpati.com.projekpati.TelpPenting.MenuTelpPenting;
import projekpati.com.projekpati.TempatIbadah.MenuIbadah;
import projekpati.com.projekpati.Tukang.MenuTukang;
import projekpati.com.projekpati.Video.MenuVideo;
import projekpati.com.projekpati.bioskop.MenuBioskop;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    private LinearLayout kulinerLayout, bMotorLayout, bMobilLayout, beritaCetakLayout, otomotifLayout, lapakLayout, aspirasiLayout,
            spbuLayout, salonLayout ,tukangLayout,polisiLayout, olahragaLayout,
            pariwisataLayout,kesehatanLayout,fasilitasUmumLayout, bankLayout,
            biosopLayout, hotelLayput, koperasiLayout,agendaLayout, kerjaanLayout, perdaLayout,
            beritaOnlineLayout, telpPentingLayout, kodePosLayout, apotekLayout, dokterLayout,rumahSakitLayout,ibadahLayout,
            pukesmasLayout, ambulanLayout, videoLayout, panganLayout;
    private LinearLayout pendidikanLayout;
    private DrawerLayout dl;
    ScrollView scroll;
    Toolbar toolbar;
    Integer icon=1;
    private MyViewPagerAdapter myViewPagerAdapter;
    LinearLayout menuicon;
    private ActionBarDrawerToggle abdt;
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    public static boolean doubleBackToExitPressedOnce = false;
    ImageView a;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent alarmIntent;
    String userID;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoUpdateDataInBackgroud();

        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        userID = sharedPreferences.getString("user_id","");



        dl= (DrawerLayout) findViewById(R.id.dl);
        scroll = findViewById(R.id.scroll);
        menuicon = findViewById(R.id.menuicon);
        toolbar = findViewById(R.id.myToolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);

        // layout xml slide 1 sampai 4
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.home_slider_1,
                R.layout.home_slider_2,
                R.layout.home_slider_3};

        // tombol dots (lingkaran kecil perpindahan slide)
        addBottomDots(1);

        myViewPagerAdapter = new MainActivity.MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        viewPager.setCurrentItem(1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);


        //getActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem loginMenu = menu.findItem(R.id.mMasukDaftar);
        if(userID.equals(""))
        {
            loginMenu.setTitle("Masuk Atau Daftar");
        }
        else {
            loginMenu.setTitle("Log out");
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id==R.id.mEditProfil){
                    Toast.makeText(MainActivity.this, "Edit Profile Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.mPenanda){
                    Toast.makeText(MainActivity.this, "Penanda Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.mPemberitahuan){
                    Toast.makeText(MainActivity.this, "Pemberitahuan Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.mMenuIcon){
                    if(icon==1)
                    {
                        scroll.setVisibility(View.GONE);
                        icon=0;
                    }
                    else
                    {
                        scroll.setVisibility(View.VISIBLE);
                        icon=1;
                    }
                }
                else if(id==R.id.mReaksiAnda){
                    Toast.makeText(MainActivity.this, "Reaksi Anda Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.mInfoGempa){
                    Toast.makeText(MainActivity.this, "Info Gempa Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.mTentangKami){
                    Toast.makeText(MainActivity.this, "Tentang Kami Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.mMasukDaftar){
                    if(userID.equals("")) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        sharedPreferences = getSharedPreferences("userData",MODE_PRIVATE);
                        myEdit = sharedPreferences.edit();
                        myEdit.putString("user_id","");
                        myEdit.putString("user_nama","");
                        myEdit.putString("user_namalogin","");
                        myEdit.putString("user_password","");
                        myEdit.putString("user_email","");
                        myEdit.putString("user_telp","");
                        myEdit.putString("user_website","");
                        myEdit.putString("user_kode","");
                        myEdit.putString("user_last_login","");
                        myEdit.putString("user_waktu_register","");
                        myEdit.putString("user_aktif","");
                        myEdit.putString("lapor_user_id","");
                        myEdit.apply();
                        Toast.makeText(MainActivity.this, "Log out berhasil..", Toast.LENGTH_SHORT);
                        finish();
                        startActivity(getIntent());
                    }
                    //Toast.makeText(MainActivity.this, "Login dalam proses",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        beritaCetakLayout = findViewById(R.id.beritaCetakLinear);
        beritaCetakLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuBeritaCetak.class);
                startActivity(i);
            }
        });
        otomotifLayout = findViewById(R.id.otomotifLinear);
        otomotifLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuOtomotif.class);
                startActivity(i);
            }
        });

        perdaLayout = findViewById(R.id.perangkatDaerahLinear);
        perdaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuPerda.class);
                startActivity(i);
            }
        });

        bMotorLayout = findViewById(R.id.bengkelMotorLinear);
        bMotorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuBeliMotor.class);
                startActivity(i);
            }
        });

        ibadahLayout = findViewById(R.id.tempatIbadahLinear);
        ibadahLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuIbadah.class);
                startActivity(i);
            }
        });

        bMobilLayout = findViewById(R.id.bengkelMobilLinear);
        bMobilLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuBeliMobil.class);
                startActivity(i);
            }
        });
        aspirasiLayout = findViewById(R.id.aspirasiLinear);
        aspirasiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuAspirasi.class);
                startActivity(i);
            }
        });

        lapakLayout = findViewById(R.id.lapakLinear);
        lapakLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuLapak.class);
                startActivity(i);
            }
        });

        kulinerLayout =  findViewById(R.id.kulinerLinear);
        kulinerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuKuliner.class);
                startActivity(i);
            }
        });
        pendidikanLayout =  findViewById(R.id.wisataLinear);
        pendidikanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuPendidikan.class);
                startActivity(i);
            }
        });
        tukangLayout = findViewById(R.id.tukangLinear);
        tukangLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuTukang.class);
                startActivity(i);
            }
        });
        pariwisataLayout = findViewById(R.id.pariwisataLinear);
        pariwisataLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuPariwisata.class);
                startActivity(i);
            }
        });
        kesehatanLayout = findViewById(R.id.kesehatanLinear);
        kesehatanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuKesehatan.class);
                startActivity(i);
            }
        });
//        fasilitasUmumLayout = findViewById(R.id.fasilitasUmumLinear);
//        fasilitasUmumLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, MenuFasilitasUmum.class);
//                startActivity(i);
//            }
//        });

        bankLayout = findViewById(R.id.bankLinear);
        bankLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuBank.class);
                startActivity(i);
            }
        });

        biosopLayout = findViewById(R.id.bioskopLinear);
        biosopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuBioskop.class);
                startActivity(i);
            }
        });

        hotelLayput = findViewById(R.id.hotelLinear);
        hotelLayput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuHotel.class);
                startActivity(i);
            }
        });

        koperasiLayout = findViewById(R.id.koperasiLinear);
        koperasiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuKoperasi.class);
                startActivity(i);
            }
        });

        olahragaLayout = findViewById(R.id.olahragaLinear);
        olahragaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuOlahraga.class);
                startActivity(i);
            }
        });

        polisiLayout = findViewById(R.id.polisiLinear);
        polisiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuPolisi.class);
                startActivity(i);
            }
        });

        salonLayout = findViewById(R.id.salonLinear);
        salonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuSalon.class);
                startActivity(i);
            }
        });

        spbuLayout = findViewById(R.id.spbuLinear);
        spbuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuSpbu.class);
                startActivity(i);
            }
        });


        agendaLayout = findViewById(R.id.agendaLinear);
        agendaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuAgenda.class);
                startActivity(i);
            }
        });

        kerjaanLayout = findViewById(R.id.kerjaanLinear);
        kerjaanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuKerjaan.class);
                startActivity(i);
            }
        });

        beritaOnlineLayout = findViewById(R.id.beritaOnlineLinear);
        beritaOnlineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuBeritaOnline.class);
                startActivity(i);
            }
        });

        kodePosLayout = findViewById(R.id.kodePosLinear);
        kodePosLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuKodePos.class);
                startActivity(i);
            }
        });

        telpPentingLayout = findViewById(R.id.telpPentingLinear);
        telpPentingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuTelpPenting.class);
                startActivity(i);
            }
        });

        apotekLayout = findViewById(R.id.apotikLinear);
        apotekLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TampilKesehatanByJenis.class);
                intent.putExtra("id_kesehatan","4");
                intent.putExtra("kategori","Apotek");
                startActivity(intent);
            }
        });

        dokterLayout = findViewById(R.id.dokterLinear);
        dokterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TampilKesehatanByJenis.class);
                intent.putExtra("id_kesehatan","7");
                intent.putExtra("kategori","Dokter");
                startActivity(intent);
            }
        });

        rumahSakitLayout = findViewById(R.id.rumahSakitLinear);
        rumahSakitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TampilKesehatanByJenis.class);
                intent.putExtra("id_kesehatan","1");
                intent.putExtra("kategori","Rumah Sakit");
                startActivity(intent);
            }
        });

        pukesmasLayout = findViewById(R.id.pukesmasLinear);
        pukesmasLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TampilKesehatanByJenis.class);
                intent.putExtra("id_kesehatan","6");
                intent.putExtra("kategori","Puskesmas");
                startActivity(intent);
            }
        });

        ambulanLayout = findViewById(R.id.ambulanLinear);
        ambulanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TampilKesehatanByJenis.class);
                intent.putExtra("id_kesehatan","5");
                intent.putExtra("kategori","Ambulan");
                startActivity(intent);
            }
        });

        videoLayout = findViewById(R.id.videoLinear);
        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuVideo.class);
                startActivity(intent);
            }
        });

        panganLayout = findViewById(R.id.hargaPanganLinear);
        panganLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuPangan.class);
                startActivity(intent);
            }
        });

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Membutuhkan Izin Lokasi", Toast.LENGTH_SHORT).show();
            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }
        } else {
            // Permission has already been granted
            Toast.makeText(this, "Izin Lokasi diberikan", Toast.LENGTH_SHORT).show();
        }

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            if(position==1)
            {
                final ImageMap mImageMap;
                mImageMap = (ImageMap)view.findViewById(R.id.map);
                mImageMap.setImageResource(R.drawable.backgroundberanda);

                // add a click handler to react when areas are tapped
                mImageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler()
                {
                    @Override
                    public void onImageMapClicked(int id, ImageMap imageMap)
                    {
                        // when the area is tapped, show the name in a
                        // text bubble

                        //mImageMap.showBubble(id);
                        if(mImageMap.getName(id).equals("Aspirasi"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuAspirasi.class);
                            startActivity(i);
                        }
                        else if(mImageMap.getName(id).equals("JadwalSholat"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuIbadah.class);
                            startActivity(i);
                        }
                        else if(mImageMap.getName(id).equals("Video"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuVideo.class);
                            startActivity(i);
                        }
                        else if(mImageMap.getName(id).equals("hargaPangan"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuPangan.class);
                            startActivity(i);
                        }
                        else if(mImageMap.getName(id).equals("Tukang"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuTukang.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("infoKab"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuPerda.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("Hotel"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuHotel.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("bank"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuBank.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("spbu"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuSpbu.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("kuliner"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuKuliner.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("kesehatan"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuKesehatan.class);
                            startActivity(i);
                        }
                        Toast.makeText(MainActivity.this, mImageMap.getName(id),Toast.LENGTH_SHORT).show();
                        Log.d("MasukClick","Clicked");
                    }

                    @Override
                    public void onBubbleClicked(int id)
                    {
                        // react to info bubble for area being tapped
                    }
                });

            }

            else if (position==0){
                final ImageMap mImageMap;
                mImageMap = (ImageMap)view.findViewById(R.id.map);
                mImageMap.setImageResource(R.drawable.left);
                mImageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler() {
                    @Override
                    public void onImageMapClicked(int id, ImageMap imageMap) {
                        if(mImageMap.getName(id).equals("Olahraga"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuOlahraga.class);
                            startActivity(i);
                        }
                        else if(mImageMap.getName(id).equals("Berita"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuBeritaCetak.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("Bioskop"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuBioskop.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("Pariwisata"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuPariwisata.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("Agenda"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuAgenda.class);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onBubbleClicked(int id) {

                    }
                });
            }

            else if(position==2)
            {
                final ImageMap mImageMap;
                mImageMap = (ImageMap)view.findViewById(R.id.map);
                mImageMap.setImageResource(R.drawable.right);
                mImageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler() {
                    @Override
                    public void onImageMapClicked(int id, ImageMap imageMap) {
                        if(mImageMap.getName(id).equals("Pendidikan"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuPendidikan.class);
                            startActivity(i);
                        }
                        else if(mImageMap.getName(id).equals("Motor"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuBeliMotor.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("Koperasi"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuKoperasi.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("Mobil"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuBeliMobil.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("Kerjaan"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuKerjaan.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("Polisi"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuPolisi.class);
                            startActivity(i);
                        }
                        else if (mImageMap.getName(id).equals("Salon"))
                        {
                            Intent i = new Intent(MainActivity.this, MenuSalon.class);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onBubbleClicked(int id) {

                    }
                });
            }

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            this.finishAffinity();
            System.exit(1);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Double Click for Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


//    private void scheduleNotification (Notification notification , int delay) {
//        Intent notificationIntent = new Intent( this, NotificationPublisher. class ) ;
//        notificationIntent.putExtra(NotificationPublisher. NOTIFICATION_ID , 1 ) ;
//        notificationIntent.putExtra(NotificationPublisher. NOTIFICATION , notification) ;
//        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
//        long futureInMillis = SystemClock. elapsedRealtime () + delay ;
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
//        assert alarmManager != null;
//        alarmManager.set(AlarmManager. ELAPSED_REALTIME_WAKEUP , futureInMillis , pendingIntent) ;
//    }
//
//
//    private Notification getNotification (String content) {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
//        builder.setContentTitle( "Scheduled Notification" ) ;
//        builder.setContentText(content) ;
//        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
//        builder.setAutoCancel( true ) ;
//        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
//        return builder.build() ;
//    }

    private void AutoUpdateDataInBackgroud(){
        alarmIntent = new Intent(MainActivity.this, NotificationPublisher.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,alarmIntent,0);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        long interval = 500;
        Calendar calendar = Calendar.getInstance();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),interval,pendingIntent);

    }

}
