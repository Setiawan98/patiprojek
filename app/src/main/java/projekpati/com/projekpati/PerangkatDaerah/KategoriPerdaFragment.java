package projekpati.com.projekpati.PerangkatDaerah;


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
import projekpati.com.projekpati.Model.PerangkatDaerah.JenisPerda;
import projekpati.com.projekpati.Model.PerangkatDaerah.JenisPerdaLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KategoriPerdaFragment extends Fragment {
    GridView listPerda;
    List<JenisPerda> list = new ArrayList<>();

    public KategoriPerdaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori_perda, container, false);
        listPerda = (GridView) view.findViewById(R.id.listPerda);
        getAllJenis();
        return view;
    }
    public void getAllJenis(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisPerdaLengkap> call = api.tampilJenisPerda();

        call.enqueue(new Callback<JenisPerdaLengkap>() {
            @Override
            public void onResponse(Call<JenisPerdaLengkap> call, final Response<JenisPerdaLengkap> response) {
                Map<String, JenisPerda> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }

                listPerda.setAdapter(new JenisPerdaAdapter(getContext(), R.layout.jenis_perda_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JenisPerdaLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listPerda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), TampilPerdaByJenis.class);
                intent.putExtra("id_perda",list.get(position).getId());
                intent.putExtra("kategori",list.get(position).getNama());
                startActivity(intent);
            }
        });

    }
}
