package projekpati.com.projekpati.Kerjaan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import projekpati.com.projekpati.Kerjaan.JenisKerjaanAdapter;
import projekpati.com.projekpati.Kerjaan.TampilKerjaanByJenis;
import projekpati.com.projekpati.Model.Kerjaan.JenisKerjaan;
import projekpati.com.projekpati.Model.Kerjaan.JenisKerjaanLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KategoriKerjaanFragment extends Fragment {
    GridView listKerjaan;
    List<JenisKerjaan> list = new ArrayList<>();

    public KategoriKerjaanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori_kerjaan, container, false);
        listKerjaan = (GridView) view.findViewById(R.id.listKerjaan);
        getAllJenis();
        return view;
    }
    public void getAllJenis(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisKerjaanLengkap> call = api.tampilJenisKerjaan();

        call.enqueue(new Callback<JenisKerjaanLengkap>() {
            @Override
            public void onResponse(Call<JenisKerjaanLengkap> call, final Response<JenisKerjaanLengkap> response) {
                Map<String, JenisKerjaan> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }

                listKerjaan.setAdapter(new JenisKerjaanAdapter(getContext(), R.layout.jenis_kerjaan_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JenisKerjaanLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listKerjaan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), TampilKerjaanByJenis.class);
                intent.putExtra("id_kerjaan",list.get(position).getId());
                intent.putExtra("kategori",list.get(position).getNama());
                startActivity(intent);
            }
        });

    }


}
