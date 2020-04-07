package projekpati.com.projekpati.Bank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
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
import projekpati.com.projekpati.FasilitasUmum.DataFasilitasUmumFragment;
import projekpati.com.projekpati.Kesehatan.KategoriKesehatanFragment;
import projekpati.com.projekpati.Kesehatan.MenuKesehatan;
import projekpati.com.projekpati.MainActivity;
import projekpati.com.projekpati.Model.Bank.BankModel;
import projekpati.com.projekpati.Model.Bank.ListBank;
import projekpati.com.projekpati.Model.FasilitasUmum.FasilitasUmumModel;
import projekpati.com.projekpati.Model.FasilitasUmum.ListFasilitasUmum;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuBank extends AppCompatActivity {
    ListView listView;
    EditText cari;
    List<ListBank> list = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    ImageView iconView;
    Integer status =0;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bank);
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("user_id","");

        toolbar = (Toolbar) findViewById(R.id.bankToolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);
        iconView = toolbar.findViewById(R.id.icon);

        //getIconImage();
        iconView.setLayoutParams(new LinearLayout.LayoutParams(80,80));
        iconView.setImageResource(R.drawable.iconatmbank);

        title.setText("Bank");
        title.setPadding(20,0,0,0);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.menuBank);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id==R.id.beranda){
                    DataBankFragment first = new DataBankFragment();
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
                else if(id==R.id.kategori){

                    KategoriBankFragment second = new KategoriBankFragment();
                    openFragment(second);
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

                    if(userid.equals(""))
                    {
                        Toast.makeText(MenuBank.this,"Silahkan login terlebih dahulu untuk tambah data",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        TambahBankFragment third = new TambahBankFragment();
                        openFragment(third);
                        bottomNavigationView.setEnabled(false);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                status = 0;
                                bottomNavigationView.setEnabled(true);
                            }
                        }, 5000);
                    }

                }
                else if(id==R.id.saring){
//
                    SaringBankFragment fouth = new SaringBankFragment();
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

        DataBankFragment first = new DataBankFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment, first).commit();
    }

    public void getIconImage(){
        //defining a progress dialog to show while signing up
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<BankModel> call = api.tampilSemuaBank();

        call.enqueue(new Callback<BankModel>() {
            @Override
            public void onResponse(Call<BankModel> call, final Response<BankModel> response) {
                Map<String, ListBank> data = response.body().getData();
                Log.d("iconnn",response.body().getIcon());
                title.setText("Bank");

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
            public void onFailure(Call<BankModel> call, Throwable t) {

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
            Intent intent = new Intent(MenuBank.this, CariBank.class);
            startActivity(intent);

        }
        else if(id==android.R.id.home)
        {
            Intent i = new Intent(MenuBank.this, MainActivity.class);
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
