package projekpati.com.projekpati.Pangan;

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
import projekpati.com.projekpati.API.RetrofitClientInstanceDemo;
import projekpati.com.projekpati.Model.Pangan.JenisPangan;
import projekpati.com.projekpati.Model.Pangan.JenisPanganLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriPanganFragment extends Fragment {
    GridView listPangan;
    List<JenisPangan> list = new ArrayList<>();

    public KategoriPanganFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori_pangan, container, false);
        listPangan = (GridView) view.findViewById(R.id.listPangan);
        getAlljenis();
        return view;
    }
    public void getAlljenis(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstanceDemo.getRetrofitInstance().create(API.class);
        Call<JenisPanganLengkap> call = api.tampilJenisPangan();

        call.enqueue(new Callback<JenisPanganLengkap>() {
            @Override
            public void onResponse(Call<JenisPanganLengkap> call, final Response<JenisPanganLengkap> response) {
                Map<String, JenisPangan> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }

                listPangan.setAdapter(new JenisPanganAdapter(getContext(), R.layout.jenis_pangan_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JenisPanganLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listPangan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),TampilPanganByJenis.class);
                intent.putExtra("id_pangan",list.get(position).getId());
                intent.putExtra("kategori",list.get(position).getNama());
                startActivity(intent);
            }
        });

    }

}
