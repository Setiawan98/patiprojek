package projekpati.com.projekpati.BeritaOnline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.BeritaOnline.JenisBeritaOnlineAdapter;
import projekpati.com.projekpati.BeritaOnline.TampilBeritaOnlineByJenis;
import projekpati.com.projekpati.Model.BeritaOnline.JenisBeritaOnline;
import projekpati.com.projekpati.Model.BeritaOnline.JenisBeritaOnlineLengkap;
import projekpati.com.projekpati.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriBeritaOnlineFragment extends Fragment {
    GridView listBeritaOnline;
    List<JenisBeritaOnline> list = new ArrayList<>();

    public KategoriBeritaOnlineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kategori_berita_online, container, false);
        listBeritaOnline = (GridView) view.findViewById(R.id.listBeritaOnline);
        getAllJenis();
        return view;
    }
    public void getAllJenis(){
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<JenisBeritaOnlineLengkap> call = api.tampilJenisBeritaOnline();

        call.enqueue(new Callback<JenisBeritaOnlineLengkap>() {
            @Override
            public void onResponse(Call<JenisBeritaOnlineLengkap> call, final Response<JenisBeritaOnlineLengkap> response) {
                Map<String, JenisBeritaOnline> data = response.body().getData();

                Log.w("Response", new Gson().toJson(response.body()));
                for (int i = 1; i <= response.body().getJumlah_data(); i++)
                {
                    list.add(data.get(String.valueOf(i)));
                }

                listBeritaOnline.setAdapter(new JenisBeritaOnlineAdapter(getContext(), R.layout.jenis_berita_online_adapter, list));
                Toast.makeText(getContext().getApplicationContext(),"Sukses", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<JenisBeritaOnlineLengkap> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext().getApplicationContext(),t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("onResponse", t.toString());
            }
        });

        listBeritaOnline.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), TampilBeritaOnlineByJenis.class);
                intent.putExtra("id_beritaOnline",list.get(position).getId());
                intent.putExtra("kategori",list.get(position).getNama());
                startActivity(intent);
            }
        });

    }


}
