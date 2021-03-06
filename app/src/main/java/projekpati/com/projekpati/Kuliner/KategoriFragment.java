package projekpati.com.projekpati.Kuliner;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Kuliner.JenisKuliner;
import projekpati.com.projekpati.Model.Kuliner.JenisKulinerLengkap;
import projekpati.com.projekpati.Model.Kuliner.JenisMakanan;
import projekpati.com.projekpati.Model.Kuliner.JenisMakananLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class KategoriFragment extends Fragment {

    GridView listView, listMakanan;
    List<JenisKuliner> list = new ArrayList<>();
    List<JenisMakanan> list2 = new ArrayList<>();
    public KategoriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori, container, false);
        listView = (GridView) view.findViewById(R.id.listKuliner);
        listMakanan = view.findViewById(R.id.listMakanan);
        getAllJenis();
        return view;
    }

    public void getAllJenis(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisKulinerLengkap> call = api.tampilSemuaJenis();

        call.enqueue(new Callback<JenisKulinerLengkap>() {
            @Override
            public void onResponse(Call<JenisKulinerLengkap> call, final Response<JenisKulinerLengkap> response) {
                Map<String, JenisKuliner> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                        list.add(data.get(String.valueOf(i)));
                }

                listView.setAdapter(new JenisAdapter(getContext(), R.layout.jenis_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JenisKulinerLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),TampilKulinerByJenis.class);
                intent.putExtra("id_kuliner",list.get(position).getId());
                intent.putExtra("kategori",list.get(position).getNama());
                startActivity(intent);
            }
        });


        API api2 = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisMakananLengkap> call2 = api2.tampilJenisMakanan();
        call2.enqueue(new Callback<JenisMakananLengkap>() {
            @Override
            public void onResponse(Call<JenisMakananLengkap> call, Response<JenisMakananLengkap> response) {
                Map<String, JenisMakanan> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list2.add(data.get(String.valueOf(i)));
                }

                listMakanan.setAdapter(new JenisMakananAdapter(getContext(), R.layout.jenis_makanan_adapter, list2));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<JenisMakananLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });


        listMakanan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),TampilJenisMakanan.class);
                intent.putExtra("id_kuliner",list2.get(position).getId());
                intent.putExtra("makanan",list2.get(position).getNama());
                startActivity(intent);
            }
        });
    }

}
