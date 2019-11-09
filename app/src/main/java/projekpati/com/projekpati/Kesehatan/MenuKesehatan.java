package projekpati.com.projekpati.Kesehatan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.MainActivity;
import projekpati.com.projekpati.Model.Kesehatan.KesehatanModel;
import projekpati.com.projekpati.Model.Kesehatan.ListKesehatan;
import projekpati.com.projekpati.Model.Pendidikan.ListPendidikan;
import projekpati.com.projekpati.Model.Pendidikan.PendidikanModel;
import projekpati.com.projekpati.Pendidikan.CariPendidikan;
import projekpati.com.projekpati.Pendidikan.DataPendidikanFragment;
import projekpati.com.projekpati.Pendidikan.KategoriPendidikanFragment;
import projekpati.com.projekpati.Pendidikan.MenuPendidikan;
import projekpati.com.projekpati.Pendidikan.SaringPendidikanFragment;
import projekpati.com.projekpati.Pendidikan.TambahPendidikanFragment;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuKesehatan extends AppCompatActivity {
    ListView listView;
    EditText cari;
    List<ListKesehatan> list = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    ImageView iconView;
    Integer status =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kesehatan);

        toolbar = (Toolbar) findViewById(R.id.kesehatanToolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);
        iconView = toolbar.findViewById(R.id.icon);

        getIconImage();

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.menuKesehatan);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id==R.id.beranda){
                    DataKesehatanFragment first = new DataKesehatanFragment();
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
                    Toast.makeText(MenuKesehatan.this, "Clicked",Toast.LENGTH_SHORT).show();

                }
                else if(id==R.id.kategori){

                    KategoriKesehatanFragment second = new KategoriKesehatanFragment();
                    openFragment(second);
                    bottomNavigationView.setEnabled(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bottomNavigationView.setEnabled(true);
                        }
                    },5000);

                    Toast.makeText(MenuKesehatan.this, "Clicked",Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.tambah){

//                    TambahPendidikanFragment third = new TambahPendidikanFragment();
//                    openFragment(third);
//                    bottomNavigationView.setEnabled(false);
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            status =0;
//                            bottomNavigationView.setEnabled(true);
//                        }
//                    },5000);
//                    Toast.makeText(MenuPendidikan.this, "Clicked",Toast.LENGTH_SHORT).show();

                }
                else if(id==R.id.saring){

                    SaringKesehatanFragment fouth = new SaringKesehatanFragment();
                    openFragment(fouth);
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
                else if(id==R.id.dataku){

                    //Toast.makeText(MenuPendidikan.this, "Login dalam proses",Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

        DataKesehatanFragment first = new DataKesehatanFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment, first).commit();
    }

    public void getIconImage(){
        //defining a progress dialog to show while signing up
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<KesehatanModel> call = api.tampilSemuaKesehatan();

        call.enqueue(new Callback<KesehatanModel>() {
            @Override
            public void onResponse(Call<KesehatanModel> call, final Response<KesehatanModel> response) {
                Map<String, ListKesehatan> data = response.body().getData();

                try {
                    URL url = new URL(response.body().getIcon());
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    Bitmap scaled = Bitmap.createScaledBitmap(bmp,80,80,true);
                    BitmapDrawable icon = new BitmapDrawable(toolbar.getResources(),scaled);
                    iconView.setImageDrawable(icon);
                    title.setText("Kesehatan");
                    //BitmapDescriptor icon =  BitmapDescriptorFactory.fromBitmap(scaled);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(Call<KesehatanModel> call, Throwable t) {

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
            Intent intent = new Intent(MenuKesehatan.this, CariKesehatan.class);
            startActivity(intent);

        }
        else if(id==android.R.id.home)
        {
//            Intent i = new Intent(MenuPendidikan.this, MainActivity.class);
//            startActivity(i);
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
