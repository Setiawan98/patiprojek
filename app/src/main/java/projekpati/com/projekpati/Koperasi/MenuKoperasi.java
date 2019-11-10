package projekpati.com.projekpati.Koperasi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Koperasi.DataKoperasiFragment;
import projekpati.com.projekpati.MainActivity;
import projekpati.com.projekpati.Model.Koperasi.KoperasiModel;
import projekpati.com.projekpati.Model.Koperasi.ListKoperasi;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuKoperasi extends AppCompatActivity {
    ListView listView;
    EditText cari;
    List<ListKoperasi> list = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    ImageView iconView;
    Integer status =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_koperasi);

        toolbar = (Toolbar) findViewById(R.id.koperasiToolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);
        iconView = toolbar.findViewById(R.id.icon);

        getIconImage();

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.menuKoperasi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id==R.id.beranda){
                    DataKoperasiFragment first = new DataKoperasiFragment();
                    openFragment(first);
                    //status =1;
                    bottomNavigationView.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bottomNavigationView.setEnabled(true);
                        }
                    },5000);

                }
                else if(id==R.id.tambah){

                    TambahKoperasiFragment third = new TambahKoperasiFragment();
                    openFragment(third);
                    bottomNavigationView.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            status =0;
                            bottomNavigationView.setEnabled(true);
                        }
                    },5000);

                }
                else if(id==R.id.saring){
//
//                    SaringKesehatanFragment fouth = new SaringKesehatanFragment();
//                    openFragment(fouth);
//                    bottomNavigationView.setEnabled(false);
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            status =0;
//                            bottomNavigationView.setEnabled(true);
//                        }
//                    },5000);

                }
                else if(id==R.id.dataku){

                    //Toast.makeText(MenuPendidikan.this, "Login dalam proses",Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

        DataKoperasiFragment first = new DataKoperasiFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment, first).commit();
    }

    public void getIconImage(){
        //defining a progress dialog to show while signing up
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KoperasiModel> call = api.tampilSemuaKoperasi();

        call.enqueue(new Callback<KoperasiModel>() {
            @Override
            public void onResponse(Call<KoperasiModel> call, final Response<KoperasiModel> response) {
                Map<String, ListKoperasi> data = response.body().getData();
                Log.d("iconnn",response.body().getIcon());
                title.setText("Koperasi");

//                try {
//                    URL url = new URL(response.body().getIcon());
//                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                    Bitmap scaled = Bitmap.createScaledBitmap(bmp,80,80,true);
//                    BitmapDrawable icon = new BitmapDrawable(toolbar.getResources(),scaled);
//                    iconView.setImageDrawable(icon);
//
//                    //BitmapDescriptor icon =  BitmapDescriptorFactory.fromBitmap(scaled);
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }



            }

            @Override
            public void onFailure(Call<KoperasiModel> call, Throwable t) {

                Log.d("onResponse", t.toString());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.btnSearch)
        {
            Intent intent = new Intent(MenuKoperasi.this, CariKoperasi.class);
            startActivity(intent);

        }
        else if(id==android.R.id.home)
        {
            Intent i = new Intent(MenuKoperasi.this, MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void openFragment (final Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
    }
}
