package projekpati.com.projekpati.Otomotif;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Kuliner.JenisMakananAdapter;
import projekpati.com.projekpati.Model.Otomotif.JenisOtomotif;
import projekpati.com.projekpati.Model.Otomotif.JenisOtomotifLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KategoriOtomotifFragment extends Fragment {

    GridView listView, listView2;
    List<JenisOtomotif> list = new ArrayList<>();
    List<JenisOtomotif> list2 = new ArrayList<>();
    public KategoriOtomotifFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori_otomotif, container, false);
        listView = (GridView) view.findViewById(R.id.listMobil);
        listView2 = view.findViewById(R.id.listMotor);
        getAllJenis();
        return view;
    }

    public void getAllJenis(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisOtomotifLengkap> call = api.tampilJenisMobilOtomotif();

        call.enqueue(new Callback<JenisOtomotifLengkap>() {
            @Override
            public void onResponse(Call<JenisOtomotifLengkap> call, final Response<JenisOtomotifLengkap> response) {
                Map<String, JenisOtomotif> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }

                listView.setAdapter(new JenisOtomotifAdapter(getContext(), R.layout.jenis_otomotif_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JenisOtomotifLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),TampilOtomotifByJenis.class);
                intent.putExtra("id_otomotif",list.get(position).getId());
                startActivity(intent);
            }
        });


        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisOtomotifLengkap> call2 = api2.tampilJenisMotorOtomotif();
        call2.enqueue(new Callback<JenisOtomotifLengkap>() {
            @Override
            public void onResponse(Call<JenisOtomotifLengkap> call, Response<JenisOtomotifLengkap> response) {
                Map<String, JenisOtomotif> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list2.add(data.get(String.valueOf(i)));
                }

                listView2.setAdapter(new JenisOtomotifAdapter(getContext(), R.layout.jenis_otomotif_adapter, list2));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<JenisOtomotifLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),TampilOtomotifByJenis.class);
                intent.putExtra("id_otomotif",list2.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
