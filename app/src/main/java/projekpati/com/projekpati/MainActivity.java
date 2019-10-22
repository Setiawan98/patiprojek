package projekpati.com.projekpati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import projekpati.com.projekpati.Kuliner.MenuKuliner;

public class MainActivity extends AppCompatActivity {

    private LinearLayout kulinerLayout;
    private DrawerLayout dl;
    Toolbar toolbar;
    Integer icon=1;
    LinearLayout menuicon;
    private PrefManager prefManager;
    private ActionBarDrawerToggle abdt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl= (DrawerLayout) findViewById(R.id.dl);
        menuicon = findViewById(R.id.menuicon);
        toolbar = findViewById(R.id.myToolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

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
                        menuicon.setVisibility(View.INVISIBLE);
                        icon=0;
                    }
                    else
                    {
                        menuicon.setVisibility(View.VISIBLE);
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
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


}
