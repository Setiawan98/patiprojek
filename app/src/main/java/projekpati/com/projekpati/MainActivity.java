package projekpati.com.projekpati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import projekpati.com.projekpati.Bank.MenuBank;
import projekpati.com.projekpati.FasilitasUmum.MenuFasilitasUmum;
import projekpati.com.projekpati.Kesehatan.MenuKesehatan;
import projekpati.com.projekpati.Kuliner.MenuKuliner;
import projekpati.com.projekpati.Pariwisata.MenuPariwisata;
import projekpati.com.projekpati.Pendidikan.MenuPendidikan;
import projekpati.com.projekpati.Tukang.MenuTukang;

public class MainActivity extends AppCompatActivity {

    private LinearLayout kulinerLayout,tukangLayout, pariwisataLayout,kesehatanLayout,fasilitasUmumLayout, bankLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    Toast.makeText(MainActivity.this, "Login dalam proses",Toast.LENGTH_SHORT).show();
                }
                return true;
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
        fasilitasUmumLayout = findViewById(R.id.fasilitasUmumLinear);
        fasilitasUmumLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuFasilitasUmum.class);
                startActivity(i);
            }
        });

        bankLayout = findViewById(R.id.bankLinear);
        bankLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuBank.class);
                startActivity(i);
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
}
