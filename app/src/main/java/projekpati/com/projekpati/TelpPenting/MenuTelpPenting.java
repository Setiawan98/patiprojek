package projekpati.com.projekpati.TelpPenting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.BeritaOnline.KategoriBeritaOnlineFragment;
import projekpati.com.projekpati.MainActivity;
import projekpati.com.projekpati.Model.TelpPenting.TelpPentingModel;
import projekpati.com.projekpati.Model.TelpPenting.ListTelpPenting;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuTelpPenting extends AppCompatActivity {
    ListView listView;
    EditText cari;
    List<ListTelpPenting> list = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    ImageView iconView;
    Integer status =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_telp_penting);

        toolbar = (Toolbar) findViewById(R.id.beritaCetakToolbar);
        title = toolbar.findViewById(R.id.title);
        title.setTextColor(0xFFFFFFFF);
        iconView = toolbar.findViewById(R.id.icon);

        //getIconImage();
        iconView.setLayoutParams(new LinearLayout.LayoutParams(80,80));
        iconView.setImageResource(R.drawable.iconnomorpenting);

        title.setText("Telp Penting");
        title.setPadding(20,0,0,0);

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DataTelpPentingFragment first = new DataTelpPentingFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment, first).commit();
    }
    public void getIconImage(){
        //defining a progress dialog to show while signing up
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<TelpPentingModel> call = api.tampilSemuaTelpPenting();

        call.enqueue(new Callback<TelpPentingModel>() {
            @Override
            public void onResponse(Call<TelpPentingModel> call, final Response<TelpPentingModel> response) {
                Map<String, ListTelpPenting> data = response.body().getData();
                Log.d("iconnn",response.body().getIcon());
                title.setText("BeritaOnline");

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
            public void onFailure(Call<TelpPentingModel> call, Throwable t) {

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
            Intent intent = new Intent(MenuTelpPenting.this, CariTelpPenting.class);
            startActivity(intent);

        }
        else if(id==android.R.id.home)
        {
            Intent i = new Intent(MenuTelpPenting.this, MainActivity.class);
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
